<%--

    Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
    Copyright (C) 2010 Esup Portail http://www.esup-portail.org
    @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
    @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
    @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>

    Licensed under the GPL License, (please see the LICENCE file)

--%>

<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<div class="esupsympaportlet-messages-mobile">
<h3><spring:message code="title" htmlEscape="true"/></h3>
<c:set var="namespace"><portlet:namespace/></c:set>
<portlet:actionURL var="actionURL">
</portlet:actionURL>
<%-- create list --%>

<c:if test="${not empty createList and fn:length(createList) gt 0}">

		<c:forEach items="${createList}" var="create">
			<a class="portlet-menu-item" href="<c:out value="${create.accessUrl}" escapeXml="true"/>"><spring:message code="createNewList" htmlEscape="true"/> <strong>@<c:out value="${create.serverName}" escapeXml="true"/></strong></a><br/>
		</c:forEach>
	
</c:if>
<form method="post" class="c" action="<c:out value="${actionURL}" escapeXml="true"/>">
	<p><span><spring:message code="search.title" htmlEscape="true"/></span> : </p>
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
		<label for="${namespace}_${status.expression}" class="portlet-form-field-label"><spring:message code="search.subscriber" htmlEscape="true"/></label>
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
		<label for="${namespace}_${status.expression}" class="portlet-form-field-label"><spring:message code="search.owner" htmlEscape="true"/></label>
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
		<label for="${namespace}_${status.expression}" class="portlet-form-field-label"><spring:message code="search.editor" htmlEscape="true"/></label>
	</spring:bind>
	<input type="submit" class="portlet-form-button" value="OK"/>
</form>
<c:choose>

<c:when test="${not empty sympaList and fn:length(sympaList) gt 0}">
        
		    <span class="esupsympaportletresults"><p>>>> <spring:message code="results.caption" arguments="${fn:length(sympaList)}" htmlEscape="true"/></p></span>
			<c:forEach items="${sympaList}" var="list" varStatus="varStatus">
			<ul>  
				<li><span><spring:message code="list.name" htmlEscape="true"/></span> : <a class="esupsympaportletLink" href="<c:out value="${list.listUrl}" escapeXml="true"/>" target="_blank" title="<spring:message code="gotoList" arguments="${list.address}" htmlEscape="true"/>"><c:out value="${list.address}" escapeXml="true"/></a></li>
				<li><span><spring:message code="list.subject" htmlEscape="true"/></span> : <c:out value="${list.subject}" escapeXml="true"/></li>
				<li><span>Droits</span> : 
				<c:if test="${list.subscriber==true}"><spring:message code="list.subscriber" htmlEscape="true"/> -</c:if>
				<c:if test="${list.owner==true}"><spring:message code="list.owner" htmlEscape="true"/> - </c:if>
				<c:if test="${list.editor==true}"><spring:message code="list.editor" htmlEscape="true"/></c:if>
				</li>
			</ul>
			</c:forEach>
</c:when>
<c:otherwise>
	<div class="portlet-msg-alert"><p><spring:message code="results.noResults" htmlEscape="true"/></p></div>
</c:otherwise>
</c:choose>
</div>