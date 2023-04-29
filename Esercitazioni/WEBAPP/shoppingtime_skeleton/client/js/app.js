var order_update = 0;  // order_update tracks if the status of orders were modified by the operator


var main = function () {

    "use strict";

    $(".tabs a span").toArray().forEach(function (element) {
        var $element = $(element);

        // create a click handler for this element
        $element.on("click", function () {
            var $content;

            

            $(".tabs a span").removeClass("active");
            $element.addClass("active");
            $("main .content").empty();

            if ($element.parent().is(":nth-child(1)")) { // "Orders" tab: Shows all the orders made by the client
                
                
                

            } else if ($element.parent().is(":nth-child(2)")) { // "Add product" tab: Allows the client to add a product to the cart
                


            } else if ($element.parent().is(":nth-child(3)")) { // "Cart" tab: Shows all the products in the cart, allowing to submit the order

                

            }

            $("main .content").append($content);

            return false;
        });
    });

    $(".tabs a:first-child span").trigger("click");
};

// Add code for notification


// When the document is loaded
$(document).ready(function () {

    main();                     // Call the main function

});
