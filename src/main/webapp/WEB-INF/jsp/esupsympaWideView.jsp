<%--

    Copyright (C) 2010 INSA LYON http://www.insa-lyon.fr
    Copyright (C) 2010 Esup Portail http://www.esup-portail.org
    @Author (C) 2010 Olivier Franco <Olivier.Franco@insa-lyon.fr>
    @Contributor (C) 2010 Doriane Dusart <Doriane.Dusart@univ-valenciennes.fr>
    @Contributor (C) 2010 Jean-Pierre Tran <Jean-Pierre.Tran@univ-rouen.fr>
    @Contributor (C) 2010 Vincent Bonamy <Vincent.Bonamy@univ-rouen.fr>

    Licensed under the GPL License, (please see the LICENCE file)

--%>

<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<h3 class="portlet-section-header"><spring:message code="title" htmlEscape="true"/></h3>
<span id="sympalink"><p><img src="<%=request.getContextPath()%>/media/icons/application_go.png" /><a href="${homeUrl}" target="blank"><spring:message code="gotoSympa" htmlEscape="true"/></a></p></span>
<c:set var="namespace"><portlet:namespace/></c:set>
<portlet:actionURL var="actionURL">
</portlet:actionURL>
<%-- create list --%>
<c:if test="${not empty createList and fn:length(createList) gt 0}">
	<div class="portlet-msg-info">
		<c:forEach items="${createList}" var="create">
			<a class="portlet-menu-item" href="<c:out value="${create.accessUrl}" escapeXml="true"/>"><spring:message code="createNewList" htmlEscape="true"/> <strong>@<c:out value="${create.serverName}" escapeXml="true"/></strong></a><br/>
		</c:forEach>
	</div>
</c:if>
<form method="post" class="c" action="<c:out value="${actionURL}" escapeXml="true"/>">
	<span><spring:message code="search.title" htmlEscape="true"/> : </span>
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
	<input type="submit" class="portlet-form-button" value="<spring:message code="search.validate" htmlEscape="true"/>"/>
</form>
<c:choose>
<c:when test="${not empty sympaList and fn:length(sympaList) gt 0}">
	<table class="data centered" border="0" cellpadding="0" cellspacing="0" summary="<spring:message code="results.summary" htmlEscape="true"/>" width="90%">
		<caption class="portlet-table-subheader"><spring:message code="results.caption" arguments="${fn:length(sympaList)}" htmlEscape="true"/></caption>
		<thead class="portlet-table-header">
			<tr>
				<td><spring:message code="list.name" htmlEscape="true"/></td>
				<td><spring:message code="list.subject" htmlEscape="true"/></td>
				<td><spring:message code="list.subscriber" htmlEscape="true"/></td>
				<td><spring:message code="list.owner" htmlEscape="true"/></td>
				<td><spring:message code="list.editor" htmlEscape="true"/></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sympaList}" var="list" varStatus="varStatus">
			<tr<c:if test="${varStatus.index%2!=0}"> class="portlet-table-alternate"</c:if>>
				<td><a class="portlet-menu-item" href="<c:out value="${list.listUrl}" escapeXml="true"/>" target="_blank" title="<spring:message code="gotoList" arguments="${list.address}" htmlEscape="true"/>"><c:out value="${list.address}" escapeXml="true"/></a></td>
				<td><c:out value="${list.subject}" escapeXml="true"/></td>
				<td class="c"><insa:icon value="${list.subscriber}"/></td>
				<td class="c">
					<c:choose>
					<c:when test="${list.owner==true}">
					<a class="portlet-menu-item" href="<c:out value="${list.listAdminUrl}" escapeXml="true"/>" target="_blank" title="<spring:message code="gotoListAdmin" arguments="${list.address}" htmlEscape="true"/>"><insa:icon value="${list.owner}"/></a>
					</c:when>
					<c:otherwise>
					<insa:icon value="${list.owner}"/>
					</c:otherwise>
					</c:choose>
				</td>
				<td class="c"><insa:icon value="${list.editor}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</c:when>
<c:otherwise>
	<div class="portlet-msg-alert"><p><spring:message code="results.noResults" htmlEscape="true"/></p></div>
</c:otherwise>
</c:choose>