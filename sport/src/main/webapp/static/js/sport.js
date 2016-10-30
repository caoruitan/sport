$.extend({
    StandardPost:function(url,args){
    	var name = $("#csrfToken").attr("name");
        var value = $("#csrfToken").val();
        var body = $(document.body),
            form = $("<form method='post'></form>"),
            input;
        form.attr({"action":url});
        if(args){
        	$.each(args,function(key,value){
        		 input = $("<input type='hidden'>");
                 input.attr({"name":key});
                 input.val(value);
                 form.append(input);
        	});
        }
        $input = $("<input type='hidden'>");
        $input.attr({"name":name});
        $input.val(value);
        form.append($input);
        form.appendTo(document.body);
        form.submit();
        document.body.removeChild(form[0]);
    }
});
var Sport = {
	name : "DOMAIN_UN",
	Cookie : {
		set:function(name,value,time){
	         var exp  = new Date();  
	         exp.setTime(exp.getTime() + time*24*60*60*1000);  
	         document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	    },
	    remove:function(name){
	        var date=new Date(); 
	        date.setTime(date.getTime()-10000);
	        document.cookie=name+"=v; expires="+date.toGMTString(); 
	    },
	    get:function(name){
	         var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	         if(arr=document.cookie.match(reg))
	             return unescape(arr[2]);
	         else
	             return null;
	    },
	    update:function(name,value,time){
	        var cookieString=name+"="+escape(value); 
	        if(time>0){ 
	          var date=new Date(); 
	          date.setTime(date.getTime+time*3600*1000); //单位是多少小时后失效
	          cookieString=cookieString+"; expires="+date.toGMTString(); 
	        } 
	          document.cookie=cookieString;
	    }
	},
	Header : {
		getProtocol : function() {
			var parentScheme = '';
			try {
				parentScheme = parent.location.protocol;
			} catch (e) {
				parentScheme = LoginConstant.HTTP_SCHEME;
			}
			if (!parentScheme) {
				parentScheme = LoginConstant.HTTP_SCHEME;
			}
			return parentScheme;
		}
	},
	getEntryptPwd : function(pubKey, pwd) {
		if (!pwd || !pubKey) {
			return pwd;
		}
		var encrypt = new JSEncrypt();
		encrypt.setPublicKey(pubKey);
		return encrypt.encrypt(pwd);
	},
	getBasePath:function(){
		var location = (window.location+'').split('/'); 
		return location[0]+'//'+location[2]+'/'+location[3]; 
	},
	isNull:function(s){
        if(s == null || s == undefined){
            return true;
        }
        s = $.trim(s);
        if(s == "null"|| s == "undefined" || s == "" || s == " "){
            return true;
        }
        return false;
    },
    updateChange:function($pathDisplayer){
    	fileName = $("#FileUploader").val();
    	$pathDisplayer.val(fileName);
    },
    createOrg:function(fromCls,btnCls,ajaxUrl,returnUrl,operName){
    	var region = $("#loc_province").val();
		var regionFlag = Sport.isNull(region);
		if(regionFlag){
			$(".region-error").text("请选择地区");
		}else{
			$(".region-error").text("");
		}
		
		var quality = $("#quality").val();
		var qualityFlag = Sport.isNull(quality);
		if(qualityFlag){
			$(".quality-error").text("请选择单位性质");
		}else{
			$(".quality-error").text("");
		}
		if($("."+fromCls).valid() && !regionFlag && !qualityFlag){
			$('.'+btnCls).text("提交中...");
			$('.'+btnCls).attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+ajaxUrl,
				type: "POST",
				//dataType: "JSON",
				data: $("."+fromCls).serialize(),
				error: function () {
					$('.'+btnCls).removeAttr("disabled");
					$('.'+btnCls).text("保存");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj){
						layer.msg(operName+"成功!");
						window.location.href = Sport.getBasePath()+returnUrl+"?orgId="+obj;
					}else{
						layer.msg(operName+"失败!");
					}
					$('.'+btnCls).removeAttr("disabled");
					$('.'+btnCls).text("保存");
				}
			})
		}
    },
    createManager:function(fromCls,btnCls,ajaxUrl,returnUrl,operName){
    	// 证书校验
		var credType = $(".credType-select").val();
		var credFlag = Sport.isNull(credType);
		if(credFlag){
			$(".credType-error").text("请选择证件类型");
		}else{
			$(".credType-error").text("");
		}
		// 性别校验
		var gender = $(".gender-select").val();
		var genderFlag = Sport.isNull(gender);
		if(genderFlag){
			$(".gender-error").text("请选择性别");
		}else{
			$(".gender-error").text("");
		}
		
		// 验证通过
		if($("."+fromCls).valid() && !credFlag && !genderFlag){
			$('.'+btnCls).text("提交中...");
			$('.'+btnCls).attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+ajaxUrl,
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userId:$('#userId').val(),
					userName:$('#userName').val(),
					loginName:$('#loginName').val(),
					credType:$('#credType').val(),
					credNo:$('#credNo').val(),
					gender:$('#gender').val(),
					role:$('input[name="role"]:checked').val(),
					organization:$('#organization').val(),
					birthday:$('#birthday').val(),
					zc:$('#zc').val(),
					zw:$('#zw').val(),
					dept:$('#dept').val(),
					major:$('#major').val(),
					email:$('#email').val(),
					telephone:$('#telephone').val(),
					phone:$('#phone').val(),
					address:$('#address').val(),
					degrees:$("#degrees").val(),
					password:Sport.getEntryptPwd($('#pubKey').val(),$('#password').val())
				},
				error: function () {
					$('.'+btnCls).removeAttr("disabled");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj.success){
						layer.msg(operName+"成功!");
						window.location.href = Sport.getBasePath()+returnUrl;
					}else{
						layer.msg(obj.msg);
					}
					$('.'+btnCls).removeAttr("disabled");
					$('.'+btnCls).text("保存");
				}
			})
		}
    }
};

