<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- BEGIN META -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="your,keywords">
	<meta name="description" content="Short explanation about this website">
	<!-- END META -->
	<!-- BEGIN STYLESHEETS -->
	<link href="<%=basePath %>/static/css/base.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/animate.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>/static/css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<title>登录-国家体育总局科研项目申报系统</title>
</head>
<body>
		<div class="layout_top">
			<div class="top_logo">单位注册</div>
			<div class="op">
				<a href="01login.html">直接登录</a>
			</div>
		</div>
		<div class="editBox">
			<div class="b-title">一、申报单位信息<span class="redfont">请先输入单位全称点击“检索”按钮进行单位检索</span></div>

			<table class="editTable">
				<tr>
					<th class="required">单位全称</th>
					<td><input name="name" type="text" value="" /></td>
					<th>英文名称</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th>单位简称</th>
					<td><input name="name" type="text" value="" /></td>
					<th>单位主页</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th class="required">单位地址</th>
					<td colspan="3"><input name="name" style="width: 560px;" type="text" value="" /></td>
				</tr>
				<tr>
					<th class="required">法人代表</th>
					<td><input name="name" type="text" value="" /></td>
					<th class="required">所在地区</th>
					<td>
						<select id="lunch" class="selectpicker" data-live-search="true" title="请选择" style="width: 300px;">
							<option>请选择</option>
							<option>国家体育总局</option>
							<option>国家体育局射击队</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="required">单位电话</th>
					<td><input name="name" type="text" value="" /></td>
					<th class="required">单位传真</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th class="required">单位性质</th>
					<td>
						<select id="lunch" class="selectpicker" data-live-search="true" title="请选择" style="width: 300px;">
							<option>请选择</option>
							<option>国家体育总局</option>
							<option>国家体育局射击队</option>
						</select></td>
					<th class="required">电子邮箱</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th class="required">组织机构代码</th>
					<td><input name="name" type="text" value="" style="width: 120px;margin-right: 10px;" /><span style="float: left;margin:6px 14px 0 0 ;">-</span><input name="name" style="width: 50px" type="text" value="" /></td>
					<th class="required">邮政编码</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="3" class="redfont">
						组织机构代码指项目承担单位组织机构代码证上的标识代码，它是由全国组织机构代码管理中心所赋予的唯一法人标识代码
					</td>
				</tr>

			</table>

			<div class="b-title">二、业务负责人信息</div>

			<table class="editTable">
				<tr>
					<th class="required">姓名</th>
					<td><input name="name" type="text" value="" /></td>
					<th>移动电话</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><input name="name" type="text" value="" /></td>
					<th>传真</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th>电子邮件</th>
					<td colspan="3"><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<p class="save-btn">
							<a href="14zc-step2.html"><button class="btn-red btn-size-big" type="">下一步</button></a>
							<button class="btn-wisteria btn-size-big" type="">重置</button>
						</p>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>