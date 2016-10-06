<%@ tag body-content="empty" isELIgnored="false" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/include.jsp" %>
<%@ attribute name="value" required="true" type="java.lang.Boolean" %>
<c:url var="iconPath" scope="page" value="/media/icons/"/>
<c:choose>
	<c:when test="${value == true}">
             <span class="glyphicon glyphicon-ok-circle" style="color:#5cb85c;" aria-label="<spring:message code='yes' htmlEscape='true'/>" title="<spring:message code='yes' htmlEscape='true'/>" tabindex="0"></span>
	</c:when>
	<c:otherwise>
             <span class="glyphicon glyphicon-remove-circle" style="color:#d9534f;" aria-label="<spring:message code='no' htmlEscape='true'/>" title="<spring:message code='no' htmlEscape='true'/>" tabindex="0"></span>
	</c:otherwise>
</c:choose>

