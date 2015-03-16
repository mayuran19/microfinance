<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/javascript/choosen/chosen.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/choosen/chosen.jquery.js"></script>
<!-- header ends -->

<c:url var="getAllDivisionsJSONByDistrictIdURL" value="/admin/divisions/getAllDivisionsJSONByDistrictId"></c:url>
<c:url var="getAllGNDivisionsJSONByDivisionIdURL" value="/admin/divisions/getAllGNDivisionsJSONByDivisionId"></c:url>
<c:url var="getAllSocietiessJSONByGNDivisionIdURL" value="/admin/societies/getAllSocietiesJSONByGNDivisionId"></c:url>
<c:url var="getAllGroupsJSONBySocietyIdURL" value="/admin/groups/getAllGroupsJSONBySocietyId"></c:url>
<script type="text/javascript">
$(document).ready(function() { 
	$(".chzn-select").chosen();
	$('#group-society-gnDivision-division-district').change(
		function() {
			$.getJSON('${getAllDivisionsJSONByDistrictIdURL}', {
				districtId : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value="0"></option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#group-society-gnDivision-division').html(html);
				$('#group-society-gnDivision').html("");
				$('#group-society').html("");
				$("#group-society-gnDivision-division").trigger("liszt:updated");
			});
		});
});

$(document).ready(function() { 
	$('#group-society-gnDivision-division').change(
		function() {
			$.getJSON('${getAllGNDivisionsJSONByDivisionIdURL}', {
				divisionId : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value="0"></option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#group-society-gnDivision').html(html);
				$('#group-society').html("");
				$('#group').html("");
				$("#group-society-gnDivision").trigger("liszt:updated");
			});
		});
});

$(document).ready(function() { 
	$('#group-society-gnDivision').change(
		function() {
			$.getJSON('${getAllSocietiessJSONByGNDivisionIdURL}', {
				gnDivisionId : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value="0"></option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#group-society').html(html);
				$('#group').html(html);
				$("#group-society").trigger("liszt:updated");
			});
		});
});

$(document).ready(function() { 
	$('#group-society').change(
		function() {
			$.getJSON('${getAllGroupsJSONBySocietyIdURL}', {
				societyId : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value="0"></option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				html += '</option>';
				
				$('#group').html(html);
				$("#group").trigger("liszt:updated");
			});
		});
});

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
			<h5 class="colorBlue">ADD NEW BENEFICIARY</h5>
			<form:form action="processAdd" method="post" modelAttribute="beneficiary">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="beneficiary">
					<div class="response-msg error">
						<spring:bind path="beneficiary">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="beneficiary.*">
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
						District:
						</td>
						<td>
							<form:select path="group.society.gnDivision.division.district" cssClass="text chzn-select" id="group-society-gnDivision-division-district">
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
							<form:select path="group.society.gnDivision.division" cssClass="text chzn-select" id="group-society-gnDivision-division">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${divisions}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						GN Division:
						</td>
						<td>
							<form:select path="group.society.gnDivision" cssClass="text chzn-select" id="group-society-gnDivision">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${gnDivisions}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Society:
						</td>
						<td>
							<form:select path="group.society" cssClass="text chzn-select" id="group-society">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${societies}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Group:
						</td>
						<td>
							<form:select path="group" cssClass="text chzn-select" id="group">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${groups}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Member no:
						</td>
						<td>
							<form:input path="memberNo"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						First name:
						</td>
						<td>
							<form:input path="firstName"/>
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
						NIC No.:
						</td>
						<td>
						<form:input path="nicNo" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Address:
						</td>
						<td>
						<form:textarea path="address" cssClass="text"/>
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
						Gender:
						</td>
						<td>
							<form:select path="gender" cssClass="text" id="gender">
								<form:option value="0">Male</form:option>
								<form:option value="1">Female</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Marital Status:
						</td>
						<td>
							<form:select path="maritalStatus" cssClass="text" id="maritalStatus">
								<form:option value="0">SINGLE</form:option>
								<form:option value="1">MARRIED</form:option>
								<form:option value="2">WIDOW</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Special Identification:
						</td>
						<td>
							<form:select path="specialIdentification" cssClass="text" id="specialIdentification">
								<form:option value="0">NONE</form:option>
								<form:option value="1">HANDICAP</form:option>
								<form:option value="2">DISABLED</form:option>
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