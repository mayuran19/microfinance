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
		$( "#loanStartDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy'
		});
	});
});

$(document).ready(function() { 
	$(function() {
		$( "#loanEndDate" ).datepicker({
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
			<a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}">${beneficiary.firstName} ${beneficiary.lastName}</a> &nbsp;>>&nbsp; 
			<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/beneficiaryLoans">Loans</a> &nbsp;>>&nbsp;
			Add loan
		</div>
	</div>
	<!-- Beneficiary details -->
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">Beneficiary name:</td>
				<td>${beneficiary.firstName} ${beneficiary.lastName}</td>
			</tr>
			<tr>
				<td class="key">NIC No:</td>
				<td>${beneficiary.nicNo}</td>
			</tr>
			<tr>
				<td class="key">Member No:</td>
				<td>${beneficiary.memberNo}</td>
			</tr>
			<tr>
				<td class="key">Member No:</td>
				<td>${beneficiary.memberNo}</td>
			</tr>
			<tr>
				<td class="key">Address:</td>
				<td>${beneficiary.address}</td>
			</tr>
			<tr>
				<td class="key">Date of birth:</td>
				<td>${beneficiary.dateOfBirth}</td>
			</tr>
			<tr>
				<td class="key">Gender:</td>
				<td>${beneficiary.gender}</td>
			</tr>
			<tr>
				<td class="key">Marital status:</td>
				<td>${beneficiary.maritalStatus}</td>
			</tr>
			<tr>
				<td class="key">Marital status:</td>
				<td>${beneficiary.maritalStatus}</td>
			</tr>
			<tr>
				<td class="key">Beneficiary Type:</td>
				<td>${beneficiary.beneficiaryType}</td>
			</tr>
			<tr>
				<td class="key">Group:</td>
				<td>${beneficiary.group.name}</td>
			</tr>
			<tr>
				<td class="key">Society:</td>
				<td>${beneficiary.group.society.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division:</td>
				<td>${beneficiary.group.society.gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${beneficiary.group.society.gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">District:</td>
				<td>${beneficiary.group.society.gnDivision.division.district.name}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">ADDING NEW LOAN FOR ${beneficiary.firstName} ${beneficiary.lastName}</h5>
			<form:form action="processAdd" method="post" modelAttribute="loanForm">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="loanForm">
					<div class="response-msg error">
						<spring:bind path="loanForm">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="loanForm.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<form:hidden path="id"/>
				<form:hidden path="beneficiaryId"/>
				<table class="form">
					<tr>
						<td class="key">
						Loan name:
						</td>
						<td>
						<form:textarea path="loanName" cssClass="text" cssStyle="width:400px;"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan amount:
						</td>
						<td>
						<form:input path="loanAmount" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan start date:
						</td>
						<td>
						<form:input path="loanStartDate" cssClass="text" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan end date:
						</td>
						<td>
						<form:input path="loanEndDate" cssClass="text" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Grace period:
						</td>
						<td>
						<form:input path="gracePeriod" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan period(In months):
						</td>
						<td>
						<form:input path="loanPeriod" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Interest rate:
						</td>
						<td>
						<form:input path="interestRate" cssClass="text" />
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan Recovery Method:
						</td>
						<td>
							<form:select cssClass="text" path="loanRecoveryMethodId" items="${loanRecoveryMethods}" itemValue="id" itemLabel="name">
								<form:option value="">--Select--</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Loan purpose:
						</td>
						<td>
						<form:textarea path="loanPurpose" cssClass="text" cssStyle="width:400px; height: 80px;"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Remarks:
						</td>
						<td>
						<form:textarea path="Remarks" cssClass="text" cssStyle="width:400px; height: 80px;"/>
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