<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="archiveList">
	<h2>My Archives</h2>

	<c:out value="${fileDownloadUri }"></c:out>

    <table id="archivesTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 200px;">File</th>
            <th style="width: 200px;">Title</th>
            <th style="width: 200px;">Upload Date</th>
            <th style="width: 200px;">Assigned Metrics</th>
            <th style="width: 200px;">Add New Metric</th>
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
                   <spring:url value="assignMetric?archiveId={archiveId}" var="ownerUrl">
                        <spring:param name="archiveId" value="${archive.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${archive.title}"/></a>
                </td>
         </tr>
        </c:forEach>
        </tbody>
    </table>




</petclinic:layout>