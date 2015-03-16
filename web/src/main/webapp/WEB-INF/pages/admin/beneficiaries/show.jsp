<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="table-top-caption-left">Showing details for beneficiary <i>${beneficiary.firstName} ${beneficiary.lastName}</i></div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">District:</td>
				<td>${beneficiary.group.society.gnDivision.division.district.name}</td>
			</tr>
			<tr>
				<td class="key">Division:</td>
				<td>${beneficiary.group.society.gnDivision.division.name}</td>
			</tr>
			<tr>
				<td class="key">GN Division:</td>
				<td>${beneficiary.group.society.gnDivision.name}</td>
			</tr>
			<tr>
				<td class="key">Society:</td>
				<td>${beneficiary.group.society.name}</td>
			</tr>
			<tr>
				<td class="key">Group:</td>
				<td>${beneficiary.group.name}</td>
			</tr>
			<tr>
				<td class="key">Member no:</td>
				<td>${beneficiary.memberNo}</td>
			</tr>
			<tr>
				<td class="key">NIC No:</td>
				<td>${beneficiary.nicNo}</td>
			</tr>
			<tr>
				<td class="key">Address:</td>
				<td>${beneficiary.address}</td>
			</tr>
			<tr>
				<td class="key">Date of birth:</td>
				<td>${beneficiary.dateOfBirth}</td>
			</tr>
			<tr>
				<td class="key">Gender:</td>
				<td>${beneficiary.gender}</td>
			</tr>
			<tr>
				<td class="key">Marital status:</td>
				<td>${beneficiary.maritalStatus}</td>
			</tr>
			<tr>
				<td class="key">Special indentification:</td>
				<td>${beneficiary.specialIdentification}</td>
			</tr>
		</table>
	</div>
	<div class="40-px-height">&nbsp;</div>
	<div class="table-top-caption">
		<div class="table-top-caption-left">Family members of <i>${beneficiary.firstName} ${beneficiary.lastName}</i></div>
		<div class="table-top-caption-right"><a href="<%=request.getContextPath()%>/admin/beneficiaries/${beneficiary.id}/beneficiaryFamilies/add">+Add new family member</a></div>
	</div>
	<div class="clear"></div>
	<div id="wrap">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableBorder">
			<tr>
				<td width="2%" class="tableHeader">ID</td>
				<td width="14%" class="tableHeader">First name</td>
				<td width="14%" class="tableHeader">Last name</td>
				<td width="20%" class="tableHeader">NIC No</td>
				<td width="20%" class="tableHeader">Relationship</td>
				<td width="20%" class="tableHeader">Action</td>
			</tr>
			
			<c:forEach items="${beneficiary.familyMembers}" var="model">
				<tr>
					<td class="tableData">${model.id}</td>
					<td class="tableData-left-align">${model.firstName}</td>
					<td class="tableData-left-align">${model.lastName}</td>
					<td class="tableData-left-align">${model.nicNo}</td>
					<td class="tableData-left-align">${model.familyRelationship.name}</td>
					<td class="tableData">
						<a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.beneficiary.id}/beneficiaryFamilies/${model.id}">View</a>&nbsp;
						<a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.beneficiary.id}/beneficiaryFamilies/${model.id}/edit">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@include file="../../common/footer.jsp" %>