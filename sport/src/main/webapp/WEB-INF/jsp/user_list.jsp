<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
    <script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
    <script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<style type="text/css">
		body{
			background: #F2F2F2;}
	</style>
</head>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />用户管理</div>
	<div class="searchBtn">查询</div>
</div>
<div class="searchBox">
	<dt>姓名</dt>
	<dd><input type="text" /></dd>
	<dt>职称</dt>
	<dd><input type="text" /></dd>
	<dt>系统归属</dt>
	<dd>
		<select class="selectpicker" title="请选择系统">
		<option>OA系统</option>
		<option>申报系统</option>
	</select>
	</dd>
	<button class="search-btn">查询</button>
</div>
<div class="listBox">
	<div class="opBtnBox">
		<c:if test="${hasOper}">
			<div class="fl-l">
					<a href="javascript:;;" class="sport-user-create-btn"><button class="btn-red">+ 创建</button></a>
			</div><div class="fl-r">
				<button class="btn-wisteria">删除</button>
			</div>
		</c:if>
	</div>
	<table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script type="text/javascript"> 
	$(document).ready(function () {
        // 处理select不初始化的问题
		$(window).trigger("load");
		$("#jqGrid").jqGrid({
		url: '<%=basePath %>/user/datas',
		mtype: "GET",
		datatype: "json",
			colModel: [
				{label:"用户名",name:'loginName',align:"center", width:20, sorttype:"date"},
				{label:"用户名",name:'userName', align:"center", width:15},
				{label:"用户名",name:'credType', width:10, align:"center",sorttype:"float"},
				{label:"用户名",name:'credNo', width:30, align:"left",sorttype:"float"},		
				{label:"用户名",name:'dept', width:10,align:"center",sorttype:"float"},		
				{label:"用户名",name:'操作', width:10, align:"center",sortable:false,hidden:"${hasOper}"}
              ],
			autowidth:true,
			viewrecords: true,
			height: 200,
			rowNum: 20,
			multiselect: true,
			pager: "#jqGridPager"
          });
			doResize(); 
			$("#jqGrid").setGridWidth($(".listBox").width());
      });
</script>