<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />密码修改</div>
	<div data-type="${user_type}"  class="returnBtn user-password-returnBtn">返回</div>
</div>
<div class="editBox">
	<form class="sport-pwd-update-form">
		<table class="editTable">
			<tr>
				<th class="required">旧密码</th>
				<td>
					<input name="oldPassword" type="password" id="oldPassword"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
					<input type="hidden" name="pubKey" value="${pubKey}" id="pubKey"/>
					<input type="hidden" name="uuid" value="${uuid}" id="uuid"/>
				</td>
			</tr>
			<tr>
				<th class="required">新密码</th>
				<td><input name="password" type="password" id="password"/></td>
			</tr>
			<tr>
				<th class="required">确认密码</th>
				<td><input name="confirmPassword" type="password"/></td>
			</tr>
			<tr>
				<th class="required">验证码</th>
				<td>
					<input name="verifCode" type="text" id="verifCode"/>
					<span class="hyz">
						<img class="yzm sport-yzm" src="<%=basePath %>/verifCode?_t=1" /><a class="sport-yzm-btn">换一张</a>
					</span>
				</td>
			</tr>
		</table>
	</form>
	<p class="save-btn">
		<button class="btn-red btn-size-big sport-password-updae-btn" data-type="${user_type}">保存</button>
		<button class="btn-wisteria btn-size-big">重置</button>
	</p>
<script src="<%=basePath %>/static/js/jsencrypt.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
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
		
		jQuery.validator.addMethod("notEqual", function(value, element, param) {
			return this.optional(element) || value != $(param).val();
		});
		
		$(".sport-pwd-update-form").validate({
	        rules: {
	           oldPassword: {
	                required: true,
	                remote:{     
	                    type:"POST",
	                    url:"<%=basePath%>/password/check.action",
	                    data:{
	                    	oldPassword:function(){
	                    			return Sport.getEntryptPwd($('#pubKey').val(),$('#oldPassword').val())
	                    	},
	                    	_csrf:function(){return $("#csrdId").val()}
	                    }
	                 }
		        },
	        	password: {
	                required: true,
	                minlength:4,
	                maxlength:16,
	                pwd:true,
	                notEqual:"#oldPassword"
	            },
	            confirmPassword: {
	                required: true,
	                equalTo:"#password"
	            },
	            verifCode:{
	            	required: true,
	            	remote:{     
	                    type:"POST",
	                    url:"<%=basePath%>/verifCode",
	                    data:{
	                    	verifCode:function(){
	                    			return $('#verifCode').val();
	                    	},
	                    	_csrf:function(){return $("#csrdId").val()}
	                    }
	                }
	            }
	        },
	        messages: {
	        	oldPassword:{
	                required: "请填写真实姓名",
	                remote:'旧密码不正确'
	            },
	            password: {
	            	 required:'请填写密码',
	            	 minlength:'密码在6-16个字符之间',
	                 maxlength:'密码在6-16个字符之间',
	            	 pwd:'必须由两种以上的大小写字母数字和特殊字符(空格除外)组成',
	            	 notEqual:"新密码不能和旧密码一样"
	            },
	            confirmPassword: {
	            	required : "确认密码不能为空",
	            	equalTo:'两次密码输入不一致,请重新输入'
	            },
	            verifCode:{
	            	required: "请填写验证码",
	            	remote:"验证码不正确"
	            }
	        }
	     });
	});
</script>
</div>
