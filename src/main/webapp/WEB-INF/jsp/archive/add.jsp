<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="newArchive">

 <form:form modelAttribute="archive" class="form-horizontal" id="add-archive-form" enctype="multipart/form-data">
        <div class="form-group has-feedback">
           <form:hidden path = "userId" />
           <form:hidden path = "assignedMetrics" />
           <form:hidden path = "uploadDate" />
            <petclinic:inputField label="Title" name="title"/>
         	<label>File: </label> <input type="file" name="file" size="50" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="btn btn-default" type="submit">Add Archive</button>
             </div>
        </div>
    </form:form>


</petclinic:layout>