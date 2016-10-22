<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/ztree/jquery.ztree.core.min.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/plugin/location.js"></script>
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
	<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/m-dw.png" />单位:${org.fullName}<span></span></div>
			<div class="returnBtn orgdetail-returnBtn">返回列表</div>
		</div>
		<div class="viewBox">
			<table class="viewTable">
				<tr>
					<th>单位全称</th>
					<td>${org.fullName}</td>
					<th>英文名称</th>
					<td>${org.englishName}</td>
				</tr>
				<tr>
					<th>简称</th>
					<td>${org.shortName}</td>
					<th>法人代表</th>
					<td>${org.legalLeader}</td>
				</tr>
				<tr>
					<th>所在地区</th>
					<td>北京</td>
					<th>单位性质</th>
					<td>${org.quality}</td>
				</tr>
				<tr>
					<th>组织机构代码</th>
					<td>${org.code}</td>
					<th>单位电话</th>
					<td>${org.telphone}</td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td>${org.email}</td>
					<th>邮编</th>
					<td>${org.post}</td>
				</tr>
				<tr>
					<th>业务负责人姓名</th>
					<td>${org.managerName}</td>
					<th>业务负责人手机</th>
					<td>${org.managerPhone}</td>
				</tr>
				<tr>
					<th>业务负责人邮箱</th>
					<td>${org.managerEmail}</td>
				</tr>
			</table>
			<p class="save-btn">
				<button class="btn-red btn-size-big orgdetail-returnBtn" type="button">返回</button>
			</p>
		</div>
	</body>
</html>