<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<c:url var="getAllDistrictsJSONURL" value="/admin/districts/getAllDistrictsJSON"></c:url>
<c:url var="getAllDivisionsJSONByDistrictIdURL" value="/admin/divisions/getAllDivisionsJSONByDistrictId"></c:url>
<script type="text/javascript">
$(document).ready(
	function(){
		$.getJSON('${getAllDistrictsJSONURL}', {
			ajax : 'true'	
		}, function(data){
			var html = '<option value=""></option>';
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				html += '</option>';
			}
			//now that we have our options, give them to our select
			$('#profile-district').html(html);
		});
	}
);

$(document).ready(function() { 
	$('#profile-district').change(
		function() {
			$.getJSON('${getAllDivisionsJSONByDistrictIdURL}', {
				districtId : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value=""></option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#profile-division').html(html);
			});
		});
});

$(document).ready(function() { 
	$(function() {
		$( "#profile-dateOfBirth" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
			yearRange: '-100y:c+nn',
			maxDate: '-1d'
		});
	});
	
	$(function() {
		$( "#profile-dateOfAppoinment" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
			yearRange: '-15y:+2y'
		});
	});
});
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">ADD NEW USER</h5>
			<form:form action="processAdd" method="post" modelAttribute="user">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="user">
					<div class="response-msg error">
						<spring:bind path="user">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="user.*">
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
						Username:
						</td>
						<td>
						<form:input path="userName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Password:
						</td>
						<td>
						<form:password path="password" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Confirm password:
						</td>
						<td>
						<form:password path="confirmPassword" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						User role:
						</td>
						<td>
							<form:select path="roles" multiple="false" cssClass="text">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${roles}"/>	
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						First name:
						</td>
						<td>
						<form:input path="profile.firstName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Last name:
						</td>
						<td>
						<form:input path="profile.lastName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Designation:
						</td>
						<td>
							<form:select path="profile.designation" cssClass="text" id="profile-designation">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${designations}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Email:
						</td>
						<td>
						<form:input path="profile.email" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key" style="vertical-align: middle;">
						Current address:
						</td>
						<td>
						<form:textarea path="profile.currentAddress" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Permanent address:
						</td>
						<td>
						<form:textarea path="profile.permanentAddress" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date of birth:
						</td>
						<td>
						<form:input path="profile.dateOfBirth" cssClass="text" id="profile-dateOfBirth"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date of appointment:
						</td>
						<td>
						<form:input path="profile.dateOfAppoinment" cssClass="text" id="profile-dateOfAppoinment"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Telephone:
						</td>
						<td>
						<form:input path="profile.telephone" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Mobile:
						</td>
						<td>
						<form:input path="profile.mobile" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Gender:
						</td>
						<td>
							<form:select path="profile.gender" cssClass="text">
								<form:option value="0" label="Male"></form:option>
								<form:option value="1" label="Female"></form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						NIC Number:
						</td>
						<td>
							<form:input path="profile.nicNumber" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						District:
						</td>
						<td>
							<form:select path="profile.district" cssClass="text" id="profile-district">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${districts}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Division:
						</td>
						<td>
							<form:select path="profile.division" cssClass="text" id="profile-division">
								<!-- The content will be loaded by ajax -->
							</form:select>
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