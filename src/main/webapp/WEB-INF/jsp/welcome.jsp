<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<ppinot:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/ppinot.png" htmlEscape="true" var="ppinotImage"/>
            <img class="img-responsive" src="${ppinotImage}"/>
        </div>
    </div>
</ppinot:layout>
