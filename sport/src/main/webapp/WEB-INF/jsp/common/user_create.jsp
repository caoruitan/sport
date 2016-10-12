<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath %>/static/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/sport.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath %>/static/css/bootstrap.min.css">
	<script src="<%=basePath %>/static/js/jquery.min1.10.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script src="<%=basePath %>/static/js/common.js"></script>
	<script src="<%=basePath %>/static/js/sport.js"></script>
	
	<!--select-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<!--checkbox-->
	<link href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet">
	<script src="<%=basePath%>/static/js/jqselect/bootstrap-select.js"></script>
	<script src="<%=basePath %>/static/js/icheck/icheck.js" type="text/javascript" charset="utf-8"></script>
	<!--My97-->
	<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
	<!--My97-->
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
</head>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />用户新增<span>所有加 * 的区域为必填项。</span></div>
	<a href="javascript:history.go(-1)">
		<div class="returnBtn">返回列表</div>
	</a>
</div>
<div class="editBox">
	<form class="sport-form">
		<table class="editTable">
			<tr>
				<th class="required">用户名</th>
				<td><input name="loginName" type="text" /></td>
				<th class="required">真实姓名</th>
				<td><input name="userName" type="text" /></td>
			</tr>
			<tr>
				<th class="required">登录密码</th>
				<td><input name="password" type="text"/></td>
				<th class="required">证件类型</th>
				<td>
					<select id="lunch" class="selectpicker" title="请选择">
						<option>请选择</option>
						<option>身份证</option>
						<option>军官证</option>
						<option>港澳台胞证</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="required">确认密码</th>
				<td><input name="confirmPassword" type="text" /></td>
				<th class="required">证件号码</th>
				<td><input name="credNo" type="text" value="" /></td>
			</tr>
			<tr>
				<th class="required">性别</th>
				<td>
					<input id="男" type="radio" name="性别" checked=""><label for="男">男</label>
					<input id="女" type="radio" name="性别"><label for="女">女</label>
				</td>
				<th class="required">用户角色</th>
				<td>
					<input id="管理员" type="radio" name="角色" checked=""><label for="管理员">管理员</label>
					<input id="领导" type="radio" name="角色"><label for="领导">领导</label>
				</td>
			</tr>
			<tr>
				<th class="required">所属单位</th>
				<td><input name="company" type="text" value="" />
				</td>
			</tr>
			<tr>
				<th>出生日期</th>
				<td><input id="d11" type="text" onClick="WdatePicker()"/></td>
				<th>电子邮件</th>
				<td><input name="email" type="text" /></td>
			</tr>
			<tr>
				<th>职称</th>
				<td><input name="zc" type="text" /></td>
				<th>职务</th>
				<td><input name="zw" type="text"/></td>
			</tr>
			<tr>
				<th>所属部门</th>
				<td><input name="name" type="text" /></td>
				<th>学历</th>
				<td><input name="degrees" type="text" /></td>
			</tr>
			<tr>
				<th>专业</th>
				<td><input name="major" type="text"/></td>
				<th>电话</th>
				<td><input name="telephone" type="text" /></td>
			</tr>
			<tr>
				<th>手机</th>
				<td><input name="phone" type="text" /></td>
				<th>地址</th>
				<td><input name="address" type="text" /></td>
			</tr>
		</table>
	</form>
	<p class="save-btn">
		<button class="btn-red btn-size-big" type="button">保存</button>
		<button class="btn-wisteria btn-size-big sport-rest-btn" type="button">重置</button>
	</p>
	<script>
		$(document).ready(function() {
			$('input').iCheck({
				checkboxClass: 'icheckbox_flat-blue',
				radioClass: 'iradio_flat-blue',
				increaseArea: '20%' // optional
			});
		});
	</script>
</div>