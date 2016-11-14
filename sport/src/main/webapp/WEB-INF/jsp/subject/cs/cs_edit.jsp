<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="ktview">
	<div class="kt-left">
		<div class="lc">
			<div class="line">
				<li class="ok b-active" id="e-zc" onclick="showLayout('b-zc')" >
					<a title="注册">注册</a>
				</li>
				<li class="ok" id="e-txsm" onclick="showLayout('b-txsm')" >
					<a title="填写说明">填写说明</a>
				</li>
				<li class="dot" id="e-sbsfm" onclick="showLayout('b-sbsfm')" >
					<a title="申报书封面">上传附件</a>
				</li>
			</div>
		</div>
	</div>
	<div class="kt-right">
		<div class="box" style="height:400px;display: block; background: url(<%=basePath %>/static/img/welcome.png) center center no-repeat;">
		</div>
		<!--注册-->
		<div class="box b-zc">
			<div class="t">注册</div>
			<div class="c">
				<div class="centerBox">
					<img src="<%=basePath %>/static/img/success.png" />
					<p>已注册成功，请继续下一步</p>
				</div>
			</div>
		</div>
		<!--填写说明-->
		<div class="box b-txsm">
			<div class="t">填写说明</div>
			<div class="c contentTxt">
				${news.content}
			</div>
		</div>
		<!--申报书封面-->
		<div class="box b-sbsfm wow bounceInDown">
			<div class="t">上传附件</div>
			<div class="c">
				<div class="editBox">
					<form id="csFileForm">
						<table class="editTable">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdToken"/>
							<input type="hidden" name="subjectId" value="${sc.subjectId }"/>
							<input type="hidden" name="conclusionId" value="${sc.conclusionId }"/>
							<th class="required">附件</th>
							<td style="width:100%;">
								<div class="upload-file-wrap" style="float:left;width:100px;">
									<input type="file" class="file-uploader" id="FileUploader" />
									<div class="upload-wrap">
										<a href="javascript:void(0)" class="upload-btn"></a>
									</div>
								</div>
								<div style="clear:both"></div>
								<div class="news-file-container">
									<c:forEach items="${sas}" var="file" varStatus="status">
										<div class="sport-news-file" data-index="${status.index}">
											<input type="hidden" name="files[${status.index}].id" value="${file.fileId}"/>
						            	 	<input type="hidden" name="files[${status.index}].name" value="${file.fileName}"/>
						            	 	<input type="hidden" name="files[${status.index}].path" value="${file.path}"/>
						            	 	<span style="text-align:left;display:inline-block;width:90%;line-height: 25px;color:#1e5494;">${file.fileName}</span>
						            	 	<span style="text-align:right;display:inline-block;width:8%;line-height: 25px;color:#1e5494;cursor:pointer;" class="delete-file">删除</span>
										</div>
									</c:forEach>
								</div>
								<span style="color:rgb(255, 102, 0);" class="news-file-error"></span>
								<p class="save-btn">
									<button id="fileFormSubmit" class="btn-red btn-size-big" type="button" onclick="saveFile()">保存</button>
									<button class="btn-wisteria btn-size-big" onclick="reset('csFileForm')">重置</button>
								</p>
							</td>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">		
	var showLayout = function(obj) {
		$(".box").hide();
		$('.' + obj).slideDown();
	}
	
	var reset = function(formname) {
		$("#" + formname)[0].reset();
	}
	
	var saveFile = function() {
		$('#fileFormSubmit').attr("disabled",true);
		$('#fileFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveCsFile.action",
			type: "POST",
			dataType: "JSON",
			data: $("#csFileForm").serialize(),
			error: function (obj) {
				$('#fileFormSubmit').removeAttr("disabled");
				$("#fileFormSubmit").text("保存");
				layer.msg("保存失败，请稍后重试！");
			},
			success: function (obj) {
				$('#fileFormSubmit').removeAttr("disabled");
				$("#fileFormSubmit").text("保存");
				layer.msg("保存成功！");
			}
		});
	}
	
	$(function() {
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
	             action: '<%=basePath %>/subject/sboper/upload.action?_csrf='+$("#csrdToken").val(),
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
</div>