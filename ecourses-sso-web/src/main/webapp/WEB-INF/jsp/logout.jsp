<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/png.js"></script>
<script type="text/javascript" src="/js/cas.login.js"></script>
<script type="text/javascript" src="/js/capsLock.js"></script>
<title>易课寄——登出</title>
</head>
<body>
<a href="http://www.ecourses.cn">返回首页</a>
<script type="text/javascript">

var logout = {
		checkLogout : function(){
			var _ticket = $.cookie('token');
			//alert(_ticket);
			if(!_ticket){
				return ;
			}
			$.ajax({
				url : "http://sso.ecourses.cn/user/logout/" + _ticket,
				dataType : "jsonp",
				type : "GET",
				complete : function(data){
					//alert(data.status);
					if(data.status == 200){
						alert("注销成功");
						window.location.href = "http://www.ecourses.cn";
					}
				}
			});
		}
	}
function logoutfunction()
{
	logout.checkLogout();
}

$(window).load(function() {
	logoutfunction();
});
</script>
</body>
</html>