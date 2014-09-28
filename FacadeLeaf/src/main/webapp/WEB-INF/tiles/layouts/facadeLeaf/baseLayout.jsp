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
  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/leafBase_v1.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/head.css" />
    
    <!--<script type="text/javascript" src="/static/js/common/angular.js"></script>-->
    <script type="text/javascript" src="/static/js/common/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="/static/js/common/angular.min.js"></script> 

     
	<!--[if lte IE 9]>
		<script src="/static/js/common/respond.min.js"></script>
		<script src="/static/js/common/html5shiv.js"></script>
	<![endif]-->
	    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
  <div class="container-fluid text-center debug">
    <tiles:insertAttribute name="head" />
    <%-- <tiles:insertAttribute name="left_crumbs" /> --%>
    <tiles:insertAttribute name="body" />
  </div>

  
  </body>
</html>