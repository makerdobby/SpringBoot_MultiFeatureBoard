/**
 * main javascript
 */

function goSurveyHome(){
	location.href="/survey";
}

function goSurveyDetail(){
	location.href="/survey/detail";
}

function goSurveyInput(){
	location.href="/survey/input";
}

function doSurveyInsert(){
	document.bFrm01.action="/survey/insert";
	document.bFrm01.method ="post";
	document.bFrm01.submit();
}

function doSurveyUpdate(){
	document.bFrm01.action="/survey/update";
	document.bFrm01.method ="post";
	document.bFrm01.submit();
}

function doSurveyDelete(){
	document.bFrm01.action="/survey/delete";
	document.bFrm01.submit();
}

