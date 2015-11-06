INSERT INTO ${root.tableName}
<#assign index = "first">
<#list root.headers as header>
	  <#if index = "first">
		( ${header}
	  </#if>
	  <#if index != "first">
		, ${header}
	  </#if>
	  <#assign index = "not_first">
</#list>
        )
    VALUES
<#assign index = "first">
<#list root.columns as column>
	  <#if index = "first">
		( ${column}
	  </#if>
	  <#if index != "first">
		, ${column}
	  </#if>
	  <#assign index = "not_first">
</#list>
        );
