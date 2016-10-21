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
			<div class="title"><img src="<%=basePath %>/static/img/m-dw.png" />单位:${org.fullName}<span></span></div>
			<div class="returnBtn orgdetail-returnBtn">返回列表</div>
		</div>
		<div class="viewBox">
			<table class="viewTable">
				<tr>
					<th>单位全称</th>
					<td>${org.fullName}</td>
					<th>英文名称</th>
					<td>${org.englishName}</td>
				</tr>
				<tr>
					<th>简称</th>
					<td>${org.shortName}</td>
					<th>法人代表</th>
					<td>${org.legalLeader}</td>
				</tr>
				<tr>
					<th>所在地区</th>
					<td>北京</td>
					<th>单位性质</th>
					<td>${org.quality}</td>
				</tr>
				<tr>
					<th>组织机构代码</th>
					<td>${org.code}</td>
					<th>单位电话</th>
					<td>${org.telphone}</td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td>${org.email}</td>
					<th>邮编</th>
					<td>${org.post}</td>
				</tr>
				<tr>
					<th>业务负责人姓名</th>
					<td>${org.managerName}</td>
					<th>业务负责人手机</th>
					<td>${org.managerPhone}</td>
				</tr>
				<tr>
					<th>业务负责人Email</th>
					<td>${org.managerEmail}</td>
				</tr>
			</table>
			<p class="save-btn">
				<button class="btn-red btn-size-big sport-verify-pass" type="button" data-id="${org.orgId}">通过</button>
				<button class="btn-wisteria btn-size-big sport-verify-unpass" type="button" data-id="${org.orgId}">不通过</button>
			</p>
		</div>
<script type="text/javascript">
	$(function(){
		// 注册单位审核
		$('.sport-verify-pass').dialog({
			id: 'sh',
			title: '审核通过',
			content: '<div class="dlg-contentbox"><img src="'+Sport.getBasePath()+'/static/img/prompt.gif" />确定审核通过吗？</div>',
			width: 400,
			height: 130,
			ok: function(data){
				$(".sport-verify-pass").attr("disabled",true);
				var token = $("meta[name='_csrf']").attr("content");
			    var header = $("meta[name='_csrf_header']").attr("content");
			    $(document).ajaxSend(function(e, xhr, options) {
			         xhr.setRequestHeader(header, token);
			    });
				$.ajax({
					url: Sport.getBasePath()+"/org/kjsadmin/pass.action",
					type: "POST",
					data: {orgId:$(".sport-verify-pass").attr("data-id")},
					error: function () {
						layer.msg("系统异常，请稍后重试!");
						$(".sport-verify-pass").removeAttr("disabled");
					},
					success: function (obj) {
						if(obj){
							layer.msg("审核单位成功！");
							window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
						}else{
							layer.msg("审核单位失败！");
						}
						$(".sport-verify-pass").removeAttr("disabled");
					}
				})
			},
			cancel: true
		});
		
		// 注册单位审核
		$('.sport-verify-unpass').dialog({
			id: 'btg',
			title: '审核不通过',
			content: '<div class="dlg-contentbox"><textarea placeholder="请填写退回意见" name="" style="width:530px" rows="4" cols="10"></textarea></div>',
			width: 600,
			height: 230,
			ok: function(data){
				var token = $("meta[name='_csrf']").attr("content");
			    var header = $("meta[name='_csrf_header']").attr("content");
			    $(document).ajaxSend(function(e, xhr, options) {
			         xhr.setRequestHeader(header, token);
			    });
			},
			cancel: true
		});
	})
</script>
	</body>
</html>