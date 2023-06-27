var order_update = 0;  // order_update track if orders were added by clients to PENDING

var main = function (orderObjects) {

    "use strict";

    $(".tabs a span").toArray().forEach(function (element) {
        var $element = $(element);

        // create a click handler for this element
        $element.on("click", function () {
        
            $(".tabs a span").removeClass("active");
            $element.addClass("active");
            $("main .content").empty();


            // comportamento tabs
            // PENDING ORDERS
            // Mostra tutti gli ordini fatti dal cliente e permette di cambiare stato a COMPLETED o REJECTED, aggiornando il DB
            

            if ($element.parent().is(":nth-child(1)")) {

                // Utilizzare querySelector su ul e addEventListener per ottenere <li> selezionato con ID specifico
                document.querySelector('ul').addEventListener('click', function(e) {
                    
                });
                 
            }

            // Completed ORDERS
            // Mostra tutti gli ordini che sono nello stato di COMPLETED
            else if ($element.parent().is(":nth-child(2)")) {

               
                 
            }

            console.log("content to add to main" + $content)
            $("main .content").append($content);

            return false;
        });
    });

    $(".tabs a:first-child span").trigger("click");
};


// Add code for notifications


// When the document is loaded
$(document).ready(function () {

    main();                     // Call the main function

});
