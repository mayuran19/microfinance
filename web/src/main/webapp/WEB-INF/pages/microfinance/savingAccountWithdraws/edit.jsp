<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#withdrawDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-mm-yy'
			});
		});
	});
</script>

<!-- Page content starts here -->
<div id="body">
  <div class="wrap">
    <div class="breadcrumb">
      <a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Beneficiaries</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}">${beneficiary.firstName} ${beneficiary.lastName}</a> &nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${beneficiary.id}/savingAccounts/benificiarySavingAccounts">Savings</a> &nbsp;>>&nbsp; Add loan
    </div>
  </div>
  <div class="40-px-height">&nbsp;</div>
  <div class="form-wrapper">
    <div class="form-big">
      <h5 class="colorBlue">EDIT WITHDRAW FOR ${savingAccount.accountNumber}</h5>
      <form:form action="processEdit" method="post" modelAttribute="savingAccountWithdrawForm">
        <!-- print all errors if exists -->
        <spring:hasBindErrors name="savingAccountWithdrawForm">
          <div class="response-msg error">
            <spring:bind path="savingAccountWithdrawForm">
              <c:forEach items="${status.errors.globalErrors}" var="error">
                <span><spring:message code="${error.code}"></spring:message></span>
              </c:forEach>
            </spring:bind>
            <spring:bind path="savingAccountWithdrawForm.*">
              <c:forEach items="${status.errors.fieldErrors}" var="error">
                <spring:message code="${error.code}"></spring:message>
                <br>
              </c:forEach>
            </spring:bind>
          </div>
        </spring:hasBindErrors>
        <!-- spring error print ends -->
        <form:hidden path="id" />
        <form:hidden path="savingAccountId" />
        <table class="form">
          <tr>
            <td class="key">Amount:</td>
            <td><form:input path="amount" cssClass="text" /></td>
            <td class="key">Withdraw date:</td>
            <td><form:input path="withdrawDate" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">Withdraw type:</td>
            <td>
              <form:select path="savingAccountWithdrawTypeId" items="${savingAccountWithdrawTypes}" itemLabel="description" itemValue="id" cssStyle="width:400px;"></form:select>
            </td>
            <td class="key">Processed by</td>
            <td>
              <form:select path="processedById" items="${employees}" itemLabel="name" itemValue="id" cssClass="text">
                  <form:option value="">--Select--</form:option>
              </form:select>
            </td>
          </tr>
          <tr>
            <td class="key">Remarks:</td>
            <td><form:textarea path="remarks" cssClass="text" cssStyle="width:400px;height:60px;" /></td>
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