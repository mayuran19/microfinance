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
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">

		</div>
	</div>
		<div id="tabs">
		    <ul class="tab">
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employees/${employmentPayTaxHeader.employeeId}/employmentPayrollSetups/setupEmployment">Employment</a></li>
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTaxHeader.employmentId}/employmentPayrollSetups/setupPayTypes">Pay types</a></li>
		        <li class="tab"><a class="tab-active" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTaxHeader.employmentId}/employmentPayrollSetups/setupPayTax">Pay taxes</a></li>
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTaxHeader.employmentId}/employmentPayrollSetups/setupPayReductions">Pay reductions</a></li>
		    </ul>
		    	<div class="tab-form-wrapper">
		        	<h5 class="colorBlue">Setting up pay taxes</h5>
		        	<table class="form">
		        		<tr>
		        			<td align="center">
			        			<form:form action="procesSetupPayTax" method="post" modelAttribute="employmentPayTaxHeader">
									<!-- print all errors if exists -->
									<spring:hasBindErrors name="employmentPayTaxHeader">
										<div class="response-msg error">
											<spring:bind path="employmentPayTaxHeader">
												<c:forEach items="${status.errors.globalErrors}" var="error">
													<span><spring:message code="${error.code}"></spring:message></span>
												</c:forEach>
											</spring:bind>
											<spring:bind path="employmentPayTaxHeader.*">
												<c:forEach items="${status.errors.fieldErrors}" var="error">
													<spring:message code="${error.code}" ></spring:message><br>
												</c:forEach>
											</spring:bind>
										</div>
									</spring:hasBindErrors>			
									<!-- spring error print ends -->
									<form:hidden path="employmentId"/>
									<table class="form-1" style="width: 60%;">
										<c:forEach items="${allFixedPayTaxes}" var="fixedPayTax" varStatus="status">
											<tr>
												<td>
													<form:checkbox path="employmentPayTaxDetails[${status.index}].payTaxId" value="${fixedPayTax.id}"/>
													<!--  
													<input type="checkbox" name="employmentPayTypeForm[${status.index}].payTypeId" value="${fixedPayType.id}">
													-->
												</td>
												<td>
													${fixedPayTax.payTax}
												</td>
												<td>
													<form:input path="employmentPayTaxDetails[${status.index}].amount"/>
													<!--  
													<input type="text" name="employmentPayTypeForm[${status.index}].amount">
													-->
												</td>
											</tr>
										</c:forEach>
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