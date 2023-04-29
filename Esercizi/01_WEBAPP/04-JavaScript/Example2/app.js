var main = function () {
    "use strict";

    
    var addCommentFromInputBox = function () {
        var $new_comment; // il $ in una variabile è una convenzione per dire che sto usando quella var
                            // per elementi del DOM

        
        if ($(".comment-input input").val() !== "") { //check valore nullo
            $new_comment = $("<p>").text($(".comment-input input").val()); // prendo il valore della text box
            $new_comment.hide(); // serve per avere l'effetto fadein
            $(".comments").append($new_comment);
            $new_comment.fadeIn();
            $(".comment-input input").val(""); //cancella il text dopo aver inserito
        }
    };

    $(".comment-input button").on("click", function (event) {
        addCommentFromInputBox();
    });

    $(".comment-input input").on("keypress", function (event) {
        if (event.keyCode === 13) { //keycode = 13 perchè in ASCII enter key è 13 in decimale
            // keyCode è deprecato, usare key, ovvero event.key === 'Enter'
            //if (event.key === 'Enter'){
            addCommentFromInputBox();
        }
    });
};

$(document).ready(main);
