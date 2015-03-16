<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/javascript/choosen/chosen.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/choosen/chosen.jquery.js"></script>

<script type="text/javascript">
$(document).ready(function() { 
	$(".chzn-select").chosen({allow_single_deselect: true});
});
</script>

<script type="text/javascript">
$(document).ready(function() { 
	$(function() {
		$( "#paymentDate" ).datepicker({
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
			<a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Beneficiaries</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/beneficiaries/${loan.beneficiary.id}">${loan.beneficiary.firstName} ${loan.beneficiary.lastName}</a> &nbsp;>>&nbsp; 
			<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${loan.beneficiary.id}/loans/beneficiaryLoans">Loans</a> &nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${loan.beneficiary.id}/loans/${loan.id}">${loan.loanName}</a> &nbsp;>>&nbsp;
			Edit loan payment
		</div>
	</div>
	<!-- Beneficiary details -->
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">Beneficiary name:</td>
				<td>${loan.beneficiary.firstName} ${loan.beneficiary.lastName}</td>
			</tr>
			<tr>
				<td class="key">NIC No:</td>
				<td>${loan.beneficiary.nicNo}</td>
			</tr>
			<tr>
				<td class="key">Member No:</td>
				<td>${loan.beneficiary.memberNo}</td>
			</tr>
			<tr>
				<td class="key">Member No:</td>
				<td>${loan.beneficiary.memberNo}</td>
			</tr>
			<tr>
				<td class="key">Address:</td>
				<td>${loan.beneficiary.address}</td>
			</tr>
			<tr>
				<td class="key">Date of birth:</td>
				<td>${loan.beneficiary.dateOfBirth}</td>
			</tr>
			<tr>
				<td class="key">Gender:</td>
				<td>${loan.beneficiary.gender}</td>
			</tr>
			<tr>
				<td class="key">Marital status:</td>
				<td>${loan.beneficiary.maritalStatus}</td>
			</tr>
			<tr>
				<td class="key">Marital status:</td>
				<td>${loan.beneficiary.maritalStatus}</td>
			</tr>
			<tr>
				<td class="key">Beneficiary Type:</td>
				<td>${loan.beneficiary.beneficiaryType}</td>
			</tr>
			<tr>
				<td class="key">Group:</td>
				<td>${loan.beneficiary.group.name}</td>
			</tr>
			<tr>
				<td class="key">Society:</td>
				<td>${loan.beneficiary.group.society.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division:</td>
				<td>${loan.beneficiary.group.society.gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${loan.beneficiary.group.society.gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">District:</td>
				<td>${loan.beneficiary.group.society.gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Loan name:</td>
				<td>${loan.loanName}</td>
			</tr>
			<tr>
				<td class="key">Loan purpose:</td>
				<td>${loan.loanPurpose}</td>
			</tr>
			<tr>
				<td class="key">Loan start date:</td>
				<td>${loan.loanStartDate}</td>
			</tr>
			<tr>
				<td class="key">Loan end date:</td>
				<td>${loan.loanEndDate}</td>
			</tr>
			<tr>
				<td class="key">Due amount:</td>
				<td>${loan.dueAmount}</td>
			</tr>
			<tr>
				<td class="key">Paid amount:</td>
				<td>${loan.paidAmount}</td>
			</tr>
			<tr>
				<td class="key">Loan total:</td>
				<td>${loan.loanAmount}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">EDITING LOAN PAYMENT FOR ${loan.beneficiary.firstName} ${loan.beneficiary.lastName}</h5>
			<form:form action="processEdit" method="post" modelAttribute="loanPaymentForm">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="loanPaymentForm">
					<div class="response-msg error">
						<spring:bind path="loanPaymentForm">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="loanPaymentForm.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<form:hidden path="id"/>
				<form:hidden path="loanId"/>
				<table class="form">
					<tr>
						<td class="key">
						Amount:
						</td>
						<td>
						<form:input path="amount" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Payment date:
						</td>
						<td>
						<form:input path="paymentDate" cssClass="text" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Collected by:
						</td>
						<td>
							<form:select path="collectedById" cssClass="text chzn-select">
								<form:option value="">Select user</form:option>
								<form:options items="${users}" itemLabel="profile.fullName" itemValue="id"/>
							</form:select>
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