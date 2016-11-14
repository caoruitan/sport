<%@ page contentType="text/html; charset=UTF-8" %>
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
		<div class="title">${subject.name}<span>${status[sc.status]}</span></div>
		<div class="btnBox">
			<c:if test='${not empty sc && (sc.status eq "SBOPER_TB" || sc.status eq "BACK")}'>
				<button class="btn-img" id="tj"><img src="<%=basePath %>/static/img/d-tj.png"/>校验提交</button>
			</c:if>
		</div>
	</div>
	
	<c:if test='${empty sc || sc.status eq "SBOPER_TB" || sc.status eq "BACK"}'>
		<jsp:include page="../cs/cs_edit.jsp"></jsp:include>
	</c:if>
	<c:if test='${not empty sc && sbs.status ne "SBOPER_TB" && sbs.status ne "BACK"}'>
		<jsp:include page="../cs/cs_readonly.jsp"></jsp:include>
	</c:if>
<script type="text/javascript">
	$(function(){
		$("#tj").click(function() {
			$.ajax({
				url: "<%=basePath%>/subject/sboper/checkAndSubmitJt.action",
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
					if(obj.success == "true") {
						layer.msg("提交成功！");
						window.location.reload();
					} else if(obj.success == "false") {
						$.dialog({
							id: 'tj',
							title: '校验结果',
							content: '<div class="dlg-contentbox">' + obj.msg + '</div>',
							width: 400,
							height: 130,
							ok: true
						});
					}
				}
			});
		});
		
		$("#xz").click(function() {
			window.open("<%=basePath%>/sbs/download.action?subjectId=${subjectId}");
		});
		
		$("#viewComment").dialog({
			id: 'tj',
			title: '审批意见',
			content: '<div class="dlg-contentbox">${sbs.comment}</div>',
			width: 400,
			height: 130,
			ok: true
		});
	});
</script>
</body>