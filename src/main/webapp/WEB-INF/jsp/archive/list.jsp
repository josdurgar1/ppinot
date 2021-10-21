<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="myArchives">
	<h2>My Archives</h2>

    <table id="archivesTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 200px;">Details</th>
            <th style="width: 200px;">Title</th>
            <th style="width: 200px;">Upload Date</th>
            <th style="width: 200px;">Assigned Metrics</th>
            <th style="width: 200px;">Add New Metric</th>
            <th style="width: 200px;">Delete Archive</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${myArchives}" var="archive">
            <tr>
                <td>
                    <spring:url value="details?archiveId={archiveId}" var="ownerUrl">
                        <spring:param name="archiveId" value="${archive.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${archive.title}"/></a>
                </td>
                <td>
                    <c:out value="${archive.title}"/>
                </td>
                <td>
               <fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${archive.uploadDate}" />
                </td>
                  <td>
                    <c:out value="${archive.assignedMetrics}"/>
                </td>
                 <td>
                   <spring:url value="/metrics/new?archiveId={archiveId}" var="assignUrl">
                        <spring:param name="archiveId" value="${archive.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(assignUrl)}"><c:out value="${archive.title}"/></a>
                </td>
                 <td>
                <spring:url value="delete?archiveId={archiveId}" var="deleteUrl">
                        <spring:param name="archiveId" value="${archive.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(deleteUrl)}"><c:out value="Delete"/></a>
                </td>
         </tr>
        </c:forEach>
        </tbody>
    </table>




</petclinic:layout>