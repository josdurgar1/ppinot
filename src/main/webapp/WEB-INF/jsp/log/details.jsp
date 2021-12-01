<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="Log Details">
	<h2>
		<c:out value="${log.title }"></c:out>
		Details
	</h2>

	<a href='<c:out value="${log.downloadUri }"></c:out>'>Download Log</a>

	<c:if test="${metrics }>0">
	<div class="success">
		<c:out value="${successMessage }"></c:out>
	</div>
		<table id="metricsTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 200px;">Title</th>
					<th style="width: 200px;">Date</th>
					<th style="width: 1000px;">Measure</th>
					<th style="width: 200px;">Delete Metric</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach items="${metrics}" var="metric">
					<tr>
						<td><c:out value="${metric.name}" /></td>
						<td><c:out value="${metric.creationDate}" /></td>
						<td><c:out value="${metric.id}" /></td>


						<td><spring:url value="delete?logId={logId}" var="deleteUrl">
								<spring:param name="logId" value="${log.id}" />
							</spring:url>
							<button onclick="sample()" class="btn btn-link">Delete</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<script>
		function sample() {
			confirmed = confirm("Delete Metric!?");
			if (confirmed) {
				window.location = "${fn:escapeXml(deleteUrl)}";
			} else {
			}
		}
	</script>


</petclinic:layout>

