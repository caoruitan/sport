<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">12 需拨付其他单位经费情况</div>
<div class="listBox">
<div class="c">
	<div class="opBtnBox" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="approprToken"/>
		<div class="fl-l">
			<button class="btn-red" id="bf">+ 新增</button>
		</div>
		<div class="fl-r">
			<button class="btn-wisteria appropr-delete">删除</button>
		</div>
	</div>
	<div class="tb">
	<table id="jqGrid-bf"></table>
	<div id="jqGridPager-bf"></div>
	</div>
</div>
<script type="text/javascript">
	//进度
	$('#bf').dialog({
		id: 'bf',
		title: '需拨付其他单位经费情况新增',
		content: 'url:'+Sport.getBasePath()+"/subject/appropriation/sboper/create.htm?rwsId=${rwsId}&subjectId=${subjectId}",
		width: 800,
		height: 234,
		ok: function(){
			return scheduling(this,"subject/appropriation/sboper/create.action");
		},
		cancel: true
	});
	
	jQuery.validator.addMethod("decimal", function(value, element) {
		var decimal = /^-?\d+(\.\d{1,3})?$/;
		return this.optional(element) || (decimal.test(value));
	}, $.validator.format("小数位数不能超过三位!"));
	
	
	function scheduling(obj,url){
		var doucment = obj.content.document.forms[0];
		$(doucment).validate({
	        rules: {
	        	gainOrg:{
	                required: true
	            },
	            approAmount:{
	                required: true,
	                decimal:true
	            }
	        },
	        messages: {
	        	gainOrg:{
	                required: "请填写拨往单位"
	            },
	            approAmount:{
	                required: "请填写拨款数额"
	            }
	        }
	    });
		if($(doucment).valid()){
			$.ajax({
				url:"<%=basePath %>/"+url,
				data:$(doucment).serialize(),
				type:"POST",
				success:function(data){
					$(".b-xbfjf").load("<%=basePath%>/subject/appropriation/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
				},
				error:function(){
					
				}
			});
			return true;
		}
		return false;
	}
	
	//进度安排
	$("#jqGrid-bf").jqGrid({
		datatype: "local",
		colModel: [{
			name: 'approId',
			width: 10,
			hidden:true
		},{
			name: 'gainOrg',
			label:"拨往单位",
			align: "center",
			width: 10
		},  {
			name: 'approAmount',
			label:"拨付数额（万元）",
			align: "center",
			width: 10
		}, {
			label: '用途说明',
			name: 'describe',
			align: "center",
			width: 25
		}, {
			name: '操作',
			width: 10,
			align: "center",
			sortable: false,
			formatter:function(value, grid, rows, state){
				return "<a href='javascript:;;' class='sport-appropr-edit'  data-id='"+rows.approId+"'>编辑</a>";
			}
		}],
		viewrecords: true,
		width: 860,
		rowNum: 20,
		multiselect: true,
		pager: "#jqGridPager-bf"
	});
	
	var datas = $.parseJSON('${ssDatas}');
	$.each(datas,function(i,v){
		jQuery("#jqGrid-bf").jqGrid('addRowData', i + 1, datas[i]);
	});
	
	$(function(){
		$(".sport-appropr-edit").click(function(){
			var dataId = $(this).attr("data-id");
			new $.dialog({
				id: 'jd_update',
				title: '需拨付其他单位经费情况修改',
				content: 'url:'+Sport.getBasePath()+"/subject/appropriation/sboper/update.htm?sId="+dataId,
				width: 800,
				height: 234,
				ok: function(){
					return scheduling(this,"subject/appropriation/sboper/update.action");
				},
				cancel: true
			});
		});
		
		$(".appropr-delete").click(function(){
			var selectedIds = $("#jqGrid-bf").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length<1){
				layer.msg("请最少选择一行数据");
				return;
			}
			var sIds = new Array();
			for (var int = 0; int < selectedIds.length; int++) {
				var rowData = $("#jqGrid-bf").jqGrid("getRowData",selectedIds[int]);
				sIds.push(rowData.approId);
			}
			new $.dialog({
				id: 'bf_delete',
				title: '需拨付其他单位经费情况删除确认',
				content: "您确定要删除该需拨付其他单位经费情况吗？",
				width: 250,
				height: 80,
				ok: function(){
					$.ajax({
						url:Sport.getBasePath()+"/subject/appropriation/sboper/delete.action",
						data:{sIds:sIds.join(","),_csrf:$("#approprToken").val()},
						type:"POST",
						success:function(data){
							$(".b-xbfjf").load("<%=basePath%>/subject/appropriation/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
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