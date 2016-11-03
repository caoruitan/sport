<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">07 进度安排填报</div>
<div class="listBox">
<div class="c">
	<div class="tb">
	<table id="jqGrid-jd"  class="sport-grid"></table>
	<div id="jqGridPager-jd"></div>
	</div>
<script type="text/javascript">
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
</script>
</div>
</div>