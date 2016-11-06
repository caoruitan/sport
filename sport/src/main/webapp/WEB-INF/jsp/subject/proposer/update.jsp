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
                            <input name="name" type="text" value="${proposer.name}" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
                            <input type="hidden" name="subjectId" value="${proposer.subjectId}" />
                            <input type="hidden" name="sbsId" value="${proposer.sbsId}" />
                             <input type="hidden" name="id" value="${proposer.id}" />
                        </td>
            
            			<th class="required">性别</th>
            			<td>
            				<c:if test="${proposer.gender==0}">
        						<input type="radio" name="gender" value="0" checked="checked"><label for="男">男</label>
        						<input type="radio" name="gender" value="1" ><label for="女">女</label>
        					</c:if>
        					<c:if test="${proposer.gender==1}">
        						<input type="radio" name="gender" value="0" ><label for="男">男</label>
        						<input type="radio" name="gender" value="1" checked="checked"><label for="女">女</label>
        					</c:if>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">出生日期</th>
            		    <td><input type="text" name="birthday" id="birthday" value="${proposer.birthday}" onClick="WdatePicker()" readonly="readonly"/></td>
            			<th class="required">职务</th>
            			<td>
            				<select name="zw" class="selectpicker zw-select" title="请选择">
            					<c:forEach items="${zwDics}" var="zw">
            						<option value="${zw.code}">${zw.name}</option>
            					</c:forEach>
            				</select>
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
            			</td>
            			<th class="required">所学专业</th>
            			<td>
            				<input name="major" type="text" value="${proposer.major}"/>
            			</td>
            		</tr>
            		<tr>
            			<th class="required">所属单位</th>
            			<td><input name="org" type="text" value="${proposer.org}" /></td>
            			<th>电子邮件</th>
            			<td><input name="email" type="text" value="${proposer.email}"/></td>
            		</tr>
            		<tr>
            			<th>毕业院校</th>
            			<td><input name="university" type="text" value="${proposer.university}" /></td>
            			<th>电话</th>
            			<td><input name="phone" type="text" value="${proposer.phone}"/></td>
            		</tr>
            		<tr>
                        <th >学位</th>
                        <td ><input name="xuewei" type="text" value="${proposer.xuewei}" /></td>
                    </tr>
            		<tr>
                        <th class="required">研究分工</th>
                        <td colspan="3"><input name="work" type="text"  style="width: 560px;" value="${proposer.work}"/></td>
                    </tr>
                    <tr>
            			<th class="required">研究背景</th>
            			<td colspan="3"><input name="backdrop" type="text" style="width: 560px;" value="${proposer.backdrop}"/></td>
            		</tr>
            	</table>
            </form>
		</div>
		<script>
			$(function() {
				$(window).trigger("load");
				$('.degrees-select').selectpicker('val', "${proposer.degrees}");
				// 设置select的默认值
				$('.zw-select').selectpicker('val', "${proposer.zw}");
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-blue',
					radioClass: 'iradio_flat-blue',
					increaseArea: '20%' 
				});
			});
		</script>
	</body>

</html>