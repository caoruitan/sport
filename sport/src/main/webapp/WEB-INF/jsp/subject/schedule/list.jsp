<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">07 进度安排填报</div>
<div class="listBox">
<div class="c">
	<div class="opBtnBox" >
		<div class="fl-l">
			<button class="btn-red" id='jd'>+ 新增</button>
		</div>
		<div class="fl-r">
			<button class="btn-wisteria schedule-delete">删除</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="scheduleToken"/>
		</div>
	</div>
	<div class="tb">
	<table id="jqGrid-jd"  class="sport-grid"></table>
	<div id="jqGridPager-jd"></div>
	</div>
<script type="text/javascript">
	//进度
	$('#jd').dialog({
		id: 'jd',
		title: '进度安排',
		content: 'url:'+Sport.getBasePath()+"/subject/schedule/sboper/create.htm?rwsId=${rwsId}&subjectId=${subjectId}",
		width: 700,
		height: 400,
		ok: function(){
			return scheduling(this,"/subject/schedule/sboper/create.action");
		},
		cancel: true
	});
	
	function scheduling(obj,url){
		var doucment = obj.content.document.forms[0];
		$(doucment).validate({
	        rules: {
	        	schTime:{
	                required: true
	            },
	            work:{
	                required: true
	            },
	            goal:{
	            	required:false,
	            	maxlength:3000
	            }
	        },
	        messages: {
	        	schTime:{
	                required: "请填写进度安排年月"
	            },
	            work:{
	                required: "请填写主要工作内容",
	            },
	            goal: {
	            	maxlength:'目标不能超过3000',
	            }
	        }
	    });
		if($(doucment).valid()){
			$.ajax({
				url:"<%=basePath %>/"+url,
				data:$(doucment).serialize(),
				type:"POST",
				success:function(data){
					$(".b-jdap").load("<%=basePath%>/subject/schedule/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
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
			name: 'sId',
			width: 10,
			hidden:true
		},{
			name: 'year',
			label:"进度安排年",
			width: 10
		},  {
			name: 'month',
			label:"进度安排月",
			align: "center",
			width: 10
		}, {
			label: '主要工作内容',
			name: 'work',
			align: "center",
			width: 25
		}, {
			label: '目标',
			name: 'goal',
			width: 20,
			align: "center"
		}, {
			name: '操作',
			width: 10,
			align: "center",
			sortable: false,
			formatter:function(value, grid, rows, state){
				return "<a href='javascript:;;' class='sport-schedule-edit'  data-id='"+rows.sId+"'>编辑</a>";
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
		$(".sport-schedule-edit").click(function(){
			var dataId = $(this).attr("data-id");
			new $.dialog({
				id: 'jd_update',
				title: '进度安排',
				content: 'url:'+Sport.getBasePath()+"/subject/schedule/sboper/update.htm?sId="+dataId,
				width: 700,
				height: 400,
				ok: function(){
					return scheduling(this,"/subject/schedule/sboper/update.action");
				},
				cancel: true
			});
		});
		
		$(".schedule-delete").click(function(){
			var selectedIds = $("#jqGrid-jd").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length<1){
				layer.msg("请最少选择一行数据");
				return;
			}
			var sIds = new Array();
			for (var int = 0; int < selectedIds.length; int++) {
				var rowData = $("#jqGrid-jd").jqGrid("getRowData",selectedIds[int]);
				sIds.push(rowData.sId);
			}
			new $.dialog({
				id: 'jd_delete',
				title: '进度安排',
				content: "您确定要删除该进度安排吗？",
				width: 250,
				height: 80,
				ok: function(){
					$.ajax({
						url:Sport.getBasePath()+"/subject/schedule/sboper/delete.action",
						data:{sIds:sIds.join(","),_csrf:$("#scheduleToken").val()},
						type:"POST",
						success:function(data){
							$(".b-jdap").load("<%=basePath%>/subject/schedule/sboper/list.htm?rwsId=${rwsId}&subjectId=${subjectId}");
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