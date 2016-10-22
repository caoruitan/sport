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
		<title>国家体育总局科研项目申报系统-单位注册</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/my.select.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
		<script type="text/javascript" src="<%=basePath %>/static/js/jquery.min1.10.1.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/static/js/common.js"></script>
		<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
		<script type="text/javascript" src="<%=basePath %>/static/js/my97/WdatePicker.js" charset="utf-8"></script>
		<style type="text/css">
			body {
				background: #f2f2f2;
			}
		</style>
	</head>
	<body>
		<div class="layout_top">
			<div class="top_logo_register">单位注册</div>
			<div class="op">
				<a href="<%=basePath %>/login.htm">直接登录</a>
			</div>
		</div>
		<div class="editBox registBox">
			<div class="b-title">一、申报单位信息<span class="redfont">请先输入单位全称点击“检索”按钮进行单位检索</span></div>
			<form class="register-form">
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
			  <input type="hidden" name="orgId" value="${org.orgId}" id="orgId"/>
			  <table class="editTable registTable">
				<tr>
					<th class="required">单位全称</th>
					<td><input name="fullName" type="text" value="${org.fullName}" id="fullName"/></td>
					<th>英文名称</th>
					<td><input name="englishName" type="text" value="${org.englishName}" id="englishName"/></td>
				</tr>
				<tr>
					<th>单位简称</th>
					<td><input name="shortName" type="text" value="${org.shortName}" id="shortName"/></td>
					<th>单位主页</th>
					<td><input name="homepage" type="text" value="${org.homepage}" id="homepage"/></td>
				</tr>
				<tr>
					<th class="required">单位地址</th>
					<td colspan="3"><input name="address" style="width: 560px;" type="text" value="${org.address}" /></td>
				</tr>
				<tr>
					<th class="required">法人代表</th>
					<td><input name="legalLeader" type="text" value="${org.legalLeader}" /></td>
					<th class="required">所在地区</th>
					<td>
						<select name="region" id="loc_province" class="selectpicker" style="width:300px;" title="请选择" data-live-search="true">
							<c:forEach items="${address}" var="dic">
								<option value="${dic.code}">${dic.name}</option>
							</c:forEach>
						</select>
						<span style="color:rgb(255, 102, 0);" class="region-error"></span>
					</td>
				</tr>
				<tr>
					<th class="required">单位电话</th>
					<td><input name="telphone" type="text" value="${org.telphone}" /></td>
					<th class="required">单位传真</th>
					<td><input name="fax" type="text" value="${org.fax}" /></td>
				</tr>
				<tr>
					<th class="required">单位性质</th>
					<td>
						<select id="quality" name="quality" class="selectpicker" data-live-search="true" title="请选择" style="width: 300px;">
							<c:forEach items="${dics}" var="dic">
								<option value="${dic.code}">${dic.name}</option>
							</c:forEach>
						</select>
						<span style="color:rgb(255, 102, 0);" class="quality-error"></span>
					</td>
					<th class="required">电子邮箱</th>
					<td><input name="email" type="text" value="${org.email}" /></td>
				</tr>
				<tr>
					<th class="required">组织机构代码</th>
					<td><input name="codePre" type="text" value="${org.codePre}" style="width: 120px;margin-right: 10px;" /><span style="float: left;margin:6px 14px 0 0 ;">-</span><input name="codeSufix" style="width: 50px" type="text" value="${org.codeSufix}" /></td>
					<th class="required">邮政编码</th>
					<td><input name="post" type="text" value="${org.post}" /></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="3" class="redfont">
						组织机构代码指项目承担单位组织机构代码证上的标识代码，它是由全国组织机构代码管理中心所赋予的唯一法人标识代码
					</td>
				</tr>
			</table>
			<div class="b-title">二、业务负责人信息</div>
			<table class="editTable registTable">
				<tr>
					<th class="required">姓名</th>
					<td><input name="managerName" type="text" value="${org.managerName}" /></td>
					<th>移动电话</th>
					<td><input name="managerPhone" type="text" value="${org.managerPhone}" /></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><input name="managerTel" type="text" value="${org.managerTel}" /></td>
					<th>传真</th>
					<td><input name="managerfax" type="text" value="${org.managerfax}" /></td>
				</tr>
				<tr>
					<th>电子邮件</th>
					<td colspan="3"><input name="managerEmail" type="text" value="${org.managerEmail}" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<p class="save-btn">
							<button class="btn-red btn-size-big sport-regist-first-update" type="button">下一步</button>
							<button class="btn-wisteria btn-size-big">重置</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/plugin/location.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/layer/layer.js"></script>
