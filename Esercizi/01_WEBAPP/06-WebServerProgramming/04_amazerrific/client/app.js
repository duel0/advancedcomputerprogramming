var main = function(toDoObjects) {

	
	"use strict";

	var tabNumber;

	/*
	var toDos = [
			"Finish writing this book",
	                 "Take Gracie to the park",
        	         "Answer emails",
                	 "Prep for Monday's class",
	                 "Make up some new ToDos",
        	         "Get Groceries"
			];
	*/

	var toDos = toDoObjects.map(function (toDo){
	
		return toDo.description;
	
	});


	console.log(toDos);




	$(".tabs a span").toArray().forEach(function (element) {

			
		$(element).on("click", function () {

			var $element = $(element);
			var $content, i, $input, $button;

			$(".tabs a span").removeClass("active");
                        $element.addClass("active");
                        $("main .content").empty();

			if ($element.parent().is(":nth-child(1)")){

				console.log("click 1 tab");

				// voglio caricare la lista dei todo in maniera
				// dal piu' recente
				//
				$content = $('<ul>');
				//$content.append("<li>Get Groceries</li>");
				for (i = toDos.length-1; i>=0; i--){
					$content.append($('<li>').text(toDos[i]));
				}

			} else if ($element.parent().is(":nth-child(2)")){

				console.log("click 2 tab");

				$content = $('<ul>');
				toDos.forEach(function (todo_el){
					
					$content.append($('<li>').text(todo_el));
				
				});

			} else if ($element.parent().is(":nth-child(3)")){
			
				var tags = [];
				// tags = ["chores, "shopping", "writing", "work", "teaching",...]
				toDoObjects.forEach(function (toDo){
				
					toDo.tags.forEach(function (tag){
					
						// per non creare duplicati!
						if (tags.indexOf(tag) === -1){
							tags.push(tag);
						}
					
					});
				
				
				});

				console.log("ARRAY DEI TAG => " + tags);
			
				// [
				//
				// 	"chores, 
				// 	"shopping", 
				// 	"writing", 
				// 	"work", 
				// 	"teaching" 
				//
				// ]
			        
				// "shopping" -> { "name": "shopping", "toDos": ["Get Groceries"]}
				// "work" -> { "name": "work", "toDos": ["Make up some new ToDos", "Answer emails", "Prep for Monday's class""]}
				//
				/*
				[
				    {
					"description" : "Get groceries",
					"tags"  : [ "shopping", "chores" ]
				    },
				    {
					"description" : "Make up some new ToDos",
					"tags"  : [ "writing", "work" ]
				    },
				    {
					"description" : "Prep for Monday's class",
					"tags"  : [ "work", "teaching" ]
				    },
				    {
					"description" : "Answer emails",
					"tags"  : [ "work" ]
				    },
				    {
					"description" : "Take Gracie to the park",
					"tags"  : [ "chores", "pets" ]
				    },
				    {
					"description" : "Finish writing this book",
					"tags"  : [ "writing", "work" ]
				    }
				]
				*/

				// tags = ["chores, "shopping", "writing", "work", "teaching",...]

				var tagObjects = tags.map(function (tag){
				
					//....
					var toDoWithTag = [];
					
					toDoObjects.forEach(function (toDo){
					
						/*
						 * 
						 * {
                                        		"description" : "Get groceries",
		                                        "tags"  : [ "shopping", "chores" ]
                		                    },
						*/

						if (toDo.tags.indexOf(tag) !== -1){
						
							toDoWithTag.push(toDo.description);
						}

					
					});
					// tag = "chores" -> toDoWithTag = ["Get groceries", "Take Gracie to the park"]
				
					return {"name": tag, "toDos": toDoWithTag};
				});


				console.log("tagObjects CONTENUTO");
				console.log(tagObjects);
				
				/// FAI IL RENDERING HTML

				tagObjects.forEach(function (tag){
				
					var $tagName = $("<h3>").text(tag.name), 
					    $content = $("<ul>");

					tag.toDos.forEach(function (description){
					
						var $li = $("<li>").text(description);

						$content.append($li);
						
					});

				        $("main .content").append($tagName);
				        $("main .content").append($content);

				});
				
		
			} else if ($element.parent().is(":nth-child(4)")){

				//TODO: modificare con il campo tag
				console.log("click 4 tab");

				// add 2 inputbox
				// add 1 bottone
				//
				// <input class="description">
				var $input = $("<input>").addClass("description"),
					$inputLabel = $("<p>").text("DESCRIZIONE:"),
					$tagInput = $("<input>").addClass("tags"),
					$tagLabel = $("<p>").text("TAGS:"),
					$button = $("<button>").text("ADD");

				var $content = $("<div>").append($inputLabel).append($input).append($tagLabel).append($tagInput).append($button);

				//comportamento bottone
				//
				$button.on("click", function() {
				
					var description = $input.val();
					var tags = $tagInput.val().split(",");
					var newToDo;
					console.log(description);
					console.log(tags);					

					//aggiorno la variabile che contiene il todo.json
					//var object_to_push = {};
					toDoObjects.push({"description": description, "tags": tags});
					newToDo = {"description": description, "tags": tags};

					// FARE POST del todo appena aggiunto
					// L'array toDos in questo caso non è più una var locale, ma va fatta una POST al server
					
					$.post("/todos", newToDo, function(response){
					
						console.log("posted to server, RESPONSE:");
						console.log(response);	
					
					});
					//toDos.push(description); //CODICE VECCHIO per aggiornare l'array local toDos

					toDos = toDoObjects.map(function (toDo){
					
						return toDo.description;
					
					});

					$input.val("");
					$tagInput.val("");
				
				});

			}
			
			$("main .content").append($content);
			return false;
		});
	});

	//trigger()
	
	$(".tabs a:first-child span").trigger("click");
};


