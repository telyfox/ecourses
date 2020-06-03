<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-transform">
<meta http-equiv="Cache-Control" content="no-siteapp">
    <title>易课寄在线购课系统-业内专业的IT职业购课平台ecourses.cn</title>
    <meta name="Keywords" content="易课寄,IT职业教育,IT在线教育平台,IT在线教育,IT在线学习,it职业培训,android,ios,flash,java,python,html5,swift,cocos2dx">
    <meta name="Description" content="易课寄在线购课系统作为业内专业IT职业在线购课平台,拥有海量IT职业课程,涵盖30+个技术领域,如Android,iOS ,Flash,Java,Python,HTML5,Swift,Cocos2dx等视频教程.帮助IT学习者从零基础起步,结合IT实战案例演练,系统学习,助你快速成为IT优秀技术人才！">
    
    <meta property="wb:webmaster" content="3a008ad947166307">
    <link rel="stylesheet" type="text/css" href="/css/base_w1200.css?v=2016071333">
    <link rel="stylesheet" type="text/css" href="/css/index.css?v=2016071312">
	<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/global_index.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2018 ECourses Corporation. All rights reserved. */
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}
</style></head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<jsp:include page="commons/mainmenu.jsp" />
<!-- header end -->
<!----row1------->
<div class="slide_show" id="slide_show">
	<div class="indexW">
      <div id="index_slide" class="slide_wrap">
		<ol>
		    <!-- 广告轮播图  -->
		    <!-- bigAdList -->
			<c:forEach items="${bigAdList}" var="node" varStatus="status">
			<li>
			  <a name="sfbest_hp_hp_focus_${status.index }" class="fore_pic trackref" href="${node.url }" target="_blank"> 
				<img id="lunbo_1" alt="${node.title }"	src="${node.pic }">
			  </a>
			</li>
			</c:forEach>
		</ol>
	</div>
	 <!-- 小广告  -->      
     <div class="rSide">
     	<!-- smallAdList -->
	      <c:forEach items="${smallAdList}" var="node" varStatus="status">
			  <a name="sfbest_hp_hp_focus_right-ad${status.index }" class="a-img r-img1 trackref" href="${node.url }" target="_blank">
		          <img alt="${node.subTitle }" src="${node.pic }">
		          <div class="rmask"></div>
	          </a>
		  </c:forEach>
	 </div>
 	</div>
 	<!-- 图片下指示数量的点 -->
      <ul class="none" id="lunboNum">
      			<c:forEach items="${bigAdList }" varStatus="status">
      				<li class="<c:if test="${status.index==0 }">cur</c:if>">${status.index+1 }</li>
      			</c:forEach>
		      </ul>
		      
      <div class="indexbg" id="indexbg">
      <dl style="left: -1903px;">
	        <dd style="width: 1903px; background: rgb(20, 16, 13);"></dd>
	        <dd style="width: 1903px; background: rgb(119, 96, 86);"></dd>
	        <dd style="width: 1903px; background: rgb(17, 12, 55);"></dd>
	        <dd style="width: 1903px; background: rgb(239, 244, 248);"></dd>
	        <dd style="width: 1903px; background: rgb(231, 32, 37);"></dd>
	        <dd style="width: 1903px; background: rgb(128, 29, 92);"></dd>
	        <dd style="width: 1903px; background: rgb(117, 144, 1);"></dd>
	        <dd style="width: 1903px; background: rgb(253, 213, 29);"></dd>
	        </dl>
      </div>
    </div>
