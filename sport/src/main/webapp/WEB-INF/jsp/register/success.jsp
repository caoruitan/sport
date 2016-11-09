<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>国家体育总局科研项目申报系统-单位注册-成功页面</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
		<style type="text/css">
			body {
				background: #f2f2f2;
			}
		</style>
	</head>
	<body style="overflow-y:hidden;">
		<div class="layout_top">
			<div class="top_logo_register">单位注册</div>
			<div class="op">
				<a href="<%=basePath %>/login.htm">直接登录</a>
			</div>
		</div>
			<div class="editBox" style="height:100%;">
				<div class="ok regist-ok">
					<li><img src="<%=basePath %>/static/img/ok2.png" /></li>
					<li class="txt regist-txt">恭喜你，注册成功~~</li>
					<li class="font_red">正在提交审核，请耐心等待</li>
					<p class="save-btn">
						<a href=""><button class="btn-red btn-wisteria" type="button" id="download-regist">立即下载</button></a>
						<a href="<%=basePath %>/login.htm"><button class="btn-red btn-wisteria" type="">立即登录</button></a>
					</p>
			</div>
		</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
<script type="text/javascript">
	$("#download-regist").click(function(){
		window.open("<%=basePath%>/regist/download.action?orgId=${orgId}");
	});
</script>
	</body>
</html>