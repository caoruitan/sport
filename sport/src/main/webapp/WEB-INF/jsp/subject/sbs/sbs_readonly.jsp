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
				<li class="dot" id="e-sqrqk">
					<a  title="申请人情况">09 申请人情况</a>
				</li>
				<li class="dot" id="e-jfys">
					<a  title="经费预算">10 经费预算</a>
				</li>
				<li class="dot" id="e-zcsm" onclick="showLayout('b-zcsm')">
					<a title="经费来源及经费支出情况说明">11 经费来源及经费支出情况说明</a>
				</li>
				<li class="dot" id="e-tjyj" onclick="showLayout('b-tjyj')">
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
			<div class="c">
				<form id="xtyjForm">
					<input type="hidden" name="subjectId" value="${subjectId}">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
					<textarea style="width:100%;height:400px;" name="xtyj" disabled="disabled">${sbs.xtyj}</textarea>
				</form>
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
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
				<p class="save-btn"></p>
			</div>
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
			<div class="c">
				<table style="width:80%;margin-left:10%">
					<tr>
						<td style="border:1px solid #000000;width:100%;padding:5px;" colspan="4">
							对各科目支出的主要用途、与项目研究的相关性及测算方法、测算依据进行详细分析说明。（未对支出进行分析说明的，不予核定预算）<br/>
							一、直接费用<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（一）设备费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001001}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（二）材料费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001002}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（三）测试化验加工费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001003}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（四）差旅费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001004}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（五）会议费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001005}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（六）出版/印刷/文献/信息传播/知识产权事务费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001006}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（七）劳务费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001007}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;（八）专家咨询费<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${D_001001008}<br/>
							二、直接费用<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;${D_001002}<br/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--12 申报单位推荐意见及提供相关研...-->
		<div class="box b-tjyj">
			<div class="t">12 申报单位推荐意见及提供相关研...</div>
			<div class="c">
				<form id="tjyjForm">
					<input type="hidden" name="subjectId" value="${subjectId}">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdId"/>
					<textarea style="width:100%;height:400px;" name="tjyj" disabled="disabled">${sbs.tjyj}</textarea>
				</form>
				<p class="save-btn"></p>
			</div>
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