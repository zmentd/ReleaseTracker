EXECUTE tmp_insert USING
<#assign index = "first">
<#list root.columns as column>
	  <#if index = "first">
		  ${column}
	  </#if>
	  <#if index != "first">
		, ${column}
	  </#if>
	  <#assign index = "not_first">
</#list>
        ;