$(function(){
	// 记住登录名
	var name = Sport.Cookie.get(Sport.name);
//	if(name){
//		$("#loginName").val(name);
//	}
	// 登录验证
	$("#loginForm").validate({
        rules: {
        	loginName:{
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
        	loginName:{
                required: "用户名不能为空",
            },
            password: {
            	required : "密码不能为空"
            }
        }
     });
	
	// 登录操作
	$(document).on("click","#loginBtn",function(){
		var uuid = $("#uuid").val();
		if($("#loginForm").valid()){
			$('#loginBtn').attr("disabled",true);
			$('#loginBtn').text('正在登录...');
			$.ajax({
				url: Sport.getBasePath()+"/doLogin",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					return_url:$('#return_url').val(),
					_csrf:$("#csrdId").val(),
					loginName:$('#loginName').val(),
					password:Sport.getEntryptPwd($('#pubKey').val(),$('#password').val())
				},
				error: function () {
					$('#loginBtn').removeAttr("disabled");
				},
				success: function (obj) {
					$('#loginBtn').removeAttr("disabled");
					if (obj) {
						if (obj.success) {
							Sport.Cookie.set(Sport.name, $('#loginName').val(), 7);
							var isIE = !-[1,];
							var url = obj.url
							if(url.indexOf("https://")==-1 && url.indexOf("http://")==-1){
								url = Sport.getBasePath()+"/"+obj.url;
							}
							if (isIE) {
								var link = document.createElement("a");
								link.href =url;
								link.style.display = 'none';
								document.body.appendChild(link);
								link.click();
							} else {
								window.location = url;
							}
							return;
						}else{
							$("#errorMsg").text(obj.msg);
							$("#errorMsg").show();
						}
					}
					$("#loginBtn").text("登&nbsp;&nbsp;&nbsp;&nbsp;录");
				}
			});
		}
	}).on("click","a.sport-logout",function(){
		$.StandardPost(Sport.getBasePath()+"/logout");
	}).on("click","button.sport-rest-btn",function(){
		$(".sport-form")[0].reset();
	})
	
	// 数据字典页面加载
	$(document).on("click",".sport-dic-menu",function(){
		window.location.href = Sport.getBasePath()+"/dic/list.htm";
	}).on("click",".sport-dic-create-btnskip",function(){
		var zTree=$.fn.zTree.getZTreeObj("dicTreeDiv");
        nodes=zTree.getSelectedNodes(true);
		window.location.href = Sport.getBasePath()+"/dic/create.htm?pCode="+nodes[0].code;
	}).on("click",".dic-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/dic/list.htm";
	}).on("click",".sport-dic-reset",function(){
		$("#dic-create-form")[0].reset();
	}).on("click",".sport-dic-create-btn",function(){
		if($("#dic-create-form").valid()){
			$('.sport-dic-create-btn').attr("disabled",true);
			$('.sport-dic-create-btn').text('正在提交...');
			$.ajax({
				url: Sport.getBasePath()+"/dic/create.action",
				type: "POST",
				dataType: "JSON",
				data: $("#dic-create-form").serialize(),
				error: function () {
					$('.sport-dic-create-btn').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试！");
					$(".sport-dic-create-btn").text("保存");
				},
				success: function (obj) {
					$('.sport-dic-create-btn').removeAttr("disabled");
					if(obj){
						window.location.href = Sport.getBasePath()+"/dic/list.htm?pCode="+$("#pCode").val();
					}else{
						layer.msg("保存失败，请稍后重试！");
					}
					$(".sport-dic-create-btn").text("保存");
				}
			});
		};
	}).on("click",".sport-dic-edit",function(){
		window.location.href = Sport.getBasePath()+"/dic/update.htm?dicId="+$(this).attr("data-id");
	}).on("click",".sport-dic-edit-btn",function(){
		if($("#dic-update-form").valid()){
			$('.sport-dic-edit-btn').attr("disabled",true);
			$('.sport-dic-edit-btn').text('正在提交...');
			$.ajax({
				url: Sport.getBasePath()+"/dic/update.action",
				type: "POST",
				dataType: "JSON",
				data: $("#dic-update-form").serialize(),
				error: function () {
					$('.sport-dic-edit-btn').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试！");
					$(".sport-dic-create-btn").text("保存");
				},
				success: function (obj) {
					$('.sport-dic-edit-btn').removeAttr("disabled");
					if(obj){
						window.location.href = Sport.getBasePath()+"/dic/list.htm?pCode="+$("#pCode").val();
					}else{
						layer.msg("保存失败，请稍后重试！");
					}
					$(".sport-dic-edit-btn").text("保存");
				}
			});
		};
	}).on("click",".sport-dic-delete",function(){
		var selectedIds = $("#dicGridDiv").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
			layer.msg("请最少选择一行数据");
			return;
		}
		var dicIds = new Array();
		for (var int = 0; int < selectedIds.length; int++) {
			var rowData = $("#dicGridDiv").jqGrid("getRowData",selectedIds[int]);
			dicIds.push(rowData.code);
		}
		layer.confirm('您确定要删除该数据字典吗？', {
			  btn: ['是的','稍后'] 
			}, function(){
				$('.sport-dic-delete').attr("disabled",true);
				$.ajax({
					url: Sport.getBasePath()+"/dic/delete.action",
					type: "POST",
					dataType: "JSON",
					data: {_csrf:$("#csrdId").val(),dicIds:dicIds.join(",")},
					error: function () {
						$('.sport-dic-delete').removeAttr("disabled");
						layer.msg("系统异常，请稍后重试！");
					},
					success: function (obj) {
						$('.sport-dic-delete').removeAttr("disabled");
						if(obj){
							window.location.href = Sport.getBasePath()+"/dic/list.htm";
						}else{
							layer.msg("删除失败，请稍后重试！");
						}
					}
				});
			}, function(){
		});
	}).on("click","#dicQuery",function(){
		var zTree=$.fn.zTree.getZTreeObj("dicTreeDiv");
        nodes=zTree.getSelectedNodes(true);
        var name = $("#dicSearchName").val();
        var code = $("#dicSearchCode").val();
        $("#dicGridDiv").jqGrid('setGridParam',{datatype:'json',postData:{typeId:nodes[0].id,name:name,code:code}}).trigger('reloadGrid');
	});
	
	// 用户管理
	$(document).on("click",".sport-user-menu",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(this).attr("data-type")+"/list.htm";
	}).on("click",".sport-user-create-btn",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(this).attr("data-type")+"/create.htm";
	}).on("change",".credType-select",function(){
		$(".credType-error").text("");
	}).on("click",".sport-user-save",function(){
		// 证书校验
		var credType = $(".credType-select").val();
		var credFlag = Sport.isNull(credType);
		if(credFlag){
			$(".credType-error").text("请选择证件类型");
		}else{
			$(".credType-error").text("");
		}
		// 验证通过
		if($(".sport-user-form").valid() && !credFlag){
			$('.sport-user-save').text("提交中...");
			$('.sport-user-save').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/user/"+$(".sport-user-save").attr("data-type")+"/create.action",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userName:$('#userName').val(),
					loginName:$('#loginName').val(),
					credType:$('#credType').val(),
					credNo:$('#credNo').val(),
					gender:$('input[name="gender"]:checked').val(),
					role:$('input[name="role"]:checked').val(),
					organization:$('#organization').val(),
					birthday:$('#birthday').val(),
					zc:$('#zc').val(),
					zw:$('#zw').val(),
					dept:$('#dept').val(),
					major:$('#major').val(),
					telephone:$('#telephone').val(),
					phone:$('#phone').val(),
					address:$('#address').val(),
					degrees:$("#degrees").val(),
					password:Sport.getEntryptPwd($('#pubKey').val(),$('#password').val())
				},
				error: function () {
					$('.sport-user-save').removeAttr("disabled");
				},
				success: function (obj) {
					if(obj.success){
						layer.msg("新增用户成功!");
						window.location.href = Sport.getBasePath()+"/user/"+$(".sport-user-save").attr("data-type")+"/list.htm";
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-user-save').removeAttr("disabled");
					$('.sport-user-save').text("保存");
				}
			});
		}
	}).on("click",".user-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(this).attr("data-type")+"/list.htm";
	}).on("click",".search-user-btn",function(){
		var value = $(".sport-user-key").val();
		value = Sport.isNull(value)?"":value;
		//encodeURI(encodeURI(value))
		$("#jqGrid").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:{'name':value},
            page:1  
        }).trigger("reloadGrid"); 
	}).on("click",".sport-user-edit",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(this).attr("data-type")+"/update.htm?userId="+$(this).attr("data-id");
	}).on("click",".sport-cancel-btn",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(".sport-cancel-btn").attr("data-type")+"/list.htm";
	}).on("click",".sport-user-update",function(){
		// 证书校验
		var credType = $(".credType-select").val();
		var credFlag = Sport.isNull(credType);
		if(credFlag){
			$(".credType-error").text("请选择证件类型");
		}else{
			$(".credType-error").text("");
		}
		// 验证通过
		if($(".sport-user-form").valid() && !credFlag){
			$('.sport-user-update').text("提交中...");
			$('.sport-user-update').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/user/"+$(".sport-user-update").attr("data-type")+"/update.action",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userId:$('#userId').val(),
					userName:$('#userName').val(),
					loginName:$('#loginName').val(),
					credType:$('#credType').val(),
					credNo:$('#credNo').val(),
					gender:$('input[name="gender"]:checked').val(),
					role:$('input[name="role"]:checked').val(),
					organization:$('#organization').val(),
					birthday:$('#birthday').val(),
					zc:$('#zc').val(),
					zw:$('#zw').val(),
					dept:$('#dept').val(),
					major:$('#major').val(),
					telephone:$('#telephone').val(),
					phone:$('#phone').val(),
					degrees:$("#degrees").val(),
					address:$('#address').val()
				},
				error: function () {
					$('.sport-user-update').removeAttr("disabled");
				},
				success: function (obj) {
					if(obj.success){
						layer.msg("修改用户成功!");
						window.location.href = Sport.getBasePath()+"/user/"+$(".sport-user-update").attr("data-type")+"/list.htm";
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-user-update').removeAttr("disabled");
					$('.sport-user-update').text("保存");
				}
			});
		}
	}).on("click",".sport-user-delete",function(){
		var selectedIds = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
			layer.msg("请最少选择一行数据");
			return;
		}
		var userIds = new Array();
		for (var int = 0; int < selectedIds.length; int++) {
			var rowData = $("#jqGrid").jqGrid("getRowData",selectedIds[int]);
			if(rowData.hasOpr==true || rowData.hasOpr=="true"){
				userIds.push(rowData.userId);
			}else{
				layer.msg("管理员["+rowData.userName+"]不能被删除!");
				return;
			}
		}
		lhgdialog.confirm("您确定要删除该用户吗？",function(){
			$('.sport-user-delete').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/user/"+$(".sport-user-delete").attr("data-type")+"/delete.action",
				type: "POST",
				dataType: "JSON",
				data: {_csrf:$("#csrdId").val(),userIds:userIds.join(",")},
				error: function () {
					$('.sport-user-delete').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试");
				},
				success: function (obj) {
					if(obj){
						layer.msg("删除用户成功!");
						window.location.href = Sport.getBasePath()+"/user/"+$(".sport-user-delete").attr("data-type")+"/list.htm";
					}else{
						layer.msg("删除用户失败,请稍后重试。");
					}
					$('.sport-user-delete').removeAttr("disabled");
				}
			});
		});
	});
	
	// 密码重置
	$(document).on("click",".sport-password-reset-menu",function(){
		window.location.href = Sport.getBasePath()+"/user/"+$(this).attr("data-type")+"/resetpassword.htm";
	}).on("click",".sport-yzm-btn",function(){
		var url = $(".sport-yzm").attr("src");
        if (url.indexOf("?") >= 0) {
            url = url + "&_time=" + new Date().getTime();
        } else {
            url = url + "?_time=" + new Date().getTime();
        }
        $(".sport-yzm").attr("src", url);
	}).on("click",".user-index-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/portal/"+$(this).attr("data-type")+"/index.htm";
	}).on("click",".sport-reset-pwd-btn",function(){
		if($("#reset-pwd-form").valid()){
			$('.sport-reset-pwd-btn').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/user/"+$(".sport-reset-pwd-btn").attr("data-type")+"/resetpassword.action",
				type: "POST",
				dataType: "JSON",
				data: {_csrf:$("#csrdId").val(),loginName:$("#loginName").val(),verifCode:$("#verifCode").val()},
				error: function () {
					$(".sport-yzm-btn").trigger("click");
					$('.sport-reset-pwd-btn').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试");
				},
				success: function (obj) {
					$(".sport-yzm-btn").trigger("click");
					if(obj.success){
						layer.msg("重置密码成功!");
						window.location.href = Sport.getBasePath()+"/portal/"+$(".sport-password-updae-btn").attr("data-type")+"/index.htm";
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-reset-pwd-btn').removeAttr("disabled");
				}
			});
		}
	})
	// 修改密码
	$(document).on("click",".sport-pwd-update-btn",function(){
		window.location.href = Sport.getBasePath()+"/password/"+$(this).attr("data-type")+"/update.htm";
	}).on("click",".user-password-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/portal/"+$(this).attr("data-type")+"/index.htm";
	}).on("click",".sport-password-updae-btn",function(){
		if($(".sport-pwd-update-form").valid()){
			$('.sport-password-updae-btn').text("提交中...");
			$('.sport-password-updae-btn').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/password/update.action",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userId:$('#userId').val(),
					oldPassword:Sport.getEntryptPwd($('#pubKey').val(),$('#oldPassword').val()),
					newPassword:Sport.getEntryptPwd($('#pubKey').val(),$('#password').val()),
					verifCode:$('#verifCode').val()
				},
				error: function () {
					//重置验证码
					$(".sport-yzm-btn").trigger("click");
					$('.sport-password-updae-btn').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试！");
				},
				success: function (obj) {
					if(obj.success){
						$(".sport-yzm-btn").trigger("click");
						layer.msg("修改密码成功!");
						window.location.href = Sport.getBasePath()+"/"+obj.url;
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-password-updae-btn').removeAttr("disabled");
					$('.sport-password-updae-btn').text("保存");
				}
			});
		}
	});
	
	//新闻管理
	$(document).on("click",".sport-news-menu",function(){
		window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
	}).on("click",".sport-news-skip",function(){
		window.location.href = Sport.getBasePath()+"/news/kjsadmin/create.htm";
	}).on("click",".sport-update-skip",function(){
		window.location.href = Sport.getBasePath()+"/news/kjsadmin/update.htm";
	}).on("click",".news-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
	}).on("click",".sport-news-edit",function(){
		window.location.href = Sport.getBasePath()+"/news/kjsadmin/update.htm?newsId="+$(this).attr("data-id");
	}).on("click",".sport-news-reset",function(){
		$("#sport-news-form")[0].reset();
	}).on("click",".sport-download",function(){
		var dataId = $(this).attr("data-id");
		var dataName = $(this).attr("data-name");
		window.open(Sport.getBasePath()+"/news/kjsadmin/download.action?dataId="+dataId+"&dataName="+encodeURI(dataName));
	}).on("change","#news-column",function(){
		$(".column-error").text("");
	}).on("click",".sport-lm li",function(){
		$(".sport-lm li").removeClass('sel');
		$(this).addClass('sel');
		var column = $(this).attr("data-id");
		// 加载数据
		$("#newsGridDiv").jqGrid('setGridParam',{datatype:'json',postData:{column:column}}).trigger('reloadGrid');
	}).on("click",".sport-news-create-btn",function(){
		var columnId = $("#news-column").val();
		var columnFlag = Sport.isNull(columnId);
		if(columnFlag){
			$(".column-error").text("请选择栏目！");
		}else{
			$(".column-error").text("");
		}
		//内容
		var content = CKEDITOR.instances.content.getData();
		var contentFlag = Sport.isNull(content);
		if(columnFlag){
			$(".news-content-error").text("请填入新闻内容！");
		}else{
			$(".news-content-error").text("");
		}
		$("#contentContainer").val(html2ubb(content));
		//文件
		var len = $(".news-file-container").find(".sport-news-file").length;
		var fileIdFlag = false;
		if(len==0){
			$(".news-file-error").text("请上传附件！");
		}else{
			$(".news-file-error").text("");
			fileIdFlag = true;
		}
		if($("#sport-news-form").valid() && !columnFlag && !contentFlag && fileIdFlag){
			$('.sport-news-create-btn').text("提交中...");
			$('.sport-news-create-btn').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/news/kjsadmin/create.action",
				type: "POST",
				dataType: "JSON",
				data: $("#sport-news-form").serialize(),
				error: function () {
					$('.sport-news-create-btn').removeAttr("disabled");
					$('.sport-news-create-btn').text("保存");
					layer.msg("系统异常，请稍后重试！");
				},
				success: function (obj) {
					if(obj){
						layer.msg("恭喜您，保存新闻成功！");
						window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
					}else{
						layer.msg("系统异常，请稍后重试！");
					}
					$('.sport-news-create-btn').removeAttr("disabled");
					$('.sport-news-create-btn').text("保存");
				}
			});
		}
	}).on("click",".sport-news-update-btn",function(){
		var columnId = $("#news-column").val();
		var columnFlag = Sport.isNull(columnId);
		if(columnFlag){
			$(".column-error").text("请选择栏目！");
		}else{
			$(".column-error").text("");
		}
		//内容
		var content = CKEDITOR.instances.content.getData();
		var contentFlag = Sport.isNull(content);
		if(columnFlag){
			$(".news-content-error").text("请填入新闻内容！");
		}else{
			$(".news-content-error").text("");
		}
		$("#contentContainer").val(html2ubb(content));
		//文件
		var len = $(".news-file-container").find(".sport-news-file").length;
		var fileIdFlag = false;
		if(len==0){
			$(".news-file-error").text("请上传附件！");
		}else{
			$(".news-file-error").text("");
			fileIdFlag = true;
		}
		if($("#sport-news-form").valid() && !columnFlag && !contentFlag && fileIdFlag){
			$('.sport-news-update-btn').text("提交中...");
			$('.sport-news-update-btn').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/news/kjsadmin/update.action",
				type: "POST",
				dataType: "JSON",
				data: $("#sport-news-form").serialize(),
				error: function () {
					$('.sport-news-update-btn').removeAttr("disabled");
					$('.sport-news-update-btn').text("保存");
					layer.msg("系统异常，请稍后重试！");
				},
				success: function (obj) {
					if(obj){
						layer.msg("恭喜您，保存新闻成功！");
						window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
					}else{
						layer.msg("系统异常，请稍后重试！");
					}
					$('.sport-news-update-btn').removeAttr("disabled");
					$('.sport-news-update-btn').text("保存");
				}
			});
		}
	}).on("click",".sport-news-publish",function(){
		var selectedIds = $("#newsGridDiv").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
			layer.msg("请最少选择一行数据");
			return;
		}
		var newsIds = new Array();
		for (var int = 0; int < selectedIds.length; int++) {
			var rowData = $("#newsGridDiv").jqGrid("getRowData",selectedIds[int]);
			if(rowData.status==0 || rowData.status==-1){
				newsIds.push(rowData.id);
			}else{
				layer.msg("该消息["+rowData.title+"]已经发布!");
				return;
			}
		}
		layer.confirm('您确定要发布该新闻吗？', {
			  btn: ['是的','稍后'] 
			}, function(){
				$('.sport-news-publish').text("发布中...");
				$('.sport-news-publish').attr("disabled",true);
				var token = $("meta[name='_csrf']").attr("content");
			    var header = $("meta[name='_csrf_header']").attr("content");
			    $(document).ajaxSend(function(e, xhr, options) {
			         xhr.setRequestHeader(header, token);
			    });
				$.ajax({
					url: Sport.getBasePath()+"/news/kjsadmin/publish.action",
					type: "POST",
					dataType: "JSON",
					data: {
						newsIds:newsIds.join(",")
					},
					error: function () {
						$('.sport-news-publish').removeAttr("disabled");
						$('.sport-news-publish').text("发布");
						layer.msg("系统异常，请稍后重试！");
					},
					success: function (obj) {
						if(obj){
							layer.msg("恭喜您，发布新闻成功！");
							window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
						}else{
							layer.msg("系统异常，请稍后重试！");
						}
						$('.sport-news-publish').removeAttr("disabled");
						$('.sport-news-publish').text("发布");
					}
				});
			}, function(){
		});
	}).on("click",".sport-news-unpublish",function(){
		var selectedIds = $("#newsGridDiv").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
			layer.msg("请最少选择一行数据");
			return;
		}
		var newsIds = new Array();
		for (var int = 0; int < selectedIds.length; int++) {
			var rowData = $("#newsGridDiv").jqGrid("getRowData",selectedIds[int]);
			if(rowData.status==1){
				newsIds.push(rowData.id);
			}else{
				layer.msg("该消息["+rowData.title+"]还没有发布!");
				return;
			}
		}
		layer.confirm('您确定要取消发布该新闻吗？', {
			  btn: ['是的','稍后'] 
			}, function(){
				$('.sport-news-unpublish').text("取消发布中...");
				$('.sport-news-unpublish').attr("disabled",true);
				var token = $("meta[name='_csrf']").attr("content");
			    var header = $("meta[name='_csrf_header']").attr("content");
			    $(document).ajaxSend(function(e, xhr, options) {
			         xhr.setRequestHeader(header, token);
			    });
				$.ajax({
					url: Sport.getBasePath()+"/news/kjsadmin/unpublish.action",
					type: "POST",
					dataType: "JSON",
					data: {
						newsIds:newsIds.join(",")
					},
					error: function () {
						$('.sport-news-unpublish').removeAttr("disabled");
						$('.sport-news-unpublish').text("取消发布");
						layer.msg("系统异常，请稍后重试！");
					},
					success: function (obj) {
						if(obj){
							layer.msg("恭喜您，取消发布新闻成功！");
							window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
						}else{
							layer.msg("系统异常，请稍后重试！");
						}
						$('.sport-news-unpublish').removeAttr("disabled");
						$('.sport-news-unpublish').text("取消发布");
					}
				});
			}, function(){
		});
	}).on("click",".sport-news-delete",function(){
		var selectedIds = $("#newsGridDiv").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
			layer.msg("请最少选择一行数据");
			return;
		}
		var newsIds = new Array();
		for (var int = 0; int < selectedIds.length; int++) {
			var rowData = $("#newsGridDiv").jqGrid("getRowData",selectedIds[int]);
			newsIds.push(rowData.id);
		}
		layer.confirm('您确定要删除该新闻吗？', {
			  btn: ['是的','稍后'] 
			}, function(){
				$('.sport-news-delete').text("删除中...");
				$('.sport-news-delete').attr("disabled",true);
				var token = $("meta[name='_csrf']").attr("content");
			    var header = $("meta[name='_csrf_header']").attr("content");
			    $(document).ajaxSend(function(e, xhr, options) {
			         xhr.setRequestHeader(header, token);
			    });
				$.ajax({
					url: Sport.getBasePath()+"/news/kjsadmin/delete.action",
					type: "POST",
					dataType: "JSON",
					data: {
						newsIds:newsIds.join(",")
					},
					error: function () {
						$('.sport-news-delete').removeAttr("disabled");
						$('.sport-news-delete').text("删除");
						layer.msg("系统异常，请稍后重试！");
					},
					success: function (obj) {
						if(obj){
							layer.msg("恭喜您，删除新闻成功！");
							window.location.href = Sport.getBasePath()+"/news/kjsadmin/list.htm";
						}else{
							layer.msg("系统异常，请稍后重试！");
						}
						$('.sport-news-delete').removeAttr("disabled");
						$('.sport-news-delete').text("删除");
					}
				});
			}, function(){
		});
	}).on("click",".sport-news-search-btn",function(){
		var title = $("#search-title").val();
		var status = $("#search-status").val();
		$("#newsGridDiv").jqGrid('setGridParam',{datatype:'json',postData:{title:title,status:status}}).trigger('reloadGrid');
	});
	
	
	// 注册
	$(document).on("click",".sport-register-first-back",function(){
		var dataId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/regist/update.htm?orgId="+dataId;
	}).on("click",".sport-regist-first",function(){
		var region = $("#loc_province").val();
		var regionFlag = Sport.isNull(region);
		if(regionFlag){
			$(".region-error").text("请选择地区");
		}else{
			$(".region-error").text("");
		}
		
		var quality = $("#quality").val();
		var qualityFlag = Sport.isNull(quality);
		if(qualityFlag){
			$(".quality-error").text("请选择单位性质");
		}else{
			$(".quality-error").text("");
		}
		if($(".register-form").valid() && !regionFlag && !qualityFlag){
			$('.sport-regist-first').text("提交中...");
			$('.sport-regist-first').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/regist/regist.action",
				type: "POST",
				//dataType: "JSON",
				data: $('.register-form').serialize(),
				error: function () {
					$('.sport-regist-first').removeAttr("disabled");
					$('.sport-regist-first').text("保存");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj){
						layer.msg("新增单位管理员成功!");
						window.location.href = Sport.getBasePath()+"/regist/manager/regist.htm?orgId="+obj;
					}else{
						layer.msg("新增单位管理员失败!");
					}
					$('.sport-regist-first').removeAttr("disabled");
					$('.sport-regist-first').text("保存");
				}
			})
		}
	}).on("click",".sport-register-manager-save",function(){
		// 证书校验
		var credType = $(".credType-select").val();
		var credFlag = Sport.isNull(credType);
		if(credFlag){
			$(".credType-error").text("请选择证件类型");
		}else{
			$(".credType-error").text("");
		}
		// 性别校验
		var gender = $(".gender-select").val();
		var genderFlag = Sport.isNull(gender);
		if(genderFlag){
			$(".gender-error").text("请选择性别");
		}else{
			$(".gender-error").text("");
		}
		
		// 验证通过
		if($(".sport-user-form").valid() && !credFlag && !genderFlag){
			$('.sport-user-save').text("提交中...");
			$('.sport-user-save').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/regist/manager/regist.action",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userName:$('#userName').val(),
					loginName:$('#loginName').val(),
					credType:$('#credType').val(),
					credNo:$('#credNo').val(),
					gender:$('#gender').val(),
					role:$('input[name="role"]:checked').val(),
					organization:$('#organization').val(),
					birthday:$('#birthday').val(),
					zc:$('#zc').val(),
					zw:$('#zw').val(),
					dept:$('#dept').val(),
					major:$('#major').val(),
					email:$('#email').val(),
					telephone:$('#telephone').val(),
					phone:$('#phone').val(),
					address:$('#address').val(),
					degrees:$("#degrees").val(),
					password:Sport.getEntryptPwd($('#pubKey').val(),$('#password').val())
				},
				error: function () {
					$('.sport-user-save').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj.success){
						layer.msg("新增单位管理员成功!");
						window.location.href = Sport.getBasePath()+"/regist/success.htm";
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-user-save').removeAttr("disabled");
					$('.sport-user-save').text("保存");
				}
			})
		}
	}).on("click",".sport-register-manager-update",function(){
		// 证书校验
		var credType = $(".credType-select").val();
		var credFlag = Sport.isNull(credType);
		if(credFlag){
			$(".credType-error").text("请选择证件类型");
		}else{
			$(".credType-error").text("");
		}
		// 性别校验
		var gender = $(".gender-select").val();
		var genderFlag = Sport.isNull(gender);
		if(genderFlag){
			$(".gender-error").text("请选择性别");
		}else{
			$(".gender-error").text("");
		}
		
		// 验证通过
		if($(".sport-user-form").valid() && !credFlag && !genderFlag){
			$('.sport-register-manager-update').text("提交中...");
			$('.sport-register-manager-update').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/regist/manager/update.action",
				type: "POST",
				dataType: "JSON",
				data: {
					uuid:$('#uuid').val(),
					_csrf:$("#csrdId").val(),
					userId:$("#userId").val(),
					userName:$('#userName').val(),
					loginName:$('#loginName').val(),
					credType:$('#credType').val(),
					credNo:$('#credNo').val(),
					gender:$('#gender').val(),
					role:$('input[name="role"]:checked').val(),
					organization:$('#organization').val(),
					birthday:$('#birthday').val(),
					zc:$('#zc').val(),
					zw:$('#zw').val(),
					dept:$('#dept').val(),
					email:$('#email').val(),
					major:$('#major').val(),
					telephone:$('#telephone').val(),
					phone:$('#phone').val(),
					address:$('#address').val(),
					degrees:$("#degrees").val()
				},
				error: function () {
					$('.sport-register-manager-update').removeAttr("disabled");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj.success){
						layer.msg("新增单位管理员成功!");
						window.location.href = Sport.getBasePath()+"/regist/success.htm";
					}else{
						layer.msg(obj.msg);
					}
					$('.sport-register-manager-update').removeAttr("disabled");
					$('.sport-register-manager-update').text("保存");
				}
			})
		}
	}).on("click",".sport-regist-first-update",function(){
		var region = $("#loc_province").val();
		var regionFlag = Sport.isNull(region);
		if(regionFlag){
			$(".region-error").text("请选择地区");
		}else{
			$(".region-error").text("");
		}
		
		var quality = $("#quality").val();
		var qualityFlag = Sport.isNull(quality);
		if(qualityFlag){
			$(".quality-error").text("请选择单位性质");
		}else{
			$(".quality-error").text("");
		}
		if($(".register-form").valid() && !regionFlag && !qualityFlag){
			$('.sport-regist-first-update').text("提交中...");
			$('.sport-regist-first-update').attr("disabled",true);
			$.ajax({
				url: Sport.getBasePath()+"/regist/update.action",
				type: "POST",
				//dataType: "JSON",
				data: $('.register-form').serialize(),
				error: function () {
					$('.sport-regist-first-update').removeAttr("disabled");
					$('.sport-regist-first-update').text("保存");
					layer.msg("系统异常，请稍后重试!");
				},
				success: function (obj) {
					if(obj){
						layer.msg("修改单位信息成功!");
						window.location.href = Sport.getBasePath()+"/regist/manager/regist.htm?orgId="+obj;
					}else{
						layer.msg("修改单位信息失败!");
					}
					$('.sport-regist-first-update').removeAttr("disabled");
					$('.sport-regist-first-update').text("保存");
				}
			})
		}
	});
	
	// 注册单位审核
	$(document).on("click",".sport-register-verfiy",function(){
		window.location.href = Sport.getBasePath()+"/sborg/kjsadmin/list.htm";
	}).on("click",".sprot-search-org",function(){
		var fullName = $("#fullName").val();
		var legalLeader = $("#legalLeader").val();
		var orgStatus = $("#orgStatus").val();
		$("#orgGridDiv").jqGrid('setGridParam',{datatype:'json',postData:{fullName:fullName,legalLeader:legalLeader,status:orgStatus}}).trigger('reloadGrid');
	}).on("click",".sport-sborg-manager-query",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/sborg/kjsadmin/user.htm?orgId="+orgId;
	}).on("click",".sport-sborg-query",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/sborg/kjsadmin/detail.htm?orgId="+orgId;
	}).on("click",".sborgdetail-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/sborg/kjsadmin/list.htm";
	}).on("click",".sport-sborg-verify",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/sborg/kjsadmin/verify.htm?orgId="+orgId;
	})
	
	//组织单位
	$(document).on("click",".sport-org-menu",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
	}).on("click",".sport-org-manager-query",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/user.htm?orgId="+orgId;
	}).on("click",".sport-org-query",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/detail.htm?orgId="+orgId;
	}).on("click",".orgdetail-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
	}).on("click",".sport-org-create-btn",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/create.htm";
	}).on("click",".org-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
	}).on("click",".org-form-cancel",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
	}).on("click",".org-returnBtn",function(){
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/list.htm";
	}).on("click",".sport-orgcreate-first",function(){
		Sport.createOrg("org-form", "sport-orgcreate-first", "/org/kjsadmin/create.action", "/org/kjsadmin/manager/update.htm","新增组织单位");
	}).on("click",".sport-orgcreate-first-back",function(){
		var orgId = $(this).attr("data-id");
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/update.htm?orgId="+$(this).attr("data-id");
	}).on("click",".sport-updateorg-btn",function(){
		Sport.createOrg("org-form", "sport-updateorg-btn", "/org/kjsadmin/update.action", "/org/kjsadmin/manager/update.htm","修改组织单位");
	}).on("click",".sport-orgcreate-manager-save",function(){//保存组织单位管理员信息
		Sport.createManager("sport-user-form", "sport-orgcreate-manager-save", "/org/kjsadmin/manager/create.action", "/org/kjsadmin/list.htm","新增组织单位管理员");
	}).on("click",".sport-create-manager-back",function(){//返回创建组织机构第一步
		window.location.href = Sport.getBasePath()+"/org/kjsadmin/update.htm?orgId="+$(this).attr("data-id");
	}).on("click",".sport-create-manager-update",function(){//修改组织单位管理员信息
		Sport.createManager("sport-user-form", "sport-create-manager-update", "/org/kjsadmin/manager/update.action", "/org/kjsadmin/list.htm","修改组织单位管理员");
	})

	//课题管理
	$(document).on("click",".sport-subject-menu",function(){
		window.location.href = Sport.getBasePath()+"/subject/"+$(this).attr("data-type")+"/list.htm";
	})
})