﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>新增课题</title>
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

	<!--checkbox-->
	<link rel="stylesheet" href="<%=basePath %>/static/css/square/red.css">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/icheck/icheck.js"></script>

	<!--My97-->
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/my97/WdatePicker.js"></script>

	<style type="text/css">
		body {
			background: #F2F2F2;
		}
		
		.yqcg p {
			float: left;
			width: 33%;
		}
		
		.settingBox {
			position: absolute;
			right: 20px;
			top: 20px;
			width: 200px;
			height: auto;
			border: 1px solid #e4e4e4;
			padding: 10px;
			background: #f8f8f8;
			-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
			-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
			box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		}
		
		.settingBox .xzzj {
			float: left;
			width: 100%;
		}
		
		.settingBox .xzzj li {
			float: left;
			width: 100%;
			height: 30px;
			line-height: 30px;
		}
		
		.settingBox .xzzj li span {
			color: #999;
		}
		
		.settingBox .xzzj li a {
			color: #ff3300;
			margin-left: 4px;
		}
		
		.settingBox .xzzj li a:hover {
			color: #cc3300;
			margin-left: 4px;
		}
		
		.settingBox .jdbg {
			float: left;
			width: 100%;
		}
		
		.settingBox .jdbg li {
			float: left;
			width: 100%;
			height: 30px;
			line-height: 30px;
		}
		
		.settingBox .jdbg li span {
			color: #ff3300;
		}
	</style>
</head>

<body>
	<div class="titleBox">
		<div class="title"><img src="<%=basePath %>/static/img/yh.png" />课题申报<span>所有加 * 的区域为必填项。</span></div>
		<a href="<%=basePath %>/subject/sboper/list.htm">
			<div class="returnBtn">返回列表</div>
		</a>
	</div>
	<div class="editBox">
		<form id="subjectCreateForm" action="<%=basePath %>/subject/sboper/createSubject.htm" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
			<table class="editTable">
				<tr>
					<th class="required">课题名称</th>
					<td colspan="3"><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th class="required">课题类型</th>
					<td colspan="3">
						<c:forEach items="${types}" var="type" varStatus="status">
							<c:if test="${status.index eq 0}">
								<input id="type_${type.key}" type="radio" name="type" onchange="changeType()" checked="checked" value="${type.key}"><label for="type_${type.key}">${type.value}</label>
							</c:if>
							<c:if test="${status.index ne 0}">
								<input id="type_${type.key}" type="radio" name="type" onchange="changeType()" value="${type.key}"><label for="type_${type.key}">${type.value}</label>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th class="required">组织单位</th>
					<td colspan="3">
						<div id="kjsList" style="display: block">
							<select name="organizationId" class="selectpicker" style="width: 300px;">
								<c:forEach items="${kjsList}" var="kjs">
									<option value="${kjs.orgId}">${kjs.fullName}</option>
								</c:forEach>
							</select>
						</div>
						<div id="orgList" style="display: none">
							<select name="organizationId" class="selectpicker" style="width: 300px;">
								<c:forEach items="${orgList}" var="org">
									<option value="${org.orgId}">${org.fullName}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th>密级</th>
					<td>
						<c:forEach items="${secretList}" var="secret" varStatus="status">
							<c:if test="${status.index eq 0}">
								<input id="secret_${secret.code}" type="radio" name="security" value="${secret.code}" checked="checked"><label for="secret_${secret.code}">${secret.name}</label>
							</c:if>
							<c:if test="${status.index ne 0}">
								<input id="secret_${secret.code}" type="radio" name="security" value="${secret.code}"><label for="secret_${secret.code}">${secret.name}</label>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>参加单位总数</th>
					<td><input name="organizationCount" type="text"/></td>
				</tr>
				<tr>
					<th>课题时间</th>
					<td colspan="3">
						<p class="twoDatePicker">
							<span><input name="beginDate" type="text" onClick="WdatePicker()"/></span>
							<span> 至 </span>
							<span><input name="endDate" type="text" onClick="WdatePicker()"/></span>
						</p>
					</td>
				</tr>
				<tr>
					<th>预期成果</th>
					<td class="yqcg">
						<c:forEach items="${expectList}" var="expect">
							<p><input id="results_${expect.code}" type="checkbox" name="results" value="${expect.code}"><label for="results_${expect.code}">${expect.name}</label></p>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>产学联合</th>
					<td>
						<input id="integration_yes" type="radio" name="integration" value="true" checked="checked"><label for="integration_yes">是</label>
						<input id="integration_no" type="radio" name="integration" value="false"><label for="integration_no">否</label>
					</td>
				</tr>
			</table>
		</form>
		<p class="save-btn">
			<button class="btn-red btn-size-big" onclick="submit()">保存</button>
			<button class="btn-wisteria btn-size-big" onclick="reset()">重置</button>
		</p>
	</div>
	<script>
		var submit = function() {
			$("#subjectCreateForm").submit();
		}
		var reset = function() {
			$("#subjectCreateForm")[0].reset();
		}
		var changeType = function() {
			debugger;
			var val = $("input[name='type']:checked").val();
			if(val == "ZBKT") {
				$("#kjsList").css("display", "block");
				$("#orgList").css("display", "none");
			} else if (val == "KYGGKT") {
				$("#orgList").css("display", "block");
				$("#kjsList").css("display", "none");
			}
		}
		
		$(function() {
			$('input').iCheck({
				checkboxClass: 'icheckbox_square-red',
				radioClass: 'iradio_square-red',
				increaseArea: '20%' // optional
			});
			
			$("#subjectCreateForm").validate({
				rules: {
					name : {
						required : true,
						minlength : 1,
						maxlength : 50
					}
				},
				messages: {
					name : {
						required : "请填写课题名称",
						minlength : "请填写课题名称",
						maxlength : "课题名称不能超过50个字符"
					}
				}
			});
		});
	</script>
</body>
