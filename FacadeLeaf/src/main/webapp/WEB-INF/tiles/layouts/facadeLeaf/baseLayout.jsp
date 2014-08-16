<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/jsp/taglibs/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css" />
  
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
    <tiles:insertAttribute name="left_crumbs" />
    <tiles:insertAttribute name="primary-content" />
  </body>
</html>