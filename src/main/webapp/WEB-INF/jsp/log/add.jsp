<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="newLog">

	<form:form modelAttribute="log" class="form-horizontal"
		id="add-log-form" enctype="multipart/form-data">
		<div class="form-group has-feedback">
			<form:hidden path="userId" />
			<form:hidden path="uploadDate" />
			<petclinic:inputField label="Title" name="title" />
			<div class="${cssGroup}">

				<label class="col-sm-2 control-label">File: </label>
				<div class="col-sm-10">
					<input type="file" name="file" size="50" required="required" />
					<p style="color:gray">Only select file with '.log' extension</p>
					<c:if test="${valid}">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span>
					</c:if>
					<c:if test="${status.error}">
						<span class="glyphicon glyphicon-remove form-control-feedback"
							aria-hidden="true"></span>
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
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