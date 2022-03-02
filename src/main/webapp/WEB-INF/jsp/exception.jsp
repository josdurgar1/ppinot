<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>

<ppinot:layout pageName="error">

    <spring:url value="/resources/images/ppinot.png" var="ppinotImage"/>
    <img src="${ppinotImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</ppinot:layout>
