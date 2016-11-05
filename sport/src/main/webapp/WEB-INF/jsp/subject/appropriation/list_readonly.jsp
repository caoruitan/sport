<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">12 需拨付其他单位经费情况</div>
<div class="listBox">
<div class="c">
	<div class="tb">
	<table id="jqGrid-bf"></table>
	<div id="jqGridPager-bf"></div>
	</div>
</div>
<script type="text/javascript">
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
</script>
</div>