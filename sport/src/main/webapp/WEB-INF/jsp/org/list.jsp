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
	<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
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
			<div class="title"><img src="<%=basePath %>/static/img/m-dw.png" />注册单位管理</div>
			<div class="searchBtn">查询</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
		</div>
		<div class="searchBox">
			<dt>单位名称</dt>
			<dd><input type="text" id="fullName"/></dd>
			<dt>法人代表</dt>
			<dd><input type="text" id="legalLeader"/></dd>
			<button class="search-btn sprot-search-org">查询</button>
		</div>
		<div class="listBox">
			<div class="opBtnBox">
					<div class="fl-l">
						<a href="javascript:;;" class="sport-org-create-btn"><button class="btn-red">+ 创建</button></a>
					</div>
					<div class="fl-r">
						<button class="btn-wisteria sport-org-delete">删除</button>
					</div>
			</div>
			<table id="orgGridDiv" class="sport-grid"></table>
			<div id="jqGridPager"></div>
		</div>
		<script type="text/javascript">
			$(function() {
				$("#orgGridDiv").jqGrid({
					datatype: "json",
					url: "<%=basePath%>/org/kjsadmin/datas.action",
					colModel: [{
						name: 'orgId',
						width: 5,
						align: "center",
						hidden:true
					}, {
						name: 'status',
						width: 20,
						align: "center",
						hidden:true
					}, {
						label:"单位名称",
						name: 'fullName',
						width: 20,
						align: "center"
					},{
						label:"法人代表",
						name: 'legalLeader',
						width: 10,
						align: "center"
					}, {
						label:"所在地区",
						name: 'region',
						width: 20,
						align: "center"
					}, {
						label:"单位电话",
						name: 'telphone',
						width: 15,
						align: "center"
					}, {
						name: '操作',
						width: 20,
						align: "center",
						formatter:function(value, grid, rows, state){
							var editUser =  "<a href='javascript:;;' class='sport-org-edit' data-id='"+rows.orgId+"'>编辑</a>";
							var queryUser =  "<a href='javascript:;;' class='sport-org-query' data-id='"+rows.orgId+"'>查看</a>";
							var queryOrg = "<a href='javascript:;;' class='sport-org-manager-query' data-id='"+rows.orgId+"'>查看用户</a>";
							return editUser+"&nbsp;&nbsp;"+queryUser+"&nbsp;&nbsp;"+queryOrg;
						}
					}],
					autowidth: true,
					viewrecords: true,
					rowNum: 20,
					multiselect: true,
					pager: "#jqGridPager"
				});
				doResize();
			});
		</script>
	</body>
</html>