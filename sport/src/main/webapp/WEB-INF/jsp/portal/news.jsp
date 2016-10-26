<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>

<div class="rBox">
	<div class="t1">系统公告（<span class="redfont">${noticeTotal}</span>）</div>
	<c:forEach items="${noticeNews}" var="news">
		<li><a href="<%=basePath %>/news/${user_type}/detail/${news.id}.htm">${news.title}</a><span>${news.publishTime}</span></li>
	</c:forEach>
</div>
<div class="rBox">
	<div class="t1">申报系统和相关文档（<span class="redfont">${sbTotal}</span>）</div>
	<c:forEach items="${sbNews}" var="news">
		<li><a href="<%=basePath %>/news/${user_type}/detail/${news.id}.htm">${news.title}</a><span>${news.publishTime}</span></li>
	</c:forEach>
</div>
<div class="rBox">
	<div class="t1">政策法规（<span class="redfont">${zcTotal}</span>）</div>
	<c:forEach items="${zcNews}" var="news">
		<li><a href="<%=basePath %>/news/${user_type}/detail/${news.id}.htm">${news.title}</a><span>${news.publishTime}</span></li>
	</c:forEach>
</div>
