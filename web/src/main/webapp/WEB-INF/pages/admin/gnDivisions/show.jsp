<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="wrap">
		<div class="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/districts/list">Districts</a>
		</div>
	</div>
	<div class="table-top-caption-left">Showing details of <i>${gnDivision.name}</i> GN division</div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District:</td>
				<td>${gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${gnDivision.division.name}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="table-top-caption">
		<div class="table-top-caption-left">Societies of GN Division <i>${gnDivision.name}</i></div>
	</div>
	<div class="clear"></div>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">Society Name</td>
				<td width="14%" class="tableHeader">President</td>
				<td width="14%" class="tableHeader">Secretary</td>
				<td width="14%" class="tableHeader">Treasurer</td>
				<td width="6%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${pager.resultSet}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.name}</td>
					<td class="tableData-left-align">${model.president.firstName} ${model.president.lastName}</td>
					<td class="tableData-left-align">${model.secretary.firstName} ${model.secretary.lastName}</td>
					<td class="tableData-left-align">${model.treasurer.firstName} ${model.treasurer.lastName}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/admin/societies/${model.id}">View</a>&nbsp;
						<a href="<%=request.getContextPath()%>/admin/societies/${model.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp" %>