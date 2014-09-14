<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/error.css" />

<script type="text/javascript" src="/static/js/common/jquery-1.9.1.min.js"></script>

<title>系统故障</title>
</head>
<body class="body">
	<!-- 背景图 -->
	<div class="transparent_30 error500"></div>
	<div id="error_box">
		<div style="font-size:200%">系统故障，错误代码：${pageContext.errorData.statusCode}</div>

		<div>请将以下错误信息提交给网站技术人员，技术人员会追查处理</div>

		<button id="detail_btn">详细信息+</button>
		<div id="error_detail">
			<div>
				请求地址：<%=basePath.substring(0, basePath.length() - 1)%>${pageContext.errorData.requestURI}</div>
			<div>错误信息：${pageContext.exception}</div>
			<div>
				错误堆栈信息：<br />
				<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
					<div>${trace}</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#error_detail").hide();

		$("#detail_btn").click(function() {
			if ($("#detail_btn").text() == "详细信息+") {
				$("#detail_btn").text("折叠信息-");
			} else {
				$("#detail_btn").text("详细信息+");
			}
			$("#error_detail").toggle();
		});
	});
</script>

</html>