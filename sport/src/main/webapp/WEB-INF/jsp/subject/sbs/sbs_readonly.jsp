<%@page import="org.cd.sport.utils.AuthenticationUtils"%>
<%@page import="org.cd.sport.vo.UserVo"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
    UserVo user = AuthenticationUtils.getUser();
   	request.setAttribute("loginUser", user);
%>
<div class="ktview">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrfToken"/>
	<div class="kt-left">
		<div class="lc">
			<div class="line">
				<li class="dot" id="e-sbsfm" onclick="showLayout('b-sbsfm')" >
					<a title="申报书封面">申报书封面</a>
				</li>
				<li class="dot" id="e-xtyj" >
					<a title="一到八项文档性内容填报">一到八项文档性内容填报</a>
				</li>
				<li class="dot" id="e-sqrqk">
					<a  title="申请人情况">09 申请人情况</a>
				</li>
				<li class="dot" id="e-jfys">
					<a  title="经费预算">10 经费预算</a>
				</li>
				<li class="dot" id="e-zcsm">
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
				<li class="dot" id="e-spyj" onclick="showLayout('b-spyj')">
					<a title="国家体育总局审批意见">15 国家体育总局审批意见</a>
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
					<p class="save-btn"></p>
				</div>
			</div>
		</div>
		<!--01 选题依据-->
		<div class="box b-xtyj">
			<div class="t">01 选题依据</div>
			<div class="xtyj-container sport-container"></div>
		</div>
		<!--09 申请人情况-->
		<div class="box b-sqrqk">
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
			<div class="c">
				<table style="width:80%;margin-left:10%">
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4"><br/><br/><br/><br/><br/><br/><br/><br/><br/><span style="float: right">单位盖章</span></td>
					</tr>
				</table>
			</div>
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
		
		$("#e-xtyj").click(function(){
			showLayout('b-xtyj');
			$(".sport-container").children().remove();
			$(".xtyj-container").load("<%=basePath%>/word/open",{fileId:"sbs_new_${subjectId}${subject.creator}_001.doc",title:"一到八项文档性内容填报",_csrf:$("#csrfToken").val(),readonly:true});
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
			$(".tjyj-container").load("<%=basePath%>/word/open",{fileId:"sbs_new_${subjectId}${subject.creator}_012.doc",title:"申报单位推荐意见及提供相关研究工作条件的保证",_csrf:$("#csrfToken").val(),readonly:true});
		});
		
		$("#e-sqrqk").click(function(){
			showLayout('b-sqrqk');
			$(".sqrqk-container").load("<%=basePath%>/subject/proposer/listReadOnly.htm?sbsId=${sbs.sbsId}&subjectId=${subjectId}");
		});
		
		$("#e-jfys").click(function(){
			showLayout('b-jfys');
			$(".jfys-container").load("<%=basePath%>/subject/sbsbudget/costReadOnly.htm?sbsId=${sbs.sbsId}&subjectId=${subjectId}");
		});
	});
</script>