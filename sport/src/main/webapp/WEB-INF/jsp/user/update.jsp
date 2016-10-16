<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<link href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet">
	<script src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
</head>
<body>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />用户修改<span>所有加 * 的区域为必填项。</span></div>
    <div data-type="${user_type}" class="returnBtn user-returnBtn">返回列表</div>
</div>
<div class="editBox">
	<form class="sport-user-form sport-form">
		<input type="hidden" name="pubKey" value="${pubKey}" id="pubKey"/>
		<input type="hidden" name="userId" value="${user.userId}" id="userId"/>
		<input type="hidden" name="uuid" value="${uuid}" id="uuid"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
		<table class="editTable">
			<tr>
				<th class="required">用户名</th>
				<td><input name="loginName" type="text" id="loginName" value="${user.loginName}"/></td>
				<th class="required">真实姓名</th>
				<td><input name="userName" type="text" id="userName" value="${user.userName}"/></td>
			</tr>
			<tr>
				<th class="required">证件类型</th>
				<td>
					<select class="selectpicker credType-select" name="credType" title="请选择" id="credType">
						<option value="1">身份证</option>
						<option value="2">军官证</option>
						<option value="3">港澳台胞证</option>
					</select>
					<span class="error credType-error"></span>
				</td>
				<th class="required">证件号码</th>
				<td><input name="credNo" type="text" id="credNo" value="${user.credNo}"/></td>
			</tr>
			<tr>
				<th class="required">性别</th>
				<td>
					<c:if test="${user.gender==0}">
						<input type="radio" name="gender" value="0" checked="checked"><label for="男">男</label>
						<input type="radio" name="gender" value="1" ><label for="女">女</label>
					</c:if>
					<c:if test="${user.gender==1}">
						<input type="radio" name="gender" value="0" ><label for="男">男</label>
						<input type="radio" name="gender" value="1" checked="checked"><label for="女">女</label>
					</c:if>
				</td>
				<th class="required">用户角色</th>
				<td>
					<c:forEach var="kv" items="${roles}" varStatus="status">
						<c:if test="${kv.value==user.role}">
							<input type="radio" name="role" checked="checked" value="${kv.value}"><label for="${kv.name}">${kv.name}</label>
						</c:if>
						<c:if test="${kv.value!=user.role}">
							<input type="radio" name="role" value="${kv.value}"><label for="${kv.name}">${kv.name}</label>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th class="required">所属单位</th>
				<td><input name="organization" type="text" id="organization" value="${user.organization}"/>
				</td>
			</tr>
			<tr>
				<th>出生日期</th>
				<td><input type="text" name="birthday" id="birthday" value="${user.birthday}" onClick="WdatePicker()" readonly="readonly"/></td>
				<th>电子邮件</th>
				<td><input name="email" type="text" id="email" value="${user.email}"/></td>
			</tr>
			<tr>
				<th>职称</th>
				<td><input name="zc" type="text" id="zc" value="${user.zc}"/></td>
				<th>职务</th>
				<td><input name="zw" type="text" id="zw" value="${user.zw}"/></td>
			</tr>
			<tr>
				<th>所属部门</th>
				<td><input name="dept" type="text" id="dept" value="${user.dept}"/></td>
				<th>学历</th>
				<td>
					<select class="selectpicker degrees-select" name="degrees" title="请选择" id="degrees">
						<option >大专</option>
						<option >本科</option>
						<option >硕士</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>专业</th>
				<td><input name="major" type="text" id="major" value="${user.major}"/></td>
				<th>电话</th>
				<td><input name="telephone" type="text" id="telephone" value="${user.telephone}"/></td>
			</tr>
			<tr>
				<th>手机</th>
				<td><input name="phone" type="text" id="phone" value="${user.phone}"/></td>
				<th>地址</th>
				<td><input name="address" type="text" id="address" value="${user.address}"/></td>
			</tr>
		</table>
	</form>
	<p class="save-btn">
		<button class="btn-red btn-size-big sport-user-update" data-type="${user_type}" type="button">保存</button>
		<button class="btn-wisteria btn-size-big sport-cancel-btn" data-type="${user_type}" type="button">取消</button>
	</p>
	<script src="<%=basePath %>/static/js/icheck/icheck.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/jsencrypt.min.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(function(){
			// 处理select不初始化的问题
			$(window).trigger("load");
			// 设置select的默认值
			$('.credType-select').selectpicker('val', "${user.credType}");
			// 设置select的默认值
			$('.degrees-select').selectpicker('val', "${user.degrees}");
			
			$('input').iCheck({
				checkboxClass: 'icheckbox_flat-blue',
				radioClass: 'iradio_flat-blue',
				increaseArea: '20%' // optional
			});
			
			jQuery.validator.addMethod("mobile", function(value, element) {
				var length = value.length;
				return this.optional(element) || (length == 11 && /^1\d{10}$/.test(value));
			});
			    
			jQuery.validator.addMethod("isTel", function(value, element) { 
				var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678 
				return this.optional(element) || (tel.test(value)); 
			}); 
			
			jQuery.validator.addMethod("stringCheck", function(value, element) { 
			     return this.optional(element) || /^[\u0391-\uFFE5\w]{1,100}$/.test(value); 
			});
			
			jQuery.validator.addMethod("nullableCheck", function(value, element) { 
			    if(Sport.isNull(value)){
			    	return true;
			    } 
				return this.optional(element) || /^[\u0391-\uFFE5\w]{1,40}$/.test(value); 
			}); 
			
			// 登录验证
			$(".sport-user-form").validate({
		        rules: {
		        	loginName:{
		                required: true,
		                minlength:4,
		                maxlength:20,
		                remote:{     
		                    type:"POST",
		                    url:"<%=basePath%>/user/update/check.action",
		                    data:{
		                    	loginName:function(){return $('#loginName').val()},
		                    	userId:function(){return $('#userId').val()},
		                    	_csrf:function(){return $("#csrdId").val()}
		                    }
		                 }
		            },
		            userName:{
		                required: true,
		                minlength:1,
		                maxlength:20
		            },
		            confirmPassword: {
		                required: true,
		                equalTo:"#password"
		            },
		            credNo:{
		            	required: true
		            },
		            organization:{
		            	required: true
		            },
		            email:{
		            	 required:false,
		                 email:true
		            },
		            zc:{
		            	nullableCheck:true
		            },
		            zw:{
		            	 nullableCheck:true
		            },
		            dept:{
		            	nullableCheck:true
		            },
		            major:{
		            	nullableCheck:true
		            },
		            telephone:{
		            	required:false,
		            	isTel:true
		            },
		            phone:{
		            	required:false,
		            	mobile:true
		            },
		            address:{
			             stringCheck:true
		            }
		        },
		        messages: {
		        	loginName:{
		                required: "请填写用户名",
		                minlength:'用户名在4-20个字符之间',
		                maxlength:'用户名在4-20个字符之间',
		                remote:"用户名已被占用"
		            },
		            userName:{
		                required: "请填写真实姓名",
		                minlength:'真实姓名在1-20个字符之间',
		                maxlength:'真实姓名在1-20个字符之间'
		            },
		            confirmPassword: {
		            	required : "确认密码不能为空",
		            	equalTo:'两次密码输入不一致,请重新输入'
		            },
		            credNo:{
		            	required: "请填写证件编号"
		            },
		            organization:{
		            	required: "请填写所属单位"
		            },
		            email:{
		                 email:"请填写正确的邮箱格式"
		            },
		            zc:{
		            	nullableCheck:"职称长度不能超过40个字符"
		            },
		            zw:{
		            	nullableCheck:"职务长度不能超过40个字符"
		            },
		            dept:{
		            	nullableCheck:"部门长度不能超过40个字符"
		            },
		            major:{
		            	nullableCheck:"专业长度不能超过40个字符"
		            },
		            telephone:{
		            	isTel:"请正确填写您的电话号码"
		            },
		            phone:{
		            	mobile:"手机号码格式错误"
		            },
		            address:{
		            	stringCheck:"只能包括中文字、英文字母、数字和下划线"
		            }
		        }
		     });
		});
	</script>
</div>
</body>
</html>