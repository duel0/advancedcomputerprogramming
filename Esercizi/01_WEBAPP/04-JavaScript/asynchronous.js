
var main = function () { "use strict";
        // create and hide our content as a div
        var $content = $("<div>Hello World!</div>").hide();
        var $moreContent = $("<div>Goodbye World!</div>").hide();
        // append the content to the body element
        $("body").append($content);

        // slide the content down for 2 seconds 
        // and then execute the callback which 
        // contains the second content 
 
        /* fare prova senza usare il meccanismo di callback */
    
        $content.slideDown(2000, function () {
        
                // append the second content to the body
                $("body").append($moreContent);
        
                // fade in the second content
                $moreContent.fadeIn();
        });
};

$(document).ready(main);
