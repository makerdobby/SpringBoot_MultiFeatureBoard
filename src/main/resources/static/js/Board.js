/**
 * main javascript
 */

/*var editorInstance;

$(document).ready(function(){

	editorInstance = new toastui.Editor({
							el: document.querySelector('#editor'),
							previewStyle: 'vertical',
							height: '400px',
							language: 'ko',
							initialEditType: "html"
						});

});*/
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

/*	// 마크다운내용 저장

	var editorMarkdown = editorInstance.getMarkdown();
	var htmlContent = marked(editorMarkdown);
	$('#htmlContent').val(htmlContent);

	return;*/

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
