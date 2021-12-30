<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<petclinic:layout pageName="newCountmeasure">

	<h1>New CountMeasure</h1>

	<form:form modelAttribute="countMeasure" class="form-horizontal"
		id="add-archive-form" enctype="multipart/form-data">
		<div class="form-group has-feedback">
			<petclinic:inputField required="required" label="Name" name="name"></petclinic:inputField>
			<petclinic:inputField required="required" type="text"
				label="Description" name="description"></petclinic:inputField>
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
		<petclinic:inputField required="required" label="When"
				name="appliesWhen"></petclinic:inputField>
				
				<div class="form-group has-feedback">
			<div class="${cssGroup}">
				<label class="col-sm-2 control-label">GenericState </label>
				<div class="col-sm-10">
					<select id="when" name="when" required="required">
						<jstl:forEach items="${when}" var="item">
							<option value="${item}"><jstl:out value="${item }"></jstl:out></option>
						</jstl:forEach>
					</select>
				</div>
			</div>
		</div>
	
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Add Log</button>
			</div>
		</div>


	</form:form>


</petclinic:layout>