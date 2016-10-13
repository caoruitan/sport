<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<link href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet">
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
</head>
<body>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />用户新增<span>所有加 * 的区域为必填项。</span></div>
    <div class="returnBtn user-returnBtn">返回列表</div>
</div>
<div class="editBox">
	<form class="sport-user-form">
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
					<select class="selectpicker" name="credType" title="请选择" class="{required:true}">
						<option value="1" >身份证</option>
						<option value="2">军官证</option>
						<option value="3">港澳台胞证</option>
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
				<td><input name="organization" type="text" />
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
		<button class="btn-red btn-size-big sport-user-save" type="button">保存</button>
		<button class="btn-wisteria btn-size-big sport-rest-btn" type="button">重置</button>
	</p>
	<script src="<%=basePath %>/static/js/icheck/icheck.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(function(){
			// 处理select不初始化的问题
			$(window).trigger("load");
			$('input').iCheck({
				checkboxClass: 'icheckbox_flat-blue',
				radioClass: 'iradio_flat-blue',
				increaseArea: '20%' // optional
			});
			
			jQuery.validator.addMethod("pwd", function(value, element, param) {
			   	// 判断密码是否由大小写字母数字和特殊字符组成（除空格）
			   	for(var i = 0; i < length; i++){
			   		if(value.charCodeAt(i) < 32 || value.charCodeAt(i) > 126){
			   			return false;
			   		}
			   	}
			   	// 密码必须包含上述字符中的至少两种
			   	var reg1 = new RegExp("^\\d+$");	//不能纯数字
			   	var reg2 = new RegExp("^[a-z]+$");	//不能纯小写字母
			   	var reg3 = new RegExp("^[A-Z]+$");	//不能纯大写字母
			   	var reg4 = new RegExp("^((?=[\x21-\x7e]+)[^A-Za-z0-9])+$");	//不能纯标点
			   	return !reg1.test(value) && !reg2.test(value) && !reg3.test(value) && !reg4.test(value);
			});
			
			
			// 登录验证
			$(".sport-user-form").validate({
		        rules: {
		        	loginName:{
		                required: true,
		                minLength:4,
		                maxLength:20
		            },
		            userName:{
		                required: true,
		                minLength:4,
		                maxLength:20
		            },
		            password: {
		                required: true,
		                minLength:4,
		                maxLength:16,
		                pwd:true
		            },
		            confirmPassword: {
		                required: true,
		                equalTo:"#confirmPassword"
		            },
		            credType:{
		            	required: true
		            },
		            credNo:{
		            	required: true
		            },
		            organization:{
		            	required: true
		            },
		            email:{
		            	required: false
		            }
		        },
		        messages: {
		        	loginName:{
		                required: "请填写用户名",
		                minlength:'用户名在4-20个字符之间',
		                maxlength:'用户名在4-20个字符之间',
		            },
		            userName:{
		                required: "请填写真实姓名",
		                minlength:'真实姓名在4-20个字符之间',
		                maxlength:'真实姓名在4-20个字符之间',
		            },
		            password: {
		            	 required:'请填写密码',
		            	 minlength:'密码在6-16个字符之间',
		                 maxlength:'密码在6-16个字符之间',
		            	 pwd:'必须由两种以上的大小写字母数字和特殊字符(空格除外)组成'
		            },
		            confirmPassword: {
		            	required : "确认密码不能为空",
		            	equalTo:'两次密码输入不一致,请重新输入'
		            },
		            credType:{
		            	required: "请选择证件类型"
		            },
		            credNo:{
		            	required: "请填写证件编号"
		            },
		            organization:{
		            	required: "请填写所属单位"
		            }
		        }
		     });
		});
	</script>
</div>
</body>
</html>