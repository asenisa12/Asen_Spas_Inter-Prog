$(document).ready(function() {
	"use strict"
	
	console.log($("body"));
	console.log($("#home"));
	console.log($(".latest"));
	console.log($("*"));
	console.log($("div .latest"));
	console.log($(":input"));
	console.log($("div:last"));
	console.log($("li:first-of-type"));
	console.log($("div:even"));
	console.log($("label:contains('Фамилно име')"));
	console.log($("#col2 .latest"));
	console.log($("label + input"));
	console.log($("[href]"));
	console.log($(":image"));
	console.log($("[href='http://www.elsys-bg.org/blog/article/first-day-of-school/']"));
	
	$("div").after("<h2>java</h2>");
	$("li").after("<b>script</b>")

});