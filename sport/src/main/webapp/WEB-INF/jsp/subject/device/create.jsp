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
		<title>设备新增</title>
		<!--My97-->
		<style type="text/css">
			body {
				background: #F2F2F2;
			}
			
			.editBox {
				min-height: 400px;
			}
			.editTable th{
				width: 300px;
			}
		</style>
	</head>
	<body>
		<div class="editBox">
			<form id="deviceForm">
				<table class="editTable">
					<tr>
						<th class="required">设备购置名称</th>
						<td>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="hidden" name="rwsId" value="${rwsId}"/>
							<input type="hidden" name="subjectId" value="${subjectId}"/>
							<input name="name" type="text" value=""  style="width: 360px;" />
						</td>
					</tr>
					<tr>
						<th>购置或试制的理由、用途</th>
						<td><input name="purpose" type="text" value=""  style="width: 360px;" />
						</td>
					</tr>
					<tr>
						<th>购置设备型号及主要技术性能指标</th>
						<td><input name="norm" type="text" value=""  style="width: 360px;" />
					</tr>
					<tr>
						<th>购置或试制</th>
						<td><input name="buy" type="text" value=""  style="width: 360px;" />
						</td>
					</tr>
					<tr>
						<th class="required">（单位：万元）单价</th>
						<td><input name="price" class="device-price" type="text" value="" style="width: 60px;"  />
						</td>
					</tr>
					<tr>
						<th class="required">（单位：台）数量</th>
						<td><input name="num" class="device-num"  type="text" value="" style="width: 60px;" /></td>
					</tr>
					<tr>
						<th>（由单价与数量计算得出）总价</th>
						<td><input name="total" class="device-total" type="text" value="" style="width: 360px;" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>申请从专项经费中列支数</th>
						<td><input name="slzs" type="text" value="" style="width: 360px;"/></td>
					</tr>
					<tr>
						<th>购置设备生产国别与地区</th>
						<td><input name="orgin" type="text" value="" style="width: 360px;" /></td>
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".device-price").on("blur",function(){
					var v = $(this).val();
					v = v==""?"0":v;
					v = parseFloat(v);
					var n = $(".device-num").val();
					n = n==""?"0":n;
					n = parseFloat(n);
					var r = v*n 
					$(".device-total").val(isNaN(r)?0:r);
				});
				
				$(".device-num").on("blur",function(){
					var v = $(".device-price").val();
					v = v==""?"0":v;
					v = parseFloat(v);
					var n = $(this).val();
					n = n==""?"0":n;
					n = parseFloat(n);
					var r = v*n 
					$(".device-total").val(isNaN(r)?0:r);
				});
			})
		
		</script>
	</body>
</html>