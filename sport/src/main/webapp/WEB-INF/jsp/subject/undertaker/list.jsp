<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">08 项目承担单位、协作单位和人员情况</div>
<div class="listBox">
<div class="c">
	<div class="opBtnBox" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="undertakerToken"/>
		<div class="fl-l">
			<button class="btn-red" id="bf">+ 新增</button>
		</div>
		<div class="fl-r">
			<button class="btn-wisteria undertaker-delete">删除</button>
		</div>
	</div>
	<div class="tb">
	<table id="jqGrid-undertaker"></table>
	<div id="jqGridPager-undertaker"></div>
	</div>
</div>
<script type="text/javascript">
	//进度
	$('#bf').dialog({
		id: 'bf',
		title: '项目人员情况填报',
		content: 'url:'+Sport.getBasePath()+"/subject/undertaker/sboper/create.htm?rwsId=${rwsId}&subjectId=${subjectId}",
		width: 800,
		height: 430,
		ok: function(){
			return scheduling(this,"subject/undertaker/sboper/create.action");
		},
		cancel: true
	});
	
	jQuery.validator.addMethod("stringCheck", function(value, element) { 
	     return this.optional(element) || /^[\u0391-\uFFE5\w]{1,100}$/.test(value); 
	});
	
	
	jQuery.validator.addMethod("positiveinteger", function(value, element) {
		  var aint=parseInt(value);	
		  return (aint>0 && aint <200) || ""==value;   
	}, "请输入小于200的正整数");   
	
	function scheduling(obj,url){
		var doucment = obj.content.document.forms[0];
		$(doucment).validate({
	        rules: {
	        	name:{
	                required: true,
	                maxlength:20
	            },
	            age:{
	                required: false,
	                digits:true,
	                positiveinteger:true
	            },
	            major:{
	            	 required: true
	            },
	            org:{
	            	required:true
	            },
	            work:{
	            	required:true
	            },
	            type:{
	            	required:true
	            }
	        },
	        messages: {
	        	name:{
	                required: "请填写姓名",
	                maxlength:'姓名在1-20个字符之间'
	            },
	            age:{
	                digits:"年龄只能为数字"
	            },
	            org:{
	            	required: "请填写所属单位"
	            },
	            major:{
	            	required:"请填写专业"
	            },
	            work:{
	            	required:"请填写分工"
	            },
	            type:{
	            	required:"请填写类型"
	            }
	        }
	    });
		var result = $(doucment).valid()
		// 证书校验
		var zw = $(doucment).find(".zw-select").val();
		var zwFlag = Sport.isNull(zw);
		if(zwFlag){
			$(doucment).find(".zw-error").text("请选择职务");
		}else{
			$(doucment).find(".zw-error").text("");
		}
		// 证书校验
		var degrees = $(doucment).find(".degrees-select").val();
		var degreesFlag = Sport.isNull(degrees);
		if(degreesFlag){
			$(doucment).find(".degrees-error").text("请选择学历");
		}else{
			$(doucment).find(".degrees-error").text("");
		}
		if(!degreesFlag && !zwFlag && $(doucment).valid()){
			$.ajax({
				url:"<%=basePath %>/"+url,
				data:$(doucment).serialize(),
				type:"POST",
				success:function(data){
					$(".b-xmcddw").load("<%=basePath%>/subject/undertaker/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
				},
				error:function(){
					
				}
			});
			return true;
		}
		return false;
	}
	
	//进度安排
	$("#jqGrid-undertaker").jqGrid({
		datatype: "local",
		colModel: [
			{name:'id',align:"center", width:20,hidden:true},
			{label:"姓名",name:'name',align:"center", width:20},
			{label:"类型",name:'type', align:"center", width:15},
			{label:"单位",name:'org', align:"center", width:15},
			{label:"年龄",name:'age', width:20, align:"center"},
			{label:"职务",name:'zw', width:20, align:"center",sorttype:"float"},		
			{label:"分工",name:'work', width:30,align:"center",sorttype:"float"},		
			{label:"操作",width:10, align:"center",sortable:false,formatter:function(value, grid, rows, state){
				return "<a href='javascript:;;' class='sport-undertaker-edit' data-id='"+rows.id+"'>编辑</a>";
			}}
        ],
		viewrecords: true,
		width: 860,
		rowNum: 20,
		multiselect: true,
		pager: "#jqGridPager-undertaker"
	});
	
	var datas = $.parseJSON('${undertakers}');
	if(datas){
		$.each(datas,function(i,v){
			jQuery("#jqGrid-undertaker").jqGrid('addRowData', i + 1, datas[i]);
		});
	}
	
	
	$(function(){
		$(".sport-undertaker-edit").click(function(){
			var dataId = $(this).attr("data-id");
			new $.dialog({
				id: 'jd_update',
				title: '项目承担单位修改',
				content: 'url:'+Sport.getBasePath()+"/subject/undertaker/sboper/update.htm?sId="+dataId,
				width: 800,
				height: 430,
				ok: function(){
					return scheduling(this,"subject/undertaker/sboper/update.action");
				},
				cancel: true
			});
		});
		
		$(".undertaker-delete").click(function(){
			var selectedIds = $("#jqGrid-undertaker").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length<1){
				layer.msg("请最少选择一行数据");
				return;
			}
			var sIds = new Array();
			for (var int = 0; int < selectedIds.length; int++) {
				var rowData = $("#jqGrid-undertaker").jqGrid("getRowData",selectedIds[int]);
				sIds.push(rowData.id);
			}
			new $.dialog({
				id: 'undertaker_delete',
				title: '项目承担单位删除确认',
				content: "您确定要删除该项目承担单位吗？",
				width: 250,
				height: 80,
				ok: function(){
					$.ajax({
						url:Sport.getBasePath()+"/subject/undertaker/sboper/delete.action",
						data:{undertakerId:sIds.join(","),_csrf:$("#undertakerToken").val()},
						type:"POST",
						success:function(data){
							$(".b-xmcddw").load("<%=basePath%>/subject/undertaker/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
						},
						error:function(){
						}
					});
					
				},
				cancel: true
			});
		});
	});
</script>
</div>