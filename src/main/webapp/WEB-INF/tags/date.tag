<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<html>  
<head>  
<title>fmt:formatDate</title>  
</head>  
<body>  
<h2>Different Formats of the Date</h2>  
<c:set var="Date" value="<%=new java.util.Date()%>" />  
<p>  
Formatted Date and Time in short style :  
<fmt:formatDate type="both" dateStyle="short" timeStyle="short"  
value="${Date}" />  
</p>  
  
</body>  
</html>  
