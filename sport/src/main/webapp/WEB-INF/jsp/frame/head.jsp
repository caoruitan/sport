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
	<a href="javascript:;;" data-type="${user_type}" class="sport-pwd-update-btn">修改密码</a>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrfToken"/>
	<a href="javascript:;;" class="sport-logout">安全退出</a>
</div>
