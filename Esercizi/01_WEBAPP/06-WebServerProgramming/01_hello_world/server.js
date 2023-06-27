var http = require("http"),
	server;

server = http.createServer(function (req, res){

	res.writeHead(404, {"Content-Type": "text/html"});
	res.end("hello world!!!");

});


server.listen(1234);

console.log("server in ascolto su 1234");
