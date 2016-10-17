<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据字典管理-修改</title>
		<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css"/>
		<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css"/>
		<script src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
		<link href="<%=basePath %>/static/css/flat/blue.css" rel="stylesheet"/>
		<script src="<%=basePath %>/static/js/icheck/icheck.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath %>/static/js/my97/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
		<style type="text/css">
			body {
				background: #F2F2F2;
			}
		</style>
	</head>

	<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/m-zd.png" />数据字典管理<span>所有加 * 的区域为必填项。</span></div>
			<div class="returnBtn dic-returnBtn">返回列表</div>
		</div>
		<div class="editBox">
			<form id="dic-update-form">
				<table class="editTable">
					<tr>
						<th class="required">分类</th>
						<td>
							<input name="typeName" type="text" value="${dicType.name }" readonly="readonly"/>
							<input type="hidden" name="pCode" value="${dic.pCode}" id="pCode"/>
							<input type="hidden" name="id" value="${dic.id}" id="dicId"/>
						</td>
					</tr>
					<tr>
						<th class="required">名称</th>
						<td>
							<input name="name" type="text" value="${dic.name}" id="name"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						</td>
					</tr>
					<tr>
						<th>值</th>
						<td><input name="value" type="text" value="${dic.value }" /></td>
					</tr>
					<tr>
						<th>描述</th>
						<td><textarea name="description" style="width:500px;">${dic.description}</textarea></td>
					</tr>
				</table>
			</form>
			<p class="save-btn">
				<button class="btn-red btn-size-big sport-dic-edit-btn">保存</button>
				<button class="btn-wisteria btn-size-big sport-dic-reset">重置</button>
			</p>
		</div>
<script>
	$(function(){
		$('input').iCheck({
			checkboxClass: 'icheckbox_flat-blue',
			radioClass: 'iradio_flat-blue',
			increaseArea: '20%' // optional
		});
		jQuery.validator.addMethod("descriptionCheck", function(value, element) { 
		    if(Sport.isNull(value)){
		    	return true;
		    } 
			return this.optional(element) || /^[\u0391-\uFFE5\w]{1,200}$/.test(value); 
		}); 
		
		jQuery.validator.addMethod("valueCheck", function(value, element) { 
		    if(Sport.isNull(value)){
		    	return true;
		    } 
			return this.optional(element) || /^[\u0391-\uFFE5\w]{1,40}$/.test(value); 
		}); 
		
		$("#dic-update-form").validate({
	        rules: {
	        	name:{
	                required: true,
	                maxlength:50,
	                remote:{     
	                    type:"POST",
	                    url:"<%=basePath%>/dic/update/check.action",
	                    data:{
	                    	name:function(){return $('#name').val()},
	                    	_csrf:function(){return $("#csrdId").val()},
	                    	dicId:function(){return $("#dicId").val()}
	                    }
	                 }
	            },
	            value:{
	            	valueCheck:true
	            },
	            description: {
	            	descriptionCheck:true
	            }
	        },
	        messages: {
	        	name:{
	                required: "请填写名称",
	                maxlength:'名称在1-50个字符之间',
	                remote:"名称已经存在"
	            },
	            value:{
	            	valueCheck:'值在1-20个字符之间'
	            },
	            description: {
	            	descriptionCheck:'描述在200个字符之间'
	            }
	        }
	     });
	})
</script>
	</body>
</html>