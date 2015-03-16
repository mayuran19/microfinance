<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CLCMS Login</title>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/stylesheet/login.css" />
</head>
<body>
	<div id="content">
		<c:if test="${not empty param.login_error}">
			<div class="error">
				Your login attempt was not successful, try again.<br /> Reason:
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</div>
		</c:if>
		<form action="../j_spring_security_check" method="post">
			<ul>
				<li><label for="username"> Username </label> <input
					name="j_username" id="j_username" type="text" tabindex="1"
					accesskey="n" /></li>
				<li><label for="password"> Password <a href="#"
						class="forgot_password">(Forgot Your Password?)</a> </label> <input
					name="j_password" id="j_password" type="password" tabindex="2"
					accesskey="n" /></li>
				<li class="no_bottom_margin"><label
					for="_spring_security_remember_me">Remember Me?</label> <input
					class="checkbox" id="_spring_security_remember_me"
					name="_spring_security_remember_me" type="checkbox" value="true" />
				</li>
			</ul>
			<div id="buttons">
				<input name="submit" id="submit" type="submit" value="Login" />
			</div>
		</form>
	</div>
</body>
</html>