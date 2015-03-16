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
			<h5 class="colorBlue">ADD NEW CONFIGURATION</h5>
			<form:form action="processAdd" method="post" modelAttribute="configuration">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="configuration">
					<div class="response-msg error">
						<spring:bind path="configuration">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="configuration.*">
							<c:forEach items="${status.errors.fieldErrors}" var="error">
								<spring:message code="${error.code}" ></spring:message><br>
							</c:forEach>
						</spring:bind>
					</div>
				</spring:hasBindErrors>			
				<!-- spring error print ends -->
				<table class="form">
					<tr>
						<td class="key">
						configuration key:
						</td>
						<td>
						<form:input path="key" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Description:
						</td>
						<td>
						<form:input path="value" cssClass="text"/>
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