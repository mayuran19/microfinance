<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="wrap">
    <div class="breadcrumb">
    </div>
  </div>
  <div class="table-top-caption-left">Showing salary detail</div>
  <br style="clear: both;" />
  <c:if test="${flash_success != null}">
    <div class="response-msg success">
      <spring:message code="${flash_success}"></spring:message>
    </div>
  </c:if>
  <!-- Beneficiary details -->
  <div class="wrap">
    <table class="details">
      <tr>
        <td class="key">Name:</td>
        <td>${employmentSalaryHeader.employeeName}</td>
      </tr>
      <tr>
        <td class="key">NIC No:</td>
        <td>${employmentSalaryHeader.employeeNicNo}</td>
      </tr>
      <tr>
        <td class="key">Salary period:</td>
        <td><fmt:formatDate value="${employmentSalaryHeader.payroll.fromDate}" dateStyle="long" /> to <fmt:formatDate value="${employmentSalaryHeader.payroll.toDate}" dateStyle="long" /></td>
      </tr>
      <tr>
        <td class="key">Gross pay:</td>
        <td>${employmentSalaryHeader.grossPay}</td>
      </tr>
      <tr>
        <td class="key">Total reductions:</td>
        <td>${employmentSalaryHeader.totalReductions}</td>
      </tr>
      <tr>
        <td class="key">Net pay:</td>
        <td>${employmentSalaryHeader.netPay}</td>
      </tr>
    </table>
  </div>
  <div class="40-px-height">&nbsp;</div>
  <div class="table-top-caption">
    <div class="table-top-caption-left">Salary details</div>
  </div>
  <div class="clear"></div>
  <!-- Loans list -->
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td class="tableHeader">Earning/Reduction name</td>
        <td class="tableHeader">Earning amount</td>
        <td class="tableHeader">Reduction amount</td>
      </tr>

      <c:forEach items="${employmentSalaryDetailsEarnings}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.payTypeName}</td>
          <td class="tableData-left-align">${model.payTypeAmount}</td>
          <td class="tableData-right-align">N/A</td>
        </tr>
      </c:forEach>
      <c:forEach items="${employmentSalaryDetailsReductions}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.payReductionName}</td>
          <td class="tableData-left-align">N/A</td>
          <td class="tableData-right-align">${model.payReductionAmount}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<%@include file="../../common/footer.jsp"%>