<%@page import="org.cd.sport.action.UploadAction"%>
<%@ page language="java" import="java.util.*,com.zhuozhengsoft.pageoffice.*" pageEncoding="UTF-8"%>
<%
	FileSaver fs=new FileSaver(request,response);
	String realPath = request.getSession().getServletContext().getRealPath("/");
	fs.saveToFile(realPath+"/"+fs.getFileName());
	fs.close();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body></body>
</html>
