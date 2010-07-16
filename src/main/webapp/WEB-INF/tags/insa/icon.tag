<%@ tag body-content="empty" isELIgnored="false" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tags/include.jsp" %>
<%@ attribute name="value" required="true" type="java.lang.Boolean" %>
<c:url var="iconPath" scope="page" value="/media/icons/"/>
<c:choose>
	<c:when test="${value == true}">
		<c:url var="iconURL" value="/media/icons/stock_ok.png"/>
		<spring:message code="yes" htmlEscape="true" var="iconAlt"/>
	</c:when>
	<c:otherwise>
		<c:url var="iconURL" value="/media/icons/gtk-stop.png"/>
		<spring:message code="no" htmlEscape="true" var="iconAlt"/>
	</c:otherwise>
</c:choose>
<img src="<c:out value="${iconURL}" escapeXml="true"/>" alt="<c:out value="${iconAlt}"/>"/>
<c:remove var="iconURL"/>
<c:remove var="iconAlt"/>