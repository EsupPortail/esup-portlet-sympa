<%--

    Licensed to ESUP-Portail under one or more contributor license
    agreements. See the NOTICE file distributed with this work for
    additional information regarding copyright ownership.

    ESUP-Portail licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file except in
    compliance with the License. You may obtain a copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
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