<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="header">
  <div class="header_inner">
    <div class="logo">
                 
<a name="sfbest_hp_hp_head_logo" href="http://www.ecourses.cn" class="trackref logoleft">
		</a>
        <div class="logo-text"><img src="/images/logo_word.jpg">
</div>
    </div>
    <div class="index_promo"></div>
    <div class="search">
      <form action="/productlist/search" id="searchForm" name="query" method="GET">
        <input type="hidden" name="inputBox" value="1"><input type="hidden" name="categoryId" value="0">
        <input type="text" class="text keyword ac_input" name="keyword" id="keyword" value="" style="color: rgb(153, 153, 153);" onkeydown="javascript:if(event.keyCode==13) search_keys('searchForm');" autocomplete="off">
        <input type="button" value="" class="submit" onclick="search_keys('searchForm')">
      </form>
      
      <div class="search_hot"><a target="_blank" href="http://search.ecourses.cn/search.html?keyword=java">Java</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=php">PHP</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=c">C</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=python">Python</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=javascript">JavaScript</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=android">Android</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=ios">iOS</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=大数据">大数据</a>
      						  <a target="_blank" href="http://search.ecourses.cn/search.html?keyword=人工智能">人工智能</a>
      </div>
      
    </div>
    <div class="shopingcar" id="topCart">
      <s class="setCart"></s><a href="http://cart.e3mall.cn" class="t" rel="nofollow">我的购课车</a><b id="cartNum">0</b>
      <span class="outline"></span>
      <span class="blank"></span>
      <div id="cart_lists">
        <!--cartContent-->   
        <div class="clear"></div>
      </div>
    </div>
  </div>
</div>