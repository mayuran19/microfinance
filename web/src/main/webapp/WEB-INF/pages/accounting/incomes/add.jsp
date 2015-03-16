<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#addNewLine").click(
						function() {
							lineItems = (getMaxLineNumber() + 1);
							var new_line = $('#incomeDetail_0').clone();
							new_line = new_line.attr("id",
									"incomeDetail_" + lineItems).attr(
									"lineNum", lineItems);
							new_line.find("#remove-lineitem-0").attr("id",
									"remove-lineitem-" + lineItems).attr(
									"onclick",
									"removeLineItem('" + lineItems + "')");
							new_line.find("#incomeDetailForms0\\.productId")
									.attr(
											"id",
											"incomeDetailForms" + lineItems
													+ ".productId").attr(
											"name",
											"incomeDetailForms[" + lineItems
													+ "].productId");
							new_line.find("#incomeDetailForms0\\.description")
									.attr(
											"id",
											"incomeDetailForms" + lineItems
													+ ".description").attr(
											"name",
											"incomeDetailForms[" + lineItems
													+ "].description");
							new_line.find("#incomeDetailForms0\\.quantity")
									.attr(
											"id",
											"incomeDetailForms" + lineItems
													+ ".quantity").attr(
											"name",
											"incomeDetailForms[" + lineItems
													+ "].quantity");
							new_line.find("#incomeDetailForms0\\.unitPrice")
									.attr(
											"id",
											"incomeDetailForms" + lineItems
													+ ".unitPrice").attr(
											"name",
											"incomeDetailForms[" + lineItems
													+ "].unitPrice");
							new_line.find("#incomeDetailForms0\\.lineTotal")
									.attr(
											"id",
											"incomeDetailForms" + lineItems
													+ ".total").attr(
											"name",
											"incomeDetailForms[" + lineItems
													+ "].total");
							new_line.insertAfter('#incomeDetail_'
									+ (getMaxLineNumber()));
						});
			});

	function removeLineItem(lineItemId) {
		$('#incomeDetail_' + lineItemId).remove();
		resetIds();
	}

	function getMaxLineNumber() {
		var maximum = 0;

		$('.lineItemsClass').each(function() {
			var value = parseInt($(this).attr('lineNum'));
			maximum = (value > maximum) ? value : maximum;
		});

		return maximum;
	}

	function resetIds() {
		$('.lineItemsClass')
				.each(
						function(index) {
							var value = parseInt($(this).attr('lineNum'));
							$(this).attr("id", "incomeDetail_" + index).attr(
									"lineNum", index);
							if (index == 0) {
								$(this).find("#remove-lineitem-" + value).attr(
										"id", "remove-lineitem-" + index);
							} else {
								$(this).find("#remove-lineitem-" + value).attr(
										"id", "remove-lineitem-" + index).attr(
										"onclick",
										"removeLineItem('" + index + "')");
							}

							$(this).find(
									"#incomeDetailForms" + value
											+ "\\.productId").attr("id",
									"incomeDetailForms" + index + ".productId")
									.attr(
											"name",
											"incomeDetailForms[" + index
													+ "].productId");
							$(this).find(
									"#incomeDetailForms" + value
											+ "\\.description").attr(
									"id",
									"incomeDetailForms" + index
											+ ".description").attr(
									"name",
									"incomeDetailForms[" + index
											+ "].description");
							$(this).find(
									"#incomeDetailForms" + value
											+ "\\.quantity").attr("id",
									"incomeDetailForms" + index + ".quantity")
									.attr(
											"name",
											"incomeDetailForms[" + index
													+ "].quantity");
							$(this).find(
									"#incomeDetailForms" + value
											+ "\\.unitPrice").attr("id",
									"incomeDetailForms" + index + ".unitPrice")
									.attr(
											"name",
											"incomeDetailForms[" + index
													+ "].unitPrice");
							$(this).find(
									"#incomeDetailForms" + value
											+ "\\.lineTotal").attr("id",
									"incomeDetailForms" + index + ".lineTotal")
									.attr(
											"name",
											"incomeDetailForms[" + index
													+ "].lineTotal");
						});
	}

	$(document).ready(function() {
		$(function() {
			$("#date").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-mm-yy',
				yearRange : '-100y:c+nn'
			});
		});
	});

	$(document).ready(function() {
		$(function() {
			$("#dueDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-mm-yy',
				yearRange : '-100y:c+nn'
			});
		});
	});
</script>


