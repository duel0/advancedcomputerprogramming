var express = require("express"),
	http = require("http"),
	app = express();

// Alternativamente posso caricare il json nella variabili toDos tramite require
//var toDos = require("./todos.json")
//
var toDos = [
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
		];


http.createServer(app).listen(3000);

console.log("server avviato on 3000...");

app.use(express.static(__dirname + "/client"));

// this is fundamental
//app.use(express.urlencoded({ extended: true })) <= remove deprecation warnings
//app.use(express.urlencoded({ extended: true }));

app.use(express.urlencoded());

/*
// calling body-parser to handle the Request Object from POST requests
var bodyParser = require('body-parser');
// parse application/json, basically parse incoming Request Object as a JSON Object 
//app.use(bodyParser.json());
// parse application/x-www-form-urlencoded, basically can only parse incoming Request Object if strings or arrays
//app.use(bodyParser.urlencoded({ extended: false }));
// combines the 2 above, then you can parse incoming Request Object if object, with nested objects, or generally any type.
app.use(bodyParser.urlencoded({ extended: true }))
*/


// ROTTA GET /todos.json

// è possibile utilizzare la notazione arrow => per rendere più snella la scrittura delle function
// app.get("/todos.json", (req, res) => {});

app.get("/todos.json", function(req, res){
	
	console.log("GET /todos.json");
	console.log("Request method " + req.method);
	console.log("Request hostname " + req.hostname);
	/*
	res.status(200); // provare a cambiare status code...
	res.json(toDos);
	*/
	// oppure fare così

	res.status(200).json(toDos);


});

// POST /todos
app.post("/todos", function(req, res){

	console.log("POST /todos");
	var newToDo = req.body;
	toDos.push(newToDo);	
	console.log(toDos);
	res.status(200);
	res.json({"messaggio": "POSTATO"});
	
});

/*

app.use([
 
	(err, req, res, next) => {
		if (err) {
			res.status(500).send(err.message || 'Internal Server Error');
		} else {
			next();
		}
	},

	(req, res) => {
		res.status(400).send('Bad request');
	}

]);

*/