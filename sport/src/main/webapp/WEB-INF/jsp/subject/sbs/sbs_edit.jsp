<%@page import="org.cd.sport.vo.UserVo"%>
<%@page import="org.cd.sport.utils.AuthenticationUtils"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
    request.setAttribute("_time", System.currentTimeMillis());
    UserVo user = AuthenticationUtils.getUser();
	request.setAttribute("loginUser", user);
%>
<div class="ktview">
	<div class="kt-left">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrfToken"/>
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
				<li class="dot" id="e-xtyj" >
					<a title="一到八项文档性内容填报">一到八项文档性内容填报</a>
				</li>
				<li class="dot" id="e-sqrqk">
					<a  title="申请人情况">09 申请人情况</a>
				</li>
				<li class="dot" id="e-jfys" >
					<a title="经费预算">10 经费预算</a>
				</li>
				<li class="dot" id="e-zcsm" >
					<a title="经费来源及经费支出情况说明">11 经费来源及经费支出情况说明</a>
				</li>
				<li class="dot" id="e-tjyj">
					<a title="申报单位推荐意见及提供相关研究工作条件的保证">12 申报单位推荐意见及提供相关研...</a>
				</li>
				<li class="dot" id="e-dwyj" onclick="showLayout('b-dwyj')">
					<a title="其他联合申报单位意见">13 其他联合申报单位意见</a>
				</li>
				<li class="dot" id="e-bmyj" onclick="showLayout('b-bmyj')">
					<a title="相关部门意见">14 相关部门意见</a>
				</li>
				<li class="dot" id="e-spyj">
					<a title="国家体育总局审批意见">15 国家体育总局审批意见</a>
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
				${news.content}
			</div>
		</div>
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
								<td><input name="address" type="text" value="${sbs.address}" /></td>
							</tr>
							<tr>
								<th>联系电话</th>
								<td><input name="phone" type="text" value="${sbs.phone}" /></td>
							</tr>
							<tr>
								<th>传真</th>
								<td><input name="fax" type="text" value="${sbs.fax}" /></td>
							</tr>
							<tr>
								<th>电子邮箱</th>
								<td><input name="email" type="text" value="${sbs.email}" /></td>
							</tr>
							<tr>
								<th>完成年限</th>
								<td><input name="years" type="text" value="${sbs.years}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" /></td>
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
			<div class="t">一到八项文档性内容填报</div>
			<div class="xtyj-container sport-container"></div>
		</div>
		<!--09 申请人情况-->
		<div class="box b-sqrqk ">
			<div class="t">09 申请人情况</div>
			<div class="sqrqk-container"></div>
		</div>
		<!--10 经费预算-->
		<div class="box b-jfys jfys-container"></div>
		<!--11 预算来源及经费支出情况说明-->
		<div class="box b-zcsm">
			<div class="t">11 预算来源及经费支出情况说明</div>
			<div class="zcsm-container sport-container"></div>
		</div>
		<!--12 申报单位推荐意见及提供相关研...-->
		<div class="box b-tjyj">
			<div class="t">12 申报单位推荐意见及提供相关研...</div>
			<div class="tjyj-container sport-container"></div>
		</div>
		<!--13 其他联合申报单位意见-->
		<div class="box b-dwyj">
			<div class="t">13 其他联合申报单位意见</div>
			<div class="c">
				<table style="width:80%;margin-left:10%">
					<tr>
						<td style="border:1px solid #000000;width:20%;padding:5px;">申报单位</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
						<td style="border:1px solid #000000;width:20%;padding:5px;">联系电话</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4">申报意见<br/><br/><br/><br/><br/><br/><br/>法人代表签字<span style="float: right">单位盖章</span></td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:20%;padding:5px;">申报单位</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
						<td style="border:1px solid #000000;width:20%;padding:5px;">联系电话</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4">申报意见<br/><br/><br/><br/><br/><br/><br/>法人代表签字<span style="float: right">单位盖章</span></td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:20%;padding:5px;">申报单位</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
						<td style="border:1px solid #000000;width:20%;padding:5px;">联系电话</td>
						<td style="border:1px solid #000000;width:30%;padding:5px;">&nbsp;</td>
					</tr>
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4">申报意见<br/><br/><br/><br/><br/><br/><br/>法人代表签字<span style="float: right">单位盖章</span></td>
					</tr>
				</table>
			</div>
		</div>
		<!--14 相关部门意见-->
		<div class="box b-bmyj">
			<div class="t">14 相关部门意见</div>
			<div class="c">
				<table style="width:80%;margin-left:10%">
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4"><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>负责人签字<span style="float: right">单位盖章</span></td>
					</tr>
				</table>
			</div>
		</div>
		<!--15 国家体育总局审批意见-->
		<div class="box b-spyj">
			<div class="t">15 国家体育总局审批意见</div>
			<div class="spyj-container sport-container"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var saveBaseInfo = function(formname) {
		$('#baseInfoFormSubmit').attr("disabled",true);
		$('#baseInfoFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveBaseInfo.action",
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
	
	var saveXtyj = function() {
		$('#xtyjFormSubmit').attr("disabled",true);
		$('#xtyjFormSubmit').text('正在提交...');
		// var xtyj = CKEDITOR.instances["xtyj"];
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveXtyj.action",
			type: "POST",
			dataType: "JSON",
			data: {
				xtyj : $("#xtyjForm textarea[name='xtyj']").val(),
				subjectId : $("#xtyjForm input[name='subjectId']").val(),
				_csrf : "${_csrf.token}"
			},
			error: function (obj) {
				$('#xtyjFormSubmit').removeAttr("disabled");
				$("#xtyjFormSubmit").text("保存");
				layer.msg("保存失败，请稍后重试！");
			},
			success: function (obj) {
				$('#xtyjFormSubmit').removeAttr("disabled");
				$("#xtyjFormSubmit").text("保存");
				layer.msg("保存成功！");
			}
		});
	}
	
	var saveYjmb = function() {
		$('#yjmbFormSubmit').attr("disabled",true);
		$('#yjmbFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveYjmb.action",
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
			url: "<%=basePath%>/subject/sboper/saveJsgj.action",
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
			url: "<%=basePath%>/subject/sboper/saveYjff.action",
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
			url: "<%=basePath%>/subject/sboper/saveSyfa.action",
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
	
	var saveJdap = function() {
		$('#jdapFormSubmit').attr("disabled",true);
		$('#jdapFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveJdap.action",
			type: "POST",
			dataType: "JSON",
			data: {
				jdap : $("#jdapForm textarea[name='jdap']").val(),
				subjectId : $("#jdapForm input[name='subjectId']").val(),
				_csrf : "${_csrf.token}"
			},
			error: function (obj) {
				$('#jdapFormSubmit').removeAttr("disabled");
				$("#jdapFormSubmit").text("保存");
				layer.msg("保存失败，请稍后重试！");
			},
			success: function (obj) {
				$('#jdapFormSubmit').removeAttr("disabled");
				$("#jdapFormSubmit").text("保存");
				layer.msg("保存成功！");
			}
		});
	}
	
	var saveYqjg = function() {
		$('#yqjgFormSubmit').attr("disabled",true);
		$('#yqjgFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveYqjg.action",
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
			url: "<%=basePath%>/subject/sboper/saveGztj.action",
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
	
	var saveTjyj = function() {
		$('#tjyjFormSubmit').attr("disabled",true);
		$('#tjyjFormSubmit').text('正在提交...');
		$.ajax({
			url: "<%=basePath%>/subject/sboper/saveTjyj.action",
			type: "POST",
			dataType: "JSON",
			data: {
				tjyj : $("#tjyjForm textarea[name='tjyj']").val(),
				subjectId : $("#tjyjForm input[name='subjectId']").val(),
				_csrf : "${_csrf.token}"
			},
			error: function (obj) {
				$('#tjyjFormSubmit').removeAttr("disabled");
				$("#tjyjFormSubmit").text("保存");
				layer.msg("保存失败，请稍后重试！");
			},
			success: function (obj) {
				$('#tjyjFormSubmit').removeAttr("disabled");
				$("#tjyjFormSubmit").text("保存");
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
			$(".xtyj-container").load("<%=basePath%>/word/open",{fileId:"sbs_new_${subjectId}${subject.creator}_001.doc",title:"一到八项文档性内容填报",_csrf:$("#csrfToken").val(),readonly:false});
		});
		// 预算原因zcsm
		$("#e-zcsm").click(function(){
			showLayout('b-zcsm');
			$(".sport-container").children().remove();
			$(".zcsm-container").load("<%=basePath%>/word/open",{fileId:"sbs_new_${subjectId}${subject.creator}_011.doc",title:"预算来源及经费支出情况说明",_csrf:$("#csrfToken").val(),readonly:true});
		});
		
		// 预算原因zcsm
		$("#e-tjyj").click(function(){
			showLayout('b-tjyj');
			$(".sport-container").children().remove();
			$(".tjyj-container").load("<%=basePath%>/word/open",{fileId:"sbs_new_${subjectId}${subject.creator}_012.doc",title:"申报单位推荐意见及提供相关研究工作条件的保证",_csrf:$("#csrfToken").val(),readonly:false});
		});
		
		$("#e-sqrqk").click(function(){
			showLayout('b-sqrqk');
			$(".sqrqk-container").load("<%=basePath%>/subject/proposer/sboper/list.htm?sbsId=${sbs.sbsId}&subjectId=${subjectId}");
		});
		
		$("#e-jfys").click(function(){
			showLayout('b-jfys');
			$(".jfys-container").load("<%=basePath%>/subject/sbsbudget/sboper/cost.htm?sbsId=${sbs.sbsId}&subjectId=${subjectId}");
		});
		
	});
</script>