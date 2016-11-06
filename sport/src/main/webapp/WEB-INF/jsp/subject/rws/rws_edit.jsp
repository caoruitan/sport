<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
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
				<li class="dot" id="e-xmcddw">
					<a title="项目承担单位、协作单位和人员情况">08 项目承担单位、协作单位和人员...</a>
				</li>
				<li class="dot">
					<a id="e-jfys" title="经费预算">09 经费预算</a>
				</li>
				<li class="dot" id="e-sbgzmx">
					<a title="设备购置明细表">10 设备购置明细表</a>
				</li>
				<li class="dot" id="e-ysly" onclick="showLayout('b-ysly')">
					<a title="预算来源及经费支出情况说明">11 预算来源及经费支出情况说明</a>
				</li>
				<li class="dot" id="e-xbfjf">
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
				${news.content}
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
		<div class="box b-xmcddw"></div>
		<!--10 经费预算-->
		<div class="box b-jfys"></div>
		<!--10 设备购置明细表-->
		<div class="box b-sbgzmx"></div>
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
		<div class="box b-xbfjf"></div>
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
		
		$("#e-jdap").click(function(){
			showLayout('b-jdap');
			$(".b-jdap").load("<%=basePath%>/subject/schedule/sboper/list.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
		});
		
		$("#e-sbgzmx").click(function(){
			showLayout('b-sbgzmx');
			$(".b-sbgzmx").load("<%=basePath%>/subject/device/sboper/list.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
		});
		
		$("#e-xbfjf").click(function(){
			showLayout('b-xbfjf');
			$(".b-xbfjf").load("<%=basePath%>/subject/appropriation/sboper/list.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
		});
		
		$("#e-xmcddw").click(function(){
			showLayout('b-xmcddw');
			$(".b-xmcddw").load("<%=basePath%>/subject/undertaker/sboper/list.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
		});
		
		$("#e-jfys").click(function(){
			showLayout('b-jfys');
			$(".b-jfys").load("<%=basePath%>/subject/rwsbudget/sboper/cost.htm?rwsId=${rws.rwsId}&subjectId=${subjectId}");
		});
	});
</script>