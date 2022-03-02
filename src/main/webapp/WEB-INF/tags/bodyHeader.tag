<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags" %>

<%@ attribute name="menuName" required="true" rtexprvalue="true"
              description="Name of the active menu: home, myArchives, news or newArchive" %>

<ppinot:menu name="${menuName}"/>
