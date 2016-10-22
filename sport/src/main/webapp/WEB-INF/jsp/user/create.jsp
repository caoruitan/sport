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
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />用户新增<span>所有加 * 的区域为必填项。</span></div>
    <div data-type="${user_type}" class="returnBtn user-returnBtn">返回列表</div>
</div>
<div class="editBox">
	<form class="sport-user-form sport-form">
		<input type="hidden" name="pubKey" value="${pubKey}" id="pubKey"/>
		<input type="hidden" name="uuid" value="${uuid}" id="uuid"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
		<table class="editTable">
			<tr>
				<th class="required">用户名</th>
				<td><input name="loginName" type="text" id="loginName"/></td>
				<th class="required">真实姓名</th>
				<td><input name="userName" type="text" id="userName"/></td>
			</tr>
			<tr>
				<th class="required">登录密码</th>
				<td><input name="password" type="password" id="password"/></td>
				<th class="required">证件类型</th>
				<td>
					<select class="selectpicker credType-select" name="credType" title="请选择" id="credType">
						<c:forEach items="${creds}" var="cred">
							<option value="${cred.code}">${cred.name}</option>
						</c:forEach>
					</select>
					<span style="color:rgb(255, 102, 0);padding-left:10px;" class="credType-error"></span>
				</td>
			</tr>
			<tr>
				<th class="required">确认密码</th>
				<td><input name="confirmPassword" type="password" /></td>
				<th class="required">证件号码</th>
				<td><input name="credNo" type="text" id="credNo"/></td>
			</tr>
			<tr>
				<th class="required">性别</th>
				<td>
					<input type="radio" name="gender" value="0" checked="checked"><label for="男">男</label>
					<input type="radio" name="gender" value="1" ><label for="女">女</label>
				</td>
				<th class="required">用户角色</th>
				<td>
					<c:forEach var="kv" items="${roles}" varStatus="status">
						<c:if test="${status.index==0 }">
							<input type="radio" name="role" checked="checked" value="${kv.value}"><label for="${kv.name}">${kv.name}</label>
						</c:if>
						<c:if test="${status.index!=0 }">
							<input type="radio" name="role" value="${kv.value}"><label for="${kv.name}">${kv.name}</label>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th class="required">所属单位</th>
				<td>
					<input name="organization" type="hidden" id="organization" value="${orgId}"/>
					<input name="orgName" type="text" id="orgName" value="${orgName}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>出生日期</th>
				<td><input type="text" name="birthday" id="birthday" onClick="WdatePicker()" readonly="readonly"/></td>
				<th>电子邮件</th>
				<td><input name="email" type="text" id="email"/></td>
			</tr>
			<tr>
				<th>职称</th>
				<td>
					<select class="selectpicker zc-select" name="zc" title="请选择职称" id="zc">
						<c:forEach items="${zcDics}" var="zc">
							<option value="${zc.code}">${zc.name}</option>
						</c:forEach>
					</select>
				</td>
				<th>职务</th>
				<td>
					<select class="selectpicker zw-select" name="zw" title="请选择职务" id="zw">
						<c:forEach items="${zwDics}" var="zw">
							<option value="${zw.code}">${zw.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>所属部门</th>
				<td><input name="dept" type="text" id="dept"/></td>
				<th>学历</th>
				<td>
					<select class="selectpicker" name="degrees" title="请选择" id="degrees">
						<c:forEach items="${degrees}" var="de">
							<option value="${de.code}">${de.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>专业</th>
				<td><input name="major" type="text" id="major"/></td>
				<th>电话</th>
				<td><input name="telephone" type="text" id="telephone"/></td>
			</tr>
			<tr>
				<th>手机</th>
				<td><input name="phone" type="text" id="phone"/></td>
				<th>地址</th>
				<td><input name="address" type="text" id="address"/></td>
			</tr>
		</table>
	</form>
	<p class="save-btn">
		<button class="btn-red btn-size-big sport-user-save" type="button" data-type="${user_type}">保存</button>
		<button class="btn-wisteria btn-size-big sport-rest-btn" type="button">重置</button>
	</p>
	<script src="<%=basePath %>/static/js/icheck/icheck.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>/static/js/jsencrypt.min.js" type="text/javascript" charset="utf-8"></script>
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
		                    url:"<%=basePath%>/user/create/check.action",
		                    data:{
		                    	loginName:function(){return $('#loginName').val()},
		                    	_csrf:function(){return $("#csrdId").val()}
		                    }
		                 }
		            },
		            userName:{
		                required: true,
		                minlength:1,
		                maxlength:20
		            },
		            password: {
		                required: true,
		                minlength:4,
		                maxlength:16,
		                pwd:true
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