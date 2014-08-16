<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/taglibs/taglibs.jsp" %>

<div id="left_crumbs" class="transparent_97">
	<ul class="font_w900">
		<li id="li_login">登录${sessionScope.user}</li>
		<li id="li_signup">注册${action}</li>
		<li id="li_setting">设置</li>
	</ul>
</div>