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

<script type="text/javascript" src="/static/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#captcha").click(function() {
        $("#captcha").attr("src", "/static/getVerifyCode?t=" + Math.random());
    });
});
</script>

</head>
<body>
	<form action="/userCenter/doLogin" method="post">
		<div>
			用户名<input name="loginName" type="text" />
		</div>
		<div>
			密码<input name="password" type="text" /> <input type="submit">
		</div>


		<!-- 上面对应 是的 web.xml 的<servlet>与<servlet-mapping>-->
		<div>
			<input type='text' name='challengeResponse' value=''> <img id="captcha" src="/static/getVerifyCode?t=<%=Math.random()%>"
				style="vertical-align: middle ">
		</div>
	</form>


</body>
</html>