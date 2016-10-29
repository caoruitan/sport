<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>课题管理</title>
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
						<c:if test="${year eq endYear}"><option value="${year}" selected="selected">${year}</option></c:if>
						<c:if test="${year ne endYear}"><option value="${year}">${year}</option></c:if>
					</c:forEach>
				</select>
			</dd>
			<dt>课题类型</dt>
			<dd>
				<select id="typeselect" class="selectpicker" name="type" data-live-search="false" title="请选择">
					<option value="ALL">全部</option>
					<c:forEach items="${types}" var="type">
						<option value="${type.key}">${type.value}</option>
					</c:forEach>
				</select>
			</dd>
			<dt>状态</dt>
			<dd>
				<select id="stageselect" class="selectpicker" name="stage" data-live-search="false" title="请选择">
					<option value="ALL">全部</option>
					<c:forEach items="${stages}" var="stage">
						<option value="${stage.key}">${stage.value}</option>
					</c:forEach>
				</select>
			</dd>
			<button class="search-btn">查询</button>
	</div>
	<div class="listBox">
		<div class="opBtnBox">
			<div class="fl-l">
				<!-- <a href="<%=basePath%>/subject/sboper/createSubject.htm"><button class="btn-red">+ 申报课题</button></a> -->
				<!-- <button class="btn-red" id="xzzj">+ 选择专家</button> -->
				<!-- <button class="btn-red">阶段性报告设置</button> -->
			</div>
			<div class="fl-r">
				<!--<button class="btn-wisteria">删除</button>-->
			</div>
		</div>
		<table id="jqGrid" class="sport-grid"></table>
		<div id="jqGridPager"></div>
	</div>

	<script type="text/javascript"> 
		$(document).ready(function () {
			$("#jqGrid").jqGrid({
				url: '<%=basePath%>/subject/kjsadmin/datas.action',
				mtype: "GET",
				datatype: "json",
				colModel: [
					{label:'课题标号', name:'num', width:10, sortable: false, align:"center"},
					{label:'课题名称', name:'name', width:20, sortable: false},
					{label:'课题类型', name:'type', width:10, sortable: false, align:"center", formatter:function(value, grid, rows, state) {
						if(value == "ZBKT") {
							return "招标课题";
						} else if(value == "KYGGKT") {
							return "科技攻关课题";
						} else {
							return "";
						}
					}},
					{label:'密级', name:'securityName', width:10, sortable: false, align:"center"},
					{label:'申报单位', name:'createUnitName', width:20, sortable: false},
					{label:'负责人', name:'creatorName', width:10, sortable: false, align:"center"},
					{label:'阶段状态', name:'stage', width:10, sortable: false, align:"center", formatter:function(value, grid, rows, state) {
						if(value == "SBSTB") {
							return "申报书填报";
						} else if(value == "RWSTB") {
							return "任务书填报";
						} else if(value == "JDBG") {
							return "阶段报告";
						} else if(value == "JTBG") {
							return "结题报告";
						} else {
							return "";
						}
					}},
					{label:'操作', name:'操作', width:10, sortable:false, align:"center", formatter:function(value, grid, rows, state){
						return "<a href='<%=basePath%>/subject/kjsadmin/detail.htm?subjectId=" + rows.id + "' class='sport-user-edit'>查看</a>";
					}}
				],
				autowidth: true,
				viewrecords: true,
				height: 200,
				rowNum: 20,
				multiselect: false,
				pager: "#jqGridPager"
			});
			doResize(); 
			
			$(function(){
				$(".search-btn").on("click", function(){
					var year = $("#yearselect").val();
					var type = $("#typeselect").val();
					var stage = $("#stageselect").val();
					$("#jqGrid").jqGrid('setGridParam',{
						datatype : 'json',
						postData : {'year': year, 'type' : type, 'stage' : stage},
						page : 1
					}).trigger("reloadGrid");
				});
			}); 
			
		});
	</script>
</body>

</html>