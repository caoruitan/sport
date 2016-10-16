<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />密码重置<span>重置后，该用户的密码为 ${default_password}</span></div>
	<div data-type="${user_type}" class="returnBtn user-index-returnBtn">返回</div>
</div>
<div class="editBox">
	<form id="reset-pwd-form">
		<table class="editTable">
			<tr>
				<th class="required">用户名</th>
				<td><input name="loginName" type="text" id="loginName"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
				</td>
			</tr>
			<tr>
				<th class="required">验证码</th>
				<td><input name="verifCode" type="text" id="verifCode"/>
					<span class="hyz">
						<img class="yzm sport-yzm" src="<%=basePath %>/verifCode?_t=1" /><a class="sport-yzm-btn">换一张</a>
					</span>
				</td>
			</tr>
		</table>
	</form>
	<p class="save-btn">
		<button class="btn-red btn-size-big sport-reset-pwd-btn" data-type="${user_type}">重置密码</button>
	</p>
<script type="text/javascript">
	$(function(){
		// 登录验证
		$("#reset-pwd-form").validate({
	        rules: {
	        	loginName:{
	                required: true,
	            },
	            verifCode:{
	                required: true
	            }
	        },
	        messages: {
	        	loginName:{
	                required: "请填写用户名"
	            },
	            verifCode:{
	                required: "请填写验证码"
	            }
	        }
	     });
	});
</script>
</div>