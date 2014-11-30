$(document).ready(function() {
	"use strict"
	
	var list = $("#list");
	
	var i = 2;
	$("#button").click(function(){
		list.append("<li>item"+i+"</li>");
		i++;
		
	});
	
});