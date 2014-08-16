<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/error.css" />
<title>不存在页面</title>
</head>
<body>
	<div class="transparent_30 error404"></div>
	<div style="text-align:center;font-size:200%;font-weight:900;">页面不存在</div>
</body>
</html>