<script type="text/javascript">
	$(function(){
		$(window).trigger("load");
		// 设置select的默认值
		$('#loc_province').selectpicker('val', "${org.region}");
		// 设置select的默认值
		$('#quality').selectpicker('val', "${org.quality}");
		jQuery.validator.addMethod("englishNameCheck", function(value, element) { 
		    if(Sport.isNull(value)){
		    	return true;
		    } 
			return this.optional(element) || /^(([\u4e00-\u9fff]{2,50})|([a-z\.\s\,]{2,50}))$/i.test(value); 
		});
		
		jQuery.validator.addMethod("shortNameCheck", function(value, element) { 
		    if(Sport.isNull(value)){
		    	return true;
		    } 
		    return this.optional(element) || /^[\u0391-\uFFE5\w]{1,40}$/.test(value); 
		}); 
		
		jQuery.validator.addMethod("homepageCheck", function(value, element) { 
		    if(Sport.isNull(value)){
		    	return true;
		    } 
		    return this.optional(element) || /^(https?):\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$/.test(value); 
		}); 
		
		jQuery.validator.addMethod("addressCheck", function(value, element) { 
		    return this.optional(element) || /^[\u4E00-\u9FA5A-Za-z\d\-\_]{5,60}$/.test(value); 
		}); 
		
		jQuery.validator.addMethod("legalLeaderCheck", function(value, element) { 
		    return this.optional(element) || /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/.test(value); 
		}); 
		
		jQuery.validator.addMethod("mobile", function(value, element) {
			var length = value.length;
			return this.optional(element) || (length == 11 && /^1\d{10}$/.test(value));
		});
		    
		jQuery.validator.addMethod("isTel", function(value, element) { 
			var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678 
			return this.optional(element) || (tel.test(value)); 
		}); 
		
		jQuery.validator.addMethod("isFax", function(value, element) { 
			var tel = /^(\d{3,4}-)?\d{7,8}$/; //电话号码格式010-12345678 
			return this.optional(element) || (tel.test(value)); 
		}); 
		
		jQuery.validator.addMethod("checkCodePre", function(value, element) { 
			var tel = /^([0-9A-Z]){8}$/;
			return this.optional(element) || (tel.test(value)); 
		}); 
		
		jQuery.validator.addMethod("checkCodeSufix", function(value, element) { 
			var tel = /^[0-9|X]$/;
			return this.optional(element) || (tel.test(value)); 
		}); 
		
		jQuery.validator.addMethod("checkPost", function(value, element) { 
			var tel = /^[1-9][0-9]{5}$/;
			return this.optional(element) || (tel.test(value)); 
		}); 
		
		// 登录验证
		$(".register-form").validate({
	        rules: {
	        	fullName:{
	                required: true,
	                maxlength:50,
	                remote:{     
	                    type:"POST",
	                    url:"<%=basePath%>/regist/fullname/update/check.action",
	                    data:{
	                    	fullName:function(){return $('#fullName').val()},
	                    	orgId:function(){return $('#orgId').val()},
	                    	_csrf:function(){return $("#csrdId").val()}
	                    }
	                 }
	            },
	            englishName:{
	            	englishNameCheck: true
	            },
	            shortName: {
	            	shortNameCheck: true
	            },
	            homepage: {
	            	homepageCheck: true
	            },
	            address:{
	            	required: true,
	            	addressCheck: true
	            },
	            legalLeader:{
	            	required: true,
	            	legalLeaderCheck:true
	            },
	            telphone:{
	            	required:true,
	            	isTel:true
	            },
	            fax:{
	            	required:true,
	            	isFax:true
	            },
	            email:{
	            	required:true,
	            	email:true
	            },
	            codePre:{
	            	required:true,
	            	checkCodePre:true
	            },
	            codeSufix:{
	            	required:true,
	            	checkCodeSufix:true
	            },
	            post:{
	            	required:true,
	            	checkPost:true
	            },
	            managerName:{
	            	required:true,
	            	legalLeaderCheck:true
	            },
	            managerPhone:{
	            	required:false,
	            	mobile:true
	            },
	            managerTel:{
	            	required:false,
	            	isTel:true
	            },
	            managerFax:{
	            	isFax:true
	            },
	            managerEmail:{
		             email:true
	            }
	        },
	        messages: {
	        	fullName:{
	                required: "请填写单位全称",
	                maxlength:'单位全称在50个字符以内',
	                remote:"单位已经注册"
	            },
	            englishName:{
	            	englishNameCheck: "单位英文名称格式不正确"
	            },
	            shortName: {
	            	 shortNameCheck:'单位简称格式不正确'
	            },
	            homepage: {
	            	homepageCheck : "公司主页格式不正确"
	            },
	            address:{
	            	required:"公司地址不能为空",
	            	addressCheck: "公司地址格式不正确"
	            },
	            legalLeader:{
	            	required: "法人代表不能为空",
	            	legalLeaderCheck:"法人代表格式不正确"
	            },
	            telphone:{
	            	required:"单位电话不能为空",
	            	isTel:"请正确填写单位电话"
	            },
	            fax:{
	            	required:"单位传真不能为空",
	            	isTel:"请正确填写单位传真"
	            },
	            email:{
	            	required:"单位邮箱不能为空",
	            	email:"请填写正确的邮箱格式"
	            },
	            code:{
	            	required:"组织机构代码不能为空",
	            	checkCode:"请填写正确组织机构代码"
	            },
	            codePre:{
	            	required:"组织机构代码不能为空",
	            	checkCodePre:"请填写正确组织机构代码"
	            },
	            codeSufix:{
	            	required:"组织机构代码不能为空",
	            	checkCodeSufix:"请填写正确组织机构代码"
	            },
	            post:{
	            	required:"邮政编码不能为空",
	            	checkPost:"请填写正确的邮政编码"
	            },
	            managerName:{
	            	required:"姓名不能为空",
	            	legalLeaderCheck:"请填写正确的姓名"
	            },
	            managerPhone:{
	            	mobile:"手机号码格式错误"
	            },
	            managerTel:{
	            	isTel:"请填写正确的电话号码"
	            },
	            managerFax:{
	            	isFax:"请填写正确的传真"
	            },
	            managerEmail:{
		             email:"请填写正确的邮箱"
	            }
	        }
	     });
	});
</script>
</body>
</html>