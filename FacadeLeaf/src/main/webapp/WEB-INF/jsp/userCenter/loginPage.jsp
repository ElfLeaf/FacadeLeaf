<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css?v=<%=Math.random()%>" />

<!-- <script type="text/javascript" src="/static/js/common/jquery-1.9.1.min.js"></script> -->
<script type="text/javascript" src="/static/js/common/angular.js"></script>
<script type="text/javascript" src="/static/js/common/angular.min.js"></script>

<style>
.img_captcha {
vertical-align:top;
margin:2px;
}
#loginBox {
    padding-left:20px;
    padding-top:10px;
    padding-bottom:20px;
}
#loginBox div{
    padding-top:10px;
}
#loginForm {
border:1px gray solid;
border-radius:4px;
width:300px;
}
button {
width:80px;
border-radius:4px;
border:1px solid gray;
background-color:white;
}
button:hover {
border:1px solid orange;
}
</style>


</head>
<body ng-app="" ng-controller="loginPageCtrl">
	<form id="loginForm" action="/userCenter/doLogin" method="post">
	<div id="loginBox">
			<div>
			    <label class="label_tag w60">登录账户:</label>
				<input class="input_focus" name="loginName" type="text" placeholder="用户名"/>
			</div>
			<div>
			    <label class="label_tag w60">登录密码:</label>
				<input class="input_focus" name="password" type="text" placeholder="登录密码"/> 
			</div>
			<div>
				<label class="label_tag w60">验证码:</label>
				<input class="input_focus" type='text' name='challengeResponse' maxlength="4" style="width:40px;margin:2px;"/>
				<img id="captcha" class="img_captcha" ng-src="{{captchaUrl}}" ng-click="refreshCaptcha()" />
			</div>
			<div>
			    <button ng-click="doLogin()">登录</button>
			</div>
	</div>
	</form>

</body>


<script type="text/javascript" src="/static/js/userCenter/loginPage/ctrl/loginPageCtrl.js"></script>

</html>