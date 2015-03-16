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
			$("#openedDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-mm-yy'
			});
		});
	});

	$(document).ready(function() {
		$(function() {
			$("#loanEndDate").datepicker({
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
      <a href="<%=request.getContextPath()%>/admin/societies/list">Societies</a>&nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/admin/societies/${society.id}">${society.name}</a> &nbsp;>>&nbsp; <a href="<%=request.getContextPath()%>/microfinance/societies/${society.id}/savingAccounts/societySavingAccounts">Savings</a> &nbsp;>>&nbsp; Add saving
    </div>
  </div>
  <div class="40-px-height">&nbsp;</div>
  <div class="form-wrapper">
    <div class="form-big">
      <h5 class="colorBlue">ADDING NEW SAVING FOR ${society.name}</h5>
      <form:form action="processSocietyAdd" method="post" modelAttribute="savingAccountForm">
        <!-- print all errors if exists -->
        <spring:hasBindErrors name="savingAccountForm">
          <div class="response-msg error">
            <spring:bind path="savingAccountForm">
              <c:forEach items="${status.errors.globalErrors}" var="error">
                <span><spring:message code="${error.code}"></spring:message></span>
              </c:forEach>
            </spring:bind>
            <spring:bind path="savingAccountForm.*">
              <c:forEach items="${status.errors.fieldErrors}" var="error">
                <spring:message code="${error.code}"></spring:message>
                <br>
              </c:forEach>
            </spring:bind>
          </div>
        </spring:hasBindErrors>
        <!-- spring error print ends -->
        <form:hidden path="id" />
        <form:hidden path="beneficiaryId" />
        <form:hidden path="societyId" />
        <table class="form">
          <tr>
            <td class="key">Account holder name:</td>
            <td><form:input path="accountHolderName" cssClass="text" /></td>
            <td class="key">Account number:</td>
            <td><form:input path="accountNumber" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">Account type:</td>
            <td><form:select path="savingAccountTypeId" items="${savingAccountTypes}" itemLabel="name" itemValue="id" cssClass="text">
                <form:option value="">--Select--</form:option>
              </form:select>
            <td class="key">Address:</td>
            <td><form:textarea path="address" cssClass="text" cssStyle="width:400px;height:60px;" /></td>
          </tr>
          <tr>
            <td class="key">Opened date:</td>
            <td><form:input path="openedDate" cssClass="text" /></td>
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