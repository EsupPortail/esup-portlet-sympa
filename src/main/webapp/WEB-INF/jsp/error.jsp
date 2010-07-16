<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<h3 class="portlet-section-header"><spring:message code="title" htmlEscape="true"/></h3>
<div class="portlet-msg-error">
	<p><spring:message code="errors.appError" htmlEscape="true"/></p>
</div>
	<%-- spring model attributes are exposed as request attribute
	<c:if test="${not empty exception}"> 
	<pre>
		<c:out value="${exception.localizedMessage}" escapeXml="true"/>
		<%//scriptlet
			java.util.Enumeration e = request.getAttributeNames();
			while ( e.hasMoreElements() ) {
				pageContext.getOut().print(e.nextElement()+"\n");
			}
			Throwable thw = (Throwable)request.getAttribute("exception");
			if ( thw != null ) {
				thw.printStackTrace(new java.io.PrintWriter(pageContext.getOut()));
			} else {
				pageContext.getOut().print("POLOM\n");
			}
		%>
	</pre>
	</c:if>
	 --%>
<div class="c">
	<portlet:renderURL var="retryURL">
	</portlet:renderURL>
	<a class="portlet-form-button c" href="<c:out value="${retryURL}" escapeXml="true"/>"><spring:message code="errors.retry"/></a>
</div>