<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<script type="text/javascript">
function deleteDivistion(){
	var agree=confirm("Are you sure you want to delete?");
		if (agree){
			return true;
		}else
			return false;
	}
</script>
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/accounting/expenseAccounts/list">Expense accounts</a>&nbsp;>>&nbsp;
			${product.name}
		</div>
	</div>
	<div class="table-top-caption-left">Showing details of <i>${expenseAccount.name}</i> </div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">expenseAccount name:</td>
				<td>${expenseAccount.name}</td>
			</tr>
			<tr>
				<td class="key">Description:</td>
				<td>${expenseAccount.description}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
</div>
<%@include file="../../common/footer.jsp" %>