<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/userCenter/doLogin" method="post">
		<div>
			用户名<input name="loginName" type="text" />
		</div>
		<div>
			密码<input name="password" type="text" /> <input type="submit">
		</div>
		
		<img src="/static/getVerifyCode"><br />
		<!-- 上面对应 是的 web.xml 的<servlet>与<servlet-mapping>-->
		<input type='text' name='challengeResponse' value=''>
	</form>
</body>
</html>