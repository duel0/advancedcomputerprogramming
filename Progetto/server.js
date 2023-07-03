const { kStringMaxLength } = require("buffer");
const { time } = require("console");

var express = require("express"),
    http = require("http"),
    mongoose = require("mongoose"),
    app = express();

app.use(express.static(__dirname + "/client"));

app.use(express.urlencoded());

mongoose.connect('mongodb://127.0.0.1:27017/callcenterfinal');

var CallSchema = mongoose.Schema({
	numero : String,
	data : String,
	ora : String,
	note : String,
	esito : String
});

var Call = mongoose.model("Call", CallSchema);

http.createServer(app).listen(3000);

app.get("/calls.json", function (req, res) {
	Call.find({}, function (err, calls) {
		res.json(calls);
    	});
});

app.post("/calls", function (req, res) {
    
	console.log(req.body);

	var newCall = new Call({"numero":req.body.numero, "data":req.body.data, "ora":req.body.ora, "note":req.body.note, "esito":req.body.esito});

	newCall.save(function (err, result) {
		if (err !== null) {
			console.log(err);
			res.send("ERROR");

		} else {
			// Ridiamo la lista ogni volta
			Call.find({}, function (err, result) {
				
				if (err !== null) {
			    		// Controllo errori
			    		res.send("ERROR");
				}
			
				res.json(result);
			});
		}
	});
});
