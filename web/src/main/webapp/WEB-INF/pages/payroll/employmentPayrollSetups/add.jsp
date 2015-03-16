<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
$(document).ready(function() { 
	$(function() {
		$( "#dateOfBirth" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy'
		});
	});
});
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">

		</div>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">ADDING NEW EMPLOYEE</h5>
			<form:form action="processAdd" method="post" modelAttribute="employeeForm">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="employeeForm">
					<div class="response-msg error">
						<spring:bind path="employeeForm">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="employeeForm.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<form:hidden path="employeeId"/>
				<table class="form">
					<tr>
						<td class="key">
						Name:
						</td>
						<td>
						<form:input path="name" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Address:
						</td>
						<td>
						<form:textarea path="address" cssClass="text" cssStyle="width:400px;"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Mobile No:
						</td>
						<td>
						<form:input path="mobileNo" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Telephone:
						</td>
						<td>
						<form:input path="telephone" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Gender:
						</td>
						<td>
						<form:select cssClass="text" path="gender">
						<form:option value="0">Male</form:option>
						<form:option value="1">Female</form:option>
						</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date of birth:
						</td>
						<td>
						<form:input path="dateOfBirth" cssClass="text" readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Email:
						</td>
						<td>
						<form:input path="email" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						NIC No:
						</td>
						<td>
							<form:input path="nicNo" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Remarks:
						</td>
						<td>
						<form:textarea path="remarks" cssClass="text" cssStyle="width:400px; height: 80px;"/>
						</td>
					</tr>
				</table>
				<p align="center">
					<input type="SUBMIT" value="Save" class="buttonBlue" />
				</p>
			</form:form>
		</div>
	</div>
</div>
<!-- page content end -->
<!-- include the footer file -->
<%@include file="../../common/footer.jsp"%>
<!-- footer end -->