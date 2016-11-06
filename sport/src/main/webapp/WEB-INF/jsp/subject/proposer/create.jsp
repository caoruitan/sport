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
			<form id="sport-proposer-from">
                <table class="editTable">
                    <tr>
            			<th class="required">姓名</th>
            			<td>
                            <input name="name" type="text" value="" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
                            <input type="hidden" name="subjectId" value="${subjectId}" />
                            <input type="hidden" name="sbsId" value="${sbsId}" />
                        </td>
            			<th class="required">性别</th>
            			<td>
            				<input type="radio" name="gender" value="0" checked="checked"><label for="男">男</label>
            				<input type="radio" name="gender" value="1" ><label for="女">女</label>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">出生日期</th>
            		    <td><input type="text" name="birthday" id="birthday" onClick="WdatePicker()" readonly="readonly"/></td>
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
            			<th class="required">所学专业</th>
            			<td>
            				<input name="major" type="text" id="major"/>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">所属单位</th>
            			<td><input name="org" type="text" value="" /></td>
            			<th>电子邮件</th>
            			<td><input name="email" type="text" value="" /></td>
            		</tr>
            		<tr>
            			<th>毕业院校</th>
            			<td><input name="university" type="text" value="" /></td>
            			<th>电话</th>
            			<td><input name="phone" type="text" value="" /></td>
            		</tr>
            		<tr>
                        <th >学位</th>
                        <td ><input name="xuewei" type="text" value="" /></td>
                    </tr>
            		<tr>
                        <th class="required">研究分工</th>
                        <td colspan="3"><input name="work" type="text" value="" style="width: 560px;" /></td>
                    </tr>
                    <tr>
            			<th class="required">研究背景</th>
            			<td colspan="3"><input name="backdrop" type="text" value="" style="width: 560px;" /></td>
            		</tr>
            	</table>
            </form>
		</div>
		<script>
			$(function() {
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-blue',
					radioClass: 'iradio_flat-blue',
					increaseArea: '20%' 
				});
			});
		</script>
	</body>

</html>