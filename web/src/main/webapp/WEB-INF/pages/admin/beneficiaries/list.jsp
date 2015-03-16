<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>

<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/javascript/choosen/chosen.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/choosen/chosen.jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".chzn-select").chosen({
			allow_single_deselect : true
		});
	});
</script>

<div id="body">
  <div class="table-top-caption-left">Members</div>
  <br style="clear: both;" />

  <c:url var="beneficiarySearchUrl" value="/admin/beneficiaries/search"></c:url>
  <form:form action="${beneficiarySearchUrl}" modelAttribute="beneficiarySearch" method="get">
    <table style="width: 100%;" class="search">
      <tr>
        <td>First name:</td>
        <td style="background-color: white;"><form:input path="firstName" cssStyle="width: 240px;" /></td>
        <td>Last name:</td>
        <td style="background-color: white;"><form:input path="lastName" cssStyle="width: 240px;" /></td>
        <td>NIC No:</td>
        <td style="background-color: white;"><form:input path="nicNo" cssStyle="width: 240px;" /></td>
        <td>Member no:</td>
        <td style="background-color: white;"><form:input path="memberNo" cssStyle="width: 240px;" /></td>
      </tr>
      <tr>
        <td>District:</td>
        <td style="background-color: white;"><form:select path="district" cssClass="dropdown chzn-select" cssStyle="width: 240px;" id="district">
            <form:option value=""></form:option>
            <form:options itemLabel="name" itemValue="id" items="${districts}" />
          </form:select></td>
        <td class="key">Division:</td>
        <td style="background-color: white;"><form:select path="division" cssClass="dropdown chzn-select" cssStyle="width: 240px;" id="division">
            <form:option value=""></form:option>
            <form:options itemLabel="name" itemValue="id" items="${divisions}" />
          </form:select></td>
        <td class="key">GN Division:</td>
        <td style="background-color: white;"><form:select path="gnDivision" cssClass="dropdown chzn-select" cssStyle="width: 240px;" id="gnDivision">
            <form:option value=""></form:option>
            <form:options itemLabel="name" itemValue="id" items="${gnDivisions}" />
          </form:select></td>
        <td>Society:</td>
        <td style="background-color: white;"><form:select path="society" cssClass="dropdown chzn-select" cssStyle="width: 240px;" id="group-society">
            <form:option value=""></form:option>
            <form:options itemLabel="name" itemValue="id" items="${societies}" />
          </form:select></td>
      </tr>
      <tr>
        <td>Group:</td>
        <td style="background-color: white;"><form:select path="group" cssClass="dropdown chzn-select" cssStyle="width: 240px;" id="group">
            <form:option value=""></form:option>
            <form:options itemLabel="name" itemValue="id" items="${groups}" />
          </form:select></td>
      </tr>
      <tr>
        <td colspan="8" align="center"><input type="submit" value="Search" class="buttonBlue"></td>
      </tr>
    </table>
  </form:form>
  <div style="min-height: 20px;"></div>
  <div id="wrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
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
          <td class="tableData"><a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}">View</a>&nbsp; <a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/edit">Edit</a> <!-- 
						<c:if test="${model.status == 'ACTIVE'}">
							<a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/deactivate" onclick="return confirm('Are you sure you want to delete?')">DeActivate</a>
						</c:if> <c:if test="${model.status ==  'INACTIVE'}">
							<a href="<%=request.getContextPath()%>/admin/beneficiaries/${model.id}/activate">Activate</a>
						</c:if> --> <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${model.id}/loans/beneficiaryLoans">Loans</a> <a href="<%=request.getContextPath()%>/microfinance/beneficiaries/${model.id}/savingAccounts/benificiarySavingAccounts">Savings</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <pagination:pagination pager="${pager}" />
</div>
<%@include file="../../common/footer.jsp"%>