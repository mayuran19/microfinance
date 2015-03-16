<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="../../common/header.jsp"%>
<div id="body">
	<div class="wrap">
		<div class="table-top-caption-left">
			Showing details for group <i>${group.name}</i>
		</div>
		<br style="clear: both;" />
		<table class="details">
			<tr>
				<td class="key">District:</td>
				<td>${group.society.gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${group.society.gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division:</td>
				<td>${group.society.gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">President:</td>
				<td>${group.president.firstName} ${group.president.lastName}</td>
			</tr>
			<tr>
				<td class="key">Ecc President:</td>
				<td>${group.eccPresident.firstName}
					${group.eccPresident.lastName}</td>
			</tr>
			<tr>
				<td class="key">Group contact no:</td>
				<td>${group.contactNo}</td>
			</tr>
			<tr>
				<td class="key">ECC Name:</td>
				<td>${group.eccName}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="table-top-caption">
		<div class="table-top-caption-left">
			Beneficiaries comes under <i>${group.name}</i> group
		</div>
	</div>
	<div class="clear"></div>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">First name</td>
				<td width="14%" class="tableHeader">Last name</td>
				<td width="14%" class="tableHeader">Nic number</td>
				<td width="14%" class="tableHeader">Group</td>
				<td width="20%" class="tableHeader">Actions</td>
			</tr>

			<c:forEach items="${pager.resultSet}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.firstName}</td>
					<td class="tableData-left-align">${model.lastName}</td>
					<td class="tableData-left-align">${model.nicNo}</td>
					<td class="tableData-left-align">${model.group.name}</td>
					<td class="tableData"><a
						href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}">View</a>&nbsp;
						<a
						href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/edit">Edit</a>
						<c:if test="${model.status == 'ACTIVE'}">
							<a
								href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/deactivate"
								onclick="return confirm('Are you sure you want to delete?')">DeActivate</a>
						</c:if> <c:if test="${model.status ==  'INACTIVE'}">
							<a
								href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/activate">Activate</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<pagination:pagination pager="${pager}" />
</div>
</div>
<%@include file="../../common/footer.jsp"%>