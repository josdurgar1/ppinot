<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>

<ppinot:layout pageName="users">
    <h2>
        <c:if test="${user['new']}">New </c:if> User
    </h2>
    <h3><c:out value="${successMessage }"></c:out></h3>
    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <ppinot:inputField label="First Name" name="firstName"/>
            <ppinot:inputField label="Last Name" name="lastName"/>
            <ppinot:inputField label="Email" name="email"/>
            <ppinot:inputField label="Address" name="address"/>
            <ppinot:inputField label="City" name="city"/>
            <ppinot:inputField label="Telephone" name="telephone"/>
            <ppinot:inputField label="Username" name="username"/>
           <c:choose>
                    <c:when test="${user['new']}">
            <ppinot:inputField type="password" label="Password" name="password"/>
            </c:when>
             <c:otherwise>
             <form:hidden path="password"/>
             </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${user['new']}">
                        <button class="btn btn-default" type="submit">Add user</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update user</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</ppinot:layout>
