$(function() {
	$(".searchBtn").click(function() {
		$(".searchBox").slideToggle("fast", "swing", function() {
			doResize();
		});
	});
});

function doResize() {
	var ss = getPageSize();
	var height = ss.WinH - 280;
	if($(".searchBox").is(':visible')) {
		height = height - 60;
	}
	$("#jqGrid").jqGrid('setGridWidth', $(".listBox").width()).jqGrid('setGridHeight', height);
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
});