var express = require("express"),
    http = require("http"),
    // 1. importare mongoose
    mongoose = require("mongoose"),
    app = express();

app.use(express.static(__dirname + "/client"));
//app.use(express.bodyParser());

// 2. usare urlencoded per parsare le req/resp json
app.use(express.urlencoded());

// 3. connect to the amazeriffic data store in mongo
mongoose.connect('mongodb://localhost/amazeriffic');

// 4. Definire lo schema dei TODO
var ToDoSchema = mongoose.Schema({
    
    description: String,
    tags: [ String ]

});

// 5. Definire il modello
var ToDo = mongoose.model("ToDo", ToDoSchema);


http.createServer(app).listen(3000);

//6. Definire le rotte per il GET dei todo e il POST di nuovi TODO

app.get("/todos.json", function (req, res) {
    	
	ToDo.find({}, function (err, toDos) {
		res.json(toDos);
    	});
});

app.post("/todos", function (req, res) {
    
	console.log(req.body);

	var newToDo = new ToDo({"description":req.body.description, "tags":req.body.tags});

	newToDo.save(function (err, result) {
		if (err !== null) {
			// the element did not get saved!
			console.log(err);
			res.send("ERROR");

		} else {
			// Ricordiamoci che la parte client js richiede ogni volta la lista di tutti i todo
			// ogni volta che noi ne aggiungiamo uno

			ToDo.find({}, function (err, result) {
				
				if (err !== null) {
			    		// the element did not get saved!
			    		res.send("ERROR");
				}
			
				res.json(result);
			});
		}
	});
});
