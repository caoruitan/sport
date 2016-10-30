﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>申报书填报</title>
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
		<div class="tip">申报书填报</div>
		<div class="title">${subject.name}<span>${status[sbs.status]}</span></div>
		<div class="btnBox">
			<c:if test='${sbs.status eq "SBADMIN_SP"}'>
				<button class="btn-img" type="" id="tj"><img src="<%=basePath %>/static/img/d-tj.png"/>提交上一级</button>
				<button class="btn-img" type="" id="th"><img src="<%=basePath %>/static/img/d-th.png"/>退回</button>
			</c:if>
			<c:if test='${not empty sbs && sbs.status ne "SBOPER_TB"}'>
				<button class="btn-img" id="xz"><img src="<%=basePath %>/static/img/d-xz.png"/>下载</button>
			</c:if>
		</div>
	</div>

	<div class="ktview">
		<div class="kt-left">
			<div class="lc">
				<div class="line">
					<li class="dot" id="e-sbsfm" onclick="showLayout('b-sbsfm')" >
						<a title="申报书封面">申报书封面</a>
					</li>
					<li class="dot" id="e-xtyj" onclick="showLayout('b-xtyj')" >
						<a title="选题依据">01 选题依据</a>
					</li>
					<li class="dot" id="e-yjmb" onclick="showLayout('b-yjmb')">
						<a title="研究目标和主要研究内容">02 研究目标和主要研究内容</a>
					</li>
					<li class="dot" id="e-jsgj" onclick="showLayout('b-jsgj')" >
						<a title="本项目的技术关键与创新点">03 本项目的技术关键与创新点</a>
					</li>
					<li class="dot" id="e-yjff" onclick="showLayout('b-yjff')" >
						<a title="拟采取的研究方法、主要技术路线、主要指标及可行性分析">04 拟采取的研究方法、主要技术路...</a>
					</li>
					<li class="dot" id="e-syfa" onclick="showLayout('b-syfa')">
						<a title="研究实验方案、实验地点及联合申请单位的分工">05 研究实验方案、实验地点及联合...</a>
					</li>
					<li class="dot" id="e-jdap" onclick="showLayout('b-jdap')">
						<a title="进度安排">06 进度安排</a>
					</li>
					<li class="dot" id="e-yqjg" onclick="showLayout('b-yqjg')">
						<a title="预期结果">07 预期结果</a>
					</li>
					<li class="dot" id="e-gztj" onclick="showLayout('b-gztj')">
						<a title="申报单位现有工作条件和基础">08 申报单位现有工作条件和基础</a>
					</li>
					<li class="dot">
						<a id="e-sqrqk" title="申请人情况">09 申请人情况</a>
					</li>
					<li class="dot">
						<a id="e-jfys" href="0503sbs-jfys.html" target="_blank" title="经费预算">10 经费预算</a>
					</li>
					<li class="dot" id="e-tjyj" onclick="showLayout('b-tjyj')">
						<a title="申报单位推荐意见及提供相关研究工作条件的保证">11 申报单位推荐意见及提供相关研...</a>
					</li>
					<li class="dot" id="e-dwyj" onclick="showLayout('b-dwyj')">
						<a title="其他联合申报单位意见">12 其他联合申报单位意见</a>
					</li>
					<li class="dot" id="e-bmyj" onclick="showLayout('b-bmyj')">
						<a title="相关部门意见">13 相关部门意见</a>
					</li>
					<li class="dot" id="e-spyj" onclick="showLayout('b-spyj')">
						<a title="国家体育总局审批意见">14 国家体育总局审批意见</a>
					</li>
				</div>
			</div>
		</div>
		<div class="kt-right">
			<div class="box" style="height:400px;display: block; background: url(<%=basePath %>/static/img/welcome.png) center center no-repeat;"></div>
			<!--申报书封面-->
			<div class="box b-sbsfm wow bounceInDown">
				<div class="t">申报书封面</div>
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
									<th>申报单位</th>
									<td><input name="createUnitName" type="text" value="${subject.createUnitName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>第一申请人</th>
									<td><input name="creatorName" type="text" value="${subject.creatorName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>通信地址</th>
									<td><input name="address" type="text" value="${sbs.address}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>联系电话</th>
									<td><input name="phone" type="text" value="${sbs.phone}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>传真</th>
									<td><input name="fax" type="text" value="${sbs.fax}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>电子邮箱</th>
									<td><input name="email" type="text" value="${sbs.email}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>完成年限</th>
									<td><input name="years" type="text" value="${sbs.years}" disabled="disabled" /></td>
								</tr>
							</table>
						</form>
						<p class="save-btn">
						</p>
					</div>
				</div>
			</div>
			<!--01 选题依据-->
			<div class="box b-xtyj">
				<div class="t">01 选题依据</div>
				<div class="c">
					<form id="xtyjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="xtyj" disabled="disabled">${sbs.xtyj}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--02 研究目标和主要研究内容-->
			<div class="box b-yjmb">
				<div class="t">02 研究目标和主要研究内容</div>
				<div class="c">
					<form id="yjmbForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yjmb" disabled="disabled">${sbs.yjmb}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--03 本项目的技术关键与创新点-->
			<div class="box b-jsgj">
				<div class="t">03 本项目的技术关键与创新点</div>
				<div class="c">
					<form id="jsgjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="jsgj" disabled="disabled">${sbs.jsgj}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--04 拟采取的研究方法、主要技术路...-->
			<div class="box b-yjff">
				<div class="t">04 拟采取的研究方法、主要技术路...</div>
				<div class="c">
					<form id="yjffForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yjff" disabled="disabled">${sbs.yjff}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--05 研究实验方案、实验地点及联合...-->
			<div class="box b-syfa">
				<div class="t">05 研究实验方案、实验地点及联合...</div>
				<div class="c">
					<form id="syfaForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="syfa" disabled="disabled">${sbs.syfa}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--06 进度安排-->
			<div class="box b-jdap">
				<div class="t">06 进度安排</div>
				<div class="c">
					<form id="jdapForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="jdap" disabled="disabled">${sbs.jdap}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--07 预期结果-->
			<div class="box b-yqjg">
				<div class="t">07 预期结果</div>
				<div class="c">
					<form id="yqjgForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="yqjg" disabled="disabled">${sbs.yqjg}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--08 申报单位现有工作条件和基础-->
			<div class="box b-gztj">
				<div class="t">08 申报单位现有工作条件和基础</div>
				<div class="c">
					<form id="gztjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="gztj" disabled="disabled">${sbs.gztj}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--09 申请人情况-->
			<div class="box b-sqrqk">
				<div class="t">09 申请人情况</div>
                <div class="sqrqk-container"></div>
			</div>
			<!--10 经费预算-->
			<div class="box b-jfys">
				<div class="t">10 经费预算</div>
			</div>
			<!--11 申报单位推荐意见及提供相关研...-->
			<div class="box b-tjyj">
				<div class="t">11 申报单位推荐意见及提供相关研...</div>
				<div class="c">
					<form id="tjyjForm">
						<input type="hidden" name="subjectId" value="${subjectId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
						<textarea style="width:100%;height:400px;" name="tjyj" disabled="disabled">${sbs.tjyj}</textarea>
					</form>
					<p class="save-btn">
					</p>
				</div>
			</div>
			<!--12 其他联合申报单位意见-->
			<div class="box b-dwyj">
				<div class="t">12 其他联合申报单位意见</div>
			</div>
			<!--13 相关部门意见-->
			<div class="box b-bmyj">
				<div class="t">13 相关部门意见</div>
			</div>
			<!--14 国家体育总局审批意见-->
			<div class="box b-spyj">
				<div class="t">14 国家体育总局审批意见</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var showLayout = function(obj) {
			$(".box").hide();
			$('.' + obj).slideDown();
		}
		
		$(function() {
			$('.line li').click(function(){
				$('.line li').removeClass('b-active');
				$(this).addClass("b-active");
			});
			//提交上一级
			$('#tj').dialog({
				id: 'tj',
				title: '提交上一级',
				content: '<div class="dlg-contentbox"><img src="<%=basePath%>/static/img/prompt.gif" />确定要提交到上一级吗？</div>',
				width: 400,
				height: 130,
				ok: function() {
					$.ajax({
						url: "<%=basePath%>/subject/sbadmin/pass.action",
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
				},
				cancel: true
			});
			// 退回
			$('#th').dialog({
				id: 'th',
				title: '请填写退回意见',
				content: '<div class="dlg-contentbox"><textarea placeholder="请填写退回意见" id="approvalComment" name="" style="width:530px" rows="4" cols="10"></textarea></div>',
				width: 600,
				height: 230,
				ok: function() {
					$.ajax({
						url: "<%=basePath%>/subject/sbadmin/unpass.action",
						type: "POST",
						dataType: "JSON",
						data: {
							subjectId : "${subjectId}",
							_csrf : "${_csrf.token}",
							comment : $("#approvalComment").val()
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
				},
				cancel: true
			});
			
			$("#xz").click(function() {
				window.open("<%=basePath%>/sbs/download.action?subjectId=${subjectId}");
			});
			
			$("#e-sqrqk").click(function(){
				showLayout('b-sqrqk');
				$(".sqrqk-container").load("<%=basePath%>/subject/proposer/sbadmin/list.htm?sbsId=${sbs.sbsId}&subjectId=${subjectId}");
			});
		});
	</script>
</body>
