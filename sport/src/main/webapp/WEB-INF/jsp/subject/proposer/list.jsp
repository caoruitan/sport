<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<title>新增申请人</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/square/red.css">
	<style type="text/css">
		body {
			background: #F2F2F2;
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
			left: 50px;
			top: 0px;
			width: 100px;
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
		<c:forEach var="p" items="${primaryProposers}" varStatus="status">
			<div class="sqrBox proposerBox">
				<p class="t">第${status.index==0?'一':(status.index==1?'二':(status.index==2?'三':'')) }申请人</p>
				<li><img src="<%=basePath %>/static/img/user.png" /><a>${p.name}</a> ${p.gender}</li>
				<li>${p.zw}</li>
				<c:if test="${status.index==0 }">
					<p class="op"><img src="<%=basePath %>/static/img/edit.png"  data-id="${p.id}" class="proposer-edit" /></p>
				</c:if>
				<c:if test="${status.index!=0 }">
					<p class="op"><img  src="<%=basePath %>/static/img/del.png" data-id="${p.id}" class="proposer-delete"/><img src="<%=basePath %>/static/img/edit.png"  data-id="${p.id}" class="proposer-edit" /></p>
				</c:if>
			</div>
		</c:forEach>
		<c:if test="${fn:length(primaryProposers) < 3 }">
			<div class="sqrBox" id="add"></div>
		</c:if>
		<div class="titleBox2">
			<div class="title">其他申请人</div>
		</div>
		<div class="listBox">
			<div class="opBtnBox">
				<div class="fl-l">
					<button class="btn-red" id="sqrxz">+ 新增</button>
				</div>
				<div class="fl-r">
					<button class="btn-wisteria proposer-other-delete">删除</button>
				</div>
			</div>
			<table id="proposerGrid" class="sport-grid"></table>
			<div id="proposerGridPager"></div>
		</div>

		<script type="text/javascript">
			$(function() {
				jQuery.validator.addMethod("stringCheck", function(value, element) { 
					return this.optional(element) || /^[\u0391-\uFFE5\w]{1,100}$/.test(value); 
				});
				
				jQuery.validator.addMethod("nullableCheck", function(value, element) { 
					if(Sport.isNull(value)) {
						return true;
					} 
					return this.optional(element) || /^[\u0391-\uFFE5\w]{1,40}$/.test(value); 
				}); 
				$("#proposerGrid").jqGrid({
					datatype: "json",
					url: "<%=basePath%>/subject/proposer/datas.action?sbsId=${sbsId}",
					colModel: [
						{name:'id',align:"center", width:20,hidden:true},
						{label:"姓名",name:'name',align:"center", width:20},
						{label:"所属单位",name:'org', align:"center", width:15},
						{label:"出生日期",name:'birthday', width:20, align:"center",sorttype:"date"},
						{label:"职务",name:'zw', width:20, align:"center",sorttype:"float"},
						{label:"研究分工",name:'work', width:30,align:"center",sorttype:"float"},
						{label:"操作",width:10, align:"center",sortable:false,formatter:function(value, grid, rows, state){
							return "<a href='javascript:;;' class='proposer-other-edit' data-id='"+rows.id+"'>编辑</a>";
						}}
					],
					viewrecords: true,
					rowNum: 20,
					multiselect: true,
					pager: "#proposerGridPager"
				});
				doResize();
				$("#proposerGrid").setGridWidth($("div.listBox").width());

				$(".proposerBox").hover(
					function() {
						$(this).find(".op").show();
					}, 
					function () {
						$(this).find(".op").hide();
					}
				);
				
				$(".proposer-delete").click(function(){
					var dataId = $(this).attr("data-id");
					lhgdialog.confirm("您确定要删除申请人吗？",function(){
						$.ajax({
							url: Sport.getBasePath()+"/subject/proposer/sboper/delete.action",
							data:{pId:dataId,_csrf:$("#csrdId").val()},
							type:"POST",
							success:function(data){
								$(".sqrqk-container").load("<%=basePath%>/subject/proposer/sboper/list.htm?sbsId=${sbsId}&subjectId=${subjectId}");
							},
							error:function(){
								
							}
						});
					});
				});
				
				//申请人编辑
				$(document).on('click','.proposer-edit,.proposer-other-edit',function(){
					proposerId = $(this).attr("data-id");
					var dg = new $.dialog({
						id: 'sportproposerupdate',
						title: '修改申请人',
						content: 'url:<%=basePath %>/subject/proposer/sboper/update.htm?sbsId=${sbsId}&subjectId=${subjectId}&proposerId='+proposerId,
						width: 800,
						height: 430,
						ok: function(){
							return operProposer(this,"subject/proposer/sboper/update.action");
						},
						cancel: true
					});
				});
				
				//申请人新增
				$('#add').dialog({
					id: 'sportproposernew',
					title: '新增申请人',
					content: 'url:<%=basePath %>/subject/proposer/sboper/primary/create.htm?sbsId=${sbsId}&subjectId=${subjectId}',
					width: 1000,
					height: 430,
					ok: function(){
						return operProposer(this,"subject/proposer/sboper/primary/create.action");
					},
					cancel: true
				});
				
				//申请人新增
				$('#sqrxz').dialog({
					id: 'sportproposernew',
					title: '新增申请人',
					content: 'url:<%=basePath %>/subject/proposer/sboper/other/create.htm?sbsId=${sbsId}&subjectId=${subjectId}',
					width: 1000,
					height: 430,
					ok: function(){
						return operProposer(this,"subject/proposer/sboper/other/create.action");
					},
					cancel: true
				});
				
				$(".proposer-other-delete").click(function(){
					var selectedIds = $("#proposerGrid").jqGrid("getGridParam", "selarrrow");
					if(selectedIds.length<1){
						layer.msg("请最少选择一行数据");
						return;
					}
					var dicIds = new Array();
					for (var int = 0; int < selectedIds.length; int++) {
						var rowData = $("#proposerGrid").jqGrid("getRowData",selectedIds[int]);
						dicIds.push(rowData.id);
					}
					lhgdialog.confirm("您确定要删除申请人吗？",function(){
						$('.proposer-other-delete').attr("disabled",true);
						$.ajax({
							url: Sport.getBasePath()+"/subject/proposer/sboper/delete.action",
							type: "POST",
							dataType: "JSON",
							data: {_csrf:$("#csrdId").val(),pId:dicIds.join(",")},
							error: function () {
								$('.proposer-other-delete').removeAttr("disabled");
								layer.msg("系统异常，请稍后重试");
							},
							success: function (obj) {
								if(obj){
									layer.msg("删除用户成功!");
									$(".sqrqk-container").load("<%=basePath%>/subject/proposer/sboper/list.htm?sbsId=${sbsId}&subjectId=${subjectId}");
								}else{
									layer.msg("删除用户失败,请稍后重试。");
								}
								$('.proposer-other-delete').removeAttr("disabled");
							}
						});
					});
				});
			});
			
			function operProposer(obj,url){
				debugger;
				var doucment = obj.content.document.forms[0];
				// 参数验证
				$(doucment).validate({
					rules: {
						name:{
							required: true,
							maxlength:20
						},
						birthday:{
							required: true
						},
						zw:{
							nullableCheck:true
						},
						major:{
							nullableCheck:true
						},
						org:{
							required:false
						},
						backdrop:{
							required:true
						},
						work:{
							required:true
						}
					},
					messages: {
						name:{
							required: "请填写申请人姓名",
							maxlength:'姓名在1-20个字符之间'
						},
						birthday:{
							required: "请填写申请人出生日期"
						},
						zw:{
							nullableCheck:"职务长度不能超过40个字符"
						},
						org:{
							required: "请填写所属单位"
						},
						email:{
							 email:"请填写正确的邮箱格式"
						},
						major:{
							nullableCheck:"专业长度不能超过40个字符"
						},
						backdrop:{
							required:"请填写研究背景"
						},
						work:{
							required:"请填写研究分工"
						}
					}
				});
				var result = $(doucment).valid()
				// 证书校验
				var zw = $(doucment).find(".zw-select").val();
				var zwFlag = Sport.isNull(zw);
				if(zwFlag){
					$(doucment).find(".zw-error").text("请选择职务");
				}else{
					$(doucment).find(".zw-error").text("");
				}
				// 证书校验
				var degrees = $(doucment).find(".degrees-select").val();
				var degreesFlag = Sport.isNull(degrees);
				if(degreesFlag){
					$(doucment).find(".degrees-error").text("请选择学历");
				}else{
					$(doucment).find(".degrees-error").text("");
				}
				if(result && !zwFlag && !degreesFlag){
					$.ajax({
						url:"<%=basePath %>/"+url,
						data:$(doucment).serialize(),
						type:"POST",
						success:function(data){
							$(".sqrqk-container").load("<%=basePath%>/subject/proposer/sboper/list.htm?sbsId=${sbsId}&subjectId=${subjectId}");
						},
						error:function(){
							
						}
					});
					return true;
				}
				return false;
			}
		</script>
	</body>
</html>