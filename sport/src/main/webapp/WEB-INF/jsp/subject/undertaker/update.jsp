<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<title>修改项目人员</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/square/red.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
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
			<form id="sport-proposer-from">
                <table class="editTable">
                    <tr>
            			<th class="required">姓名</th>
            			<td>
                            <input name="name" type="text" value="${undertaker.name}" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="subjectId" value="${undertaker.subjectId}" />
                            <input type="hidden" name="rwsId" value="${undertaker.rwsId}" />
                            <input type="hidden" name="id" value="${undertaker.id}" />
                        </td>
            		</tr>
            		<tr>
            			<th class="required">所属单位</th>
            			<td><input name="org" type="text" value="${undertaker.org}" /></td>
            		</tr>
            		<tr>
            			<th>年龄</th>
            		    <td><input type="text" name="age" value="${undertaker.age}"/></td>
            		</tr>
            		<tr>
            			<th class="required">职务</th>
            			<td>
            				<select name="zw" class="selectpicker zw-select" title="请选择">
            					<c:forEach items="${zwDics}" var="zw">
            						<option value="${zw.code}">${zw.name}</option>
            					</c:forEach>
            				</select>
                            &nbsp;&nbsp;&nbsp;<span style="color:rgb(255, 102, 0);" class="zw-error"></span>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">学历</th>
            			<td>
            				<select name="degrees" class="selectpicker degrees-select" title="请选择">
            					<c:forEach items="${degrees}" var="de">
            						<option value="${de.code}">${de.name}</option>
            					</c:forEach>
            				</select>
                             &nbsp;&nbsp;&nbsp;<span style="color:rgb(255, 102, 0);" class="degrees-error"></span>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">专业</th>
            			<td>
            				<input name="major" type="text" id="major" value="${undertaker.major}"/>
            			</td>
            		</tr>
            		<tr>
                        <th class="required">分工</th>
                        <td colspan="3"><input name="work" type="text" value="${undertaker.work}" /></td>
                    </tr>
                    <tr>
            			<th class="required">类型</th>
            			<td colspan="3"><input name="type" type="text" value="${undertaker.type}" /></td>
            		</tr>
            	</table>
            </form>
		</div>
		<script>
			$(function() {
				$(window).trigger("load");
				$('.degrees-select').selectpicker('val', "${undertaker.degrees}");
				// 设置select的默认值
				$('.zw-select').selectpicker('val', "${undertaker.zw}");
			});
		</script>
	</body>
</html>