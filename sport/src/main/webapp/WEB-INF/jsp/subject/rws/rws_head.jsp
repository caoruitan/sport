<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>任务书填报</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	
	<!--jqgrid-->
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	
	<!--lhgdialog-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>

	<!--checkbox-->
	<link rel="stylesheet" href="<%=basePath %>/static/css/square/red.css">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/icheck/icheck.js"></script>
	
	<script src="<%=basePath %>/static/js/ckeditor/ckeditor.js"></script>
	<style type="text/css">
		body {
			background: #F6F6F6;
		}
		
		.titleBox1 {
			box-sizing: border-box;
			width: 1200px;
			height: 50px;
			border: 1px solid #e4e4e4;
			border-top: 2px solid #F96A66;
			background: #ffffff;
			position: relative;
			color: #73819A;
			margin: 0 auto;
			margin-bottom: 10px;
		}
		
		.titleBox1 .tip {
			position: absolute;
			top: 6px;
			left: -4px;
			background: url(<%=basePath %>/static/img/tip1.png);
			color: #FFFFFF;
			padding-left: 14px;
			line-height: 32px;
			height: 32px;
			width: 114px;
		}
		
		.titleBox1 .btnBox {
			position: absolute;
			top: 10px;
			right: 0px;
		}
		
		.titleBox1 .title {
			width: 1000px;
			height: 46px;
			line-height: 46px;
			font-size: 18px;
			font-family: "微软雅黑";
			text-align: left;
			padding-left: 120px;
		}
		
		.titleBox1 .title span {
			font-size: 14px;
			font-family: "微软雅黑";
			text-align: left;
			color: #ff3300;
			margin-left: 10px;
		}
		
		.ktview {
			width: 1200px;
			margin: 0 auto;
			height: auto;
			min-height: 500px;
		}
		
		.ktview .kt-right {
			float: left;
			width: 890px;
			background: #ffffff;
			min-height: 660px;
			margin: 0 0 0 10px;
			border: 1px solid #e4e4e4;
		}
		
		.ktview .kt-left {
			float: left;
			width: 300px;
			z-index: 10;
		}
		
		.ktview .kt-left .lc {
			margin: 0px 0 0 0px;
			min-width: 100px;
			width: 100%;
			height: auto;
			min-height: 500px;
			float: left;
			border: 1px solid #D7D7D7;
			padding: 10px;
			background: #fafafa;
		}
		
		.line {
			float: left;
			position: relative;
			width: 280px;
			height: auto;
			border-left: 3px double #d2d2d2;
			margin: 10px 0 0 10px;
		}
		
		.line li {
			width: 100%;
			overflow: hidden;
			float: left;
			background: red;
			height: auto;
			cursor: pointer;
			border: 1px solid #ffffff;
		}
		
		.line li a {
			width: 240px;
			overflow: hidden;
			text-decoration: none;
		}
		
		.line li.dot {
			background: url(<%=basePath %>/static/img/kt-edit.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.ok {
			background: url(<%=basePath %>/static/img/kt-ok.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.cur {
			background: url(<%=basePath %>/static/img/kt-cur.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.b-active {
			color: #ff3300 !important;
			border-radius: 10px 2px 2px 10px;
			background-color: #f2f2f2;
			border: 1px solid #aaa;
			
		}
		.line li:hover {
			border-radius: 10px 0 0 10px;
			background-color: #f2f2f2;
			border: 1px solid #aaa;
		}
		
		.box {
			display: none;
			width: 100%;
			box-sizing: border-box;
			padding: 10px 10px 10px 10px;
		}
		
		.box .t {
			width: 100%;
			height: 40px;
			line-height: 40px;
			text-align: center;
			font-size: 18px;
			color: #73819A;
			font-weight: bold;
			border-bottom: 1px solid #e4e4e4;
		}
		
		.box .c {
			width: 100%;
			height: auto;
			margin: 10px 0 0 0;
		}
		
		.box .c .centerBox {
			width: 100%;
			margin: 20px 0 0 0;
			text-align: center;
		}
		
		.box .c .centerBox img {
			width: 68px;
			margin: 20px auto;
		}
	</style>
</head>