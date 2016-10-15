<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="titleBox">
	<div class="title"><img src="../img/yh.png" />密码修改</div>
	<a href="javascript:history.go(-1)">
		<div class="returnBtn">返回</div>
	</a>
</div>
<div class="editBox">
	<table class="editTable">
		<tr>
			<th class="required">旧密码</th>
			<td><input name="name" type="password" value="" /></td>
		</tr>
		<tr>
			<th class="required">新密码</th>
			<td><input name="name" type="password" value="" /></td>
		</tr>
		<tr>
			<th class="required">确认密码</th>
			<td><input name="name" type="password" value="" /></td>
		</tr>
		<tr>
			<th class="required">验证码</th>
			<td><input name="name" type="text" value="" /><span class="hyz"><img class="yzm" src="../img/yzm.png" /><a>换一张</a></span></td>
		</tr>
	</table>
	<p class="save-btn">
		<button class="btn-red btn-size-big" type="">保存</button>
		<button class="btn-wisteria btn-size-big" type="">重置</button>
	</p>
</div>
