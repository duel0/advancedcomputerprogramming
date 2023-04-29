// By convention, I start jQuery object variables with a $

var main = function () {
        "use strict";

        var $newUL = $("<ul>"); //create a new li element
        var $newParagraphElement = $("<p>"); // create a new p element

        $newParagraphElement.text("this is a paragraph");
    
        $("body").append($newParagraphElement); //append element created
}

$(document).ready(main);

