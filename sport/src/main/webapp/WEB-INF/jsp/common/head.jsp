<%@page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="top_logo"></div>
<div class="mainBtn">
	<a href="11xxlb.html" target="frmMain"><img src="<%=basePath %>/static/img/frame/xx.png" /><div class="count">9</div></a>
	<a href="03welcome.html" target="frmMain"><img src="<%=basePath %>/static/img/frame/sy.png" /></a>
</div>
<div class="op">
	<a href="12xgmm.html" target="frmMain">修改密码</a>
	<a href="01login.html">安全退出</a>
</div>
