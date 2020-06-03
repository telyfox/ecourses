<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易课寄后台管理系统</title>
<!-- 美化区 -->
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/insdep/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/insdep/easyui_plus.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/insdep/easyui_animation.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/insdep/icon.css" />
<link rel="stylesheet" type="text/css" href="css/e3.css" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<!-- 非美化区 -->
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
    <!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#1DA02B"> 
		<span class="northTitle">易课寄在线购课系统后台管理系统</span>
	    <span class="loginInfo">登录用户：admin&nbsp;&nbsp;角色：系统管理员</span>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'user-list'}">普通用户管理</li>
	         		<li data-options="attributes:{'url':'admin-list'}">管理员用户管理</li>
	         	</ul>
         	</li>
         	<li>
         		<span>课程管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增课程</li>
	         		<li data-options="attributes:{'url':'item-list'}">管理课程</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         	         	<li>
         		<span>订单内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'order-user-list'}">订单用户信息管理</li>
	         		<li data-options="attributes:{'url':'order-list'}">订单信息管理</li>
	         	</ul>
         	</li>
         	<li>
         		<span>SOA服务框架管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'dubbo'}">Dubbo</li>
	         	</ul>
         	</li>
         	<li>
         		<span>SolrCloud管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'index-item'}">SolrCloud索引库维护</li>
	         		<li data-options="attributes:{'url':'solr01'}">SolrCloud 1号集群</li>
	         		<li data-options="attributes:{'url':'solr02'}">SolrCloud 2号集群</li>
	         		<li data-options="attributes:{'url':'solr03'}">SolrCloud 3号集群</li>
	         		<li data-options="attributes:{'url':'solr04'}">SolrCloud 4号集群</li>
	         	</ul>
         	</li>
         	<li>
         		<span>消息中间件管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'activemq'}">ActiveMQ</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="欢迎" style="padding:5px;">
		    
		      		<div class="alert alert-success">
  			  		<div class="alert-icons"></div>
  			  		<b>欢迎使用易课寄在线购课系统</b> <p>点击左侧菜单栏的相关选项, 即可对本系统进行相应的管理.</p>
				</div>
		    </div>
		</div>
    </div>
    <!-- 页脚信息 -->
	<div data-options="region:'south',border:false" style="height:20px; background:#1DA02B; padding:2px; vertical-align:middle;">
		<span id="sysVersion">系统版本：V0.1</span>
	    <span id="nowTime"></span>
	</div>
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>