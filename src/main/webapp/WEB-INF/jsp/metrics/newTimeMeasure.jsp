<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ppinot" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>




<ppinot:layout pageName="newTimemeasure">
<script>

function showInp(){
	  var getSelectValue = document.getElementById("timeMeasureType").value;
	  
	  switch (getSelectValue) {
	    case "CYCLIC": 
	      document.getElementById("CYCLIC").style.display ="inline-block";
	      document.getElementById("CYCLIC2").style.display ="inline-block";
	      document.getElementById("CYCLIC2").setAttribute("required", "required");
	      break;
	      
	    default:
	    	document.getElementById("CYCLIC").style.display ="none";
	    	document.getElementById("CYCLIC2").style.display = "none";
	        document.getElementById("CYCLIC2").removeAttribute("required");
	  }
}
</script>
	<h1>New TimeMeasure</h1>

	<form:form modelAttribute="timeMeasure" class="form-horizontal"
		id="add-archive-form" enctype="multipart/form-data">
		<div class="form-group has-feedback">
			<ppinot:inputField required="required" label="Name" name="name"></ppinot:inputField>
			<ppinot:inputField required="required" type="text"
				label="Description" name="description"></ppinot:inputField>

			<ppinot:inputField required="required" label="From"
				name="appliesFrom"></ppinot:inputField>

			<div class="form-group has-feedback">
				<div class="${cssGroup}">
					<label class="col-sm-2 control-label">GenericState </label>
					<div class="col-sm-10">
						<select id="from" name="from" required="required">
							<jstl:forEach items="${state}" var="item">
								<option value="${item}"><jstl:out value="${item }"></jstl:out></option>
							</jstl:forEach>
						</select>
					</div>
				</div>
			</div>

			<ppinot:inputField required="required" type="text" label="To"
				name="appliesTo"></ppinot:inputField>

			<div class="form-group has-feedback">
				<div class="${cssGroup}">
					<label class="col-sm-2 control-label">GenericState </label>
					<div class="col-sm-10">
						<select id="to" name="to" required="required">
							<jstl:forEach items="${state}" var="item">
								<option value="${item}"><jstl:out value="${item }"></jstl:out></option>
							</jstl:forEach>
						</select>
					</div>
				</div>
			</div>

		</div>
		
		<div class="form-group has-feedback">
			<div class="${cssGroup}">
				<label class="col-sm-2 control-label">Scale </label>
				<div class="col-sm-10">
					<select id="scale" name="scale" required="required">
						<jstl:forEach items="${scale_}" var="item">
							<option value="${item}"><jstl:out value="${item }"></jstl:out></option>
						</jstl:forEach>
					</select>
				</div>
			</div>
		</div>


		<div class="form-group has-feedback">
			<div class="${cssGroup}">
				<label class="col-sm-2 control-label">Unit </label>
				<div class="col-sm-10">
					<select id="unitOfMeasure" name="unitOfMeasure" required="required">
						<jstl:forEach items="${unitOfMeasure_}" var="item">
							<option value="${item}"><jstl:out value="${item }"></jstl:out></option>
						</jstl:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="form-group has-feedback">
			<div class="${cssGroup}">
				<label class="col-sm-2 control-label">TimeMeasureType </label>
				<div class="col-sm-10">
					<select onchange="showInp()" id="timeMeasureType" name="timeMeasureType"
						required="required">
						<jstl:forEach items="${timeMeasureType}" var="itemType">
							<option value="${itemType}"><jstl:out value="${itemType }"></jstl:out></option>
						</jstl:forEach>
					</select>
				</div>
			</div>
		</div>
		
		 <div class="form-group ${status.error ? 'has-error' : '' }">
        <label class="col-sm-2 control-label" id="CYCLIC" style="display: none">CYCLIC</label>

        <div class="col-sm-10">
           <form:input class="form-control" id="CYCLIC2" path="singleInstanceAggFunction"  style="display: none"/>
            <c:if test="${not status.error and not empty status.actualValue}">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            </c:if>
            <c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Add Log</button>
			</div>
		</div>


	</form:form>


</ppinot:layout>