<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<jsp:include page="../cs/cs_head.jsp"></jsp:include>
<body>
	<div class="titleBox1">
		<div class="tip">任务书填报</div>
		<div class="title">${subject.name}<span>${status[sc.status]}</span></div>
	</div>
	<jsp:include page="../cs/cs_readonly.jsp"></jsp:include>
</body>