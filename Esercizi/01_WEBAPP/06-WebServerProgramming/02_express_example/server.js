var express = require("express"),
	http = require("http"),
	app = express();

http.createServer(app).listen(3000);

console.log("server in ascolto su 3000");

app.use(express.static(__dirname + "/client"));

//rotta GET ~/hello
app.get("/hello", function (req, res) {

	console.log("METODO per /hello " + req.method);
	res.send("Hello world!");

});

//rotta GET ~/goodby
app.get("/goodbye", function (req, res){


	res.send("CIAO CIAO!");
});

//rotta di default, non viene considerata se usiamo app.use(express.static(__dirname + "/client")); 
app.get("/", function (req, res){


	//res.send("CIAO SONO LA ROTTA DI DEFAULT!");
	res.send("<html><head></head><body><h1>Hello World! INDEX PROVA</h1></body></html>");
	
});


