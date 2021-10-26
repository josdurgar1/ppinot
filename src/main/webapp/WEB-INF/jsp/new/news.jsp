<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="news">

	<h2>Last Changes</h2>
	<p>--------------------</p>
	<br>

	<table id="logsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 200px;">Author</th>
				<th style="width: 200px;">Message</th>
				<th style="width: 200px;">Upload Date</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${git }" var="item">
				<tr>

					<td><c:out value="${item.author.login }" /></td>

					<td><c:out value="${item.commit.message }" /></td>

					<td><c:out value="${item.commit.author.date }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</petclinic:layout>