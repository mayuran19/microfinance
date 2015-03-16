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
      <a href="<%=request.getContextPath()%>/accounting/incomes/list">Incomes</a>&nbsp;>>&nbsp; ${income.invoiceNo}
    </div>
  </div>
  <div class="table-top-caption-left">
    Showing details of <i>${income.invoiceNo}</i>
  </div>
  <br style="clear: both;" />
  <div class="wrap">
    <table class="details">
      <tr>
        <td class="key">Id:</td>
        <td>${income.id}</td>
      </tr>
      <tr>
        <td class="key">Customer:</td>
        <td>${income.customer.customerName}</td>
      </tr>
      <tr>
        <td class="key">Cheque No:</td>
        <td>${income.chequeNo}</td>
      </tr>
      <tr>
        <td class="key">Notes:</td>
        <td>${income.notes}</td>
      </tr>
      <tr>
        <td class="key">Invoice number:</td>
        <td>${income.invoiceNo}</td>
      </tr>
      <tr>
        <td class="key">Date:</td>
        <td>${income.date}</td>
      </tr>
      <tr>
        <td class="key">Due date:</td>
        <td>${income.dueDate}</td>
      </tr>
      <tr>
        <td class="key">Terms of payment:</td>
        <td>${income.termOfPayment.termOfPayment}</td>
      </tr>
    </table>
  </div>

  <div class="40-px-height">&nbsp;</div>
  <div class="table-top-caption">
    <div class="table-top-caption-left">
      Income details</i>
    </div>
  </div>
  <div class="clear"></div>
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
      <tr>
        <td width="2%" class="tableHeader">ID</td>
        <td width="14%" class="tableHeader">Product</td>
        <td width="20%" class="tableHeader">Description</td>
        <td width="20%" class="tableHeader">Quantity</td>
        <td width="20%" class="tableHeader">Unit price</td>
        <td width="20%" class="tableHeader">Total</td>
      </tr>
      <c:forEach items="${income.incomeDetails}" var="model">
        <tr>
          <td class="tableData">${model.id}</td>
          <td class="tableData-left-align">${model.product.name}</td>
          <td class="tableData-left-align">${model.description}</td>
          <td class="tableData-left-align">${model.quantity}</td>
          <td class="tableData-left-align">${model.unitPrice}</td>
          <td class="tableData-left-align">${model.lineTotal}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<%@include file="../../common/footer.jsp"%>