<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="t">10 设备购置明细表</div>
<div class="listBox">
<div class="c">
	<div class="tb">
	<table id="jqGrid-sbgz"  class="sport-grid"></table>
	<div id="jqGridPager-sbgz"></div>
	</div>
<script type="text/javascript">
	
	$("#jqGrid-sbgz").jqGrid({
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
		}],
		viewrecords: true,
		width: 860,
		rowNum: 20,
		multiselect: true,
		pager: "#jqGridPager-sbgz"
	});
	
	var datas = $.parseJSON('${ssDatas}');
	$.each(datas,function(i,v){
		jQuery("#jqGrid-sbgz").jqGrid('addRowData', i + 1, datas[i]);
	});
</script>
</div>
</div>