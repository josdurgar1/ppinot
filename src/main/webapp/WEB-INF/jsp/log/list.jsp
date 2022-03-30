<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags"%>




<ppinot:layout pageName="myLogs">
	<h2>My Logs</h2>
	<c:choose>
		<c:when test="${myLogs.size()<'1'}">
			<h3><c:out value="No tiene Logs"></c:out></h3>
		</c:when>

	<c:otherwise>
		<div class="success">
			<c:out value="${successMessage }"></c:out>
		</div>
		<table id="logsTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 200px;">Details</th>
					<th style="width: 200px;">Title</th>
					<th style="width: 200px;">Upload Date</th>
					<th style="width: 200px;">Add New Metric</th>
					<th style="width: 200px;">Delete Log</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${myLogs}" var="log">
					<tr>
						<td><spring:url value="details?logId={logId}" var="ownerUrl">
								<spring:param name="logId" value="${log.id}" />
							</spring:url> <a href="${fn:escapeXml(ownerUrl)}"><c:out
									value="${log.title}" /></a></td>
						<td><c:out value="${log.title}" /></td>
						<td><fmt:formatDate type="both" dateStyle="short"
								timeStyle="short" value="${log.uploadDate}" /></td>
						<td><spring:url value="/metrics/new?logId={logId}"
								var="assignUrl">
								<spring:param name="logId" value="${log.id}" />
							</spring:url> <a href="${fn:escapeXml(assignUrl)}"><c:out
									value="${log.title}" /></a></td>
						<td><spring:url value="delete?logId={logId}" var="deleteUrl">
								<spring:param name="logId" value="${log.id}" />
							</spring:url>
							<button onclick="sample()" class="btn btn-link">Delete</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>

	<script>
		function sample() {
			confirmed = confirm("Delete Log!?");
			if (confirmed) {
				window.location = "${fn:escapeXml(deleteUrl)}";
			} else {
			}
		}
	</script>

</ppinot:layout>