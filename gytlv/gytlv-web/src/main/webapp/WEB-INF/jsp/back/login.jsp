<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="stylesheet" href="/plugin/css/bootstrap.min.css" type="text/css" />
<script src="/plugin/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="/plugin/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/plugin/js/dialog.js" type="text/javascript"></script>
<script src="/back/js/login.js" type="text/javascript"></script>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<title>清风暮雨</title>
</head>
<body>
	<shiro:notAuthenticated>
		<div style="margin: 5% auto; width: 500px; height: 300px">
			<div class="thumbnail" align="center">thanks welcome!</div>
			<form id="loginForm">
				<div class="form-group">
					<label for="username">用户名</label><span id="usernametip"></span> <input type="text" class="form-control" id="username" placeholder="请填写用户名" value="root">
				</div>
				<div class="form-group">
					<label for="password">密码</label> <input type="password" class="form-control" id="password" placeholder="请填写密码" value="">
				</div>
				<div class="form-group">
					<input type="checkbox" name="rememberMe" id="rememberMe" checked="checked" />记住我<br>
				</div>
			</form>
			<div class="form-group">
				<button type="submit" id="login" class="btn btn-default">登录</button>
				<button type="submit" id="reg" class="btn btn-default">注册</button>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="/">回到首页>>></a>
			</div>
		</div>
	</shiro:notAuthenticated>
	<shiro:authenticated>
		<%
			response.sendRedirect("/");
		%>
	</shiro:authenticated>
</body>
</html>