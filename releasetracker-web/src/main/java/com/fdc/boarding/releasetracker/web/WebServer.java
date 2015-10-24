package com.fdc.boarding.releasetracker.web;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdc.boarding.core.log.LoggerProxy;

import fdinet.core.APIDef;
import fdinet.core.FDiNetException;
import fdinet.core.FDiNetPropertiesLoader;
import fdinet.core.FDiNetServiceProxyBase;
import fdinet.core.HealthStatus;
import fdinet.core.Processor;
import fdinet.core.Result;
import fdinet.core.ServiceDef;
import fdinet.core.TypeSerializer.EncodingType;
import fdinet.core.TypedResponseHandler;
import fdinet.core.VoidHolder;
import fdinet.core.WebDef;
import fdinet.core.messaging.Msg;
import fdinet.core.validations.PathValidationSet;
import fdinet.core.web.DefaultFDiNetHandler;
import fdinet.core.web.NoPrincipalExtractor;
import fdinet.core.web.PrincipalExtractor;
import fdinet.core.web.StaticContentHolder;
import fdinet.core.web.WebHookService;
import fdinet.core.web.WebProxyConfig;
import fdinet.services.core.ServiceInfo;

public final class WebServer extends WebHookService {

	static final LoggerProxy 			logger				= LoggerProxy.getLogger("WebServer");
	static final Logger LOG = LoggerFactory.getLogger("WebProxy");

	private Server 						server;
	private final Set<ServiceInfo> 		installedServices 	= new TreeSet<>();
	private static boolean 				running 			= true;

	public WebServer(String webProxyServiceName) {
		super(webProxyServiceName);
	}

