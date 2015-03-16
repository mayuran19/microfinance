<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<div id="body">		
	<div class="table-top-caption-left">Showing all groups</div>
	<br style="clear: both;"/>
	<div id="wrap">

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">Name</td>
				<td width="14%" class="tableHeader">Society</td>
				<td width="14%" class="tableHeader">GN Division</td>
				<td width="14%" class="tableHeader">Division</td>
				<td width="14%" class="tableHeader">District</td>
				<td width="6%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${pager.resultSet}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.name}</td>
					<td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/societies/${model.society.id}">${model.society.name}</a></td>
					<td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/divisions/${model.society.gnDivision.division.id}/gnDivisions/${model.society.gnDivision.id}">${model.society.gnDivision.name}</a></td>
					<td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/districts/${model.society.gnDivision.division.district.id}/divisions/${model.society.gnDivision.division.id}">${model.society.gnDivision.division.name}</a></td>
					<td class="tableData-left-align"><a href="<%=request.getContextPath()%>/admin/districts/${model.society.gnDivision.division.district.id}">${model.society.gnDivision.division.district.name}</a></td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/admin/groups/${model.id}">View</a>
						<a href="<%=request.getContextPath()%>/admin/groups/${model.id}/edit">Edit</a>
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