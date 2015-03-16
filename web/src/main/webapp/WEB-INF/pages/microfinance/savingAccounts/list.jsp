<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="table-top-caption-left">Showing all savings</div>
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
        <td width="14%" class="tableHeader">Account No</td>
        <td width="14%" class="tableHeader">Opened date</td>
        <td width="14%" class="tableHeader">Balance</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${pager.resultSet}" var="model">
        <tr>
          <td class="tableData">${model.savingAccountId}</td>
          <td class="tableData-left-align">${model.accountHolderName}</td>
          <td class="tableData-right-align">${model.accountNumber}</td>
          <td class="tableData-right-align">${model.openedAt}</td>
          <td class="tableData-right-align">${model.balance}</td>
          <td class="tableData"><a href="<%=request.getContextPath()%>/microfinance/savingAccounts/${model.savingAccountId}">View</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>