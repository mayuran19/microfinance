<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="table-top-caption-left">Showing all employees</div>
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
        <td width="14%" class="tableHeader">Name</td>
        <td width="14%" class="tableHeader">Mobile number</td>
        <td width="14%" class="tableHeader">NIC No.</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>
      <c:forEach items="${pager.resultSet}" var="model">
        <tr>
          <td class="tableData">${model.employeeId}</td>
          <td class="tableData-left-align">${model.name}</td>
          <td class="tableData-right-align">${model.mobileNumber}</td>
          <td class="tableData-right-align">${model.nicNo}</td>
          <td class="tableData"><c:if test="${model.activeEmploymentId == null }">
              <a href="<%=request.getContextPath()%>/payroll/employees/${model.employeeId}/employmentPayrollSetups/setupEmployment"> Setup payroll </a>&nbsp;
						</c:if> <c:if test="${model.activeEmploymentId != null }">
              <a href="<%=request.getContextPath()%>/payroll/employees/${model.employeeId}/employmentPayrollSetups/setupEmployment"> Edit payroll </a>&nbsp;
						</c:if> <a href="<%=request.getContextPath()%>/payroll/employees/${model.employeeId}/edit">Edit</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>