<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="wrap">
    <div class="breadcrumb">
      <a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Beneficiaries</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}">${beneficiary.firstName} ${beneficiary.lastName}</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/beneficiaryLoans">Loans</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/loans/${loan.id}">${loan.loanName}</a>
    </div>
  </div>
  <div class="table-top-caption-left">Showing all loans</div>
  <br style="clear: both;" />
  <c:if test="${flash_success != null}">
    <div class="response-msg success">
      <spring:message code="${flash_success}"></spring:message>
    </div>
  </c:if>
  <!-- Beneficiary details -->
  <div class="wrap">
    <table class="show">
      <tr>
        <td class="key">Beneficiary name:</td>
        <td>${beneficiary.firstName} ${beneficiary.lastName}</td>
        <td class="key">NIC No:</td>
        <td>${beneficiary.nicNo}</td>
      </tr>
      <tr>
        <td class="key">Member No:</td>
        <td>${beneficiary.memberNo}</td>
        <td class="key">Address:</td>
        <td>${beneficiary.address}</td>
      </tr>
      <tr>
        <td class="key">Date of birth:</td>
        <td>${beneficiary.dateOfBirth}</td>
        <td class="key">Gender:</td>
        <td>${beneficiary.gender}</td>
      </tr>
      <tr>
        <td class="key">Marital status:</td>
        <td>${beneficiary.maritalStatus}</td>
        <td class="key">Beneficiary Type:</td>
        <td>${beneficiary.beneficiaryType}</td>
      </tr>
      <tr>
        <td class="key">Group:</td>
        <td>${beneficiary.group.name}</td>
        <td class="key">Society:</td>
        <td>${beneficiary.group.society.name}</td>
      </tr>
      <tr>
        <td class="key">GN Division:</td>
        <td>${beneficiary.group.society.gnDivision.name}</td>
        <td class="key">Division:</td>
        <td>${beneficiary.group.society.gnDivision.division.name}</td>
      </tr>
      <tr>
        <td class="key">District:</td>
        <td>${beneficiary.group.society.gnDivision.division.district.name}</td>
        <td class="key"></td>
        <td></td>
      </tr>
    </table>
  </div>
  <div class="40-px-height">&nbsp;</div>
  <div class="table-top-caption">
    <div class="table-top-caption-left">
      Deposits of <i>${savingAccount.accountNumber}</i>
    </div>
    <div class="table-top-caption-right">
      <a href="<%=request.getContextPath()%>/microfinance/savingAccounts/${savingAccount.id}/savingAccountDeposits/add">+ Add new deposit</a>
    </div>
  </div>
  <div class="clear"></div>
  <!-- Loans list -->
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td class="tableHeader">Payment Date</td>
        <td class="tableHeader">Collected by</td>
        <td class="tableHeader">Amount</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${loanPayments}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.paymentDate}</td>
          <td class="tableData-left-align">${model.collectedBy.profile.fullName}</td>
          <td class="tableData-right-align">${model.amount}</td>
          <td class="tableData"><a href="<%=request.getContextPath()%>/microfinance/loans/${loan.id}/loanPayments/${model.id}">View</a>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/loans/${loan.id}/loanPayments/${model.id}/edit">Edit</a> <a href="<%=request.getContextPath()%>/microfinance/loans/${loan.id}/loanPayments/${model.id}/deactivate" onclick="return confirm('Are you sure you want to delete?')"> Delete</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<%@include file="../../common/footer.jsp"%>