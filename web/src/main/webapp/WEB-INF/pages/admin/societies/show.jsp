<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="table-top-caption-left">Showing details of <i>'${society.name}'</i> society</div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District:</td>
				<td>${society.gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${society.gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division:</td>
				<td>${society.gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">President:</td>
				<td>${society.president.firstName} ${society.president.lastName}</td>
			</tr>
			<tr>
				<td class="key">Secretary:</td>
				<td>${society.secretary.firstName} ${society.secretary.lastName}</td>
			</tr>
			<tr>
				<td class="key">Treasurer:</td>
				<td>${society.treasurer.firstName} ${society.treasurer.lastName}</td>
			</tr>
			<tr>
				<td class="key">Allowed Interest Rate:</td>
				<td>${society.allowedInterestRate}</td>
			</tr>
			<tr>
				<td class="key">Share Interest Rate:</td>
				<td>${society.shareInterestRate}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="table-top-caption">
		<div class="table-top-caption-left">Groups comes under <i>${society.name}</i> society</div>
	</div>
	<div class="clear"></div>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">Name</td>
				<td width="14%" class="tableHeader">President</td>
				<td width="14%" class="tableHeader">Ecc president</td>
				<td width="14%" class="tableHeader">Ecc name</td>
				<td width="14%" class="tableHeader">Contact no</td>
				<td width="6%" class="tableHeader">Actions</td>
			</tr>
			
			<c:forEach items="${pager.resultSet}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.name}</td>
					<td class="tableData-left-align">${model.president.firstName} ${model.president.lastName}</td>
					<td class="tableData-left-align">${model.eccPresident.firstName} ${model.eccPresident.lastName}</td>
					<td class="tableData-left-align">${model.eccName}</td>
					<td class="tableData-left-align">${model.contactNo}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/admin/groups/${model.id}">View</a>
						<a href="<%=request.getContextPath()%>/admin/groups/${model.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp" %>