<!----row1 end------->
<!--start -->
<div class="indexW mt1">
	<div class="bestbuy">
		<div class="b_left">
			<h2>优选课程<span></span></h2>
			
			<ul class="bbig" id="bigPerfect">
				<!-- bestUpList -->
				<c:forEach items="${bestUpList}" var="node" varStatus="status">
					<li class="price_list0" ><a href="${node.url }" title="${node.title }" target="_blank"><img class="lazy" src="${node.pic }" style="display: inline;"></a>
						<div class="bprice" >
							<span><sup>￥</sup></span>${node.subTitle }
						</div>
					</li>
			  	</c:forEach>
			</ul>
				
			<ul class="bsmall" id="smallPerfect">
				<!-- bestDownList -->
				<c:forEach items="${bestDownList}" var="node" varStatus="status">
					<li class="price_list0" ><a href="${node.url }" title="${node.title }" target="_blank"><img class="lazy" src="${node.pic }" style="display: inline;"></a>
						<div class="bprice" >
							<span><sup>￥</sup></span>${node.subTitle }
						</div>
					</li>
			  	</c:forEach>
			</ul>
		</div>   
	<!-- end --> 
	<div class="rSide1">
			
    	<div class="rImg2">
      	<a name="sfbest_hp_hp_news_right-ad" class="trackref" href="#" target="_blank"><img salt="9.2-9.5" src="/images/goods/751d2091c008c2a49c1934545730f041.jpg"></a>
   		</div>
		 
        <div class="sfNews">
          <div class="rTitle"><h2>最新动态</h2><a href="#" target="_blank" class="more">更多&gt;</a></div>
          <ul>
          <!-- newsList -->
	      <c:forEach items="${newsList}" var="node" varStatus="status">
				<li><a name="sfbest_hp_hp_news_1" href="${node.url }" target="_blank" class="<c:choose><c:when test='${node.subTitle==1 }'>red trackref</c:when><c:otherwise>trackref</c:otherwise></c:choose>" title="${node.title }">${node.title }</a></li>
		  </c:forEach>

		  </ul>
        </div>
    </div>
	</div>
	<div class="clr"></div>
</div>
<!--楼层 start-->
<div class="indexW mt2 full_ad" style="width:1190px;padding-left:10px;"><a name="sfbest_hp_hp_banner_1" class="trackref" href="#" target="_blank"><img alt="8.30-9.5" class="lazy" 
 src="/images/goods/8f42d6d2deead3da7d50c8a702a3c939.jpg" style="display: inline;"></a>
	 </div>
<div class="indexW mt2 ie6 fresh">
    <div class="category">
		<ul class="mTitle">
			<li>
			<div class="cir"></div>
			<h2>
				<em></em>
				<a name="sfbest_hp_hp_floor1_floor-category1" class="trackref" href="#" target="_blank">限时优惠课程</a>
			</h2>
			</li>
		</ul>
		<!-- cheapLeftList -->
		<c:forEach items="${cheapLeftList}" var="node" varStatus="status">
			<div class="lCont"><a name="sfbest_hp_hp_floor1_left-ad" class="trackref" href="${node.url }" target="_blank">
				<img alt="${node.title }" class="lazy" src="${node.pic }" style="display: inline;"></a>
			</div>
	  	</c:forEach>
	  	
	  </div>
  <div class="sfRight">
      <div class="subCont">
				<ul class="pList" id="floor-gap-1">
				<!-- cheapList -->
				<c:forEach items="${cheapList}" var="node" varStatus="status">
					<li class="price_list1" >
						<div class="pImg">
							<a href="${node.url }" target="_blank" title="${node.title }"><img class="lazy" alt="${node.title }" src="${node.pic }" style="display: block;"></a>
						</div>
						<div class="title-a">
							<a href="${node.url }" target="_blank" title="${node.title }">${node.title }</a>
						</div>
						<div class="price" id="priceL_l_218031_7_297">
							<b>￥${node.subTitle }</b>
						</div>
					</li>
			  	</c:forEach>
			  	
				</ul>
			</div>
      <div class="redge">
        <ul class="rHot">
	      	<c:forEach items="${miniTitleList}" var="node" varStatus="status">
				<li><a name="sfbest_hp_hp_floor1_Keywords${status.index+1 }" class="trackref" href="${node.url }" target="_blank">${node.title }</a></li>
		  	</c:forEach>
        </ul>
        <div class="clr"></div>
        <div class="rimg">
		     
          <a name="sfbest_hp_hp_floor1_right-ad" class="ht1 trackref" href="#" target="_blank"><img alt="8.30-9.5" class="lazy" data="http://www.ecourses.cn/images/goods/160x160-3.jpg" src="http://www.ecourses.cn/images/goods/160x160-3.jpg" style="display: inline;"></a>
          <div class="rbutton"><a href="#" target="_blank"></a></div>
		          </div>
      </div>
  </div>
  <!---- -->
    <span class="clr"></span>
</div>
<!--楼层 end -->

<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
</body>
</html>