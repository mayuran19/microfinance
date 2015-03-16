<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../../common/header.jsp"%>
<script type="text/javascript">
	function deleteDivistion() {
		var agree = confirm("Are you sure you want to delete?");
		if (agree) {
			return true;
		} else
			return false;
	}
</script>
<div id="body">
  <div class="wrap">
    <div class="breadcrumb">
      <a href="<%=request.getContextPath()%>/accounting/expenses/list">Expenses</a>&nbsp;>>&nbsp; ${expense.invoiceNumber}
    </div>
  </div>
  <div class="table-top-caption-left">
    Showing details of <i>${expense.invoiceNumber}</i>
  </div>
  <br style="clear: both;" />
  <div class="wrap">
    <table class="details">
      <tr>
        <td class="key">Id:</td>
        <td>${expense.id}</td>
      </tr>
      <tr>
        <td class="key">Vendor:</td>
        <td>${expense.vendor.name}</td>
      </tr>
      <tr>
        <td class="key">Cheque No:</td>
        <td>${expense.chequeNo}</td>
      </tr>
      <tr>
        <td class="key">Notes:</td>
        <td>${expense.notes}</td>
      </tr>
      <tr>
        <td class="key">Invoice number:</td>
        <td>${expense.invoiceNumber}</td>
      </tr>
      <tr>
        <td class="key">Date:</td>
        <td>${expense.date}</td>
      </tr>
      <tr>
        <td class="key">Due date:</td>
        <td>${expense.dueDate}</td>
      </tr>
      <tr>
        <td class="key">Terms of payment:</td>
        <td>${expense.termsOfPayment}</td>
      </tr>
      <tr>
        <td class="key">Purchase order:</td>
        <td>${expense.purchaseOrder}</td>
      </tr>
      <tr>
        <td class="key">Total:</td>
        <td>${expense.total}</td>
      </tr>
      <tr>
        <td class="key">Paid amount:</td>
        <td>${expense.paidAmount}</td>
      </tr>
    </table>
  </div>

  <div class="40-px-height">&nbsp;</div>
  <div class="table-top-caption">
    <div class="table-top-caption-left">
      Expense details</i>
    </div>
  </div>
  <div class="clear"></div>
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td width="14%" class="tableHeader">Product</td>
        <td width="14%" class="tableHeader">Expense account</td>
        <td width="20%" class="tableHeader">Description</td>
        <td width="20%" class="tableHeader">Quantity</td>
        <td width="20%" class="tableHeader">Unit price</td>
        <td width="20%" class="tableHeader">Total</td>
      </tr>

      <c:forEach items="${expense.expenseDetails}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.product.name}</td>
          <td class="tableData-left-align">${model.expenseAccount.name}</td>
          <td class="tableData-left-align">${model.description}</td>
          <td class="tableData-left-align">${model.quantity}</td>
          <td class="tableData-left-align">${model.unitPrice}</td>
          <td class="tableData-left-align">${model.total}</td>
        </tr>
      </c:forEach>
    </table>
  </div>

  <div class="40-px-height">&nbsp;</div>
  <div class="table-top-caption">
    <div class="table-top-caption-left">Payments</div>
    <div class="table-top-caption-right">
      <a href="<%=request.getContextPath()%>/accounting/expenses/${expense.id}/payments/add">+Add payment</a>
    </div>
  </div>
  <div class="clear"></div>
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td width="14%" class="tableHeader">Vendor</td>
        <td width="14%" class="tableHeader">Date</td>
        <td width="20%" class="tableHeader">Description</td>
        <td width="20%" class="tableHeader">Amount</td>
        <td width="20%" class="tableHeader">Actions</td>
      </tr>

      <c:forEach items="${payments}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${expense.vendor.name}</td>
          <td class="tableData-left-align">${model.date}</td>
          <td class="tableData-left-align">${model.description}</td>
          <td class="tableData-left-align">${model.amount}</td>
          <td class=""><a href="<%=request.getContextPath()%>/accounting/expenses/${expense.id}/payments/${model.id}">Edit</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<%@include file="../../common/footer.jsp"%>