<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


    <div ng-app="headApp">
	    <div class="row toolbar" ng-fixed-toolbar>
	        <div class="col-lg-1 col-md-2 nopadding">
	           <span class="bar_icon border_l_r icons_option">
	                   <span class="bar_span"><b>选项设置</b></span> 
	           </span>
	        </div>
	        <div class="col-lg-2 col-md-2"><!-- 工具条空白区 --></div>
	        <div class="col-lg-6 col-md-6">
	                <span>搜一下：</span>
	            <span class="search_span border_l_r">
	                <input type="text" class="search_input"/>
	            </span>
	        </div>
	        <div class="col-lg-3 col-md-2"><!-- 工具条空白区 --></div>
	    </div>


        <div class="row head"><!-- head pic --></div>
        
        <div>
            <div class="row navbar" ng-fixed-nav>
	            <div class="col-lg-1"><!-- 导航条空白区 --></div>
	            <div class="col-lg-10 text-left">
	               <ul class="nav navbar-nav">
	                   <li><span class="bar_span">导航一</span></li>
	                   <li><span class="bar_span">导航二</span></li>
	                   <li><span class="bar_span">导航三</span></li>
	                   <li><span class="bar_span">导航四</span></li>
	               </ul>
	            </div>
	            <div class="col-lg-1"><!-- 导航条空白区 --></div>
            </div>
	    </div>
	    
    </div>

    
    
<script>

var headApp = angular.module('headApp', []);
headApp.directive('ngFixedNav', function() {
    return {
        restrict: 'A',
        link:function(scope,element){
        	angular.element(window).bind("scroll", function() {
        		//40为toolbar高度,考虑上滚遮盖问题
        		var elemOffsetY = element[0].parentElement.offsetTop - window.pageYOffset;

        		if (elemOffsetY < 0) {
        			element.addClass("bar_fixed");
        			//向上时会显示搜索框，所以要设置预留位置给搜索框
        			if(_isScrollup) {
        				element.addClass("toolbar_space");
        			} else {
        				element.removeClass("toolbar_space");
        			}
				} else {
					if(_isScrollup && elemOffsetY < 40) {
						//40为toolbar高度
						//解决上滚快到原位时有可能被toolbar遮盖
						element.addClass("bar_fixed");
						element.addClass("toolbar_space");
					} else {
						element.removeClass("bar_fixed");
					}
				}
        	});//end of angular.element(window).bind("scroll", function() {
        }//end of link:function(scope,element){
    };
});


//临时保存windows上一次滚轮状态，用于判断用户滚轮向上还是向下
var cacheWindowPageYOffset = 0;
//表示用户滚轮状态,true向上滚动,false向下滚动
var _isScrollup = false;

/**
 * 记录滚轮动作向上或向下
 */
angular.element(window).bind('scroll', function() {
	//滚动方向,大于0向上，小于0向下
    var direction = cacheWindowPageYOffset - window.pageYOffset;
    _isScrollup = direction > 0 ? true : false;
});


headApp.directive('ngFixedToolbar', function() {
    return {
        restrict: 'A',
        link:function(scope,element){
            angular.element(window).bind("scroll", function() {
                var elemOffsetY = element[0].parentElement.offsetTop - window.pageYOffset;

                if (elemOffsetY < 0 && _isScrollup) {
                    element.addClass("bar_fixed");
                } else {
                    element.removeClass("bar_fixed");
                }
                cacheWindowPageYOffset = window.pageYOffset;
            });//end of angular.element(window).bind("scroll", function() {
        }//end of link:function(scope,element){
    };
});        	
        	

</script>