<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<c:url var="getAllDivisionsJSONByDistrictIdURL" value="/admin/divisions/getAllDivisionsJSONByDistrictId"></c:url>
<c:url var="getAllGNDivisionsJSONByDivisionIdURL" value="/admin/divisions/getAllGNDivisionsJSONByDivisionId"></c:url>
<c:url var="getAllSocietiessJSONByGNDivisionIdURL" value="/admin/societies/getAllSocietiesJSONByGNDivisionId"></c:url>
<script type="text/javascript">
$(document).ready(function() { 
	$('#society-gnDivision-division-district').change(
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
 
				$('#society-gnDivision-division').html(html);
				$('#society-gnDivision').html("");
				$('#society').html("");
			});
		});
});

$(document).ready(function() { 
	$('#society-gnDivision-division').change(
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
 
				$('#society-gnDivision').html(html);
				$('#society').html("");
			});
		});
});

$(document).ready(function() { 
	$('#society-gnDivision').change(
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
 
				$('#society').html(html);
			});
		});
});
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">EDIT GROUP</h5>
			<form:form action="processEdit" method="post" modelAttribute="group">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="group">
					<div class="response-msg error">
						<spring:bind path="group">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="group.*">
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
							<form:select path="society.gnDivision.division.district" cssClass="text" id="society-gnDivision-division-district">
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
							<form:select path="society.gnDivision.division" cssClass="text" id="society-gnDivision-division">
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
							<form:select path="society.gnDivision" cssClass="text" id="society-gnDivision">
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
							<form:select path="society" cssClass="text" id="society">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${societies}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Group name:
						</td>
						<td>
							<form:input path="name"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Description:
						</td>
						<td>
							<form:textarea path="description"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						President first name:
						</td>
						<td>
						<form:input path="president.firstName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						President last name:
						</td>
						<td>
						<form:input path="president.lastName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						ECC President first name:
						</td>
						<td>
						<form:input path="eccPresident.firstName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						ECC President last name:
						</td>
						<td>
						<form:input path="eccPresident.lastName" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Group contact no:
						</td>
						<td>
						<form:input path="contactNo" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						ECC Name:
						</td>
						<td>
						<form:input path="eccName" cssClass="text"/>
						</td>
					</tr>
				</table>
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