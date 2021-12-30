<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<petclinic:layout pageName="Log Details">
	<h2>
		<c:out value="${log.title }"></c:out>
		Details
	</h2>

	<a href='<c:out value="${log.downloadUri }"></c:out>'>Download Log</a>

	<jstl:if test="${metrics.size()>0 }">
	<div class="success">
		<c:out value="${successMessage }"></c:out>
	</div>
	<h3>Associated Metrics</h3>
		<table id="metricsTable" class="table table-striped">
			<thead>
				<tr>
			     	<th style="width: 200px;">Type Measure</th>
					<th style="width: 200px;">Name</th>
					<th style="width: 200px;">Description</th>
					<th style="width: 200px;">Date</th>
					<th style="width: 200px;">Time Measure Type</th>
					<th style="width: 200px;">Measures</th>
					<th style="width: 200px;">Scale</th>
					<th style="width: 200px;">Unit Of Measure</th>
					<th style="width: 200px;">Delete Metric</th>
				</tr>
			</thead>
			<tbody>

				<jstl:forEach items="${metrics}" var="metric">
					<tr>
					    <td><c:out value="${metric.typeMeasure}" /></td>
						<td><c:out value="${metric.name}" /></td>
						<td><c:out value="${metric.description}" /></td>
						<td><c:out value="${metric.creationDate}" /></td>
						<td><c:out value="${metric.timeMeasureType}" /></td>
						<td><c:out value="${metric.measure.size()}" /></td>
						<td><c:out value="${metric.scale}" /></td>
						<td><c:out value="${metric.unitOfMeasure}" /></td>

						<td><spring:url value="delete?logId={logId}" var="deleteUrl">
								<spring:param name="logId" value="${log.id}" />
							</spring:url>
							<button onclick="sample()" class="btn btn-link">Delete</button></td>
					</tr>
				</jstl:forEach>
			</tbody>
		</table>
	</jstl:if>
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

