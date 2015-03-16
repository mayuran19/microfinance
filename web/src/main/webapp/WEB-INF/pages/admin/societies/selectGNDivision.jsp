<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/districts/list">Districts</a>&nbsp;>>&nbsp;
			<a href="<%=request.getContextPath()%>/admin/districts/${gnDivision.division.district.id}">${gnDivision.division.district.name}</a>&nbsp;>>&nbsp;
			${gnDivision.division.name}
		</div>
	</div>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District name:</td>
				<td>${gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">District description:</td>
				<td>${gnDivision.division.district.description}</td>
			</tr>
			<tr>
				<td class="key">Division name:</td>
				<td>${gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">Division description:</td>
				<td>${gnDivision.division.description}</td>
			</tr>
			<tr>
				<td class="key">GN Division name:</td>
				<td>${gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division description:</td>
				<td>${gnDivision.description}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
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
						<a href="<%=request.getContextPath()%>/admin/societies/${model.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="pagination">
		<ul class="pagination">
			<c:forEach items="${pager.pages}" var="page">
				<c:choose>
					<c:when test="${pager.pager.page == page}">
						<li class="active">${page}</li>
					</c:when>
					<c:otherwise>
						<li><a href="?page=${page}&pageSize=${pager.pager.pageSize}">${page}</a></li>
					</c:otherwise>
				</c:choose>			
			</c:forEach>
		</ul>
	</div>
</div>
<%@include file="../../common/footer.jsp" %>