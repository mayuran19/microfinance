<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="table-top-caption-left">Showing all payrolls</div>
  <div class="table-top-caption-right">
    <a href="<%=request.getContextPath()%>/payroll/payrolls/add">Add payroll</a>
  </div>
  <br style="clear: both;" />
  <c:if test="${flash_success != null}">
    <div class="response-msg success">
      <spring:message code="${flash_success}"></spring:message>
    </div>
  </c:if>
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td width="14%" class="tableHeader">Pay schedule</td>
        <td width="14%" class="tableHeader">From date</td>
        <td width="14%" class="tableHeader">To date</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${pager.resultSet}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.paySchedule.paySchedule}</td>
          <td class="tableData-right-align"><fmt:formatDate value="${model.fromDate}" dateStyle="long" /></td>
          <td class="tableData-right-align"><fmt:formatDate value="${model.toDate}" dateStyle="long" /></td>
          <td class="tableData">
            <div style="float: left;">
              <a href="<%=request.getContextPath()%>/payroll/payrolls/${model.id}/employmentSalaryHeaders/list">View</a>
            </div>
            <c:if test="${model.payrollStatus == 'NEW'}">
              <form:form action="${model.id}/processPayroll">
                <input type="submit" value="Process payroll"/>
              </form:form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>