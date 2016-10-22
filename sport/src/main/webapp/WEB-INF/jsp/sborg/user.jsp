<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/ztree/jquery.ztree.core.min.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/plugin/location.js"></script>
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
	<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/yh.png" />${org.fullName}</div>
			<div class="returnBtn orgdetail-returnBtn">返回列表</div>
		</div>
		<div class="listBox">
			<table id="orgDetailDiv"></table>
    		<div id="jqGridPager"></div>
		</div>
<script type="text/javascript"> 
        $(function () {
            $("#orgDetailDiv").jqGrid({
            	url: "<%=basePath %>/sborg/kjsadmin/userDatas.action?orgId=${org.orgId}",
            	datatype: "json",
            	mtype: "GET",
				colModel: [
					{name:'userId',align:"center", width:20,hidden:true},
					{name:'hasOpr',align:"center", width:20,hidden:true},
					{label:"用户名",name:'loginName',align:"center", width:20, sorttype:"date"},
					{label:"真实姓名",name:'userName', align:"center", width:15},
					{label:"证件类型",name:'credTypeName', width:10, align:"center",sorttype:"float"},
					{label:"证件编号",name:'credNo', width:30, align:"center",sorttype:"float"},		
					{label:"所属部门",name:'dept', width:10,align:"center",sorttype:"float"}	
	              ],
                autowidth:true,
				viewrecords: true,
                height: 200,
                rowNum: 20,
                multiselect: true,
                pager: "#jqGridPager"
            });
            //$("#orgDetailDiv").addRowData(1,$.parseJSON('${user}'));
			doResize(); 
        });
   </script>
</body>
</html>