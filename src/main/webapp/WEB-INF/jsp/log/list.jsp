<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>




<petclinic:layout pageName="myLogs">
	<h2>My Logs</h2>
	<h3 class="error">
		<c:out value="${successMessage }"></c:out>
	</h3>
	<table id="logsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 200px;">Details</th>
				<th style="width: 200px;">Title</th>
				<th style="width: 200px;">Upload Date</th>
				<th style="width: 200px;">Assigned Metrics</th>
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
					<td><c:out value="${log.assignedMetrics}" /></td>
					<td><spring:url value="/metrics/new?logId={logId}"
							var="assignUrl">
							<spring:param name="logId" value="${log.id}" />
						</spring:url> <a href="${fn:escapeXml(assignUrl)}"><c:out
								value="${log.title}" /></a></td>
					<td><spring:url value="delete?logId={logId}" var="deleteUrl">
							<spring:param name="logId" value="${log.id}" />
						</spring:url> 
						<button onclick="sample()" class="btn btn-link">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


<script>
	function sample() {
		confirmed = confirm("Press a button!");
		if (confirmed) {
			window.location="${fn:escapeXml(deleteUrl)}";
		} else {
		}
	}
</script>

</petclinic:layout>