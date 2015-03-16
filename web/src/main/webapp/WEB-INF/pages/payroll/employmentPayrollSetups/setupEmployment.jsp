<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<!--  
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/javascript/jquery/themes/base/jquery-ui.css" />
<script src="<%=request.getContextPath()%>/javascript/jquery/js/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/javascript/jquery/js/jquery-ui-1.9.0.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
$(document).ready(function() { 
	$(function() {
		$("#startDate").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy'
		});
	});
	$(function() {
		$("#endDate").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy'
		});
	});
});

//$(function() {
//    $("#tabs").tabs();
//});
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">

		</div>
	</div>
		<div id="tabs">
		    <ul class="tab">
		        <li class="tab"><a class="tab-active" href="<%=request.getContextPath()%>/payroll/employees/${employmentForm.employeeId}/employmentPayrollSetups/setupEmployment">Employment</a></li>
		        <c:if test="${employmentForm.employmentId != null}">
			        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentForm.employmentId}/employmentPayrollSetups/setupPayTypes">Pay types</a></li>
			        <!--  
			        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentForm.employmentId}/employmentPayrollSetups/setupPayTax">Pay taxes</a></li>-->
			        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentForm.employmentId}/employmentPayrollSetups/setupPayReductions">Pay reductions</a></li>
		        </c:if>
		        <c:if test="${employmentForm.employmentId == null}">
		        	<li class="tab"><a class="tab" href="javascript:void(0)">Pay types</a></li>
		        	<!-- 
		        	<li class="tab"><a class="tab" href="javascript:void(0)">Pay taxes</a></li> -->
		        	<li class="tab"><a class="tab" href="javascript:void(0)">Pay reductions</a></li>
		        </c:if>
		    </ul>
		        <div class="tab-form-wrapper">
		        	<h5 class="colorBlue">SETUP EMPLOYMENT</h5>
		        	<table class="form">
		        		<tr>
		        			<td align="center">
			        			<form:form action="processSetupEmployment" method="post" modelAttribute="employmentForm">
									<!-- print all errors if exists -->
									<spring:hasBindErrors name="employmentForm">
										<table class="form-1-error" style="width: 60%;">
											<tr>
												<td width="100%">
													<div class="response-msg error">
														<spring:bind path="employmentForm">
															<c:forEach items="${status.errors.globalErrors}" var="error">
																<span><spring:message code="${error.code}"></spring:message></span>
															</c:forEach>
														</spring:bind>
														<spring:bind path="employmentForm.*">
															<c:forEach items="${status.errors.fieldErrors}" var="error">
																<spring:message code="${error.code}" ></spring:message><br>
															</c:forEach>
														</spring:bind>
													</div>
												</td>
											</tr>										
										</table>
									</spring:hasBindErrors>			
									<!-- spring error print ends -->
									<form:hidden path="employeeId"/>
									<form:hidden path="employmentId"/>
									<table class="form-1" style="width: 60%;">
										<tr>
											<td class="key">Employment status:</td>
											<td>
											<form:select path="employmentStatusId" itemLabel="name" itemValue="id" items="${employmentStatus}"></form:select>
											</td>
										</tr>
										<tr>
											<td class="key">Pay schedule:</td>
											<td>
											<form:select path="payScheduleId" itemLabel="paySchedule" itemValue="id" items="${paySchedules}"></form:select>
											</td>
										</tr>
										<tr>
											<td class="key">Start date:</td>
											<td>
											<form:input path="startDate" cssClass="text" id="startDate"/>
											</td>
										</tr>
										<tr>
											<td class="key">End date:</td>
											<td>
											<form:input path="endDate" cssClass="text" id="endDate"/>
											</td>
										</tr>
										<tr>
											<td class="key">Work location:</td>
											<td>
											<form:input path="workLocation" cssClass="text" />
											</td>
										</tr>
										<tr>
											<td class="key">Remarks:</td>
											<td>
											<form:textarea path="remarks" cssClass="text" cssStyle="width:400px; height: 80px;"/>
											</td>
										</tr>
									</table>
									<p align="center">
										<input type="SUBMIT" value="Save" class="buttonBlue" />
									</p>
								</form:form>
		        			</td>
		        		</tr>
		        	</table>
		        </div>
		</div>
	</div>
<!-- page content end -->
<!-- include the footer file -->
<%@include file="../../common/footer.jsp"%>
<!-- footer end -->