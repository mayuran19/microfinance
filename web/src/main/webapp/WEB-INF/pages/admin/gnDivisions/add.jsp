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
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/districts/list">Districts</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/districts/${gnDivision.division.district.id}">${gnDivision.division.district.name}</a>&nbsp;>>&nbsp;
			Divisions&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/divisions/${gnDivision.division.id}">${gnDivision.division.name}</a>
		</div>
	</div>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District name:</td>
				<td>${gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Description:</td>
				<td>${gnDivision.division.district.description}</td>
			</tr>
			<tr>
				<td class="key">Description:</td>
				<td>${gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">Description:</td>
				<td>${gnDivision.division.description}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="form-wrapper">
		<div class="form">
			<h5 class="colorBlue">ADD NEW GN DIVISION FOR DIVISION ${gnDivision.division.name}</h5>
			<form:form action="processAdd" method="post" modelAttribute="gnDivision">
				<!-- print all errors if exists -->
				<spring:hasBindErrors name="gnDivision">
					<div class="response-msg error">
						<spring:bind path="gnDivision">
							<c:forEach items="${status.errors.globalErrors}" var="error">
								<span><spring:message code="${error.code}"></spring:message></span>
							</c:forEach>
						</spring:bind>
						<spring:bind path="gnDivision.*">
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
						Division name:
						</td>
						<td>
						<form:input path="name" cssClass="text"/>
						</td>
					</tr>
					<tr>
						<td class="key">
						Division description:
						</td>
						<td>
						<form:textarea path="description" cssClass="text"/>
						</td>
					</tr>
				</table>
				<form:hidden path="division.id"/>
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