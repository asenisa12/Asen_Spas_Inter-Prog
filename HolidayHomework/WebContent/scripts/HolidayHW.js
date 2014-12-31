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

});