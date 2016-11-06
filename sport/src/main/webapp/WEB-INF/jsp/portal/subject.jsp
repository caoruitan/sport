<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="rwBox">
	<div class="t">
		我的任务
		<a class="wdkt"><button data-type="${user_type}" class="btn-red btn-size-big sport-my-kt-skip">我的课题</button></a>
	</div>
	<div class="l">
		<p class="a1" style="background: url(<%=basePath%>/static/img/a1.png) no-repeat center center;">重点研究领域攻关课题</p>
		<p class="a1-1"><a>${zb_return_total}</a>已退回在课题数</p>
		<p class="a1-2"><a>${zb_end_total}</a>已结题的课题数</p>
	</div>
	<div class="r">
		<p class="a1" style="background: url(<%=basePath%>/static/img/a2.png) no-repeat center center;">国家队科研综合攻关研究课题</p>
		<p class="a1-1"><a>${ky_return_total}</a>已退回在课题数</p>
		<p class="a1-2"><a>${ky_end_total}</a>已结题的课题数</p>
	</div>
</div>