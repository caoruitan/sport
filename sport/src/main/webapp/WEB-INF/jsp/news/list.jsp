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
			<div class="title"><img src="<%=basePath %>/static/img/m-news.png" />新闻列表</div>
			<div class="searchBtn">查询</div>
		</div>
		<div class="searchBox" style="display: none">
			<dt>标题</dt>
			<dd>
				<input type="text" id="search-title"/>
			</dd>
			<dt>状态</dt>
			<dd>
				<select id="search-status" class="selectpicker" title="请选择">
					<c:forEach items="${status}" var="s">
						<option value="${s.key }">${s.value}</option>
					</c:forEach>
				</select>
			</dd>
			<button class="search-btn sport-news-search-btn">查询</button>
		</div>
		<div class="twoColumn">
			<div class="t-left">
				<div class="leftBox-news sport-lm">
					<p class="lm">栏目<a></a></p>
					<c:forEach items="${columns}" var="column">
						<li data-id="${column.key }">${column.value}</li>
					</c:forEach>
				</div>
			</div>
			<div class="t-right">
				<div class="listBox">
					<div class="opBtnBox">
						<div class="fl-l">
							<a href="javascript:;;"><button class="btn-red sport-news-skip">+ 创建</button></a>
							<a href="javascript:;;" id="fb"><button class="btn-red sport-news-publish">发布</button></a>
							<a href="javascript:;;" ><button class="btn-red sport-news-unpublish">取消发布</button></a>
						</div>
						<div class="fl-r">
							<button class="btn-wisteria sport-news-delete">删除</button>
						</div>
					</div>
					<table id="newsGridDiv" class="sport-grid"></table>
					<div id="jqGridPager"></div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$(function() {
				$("#newsGridDiv").jqGrid({
					datatype: "local",
					url: "<%=basePath%>/news/kjsadmin/datas.action",
					colModel: [{
						name: 'id',
						width: 5,
						align: "center",
						hidden:true
					}, {
						name: 'status',
						width: 50,
						align: "center",
						hidden:true
					},{
						label:"标题",
						name: 'title',
						width: 50,
						align: "center",
						formatter:function(value, grid, rows, state){
							return "<a href='"+Sport.getBasePath()+"/news/kjsadmin/detail/"+rows.id+".htm'>"+value+"</a>";
						}
					}, {
						label:"所属栏目",
						name: 'columnName',
						width: 10,
						align: "center"
					}, {
						label: '发布时间',
						name: 'publishTime',
						width: 15,
						align: "center",
						sorttype: "date"
					}, {
						label: '状态',
						name: 'statusName',
						width: 10,
						align: "center",
						sorttype: "float"
					}, {
						name: '操作',
						index: '操作',
						width: 10,
						align: "center",
						sortable: false,
						formatter:function(value, grid, rows, state){
							if(rows.status!=1){
								return "<a href='javascript:;;' class='sport-news-edit' data-id='"+rows.id+"'>编辑</a>";
							}
							return "";
						}
					}],
					autowidth: true,
					viewrecords: true,
					rowNum: 20,
					multiselect: true,
					pager: "#jqGridPager"
				});
				$(".sport-lm").find("li").first().trigger("click");
				doResize(20); 
			});
		</script>
	</body>
</html>