<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
  <div class="table-top-caption-left">Showing all societies</div>
  <br style="clear: both;" />
  <div id="wrap">

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td width="14%" class="tableHeader">Name</td>
        <td width="14%" class="tableHeader">GN Division</td>
        <td width="14%" class="tableHeader">Division</td>
        <td width="14%" class="tableHeader">District</td>
        <td width="6%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${pager.resultSet}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.name}</td>
          <td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/divisions/${model.gnDivision.division.id}/gnDivisions/${model.gnDivision.id}">${model.gnDivision.name}</a></td>
          <td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/districts/${model.gnDivision.division.district.id}/divisions/${model.gnDivision.division.id}">${model.gnDivision.division.name}</a></td>
          <td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/districts/${model.gnDivision.division.district.id}">${model.gnDivision.division.district.name}</a></td>
          <td class="tableData"><a href="<%=request.getContextPath()%>/admin/societies/${model.id}">View</a> <a href="<%=request.getContextPath()%>/admin/societies/${model.id}/edit">Edit</a> <a href="<%=request.getContextPath()%>/microfinance/societies/${model.id}/savingAccounts/societySavingAccounts">Savings</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>