<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<jsp:include page="../rws/rws_head.jsp"></jsp:include>
<body>
	<div class="titleBox1">
		<div class="tip">任务书填报</div>
		<div class="title">${subject.name}<span>${status[rws.status]}</span></div>
		<div class="btnBox">
			<c:if test='${not empty rws && rws.status ne "SBOPER_TB"}'>
				<button class="btn-img" id="xz"><img src="<%=basePath %>/static/img/d-xz.png"/>下载</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../rws/rws_readonly.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			$("#xz").click(function() {
				window.open("<%=basePath%>/rws/download.action?subjectId=${subjectId}");
			});
		});
	</script>
</body>
