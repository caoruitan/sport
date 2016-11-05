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
	<title>拨往其他单位经费情况填报</title>
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
		
		.editBox {
			min-height: 200px;
		}
		.editTable th{
			width: 300px;
		}
	</style>
</head>
<body>
	<div class="editBox">
		<form id="ApproForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="rwsId" value="${rwsId}" />
		    <input type="hidden" name="subjectId" value="${subjectId}" /> 
			<table class="editTable">
				<tr>
					<th class="required">拨往单位</th>
					<td><input name="gainOrg" type="text"/></td>
				</tr>
				<tr>
					<th class="required"> （单位：万元）拨款数额</th>
					<td><input name="approAmount" type="text" value="" style="width: 60px;"  />
					</td>
				</tr>
				<tr>
					<th>用途说明</th>
					<td><textarea name="describe" cols="10" rows="4" style="width: 500px;height: 100px;" type="text" value="" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>