$(function() {
	$(".searchBtn").click(function() {
		$(".searchBox").slideToggle();
	});
});

function doResize() {
	var ss = getPageSize();
	$("#jqGrid").jqGrid('setGridWidth', ss.WinW - 20).jqGrid('setGridHeight', ss.WinH - 300);
}

function getPageSize() {
	var winW, winH;
	if(window.innerHeight) { // all except IE 
		winW = window.innerWidth;
		winH = window.innerHeight;
	} else if(document.documentElement && document.documentElement.clientHeight) { // IE 6 Strict Mode 
		winW = document.documentElement.clientWidth;
		winH = document.documentElement.clientHeight;
	} else if(document.body) { // other 
		winW = document.body.clientWidth;
		winH = document.body.clientHeight;
	} // for small pages with total size less then the viewport  
	return {
		WinW: winW,
		WinH: winH
	};
}

$(document).ready(function() {
	if($("#jqGrid").length > 0) {
		$("#jqGrid").setGridWidth($(window).width() * 0.99);
		$("#jqGrid").setGridWidth(document.body.clientWidth * 0.99);
	}
});