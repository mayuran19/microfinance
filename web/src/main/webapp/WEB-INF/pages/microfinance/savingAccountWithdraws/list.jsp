<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="wrap">
    <div class="breadcrumb">
      <a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Beneficiaries</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}">${beneficiary.firstName} ${beneficiary.lastName}</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/savingAccounts/benificiarySavingAccounts">Savings</a>&nbsp;
    </div>
  </div>
  <c:if test="${flash_success != null}">
    <div class="response-msg success">
      <spring:message code="${flash_success}"></spring:message>
    </div>
  </c:if>
  <!-- Beneficiary details -->
  <div class="table-top-caption">
    <div class="table-top-caption-left">
      Withdraws of <i>${savingAccount.accountNumber}</i>
    </div>
    <div class="table-top-caption-right">
      <a href="<%=request.getContextPath()%>/microfinance/savingAccounts/${savingAccount.id}/savingAccountWithdraws/add">+ Add new withdraw</a>
    </div>
  </div>
  <div class="clear"></div>
  <!-- Loans list -->
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td class="tableHeader">Withdraw type</td>
        <td class="tableHeader">Withdraw date</td>
        <td class="tableHeader">Amount</td>
        <td class="tableHeader">Remarks</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${pager.resultSet}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.savingAccountWithdrawType.description}</td>
          <td class="tableData-left-align">${model.withdrawDate}</td>
          <td class="tableData-left-align">${model.amount}</td>
          <td class="tableData-right-align">${model.remarks}</td>
          <td class="tableData"><a href="<%=request.getContextPath()%>/microfinance/savingAccounts/${savingAccount.id}/savingAccountWithdraws/${model.id}/edit">Edit</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>