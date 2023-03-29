

/**
 * main javascript
 */
$(document).ready(function(){
	if($('.form-select')){
		$.ajax({
			url:"/market/ajaxL",
			type : "GET",
			success:function(data){
				console.log(data);
				var makeHtml="";
				for(var i in data ){
					console.log(data[i]);
					makeHtml += "<option id='cat-L' value='"+data[i]+"'>"+data[i]+"</option>"
				}
				$('#cat-L').replaceWith(makeHtml);
			},error:function(e){
				console.log("실패! : ",e);

			}
		});




	}

});


function goMarketHome(){
	location.href="/market";
}

function goMarketDetail(){
	location.href="/market/detail";
}

function goMarketInput(){
	location.href="/market/input";
}

function doMarketInsert(){

	document.mFrm01.action="/market/insert";
	document.mFrm01.method ="post";
	document.mFrm01.submit();
}

function doMarketUpdate(){
	document.mFrm01.action="/market/update";
	document.mFrm01.method ="post";
	document.mFrm01.submit();
}

function doMarketDelete(){
	document.mFrm01.action="/market/delete";
	document.mFrm01.submit();
}



