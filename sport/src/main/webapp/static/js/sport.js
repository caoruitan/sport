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
	}
};

$(function(){
	// 记住登录名
	var name = Sport.Cookie.get(Sport.name);
	if(name){
		$("#loginName").val(name);
	}
	// 登录操作
	$(document).on("click","#loginBtn",function(){
		$('#loginBtn').text('正在登录...');
		$('#loginBtn').attr("disabled",true);
		var uuid = $("#uuid").val();
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
	}).on("click","a.sport-logout",function(){
		$.StandardPost(Sport.getBasePath()+"/logout");
	})
})
