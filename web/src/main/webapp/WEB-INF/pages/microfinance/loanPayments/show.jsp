<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Beneficiaries</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}">${beneficiary.firstName} ${beneficiary.lastName}</a>&nbsp;>>&nbsp;Loans
		</div>
	</div>
	<div class="table-top-caption-left">Showing all loans</div>
	<br style="clear: both;"/>
	<c:if test="${flash_success != null}">
		<div class="response-msg success">
			<spring:message code="${flash_success}"></spring:message>
		</div>
	</c:if>
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
	<div class="table-top-caption">
		<div class="table-top-caption-left">Payments of <i>${loan.loanName}</i> loan</div>
		<div class="table-top-caption-right"><a href="<%=request.getContextPath()%>/microfinance/loans/${loan.id}/payments/add">+Add new payment</a></div>
	</div>
	<div class="clear"></div>
	<!-- Loans list -->
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td class="tableHeader">Loan name</td>
				<td class="tableHeader">Loan purpose</td>
				<td class="tableHeader">Loan start date</td>
				<td class="tableHeader">Loan end date</td>
				<td class="tableHeader">Loan due</td>
				<td class="tableHeader">Paid amount</td>
				<td class="tableHeader">Loan total</td>
				<td width="20%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${loans}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.loanName}</td>
					<td class="tableData-left-align">${model.loanPurpose}</td>
					<td class="tableData-left-align">${model.loanStartDate}</td>
					<td class="tableData-left-align">${model.loanEndDate}</td>
					<td class="tableData-right-align">${model.dueAmount}</td>
					<td class="tableData-right-align">${model.paidAmount}</td>
					<td class="tableData-right-align">${model.loanAmount}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/${model.id}">View</a>&nbsp;
						<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/${model.id}/edit">Edit</a>
						<a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/${model.id}/deactivate" onclick="return confirm('Are you sure you want to delete?')"> Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@include file="../../common/footer.jsp" %>