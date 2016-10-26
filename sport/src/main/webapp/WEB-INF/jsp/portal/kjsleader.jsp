<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<style type="text/css">
			body {
				background: #f2f2f2;
			}
			
			.rwBox {
				box-sizing: border-box;
				width: 100%;
				height: auto;
				float: left;
				border: 1px solid #e4e4e4;
				background: #ffffff;
				position: relative;
				border-radius: 4px;
				color: #73819A;
				margin-bottom: 10px;
				padding: 20px;
			}
			
			.rwBox .t {
				width: 100%;
				border-bottom: 1px solid #73819A;
				height: 30px;
				font-size: 16px;
				color: #73819A;
				text-align: left;
			}
			
			.rwBox .l1 {
				float: left;
				width: 33%;
				height: 160px;
				position: relative;
				border-right: 1px dashed #E4E4E4;
				margin: 20px 0 0 0;
			}
			
			.noRightBorder {
				border-right: 0px solid #E4E4E4 !important;
			}
			
			.rwBox .wdkt {
				position: absolute;
				right: 10px;
				top: 2px;
			}
			
			.rBox {
				float: left;
				width: 32.333%;
				height: 220px;
				border: 1px solid #e4e4e4;
				background: #ffffff;
				position: relative;
				border-radius: 4px;
				color: #73819A;
				margin-bottom: 10px;
				margin-right: 1%;
				padding: 10px;
			}
			
			.t1 {
				font-family: "微软雅黑";
				font-size: 16px;
				width: 100%;
				height: 30px;
				line-height: 30px;
				margin-bottom: 10px;
				border-bottom: 1px solid #E4E4E4;
			}
			
			.rBox li {
				height: 30px;
				line-height: 30px;
				overflow: hidden;
				position: relative;
			}
			
			.rBox li span {
				float: right;
				color: #999;
				position: absolute;
				top: 3px;
				right: 1px;
			}
			
			.rBox li a {
				display: block;
				width: 70%;
				overflow: hidden;
			}
			
			.a1-1 {
				position: absolute;
				left: 50%;
				margin-left: -100px;
				top: 110px;
				width: 200px;
				height: 30px;
				text-align: center;
				font-size: 18px;
			}
			
			.a1-1 a {
				position: absolute;
				left: 50%;
				top: -70px;
				margin-left: -30px;
				display: inline-block;
				width: 60px;
				height: 60px;
				line-height: 60px;
				border-radius: 40px;
				border: 2px solid #F96A66;
				text-align: center;
				font-size: 28px;
				color: #F96A66;
			}
			
			.a1-1 a:hover {
				color: #FFFFFF;
				background: #F96A66;
				text-decoration: none;
			}
			
			.a1-1 a.color2 {
				border: 2px solid #00CC99;
				color: #00CC99;
			}
			
			.a1-1 a.color2:hover {
				background: #00CC99;
				color: #ffffff;
			}
			
			.a1-1 a.color3 {
				border: 2px solid #99CC33;
				color: #99CC33;
			}
			
			.a1-1 a.color3:hover {
				background: #99CC33;
				color: #ffffff;
			}
			
			.ktBox {
				width: 100%;
				height: auto;
				float: left;
				padding: 10px 0 20px 0;
			}
			
			.bottomBorder {
				border-bottom: 1px solid #CCCCCC;
			}
		</style>
	</head>
	<body>
		<div class="rwBox">
			<div class="t">
				总共申报的课题总数<span class="redfont">${kt_zs}</span>个，其中：
			</div>
			<div class="ktBox bottomBorder">
				<div class="ktBoxTitle">
					招标课题共 <span class="redfont">${zbkt_zs}</span> 个，其中：
				</div>
				<div class="l1">
					<p class="a1-1"><a>${zbkt_sbs}</a>申报书填报阶段</p>
				</div>
				<div class="l1">
					<p class="a1-1"><a class="color2">${zbkt_rws}</a>任务书填报阶段</p>
				</div>
				<div class="l1 noRightBorder">
					<p class="a1-1"><a class="color3">${zbkt_jt}</a>已结题</p>
				</div>
			</div>
			<div class="ktBox">
				<div class="ktBoxTitle">
					招标课题共 <span class="redfont">${kyggkt_zs}</span> 个，其中：
				</div>
				<div class="l1">
					<p class="a1-1"><a>${kyggkt_sbs}</a>申报书填报阶段</p>
				</div>
				<div class="l1">
					<p class="a1-1"><a class="color2">${kyggkt_rws}</a>任务书填报阶段</p>
				</div>
				<div class="l1 noRightBorder">
					<p class="a1-1"><a class="color3">${kyggkt_jt}</a>已结题</p>
				</div>

			</div>
		</div>
		<jsp:include page="news.jsp"></jsp:include>
	</body>
</html>