<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ppinot:layout pageName="error">

    <spring:url value="/resources/images/ppinot.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>NO ENCONTRADO </h2>

    <p><c:out value="${exceptionMessage}"/></p>

</ppinot:layout>