	@Override
	protected final void initAfter() {
		WebProxyConfig config;
		try {
			config = getServiceConfiguration();
		} catch (Exception e) {
			LOG.error("Error on Startup", e);
			throw new RuntimeException(e);
		}
		final WebProxyConfig c = config;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					init(c);
				} catch (Exception e) {
					LOG.error("Error on Startup", e);
				}
			}
		}, "Web Main").start();
	}

	private void init(WebProxyConfig config) throws Exception {
		server = new Server();
		Boolean isSsl = Boolean.valueOf(FDiNetPropertiesLoader.getInstance().getProperty("jetty.isSsL"));
		if (!isSsl) {
			ServerConnector connector = new ServerConnector(server);
			connector.setPort(config.getPort());
			server.setConnectors(new Connector[]{connector});
		} else {
			HttpConfiguration https = new HttpConfiguration();
			https.addCustomizer(new SecureRequestCustomizer());
			SslContextFactory sslContextFactory = new SslContextFactory();
			sslContextFactory.setKeyStorePath(System.getProperty("javax.net.ssl.keyStore"));
			sslContextFactory.setKeyStorePassword(System.getProperty("javax.net.ssl.keyStorePassword"));
			sslContextFactory.setKeyManagerPassword(System.getProperty("javax.net.ssl.keyStorePassword"));
			Boolean needMutualAuth = Boolean.valueOf(FDiNetPropertiesLoader.getInstance().getProperty("jetty.needMutualAuth"));
			if(needMutualAuth) {
				sslContextFactory.setNeedClientAuth(true);
				sslContextFactory.setValidatePeerCerts(true);
				sslContextFactory.setTrustStorePath(System.getProperty("javax.net.ssl.trustStore"));
				sslContextFactory.setTrustStorePassword(System.getProperty("javax.net.ssl.trustStorePassword"));
			}
			ServerConnector sslConnector = new ServerConnector(server,
					new SslConnectionFactory(sslContextFactory, "http/1.1"),
					new HttpConnectionFactory(https));
			sslConnector.setPort(config.getPort());
			server.setConnectors(new Connector[]{sslConnector});
		}

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		String path = config.getContextPath();
		context.setContextPath(path != null ? path : "/");
		
		server.setHandler(context);

		if(config.getStaticConfigs() != null) {
			for(WebProxyConfig.StaticContentConfig c: config.getStaticConfigs()) {
				LOG.info("Adding static config " + c.getContentRoot() + " " + c.getUrlPath());
				context.addServlet(new StaticContentHolder(c.getContentRoot()),c.getUrlPath());
			}
		} else {
			String 						resourcePath;
			URL							resource;
			
			resource		= WebServer.class.getClassLoader().getResource("web.marker");
			if( resource != null ){
				resourcePath = resource.toExternalForm();
				resourcePath = resourcePath.substring(0, resourcePath.length() - 10 );
				LOG.info("Adding static config " + resourcePath + " " + "/releasetracker/*");
				context.addServlet(new StaticContentHolder(resourcePath), "/releasetracker/*");
			}
			
			resource	= WebServer.class.getClassLoader().getResource("logo.png");
			if( resource != null ){
				resourcePath = resource.toExternalForm();
				resourcePath = resourcePath.substring(0, resourcePath.length() - 8);
				LOG.info("Adding static config " + resourcePath + " " + "/*");
				context.addServlet(new StaticContentHolder(resourcePath), "/*");
			}
		}

		final PrincipalExtractor pExt;
		if(config.isRequirePrincipal()) {
			pExt = (PrincipalExtractor)Class.forName(config.getPrincipalExtractorClass()).newInstance();
			context.addFilter(new FilterHolder(new Filter() {

				@Override
				public void init(FilterConfig filterConfig) throws ServletException {
				}

				@Override
				public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
					if(pExt.getPrincipal((HttpServletRequest)servletRequest) == null) {
						((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
					filterChain.doFilter(servletRequest, servletResponse);
				}

				@Override
				public void destroy() {
				}
			}), "/*", EnumSet.of(DispatcherType.REQUEST));
			LOG.info("Using Principal Extractor : " + pExt);
		} else {
			pExt = NoPrincipalExtractor.INSTANCE;
		}

		Map<String, String> headerMap = new HashMap<>();
		for(WebProxyConfig.HeaderMap h: config.getHeaderMaps()) {
			headerMap.put(h.getHttpKey(), h.getMsgKey());
		}
		Map<String, String> responseMap = new HashMap<>();
		for(WebProxyConfig.HeaderMap h2: config.getResponseMaps()) {
			responseMap.put(h2.getMsgKey(), h2.getHttpKey());
		}
		if(config.getProcessor() == null) {
			config.setProcessor(Processor.NULL_PROCESSOR);
		}
		for(ServiceInfo i: config.getProxiedServices()) {
			addService(context, i, pExt,  config.getProcessor(), headerMap, responseMap);
		}

		LOG.info("Max Content Size set to " + context.getMaxFormContentSize());

		server.setStopAtShutdown(true);
		server.setStopTimeout(config.getStopTimeoutMillis());
		server.start();
		server.join();
	}

	private void addService(ServletContextHandler context, ServiceInfo svi, PrincipalExtractor pExt, Processor processor, Map<String, String> headerMaps, Map<String, String> responseMaps) throws FDiNetException {
		if(!installedServices.add(svi)) {
			LOG.warn("Duplicate service info, ignoring duplicate " + svi);
			return;
		}
		FDiNetServiceProxyBase proxy = new FDiNetServiceProxyBase(svi.getServiceName(), EncodingType.JSON, svi.getServiceVersion(), processor);
		ServiceDef def = null;
		try {
			Result<ServiceDef> result = new Result<>();
			proxy.getServiceDef(null, result);
			def = result.get();
		} catch (Exception e) {
			LOG.warn("Service " + svi.getServiceName() + " not currently running, moving to next configured service");
			return;
		}
		WebDef d = def.getWebDef();
		if(d == null) {
			LOG.warn("No Web configuration for " + svi);
			return;
		}
		if(context.getMaxFormContentSize() < def.getWebDef().getMaxContentSize()) {
			context.setMaxFormContentSize(def.getWebDef().getMaxContentSize());
		}
		String servicePath = d.getPath();
		servicePath = servicePath.startsWith("/") ? servicePath : "/" + servicePath;
		for(APIDef api: def.getApis()) {
			if(api.getWebDef() != null) {
				PathValidationSet val = null;
				try {
					val = proxy.getValidations(api.getName());
				} catch(Exception ex) {
					LOG.warn("Error in getting API validations : ", ex);
				}
				DefaultFDiNetHandler handler = new DefaultFDiNetHandler(proxy, api, val, pExt, headerMaps, responseMaps);
				String apiPath = (api.getWebDef().getPath() == null) ? api.getName() : api.getWebDef().getPath();
				apiPath = apiPath.startsWith("/") ? apiPath : "/" + apiPath;
				LOG.info(servicePath +"/v" + svi.getServiceVersion() + apiPath);
				context.addServlet(new ServletHolder(handler), servicePath +"/v" + svi.getServiceVersion() + apiPath);
			}
		}
	}

	@Override
	public void shutDownBefore() {
		running = false;
		Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
			@Override
			public void run() {
				try {
					server.stop();
				} catch (Exception e) {
					LOG.error("Exception stopping web proxy service", e);
				}
			}
		}, 30000L, TimeUnit.MILLISECONDS);
	}

	@Override
	public void healthCheck(VoidHolder request, TypedResponseHandler<HealthStatus> handler) {
		if (running) {
			HealthStatus h = new HealthStatus(Boolean.TRUE);
			h.setDescription("Webproxy running without issue");
			handler.handleResponse(h);
		} else {
			handler.handleError(Msg.StatusCode.GENERAL_ERROR, "Webproxy stopped");
		}
	}

	@Override
	public void restart(VoidHolder request, TypedResponseHandler<VoidHolder> handler) {
	}
}
