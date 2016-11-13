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
			<c:if test='${not empty sc && sc.status ne "SBOPER_TB"}'>
				<button class="btn-img" id="xz"><img src="<%=basePath %>/static/img/d-xz.png"/>下载</button>
			</c:if>
		</div>
	</div>
	
	<c:if test='${empty sc || sc.status eq "SBOPER_TB" || sc.status eq "BACK"}'>
		<jsp:include page="../cs/cs_edit.jsp"></jsp:include>
	</c:if>
	<c:if test='${not empty sc && sbs.status ne "SBOPER_TB" && sbs.status ne "BACK"}'>
		<jsp:include page="../cs/cs_readonly.jsp"></jsp:include>
	</c:if>
</body>