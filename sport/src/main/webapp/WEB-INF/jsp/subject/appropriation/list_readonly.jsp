<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="t">09 拨往其他单位经费情况填报</div>
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
			name: 'approId',
			width: 10,
			hidden:true
		}, {
			label: '拨往单位',
			name: 'gainOrg',
			align: "center",
			width: 25
		}, 
		 {
			label: '拨付款额',
			name: 'approAmount',
			align: "center",
			width: 25
		}, 
		 {
			label: '用途说明',
			name: 'describe',
			align: "center",
			width: 25
		}, ],
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