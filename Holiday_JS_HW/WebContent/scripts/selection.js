$(document).ready(function() {
	"use strict"
	
	//2)
	console.log($("#footer a:first"));
	
	//3)
	console.log($("div:contains('Училището') p:first").text());
	
	//4)
	$("#menu-top-level-menu").append("<a>new button</a>");
	
	//5)
	$("#footer").prepend("<div id='dynamiccontent'></div>");
	
	//6)
	$("#footer div:first").append("<input id = 'textinput'/>");
	var inputText = $("#textinput");
	
	//7)
	$("#footer div:first").append("<button id = 'addbutton'>add Text</button>");
	var button = $("#addbutton");
	
	//8)
	$("#footer div:first").append("<ul id='posts'></ul>");
	
	//9
	var new_button = $("#menu-top-level-menu a");
	new_button.click(function(){
		alert("hello world");
	});
	
	//10
	new_button.click(function(){
		var a = $("#menu-top-level-menu a:contains('Съобщения')");
		var aClone = a.clone();
		
		var b = $("#menu-top-level-menu a:contains('Училище')");
		var bClone = b.clone();
		
		b.after(aClone);
		a.after(bClone);
		
		a.remove();
		b.remove();
	});
	
	//11
	function handleError(error)
	{
		console.error("error", error, arguments);
	}
	
	function appendToList(list, post){
		var newElement = $("<li/>");
		newElement.text(post.title);
		list.append(newElement);
	}
	function processResponse(response){
		var list = $("#posts");
		var i=0;
		$.each(response, function(){
			appendToList(list, this);
			if(i++ >=5){
				return false;
			}
		});
	}
	
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
		  method: "GET"
	}).then(processResponse, handleError);
	
	//12)
	var postnum =1;
	var addElement = function(){
		var text = inputText.val();
		if(!text) {
			alert("you must enter text!!!");
			return;
		}
	//13)
		$.ajax("http://jsonplaceholder.typicode.com/posts", {
			method: "Post",
			data: {
				title: 'Title '+postnum,
				body: text,
				userId: postnum
			}
		});
		postnum++;
		inputText.val("");
	};
	
	
	button.click(addElement);
});