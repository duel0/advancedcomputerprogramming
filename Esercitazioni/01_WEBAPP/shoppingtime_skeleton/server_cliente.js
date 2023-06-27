var express = require("express"),
    http = require("http"),
    mongoose = require("mongoose"),
    app = express(),
	id = 0;

app.use(express.static(__dirname + "/client"));

app.use(express.urlencoded());

mongoose.connect('mongodb://localhost/shoppingtime');


var OrderSchema = mongoose.Schema({    
    id: Number,
	status: {
		type: [String],
		enum : ["CART", "PENDING", "COMPLETED", "REJECTED"],
		default: 'CART'
	},
	products: [ {name: String, quantity: Number} ]

});



var Order = mongoose.model("Order", OrderSchema);


http.createServer(app).listen(3000);

// Aggiungere rotte e codice per la generazione dell'indice
