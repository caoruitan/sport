﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
    request.setAttribute("_time", System.currentTimeMillis());
%>
<jsp:include page="../cs/cs_head.jsp"></jsp:include>
<body>
	<div class="titleBox1">
		<div class="tip">结题报告</div>
		<div class="title">
			${subject.name}<span>${status[sc.status]}</span>
			<c:if test='${sc.status eq "BACK" }'><a style="font-size:14px;color:0000ff" id="viewComment" href="#">查看审批意见</a></c:if>
		</div>
		<div class="btnBox">
			<c:if test='${sc.status eq "SBADMIN_SP"}'>
				<button class="btn-img" type="" id="tj"><img src="<%=basePath %>/static/img/d-tj.png"/>提交上一级</button>
				<button class="btn-img" type="" id="th"><img src="<%=basePath %>/static/img/d-th.png"/>退回</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../cs/cs_readonly.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		//提交上一级
		$('#tj').dialog({
			id: 'tj',
			title: '提交上一级',
			content: '<div class="dlg-contentbox"><img src="<%=basePath%>/static/img/prompt.gif" />确定要提交到上一级吗？</div>',
			width: 400,
			height: 130,
			ok: function() {
				$.ajax({
					url: "<%=basePath%>/subject/sbadmin/jtPass.action",
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
						window.location.reload();
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
					url: "<%=basePath%>/subject/sbadmin/jtUnpass.action",
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
						window.location.reload();
					}
				});
			},
			cancel: true
		});
	});
</script>
</body>