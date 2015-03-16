<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/css.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/nav.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.innerfade.js"></script>
        <link rel="fav icon" href="<%=request.getContextPath()%>/images/icons/favicon.jpg" />
        <title>Micro finance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>
        <div id="container">
            <div id="header">
                <div id="welcome">
                    <strong>Welcome</strong>, <security:authentication property="principal.userName"></security:authentication>&#160;|&#160;<a href="<%=request.getContextPath()%>/logout">Logout</a>
                </div>
                <!-- end welcome-->
                <a href="<%=request.getContextPath()%>" id="logo"></a>
                <h4>Centre For Livelihood Credit Management Service</h4>
                <ul id="topnav">
                	<security:authorize access="hasRole('ROLE_ADMIN')">
                	<li>
                		<a href="<%=request.getContextPath()%>/admin/districts/list">Districts</a>
		                    <span>
		                    	<a href="<%=request.getContextPath()%>/admin/districts/list">District list</a> |
			                    <a href="<%=request.getContextPath()%>/admin/districts/add">Add new district</a> |
		                    </span>
                    </li>                    
                    <li>
                    	<a href="<%=request.getContextPath()%>/admin/societies/list">Societies</a>
                    	<span>
                            <a href="<%=request.getContextPath()%>/admin/societies/add">Add society</a>
                        </span>
                    </li>
                    <li>
                    	<a href="<%=request.getContextPath()%>/admin/groups/list">Groups</a>
                    	<span>
                            <a href="<%=request.getContextPath()%>/admin/groups/add">Add group</a>
                        </span>
                    </li>
                    </security:authorize>
                    <li>
                    	<a href="<%=request.getContextPath()%>/admin/beneficiaries/list">Members</a>
                    	<span>
				        	<a href="<%=request.getContextPath()%>/admin/beneficiaries/add">Add new member</a>
			            </span>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/microfinance/loans/list">Microfinance</a></li>
                    <li><a href="<%=request.getContextPath()%>/admin/users/list">Users</a>
                        <span>
                            <a href="<%=request.getContextPath()%>/admin/users/add">Add New User</a> |
                            <a href="#">User History</a> |
                            <a href="#">User Settings</a>
                        </span>
                    </li>
                    <li>
                    	<a href="<%=request.getContextPath()%>/accounting/expenses/list">Accounts</a>
                    	<span>
                            <a href="<%=request.getContextPath()%>/accounting/expenses/list">Expenses</a> |
                            <a href="<%=request.getContextPath()%>/accounting/incomes/list">Income</a> |
                            <a href="<%=request.getContextPath()%>/accounting/products/list">Products</a> |
                            <a href="<%=request.getContextPath()%>/accounting/products/add">add Products</a> |
                            <a href="<%=request.getContextPath()%>/accounting/vendors/list">Vendors</a>	|
                            <a href="<%=request.getContextPath()%>/accounting/vendors/add">add Vendors</a> |
                            <a href="<%=request.getContextPath()%>/accounting/customers/list">Customers</a>	|
                            <a href="<%=request.getContextPath()%>/accounting/customers/add">Add customer</a> |
                            <a href="<%=request.getContextPath()%>/accounting/expenseAccounts/list">Expense Accounts</a> |
                            <a href="<%=request.getContextPath()%>/accounting/expenseAccounts/add">Add Expense Accounts</a> |
                        </span>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/payroll/payrolls/list">Payroll</a>
                        <span>
                        	<a href="<%=request.getContextPath()%>/payroll/employees/list">Employees</a>|
                            <a href="<%=request.getContextPath()%>/payroll/employees/add">Add New Employee</a>|
                            <!--  
                            <a href="<%=request.getContextPath()%>/payroll/payrolls/selectPaySchedule">Process payroll</a>-->
                        </span>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/documentTracking/documentTrackings/inboundList">Document management</a>
                        <span>
                          <a href="<%=request.getContextPath()%>/documentTracking/documentTrackings/inboundList">Inbound</a>| 
                          <a href="<%=request.getContextPath()%>/documentTracking/documentTrackings/outboundList">Outbound</a>| 
                          <a href="<%=request.getContextPath()%>/documentTracking/documentTrackings/addInbound">Add in bound</a>| 
                          <a href="<%=request.getContextPath()%>/documentTracking/documentTrackings/addOutbound">Add out bound</a>
                        </span>
                    </li>
                    <li>
                    	<a href="<%=request.getContextPath()%>/reports/index">Reports</a>
                    </li>
                    </ul>
                <!-- end nav-->
            </div>