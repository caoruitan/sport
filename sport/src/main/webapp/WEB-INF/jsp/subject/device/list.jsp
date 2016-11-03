<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="t">10 设备购置明细表</div>
<div class="listBox">
<div class="c">
	<div class="opBtnBox" >
		<div class="fl-l">
			<button class="btn-red" id='device-xj'>+ 新增</button>
		</div>
		<div class="fl-r">
			<button class="btn-wisteria device-delete">删除</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="deviceToken"/>
		</div>
	</div>
	<div class="tb">
	<table id="jqGrid-jd"  class="sport-grid"></table>
	<div id="jqGridPager-jd"></div>
	</div>
<script type="text/javascript">
	//进度
	$('#device-xj').dialog({
		id: 'device-xj',
		title: '设备新增',
		content: 'url:'+Sport.getBasePath()+"/subject/device/sboper/create.htm?rwsId=${rwsId}&subjectId=${subjectId}",
		width: 700,
		height: 480,
		ok: function(){
			return scheduling(this,"/subject/device/sboper/create.action");
		},
		cancel: true
	});
	
	jQuery.validator.addMethod("decimal", function(value, element) {
		var decimal = /^-?\d+(\.\d{1,2})?$/;
		return this.optional(element) || (decimal.test(value));
	}, $.validator.format("小数位数不能超过两位!"));
	
	jQuery.validator.addMethod("positiveinteger", function(value, element) {
		  var aint=parseInt(value);	
		  return aint>0&& (aint+"")==value;   
	}, "请输入正整数");   

	
	function scheduling(obj,url){
		var doucment = obj.content.document.forms[0];
		$(doucment).validate({
	        rules: {
	        	name:{
	                required: true
	            },
	            price:{
	                required: true,
	                decimal:true
	            },
	            num:{
	            	required:true,
	            	digits:true,
	            	positiveinteger:true
	            }
	        },
	        messages: {
	        	name:{
	                required: "请填写设备名称"
	            },
	            price:{
	                required: "请填写设备单价",
	            },
	            num: {
	            	required:"请填写购买数量",
	            	digits:"设备购买数量只能为整数"
 	            }
	        }
	    });
		
		if($(doucment).valid()){
			$.ajax({
				url:"<%=basePath %>/"+url,
				data:$(doucment).serialize(),
				type:"POST",
				success:function(data){
					$(".b-sbgzmx").load("<%=basePath%>/subject/device/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
				},
				error:function(){
					
				}
			});
			return true;
		}
		return false;
	}
	
	//进度安排
	$("#jqGrid-jd").jqGrid({
		datatype: "local",
		colModel: [{
			name: 'dId',
			width: 10,
			hidden:true
		},{
			name: 'name',
			label:"设备名称",
			width: 10
		},  {
			name: 'purpose',
			label:"购置或试制的理由、用途",
			align: "center",
			width: 10
		}, {
			label: '购置设备型号及主要技术性能指标',
			name: 'norm',
			align: "center",
			width: 25
		}, {
			label: '单价',
			name: 'price',
			width: 20,
			align: "center"
		}, {
			label: '数量',
			name: 'num',
			width: 10,
			align: "center",
		}, {
			label: '申请从专项经费中列支数',
			name: 'slzs',
			width: 10,
			align: "center",
		}, {
			name: '操作',
			width: 10,
			align: "center",
			sortable: false,
			formatter:function(value, grid, rows, state){
				return "<a href='javascript:;;' class='sport-device-edit'  data-id='"+rows.dId+"'>编辑</a>";
			}
		}],
		viewrecords: true,
		width: 860,
		rowNum: 20,
		multiselect: true,
		pager: "#jqGridPager-jd"
	});
	
	var datas = $.parseJSON('${ssDatas}');
	$.each(datas,function(i,v){
		jQuery("#jqGrid-jd").jqGrid('addRowData', i + 1, datas[i]);
	});
	
	$(function(){
		$(".sport-device-edit").click(function(){
			var dataId = $(this).attr("data-id");
			new $.dialog({
				id: 'jd_update',
				title: '进度安排',
				content: 'url:'+Sport.getBasePath()+"/subject/device/sboper/update.htm?sId="+dataId,
				width: 700,
				height: 400,
				ok: function(){
					return scheduling(this,"/subject/device/sboper/update.action");
				},
				cancel: true
			});
		});
		
		$(".device-delete").click(function(){
			var selectedIds = $("#jqGrid-jd").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length<1){
				layer.msg("请最少选择一行数据");
				return;
			}
			var sIds = new Array();
			for (var int = 0; int < selectedIds.length; int++) {
				var rowData = $("#jqGrid-jd").jqGrid("getRowData",selectedIds[int]);
				sIds.push(rowData.dId);
			}
			new $.dialog({
				id: 'jd_delete',
				title: '进度安排',
				content: "您确定要删除该进度安排吗？",
				width: 250,
				height: 80,
				ok: function(){
					$.ajax({
						url:Sport.getBasePath()+"/subject/device/sboper/delete.action",
						data:{sIds:sIds.join(","),_csrf:$("#deviceToken").val()},
						type:"POST",
						success:function(data){
							$(".b-sbgzmx").load("<%=basePath%>/subject/device/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
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
</div>