<!-- Page content starts here -->
<div id="body">
  <div class="form-wrapper">
    <div class="form-big" style="width: 98%">
      <h5 class="colorBlue">Enter bill</h5>
      <form:form action="processAdd" method="post" modelAttribute="incomeForm">
        <!-- print all errors if exists -->
        <spring:hasBindErrors name="incomeForm">
          <div class="response-msg error">
            <spring:bind path="incomeForm">
              <c:forEach items="${status.errors.globalErrors}" var="error">
                <span><spring:message code="${error.code}"></spring:message></span>
              </c:forEach>
            </spring:bind>
            <spring:bind path="incomeForm.*">
              <c:forEach items="${status.errors.fieldErrors}" var="error">
                <c:set var="theString" value="${error.code}" />
                <c:choose>
                  <c:when test="${fn:contains(theString, 'incomeForm.incomeDetail')}">
                  </c:when>
                  <c:otherwise>
                    <spring:message code="${error.code}"></spring:message>
                    <br>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </spring:bind>
          </div>
        </spring:hasBindErrors>
        <!-- spring error print ends -->
        <table class="form">
          <tr>
            <td class="key" width="100px;">Customer name:</td>
            <td><form:select path="customerId" cssClass="text" id="customer">
                <form:option value=""></form:option>
                <form:options itemLabel="customerName" itemValue="id" items="${customers}" />
              </form:select></td>
          </tr>
          <tr>
            <td class="key">Cheque No:</td>
            <td><form:input path="chequeNo" /></td>
          </tr>
          <tr>
            <td class="key">Notes:</td>
            <td><form:input path="notes" /></td>
          </tr>
          <tr>
            <td class="key">Invoice:</td>
            <td><form:input path="invoiceNo" /></td>
          </tr>
          <tr>
            <td class="key">Date:</td>
            <td><form:input path="date" id="date" /></td>
          </tr>
          <tr>
            <td class="key">Due date:</td>
            <td><form:input path="dueDate" id="dueDate" /></td>
          </tr>
          <tr>
            <td class="key">Terms of payment:</td>
            <td><form:select path="termOfPaymentId" cssClass="text" id="termOfPayment">
                <form:option value=""></form:option>
                <form:options itemLabel="termOfPayment" itemValue="id" items="${termOfPayments}" />
              </form:select></td>
          </tr>
        </table>
        <table class="details">
          <thead>
            <tr>
              <td>Product</td>
              <td>Description</td>
              <td>Quantity</td>
              <td>Price</td>
              <td>Amount</td>
            </tr>
          </thead>
          <c:forEach items="${incomeForm.incomeDetailForms}" varStatus="i">
            <tr id="incomeDetail_${i.index}" class="lineItemsClass" lineNum="${i.index}">
              <td><form:select path="incomeDetailForms[${i.index}].productId" cssStyle="width: 200px;">
                  <form:options items="${products}" itemLabel="name" itemValue="id" />
                </form:select></td>
              <td><form:input path="incomeDetailForms[${i.index}].description" cssStyle="width: 200px;" /> <label style="color: red;"><form:errors path="incomeDetailForms[${i.index}].description" /></label></td>
              <td><form:input path="incomeDetailForms[${i.index}].quantity" cssStyle="width: 60px;" readonly="true"/> <label style="color: red;"><form:errors path="incomeDetailForms[${i.index}].quantity" /></label></td>
              <td><form:input path="incomeDetailForms[${i.index}].unitPrice" cssStyle="width: 60px;" readonly="true" /> <label style="color: red;"><form:errors path="incomeDetailForms[${i.index}].unitPrice" /></label></td>
              <td><form:input path="incomeDetailForms[${i.index}].lineTotal" cssStyle="width: 60px;" /> <label style="color: red;"><form:errors path="incomeDetailForms[${i.index}].lineTotal" /></label></td>
              <td><a href="javascript:void(0)"> <c:if test="${i.index == 0}">
                    <img id="remove-lineitem-${i.index}" alt="remove" src="<%=request.getContextPath()%>/images/icons/remove.png">
                  </c:if> <c:if test="${i.index > 0}">
                    <img id="remove-lineitem-${i.index}" alt="remove" src="<%=request.getContextPath()%>/images/icons/remove.png" onclick="removeLineItem('${i.index}')">
                  </c:if>
              </a></td>
            </tr>
          </c:forEach>
        </table>
        <a href="javascript:void(0)" id="addNewLine">+ Add line</a>
        <p align="center">
          <input type="SUBMIT" value="Save" class="buttonBlue" />
        </p>
      </form:form>
    </div>
  </div>
</div>
<!-- page content end -->
<!-- include the footer file -->
<%@include file="../../common/footer.jsp"%>
<!-- footer end -->