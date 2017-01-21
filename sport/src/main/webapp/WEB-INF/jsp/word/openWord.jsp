<%@page import="org.cd.sport.vo.UserVo"%>
<%@page import="org.cd.sport.utils.AuthenticationUtils"%>
<%@page import="org.cd.sport.action.UploadAction"%>
<%@ page language="java" import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	UserVo user = AuthenticationUtils.getUser();
	request.setAttribute("loginUser", user);
	String fileId=request.getParameter("fileId");
	String title=request.getParameter("title");
	System.out.print(fileId +"titie ---> " +title);
	String realPath = request.getSession().getServletContext().getRealPath("/" + UploadAction.DOC_DIR);
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	
	//设置服务器页面
	poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
	WordDocument wordDoc = new WordDocument();
	// 设置水印
	//wordDoc.getWaterMark().setText("国家体育总局");
	//wordDoc.getTemplate().defineDataRegion("PO_FM","封面");
	//poCtrl.addCustomToolButton("定义数据区域", "ShowDefineDataRegions()", 3);
	
	//DataRegion dataRegion = wordDoc.openDataRegion("PO_p1");
	//System.out.println("*********************88"+dataRegion.getName());
	//dataRegion.setValue("[word]"+realPath+"/sbs_new_template_000.doc[/word]");
	
	poCtrl.setWriter(wordDoc);
	// 设置标题
	poCtrl.	setCaption(title);
	//poCtrl.addCustomToolButton("加盖印章", "InsertSeal()", 2);
	//poCtrl.addCustomToolButton("签字", "AddHandSign()", 3);
	//poCtrl.addCustomToolButton("验证印章", "VerifySeal()", 5);
	//poCtrl.addCustomToolButton("修改密码", "ChangePsw()", 0);
	poCtrl.setSaveFilePage("/word/SaveFile");
	// 保存数据
	//poCtrl.setSaveDataPage("word/SaveData");
	
	boolean readonly = (Boolean)request.getAttribute("readonly");
	poCtrl.setOfficeToolbars(!readonly);
	poCtrl.webOpen(realPath+"/"+fileId, readonly?OpenModeType.docReadOnly:OpenModeType.docNormalEdit, user.getUserName());
	poCtrl.setTagId("PageOfficeCtrl1");//此行必需
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>保存文档中指定位置的数据的同时也保存该文档</title>
<script type="text/javascript">
//获取后台添加的书签名称字符串
function getBkNames() {
    var bkNames = document.getElementById("PageOfficeCtrl1").DataRegionList.DefineNames;
    return bkNames;
}

//获取后台添加的书签文本字符串
function getBkConts() {
    var bkConts = document.getElementById("PageOfficeCtrl1").DataRegionList.DefineCaptions;
    return bkConts;
}

//定位书签
function locateBK(bkName) {
    var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
    drlist.GetDataRegionByName(bkName).Locate();
    document.getElementById("PageOfficeCtrl1").Activate();
    window.focus();
}

//添加书签
function addBookMark(param) {
    var tmpArr = param.split("=");
    var bkName = tmpArr[0];
    var content = tmpArr[1];
    var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
    drlist.Refresh();
    try {
        document.getElementById("PageOfficeCtrl1").Document.Application.Selection.Collapse(0);
        drlist.Add(bkName, content);
        return "true";
    } catch (e) {
        return "false";
    }
}
//删除书签
function delBookMark(bkName) {
    var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
    try {
        drlist.Delete(bkName);
        return "true";
    } catch (e) {
        return "false";
    }
}

//遍历当前Word中已存在的书签名称
function checkBkNames() {
    var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
    drlist.Refresh();
    var bkName = "";
    var bkNames = "";
    for (var i = 0; i < drlist.Count; i++) {
        bkName = drlist.Item(i).Name;
        bkNames += bkName.substr(3) + ",";
    }
    return bkNames.substr(0, bkNames.length - 1);
}

//遍历当前Word中已存在的书签文本
function checkBkConts() {
    var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
    drlist.Refresh();
    var bkCont = "";
    var bkConts = "";
    for (var i = 0; i < drlist.Count; i++) {
        bkCont = drlist.Item(i).Value;
        bkConts += bkCont + ",";
    }
    return bkConts.substr(0, bkConts.length - 1);
}

function InsertSeal() {
    try {
        document.getElementById("PageOfficeCtrl1").ZoomSeal.AddSeal();
    }
    catch (e) { }
}
function AddHandSign() {
    document.getElementById("PageOfficeCtrl1").ZoomSeal.AddHandSign();
}
function VerifySeal() {
    document.getElementById("PageOfficeCtrl1").ZoomSeal.VerifySeal();
}
function ChangePsw() {
    document.getElementById("PageOfficeCtrl1").ZoomSeal.ShowSettingsBox();
}
function ShowDefineDataRegions() {
    document.getElementById("PageOfficeCtrl1").ShowHtmlModelessDialog("/word/DataRegionDlg.action", "parameter=xx", "left=300px;top=390px;width=520px;height=410px;frame:no;");

}
</script>
</head>
<body>
	<div style="width: 878px; height: 700px;">
		<po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
	</div>
</body>
</html>
