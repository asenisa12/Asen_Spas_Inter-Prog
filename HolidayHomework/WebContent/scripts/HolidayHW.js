$(document).ready(function() {
	"use strict"

//2nd	
	console.log($("a.tu").attr("title"));

//3rd
	console.log($("#col1 p").text());

//4th
	$("#menu-top-level-menu" ).append("<li id = 'menu-item-2915' class = 'menu-item-2915'><a>New Button</a></li>")

//5th
	$("#footer").prepend("<div id ='dynamiccontent' class = 'train'></div>")

//6th
	var itemNameInput = $("<input id = 'textinput'/>");
	$("#dynamiccontent").append($(itemNameInput));

//7th
	var addButton = $("<button id = 'addbutton'/>").text("ADD");
	$(".train").append($(addButton));	

//8th	
	var addUL = $("<ul id = 'posts' />");
	$("#dynamiccontent").append($(addUL));

//9th
	var uuu = 0;
	$(".menu-item-2915").click(function() {
		uuu++;
		alert("hello world");
		
	});
	
//10th
	$(".menu-item-2915").click(function() {
		if(uuu%2 == true)
			$("#col2").insertBefore($("#col1"))
		if(uuu%2 == false)
			$("#col1").insertBefore($("#col2"))
	});

//11th
function handleError(error){
		console.error("error", error, arguments);
	}
	function appendToList(list, post) {
		var newElement = $("<li/>");
		newElement.text(post.title);
		list.append(newElement);
		return newElement;
	}
	function processResponse(response) {
		var list = $("ul#posts");
		var i = 0;

		$.each(response, function() {
			appendToList(list, this);
			i++;
			if(i == 5) {
				return false;
			}
		});
	}
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
		method: "GET"
	}).then(processResponse, handleError);

});