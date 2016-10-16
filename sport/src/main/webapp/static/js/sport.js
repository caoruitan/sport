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
};

var clickmenu = function(url) {
	window.location.href=url;
}

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
				//contentType: "application/x-www-form-urlencoded; charset=utf-8",
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
	})
	
	
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
        $("#jqGrid").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:{'name':encodeURI(encodeURI(value))},
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
		if(selectedIds.length<=1){
			layer.msg("请最少选择一行数据");
			return;
		}
		
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
	})
})