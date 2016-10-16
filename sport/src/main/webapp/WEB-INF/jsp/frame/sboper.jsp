<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html>
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
						<div class="user-name">龙五</div>
						<div class="user-org">国家体育总局</div>
					</div>
					<div class="menu">
						<ul>
							<li onclick="clickmenu('<%=basePath %>/subject/sboper/list.htm')"><img src="<%=basePath %>/static/img/menu/kt.png" /> 课题管理</li>
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
	
	<script type="text/javascript">
		
	</script>
</body>
</html>
