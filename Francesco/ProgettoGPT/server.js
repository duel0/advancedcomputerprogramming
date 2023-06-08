const express = require('express');
const app = express();
const port = 3000;
const mongoose = require('mongoose');

// Connessione a MongoDB
mongoose.connect('mongodb://127.0.0.1:27017/telefonate', {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

// Definizione dello schema per le telefonate
const phoneCallSchema = new mongoose.Schema({
  date: { type: Date, required: true },
  time: { type: String, required: true },
  notes: { type: String, required: true },
  outcome: { type: Number, required: true, min: 1, max: 5 }
});

// Creazione del modello per le telefonate
const PhoneCall = mongoose.model('PhoneCall', phoneCallSchema);

app.use(express.static('public'));
app.use(express.json());

// API per ottenere tutte le telefonate registrate
app.get('/api/phonecalls', async (req, res) => {
  try {
    const phoneCalls = await PhoneCall.find();
    res.json(phoneCalls);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Errore durante il recupero delle telefonate.' });
  }
});

// API per registrare una nuova telefonata
app.post('/api/phonecalls', async (req, res) => {
  const { date, time, notes, outcome } = req.body;

  try {
    const phoneCall = new PhoneCall({
      date,
      time,
      notes,
      outcome
    });

    await phoneCall.save();
    res.json(phoneCall);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Errore durante la registrazione della telefonata.' });
  }
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
