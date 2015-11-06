DELETE FROM ${root.tableName}
<#assign index = "first">
<#list root.columnEquals as columnEqual>
	  <#if index = "first">
  WHERE ( ${columnEqual} 
	  </#if>
	  <#if index != "first">
		  AND ${columnEqual}
	  </#if>
	  <#assign index = "not_first">
</#list>
        );
