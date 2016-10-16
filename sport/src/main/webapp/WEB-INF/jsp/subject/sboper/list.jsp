<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>课题管理</title>
	<!--lhgdialog-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
	
    <!--jqgrid-->
    <script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
    <script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
    
    <!--select-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<style type="text/css">
		body{
			background: #F2F2F2;
		}
	</style>
</head>

<body>
	<div class="titleBox">
		<div class="title"><img src="<%=basePath %>/static/img/kt.png" />课题列表</div>
		<div class="searchBtn">查询</div>
	</div>
	<div class="searchBox">
			<dt>年份</dt>
			<dd>
				<select id="yearselect" class="selectpicker" name="year" data-live-search="false" title="请选择">
					<c:forEach items="${years}" var="year">
						<option value="${year}">${year}</option>
					</c:forEach>
				</select>
			</dd>
			<dt>课题类型</dt>
			<dd>
				<select id="typeselect" class="selectpicker" name="type" data-live-search="false" title="请选择">
					<option value="ALL">全部</option>
					<option value="ZBKT">招标课题</option>
					<option value="KYGGKT">科技攻关课题</option>
				</select>
			</dd>
			<dt>状态</dt>
			<dd>
				<select id="stageselect" class="selectpicker" name="stage" data-live-search="false" title="请选择">
					<option>请选择</option>
					<option>已审批</option>
					<option>待审批</option>
				</select>
			</dd>
			<button class="search-btn">查询</button>
	</div>
	<div class="listBox">
		<div class="opBtnBox">
			<div class="fl-l">
				<a href="04ktlb-xz.html"><button class="btn-red">+ 申报课题</button></a>
				<!--<button class="btn-red" id="xzzj">+ 选择专家</button>-->
				<!--<button class="btn-red">阶段性报告设置</button>-->
			</div>
			<div class="fl-r">
				<!--<button class="btn-wisteria">删除</button>-->
			</div>
		</div>
		<table id="jqGrid"></table>
		<div id="jqGridPager"></div>
	</div>

	<script type="text/javascript"> 
		$(document).ready(function () {
			$("#jqGrid").jqGrid({
				url: '<%=basePath%>/subject/sboper/datas.action',
				mtype: "GET",
				datatype: "json",
				colModel: [
					{label:'课题标号', name:'num', width:10, sortable: false, align:"center"},
					{label:'课题名称', name:'name', width:20, sortable: false},
					{label:'课题类型', name:'type', width:10, sortable: false},
					{label:'密级', name:'security', width:10, sortable: false, align:"center"},
					{label:'申报单位', name:'creatUnitId', width:20, sortable: false},
					{label:'负责人', name:'creator', width:10, sortable: false, align:"center"},
					{label:'阶段状态', name:'stage', width:10, sortable: false, align:"center"},
					{label:'操作', name:'操作', width:10, sortable:false, align:"center"}
				],
				autowidth:true,
				viewrecords: true,
				height: 200,
				rowNum: 20,
				multiselect: true,
				pager: "#jqGridPager"
			});
			doResize(); 
			
			$(function(){   
				//选择专家弹出框
				$('#xzzj').dialog({ 
					id:'xzzj',
					title:'选择专家',
					content: '<>',
					ok: true,
					cancel: true /*为true等价于function(){}*/
				});
			}); 
			
		});
	</script>
</body>

</html>