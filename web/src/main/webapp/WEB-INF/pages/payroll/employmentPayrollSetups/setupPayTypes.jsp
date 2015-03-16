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
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employees/${employmentPayTypesForm.employeeId}/employmentPayrollSetups/setupEmployment">Employment</a></li>
		        <li class="tab"><a class="tab-active" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTypesForm.employmentId}/employmentPayrollSetups/setupPayTypes">Pay types</a></li>
		        <!-- 
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTypesForm.employmentId}/employmentPayrollSetups/setupPayTax">Pay taxes</a></li> -->
		        <li class="tab"><a class="tab" href="<%=request.getContextPath()%>/payroll/employments/${employmentPayTypesForm.employmentId}/employmentPayrollSetups/setupPayReductions">Pay reductions</a></li>
		    </ul>
		        <div class="tab-form-wrapper">
		        	<h5 class="colorBlue">Setting up pay types</h5>
		        	<table class="form">
		        		<tr>
		        			<td align="center">
			        			<form:form action="processSetupPayTypes" method="post" modelAttribute="employmentPayTypesForm">
									<!-- print all errors if exists -->
									<spring:hasBindErrors name="employmentPayTypeForm">
										<div class="response-msg error">
											<spring:bind path="employmentPayTypeForm">
												<c:forEach items="${status.errors.globalErrors}" var="error">
													<span><spring:message code="${error.code}"></spring:message></span>
												</c:forEach>
											</spring:bind>
											<spring:bind path="employmentPayTypeForm.*">
												<c:forEach items="${status.errors.fieldErrors}" var="error">
													<spring:message code="${error.code}" ></spring:message><br>
												</c:forEach>
											</spring:bind>
										</div>
									</spring:hasBindErrors>			
									<!-- spring error print ends -->
									<form:hidden path="employmentId"/>
									<table class="form-1" style="width: 60%;">
										<c:forEach items="${allFixedPayTypes}" var="fixedPayType" varStatus="status">
											<tr>
												<td>
													<c:choose>
														<c:when test="${fixedPayType.isMandatory == 'true'}">
															<form:checkbox readonly="true" path="employmentPayTypeForm[${status.index}].payTypeId" value="${fixedPayType.id}"/>
														</c:when>
														<c:otherwise>
															<form:checkbox path="employmentPayTypeForm[${status.index}].payTypeId" value="${fixedPayType.id}"/>
														</c:otherwise>
													</c:choose>
													<!--  
													<input type="checkbox" name="employmentPayTypeForm[${status.index}].payTypeId" value="${fixedPayType.id}">
													-->
												</td>
												<td>
													${fixedPayType.payType}
												</td>
												<td>
													<form:input path="employmentPayTypeForm[${status.index}].amount"/>
                                                    <c:choose>
                                                      <c:when test="${fixedPayType.isUserEnteredAmount == 'false'}">
                                                        &nbsp;% of the&nbsp;
                                                        <form:select path="employmentPayTypeForm[${status.index}].salaryType">
                                                          <form:option value="Basic salary">Basic salary</form:option>
                                                          <form:option value="Gross salary">Gross salary</form:option>
                                                        </form:select>
                                                      </c:when>
                                                    </c:choose>
													<br/>
													<label style="color: red;"><form:errors path="employmentPayTypeForm[${status.index}].amount"/></label>
                                                    <form:hidden path="employmentPayTypeForm[${status.index}].isUserEnteredAmount"/>
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