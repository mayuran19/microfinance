<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<!--  
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/javascript/jquery/themes/base/jquery-ui.css" />
<script src="<%=request.getContextPath()%>/javascript/jquery/js/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/javascript/jquery/js/jquery-ui-1.9.0.custom.min.js"></script>
<!-- header ends -->

<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#sentOutDate").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd-mm-yy'
			});
		});
	});
</script>

<!-- Page content starts here -->
<div id="body">
  <div class="wrap">
    <div class="breadcrumb"></div>
    <div class="tab-form-wrapper">
      <table class="form">
        <tr>
          <td align="center">
            <h5>
              Edit
              <c:if test="${documentTrackingForm.inboundOutBound == 1}">inbound</c:if>
              <c:if test="${documentTrackingForm.inboundOutBound == 2}">outbound</c:if>
              inbound document
            </h5> <form:form action="processEditDocumentTracking" method="post" modelAttribute="documentTrackingForm">
              <!-- print all errors if exists -->
              <table class="form-1-error" style="width: 60%;">
                <tr>
                  <td><spring:hasBindErrors name="documentTrackingForm">
                      <div class="response-msg error">
                        <spring:bind path="documentTrackingForm">
                          <c:forEach items="${status.errors.globalErrors}" var="error">
                            <span><spring:message code="${error.code}"></spring:message></span>
                          </c:forEach>
                        </spring:bind>
                        <spring:bind path="documentTrackingForm.*">
                          <c:forEach items="${status.errors.fieldErrors}" var="error">
                            <spring:message code="${error.code}"></spring:message>
                            <br>
                          </c:forEach>
                        </spring:bind>
                      </div>
                    </spring:hasBindErrors></td>
                </tr>
              </table>
              <!-- spring error print ends -->
              <table class="form-1" style="width: 60%;">
                <tr>
                  <td>Document type:</td>
                  <td><form:select path="documentTypeId" items="${documentTypes}" itemLabel="name" itemValue="id"></form:select></td>
                </tr>
                <tr>
                  <td>Document sending medium:</td>
                  <td><form:select path="documentSendingMediumId" items="${documentSendingMediums}" itemLabel="name" itemValue="id"></form:select></td>
                </tr>
                <tr>
                  <td>Document status:</td>
                  <td><form:select path="documentStatusId" items="${documentStatus}" itemLabel="name" itemValue="id"></form:select></td>
                </tr>
                <tr>
                  <td>Sending medium no:</td>
                  <td><form:input path="sendingMediumIdentityNumber" /></td>
                </tr>
                <tr>
                  <td>To whom:</td>
                  <td><form:input path="sendingToPersonName" /></td>
                </tr>
                <tr>
                  <td>By whom:</td>
                  <td><form:input path="sendingByPersonName" /></td>
                </tr>
                <tr>
                  <td>Subject:</td>
                  <td><form:input path="documentSubject" /></td>
                </tr>
                <tr>
                  <td>Sent date:</td>
                  <td><form:input path="sentOutDate" /></td>
                </tr>
                <tr>
                  <td>Reference No:</td>
                  <td><form:input path="referenceNumber" /></td>
                </tr>
                <tr>
                  <td>No of documents:</td>
                  <td><form:input path="noOfDocuments" /></td>
                </tr>
                <tr>
                  <td>Action taken:</td>
                  <td><form:textarea path="remarks" /></td>
                </tr>
              </table>
              <form:hidden path="inboundOutBound" />
              <form:hidden path="id" />
              <p align="center">
                <input type="SUBMIT" value="Save" class="buttonBlue" />
              </p>
            </form:form>
          </td>
        </tr>
      </table>
    </div>
  </div>
</div>
<!-- page content end -->
<!-- include the footer file -->
<%@include file="../../common/footer.jsp"%>
<!-- footer end -->