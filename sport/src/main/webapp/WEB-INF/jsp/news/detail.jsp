<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<link rel="stylesheet" href="<%=basePath %>/static/js/lhgdialog/skins/discuz.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
	</style>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<title>新闻详情</title>
</head>
	<body>
		<body>
		<div class="titleBox">
			<div class="title"><img src="<%=basePath %>/static/img/m-news.png" />新闻详情</div>
			<c:if test="${notLogin !=true}">
				<div class="returnBtn news-returnBtn">返回列表</div>
			</c:if>
		</div>
		<div class="editBox">
			<table class="editTable">
				<tr>
					<th>所属栏目: </th>
					<td style="width:800px;">
						<span>${news.columnName}</span>
					</td>
				</tr>
				<tr>
					<th>标题: </th>
					<td style="width:800px;">
						<span>${news.title}</span>
					</td>
				</tr>
				<tr>
					<th>内容: </th>
					<td>
						<div style="width:800px;">${news.content}</div>
					</td>
				</tr>
				<tr>
					<th>附件 : </th>
					<td style="width:800px;">
						<c:forEach items="${news.files}" var="file" varStatus="status">
			            	<a href="javascript:;;" data-id="${file.path}" data-name="${file.name}" class="sport-download" style="text-align:left;width:100%;display:inline-block;line-height: 25px;">${file.name}</a>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>