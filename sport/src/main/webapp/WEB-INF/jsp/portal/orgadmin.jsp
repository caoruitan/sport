<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<title>系统管理</title>
		<style type="text/css">body {background: #F2F2F2;}</style>
	</head>
	<body>
		<jsp:include page="subject.jsp"></jsp:include>
		<jsp:include page="news.jsp"></jsp:include>
	</body>
</html>