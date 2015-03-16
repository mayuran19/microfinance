<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="table-top-caption-left">Income</div>
	<a href="<%=request.getContextPath()%>/accounting/incomes/add" style="float: right;">+ Add new invoice</a>
	<br style="clear: both;"/>
	
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">Date</td>
                <td width="14%" class="tableHeader">Cheque No</td>
				<td width="14%" class="tableHeader">Invoice</td>
				<td width="14%" class="tableHeader">Customer</td>
				<td width="14%" class="tableHeader" align="right">Total</td>
				<td width="20%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${pager.resultSet}" var="obj">
				<tr>
					<td class="tableData">${obj.id}</td>
					<td class="tableData-left-align">${obj.date}</td>
                    <td class="tableData-left-align">${obj.chequeNo}</td>
					<td class="tableData-left-align">${obj.invoiceNumber}</td>
					<td class="tableData-left-align">${obj.customerName}</td>
					<td class="tableData-right-align">${obj.total}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/accounting/incomes/${obj.id}">Show</a>
						<a href="<%=request.getContextPath()%>/accounting/incomes/${obj.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<pagination:pagination pager="${pager}"/>
</div>
<%@include file="../../common/footer.jsp" %>