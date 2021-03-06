<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- include the header file -->
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/ui-lightness/jquery-ui-1.8.14.custom.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.8.14.custom.min.js"></script>
<!-- header ends -->

<!-- Page content starts here -->
<div id="body">
  <div class="form-wrapper">
    <div class="form">
      <h5 class="colorBlue">EDIT CUSTOMER</h5>
      <form:form action="processEdit" method="post" modelAttribute="customer">
        <!-- print all errors if exists -->
        <spring:hasBindErrors name="customer">
          <div class="response-msg error">
            <spring:bind path="customer">
              <c:forEach items="${status.errors.globalErrors}" var="error">
                <span><spring:message code="${error.code}"></spring:message></span>
              </c:forEach>
            </spring:bind>
            <spring:bind path="customer.*">
              <c:forEach items="${status.errors.fieldErrors}" var="error">
                <spring:message code="${error.code}" ></spring:message><br>
              </c:forEach>
            </spring:bind>
          </div>
        </spring:hasBindErrors>     
        <!-- spring error print ends -->
        <form:hidden path="id"/>
        <table class="form">
          <tr>
            <td class="key">
            Name:
            </td>
            <td>
            <form:input path="customerName" cssClass="text"/>
            </td>
          </tr>
          <tr>
            <td class="key">
            Address:
            </td>
            <td>
            <form:textarea path="address" cssClass="text"/>
            </td>
          </tr>
        </table>
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