<%@page contentType="text/html; charset=UTF-8" %>
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
</head>
<div class="titleBox">
	<div class="title"><img src="<%=basePath%>/static/img/m-zd.png" />数据字典</div>
	<div class="searchBtn">查询</div>
</div>
<div class="searchBox" style="display: none">
	<dt>名称</dt>
	<dd><input type="text" /></dd>
	<dt>编码</dt>
	<dd><input type="text" /></dd>
	<button class="search-btn">查询</button>
</div>
<div class="twoColumn">
	<div class="t-left">
		<div class="leftBox">
			<p class="lm">数据字典<a></a></p><p>
				<ul id="treeDemo" class="ztree"></ul>
			</p>
		</div>
	</div>
	<div class="t-right">
		<div class="listBox">
			<div class="opBtnBox">
				<div class="fl-l">
					<a href="10sjzdlb-xz.html"><button class="btn-red">+ 创建</button></a>
				</div>
				<div class="fl-r">
					<button class="btn-wisteria">删除</button>
				</div>
			</div>
			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>
	</div>
</div>