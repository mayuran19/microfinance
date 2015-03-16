<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/css.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/nav.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.innerfade.js"></script>
        <title>Micro finance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="welcome">
                    <strong>Welcome</strong>, 
                    <security:authorize access="isAuthenticated()">
                    	<!--<security:authentication property="principal.userName"></security:authentication>-->
                    	&#160;|&#160;<a href="<%=request.getContextPath()%>/logout">Logout</a>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">Guest</security:authorize> 
                </div>
                <!-- end welcome-->
                <a href="<%=request.getContextPath()%>" id="logo"></a>
                <h4>Centre For Livelihood Credit Management Service</h4>
                <ul id="topnav">
                    <li><a href="#">Society</a></li>
                    <li><a href="#">Members</a></li>
                    <li><a href="#">Payment</a></li>
                    <li><a href="#">Microfinance</a></li>
                    <li><a href="#">Users</a>
                        <span>
                            <a href="#">Add New User</a> |
                            <a href="#">User History</a> |
                            <a href="#">User Settings</a>
                        </span>
                    </li>
                    <li><a href="#">Accounts</a></li>
                    <!-- Second Level Menue Example
                    <li>
                        <a href="#">About</a>
                        <span>
                            <a href="#">The Company</a> |
                            <a href="#">The Team</a> |
                            <a href="#">Careers</a>
                        </span>


                    -->
                </ul>
                <!-- end nav-->
            </div>
            <!-- end Header-->
            <div id="body">
                <ul id="display">
                    <li><img src="<%=request.getContextPath()%>/images/rolling/006.jpg"></li>
                    <li><img src="<%=request.getContextPath()%>/images/rolling/009.jpg"></li>
                </ul>
                <!-- end display-->
                <security:authorize access="isAnonymous()"> 
	                <div class="left_3">
		                <form action="<%=request.getContextPath()%>/j_spring_security_check" method="post">
		                	<c:if test="${not empty param.login_error}">
								<div class="response-msg error">
									<span>Your login attempt was not successful, try again.</span>
									<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
								</div>
							</c:if>
		                    <h5 class="colorBlue">USER LOGIN</h5>
		                    <p>
		                        <label class="marginRight_1" for="username"><strong>Username :</strong></label>
		                        <input type="text" style="width: 70%;" name="j_username" id="j_username"/>
		                    </p>
		                    <p>
		                        <label class="marginRight_1" for="password"><strong>Password :</strong></label>
		                        <input type="password" style="width: 70%;" name="j_password" id="j_password" />
		                    </p>
		                    <p align="center">
		                        <input type="SUBMIT" value="Submit" class="buttonBlue"/>
		                    </p>
		                </form>    
	                </div>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                	<div class="left_3">
                		Content
                	</div>
                </security:authorize>
                <div class="center_3">
                    Content
                </div>
                <div class="right_3">
                    Content
                </div>
            </div>
            <!--End Body -->
            <div id="footer">
                	&#169; 2011-2012
            </div>
        </div>
        <!-- end Container-->
    </body>
</html>
