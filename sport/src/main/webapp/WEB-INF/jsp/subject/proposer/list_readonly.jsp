<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<title>新增申请人</title>
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
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/square/red.css">
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/icheck/icheck.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/my97/WdatePicker.js"></script>
	<style type="text/css">
		body {
			background: #F2F2F2;
			width: 1200px;
			margin: 0 auto;
		}
		
		.titleBox2 {
			font-size: 16px;
			float: left;
			width: 100%;
			margin-bottom: 10px;
		}
		
		.sqrBox {
			width: 200px;
			height: 100px;
			padding-top: 32px;
			margin-bottom: 30px;
			position: relative;
			border: 1px solid #E4E4E4;
			background: #fafafa;
			float: left;
			margin-right: 10px;
		}
		.sqrBox:hover{
			border:1px solid #aaaaaa;
		}
		
		.sqrBox img {
			width: 16px;
			height: 16px;
			margin: 0 6px 2px 0;
			cursor: pointer;
		}
		
		.sqrBox li {
			width: 100%;
			height: 26px;
			line-height: 26px;
			padding-left: 20px;
			color: #999999;
		}
		
		.sqrBox .t {
			position: absolute;
			left: 60px;
			top: 0px;
			width: 80px;
			height: 22px;
			line-height: 22px;
			background: #5A677E;
			color: #FFFFFF;
			text-align: center;
		}
		
		.sqrBox .op {
			position: absolute;
			right: -1px;
			bottom: -32px;
			width: 60px;
			height: 22px;
			line-height: 22px;
			background: #fafafa;
			color: #FFFFFF;
			text-align: center;
			border: 1px solid #aaaaaa;
			border-top: none;
			display: none;
		}
		
		#add {
			border:1px dashed #aaaaaa;
			background: #ffffff url(<%=basePath %>/static/img/jia.png) center center no-repeat;
			cursor: pointer;
			border-radius: 4px;
		}
		
		#add:hover {
			border:1px dashed #ff3300;
			background: #ffffff url(<%=basePath %>/static/img/jia-hover.png) center center no-repeat;
			cursor: pointer;
		}
	</style>
	</head>
	<body>
		<div class="titleBox2">
			<div class="title">主要申请人</div>
		</div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
        <c:forEach var="p" items="${primaryProposers}">
            <div class="sqrBox proposerBox">
                <p class="t">第一申请人</p>
                <li><img src="<%=basePath %>/static/img/user.png" /><a>${p.name}</a> ${p.gender} ${p.age}岁</li>
                <li>${p.zw}</li>
            </div>
        </c:forEach>
		<div class="sqrBox" id="add">
		</div>
		<div class="titleBox2">
			<div class="title">其他申请人</div>
		</div>
		<div class="listBox" id="ddfdfd">
			<table id="proposerGrid" class="sport-grid"></table>
			<div id="proposerGridPager"></div>
		</div>

		<script type="text/javascript">
			$(function() {
				$("#proposerGrid").jqGrid({
					datatype: "json",
					url: "<%=basePath%>/subject/proposer/other/datas.action?sbsId=${sbsId}",
					colModel: [
						{name:'id',align:"center", width:20,hidden:true},
						{label:"姓名",name:'name',align:"center", width:20},
						{label:"所属单位",name:'org', align:"center", width:15},
						{label:"出生日期",name:'birthday', width:20, align:"center",sorttype:"date"},
						{label:"职务",name:'zw', width:20, align:"center",sorttype:"float"},		
						{label:"研究分工",name:'work', width:30,align:"center",sorttype:"float"}
		              ],
					viewrecords: true,
					rowNum: 20,
					multiselect: true,
					pager: "#proposerGridPager"
				});
				doResize();
				$("#proposerGrid").setGridWidth($("div.listBox").width());
			});
		</script>
	</body>
</html>