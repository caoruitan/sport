<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="titleBox">
	<div class="title"><img src="<%=basePath %>/static/img/yh.png" />密码重置<span>重置后，该用户的密码为 ${default_password}</span></div>
	<a href="javascript:history.go(-1)">
		<div class="returnBtn">返回</div>
	</a>
</div>
<div class="editBox">
	<table class="editTable">
		<tr>
			<th class="required">用户名</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th class="required">验证码</th>
			<td><input name="name" type="text" value="" />
				<span class="hyz">
					<img class="yzm sport-yzm" src="<%=basePath %>/verifCode?_t=1" /><a class="sport-yzm-btn">换一张</a>
				</span>
			</td>
		</tr>
	</table>
	<p class="save-btn">
		<button class="btn-red btn-size-big" type="">重置密码</button>
	</p>
</div>