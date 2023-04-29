var main = function () {
    	"use strict";

	var insertCountsIntoDOM = function(counts){

		console.log(counts);
		$("p").text("COUNT VACCINE WORD = " + counts);

	}

	setInterval(function(){

		//fare la GET su counts.json

		$.getJSON("/counts.json", insertCountsIntoDOM);


	}, 5000);

	$.getJSON("/counts.json", insertCountsIntoDOM);
};

$(document).ready(main);

