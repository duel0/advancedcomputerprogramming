const mongoose = require("mongoose");

// connect to the amazeriffic data store in mongo
mongoose.connect('mongodb://localhost/prova_mongoose');

var CardSchema = mongoose.Schema({ 

	"rank" : String,
        "suit" : String

});

var Card = mongoose.model("Card", CardSchema);

var main = function(){

    //remove all => Card.remove({});
    
	
	var remove_card = function(){
		Card.remove({ "rank" : "ace", "suit" : "spades" }, function (err) { 
			
			if (err !== null) {
				// object was not successfully removed!
				console.log(err);
			}
			else{
				console.log("REMOVE OK!");
			}
		});
	};
	

	var c1 = new Card({"rank":"ace", "suit":"spades"});

	// save this card to our data store

	c1.save(function (err) { 
		
		if (err !== null) {
		    // object was not saved!
			console.log(err); 
		} else {
			console.log("the object was saved!");
			
		}
		remove_card();
	});

	Card.find({}, function (err, cards) { 
		
		if (err !== null) {
			console.log("ERROR: " + err); // return from the function return;
		}
		// if we get here, there was no error

		cards.forEach(function (card) {

			// this will print all of the cards in the database console.log (card.rank + " of " + card.suit);
			console.log ("[Card.find]" + card.rank + " of " + card.suit);
		}); 

	});
	
	Card.find({"suit" : "spades"}, function (err, cards) { 
		console.log('invoked Card.find({"suit" : "spades"}');
		cards.forEach(function (card) {
			// update the card to spades
			card.suit = "hearts";
			// save the updated card
			card.save(function (err) { 
				
				if (err) {
					// object was not saved
					console.log(err);
				}
			}); 
		});
	});

}

