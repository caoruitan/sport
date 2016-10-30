<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<title>新增申请人</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/square/red.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
	<script type="text/ecmascript" charset="utf-8" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/icheck/icheck.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/my97/WdatePicker.js"></script>
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
		
		.editBox {
			min-height: 400px;
		}
	</style>
	</head>
	<body>
		<div class="editBox">
			<table class="editTable">
				<tr>
					<th class="required">姓名</th>
					<td><input name="name" type="text" value="" /></td>

					<th class="required">性别</th>
					<td>
						<input id="男" type="radio" name="性别" checked=""><label for="男">男</label>
						<input id="女" type="radio" name="性别"><label for="女">女</label>
					</td>
				</tr>
				<tr>
					<th>出生日期</th>
					<td><input id="d11" type="text" onClick="WdatePicker()"/></td>
					<th class="required">职务</th>
					<td>
						<select name="zw" class="selectpicker" title="请选择">
							<c:forEach items="${zwDics}" var="zw">
    							<option value="${zw.code}">${zw.name}</option>
    						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th class="required">学历</th>
					<td>
						<select name="degrees" class="selectpicker" title="请选择">
    						<c:forEach items="${degrees}" var="de">
    							<option value="${de.code}">${de.name}</option>
    						</c:forEach>
						</select>
					</td>
					<th class="required">所学专业</th>
					<td>
						<select id="lunch" class="selectpicker" title="请选择">
							<option>请选择</option>
							<option>计算机</option>
							<option>体育</option>
							<option>心理学</option>
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
					<th>所属单位</th>
					<td><input name="name" type="text" value="" />
					</td>
					<th>Email</th>
					<td><input name="name" type="text" value="" />
					</td>
				</tr>
				<tr>
					<th>研究分工</th>
					<td colspan="3"><input name="name" type="text" value="" style="width: 560px;" /></td>
				</tr>
				<tr>
					<th>毕业院校</th>
					<td><input name="name" type="text" value="" /></td>
					<th>电话</th>
					<td><input name="name" type="text" value="" /></td>
				</tr>
				<tr>
					<th>研究背景</th>
					<td colspan="3"><input name="name" type="text" value="" style="width: 560px;" /></td>
				</tr>
			</table>
		</div>
		<script>
			$(function() {
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-blue',
					radioClass: 'iradio_flat-blue',
					increaseArea: '20%' // optional
				});
				alert(111);
			});
		</script>
	</body>

</html>