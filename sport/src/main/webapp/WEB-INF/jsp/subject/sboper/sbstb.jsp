<%@ page contentType="text/html; charset=UTF-8" %>
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
		<div class="title">国民体质测试器材标注的研究<span>已提交至本单位管理员</span></div>
		<div class="btnBox">
			<button class="btn-img" type="" id="tj"><img src="<%=basePath %>/static/img/d-tj.png"/>提交</button>
		</div>
	</div>

	<div class="ktview">
		<div class="kt-left">
			<div class="lc">
				<div class="line">
					<li class="ok b-active" id="e-zc" onclick="showLayout('b-zc')" >
						<a title="注册">注册</a>
					</li>
					<li class="ok" id="e-txsm" onclick="showLayout('b-txsm')" >
						<a title="填写说明">填写说明</a>
					</li>
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
						<a id="e-sqrqk" href="0504sbs-sqrqk.html" target="_blank" title="申请人情况">09 申请人情况</a>
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
					<li class="dot" id="e-jy" onclick="showLayout('b-jy')">
						<a title="申报书校验提交">申报书校验提交</a>
					</li>
				</div>
			</div>
		</div>
		<div class="kt-right">
			<div class="box" style="height:400px;display: block; background: url(<%=basePath %>/static/img/welcome.png) center center no-repeat;">
			</div>
			<!--注册-->
			<div class="box b-zc">
				<div class="t">注册</div>
				<div class="c">
					<div class="centerBox">
						<img src="<%=basePath %>/static/img/success.png" />
						<p>已注册成功，请继续下一步</p>
					</div>
				</div>
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
			<!--申报书封面-->
			<div class="box b-sbsfm wow bounceInDown">
				<div class="t">申报书封面</div>
				<div class="c">
					<div class="editBox">
						<form id="baseInfoForm">
							<input type="hidden" name="subjectId" value="${subjectId}">
							<input type="hidden" name="rwsId" value="${rwsId}">
							<table class="editTable">
								<tr>
									<th>课题名称</th>
									<td><input name="name" type="text" value="${subject.name}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>申报单位</th>
									<td><input name="name" type="text" value="${subject.organizationName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>第一申请人</th>
									<td><input name="name" type="text" value="${subject.creatorName}" disabled="disabled" /></td>
								</tr>
								<tr>
									<th>通信地址</th>
									<td><input name="name" type="text" value="" /></td>
								</tr>
								<tr>
									<th>联系电话</th>
									<td><input name="name" type="text" value="" /></td>
								</tr>
								<tr>
									<th>传真</th>
									<td><input name="name" type="text" value="" /></td>
								</tr>
								<tr>
									<th>电子邮箱</th>
									<td><input name="name" type="text" value="" /></td>
								</tr>
								<tr>
									<th>完成年限</th>
									<td><input name="name" type="text" value="" /></td>
								</tr>
							</table>
						</form>
						<p class="save-btn">
							<button class="btn-red btn-size-big" onclick="submit('baseInfoForm')">保存</button>
							<button class="btn-wisteria btn-size-big" onclick="reset('baseInfoForm')">重置</button>
						</p>
					</div>
				</div>
			</div>
			<!--01 选题依据-->
			<div class="box b-xtyj">
				<div class="t">01 选题依据</div>
				<div class="c">
					<textarea class="ckeditor" name="editor1"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--02 研究目标和主要研究内容-->
			<div class="box b-yjmb">
				<div class="t">02 研究目标和主要研究内容</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-yjmb"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--03 本项目的技术关键与创新点-->
			<div class="box b-jsgj">
				<div class="t">03 本项目的技术关键与创新点</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-jsgj"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--04 拟采取的研究方法、主要技术路...-->
			<div class="box b-yjff">
				<div class="t">04 拟采取的研究方法、主要技术路...</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-yjff"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--05 研究实验方案、实验地点及联合...-->
			<div class="box b-syfa">
				<div class="t">05 研究实验方案、实验地点及联合...</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-syfa"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--06 进度安排-->
			<div class="box b-jdap">
				<div class="t">06 进度安排</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-jdap"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--07 预期结果-->
			<div class="box b-yqjg">
				<div class="t">07 预期结果</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-yqjg"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--08 申报单位现有工作条件和基础-->
			<div class="box b-gztj">
				<div class="t">08 申报单位现有工作条件和基础</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-gztj"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
					</p>
				</div>
			</div>
			<!--09 申请人情况-->
			<div class="box b-sqrqk">
				<div class="t">09 申请人情况</div>
			</div>
			<!--10 经费预算-->
			<div class="box b-jfys">
				<div class="t">10 经费预算</div>
			</div>
			<!--11 申报单位推荐意见及提供相关研...-->
			<div class="box b-tjyj">
				<div class="t">11 申报单位推荐意见及提供相关研...</div>
				<div class="c">
					<textarea class="ckeditor" name="editor-tjyj"></textarea>
					<p class="save-btn">
						<button class="btn-red btn-size-big" type="">保存</button>
						<button class="btn-wisteria btn-size-big" type="">重置</button>
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
			<!--申报书校验提交-->
			<div class="box b-jy">
				<div class="t">申报书校验提交</div>
				<div class="c">
					<button class="btn-red btn-size-big" type="">开始校验</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var submit = function(formname) {
			$("#" + formname).submit();
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
		});
	</script>
</body>
