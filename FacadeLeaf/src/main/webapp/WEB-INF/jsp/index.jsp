<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/common/slidePic.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/index/index.css" />

<script>
$(document).ready(function(){

	var slidePicH = $("#slidePic_box").height();
	var slidePicW = $("#slidePic_box").width();

	//初始化图片宽度和高度
	$("#slide_pic li").width(slidePicW);
	$("#slide_pic li").height(slidePicH);
	
	//滑动文字需要偏移的高度
	var slide_text_top = slidePicH-$($(".slide_text")[0]).height();
	$(".slide_text").css({"top": slide_text_top});
	$(".slide_text").width(slidePicW);
	//2像素为边框距离
    var slideWidth = slidePicW;
	//autoSlidePic(slideWidth,1000);
    autoSlidePic(slideWidth,3000);
    //setInterval("autoSlidePic(" + slideWidth + "," + speed + ")",1000);
	
	$("#test").click(function(){
		var curLeft = $("#slide_pic").css("left").replace("px","");
	    $("#slide_pic").animate({"left":curLeft-slidePicW - 2});
	});
});


/**
 * @param slideWidth 滑动宽度
 */
function autoSlidePic(slideWidth,time) {
	
	//获得图片总数
	var liArr = $("#slide_pic").children("li");
	var picCount = liArr.length;
	
	var doAutoSlide = function() {
		//当前ul已左移距离
	    var curLeft = $("#slide_pic").css("left").replace("px","");
		
	    if(Math.abs(curLeft) > (picCount - 2) * slideWidth) {
	        //如果当前已滑动距离>去掉头尾两张图片后其余图片宽度总数，那么认为滑动到底了
	        $("#slide_pic").animate({"left":0});
	        
	    } else {
	        $("#slide_pic").animate({"left":curLeft-slideWidth});
	    }
	};
	
	doAutoSlide();
	setTimeout("autoSlidePic("+ slideWidth + "," + time + ")",time);
}




</script>
<div class="container-fluid text-center" style="width:96%;">
    <div id="hot_box_head" class="row">
        <div class="row hot_row row_container">
            <div class="col-lg-3 col-md-6">
                <div class="hot_box">
　　                                 <div style="color:red;"><b>今日新闻</b></div>
                    <ul class="hot_news_ul">
                        <li><span>韩《神佑》全新圣域阵营地图原画大曝</span></li>
                        <li><span>N道防线齐开火 《风暴英雄》防御建筑</span></li>
                        <li><span>盘点《灵魂回响》玩家最爱 新职业受宠</span></li>
                        <li><span>N道防线齐开火 《风暴英雄》防御建筑</span></li>
                        <li><span>盘点《灵魂回响》玩家最爱 新职业受宠</span></li>
                    </ul>   
               </div>
            </div>
    
            <div class="col-lg-5 col-md-6">
               <div class="hot_box">
　　                                  <div style="color:red;"><b>今日热文</b></div>
                    <ul class="hot_news_ul">
                        <li><span>韩《神佑》全新圣域阵营地图原画大曝</span></li>
                        <li><span>N道防线齐开火 《风暴英雄》防御建筑</span></li>
                        <li><span>盘点《灵魂回响》玩家最爱 新职业受宠</span></li>
                        <li><span>N道防线齐开火 《风暴英雄》防御建筑</span></li>
                        <li><span>盘点《灵魂回响》玩家最爱 新职业受宠</span></li>
                    </ul>   
               </div>
            </div>
        
	        <%--幻灯片 --%>
	        <div class="col-lg-4 col-md-6">
	 
				<div id="slidePic_box" class="hot_box">
				    <ul id="slide_pic" class="horizontal_ul slide_pic_ul">
	                    <li>
	                        <div class="slide_text"><span>文字说明</span></div>
		                    <img src="<%=basePath%>/static/images/slide/1.jpg" width="auto" height="auto"/>
	                    </li>
	                    <li>
	                        <div class="slide_text"><span>文字说明</span></div>
	                        <img src="<%=basePath%>/static/images/slide/2.jpg" width="auto" height="auto"/>
	                    </li>
	                </ul>
				</div>
		   </div>

        </div>
    </div><%-- <div id="hot_box_head" class="row">--%>  
    
    
    

<!--     <div class="row">
        <div class="col-lg-12 col-md-12">喜欢的板块(自由编辑收藏区)<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/></div>
        <div class="col-lg-12 col-md-12">最近浏览板块<br/><br/><br/><br/><br/></div>
    </div> -->
	<div id="content" class="row">
	    <div class="row row_container">
	        <div class="col-lg-12 col-md-12">
	            <div class="row">
	                <div style="height:100px;width:100%;">版区相关广告？</div>
	            </div>
	            <div class="row">
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">热门版区<br/><br/><br/><br/><br/><br/></div>
	            </div>
	        </div>
	    </div>
	
	
	
	<!--     <div class="row">
	        <div class="col-lg-12 col-md-12">
	            <div class="row">版区相关广告？<br/><br/><br/><br/></div>
	            <div class="row">
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	            </div>
	            <div class="row">
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	            </div>
	            <div class="row">
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	                <div class="col-lg-4 col-md-6">随便看看<br/><br/><br/><br/><br/><br/></div>
	            </div>
	        </div>
	    </div> -->
	</div>  <%-- <div id="content" class="row">--%>  
</div> <%--<div class="container-fluid text-center" style="width:96%;"> --%>

