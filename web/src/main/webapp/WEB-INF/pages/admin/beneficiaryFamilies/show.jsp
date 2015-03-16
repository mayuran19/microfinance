<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../common/header.jsp" %>
<div id="body">
	<div class="table-top-caption-left">Showing details for family member <i>${beneficiaryFamilyMember.firstName} ${beneficiaryFamilyMember.lastName}</i></div>
	<br style="clear: both;"/>
	<div class="wrap">
		<table class="details">
			<tr>
				<td class="key">Beneficiary first name:</td>
				<td>${beneficiaryFamilyMember.beneficiary.firstName}</td>
			</tr>
			<tr>
				<td class="key">Beneficiary last name:</td>
				<td>${beneficiaryFamilyMember.beneficiary.lastName}</td>
			</tr>
			<tr>
				<td class="key">First name:</td>
				<td>${beneficiaryFamilyMember.firstName}</td>
			</tr>
			<tr>
				<td class="key">Last name:</td>
				<td>${beneficiaryFamilyMember.lastName}</td>
			</tr>
			<tr>
				<td class="key">Relationship:</td>
				<td>${beneficiaryFamilyMember.familyRelationship.name}</td>
			</tr>
			<tr>
				<td class="key">Date of birth:</td>
				<td>${beneficiaryFamilyMember.dateOfBirth}</td>
			</tr>
			<tr>
				<td class="key">Gender:</td>
				<td>${beneficiaryFamilyMember.gender}</td>
			</tr>
			<tr>
				<td class="key">NIC No:</td>
				<td>${beneficiaryFamilyMember.nicNo}</td>
			</tr>
			<tr>
				<td class="key">Occupation:</td>
				<td>${beneficiaryFamilyMember.occupation}</td>
			</tr>
		</table>
	</div>
</div>
<%@include file="../../common/footer.jsp" %>