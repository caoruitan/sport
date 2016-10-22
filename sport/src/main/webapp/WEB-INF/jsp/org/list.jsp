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
		</div>
		<div class="searchBox">
			<dt>单位名称</dt>
			<dd><input type="text" id="fullName"/></dd>
			<dt>法人代表</dt>
			<dd><input type="text" id="legalLeader"/></dd>
			<dt>状态</dt>
			<dd>
				<select id="orgStatus" class="selectpicker" title="请选择">
					<c:forEach items="${status}" var="s">
						<option value="${s.key }">${s.value}</option>
					</c:forEach>
				</select>
			</dd>
			<button class="search-btn sprot-search-org">查询</button>
		</div>
		<div class="listBox">
			<table id="orgGridDiv"></table>
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
						label:"状态",
						name: 'statusName',
						width: 10,
						align: "center"
					}, {
						name: '操作',
						width: 20,
						align: "center",
						formatter:function(value, grid, rows, state){
							var verfiy =  "<a href='javascript:;;' class='sport-org-verify' data-id='"+rows.orgId+"'>审核</a>";
							var queryUser =  "<a href='javascript:;;' class='sport-org-query' data-id='"+rows.orgId+"'>查看</a>";
							var queryOrg = "<a href='javascript:;;' class='sport-org-manager-query' data-id='"+rows.orgId+"'>查看用户</a>";
							if(rows.status == 0){
								return verfiy+"&nbsp;&nbsp;"+queryOrg;
							}
							if(rows.status == 1){
								return queryUser+"&nbsp;&nbsp;"+queryOrg;
							}
							return queryOrg;
						}
					}],
					autowidth: true,
					viewrecords: true,
					height: 200,
					rowNum: 20,
					multiselect: true,
					pager: "#jqGridPager"
				});
				doResize();
			});
		</script>
	</body>
</html>