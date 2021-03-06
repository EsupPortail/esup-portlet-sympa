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

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/media/css/jquery.mobile.structure-1.1.1.min.css" media="screen, projection"/>
<%-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mobile-1.1.1.min.js"></script>
--%>

<c:set var="namespace"><portlet:namespace/></c:set>
<portlet:actionURL var="actionURL">
</portlet:actionURL>
<div class="esup-portlet-sympa">
	<div data-role="content">
		<div data-role="navbar" class="ui-body-a">
		  <ul>
	        <li><a href="${homeUrl}" target="_blank"  data-icon="home" ><spring:message code="gotoSympa" htmlEscape="true"/></a></li>
	      </ul>
	    </div>
		<div data-role="collapsible" data-collapsed="false" data-theme="b" data-content-theme="c" data-wrapperels="span">
			<h3><spring:message code="title" htmlEscape="true"/></h3>
			<h4><spring:message code="search.title" htmlEscape="true"/></h4>
			<form method="post" action="<c:out value="${actionURL}" escapeXml="true"/>">
			  <div id="navgroup">
				<fieldset data-role="controlgroup"  data-type="horizontal" data-mini="true">
				<spring:bind path="searchForm.subscriber">
					<c:choose>
					<c:when test="${status.value == true}">
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}" checked="checked"/>
						<input type="hidden" name="_${status.expression}"/>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}"/>
						<input type="hidden" name="_${status.expression}"/>
					</c:otherwise>
					</c:choose>
					<label for="${namespace}_${status.expression}"><spring:message code="search.subscriber" htmlEscape="true"/></label>
				</spring:bind>
				<spring:bind path="searchForm.owner">
					<c:choose>
					<c:when test="${status.value == true}">
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}" checked="checked"/>
						<input type="hidden" name="_${status.expression}" value="false"/>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}"/>
						<input type="hidden" name="_${status.expression}" value="false"/>
					</c:otherwise>
					</c:choose>
					<label for="${namespace}_${status.expression}"><spring:message code="search.owner" htmlEscape="true"/></label>
				</spring:bind>
				<spring:bind path="searchForm.editor">
					<c:choose>
					<c:when test="${status.value == true}">
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}" checked="checked"/>
						<input type="hidden" name="_${status.expression}" value="false"/>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="${status.expression}" value="true" id="${namespace}_${status.expression}"/>
						<input type="hidden" name="_${status.expression}" value="false"/>
					</c:otherwise>
					</c:choose>
					<label for="${namespace}_${status.expression}"><spring:message code="search.editor" htmlEscape="true"/></label>
				</spring:bind>
				</fieldset>
			  </div>			
			  <input type="submit" value="OK"  data-mini="true" data-mini="true" data-theme="b"/>
			</form>
		
			<div data-role="collapsible" data-collapsed="false" data-theme="b" data-content-theme="c">
			<h3><spring:message code="results.caption" arguments="${fn:length(sympaList)}" htmlEscape="true"/></h3>    
			<c:choose>	
				<c:when test="${not empty sympaList and fn:length(sympaList) gt 0}">	        
				 <c:forEach items="${sympaList}" var="list" varStatus="varStatus">
					<c:choose>	
			          <c:when test="${varStatus.count eq '1'}">
			            <c:set var="collapstate" value="true"/>
			          </c:when>
						<c:otherwise>
							<c:set var="collapstate" value="false"/>
						</c:otherwise>
					</c:choose>	 
				   <div data-role="collapsible" data-collapsed="{collapstate}"> 			 
					  	<h3><a href="<c:out value="${list.listUrl}" escapeXml="true"/>" target="_blank" title="<spring:message code="gotoList" arguments="${list.address}" htmlEscape="true"/>"><c:out value="${list.address}" escapeXml="true"/></a></h3>
					  	<ul>
							<li><span><spring:message code="list.subject" htmlEscape="true"/></span> : <c:out value="${list.subject}" escapeXml="true"/></li>
							<li><span><spring:message code="list.rights" htmlEscape="true"/></span> : 
							<c:if test="${list.subscriber==true}"><spring:message code="list.subscriber" htmlEscape="true"/> -</c:if>
							<c:if test="${list.owner==true}"><spring:message code="list.owner" htmlEscape="true"/> - </c:if>
							<c:if test="${list.editor==true}"><spring:message code="list.editor" htmlEscape="true"/></c:if>
							</li>
						</ul>	  	  
					</div>
				 </c:forEach>
				</c:when>
				<c:otherwise>
					<div><p><spring:message code="results.noResults" htmlEscape="true"/></p></div>
				</c:otherwise>
			</c:choose>	
		</div>
	</div>
</div>