<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="archiveList">
	<h2>My Archives</h2>

    <table id="archivesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Title</th>
            <th style="width: 200px;">File Title</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${myArchives}" var="archive">
            <tr>
                <td>
                    <spring:url value="/owners/{ownerId}" var="ownerUrl">
                        <spring:param name="ownerId" value="${archives.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${archive.title}"/></a>
                </td>
                <td>
                    <c:out value="${archive.title}"/>
                </td>
                <td>
                    <c:out value="${archive.file.path}"/>
                </td>
         </tr>
        </c:forEach>
        </tbody>
    </table>




</petclinic:layout>