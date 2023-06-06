var express = require("express"),
    http = require("http"),
    mongoose = require("mongoose")
    app=express();

app.use(express.static(__dirname + "/client"));

app.use(express.urlencoded());

mongoose.connect('mongodb://127.0.0.1:27017/centralino');

var chiamateSchema = mongoose.Schema({
    data: String,
    ora: String,
    note: String,
    esito: String,
})

var Chiamata = mongoose.model("Chiamata",chiamateSchema);

http.createServer(app).listen(1234);

app.get("/chiamate.json", function(req,res){
    Chiamata.find({}, function(err,chiamate){
        res.json(chiamate);
    });
});

app.post("/chiamate", function(req,res){
    var nuovaChiamata = new Chiamata({"data":req.body.data,"ora":req.body.ora,"note":req.body.note,"esito":req.body.esito});
    nuovaChiamata.save(function(err,result){
        if(err!==null){
            console.log(err);
            res.send("ERRORE!!");
        } else {
            // Rivuole indietro la lista di chiamate
            Chiamata.find({}, function(err,result){
                if(err!==null){
                    res.send("ERRORE FIND!");
                }
                res.json(result);
            });
        }
    });
});