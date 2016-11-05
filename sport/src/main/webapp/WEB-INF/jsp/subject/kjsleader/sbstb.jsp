<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<jsp:include page="../sbs/sbs_head.jsp"></jsp:include>
<body>
	<div class="titleBox1">
		<div class="tip">申报书填报</div>
		<div class="title">${subject.name}<span>${status[sbs.status]}</span></div>
		<div class="btnBox">
			<c:if test='${not empty sbs && sbs.status ne "SBOPER_TB"}'>
				<button class="btn-img" id="xz"><img src="<%=basePath %>/static/img/d-xz.png"/>下载</button>
			</c:if>
		</div>
	</div>
	<jsp:include page="../sbs/sbs_readonly.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			$("#xz").click(function() {
				window.open("<%=basePath%>/sbs/download.action?subjectId=${subjectId}");
			});
		});
	</script>
</body>
