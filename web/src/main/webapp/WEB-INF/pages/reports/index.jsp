<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../common/header.jsp" %>
<div id="body">
	<div class="table-top-caption-left">Showing all reports</div>
	<br style="clear: both;"/>
	<c:if test="${flash_success != null}">
		<div class="response-msg success">
			<spring:message code="${flash_success}"></spring:message>
		</div>
	</c:if>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="20%" class="tableHeader">Report name</td>
				<td width="60%" class="tableHeader">Description</td>
				<td width="20%" class="tableHeader">Action</td>
			</tr>
			<tr>
				<td class="tableData">Expense report</td>
				<td class="tableData-left-align">Expense Report</td>
				<td class="tableData">
					<a href="<%=request.getContextPath()%>/reports/expenseReport?reportType=XLS">Download excel</a>&nbsp;
					<a href="<%=request.getContextPath()%>/reports/expenseReport?reportType=PDF">Download pdf</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@include file="../common/footer.jsp" %>