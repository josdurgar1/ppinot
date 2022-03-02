<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags"%>

<ppinot:layout pageName="newMetric">


	<h2>New Measure</h2>

	<table id="logsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 200px;">Details</th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td><spring:url value="newCountMeasure?logId={logId}"
						var="newCountMeasureUrl">
						<spring:param name="logId" value="${logId}" />
					</spring:url> <a href="${fn:escapeXml(newCountMeasureUrl)}">CountMeasure</a></td>
			</tr>
			<tr>
				<td><spring:url value="newTimeMeasure?logId={logId}"
						var="newTimeMeasureUrl">
						<spring:param name="logId" value="${logId}" />
					</spring:url> <a href="${fn:escapeXml(newTimeMeasureUrl)}">TimeMeasure</a></td>
			</tr>
			<tr>
				<td><spring:url value="newDataMeasure?logId={logId}"
						var="newDataMeasureUrl">
						<spring:param name="logId" value="${logId}" />
					</spring:url> <a href="${fn:escapeXml(newDataMeasureUrl)}">DataMeasure</a></td>
			</tr>

			<!-- <tr>
				<td><spring:url value="newDurationMeasure?logId={logId}"
						var="newDurationMeasureUrl">
						<spring:param name="logId" value="${logId}" />
					</spring:url> <a href="${fn:escapeXml(newDurationMeasureUrl)}">DurationMeasure</a>
				</td>
			</tr> -->
		</tbody>
	</table>


</ppinot:layout>