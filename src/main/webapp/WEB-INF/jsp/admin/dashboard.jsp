<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags"%>

<ppinot:layout pageName="dashboard">

	<h2>
		<c:if test="${username['new']}">New </c:if>
		<c:out value="${firsName}"></c:out>
	</h2>
	<h3>
		<c:out value="${adminMessage }"></c:out>
	</h3>

</ppinot:layout>