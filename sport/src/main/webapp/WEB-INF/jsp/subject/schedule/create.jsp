<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/layer/layer.js"></script>
		<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
		<title>进度新增</title>
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
			<form id="SchedulingForm">
				<table class="editTable">
					<tr>
						<th class="required">进度安排年月</th>
						<td>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="hidden" name="rwsId" value="${rwsId}"/>
							<input type="hidden" name="subjectId" value="${subjectId}"/>
							<input type="text" name="schTime" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th class="required"> 主要工作内容</th>
						<td><textarea name="work" cols="10" rows="4" style="width: 500px;margin-bottom:10px;height: 200px;" ></textarea></td>
					</tr>
					<tr>
						<th>目标</th>
						<td><textarea name="goal" cols="10" rows="4" style="width: 500px;height: 100px;" ></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>