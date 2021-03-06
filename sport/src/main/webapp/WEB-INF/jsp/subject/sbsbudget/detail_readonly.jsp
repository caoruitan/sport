﻿<%@ page contentType="text/html; charset=UTF-8" %>
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
		<div class="t">10 经费预算</div>
		<div class="notice-box">
			<li>注意：经费来源总额必须与经费支出总额相等。单位：万元</li>
		</div>
		<div class="TBox">
               <table class="TTable">
   				<thead>
   					<tr>
   						<th width="48%">预算科目名称</th>
   						<th width="15%">金额</th>
   						<th width="33%">计算依据和理由</th>
   					</tr>
   				</thead>
   				<!-- 
   				<tr>
   					<th class="level1">${income.name}</th>
   					<td></td>
   					<td></td>
   				</tr>
   				<c:forEach var="dic" items="${incomeDics}" varStatus="status">
                       <tr>
                           <c:if test="${status.index < fn:length(incomeDics)-1}">
                               <th class="level2">${dic.name }</th>
                               <td>
                                   <input name="income[${status.index}].cost" data-index="income${status.index}" class="price income" type="number" placeholder="0.00" value="${ssbMap[dic.code].cost}"  readonly="readonly"/>
                                   <input name="income[${status.index}].code" type="hidden" value="${dic.code}" />
                                   <input name="income[${status.index}].name" type="hidden" value="${dic.name}" />
                               </td>
                           </c:if>
                           <c:if test="${status.index == fn:length(incomeDics)-1}">
                               <th class="level2"><span class="brownfont">${dic.name }</span></th>
   					        <td class="price-ly">
                                   <input name="income[${status.index}].name" type="hidden" value="${dic.name}" />
                                   <input name="income[${status.index}].code" type="hidden" value="${dic.code}" />
                                   <input name="income[${status.index}].cost" id="income-total-td" type="hidden" value="${ssbMap[dic.code].cost}"  readonly="readonly"/>
                                   <span class="income-total-td">${ssbMap[dic.code].cost == null ?'0.00': ssbMap[dic.code].cost}</span>
                               </td>
                           </c:if>
                           <td>/</td>
       				</tr>
                   </c:forEach>
                    -->
   				<!--支出-->
   				<tr>
   					<th class="level1">${cost.name}</th>
   					<td></td>
   					<td></td>
   				</tr>
                   <c:forEach var="dic" items="${cost.children}" varStatus="status">
                       <tr>
                           <th class="level2">${dic.name }</th>
                           <c:if test="${dic.parent == true}">
                            <td class="price-ly">
                                <input name="cost[${status.index}].cost" class="price_${cost.code}" id="total_${dic.code}"  type="hidden" value="${ssbMap[dic.code].cost}" />
                                <input name="cost[${status.index}].code" type="hidden" value="${dic.code}" />
                                <input name="cost[${status.index}].name" type="hidden" value="${dic.name}" />
                                <span class="total_${dic.code} cost-total-td">${ssbMap[dic.code].cost == null ?'0.00': ssbMap[dic.code].cost}</span>
                            </td>
                            <td>/</td>
                           </c:if>
                           <c:if test="${dic.parent == false}">
                            <td>
                                <input name="cost[${status.index}].cost" class="price cost price_${cost.code}" data-pcodes="${cost.code}" type="number" placeholder="0.00" value="${ssbMap[dic.code].cost}"  readonly="readonly"/>
                                <input name="cost[${status.index}].code" type="hidden" value="${dic.code}" />
                                <input name="cost[${status.index}].name" type="hidden" value="${dic.name}" />
                            </td>
                            <td><input name="cost[${status.index}].reason" type="text" value="${ssbMap[dic.code].reason}"  readonly="readonly"></td>
                           </c:if>
                       </tr>
                       <c:forEach var="dic1" items="${dic.children}" varStatus="status1">
                        <tr>
                             <c:set var="len3" value="${fn:length(cost.children) * (status.index+1)}"></c:set>
                             <th class="level3">${dic1.name }</th>
                             <c:if test="${dic1.parent == true}">
	                            <td class="price-ly">
	                                <input name="cost[${len3+status1.index}].cost" class="price_${dic.code}" id="total_${dic1.code}"  type="hidden" value="${ssbMap[dic1.code].cost}"/>
	                                <input name="cost[${len3+status1.index}].code" type="hidden" value="${dic1.code}" />
	                                <input name="cost[${len3+status1.index}].name" type="hidden" value="${dic1.name}" />
	                                <span class="total_${dic1.code}">${ssbMap[dic1.code].cost == null ?'0.00': ssbMap[dic1.code].cost}</span>
	                            </td>
	                            <td>/</td>
                            </c:if>
                            <c:if test="${dic1.parent == false}">
	                            <td>
	                                <input name="cost[${len3+status1.index}].cost" class="price cost price_${dic.code}" data-pcodes="${dic.code}" type="number" placeholder="0.00" value="${ssbMap[dic1.code].cost}"  readonly="readonly"/>
	                                <input name="cost[${len3+status1.index}].code" type="hidden" value="${dic1.code}" />
	                                <input name="cost[${len3+status1.index}].name" type="hidden" value="${dic1.name}" />
	                            </td>
	                            <td><input name="cost[${len3+status1.index}].reason" type="text" value="${ssbMap[dic1.code].reason}"  readonly="readonly"></td>
                            </c:if>
                        </tr>
                        <c:forEach var="dic2" items="${dic1.children}" varStatus="status2">
	                        <tr>
	                            <c:set var="len4" value="${fn:length(dic.children) * (status1.index+1) }"></c:set>
	                            <th class="level4">${dic2.name }</th>
	                            <c:if test="${dic2.parent == true}">
		                            <td class="price-ly">
		                                <input name="cost[${len3+len4+status2.index}].cost" class="price_${dic1.code}" id="total_${dic2.code}" type="hidden" value="${ssbMap[dic2.code].cost}" />
		                                <input name="cost[${len3+len4+status2.index}].code" type="hidden" value="${dic2.code}" />
		                                <input name="cost[${len3+len4+status2.index}].name" type="hidden" value="${dic2.name}" />
		                                <span class="total_${dic2.code}">${ssbMap[dic2.code].cost == null ?'0.00': ssbMap[dic2.code].cost}</span>
		                            </td>
		                            <td>/</td>
	                            </c:if>
	                            <c:if test="${dic2.parent == false}">
		                            <td>
		                                <input name="cost[${len3+len4+status2.index}].cost" class="price cost price_${dic1.code}" data-pcodes="${dic1.code},${dic.code}" type="number" placeholder="0.00" value="${ssbMap[dic2.code].cost}"  readonly="readonly"/>
		                                <input name="cost[${len3+len4+status2.index}].code" type="hidden" value="${dic2.code}" />
		                                <input name="cost[${len3+len4+status2.index}].name" type="hidden" value="${dic2.name}" />
		                            </td>
		                            <td><input name="cost[${len3+len4+status2.index}].reason" type="text" value="${ssbMap[dic2.code].reason}"  readonly="readonly"></td>
	                            </c:if>
	                        </tr>
	                        <c:forEach var="dic3" items="${dic2.children}" varStatus="status3">
		                        <tr>
		                            <c:set var="len5" value="${fn:length(dic1.children) * (status2.index+1) }"></c:set>
		                            <th class="level5">${dic3.name }</th>
		                            <c:if test="${dic3.parent == true}">
			                            <td class="price-ly">
			                                <input name="cost[${len3+len4+len5+status3.index}].cost" class="price_${dic2.code}" id="total_${dic3.code}" type="hidden" value="${ssbMap[dic3.code].cost}" />
			                                <input name="cost[${len3+len4+len5+status3.index}].code" type="hidden" value="${dic3.code}" />
			                                <input name="cost[${len3+len4+len5+status3.index}].name" type="hidden" value="${dic3.name}" />
			                                <span class="total_${dic3.code}">${ssbMap[dic3.code].cost == null ?'0.00': ssbMap[dic3.code].cost}</span>
			                            </td>
			                            <td>/</td>
		                            </c:if>
		                            <c:if test="${dic3.parent == false}">
			                            <td>
			                                <input name="cost[${len3+len4+len5+status3.index}].cost" class="price cost price_${dic2.code}" type="number" data-pcodes="${dic2.code},${dic1.code},${dic.code}" placeholder="0.00" value="${ssbMap[dic3.code].cost}"  readonly="readonly"/>
			                                <input name="cost[${len3+len4+len5+status3.index}].code" type="hidden" value="${dic3.code}" />
			                                <input name="cost[${len3+len4+len5+status3.index}].name" type="hidden" value="${dic3.name}" />
			                            </td>
			                            <td><input name="cost[${len3+len4+len5+status3.index}].reason" type="text" value="${ssbMap[dic3.code].reason}"  readonly="readonly"></td>
		                            </c:if>
		                        </tr>
                        	</c:forEach>
                       	</c:forEach>
                       </c:forEach>
                   </c:forEach>
   			</table>
		</div>
	</body>
</html>