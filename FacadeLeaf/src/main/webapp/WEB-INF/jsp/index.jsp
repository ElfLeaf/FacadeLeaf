<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css" />
  </head>
  
  <body>
    <div id="left_crumbs" class="transparent_97">
        <ul class="font_w900">
            <li id="li_login">登录</li>
            <li id="li_setting">设置</li>
        </ul>
    </div>


    <div id="container">
        <div id="box"
            style="margin:auto;width:1160px;height:1000px;border:1px solid red;">
            <div id="crumbs"
                style="margin:auto;height:100px;width:1160px;background-color:white;"></div>

            <h1>My First Heading</h1>

            <p>My first paragraph1.</p>
        </div>


    </div>
  </body>
</html>

