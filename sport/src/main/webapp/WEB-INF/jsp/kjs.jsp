<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath %>/static/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/sport.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath %>/static/css/bootstrap.min.css">
	<script src="<%=basePath %>/static/js/jquery.min1.10.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/jquery.validate.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/common.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/sport.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/layer/layer.js" type="text/javascript" charset="utf-8"></script>
</head>
<decorator:head/>
<title><decorator:title/></title>
</head>
	<body>
		<div class="layout_top">
			<jsp:include page="head.jsp"/>
		</div>
		<div class="layout-container">
			<div class="layout_main">
				<div class="mainMenu">
					<div class="mainMenuBox">
						<div class="user">
							<div class="user-head"></div>
							<div class="user-name">龙五</div>
							<div class="user-org">国家体育总局</div>
						</div>
						<div class="menu">
							<ul>
								<li onclick="menuselect(this,'04ktlb.html')"><img src="<%=basePath %>/static/img/menu/kt.png" /> 课题管理
								</li>
								<li onclick="menuselect(this,'07xwlb.html')"><img src="<%=basePath %>/static/img/menu/xw.png" /> 新闻管理
								</li>
								<li class="sport-dic-menu"><img src="<%=basePath %>/static/img/menu/zd.png" /> 数据字典管理</li>
								<li onclick="menuselect(this,'09zcdwlb.html')"><img src="<%=basePath %>/static/img/menu/sh.png" /> 注册单位审核
								</li>
								<li onclick="menuselect(this,'99temp.html')"><img src="<%=basePath %>/static/img/menu/dw.png" /> 组织单位管理
								</li>
								<li class="sport-user-menu"><img src="<%=basePath %>/static/img/menu/ry.png" /> 用户管理
								</li>
								<li class="sport-password-reset-menu"><img src="<%=basePath %>/static/img/menu/mm.png" /> 用户密码重置
								</li>
								<div class="clear"></div>
							</ul>
						</div>
					</div>
				</div>
				<div class="content sport-container">
						<decorator:body />
				</div>
			</div>
		</div>
	</body>
</html>
