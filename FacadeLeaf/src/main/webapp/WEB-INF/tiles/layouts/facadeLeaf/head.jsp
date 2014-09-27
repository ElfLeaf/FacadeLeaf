<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <div class="row head"><!-- head pic --></div>
    <%-- 下面这个div用于开启angularjs，并且确认元素坐标位置 --%>
    <div ng-app="headApp">
	    <div class="row searchbar" ng-fix-nav>
	        <div class="col-md-1"><span style="display:inline-block;margin-top:10px;">工具</span></div>
	        <div class="col-md-2"><!-- 工具条空白区 --></div>
	        <div class="col-md-6">
	            <span>搜一下：</span>
	            <span style="display:inline-block;width:70%;height:38px;border-left:1px solid #CCC;border-right:1px solid #CCC;">
	                <input type="text" style="width:100%;height:38px;border:none;"/>
	            </span>
	        </div>
	
	    </div>
	    <div class="row">
	        <div class="col-md-1">1<!-- 导航条空白区 --></div>
	        <div class="col-md-10">浮动导航</div>
	        <div class="col-md-1">1<!-- 导航条空白区 --></div>
	    </div>
    </div>

    
    
<script>

var headApp = angular.module('headApp', []);
headApp.directive('ngFixNav', function() {
    return {
        restrict: 'A',
        link:function(scope,element){
        	angular.element(window).bind("scroll", function() {
        		var elemOffsetY = element[0].parentElement.offsetTop - window.pageYOffset;
        		if (elemOffsetY < 0) {
        			element.addClass("bar_fixed");
				} else {
					element.removeClass("bar_fixed");
				}
        	});//end of angular.element(window).bind("scroll", function() {
        }//end of link:function(scope,element){
    };
});

</script>