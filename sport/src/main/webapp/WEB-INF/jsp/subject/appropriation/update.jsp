<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/css/sport.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/css/bootstrap.min.css" />
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/js/jquery.min1.10.1.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/js/sport.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/static/layer/layer.js"></script>
<script src="<%=basePath%>/static/js/my97/WdatePicker.js"
	type="text/javascript" charset="utf-8"></script>
<title>拨往其他单位经费情况填报</title>
<!--My97-->
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
		<form id="ApproForm">
			<table class="editTable">
				<tr>
					<th class="required">拨往单位</th>
					<td>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					<input type="hidden" name="approId" value="${approId}" /> 
					<input type="hidden" name="subjectId" value="${subjectId}" /> 
					<input type="hidden" name="sId" value="${ss.sId}" /> 
					<textarea name="work" cols="10" rows="4"
							style="width: 500px; margin-bottom: 10px; height: 200px;"></textarea>
					</td>
				</tr>
				<tr>
					<th class="required">拨付款额</th>
					<td><textarea name="work" cols="10" rows="4"
							style="width: 500px; margin-bottom: 10px; height: 200px;"></textarea></td>
				</tr>
				<tr>
					<th class="required">用途说明</th>
					<td><textarea name="work" cols="10" rows="4"
							style="width: 500px; margin-bottom: 10px; height: 200px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>