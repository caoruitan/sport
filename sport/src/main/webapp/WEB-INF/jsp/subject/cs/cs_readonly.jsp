<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<div class="ktview">
	<div class="kt-left">
		<div class="lc">
			<div class="line">
				<li class="ok b-active" id="e-zc" onclick="showLayout('b-zc')" >
					<a title="注册">注册</a>
				</li>
				<li class="ok" id="e-txsm" onclick="showLayout('b-txsm')" >
					<a title="填写说明">填写说明</a>
				</li>
				<li class="dot" id="e-sbsfm" onclick="showLayout('b-sbsfm')" >
					<a title="附件">附件</a>
				</li>
			</div>
		</div>
	</div>
	<div class="kt-right">
		<div class="box" style="height:400px;display: block; background: url(<%=basePath %>/static/img/welcome.png) center center no-repeat;">
		</div>
		<!--注册-->
		<div class="box b-zc">
			<div class="t">注册</div>
			<div class="c">
				<div class="centerBox">
					<img src="<%=basePath %>/static/img/success.png" />
					<p>已注册成功，请继续下一步</p>
				</div>
			</div>
		</div>
		<!--填写说明-->
		<div class="box b-txsm">
			<div class="t">填写说明</div>
			<div class="c contentTxt">
				${news.content}
			</div>
		</div>
		<!--申报书封面-->
		<div class="box b-sbsfm wow bounceInDown">
			<div class="t">附件</div>
			<div class="c">
				<div class="editBox">
					<table class="editTable">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrdToken"/>
						<div class="news-file-container">
							<c:forEach items="${sas}" var="file" varStatus="status">
								<div class="sport-news-file" data-index="${status.index}">
				            	 	<span style="text-align:left;display:inline-block;width:90%;line-height: 25px;color:#1e5494;">${file.fileName}</span>
				            	 	<span style="text-align:right;display:inline-block;width:8%;line-height: 25px;color:#1e5494;cursor:pointer;" data-id="${file.fileId}" data-name="${file.fileName}" class="jt-download">下载</span>
								</div>
							</c:forEach>
						</div>
					</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
var showLayout = function(obj) {
	$(".box").hide();
	$('.' + obj).slideDown();
}
$(".jt-download").click(function(){
	var dataId = $(this).attr("data-id");
	var dataName = $(this).attr("data-name");
	window.open("<%=basePath%>/jtFile/download.action?dataId="+dataId+"&dataName="+encodeURI(dataName));
});

</script>
</div>