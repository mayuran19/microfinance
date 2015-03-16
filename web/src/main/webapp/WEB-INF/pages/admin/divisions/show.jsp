<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/districts/list">Districts</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/districts/${division.district.id}">${division.district.name}</a>&nbsp;>>&nbsp;
			${division.name}
		</div>
	</div>
	<div class="table-top-caption-left">Showing details of <i>${division.name}</i> division</div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District name:</td>
				<td>${division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Division name:</td>
				<td>${division.name}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="table-top-caption">
		<div class="table-top-caption-left">GN Divisions comes under <i>${division.name}</i> division</div>
		<div class="table-top-caption-right"><a href="<%=request.getContextPath()%>/admin/divisions/${division.id}/gnDivisions/add">+Add new GN division</a></div>
	</div>
	<div class="clear"></div>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">Name</td>
				<td width="14%" class="tableHeader">Description</td>
				<td width="20%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${pager.resultSet}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.name}</td>
					<td class="tableData-left-align">${model.description}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/admin/divisions/${division.id}/gnDivisions/${model.id}">View</a>&nbsp;
						<a href="<%=request.getContextPath()%>/admin/divisions/${division.id}/gnDivisions/${model.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp" %>