$(document).ready(function(){

	

	console.log(location);

	//var url = "http://localhost:3000" + "/todos.json";
	//$.getJSON(url, ....)

	// Possiamo utilizzare il metodo ajax
	/*
	$.ajax({
		method: "GET",
		dataType: "json",
		url: "/todos.json",
		success: function(toDoObjects){
	
			console.log(toDoObjects);
	             main(toDoObjects);
		},
		//error
		complete: function(){
			alert("DONE");
		},
		error: function(jqXHR, textStatus, error){
	
			console.log(jqXHR);
			console.log(textStatus);
			console.log(error);
	
			if (jqXHR.status === 404){
				alert("ERROR 404 NOT FOUND");
				//fai quello che devi gestire con un errore 404
			}
			else {
			
				console.log("ERRORE GENERICO");
			}
		}
	});
	*/
	// Caricare tramite GET /todos.json al server la lista dei todo correnti
	// in getJSON possiamo specificare
	// /todos.json o todos.json per richiedere la risorsa
	// di defautl viene utilizzata la variabile location che include host:port (e.g., localhost:3000)
	// se non c'è nessun webserver in ascolto e specifico todos.json mi prende la risorsa locale
	//$.getJSON("todos.json", function(toDoObjects){
	
	console.log("location", location);
	$.getJSON("/todos.json", function(toDoObjects){
	
		console.log("$.getJSON(todos.json)");
		// on SUCCESS
		console.log("GET /todos.json SUCCESS");	
		main(toDoObjects);

	})
	/*
		in vecchi script js potete trovare anche .success ma è deprecato!
    	già la callback viene eseguita in caso di successo
     	success si attiva solo se la chiamata AJAX ha esito positivo, ovvero alla fine restituisce uno stato HTTP 200.
     	fail si attiva se fallisce
     	complete quando la richiesta termina, indipendentemente dal successo.
	*/

	/*
	// .done ha rimpiazzato success
	.success(function (toDoObjects){

		main(toDoObjects);
		alert("SUCCESS!");
	})
	*/
	// always ha rimpiazzato .complete
	/*
	.always(function() { 
		alert("Done");
		console.log(".always(function()) triggered");
    })
	*/
	//.error
	.fail(function(jqXHR, textStatus, error){
	
		console.log(jqXHR);
		console.log(textStatus);
		console.log(error);

		if (jqXHR.status === 404){
			alert("ERROR 404 NOT FOUND");
			//fai quello che devi gestire con un errore 404
		}
		else {
		
			console.log("ERRORE GENERICO");
		}
	});

	
	

});


