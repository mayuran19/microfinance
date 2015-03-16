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
		$( "#date" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
			yearRange: '-100y:c+nn'
		});
	});
});
</script>

<!-- Page content starts here -->
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/accounting/expenses/list">Expenses</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/accounting/expenses/${payment.expense.id}">${payment.expense.invoiceNumber}</a> &nbsp;>>&nbsp; Edit payment
		</div>
	</div>
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">EDIT PAYMENT</h5>
			<form:form action="processEdit" method="post" modelAttribute="payment">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="payment">
					<div class="response-msg error">
						<spring:bind path="payment">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="payment.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<form:hidden path="id"/>
				<table class="form">
					<tr>
						<td class="key">
						Description:
						</td>
						<td>
						<form:input path="description" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date:
						</td>
						<td>
						<form:input path="date" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Amount:
						</td>
						<td>
						<form:input path="amount" cssClass="text"/>
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