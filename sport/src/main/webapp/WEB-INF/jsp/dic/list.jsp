<%@page contentType="text/html; charset=UTF-8" %>
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
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<div class="titleBox">
	<div class="title"><img src="<%=basePath%>/static/img/m-zd.png" />数据字典</div>
	<div class="searchBtn">查询</div>
</div>
<div class="searchBox" style="display: none">
	<dt>名称</dt>
	<dd><input type="text" id="dicSearchName"/></dd>
	<dt>编码</dt>
	<dd><input type="text" id="dicSearchCode"/></dd>
	<button class="search-btn" id="dicQuery">查询</button>
</div>
<div class="twoColumn">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
	<div class="t-left">
		<div class="leftBox">
			<p class="lm">数据字典<a></a></p><p>
				<ul id="dicTreeDiv" class="ztree"></ul>
			</p>
		</div>
	</div>
	<div class="t-right">
		<div class="listBox">
			<div class="opBtnBox">
				<div class="fl-l">
					<a href="javascript:;;"><button class="btn-red sport-dic-create-btnskip">+ 创建</button></a>
				</div>
				<div class="fl-r">
					<button class="btn-wisteria sport-dic-delete">删除</button>
				</div>
			</div>
			<table id="dicGridDiv"></table>
			<div id="jqGridPager"></div>
		</div>
	</div>
<script type="text/javascript">
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	     xhr.setRequestHeader(header, token);
	});
	var setting = {
		async: {  
			autoParam:["id=pId"],
			enable: true,  
	        url: "<%=basePath%>/dic/dataTypes.action"  
	    },  
	    check: {  
	        enable: false,
	        idKey: "dicId",  
            pIdKey: "pId",  
            rootPId: -1 
	    },  
	    data: {  
	        simpleData: {  
	           	enable: true  
	        }  
	    },
	    callback:{
	    	onClick:function(event, treeId, node){
	    		$("#dicGridDiv").jqGrid('setGridParam',{datatype:'json',postData:{pCode:node.code}}).trigger('reloadGrid');
	    	},
		    onAsyncSuccess:function(event, treeId, treeNode, msg){
		    	var zTree = $.fn.zTree.getZTreeObj(treeId);  
		    	var node = null;
		    	var id = "${pCode}";
		        //获取节点  
		    	if(Sport.isNull(id)){
			         var nodes = zTree.getNodes();  
			         if (nodes.length>0)   {  
			             node = nodes[0]; 
			         }  
		    	}else{
		         	node = zTree.getNodeByParam("id", id, null);
		         	if(node ==null){
		         		var nodes = zTree.getNodes();  
				         if (nodes.length>0)   {  
				             node = nodes[0]; 
				         }  
		         	}
		    	}
		    	zTree.selectNode(node);
		        zTree.setting.callback.onClick(null, treeId, node);
		    }
	    }
	};
	$(function(){
		$.fn.zTree.init($("#dicTreeDiv"), setting);
		$("#dicGridDiv").jqGrid({
			url: "<%=basePath%>/dic/datas.action",
			mtype: "GET",
			//datatype: "json",
			datatype:"local",
			colModel: [
				{name:'id',align:"center", width:20,hidden:true},
				{name:'pCode',align:"center", width:20,hidden:true},
				{label:"编号",name:'code', align:"center", width:15},
				{label:"名称",name:'name',align:"center", width:20},
				{label:"值",name:'value', width:10, align:"center"},
				{label:"操作",name:'操作', width:10, align:"center",sortable:false,formatter:function(value, grid, rows, state){
					return "<a href='javascript:;;' class='sport-dic-edit' data-id='"+rows.id+"'>编辑</a>";
				}}
              ],
			viewrecords: true,
			height: 200,
			rowNum: 20,
			multiselect: true,
			pager: "#jqGridPager",
			onSelectRow: function (rowid, status) {
				var selectedIds = $("#dicGridDiv").jqGrid("getGridParam", "selarrrow");
            },
            onSelectAll: function (aRowids, status) {
            }
	    });
		doResize(); 
		$("#dicGridDiv").setGridWidth($(".listBox").width());
	})
</script>
</div>