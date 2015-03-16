<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
$(document).ready(function() { 
	$("#addNewLine").click(function() {
		lineItems = (getMaxLineNumber() + 1);
		var new_line = $('#expenseDetail_0').clone();
		new_line = new_line.attr("id", "expenseDetail_" + lineItems).attr("lineNum", lineItems);
		new_line.find("#remove-lineitem-0").attr("id", "remove-lineitem-" + lineItems).attr("onclick", "removeLineItem('" + lineItems +"')");
		new_line.find("#expenseDetails0\\.product").attr("id", "expenseDetails" + lineItems + ".product").attr("name", "expenseDetails[" + lineItems + "].product");
		new_line.find("#expenseDetails0\\.expenseAccount").attr("id", "expenseDetails" + lineItems + ".expenseAccount").attr("name", "expenseDetails[" + lineItems + "].expenseAccount");
		new_line.find("#expenseDetails0\\.description").attr("id", "expenseDetails" + lineItems + ".description").attr("name", "expenseDetails[" + lineItems + "].description");
		new_line.find("#expenseDetails0\\.quantity").attr("id", "expenseDetails" + lineItems + ".quantity").attr("name", "expenseDetails[" + lineItems + "].quantity");
		new_line.find("#expenseDetails0\\.unitPrice").attr("id", "expenseDetails" + lineItems + ".unitPrice").attr("name", "expenseDetails[" + lineItems + "].unitPrice");
		new_line.find("#expenseDetails0\\.total").attr("id", "expenseDetails" + lineItems + ".total").attr("name", "expenseDetails[" + lineItems + "].total");
		new_line.insertAfter('#expenseDetail_' + (getMaxLineNumber()));
	});
});

function removeLineItem(lineItemId){
	$('#expenseDetail_' + lineItemId).remove();
	resetIds();
}

function getMaxLineNumber(){
	var maximum = 0;

	$('.lineItemsClass').each(function() {
		var value = parseInt($(this).attr('lineNum'));
		maximum = (value > maximum) ? value : maximum;
	});
	
	return maximum;
}

function resetIds(){
	$('.lineItemsClass').each(function(index) {
		var value = parseInt($(this).attr('lineNum'));
		$(this).attr("id", "expenseDetail_" + index).attr("lineNum", index);
		if(index == 0){
			$(this).find("#remove-lineitem-" + value).attr("id", "remove-lineitem-" + index);
		}else{
			$(this).find("#remove-lineitem-" + value).attr("id", "remove-lineitem-" + index).attr("onclick", "removeLineItem('" + index +"')");
		}
		
		$(this).find("#expenseDetails" + value + "\\.product").attr("id", "expenseDetails" + index + ".product").attr("name", "expenseDetails[" + index + "].product");
		$(this).find("#expenseDetails" + value + "\\.expenseAccount").attr("id", "expenseDetails" + index + ".expenseAccount").attr("name", "expenseDetails[" + index + "].expenseAccount");
		$(this).find("#expenseDetails" + value + "\\.description").attr("id", "expenseDetails" + index + ".description").attr("name", "expenseDetails[" + index + "].description");
		$(this).find("#expenseDetails" + value + "\\.quantity").attr("id", "expenseDetails" + index + ".quantity").attr("name", "expenseDetails[" + index + "].quantity");
		$(this).find("#expenseDetails" + value + "\\.unitPrice").attr("id", "expenseDetails" + index + ".unitPrice").attr("name", "expenseDetails[" + index + "].unitPrice");
		$(this).find("#expenseDetails" + value + "\\.total").attr("id", "expenseDetails" + index + ".total").attr("name", "expenseDetails[" + index + "].total");
	});
}

$(document).ready(function() { 
	$(function() {
		$( "#date" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
			yearRange: '-100y:c+nn'
		});
	});
});

$(document).ready(function() { 
	$(function() {
		$( "#dueDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
			yearRange: '-100y:c+nn'
		});
	});
});
</script>


<!-- Page content starts here -->
<div id="body">
	<div class="form-wrapper">
		<div class="form-big" style="width: 98%">
			<h5 class="colorBlue">Enter bill</h5>
			<form:form action="processAdd" method="post" modelAttribute="expense">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="expense">
					<div class="response-msg error">
						<spring:bind path="expense">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="expense.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<table class="form">
					<tr>
						<td class="key" width="100px;">
						Vendor:
						</td>
						<td>
							<form:select path="vendor" cssClass="text" id="vendor">
								<form:option value=""></form:option>
								<form:options itemLabel="name" itemValue="id" items="${vendors}"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="key">
						Notes:
						</td>
						<td>
							<form:input path="notes"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Invoice:
						</td>
						<td>
							<form:input path="invoiceNumber"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Date:
						</td>
						<td>
							<form:input path="date"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Due date:
						</td>
						<td>
							<form:input path="dueDate"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						PO/SO:
						</td>
						<td>
							<form:input path="purchaseOrder"/>
						</td>
					</tr>
				</table>
				<table class="details">
					<thead>
						<tr>
							<td>
								Product
							</td>
							<td>
								Expense account
							</td>
							<td>
								Description
							</td>
							<td>
								Quantity
							</td>
							<td>
								Price
							</td>
							<td>
								Amount
							</td>
						</tr>
					</thead>
					<c:forEach items="${expense.expenseDetails}" varStatus="i">
						<tr id="expenseDetail_${i.index}" class="lineItemsClass" lineNum = "${i.index}">
							<td>
								<form:select path="expenseDetails[${i.index}].product" cssStyle="width: 200px;">
									<form:options items="${products}" itemLabel="name" itemValue="id"/>
								</form:select>
							</td>
							<td>
								<form:select path="expenseDetails[${i.index}].expenseAccount" cssStyle="width: 200px;">
									<form:options items="${expenseAccounts}" itemLabel="name" itemValue="id"/>
								</form:select>
							</td>
							<td>
								<form:input path="expenseDetails[${i.index}].description" cssStyle="width: 200px;"/>
								<label style="color: red;"><form:errors path="expenseDetails[${i.index}].description"/></label>
							</td>
							<td>
								<form:input path="expenseDetails[${i.index}].quantity" cssStyle="width: 60px;"/>
								<label style="color: red;"><form:errors path="expenseDetails[${i.index}].quantity"/></label>
							</td>
							<td>
								<form:input path="expenseDetails[${i.index}].unitPrice" cssStyle="width: 60px;"/>
								<label style="color: red;"><form:errors path="expenseDetails[${i.index}].unitPrice"/></label>
							</td>
							<td>
								<form:input path="expenseDetails[${i.index}].total" cssStyle="width: 60px;"/>
								<label style="color: red;"><form:errors path="expenseDetails[${i.index}].total"/></label>
							</td>
							<td>
								<a href="javascript:void(0)">
									<c:if test="${i.index == 0}">
										<img id="remove-lineitem-${i.index}" alt="remove" src="<%=request.getContextPath()%>/images/icons/remove.png">
									</c:if>
									<c:if test="${i.index > 0}">
										<img id="remove-lineitem-${i.index}" alt="remove" src="<%=request.getContextPath()%>/images/icons/remove.png" onclick="removeLineItem('${i.index}')">
									</c:if>
								</a>
							</td>
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