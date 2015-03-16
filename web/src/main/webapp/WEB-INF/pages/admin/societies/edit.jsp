<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<c:url var="getAllDivisionsJSONByDistrictIdURL" value="/admin/divisions/getAllDivisionsJSONByDistrictId"></c:url>
<c:url var="getAllGNDivisionsJSONByDivisionIdURL" value="/admin/divisions/getAllGNDivisionsJSONByDivisionId"></c:url>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#gnDivision-division-district')
								.change(
										function() {
											$
													.getJSON(
															'${getAllDivisionsJSONByDistrictIdURL}',
															{
																districtId : $(
																		this)
																		.val(),
																ajax : 'true'
															},
															function(data) {
																var html = '<option value="0"></option>';
																var len = data.length;
																for ( var i = 0; i < len; i++) {
																	html += '<option value="' + data[i].id + '">'
																			+ data[i].name
																			+ '</option>';
																}
																html += '</option>';

																$(
																		'#gnDivision-division')
																		.html(
																				html);
																$('#gnDivision')
																		.html(
																				"");
															});
										});
					});

	$(document)
			.ready(
					function() {
						$('#gnDivision-division')
								.change(
										function() {
											$
													.getJSON(
															'${getAllGNDivisionsJSONByDivisionIdURL}',
															{
																divisionId : $(
																		this)
																		.val(),
																ajax : 'true'
															},
															function(data) {
																var html = '<option value="0"></option>';
																var len = data.length;
																for ( var i = 0; i < len; i++) {
																	html += '<option value="' + data[i].id + '">'
																			+ data[i].name
																			+ '</option>';
																}
																html += '</option>';

																$('#gnDivision')
																		.html(
																				html);
															});
										});
					});
</script>

<!-- Page content starts here -->
<div id="body">
  <div class="form-wrapper">
    <div class="form-big">
      <h5 class="colorBlue">EDIT SOCIETY</h5>
      <form:form action="processEdit" method="post" modelAttribute="society">
        <!-- print all errors if exists -->
        <spring:hasBindErrors name="society">
          <div class="response-msg error">
            <spring:bind path="society">
              <c:forEach items="${status.errors.globalErrors}" var="error">
                <span><spring:message code="${error.code}"></spring:message></span>
              </c:forEach>
            </spring:bind>
            <spring:bind path="society.*">
              <c:forEach items="${status.errors.fieldErrors}" var="error">
                <spring:message code="${error.code}"></spring:message>
                <br>
              </c:forEach>
            </spring:bind>
          </div>
        </spring:hasBindErrors>
        <!-- spring error print ends -->
        <table class="show">
          <tr>
            <td class="key">District:</td>
            <td><form:select path="gnDivision.division.district" cssClass="text" id="gnDivision-division-district">
                <form:option value=""></form:option>
                <form:options itemLabel="name" itemValue="id" items="${districts}" />
              </form:select></td>
            <td class="key">Division:</td>
            <td><form:select path="gnDivision.division" cssClass="text" id="gnDivision-division">
                <form:option value=""></form:option>
                <form:options itemLabel="name" itemValue="id" items="${divisions}" />
              </form:select></td>
          </tr>
          <tr>
            <td class="key">GN Division:</td>
            <td><form:select path="gnDivision" cssClass="text" id="gnDivision">
                <form:option value=""></form:option>
                <form:options itemLabel="name" itemValue="id" items="${gnDivisions}" />
              </form:select></td>
            <td class="key">Society name:</td>
            <td><form:input path="name" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">Description:</td>
            <td><form:textarea path="description" cssClass="text" cssStyle="width:400px;" /></td>
            <td class="key">Allowed interest rate:</td>
            <td><form:input path="allowedInterestRate" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">Shared interest rate:</td>
            <td><form:input path="shareInterestRate" cssClass="text" /></td>
            <td class="key">Beneficiary interest rate:</td>
            <td><form:input path="savingAccountInterest" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">President first name:</td>
            <td><form:input path="president.firstName" cssClass="text" /></td>
            <td class="key">Secretary first name:</td>
            <td><form:input path="secretary.firstName" cssClass="text" /></td>
          </tr>
          <tr>
            <td class="key">Treasurer first name:</td>
            <td><form:input path="treasurer.firstName" cssClass="text" /></td>
            <td class="key"></td>
            <td></td>
          </tr>
        </table>
        <!-- Hidden variables to keep the president details -->
        <form:hidden path="president.id" />
        <form:hidden path="secretary.id" />
        <form:hidden path="treasurer.id" />
        <form:hidden path="id" />
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