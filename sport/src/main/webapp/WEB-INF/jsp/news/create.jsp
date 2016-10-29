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
			<div class="title"><img src="<%=basePath %>/static/img/m-news.png" />新闻创建<span>所有加 * 的区域为必填项。</span></div>
			<div class="returnBtn news-returnBtn">返回列表</div>
		</div>
		<div class="editBox">
			<form id="sport-news-form">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdToken"/>
				<table class="editTable">
					<tr>
						<th class="required">所属栏目</th>
						<td>
							<select name="columnId" class="selectpicker" title="请选择" id="news-column">
								<c:forEach items="${columns}" var="column">
									<option value="${column.key}">${column.value}</option>
								</c:forEach>
							</select>
							<span style="color:rgb(255, 102, 0);padding-left:10px;" class="column-error"></span>
						</td>
					</tr>
					<tr>
						<th class="required">标题</th>
						<td><input name="title" type="text" value="" style="width: 600px;" id="title"/></td>
					</tr>
					<tr>
						<th class="required">内容</th>
						<td>
							<textarea class="ckeditor" id="content"></textarea>
							<input name="content" type="hidden" id="contentContainer">
							<span style="color:rgb(255, 102, 0);" class="news-content-error"></span>
						</td>
					</tr>
					<tr>
						<th class="required">附件</th>
						<td>
							<div style="float:right;height:40px;padding-top:15px;color:red;">附件格式:jpg,gif,bmp,png,jpeg,txt,doc,docx,excel,ppt,pdf等,大小不能超过2M.</div>
							<div class="upload-file-wrap" style="float:left;width:100px;">
								<input type="file" class="file-uploader" id="FileUploader" />
								<div class="upload-wrap">
									<a href="javascript:void(0)" class="upload-btn"></a>
								</div>
							</div>
							<div style="clear:both"></div>
							<div class="news-file-container"></div>
							<span style="color:rgb(255, 102, 0);" class="news-file-error"></span>
						</td>
					</tr>
				</table>
			</form>
			<p class="save-btn">
				<button class="btn-red btn-size-big sport-news-create-btn" type="">保存</button>
				<button class="btn-wisteria btn-size-big sport-news-reset">重置</button>
			</p>
		</div>
<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/ckeditor/ckeditor.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/plugin/ubb.js"></script>
<script type="text/ecmascript" src="<%=basePath %>/static/js/plugin/ajaxfileupload.js"></script>
<script>
	$(function() {
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
		
	  $(document).on("click",".delete-file",function(){
	        $(this).parent().siblings().each(function(){
	             var index = $(this).attr("data-index");
	             index= index<=0?1:index;
	             $(this).find('input[name="files['+index+'].id"]').attr('name','files['+(index-1)+'].id');
	             $(this).find('input[name="files['+index+'].name"]').attr('name','files['+(index-1)+'].name');
	             $(this).find('input[name="files['+index+'].path"]').attr('name','files['+(index-1)+'].path');
	        });
	        $(this).parent().remove();
	    });
		
		new AjaxUpload('FileUploader', {
             action: Sport.getBasePath() + '/news/kjsadmin/upload.action?_csrf='+$("#csrdToken").val(),
             name : 'file',
             data : {},
             autoSubmit: true,
             onSubmit:function(file,text){
            	 $(".news-file-error").text("");
            	 var exp = /.jpg$|.gif$|.bmp$|.png$|.jpeg$|.txt$|.doc$|.docx$|.excel$|.ppt$|.pdf$/;
                 if (exp.exec(file) == null) {
                     $(".news-file-error").text("文件格式不正确!");
                	 return false;
                 }
                 return true;
             },
             onComplete: function(file, response) {
            	 var data = $.parseJSON($(response).text());
            	 if(data.size>2 * 1024 * 1024 ){
            		 $(".news-file-error").text("文件不能大于2M!");
            	 }
            	 
            	 if(data.success){
            	 	var len = $(".news-file-container").find(".sport-news-file").length;
            	 	len = len<=0?0:len;
            	 	var id = $('<input type="hidden" name="files['+(len)+'].id" value="'+data.id+'"/>');
            	 	var name = $('<input type="hidden" name="files['+(len)+'].name" value="'+data.name+'"/>');
            	 	var path = $('<input type="hidden" name="files['+(len)+'].path" value="'+data.path+'"/>');
            	 	var text = $('<span style="text-align:left;display:inline-block;width:90%;line-height: 25px;color:#1e5494;">'+data.name+'</span>')
            	 	var span = $('<span style="text-align:right;display:inline-block;width:8%;line-height: 25px;color:#1e5494;cursor:pointer;" class="delete-file">删除</span>')
            	  	var $a = $('<div href="javascript:void(0)" class="sport-news-file" data-index="'+(len)+'"></div>');
                    $a.append(id).append(text).append(name).append(path).append(span);
                    $(".news-file-container").append($a);
            	 }
             }
         });
	});
</script>
	</body>
</html>