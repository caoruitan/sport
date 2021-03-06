<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>国家体育总局科研项目申报系统-单位注册-第二步</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/my.select.css">
		<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
		<style type="text/css">
			body {
				background: #f2f2f2;
			}
		</style>
	</head>
	<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/yh.png" />设置管理员<span>所有加 * 的区域为必填项。</span></div>
		</div>
		<div class="editBox">
			<form class="sport-user-form sport-form">
				<input type="hidden" name="pubKey" value="${pubKey}" id="pubKey"/>
				<input type="hidden" name="uuid" value="${uuid}" id="uuid"/>
				<input type="hidden" name="userId" value="${user.userId}" id="userId"/>
				<input type="hidden" name="organization" value="${organization}" id="organization"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
				<div class="b-title">一、用户登录信息<span class="redfont">（登录名只能是数字字母下划线且大于4位小于20位）</span></div>
				<table class="editTable">
					<tr>
						<th class="required">登录名</th>
						<td colspan="3"><input name="loginName" value="${user.loginName}" type="text" id="loginName"/></td>
					</tr>
				</table>
				<div class="b-title">二、注册用户基本信息<span class="redfont">（计划业务管理人员将用此信息与您联系，请务必填写真实有效信息！）</span></div>
				<table class="editTable">
					<tr>
						<th class="required">姓名</th>
						<td><input name="userName" type="text" value="${user.userName}" id="userName"/></td>
						<th class="required">性别</th>
						<td>
							<select name="gender" id="gender" class="selectpicker gender-select" title="请选择" style="width: 300px;">
								<option value="0">男</option>
								<option value="1">女</option>
							</select>
							<span style="color:rgb(255, 102, 0);padding-left:10px;" class="gender-error"></span>
						</td>
					</tr>
					<tr>
						<th class="required">出生日期</th>
						<td><input type="text" name="birthday" value="${user.birthday}" id="birthday" onClick="WdatePicker()" readonly="readonly"/></td>
						<th class="required">电子邮箱</th>
						<td><input name="email" type="text" id="email" value="${user.email}"/></td>
					</tr>
					<tr>
						<th class="required">证件类型</th>
						<td>
							<select class="selectpicker credType-select" name="credType" title="请选择" id="credType">
								<c:forEach items="${creds}" var="dic">
									<option value="${dic.code}">${dic.name}</option>
								</c:forEach>
							</select>
							<span style="color:rgb(255, 102, 0);" class="credType-error"></span>
						</td>
						<th class="required">证件号码</th>
						<td><input name="credNo" type="text" id="credNo" value="${user.credNo}"/></td>
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
						<th class="required">所属部门</th>
						<td><input name="dept" type="text" id="dept" value="${user.dept}"/></td>
						<th >学历</th>
						<td>
							<select class="selectpicker" name="degrees" title="请选择" id="degrees">
								<c:forEach items="${degrees}" var="dic">
									<option value="${dic.code}">${dic.name}</option>
								</c:forEach>
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
					<tr>
						<td colspan="4">
							<p class="save-btn">
								<button class="btn-wisteria btn-size-big sport-create-manager-back" type="button" data-id="${user.organization}">上一步</button>
								<button class="btn-red btn-size-big sport-create-manager-update" type="button">提交</button>
							</p>
						</td>
					</tr>
				</table>
			</form>
		</div>
<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath %>/static/js/jsencrypt.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
<script>
	$(function(){
		// 处理select不初始化的问题
		$(window).trigger("load");
		// 设置select的默认值
		$('#gender').selectpicker('val', "${user.gender}");
		// 设置select的默认值
		$('.credType-select').selectpicker('val', "${user.credType}");
		// 设置select的默认值
		$('.degrees-select').selectpicker('val', "${user.degrees}");
		
		$('.zc-select').selectpicker('val', "${user.zc}");
		// 设置select的默认值
		$('.zw-select').selectpicker('val', "${user.zw}");
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
		
		jQuery.validator.addMethod("checkDept", function(value, element) { 
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
	            credNo:{
	            	required: true
	            },
	            organization:{
	            	required: true
	            },
	            birthday:{
	            	required: true
	            },
	            email:{
	            	 required:true,
	                 email:true
	            },
	            zc:{
	            	nullableCheck:true
	            },
	            zw:{
	            	nullableCheck:true
	            },
	            dept:{
	            	required:true,
	            	checkDept:true
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
	            credNo:{
	            	required: "请填写证件编号"
	            },
	            organization:{
	            	required: "请填写所属单位"
	            },
	            birthday:{
	            	required: "请填写出生日期"
	            },
	            email:{
	            	required: "请填写电子邮件", 
	            	email:"请填写正确的邮箱格式"
	            },
	            zc:{
	            	nullableCheck:"职称长度不能超过40个字符"
	            },
	            zw:{
	            	nullableCheck:"职务长度不能超过40个字符"
	            },
	            dept:{
	            	required: "请填写部门名称", 
	            	checkDept:"部门长度不能超过40个字符"
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
</body>
</html>