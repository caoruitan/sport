<%@page import="org.cd.sport.vo.UserVo"%>
<%@page import="org.cd.sport.utils.AuthenticationUtils"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
	UserVo user = AuthenticationUtils.getUser();
	request.setAttribute("loginUser", user);
	request.setAttribute("user_type", "orgadmin");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
	<script type="text/ecmascript" charset="utf-8" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/layer/layer.js"></script>
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
						<div class="user-name">${loginUser.userName}</div>
						<div class="user-org">${loginUser.orgName}</div>
					</div>
					<div class="menu">
						<ul>
							<li class="sport-subject-menu" data-type="orgadmin"><img src="<%=basePath %>/static/img/menu/kt.png" /> 课题管理</li>
							<!--
							<li class="sport-user-menu" data-type="orgadmin"><img src="<%=basePath %>/static/img/menu/ry.png" /> 用户管理</li>
							<li class="sport-password-reset-menu" data-type="orgadmin"><img src="<%=basePath %>/static/img/menu/mm.png" /> 用户密码重置</li>
							-->
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
