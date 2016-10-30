<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<head>
	<title>课题查看</title>
	<!--jqgrid-->
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/ecmascript" src="<%=basePath %>/static/js/jqgrid/jquery.jqGrid.min.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath %>/static/js/jqgrid/css/my.jqgrid.css" />
	
	<!--select-->
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/bootstrap-select.css">
	<link rel="stylesheet" href="<%=basePath %>/static/js/jqselect/my.select.css">
	<script type="text/javascript" src="<%=basePath %>/static/js/jqselect/bootstrap-select.js"></script>
	
	<style type="text/css">
		body {
			background: #F2F2F2;
		}
		
		.ktview {
			width: 100%;
			float: left;
			height: auto;
			min-height: 500px;
			background: #ffffff;
		}
		
		.ktview .kt-right {
			float: left;
			width: 70%;
		}
		
		.ktview .kt-left {
			float: left;
			width: 30%;
			min-height: 500px;
			z-index: 10;
		}
		
		.ktview .kt-left .lc {
			margin: 20px 0 0 20px;
			min-width: 180px;
			width: 260px;
			height: auto;
			min-height: 500px;
			border: 1px solid #e4e4e4;
			padding: 10px;
			background: #f8f8f8;
			-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
			-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
			box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		}
		
		.line {
			float: left;
			position: relative;
			width: 200px;
			border-left: 3px double #d2d2d2;
			margin: 10px 0 0 10px;
		}
		
		.line li {
			width: 100%;
			float: left;
			background: red;
			height: auto;
		}
		
		.line li p b {
			color: #73819A;
			font-size: 15px;
		}
		
		.line li.dot {
			background: url(<%=basePath %>/static/img/kt-dot.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 20px -12px;
		}
		
		.line li.ok {
			background: url(<%=basePath %>/static/img/kt-ok.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 20px -11px;
		}
		
		.line li.cur {
			background: url(<%=basePath %>/static/img/kt-cur.png) no-repeat left 0px;
			padding: 0 0 0 25px;
			margin: 0 0 20px -11px;
		}
		
		.line a {
			color: #0066CC;
		}
	</style>
</head>

<body>

	<div class="titleBox">
		<div class="title"><img src="<%=basePath %>/static/img/kt.png" />${subject.name}</div>
		<a href="<%=basePath %>/subject/sboper/list.htm">
			<div class="returnBtn">返回列表</div>
		</a>
	</div>

	<div class="ktview">
		<div class="kt-left">
			<div class="lc">
				<div class="line">
					<li class="ok">注册</li>
					<c:if test='${subject.stage eq "SBSTB"}'>
						<li class="cur">
							<p><b>申报书填报与评审</b></p>
							<p>截止 <fmt:formatDate value="${subject.sbsEndDate}" type="date"/></p>
							<p>
								<a href="<%=basePath %>/subject/sboper/sbstb.shtm?subjectId=${subject.id}" target="_blank">
									<c:if test = '${empty sbs || sbs.status eq "SBOPER_TB" || sbs.status eq "BACK"}'>
										<button class="btn-red btn-size-small">填报</button>
									</c:if>
									<c:if test = '${not empty sbs && sbs.status ne "SBOPER_TB" && sbs.status ne "BACK"}'>
										<button class="btn-red btn-size-small">查看</button>
									</c:if>
								</a>
							</p>
						</li>
						<li class="dot">
							<p><b>任务书填报与评审</b></p>
							<p>截止 <fmt:formatDate value="${subject.rwsEndDate}" type="date"/></p>
							<p><a href="0601rws-tb.html" target="_blank"></a></p>
						</li>
						<li class="dot">
							<p><b>结题报告</b></p>
							<p>截止 <fmt:formatDate value="${subject.subjectEndDate}" type="date"/></p>
							<p><a href="0701jdbg-tb.html" target="_blank"></a></p>
						</li>
					</c:if>
					<c:if test='${subject.stage eq "RWSTB"}'>
						<li class="ok">
							<p><b>申报书填报与评审</b></p>
							<p>截止 <fmt:formatDate value="${subject.sbsEndDate}" type="date"/></p>
							<p><a href="<%=basePath %>/subject/sboper/sbstb.shtm?subjectId=${subject.id}" target="_blank"><button class="btn-red btn-size-small">查看</button></a></p>
						</li>
						<li class="cur">
							<p><b>任务书填报与评审</b></p>
							<p>截止 <fmt:formatDate value="${subject.rwsEndDate}" type="date"/></p>
							<p>
								<a href="<%=basePath %>/subject/sboper/rwstb.shtm?subjectId=${subject.id}" target="_blank">
									<c:if test = '${empty rws || rws.status eq "SBOPER_TB" || rws.status eq "BACK"}'>
										<button class="btn-red btn-size-small">填报</button>
									</c:if>
									<c:if test = '${not empty rws && rws.status ne "SBOPER_TB" && rws.status ne "BACK"}'>
										<button class="btn-red btn-size-small">查看</button>
									</c:if>
								</a>
							</p>
						</li>
						<li class="dot">
							<p><b>结题报告</b></p>
							<p>截止 <fmt:formatDate value="${subject.subjectEndDate}" type="date"/></p>
							<p><a href="0701jdbg-tb.html" target="_blank"></a></p>
						</li>
					</c:if>
				</div>
			</div>
		</div>
		<div class="kt-right">
			<div class="viewBox">
				<table class="viewTable">
					<tr>
						<th>课题类别</th>
						<td>
							<c:forEach items="${types}" var="type">
								<c:if test="${subject.type eq type.key}">
									<span class="redfont">${type.value}</span>
								</c:if>
							</c:forEach>
						</td>
						<th>课题名称</th>
						<td><span class="redfont">${subject.name}</span></td>
					</tr>
					<tr>
						<th>编号</th>
						<td>${subject.num}</td>
						<th>申报书截止日期</th>
						<td><fmt:formatDate value="${subject.sbsEndDate}" type="date"/></td>
					</tr>
					<tr>
						<th>任务书截止日期</th>
						<td><fmt:formatDate value="${subject.rwsEndDate}" type="date"/></td>
						<th>课题结题截止时间</th>
						<td><fmt:formatDate value="${subject.subjectEndDate}" type="date"/></td>
					</tr>
					<tr>
						<th>项目组织单位</th>
						<td>${subject.organizationName}</td>
						<th>所选专家</th>
						<td></td>
					</tr>
					<tr>
						<th>密级</th>
						<td>${subject.securityName}</td>
						<th>参加单位总数</th>
						<td>${subject.organizationCount}</td>
					</tr>
					<tr>
						<th>起始时间</th>
						<td><fmt:formatDate value="${subject.beginDate}" type="date"/></td>
						<th>终止时间</th>
						<td><fmt:formatDate value="${subject.endDate}" type="date"/></td>
					</tr>
					<tr>
						<th>产学联合</th>
						<td>
							<c:if test="${subject.integration eq true}">是</c:if>
							<c:if test="${subject.integration eq false}">否</c:if>
						</td>
						<th>预期成果</th>
						<td>${subject.resultsName}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
