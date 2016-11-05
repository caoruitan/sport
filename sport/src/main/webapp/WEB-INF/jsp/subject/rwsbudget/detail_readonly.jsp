<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
	<title>申报书经费预算</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/kt.css" />
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.min1.10.1.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/static/layer/layer.js"></script>
    <style type="text/css">
            body {
                background: #ffffff;
            }
    </style>
	</head>
	<body>
		<div class="notice-box">
			<li>注意：经费来源总额必须与经费支出总额相等。单位：万元</li>
		</div>
		<div class="TBox">
                <table class="TTable">
    				<thead>
    					<tr>
    						<th width="40%">预算科目名称</th>
    						<th width="15%">金额</th>
    						<th width="45%">计算依据和理由</th>
    					</tr>
    				</thead>
    				<tr>
    					<th class="level1">${income.name}</th>
    				</tr>
    				<c:forEach var="dic" items="${incomeDics}" varStatus="status">
                        <tr>
                            <c:if test="${status.index < fn:length(incomeDics)-1}">
                                <th class="level2">${dic.name }</th>
                                <td>
                                    <input name="income[${status.index}].cost" data-index="income${status.index}" class="price income" type="number" placeholder="0.00" value="${ssbMap[dic.code].cost}" />
                                    <input name="income[${status.index}].code" type="hidden" value="${dic.code}" />
                                    <input name="income[${status.index}].name" type="hidden" value="${dic.name}" />
                                </td>
                            </c:if>
                            <c:if test="${status.index == fn:length(incomeDics)-1}">
                                <th class="level2"><span class="brownfont">${dic.name }</span></th>
    					        <td class="price-ly">
                                    <input name="income[${status.index}].name" type="hidden" value="${dic.name}" />
                                    <input name="income[${status.index}].code" type="hidden" value="${dic.code}" />
                                    <input name="income[${status.index}].cost" id="income-total-td" type="hidden" value="${ssbMap[dic.code].cost}" />
                                    <span class="income-total-td">${ssbMap[dic.code].cost == null ?'0.00': ssbMap[dic.code].cost}</span>
                                </td>
                            </c:if>
                            <td>/</td>
        				</tr>
                    </c:forEach>
    				<!--支出-->
    				<tr>
    					<th class="level1">${cost.name}</th>
    					<td></td>
    					<td></td>
    				</tr>
                    <c:forEach var="dic" items="${costDics}" varStatus="status">
                        <c:if test="${costTotalCode !=dic.code}">
                            <tr>
                                <c:if test="${status.index < fn:length(costDics)-1}">
                                    <th class="level2">${dic.name }</th>
                                    <td>
                                        <input name="cost[${status.index}].cost" class="price cost" type="number" placeholder="0.00" value="${ssbMap[dic.code].cost}" />
                                        <input name="cost[${status.index}].code" type="hidden" value="${dic.code}" />
                                        <input name="cost[${status.index}].name" type="hidden" value="${dic.name}" />
                                    </td>
                                    <td><input name="cost[${status.index}].reason" type="text" value="${ssbMap[dic.code].reason}"></td>
                                </c:if>
                                <c:if test="${status.index == fn:length(costDics)-1}">
                                    <th class="level2"><span class="brownfont">${dic.name }</span></th>
                                    <td class="sub-total-error">
                                        <input name="cost[${status.index}].cost" type="hidden" id="cost-total-td" value="${ssbMap[dic.code].cost}" />
                                        <input name="cost[${status.index}].code" type="hidden" value="${dic.code}" />
                                        <input name="cost[${status.index}].name" type="hidden" value="${dic.name}" />
                                        <span class="cost-total-td">${ssbMap[dic.code].cost == null ?'0.00': ssbMap[dic.code].cost}</span>
                                    </td>
                                    <td>/</td>
                                </c:if>
                            </tr>
                        </c:if>
                        <c:if test="${costTotalCode ==dic.code}">
                             <tr>
                                <th class="level2">${dic.name }</th>
                                <td class="sub-total-error">
                                    <input name="cost[${status.index}].code" type="hidden" value="${dic.code}" />
                                    <input name="cost[${status.index}].name" type="hidden" value="${dic.name}" />
                                    <input name="cost[${status.index}].cost" type="hidden" class="cost" id="cost-temp-total-td" value="${ssbMap[dic.code].cost}" />
                                    <span class="cost-temp-total-td">${ssbMap[dic.code].cost == null ?'0.00': ssbMap[dic.code].cost}</span>
                                </td>
                                <td>/</td>
                            </tr>
                            <c:forEach var="costTotal" items="${costTotalDics}" varStatus="cstatus">
                                <tr>
                                    <th class="level3">${costTotal.name}</th>
                                    <td>
                                        <input name="cost[${fn:length(costDics) + cstatus.index}].cost" class="price temp" type="number" placeholder="0.00" value="${ssbMap[costTotal.code].cost}" />
                                        <input name="cost[${fn:length(costDics) + cstatus.index}].code" type="hidden" value="${costTotal.code}" />
                                        <input name="cost[${fn:length(costDics) + cstatus.index}].name" type="hidden" value="${costTotal.name}" />
                                    </td>
                                    <td><input name="cost[${fn:length(costDics) + cstatus.index}].reason" type="text" value="${ssbMap[costTotal.code].reason}"></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
    			</table>
		</div>
	</body>
</html>