<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ppinot:layout pageName="login">

    <div class="text-center">
                  <form class="form-signin" action="/login" method="post">
                <img class="mb-4" src="/resources/images/avatar.png" alt="" width="72" height="72" />
                
                 <div >
        
                <c:if test="${successMessage}">
                    <c:out value="${successMessage}"></c:out>
                    </c:if>
                </div>
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        
        
                <div style="color: red;" >
        
                <c:if test="${param.error}">
                    Invalid email or password.
                    </c:if>
                </div>
                <div style="color: red;">
                <c:if test="${param.logout}">
                    You have been logged out.
                    </c:if>
                </div>
                <label for="inputUsername" class="sr-only">Usermane</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username" required=required />
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required=required />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
            <form class="form-signin" action="/signup" method="get">
                <button class="btn btn-md btn-success btn-block" type="Submit">Signup Here</button>
            </form>
         </div>

</ppinot:layout>