const bodyParser = require('body-parser');
var express = require("express"),
	http = require("http"),
	mongoose = require("mongoose"),
	app = express();
app.use(express.static(__dirname + "/client"));

app.use(express.urlencoded());
app.use(bodyParser.urlencoded({ extended: true }));

mongoose.connect('mongodb://127.0.0.1:27017/callcenterfinal');

// Definisco lo schema MongoDB
var CallSchema = mongoose.Schema({
	numero: String,
	data: String,
	ora: String,
	note: String,
	esito: String
});

// Model della singola chiamata
var Call = mongoose.model("Call", CallSchema);

http.createServer(app).listen(3000);

// app.js farà una GET di calls.json, verrò restituito il json di quanto trovato in MongoDB con find {} (prendo tutte le chiamate)
app.get("/calls.json", function (req, res) {
	Call.find({}, function (err, calls) {
		res.json(calls);
	});
});

// Inserisco nuova chiamata, prendo tutti i dati dal body della richiesta e inizializzo un nuovo Call con i parametri
app.post("/calls", function (req, res) {

	console.log(req.body);

	var newCall = new Call({ "numero": req.body.numero, "data": req.body.data, "ora": req.body.ora, "note": req.body.note, "esito": req.body.esito });

	newCall.save(function (err, result) {
		if (err !== null) {
			console.log(err);
			res.send("ERROR");

		} else {

			Call.find({}, function (err, result) {
				// Inviamo nuovamente la risposta
				if (err !== null) {
					// Controllo errori
					res.send("ERROR");
				}

				res.json(result);
			});
		}
	});
});