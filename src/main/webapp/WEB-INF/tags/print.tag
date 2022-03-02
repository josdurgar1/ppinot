<%--
- message.tag
--%>

<%@tag language="java" body-content="empty" trimDirectiveWhitespaces="true"
	import="java.util.Collection, java.util.Map, java.util.HashMap"
%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="ppinot" tagdir="/WEB-INF/tags"%>

<%@attribute name="var" required="false" type="java.lang.String"%>
<%@attribute name="value" required="true" type="java.lang.Object"%>

<%
	String variable;
	Object value;
	String text;	
	
	variable = (String)jspContext.getAttribute("var");
	value = jspContext.getAttribute("value");
	text = value.toString();
	if (text == null)
		text = "";
	else 
		text = text.trim();
	jspContext.setAttribute("text", text);
	if (variable != null)
		request.setAttribute(variable, text);
%>

<jstl:if test="${var == null}">
	<jstl:out value="${text}"/>
</jstl:if>
