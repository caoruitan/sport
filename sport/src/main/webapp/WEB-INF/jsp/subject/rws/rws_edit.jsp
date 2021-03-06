﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="ktview">
	<div class="kt-left">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrfToken"/>
		<div class="lc">
			<div class="line">
				<li class="ok" id="e-txsm" onclick="showLayout('b-txsm')" >
					<a title="填写说明">填写说明</a>
				</li>
				<li class="dot" id="e-rwsfm" onclick="showLayout('b-rwsfm')" >
					<a title="任务书基本信息">任务书基本信息</a>
				</li>
				<li class="dot" id="e-xtyj" >
					<a title="一到六项文档性内容填报">一到六项文档性内容填报</a>
				</li>
				<li class="dot" id="e-jdap">
					<a title="进度安排">07 进度安排</a>
				</li>
				<li class="dot" id="e-xmcddw">
					<a title="项目承担单位、协作单位和人员情况">08 项目承担单位、协作单位和人员...</a>
				</li>
				<li class="dot" id="e-jfys">
					<a  title="经费预算">09 经费预算</a>
				</li>
				<li class="dot" id="e-sbgzmx">
					<a title="设备购置明细表">10 设备购置明细表</a>
				</li>
				<li class="dot" id="e-ysly">
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
		<!--01 选题依据-->
		<div class="box b-xtyj">
			<div class="t">一到六项文档性内容填报</div>
			<div class="xtyj-container sport-container"></div>
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
			<div class="ysly-container sport-container"></div>
		</div>
		<!--12 需拨付其他单位经费情况-->
		<div class="box b-xbfjf"></div>
		<!--13 共同条款-->
		<div class="box b-gttk">
			<div class="t">13 共同条款</div>
			<div class="c">
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					1、	甲乙双方须共同遵守《国家体育总局体育科学研究与科技服务工作管理办法》及相关规定。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					2、	甲乙双方须严格执行国家体育总局和中国奥委会有关反兴奋剂工作管理规定。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					3、	甲方根据有关规定，监督项目执行情况和经费使用情况。对不符合有关规定和任务书要求的行为，甲方负责提出处理意见，经总局审核批准后实施。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					4、	总局或甲方根据任务书的要求，保证按期拨付项目经费至乙方。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					5、	任务执行过程中，甲方无故终止任务时，所拨经费、物资不得追回，并承担善后处理所发生的费用。甲方提出变更任务书有关内容时，要与乙方协商达成书面协议，并报总局备案。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					6、	乙方必须按季度向甲方和总局报送项目执行情况，逾期不报的，总局或甲方有权暂停拨款。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					7、	乙方须保证提供完成项目任务必要的设备、房屋和研究人员的工作时间。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					8、	乙方因某种原因需要调整项目内容，应向甲方提出申请，经甲方审核后报总局批准实施。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					9、	乙方因某种原因需要终止任务，应视不同情况，部分或全部退回所拨经费。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					10、	甲乙双方若有争议，按国家有关规定和《国家体育总局体育科学研究与科技服务工作管理办法》相应条款处理。若仍不能解决争议，双方可申请仲裁委员会裁决，或向人民法院起诉。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					11、	任务书正式文本一式五份，甲乙方各一份，总局三份。
				</span><br/>
				<span style="margin:10px 30px;line-height:30px;font-size:14px;">
					12、	其它条款：
				</span><br/>
			</div>
		</div>
		<!--14 国家体育总局审批意见-->
		<div class="box b-zjspyj">
			<div class="t">14 国家体育总局审批意见</div>
			<div class="c contentTxt">
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目组织单位（甲方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目组织单位（乙方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">财务负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账户名：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账号：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">开户银行：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目组织单位（乙方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">财务负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账户名：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账号：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">开户银行：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目组织单位（乙方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">项目负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">财务负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账户名：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">账号：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">开户银行：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">资金等匹配条件落实保证方（甲方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">资金等匹配条件落实保证方（甲方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">资金等匹配条件落实保证方（甲方）：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">&nbsp;</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">国家体育总局意见：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">（公章）</div>
				<div style="margin-top:10px;margin-left:10%;line-height:30px;font-size:14px;width:40%;float:left">负责人：</div>
				<div style="margin-top:10px;margin-right:10%;line-height:30px;font-size:14px;width:40%;float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
				<div style="height:50px;width:100%;float:left;">&nbsp;</div>
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
		
		// 一到八项文档性内容填报
		$("#e-xtyj").click(function(){
			showLayout('b-xtyj');
			$(".sport-container").children().remove();
			$(".xtyj-container").load("<%=basePath%>/word/sboper/open",{fileId:"rws${subjectId}${subject.creator}_01.doc",title:"一到六项文档性内容填报",_csrf:$("#csrfToken").val(),readonly:false});
		});
		
		// 一到八项文档性内容填报
		$("#e-ysly").click(function(){
			showLayout('b-ysly');
			$(".sport-container").children().remove();
			$(".ysly-container").load("<%=basePath%>/word/sboper/open",{fileId:"rws${subjectId}${subject.creator}_11.doc",title:"11 预算来源及经费支出情况说明",_csrf:$("#csrfToken").val(),readonly:false});
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