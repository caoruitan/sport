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
				<input type="text" />
			</dd>
			<dt>课题类型</dt>
			<dd>
				<select id="lunch" class="selectpicker" data-live-search="false" title="请选择">
					<option>请选择</option>
					<option>招标课题</option>
					<option>科技攻关课题</option>
				</select>
			</dd>
			<dt>状态</dt>
			<dd>
				<select id="lunch" class="selectpicker" data-live-search="false" title="请选择">
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
				<!--<button class="btn-red" id="xzzj">+ 选择专家</button>
				<button class="btn-red">阶段性报告设置</button>-->
			</div>
			<div class="fl-r">
				<button class="btn-wisteria">删除</button>
			</div>
		</div>
		<table id="jqGrid"></table>
		<div id="jqGridPager"></div>
	</div>

	<script type="text/javascript"> 
		$(document).ready(function () {
			$("#jqGrid").jqGrid({
				//url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
				//mtype: "GET",
				//datatype: "jsonp",
				datatype: "local",
				colModel: [
					{name:'No',index:'No.', width:4, sorttype:"int", align:"center"},
					{name:'课题名称',index:'课题名称', width:22, sorttype:"date"},
					{name:'课题类型',index:'课题类型', width:10, align:"center"},
					{name:'申报书截止',index:'申报书截止', width:10, align:"center",sorttype:"float"},
					{name:'任务书截止',index:'任务书截止', width:10, align:"center",sorttype:"float"},
					{name:'课题截止',index:'课题截止', width:10, align:"center",sorttype:"float"},
					{name:'创建',index:'创建', width:10, align:"center",sorttype:"float"},
					{name:'当前任务节点',index:'当前任务节点', width:10, align:"center",sorttype:"float"},
					{name:'审批状态',index:'审批状态', width:6,align:"center",sorttype:"float"},
					{name:'操作',index:'操作', width:10, align:"center",sortable:false}
				],
				autowidth:true,
				viewrecords: true,
				height: 200,
				rowNum: 20,
				multiselect: true,
				pager: "#jqGridPager"
			});
			
			var mydata = [
				{No:"1",课题名称:"<a href='04ktck.html'>国民体质测试器材标注的研究1</a>",课题类型:"科技攻关课题",申报书截止:"2016-10-20",任务书截止:"2016-10-20",课题截止:"2016-10-20",创建:"2016-10-20",当前任务节点:"申报书填报",审批状态:"<span class='greenfont'>通过</span>",操作:"编辑"},
				{No:"2",课题名称:"国民体质测试器材标注的研究2",课题类型:"科技攻关课题",申报书截止:"2016-10-20",任务书截止:"2016-10-20",课题截止:"2016-10-20",创建:"2016-10-20",当前任务节点:"申报书填报",审批状态:"<span class='redfont'>不通过</span>",操作:"编辑"},
				{No:"3",课题名称:"国民体质测试器材标注的研究3",课题类型:"科技攻关课题",申报书截止:"2016-10-20",任务书截止:"2016-10-20",课题截止:"2016-10-20",创建:"2016-10-20",当前任务节点:"申报书填报",审批状态:"<span class='redfont'>已退回</span>",操作:"编辑"},
				{No:"4",课题名称:"国民体质测试器材标注的研究4",课题类型:"科技攻关课题",申报书截止:"2016-10-20",任务书截止:"2016-10-20",课题截止:"2016-10-20",创建:"2016-10-20",当前任务节点:"申报书填报",审批状态:"<span class='redfont'>待审批</span>",操作:"审批"}
			];
			for(var i=0;i<=mydata.length;i++){
				jQuery("#jqGrid").jqGrid('addRowData',i+1,mydata[i]);
			}
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