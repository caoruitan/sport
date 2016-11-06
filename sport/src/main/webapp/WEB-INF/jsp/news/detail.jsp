<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
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
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<title>新闻详情</title>
</head>
	<body>
		<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/m-news.png" />新闻详情</div>
			<c:if test="${notLogin !=true}">
				<div class="returnBtn news-returnBtn">返回列表</div>
			</c:if>
		</div>
		<div class="editBox">
			<h3 style="text-align:center;">${news.title}</h3>
			<div style="width:100%;margin-top:30px;padding:0 25px;">${news.content}</div>
			<div style="width:100%;padding:0 25px;">
				<c:forEach items="${news.files}" var="file" varStatus="status">
	            	<a href="javascript:;;" data-id="${file.path}" data-name="${file.name}" class="sport-download" style="text-align:left;width:100%;display:inline-block;line-height: 25px;">${file.name}</a>
				</c:forEach>
			</div>
		</div>
	</body>
</html>