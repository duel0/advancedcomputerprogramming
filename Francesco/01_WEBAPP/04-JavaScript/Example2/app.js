var main = function() {
    "use strict"

    var addComment = function(){
        if($(".comment-input input").val()!==""){
            var $new_comment;
            $new_comment=$("<p>").text($(".comment-input input").val());
            $new_comment.hide();
            $(".comments").append($new_comment);
            $new_comment.fadeIn();
            $(".comment-input input").val("");
        }
    }

    $(".comment-input button").on("click", function(){
        addComment();
    });
    $(".comment-input input").on("keypress", function(event){
        if(event.key==="Enter"){
            addComment();
        }
    });
}

$(document).ready(main);