var express = require("express"),
    http = require("http"),
    // Importa mongoose
    mongoose = require("mongoose"),
    app = express();

app.use(express.static(__dirname + "/client"));
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Connettiti al database MongoDB
mongoose.connect("mongodb://127.0.0.1:27017/centralino", { useNewUrlParser: true, useUnifiedTopology: true });

// Definisci lo schema per la chiamata telefonica
var CallSchema = mongoose.Schema({
  id: Number,
  data: String,
  ora: String,
  note: String,
  esito: Number
});

// Crea il modello per la chiamata telefonica
var Call = mongoose.model("Call", CallSchema);

http.createServer(app).listen(3000);

// Rotte per la registrazione e visualizzazione delle chiamate

// Registra una nuova chiamata
app.post("/api/chiamate", function(req, res) {
  var call = new Call({
    id: req.body.id,
    data: req.body.data,
    ora: req.body.ora,
    note: req.body.note,
    esito: req.body.esito
  });

  call.save(function(err, call) {
    if (err) {
      res.status(500).send(err);
    } else {
      res.json(call);
    }
  });
});

// Ottieni tutte le chiamate registrate
app.get("/api/chiamate", function(req, res) {
  Call.find({}, function(err, calls) {
    if (err) {
      res.status(500).send(err);
    } else {
      res.json(calls);
    }
  });
});

console.log("Server in ascolto sulla porta 3000");
