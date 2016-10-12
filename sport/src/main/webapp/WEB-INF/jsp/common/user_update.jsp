<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="titleBox">
	<div class="title"><img src="../img/yh.png" />用户新增<span>所有加 * 的区域为必填项。</span></div>
	<a href="javascript:history.go(-1)">
		<div class="returnBtn">返回列表</div>
	</a>
</div>
<div class="editBox">
	<table class="editTable">
		<tr>
			<th class="required">用户名</th>
			<td><input name="name" type="text" value="" /></td>
			<th class="required">真实姓名</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th class="required">登录密码</th>
			<td><input name="name" type="text" value="" /></td>
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
			<td><input name="name" type="text" value="" /></td>
			<th class="required">证件号码</th>
			<td><input name="name" type="text" value="" /></td>
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
			<td><input name="name" type="text" value="" />
			</td>
		</tr>
		<tr>
			<th>出生日期</th>
			<td><input id="d11" type="text" onClick="WdatePicker()"/></td>
			<th>电子邮件</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th>职称</th>
			<td><input name="name" type="text" value="" /></td>
			<th>职务</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th>所属部门</th>
			<td><input name="name" type="text" value="" /></td>
			<th>学历</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th>专业</th>
			<td><input name="name" type="text" value="" /></td>
			<th>电话</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
		<tr>
			<th>手机</th>
			<td><input name="name" type="text" value="" /></td>
			<th>地址</th>
			<td><input name="name" type="text" value="" /></td>
		</tr>
	</table>
	<p class="save-btn">
		<button class="btn-red btn-size-big" type="">保存</button>
		<button class="btn-wisteria btn-size-big" type="">重置</button>
	</p>
</div>