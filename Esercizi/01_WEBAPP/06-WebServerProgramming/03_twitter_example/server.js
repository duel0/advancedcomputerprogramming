var express = require("express"),
    http = require("http"),
    app = express();


// var tweetCounts = require("./search.js") <= errore perchè l'oggetto JS non si aggiorna ad ogni GET /counts.json

// avvia un server web su porto 3000
http.createServer(app).listen(3000);

app.use(express.static(__dirname + "/client"));

// route GET /counts.json
app.get("/counts.json", function (req, res) {
	// richiedo la logica implementata in search.js che mi restituisce il count dei tweet
	tweetCounts = require("./search.js");
	console.log("tweetCounts: ", (tweetCounts).toString());

	// rispondo con il count stesso in formato json
	res.json(tweetCounts);
});

