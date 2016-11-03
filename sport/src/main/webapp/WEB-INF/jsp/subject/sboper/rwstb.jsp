<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>任务书填报</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/sport.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/sport.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/layer/layer.js"></script>
	
	<!--jqgrid-->
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	
	<!--lhgdialog-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<script type="text/ecmascript" src="<%=basePath %>/static/js/lhgdialog/lhgdialog.js"></script>

	<!--checkbox-->
	<link rel="stylesheet" href="<%=basePath %>/static/css/square/red.css">
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/icheck/icheck.js"></script>
	
	<script src="<%=basePath %>/static/js/ckeditor/ckeditor.js"></script>
	<style type="text/css">
		body {
			background: #F6F6F6;
		}
		
		.titleBox1 {
			box-sizing: border-box;
			width: 1200px;
			height: 50px;
			border: 1px solid #e4e4e4;
			border-top: 2px solid #F96A66;
			background: #ffffff;
			position: relative;
			color: #73819A;
			margin: 0 auto;
			margin-bottom: 10px;
		}
		
		.titleBox1 .tip {
			position: absolute;
			top: 6px;
			left: -4px;
			background: url(<%=basePath %>/static/img/tip1.png);
			color: #FFFFFF;
			padding-left: 14px;
			line-height: 32px;
			height: 32px;
			width: 114px;
		}
		
		.titleBox1 .btnBox {
			position: absolute;
			top: 10px;
			right: 0px;
		}
		
		.titleBox1 .title {
			width: 1000px;
			height: 46px;
			line-height: 46px;
			font-size: 18px;
			font-family: "微软雅黑";
			text-align: left;
			padding-left: 120px;
		}
		
		.titleBox1 .title span {
			font-size: 14px;
			font-family: "微软雅黑";
			text-align: left;
			color: #ff3300;
			margin-left: 10px;
		}
		
		.ktview {
			width: 1200px;
			margin: 0 auto;
			height: auto;
			min-height: 500px;
		}
		
		.ktview .kt-right {
			float: left;
			width: 890px;
			background: #ffffff;
			min-height: 660px;
			margin: 0 0 0 10px;
			border: 1px solid #e4e4e4;
		}
		
		.ktview .kt-left {
			float: left;
			width: 300px;
			z-index: 10;
		}
		
		.ktview .kt-left .lc {
			margin: 0px 0 0 0px;
			min-width: 100px;
			width: 100%;
			height: auto;
			min-height: 500px;
			float: left;
			border: 1px solid #D7D7D7;
			padding: 10px;
			background: #fafafa;
		}
		
		.line {
			float: left;
			position: relative;
			width: 280px;
			height: auto;
			border-left: 3px double #d2d2d2;
			margin: 10px 0 0 10px;
		}
		
		.line li {
			width: 100%;
			overflow: hidden;
			float: left;
			background: red;
			height: auto;
			cursor: pointer;
			border: 1px solid #ffffff;
		}
		
		.line li a {
			width: 240px;
			overflow: hidden;
			text-decoration: none;
		}
		
		.line li.dot {
			background: url(<%=basePath %>/static/img/kt-edit.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.ok {
			background: url(<%=basePath %>/static/img/kt-ok.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.cur {
			background: url(<%=basePath %>/static/img/kt-cur.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 15px -11px;
		}
		
		.line li.b-active {
			color: #ff3300 !important;
			border-radius: 10px 2px 2px 10px;
			background-color: #f2f2f2;
			border: 1px solid #aaa;
			
		}
		.line li:hover {
			border-radius: 10px 0 0 10px;
			background-color: #f2f2f2;
			border: 1px solid #aaa;
		}
		
		.box {
			display: none;
			width: 100%;
			box-sizing: border-box;
			padding: 10px 10px 10px 10px;
		}
		
		.box .t {
			width: 100%;
			height: 40px;
			line-height: 40px;
			text-align: center;
			font-size: 18px;
			color: #73819A;
			font-weight: bold;
			border-bottom: 1px solid #e4e4e4;
		}
		
		.box .c {
			width: 100%;
			height: auto;
			margin: 10px 0 0 0;
		}
		
		.box .c .centerBox {
			width: 100%;
			margin: 20px 0 0 0;
			text-align: center;
		}
		
		.box .c .centerBox img {
			width: 68px;
			margin: 20px auto;
		}
	</style>
</head>

<body>

	<div class="titleBox1">
		<div class="tip">任务书填报</div>
		<div class="title">
			${subject.name}<span>${status[rws.status]}</span>
			<c:if test='${rws.status eq "BACK" }'><a style="font-size:14px;color:0000ff" id="viewComment" href="#">查看审批意见</a></c:if>
		</div>
		<div class="btnBox">
			<c:if test='${not empty rws && (rws.status eq "SBOPER_TB" || rws.status eq "BACK")}'>
				<button class="btn-img" id="tj"><img src="<%=basePath %>/static/img/d-tj.png"/>校验提交</button>
			</c:if>
			<c:if test='${not empty rws && rws.status ne "SBOPER_TB"}'>
				<button class="btn-img" id="xz"><img src="<%=basePath %>/static/img/d-xz.png"/>下载</button>
			</c:if>
		</div>
	</div>

	<div class="ktview">
		<div class="kt-left">
			<div class="lc">
				<div class="line">
					<li class="ok" id="e-txsm" onclick="showLayout('b-txsm')" >
						<a title="填写说明">填写说明</a>
					</li>
					<li class="dot" id="e-rwsfm" onclick="showLayout('b-rwsfm')" >
						<a title="任务书基本信息">任务书基本信息</a>
					</li>
					<li class="dot" id="e-yjmb" onclick="showLayout('b-yjmb')">
						<a title="研究目标和主要研究内容">01 研究目标和主要研究内容</a>
					</li>
					<li class="dot" id="e-jsgj" onclick="showLayout('b-jsgj')" >
						<a title="本项目的技术关键与创新点">02 本项目的技术关键与创新点</a>
					</li>
					<li class="dot" id="e-yjff" onclick="showLayout('b-yjff')" >
						<a title="采取的研究方法、主要技术路线、主要指标及可行性分析">03 采取的研究方法、主要技术路...</a>
					</li>
					<li class="dot" id="e-syfa" onclick="showLayout('b-syfa')">
						<a title="研究实验方案、实验地点及联合申请单位的分工">04 研究实验方案、实验地点及联合...</a>
					</li>
					<li class="dot" id="e-yqjg" onclick="showLayout('b-yqjg')">
						<a title="预期结果">05 预期结果</a>
					</li>
					<li class="dot" id="e-gztj" onclick="showLayout('b-gztj')">
						<a title="承担单位现有工作条件和基础">06 承担单位现有工作条件和基础</a>
					</li>
					<li class="dot" id="e-jdap">
						<a title="进度安排">07 进度安排</a>
					</li>
					<li class="dot" id="e-xmcddw" onclick="showLayout('b-xmcddw')">
						<a title="项目承担单位、协作单位和人员情况">08 项目承担单位、协作单位和人员...</a>
					</li>
					<li class="dot">
						<a id="e-jfys" href="0602rws-jfys.html" target="_blank" title="经费预算">09 经费预算</a>
					</li>
					<li class="dot" id="e-sbgzmx" onclick="showLayout('b-sbgzmx')">
						<a title="设备购置明细表">10 设备购置明细表</a>
					</li>
					<li class="dot" id="e-ysly" onclick="showLayout('b-ysly')">
						<a title="预算来源及经费支出情况说明">11 预算来源及经费支出情况说明</a>
					</li>
					<li class="dot" id="e-xbfjf" onclick="showLayout('b-xbfjf')">
						<a title="需拨付其他单位经费情况">12 需拨付其他单位经费情况</a>
					</li>
					<li class="dot" id="e-gttk" onclick="showLayout('b-gttk')">
						<a title="共同条款">13 共同条款</a>
					</li>
					<li class="dot" id="e-zjspyj" onclick="showLayout('b-zjspyj')">
						<a title="国家体育总局审批意见">14 国家体育总局审批意见</a>
					</li>
				</div>
			</div>
		</div>
		<div class="kt-right">
			<div class="box" style="height:400px;display: block; background: url(<%=basePath %>/static/img/welcome.png) center center no-repeat;">
			</div>
			<!--填写说明-->
			<div class="box b-txsm">
				<div class="t">填写说明</div>
				<div class="c contentTxt">
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
				</div>
			</div>
			<!--任务书封面-->
			<div class="box b-rwsfm wow bounceInDown">
				<div class="t">任务书封面</div>
				<div class="c">
					<div class="editBox">
						<form id="baseInfoForm">
							<input type="hidden" name="subjectId" value="${subjectId}">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
							<table class="editTable">
								<tr>
									<th>课题名称</th>
									<td><input name="name" type="text" value="${subject.name}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>课题组织单位</th>
									<td><input name="organizationName" type="text" value="${subject.organizationName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>课题承担单位</th>
									<td><input name="createUnitName" type="text" value="${subject.createUnitName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>课题负责人</th>
									<td><input name="creatorName" type="text" value="${subject.creatorName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>课题开始日期</th>
									<td><input name="beginDate" type="text" value="${subject.beginDate}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>课题结束日期</th>
									<td><input name="endDate" type="text" value="${subject.endDate}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>通信地址</th>
									<td><input name="address" type="text" value="${rws.address}" /></td>
								</tr>
								<tr>
									<th>联系电话</th>
									<td><input name="phone" type="text" value="${rws.phone}" /></td>
								</tr>
								<tr>
									<th>课题协作单位</th>
									<td><input name="cooperateOrg" type="text" value="${rws.cooperateOrg}" /></td>
								</tr>
							</table>
						</form>
						<p class="save-btn">
							<button id="baseInfoFormSubmit" class="btn-red btn-size-big" onclick="saveBaseInfo()">保存</button>
							<button class="btn-wisteria btn-size-big" onclick="reset('baseInfoForm')">重置</button>
						</p>
					</div>
				</div>
			</div>
			<!--01 研究目标和主要研究内容-->
			<div class="box b-yjmb">
				<div class="t">01 研究目标和主要研究内容</div>
				<div class="c">
					<form id="yjmbForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yjmb">${rws.yjmb}</textarea>
					</form>
					<p class="save-btn">
						<button id="yjmbFormSubmit" class="btn-red btn-size-big" onclick="saveYjmb()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('yjmbForm')">重置</button>
					</p>
				</div>
			</div>
			<!--02 本项目的技术关键与创新点-->
			<div class="box b-jsgj">
				<div class="t">02 本项目的技术关键与创新点</div>
				<div class="c">
					<form id="jsgjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="jsgj">${rws.jsgj}</textarea>
					</form>
					<p class="save-btn">
						<button id="jsgjFormSubmit" class="btn-red btn-size-big" onclick="saveJsgj()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('jsgjForm')">重置</button>
					</p>
				</div>
			</div>
			<!--03 采取的研究方法、主要技术路...-->
			<div class="box b-yjff">
				<div class="t">03 采取的研究方法、主要技术路...</div>
				<div class="c">
					<form id="yjffForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yjff">${rws.yjff}</textarea>
					</form>
					<p class="save-btn">
						<button id="yjffFormSubmit" class="btn-red btn-size-big" onclick="saveYjff()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('yjffForm')">重置</button>
					</p>
				</div>
			</div>
			<!--04 研究实验方案、实验地点及联合...-->
			<div class="box b-syfa">
				<div class="t">04 研究实验方案、实验地点及联合...</div>
				<div class="c">
					<form id="syfaForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="syfa">${rws.syfa}</textarea>
					</form>
					<p class="save-btn">
						<button id="syfaFormSubmit" class="btn-red btn-size-big" onclick="saveSyfa()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('syfaForm')">重置</button>
					</p>
				</div>
			</div>
			<!--05 预期结果-->
			<div class="box b-yqjg">
				<div class="t">05 预期结果</div>
				<div class="c">
					<form id="yqjgForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yqjg">${rws.yqjg}</textarea>
					</form>
					<p class="save-btn">
						<button id="yqjgFormSubmit" class="btn-red btn-size-big" onclick="saveYqjg()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('yqjgForm')">重置</button>
					</p>
				</div>
			</div>
			<!--06 承担单位现有工作条件和基础-->
			<div class="box b-gztj">
				<div class="t">06 承担单位现有工作条件和基础</div>
				<div class="c">
					<form id="gztjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="gztj">${rws.gztj}</textarea>
					</form>
					<p class="save-btn">
						<button id="gztjFormSubmit" class="btn-red btn-size-big" onclick="saveGztj()">保存</button>
						<button class="btn-wisteria btn-size-big" onclick="reset('gztjForm')">重置</button>
					</p>
				</div>
			</div>
			<!--07 进度安排-->
			<div class="box b-jdap"></div>
			<!--08 项目承担单位、协作单位和人员情况-->
			<div class="box b-xmcddw">
				<div class="t">08 项目承担单位、协作单位和人员情况</div>
				<div class="c">
					<div class="opBtnBox" >
						<div class="fl-l">
							<button class="btn-red" id="ry">+ 新增</button>
						</div>
						<div class="fl-r">
							<button class="btn-wisteria">删除</button>
						</div>
					</div>
					<div class="tb">
						<table id="jqGrid-ry"></table>
						<div id="jqGridPager-ry"></div>
					</div>
				</div>
			</div>
			<!--10 经费预算-->
			<div class="box b-jfys">
				<div class="t">09 经费预算</div>
			</div>
			<!--10 设备购置明细表-->
			<div class="box b-sbgzmx">
				<div class="t">10 设备购置明细表</div>
				<div class="c">
					<div class="opBtnBox" >
						<div class="fl-l">
							<button class="btn-red" id="sb">+ 新增</button>
						</div>
						<div class="fl-r">
							<button class="btn-wisteria">删除</button>
						</div>
					</div>
					<div class="tb">
						<table id="jqGrid-sb"></table>
						<div id="jqGridPager-sb"></div>
					</div>
				</div>
			</div>
			<!--11 预算来源及经费支出情况说明-->
			<div class="box b-ysly">
				<div class="t">11 预算来源及经费支出情况说明</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-ysly"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--12 需拨付其他单位经费情况-->
			<div class="box b-xbfjf">
				<div class="t">12 需拨付其他单位经费情况</div>
				<div class="c">
					<div class="opBtnBox" >
						<div class="fl-l">
							<button class="btn-red" id="bf">+ 新增</button>
						</div>
						<div class="fl-r">
							<button class="btn-wisteria">删除</button>
						</div>
					</div>
					<div class="tb">
						<table id="jqGrid-bf"></table>
						<div id="jqGridPager-bf"></div>
					</div>
				</div>
			</div>
			<!--13 共同条款-->
			<div class="box b-gttk">
				<div class="t">13 共同条款</div>
				<div class="c">
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
					初学交互设计的新人，因为没有做过任何的设计项目，所以不知道整个交互设计师的工作流程会有哪些内容，今天这篇文章请了专业科班出身的设计师来科普他们…
				</div>
			</div>
			<!--14 国家体育总局审批意见-->
			<div class="box b-zjspyj">
				<div class="t">14 国家体育总局审批意见</div>
				<div class="c contentTxt redfont">
					同意
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var saveBaseInfo = function(formname) {
			$('#baseInfoFormSubmit').attr("disabled",true);
			$('#baseInfoFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsBaseInfo.action",
				type: "POST",
				dataType: "JSON",
				data: $("#baseInfoForm").serialize(),
				error: function () {
					$('#baseInfoFormSubmit').removeAttr("disabled");
					$("#baseInfoFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#baseInfoFormSubmit').removeAttr("disabled");
					$("#baseInfoFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveYjmb = function() {
			$('#yjmbFormSubmit').attr("disabled",true);
			$('#yjmbFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsYjmb.action",
				type: "POST",
				dataType: "JSON",
				data: {
					yjmb : $("#yjmbForm textarea[name='yjmb']").val(),
					subjectId : $("#yjmbForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#yjmbFormSubmit').removeAttr("disabled");
					$("#yjmbFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#yjmbFormSubmit').removeAttr("disabled");
					$("#yjmbFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveJsgj = function() {
			$('#jsgjFormSubmit').attr("disabled",true);
			$('#jsgjFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsJsgj.action",
				type: "POST",
				dataType: "JSON",
				data: {
					jsgj : $("#jsgjForm textarea[name='jsgj']").val(),
					subjectId : $("#jsgjForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#jsgjFormSubmit').removeAttr("disabled");
					$("#jsgjFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#jsgjFormSubmit').removeAttr("disabled");
					$("#jsgjFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveYjff = function() {
			$('#yjffFormSubmit').attr("disabled",true);
			$('#yjffFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsYjff.action",
				type: "POST",
				dataType: "JSON",
				data: {
					yjff : $("#yjffForm textarea[name='yjff']").val(),
					subjectId : $("#yjffForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#yjffFormSubmit').removeAttr("disabled");
					$("#yjffFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#yjffFormSubmit').removeAttr("disabled");
					$("#yjffFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveSyfa = function() {
			$('#syfaFormSubmit').attr("disabled",true);
			$('#syfaFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsSyfa.action",
				type: "POST",
				dataType: "JSON",
				data: {
					syfa : $("#syfaForm textarea[name='syfa']").val(),
					subjectId : $("#syfaForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#syfaFormSubmit').removeAttr("disabled");
					$("#syfaFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#syfaFormSubmit').removeAttr("disabled");
					$("#syfaFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveYqjg = function() {
			$('#yqjgFormSubmit').attr("disabled",true);
			$('#yqjgFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsYqjg.action",
				type: "POST",
				dataType: "JSON",
				data: {
					yqjg : $("#yqjgForm textarea[name='yqjg']").val(),
					subjectId : $("#yqjgForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#yqjgFormSubmit').removeAttr("disabled");
					$("#yqjgFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#yqjgFormSubmit').removeAttr("disabled");
					$("#yqjgFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var saveGztj = function() {
			$('#gztjFormSubmit').attr("disabled",true);
			$('#gztjFormSubmit').text('正在提交...');
			$.ajax({
				url: "<%=basePath%>/subject/sboper/saveRwsGztj.action",
				type: "POST",
				dataType: "JSON",
				data: {
					gztj : $("#gztjForm textarea[name='gztj']").val(),
					subjectId : $("#gztjForm input[name='subjectId']").val(),
					_csrf : "${_csrf.token}"
				},
				error: function (obj) {
					$('#gztjFormSubmit').removeAttr("disabled");
					$("#gztjFormSubmit").text("保存");
					layer.msg("保存失败，请稍后重试！");
				},
				success: function (obj) {
					$('#gztjFormSubmit').removeAttr("disabled");
					$("#gztjFormSubmit").text("保存");
					layer.msg("保存成功！");
				}
			});
		}
		
		var reset = function(formname) {
			$("#" + formname)[0].reset();
		}
		
		var showLayout = function(obj) {
			$(".box").hide();
			$('.' + obj).slideDown();
		}
		
		$(function() {
			$('.line li').click(function(){
				$('.line li').removeClass('b-active');
				$(this).addClass("b-active");
			});
			
			$("#tj").click(function() {
				$.ajax({
					url: "<%=basePath%>/subject/sboper/checkAndSubmitRws.action",
					type: "POST",
					dataType: "JSON",
					data: {
						subjectId : "${subjectId}",
						_csrf : "${_csrf.token}"
					},
					error: function (obj) {
						$('#tj').removeAttr("disabled");
						layer.msg("提交失败，请稍后重试！");
					},
					success: function (obj) {
						$('#tj').removeAttr("disabled");
						layer.msg("提交成功！");
					}
				});
			});
			
			$("#xz").click(function() {
				window.open("<%=basePath%>/rws/download.action?subjectId=${subjectId}");
			});
			
			$("#viewComment").dialog({
				id: 'tj',
				title: '审批意见',
				content: '<div class="dlg-contentbox">${rws.comment}</div>',
				width: 400,
				height: 130,
				ok: true
			});
			//人员情况
			$("#jqGrid-ry").jqGrid({
				datatype: "local",
				colModel: [{
					name: 'No',
					index: 'No.',
					width: 5,
					sorttype: "int",
					align: "center"
				}, {
					name: '姓名',
					index: '姓名',
					width: 10,
					sorttype: "date"
				},  {
					name: '类型',
					index: '类型',
					align: "center",
					width: 10,
					sorttype: "date"
				}, {
					name: '单位',
					index: '单位',
					align: "center",
					width: 25
				}, {
					name: '出生年月',
					index: '出生年月',
					width: 20,
					align: "center",
					sorttype: "float"
				}, {
					name: '职务职称',
					index: '职务职称',
					width: 10,
					align: "left",
					sorttype: "float"
				}, {
					name: '研究分工',
					index: '研究分工',
					width: 10,
					align: "center",
					sorttype: "float"
				}, {
					name: '操作',
					index: '操作',
					width: 10,
					align: "center",
					sortable: false
				}],
//				autowidth: true,
				viewrecords: true,
				height: 200,
				width: 860,
				rowNum: 20,
				multiselect: true,
				pager: "#jqGridPager-ry"
			});

			var mydata = [{
					No: "1",
					姓名: "龙一飞",
					类型:"负责人",
					单位: "中国羽毛球协会",
					出生年月: "1982-04-03",
					职务职称: "研究员",
					研究分工: "人体力学",
					操作: "编辑"
				},

			];
			for (var i = 0; i <= mydata.length; i++) {
				jQuery("#jqGrid-ry").jqGrid('addRowData', i + 1, mydata[i]);
			}
			
			//设备购置
			$("#jqGrid-sb").jqGrid({
				datatype: "local",
				colModel: [{
					name: 'No',
					index: 'No.',
					width: 5,
					sorttype: "int",
					align: "center"
				}, {
					name: '设备名称',
					index: '设备名称',
					width: 10,
					sorttype: "date"
				},  {
					name: '购置或试制的理由用途',
					index: '购置或试制的理由用途',
					align: "center",
					width: 15,
					sorttype: "date"
				}, {
					name: '设备型号及指标',
					index: '设备型号及指标',
					align: "center",
					width: 10
				}, {
					name: '单价',
					index: '单价',
					width: 5,
					align: "center",
					sorttype: "float"
				}, {
					name: '数量',
					index: '数量',
					width: 5,
					align: "center",
					sorttype: "float"
				}, {
					name: '总价',
					index: '总价',
					width: 5,
					align: "center",
					sorttype: "float"
				}, {
					name: '申请从专项经费中列支数',
					index: '申请从专项经费中列支数',
					width: 15,
					align: "center",
					sortable: false
				}],
//				autowidth: true,
				viewrecords: true,
				height: 200,
				width: 860,
				rowNum: 20,
				multiselect: true,
				pager: "#jqGridPager-sb"
			});

			var mydata = [{
					No: "1",
					设备名称: "龙一飞",
					购置或试制的理由用途:"负责人",
					设备型号及指标: "中国羽毛球协会",
					单价: "2300",
					数量: "3",
					总价: "6900",
					申请从专项经费中列支数: "1"
				},

			];
			for (var i = 0; i <= mydata.length; i++) {
				jQuery("#jqGrid-sb").jqGrid('addRowData', i + 1, mydata[i]);
			}
			
			//拨付
			$("#jqGrid-bf").jqGrid({
				datatype: "local",
				colModel: [{
					name: 'No',
					index: 'No.',
					width: 5,
					sorttype: "int",
					align: "center"
				}, {
					name: '拨往单位',
					index: '拨往单位',
					width: 20,
					sorttype: "date"
				},  {
					name: '拨付数额万元',
					index: '拨付数额万元',
					align: "center",
					width: 15,
					sorttype: "date"
				}, {
					name: '用途说明',
					index: '用途说明',
					align: "center",
					width: 60
				}],
//				autowidth: true,
				viewrecords: true,
				height: 200,
				width: 860,
				rowNum: 20,
				multiselect: true,
				pager: "#jqGridPager-bf"
			});

			var mydata = [{
					No: "1",
					拨往单位: "拨往单位",
					拨付数额万元: "34443",
					用途说明: "用途说明"
				},
			];
			for (var i = 0; i <= mydata.length; i++) {
				jQuery("#jqGrid-bf").jqGrid('addRowData', i + 1, mydata[i]);
			}
			//项目承担
			$('#ry').dialog({
				id: 'ry',
				title: '项目人员情况填报',
				content: 'url:0603rws-xm-xz.html',
				width: 800,
				height: 430,
				ok: true,
				cancel: true
			});
			//设备
			$('#sb').dialog({
				id: 'sb',
				title: '设备',
				content: 'url:0605rws-sb-xz.html',
				width: 760,
				height: 480,
				ok: true,
				cancel: true
			});
			//拨付
			$('#bf').dialog({
				id: 'bf',
				title: '拨往其他单位的经费',
				content: 'url:0606rws-bf-xz.html',
				width: 840,
				height: 260,
				ok: true,
				cancel: true
			});
			
			$("#e-jdap").click(function(){
				showLayout('b-jdap');
				$(".b-jdap").load("<%=basePath%>/subject/schedule/sboper/list.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
			});
			
		});
	</script>
</body>
