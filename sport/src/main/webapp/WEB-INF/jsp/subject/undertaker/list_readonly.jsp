<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">08 项目承担单位、协作单位和人员情况</div>
<div class="listBox">
<div class="c">
	<div class="tb">
	<table id="jqGrid-undertaker"></table>
	<div id="jqGridPager-undertaker"></div>
	</div>
</div>
<script type="text/javascript">
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
	$.each(datas,function(i,v){
		jQuery("#jqGrid-undertaker").jqGrid('addRowData', i + 1, datas[i]);
	});
</script>
</div>