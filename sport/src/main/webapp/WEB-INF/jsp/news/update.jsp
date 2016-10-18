<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<title>新闻创建</title>
</head>
	<body>
		<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/m-news.png" />修改新闻<span>所有加 * 的区域为必填项。</span></div>
			<div class="returnBtn news-returnBtn">返回列表</div>
		</div>
		<div class="editBox">
			<form id="sport-news-form">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdToken"/>
				<input type="hidden" name="id" value="${news.id }" id="newsId"/>
				<table class="editTable">
					<tr>
						<th class="required">所属栏目</th>
						<td>
							<select name="column" class="selectpicker" title="请选择" id="news-column">
								<c:forEach items="${columns}" var="column">
									<option value="${column.key}">${column.value}</option>
								</c:forEach>
							</select>
							<span style="color:rgb(255, 102, 0);padding-left:10px;" class="column-error"></span>
						</td>
					</tr>
					<tr>
						<th class="required">标题</th>
						<td><input name="title" type="text" style="width: 600px;" id="title" value="${news.title}"/></td>
					</tr>
					<tr>
						<th class="required">内容</th>
						<td>
							<textarea class="ckeditor" id="content" name="content">${news.content}</textarea>
							<span style="color:rgb(255, 102, 0);" class="news-content-error"></span>
						</td>
					</tr>
					<tr>
						<th class="required">附件</th>
						<td>
	
							<div class="upload-file-wrap">
								<input type="file" class="file-uploader" name="fileName" id="FileUploader"/>
								<input type="hidden" class="file-uploader" name="fileId" id="newsFileId" value="${news.fileId}"/>
								<div class="upload-wrap">
									<input type="text" id="PathDisplayer" class="upload-text" value="${news.fileName}" disabled />
									<a href="javascript:void(0)" class="upload-btn"></a>
								</div>
							</div>
							<span style="color:rgb(255, 102, 0);" class="news-file-error"></span>
						</td>
					</tr>
				</table>
			</form>
			<p class="save-btn">
				<button class="btn-red btn-size-big sport-news-update-btn">保存</button>
				<button class="btn-wisteria btn-size-big sport-news-reset">重置</button>
			</p>
		</div>
<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/ckeditor/ckeditor.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/plugin/ubb.js"></script>
<script>
	$(function() {
		$(window).trigger("load");
		$('#news-column').selectpicker('val', "${news.columnId}");
		// 新闻验证
		$("#sport-news-form").validate({
	        rules: {
	        	title:{
	                required: true,
	                minlength:4,
	                maxlength:100
	            },
	            content:{
	            	newsContent: true
	            }
	        },
	        messages: {
	        	title:{
	                required: "请填写新闻标题",
	                minlength:'新闻标题在4-100个字符之间',
	                maxlength:'新闻标题在4-100个字符之间'
	            },
	            content:{
	            	newsContent: "请填写新闻内容"
	            }
	        }
	    });
		//监听上传按钮事件
		var fileUploader = document.getElementById('FileUploader');
		var pathDisplayer = document.getElementById('PathDisplayer');
		if (fileUploader.addEventListener) {
			fileUploader.addEventListener('change', fileUploaderChangeHandler, false);
		} else if (fileUploader.attachEvent) {
			fileUploader.attachEvent('onclick', fileUploaderClickHandler);
		} else {
			fileUploader.onchange = fileUploaderChangeHandler;
		}

		function fileUploaderChangeHandler() {
			pathDisplayer.value = fileUploader.value;
		}

		function fileUploaderClickHandler() {
			setTimeout(function() {
				fileUploaderChangeHandler();
			}, 0);
		}
		//上传按钮事件结束
		
	});
</script>
	</body>
</html>