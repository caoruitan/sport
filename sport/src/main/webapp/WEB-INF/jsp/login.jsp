<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="your,keywords">
	<meta name="description" content="Short explanation about this website">
	<link href="<%=basePath %>/static/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/animate.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<title>国家体育总局科研项目申报系统-登录</title>
</head>
<body>
	<div class="login_H ">
		<c:if test="${news!=null }">
			<span>公告：<a href="<%=basePath %>/news/detail/${news.id}.htm">${news.title}</a>
			<img src="<%=basePath %>/static/img/login/ky-new.png"></span>
		</c:if>
	</div>
	<div class="login_Body ">
		<div class="login_M">
			<div class="ad ">
				<li class="ky-ico1 animated fadeInLeft"></li>
				<li class="ky-ico2 animated fadeInLeft"></li>
				<li class="ky-ico3 animated fadeInLeft"></li>
				<li class="ky-ico4 animated fadeInRight"></li>
				<li class="ky-ico5 animated fadeInRight"></li>
				<li class="ky-ico6"></li>
				<li class="ky-ico7"></li>
				<li class="ky-ico8 animated fadeInRight"></li>
				<span class="ky-line1 animated fadeInLeft"></span>
				<span class="ky-line2 animated fadeInLeft"></span>
				<span class="ky-line3 animated fadeInLeft"></span>
				<span class="ky-line4 animated fadeInRight"></span>
				<span class="ky-line5 animated fadeInRight"></span>
				<p class="ky-text1 animated fadeInLeft"></p>
				<p class="ky-text2 animated fadeInLeft"></p>
				<p class="ky-text3 animated fadeInLeft"></p>
				<p class="ky-text4 animated fadeInRight"></p>
				<p class="ky-text5 animated fadeInRight"></p>
			</div>
			<div class="loginBox wow bounceInDown">
				<div class="login">
					<header>欢迎登录</header>
					<form id="loginForm">
						<input type="hidden" name="pubKey" value="${pubKey}" id="pubKey"/>
						<input type="hidden" name="uuid" value="${uuid}" id="uuid"/>
						<input type="hidden" name="return_url" value="${return_url}" id="return_url"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<div class="login_list">
							<input style="display:none">
							<input type="text" class="input_login yh"  autocomplete="off" name="loginName" id="loginName" placeholder="请输入用户名" value="" maxlength="30" onkeyup="(function(){if(event.keyCode==13){$('#password').focus();}})()" />
						</div>
						<div class="login_list">
							<input style="display:none">
							<input type="password" class="input_login mm"  autocomplete="off" id="password" maxlength="30" placeholder="请输入密码" name="password" />
						</div>
						<span id="errorMsg" class="login-error"></span>
						<div class="login_list">
							<a class="ky_right" href="javascript:void(0)" id="wjmm">注册</a>
						</div>
						<div class="login_list">
							<input type="button" value="登录" class="btn_login" id="loginBtn" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="login_F"><p>版权所有：国家体育总局</p></div>
	</div>
<script src="<%=basePath %>/static/js/wow.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/static/js/jsencrypt.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/static/js/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/static/js/sport.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
		new WOW().init();
	};
	$("#wjmm").on("click",function(){
		window.location.href = Sport.getBasePath()+"/regist/regist.htm";
	})
</script>
</body>
</html>