/**
 * main javascript
 */
$(document).ready(function(){
	console.log(window.sessionStorage);
})
function goBoardHome(){
	location.href="/board";
}

function goBoardDetail(){
	location.href="/board/detail";
}

function goBoardInput(){
	location.href="/board/input";
}

function doBoardInsert(){
	document.bFrm01.action="/board/insert";
	document.bFrm01.method ="post";
	document.bFrm01.submit();
}

function doBoardUpdate(){
	document.bFrm01.action="/board/update";
	document.bFrm01.method ="post";
	document.bFrm01.submit();
}

function doBoardDelete(){
	document.bFrm01.action="/board/delete";
	document.bFrm01.submit();
}

