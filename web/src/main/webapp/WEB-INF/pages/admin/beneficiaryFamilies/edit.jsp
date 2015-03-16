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
			dateFormat: 'dd-mm-yy',
			yearRange: '-100y:c+nn',
			maxDate: '-1d'
		});
	});
});
</script>
<!-- Page content starts here -->
<div id="body">
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">EDIT FAMILY MEMBER</h5>
			<form:form action="processEdit" method="post" modelAttribute="beneficiaryFamilyMember">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="beneficiaryFamilyMember">
					<div class="response-msg error">
						<spring:bind path="beneficiaryFamilyMember">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="beneficiaryFamilyMember.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<table class="form">
					<tr>
						<td class="key">
						First name:
						</td>
						<td>
							<form:input path="firstName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Last name:
						</td>
						<td>
							<form:input path="lastName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Relationship:
						</td>
						<td>
							<form:select path="familyRelationship" cssClass="text">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${familyRelationships}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date of birth:
						</td>
						<td>
							<form:input path="dateOfBirth" cssClass="text"/>
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
						Occupation:
						</td>
						<td>
							<form:input path="occupation" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Gender:
						</td>
						<td>
							<form:select path="gender" cssClass="text" id="group">
								<form:option value="0">MALE</form:option>
								<form:option value="1">FEMALE</form:option>
							</form:select>
						</td>
					</tr>
				</table>
				<form:hidden path="beneficiary.id"/>
				<form:hidden path="id"/>
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