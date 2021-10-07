<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<petclinic:layout pageName="error">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{default}">
    <head>
        <title>Login</title>
    </head>
    <body class="text-center">
        <div layout:fragment="content">
            <form class="form-signin" action="/login" method="post">
                <img class="mb-4" src="https://www.pinclipart.com/picdir/middle/416-4167138_login-icon-vector-png-clipart.png" alt="" width="72" height="72" />
                
                 <div >
        
                <c:if test="${successMessage}">
                    <c:out value="${successMessage}"></c:out>
                    </c:if>
                </div>
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        
        
                <div >
        
                <c:if test="${param.error}">
                    Invalid email and password.
                    </c:if>
                </div>
                <div>
                <c:if test="${param.logout}">
                    You have been logged out.
                    </c:if>
                </div>
                <label for="inputUsername" class="sr-only">Usermane</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username" required="" />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="" />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
            <form class="form-signin" action="/signup" method="get">
                <button class="btn btn-md btn-success btn-block" type="Submit">Signup Here</button>
            </form>
        </div>
    </body>
</html>
</petclinic:layout>