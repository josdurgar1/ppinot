<%--
- form-option.tag
--%>
<%@tag language="java" body-content="empty"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>
 
<%@attribute name="value" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="selected" required="false" type="java.lang.Boolean"%>

<jstl:if test="${selected == null}">
	<jstl:set var="selected" value="false"/>
</jstl:if>

<option value="${value}" <jstl:if test="${selected}">selected</jstl:if>>
	<ppinot:message code="${code}"/>
</option>
