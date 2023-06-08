// JavaScript code

// Funzione per inviare una richiesta HTTP al server per registrare una chiamata
function registraChiamata() {
  // Ottieni i dati del modulo
  var id = document.getElementById("id").value;
  var data = document.getElementById("data").value;
  var ora = document.getElementById("ora").value;
  var note = document.getElementById("note").value;
  var esito = document.getElementById("esito").value;

  // Crea un oggetto con i dati della chiamata
  var chiamata = {
    id: id,
    data: data,
    ora: ora,
    note: note,
    esito: esito
  };

  // Invia una richiesta POST al server per registrare la chiamata
  fetch("/api/chiamate", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(chiamata)
  })
    .then(function(response) {
      if (response.ok) {
        alert("Chiamata registrata con successo!");
      } else {
        alert("Si è verificato un errore durante la registrazione della chiamata.");
      }
    })
    .catch(function(error) {
      alert("Si è verificato un errore durante la registrazione della chiamata.");
      console.log(error);
    });
}

// Funzione per caricare le chiamate registrate dal server e visualizzarle
function visualizzaChiamate() {
  // Ottieni l'elemento HTML in cui visualizzare le chiamate
  var contentElement = document.querySelector(".content");

  // Svuota il contenuto precedente
  contentElement.innerHTML = "";

  // Ottieni le chiamate dal server
  fetch("/api/chiamate")
    .then(function(response) {
      return response.json();
    })
    .then(function(chiamate) {
      // Itera sulle chiamate e crea gli elementi HTML per visualizzarle
      chiamate.forEach(function(chiamata) {
        var chiamataElement = document.createElement("div");
        chiamataElement.classList.add("chiamata");

        var idElement = document.createElement("p");
        idElement.textContent = "ID: " + chiamata.id;

        var dataElement = document.createElement("p");
        dataElement.textContent = "Data: " + chiamata.data;

        var oraElement = document.createElement("p");
        oraElement.textContent = "Ora: " + chiamata.ora;

        var noteElement = document.createElement("p");
        noteElement.textContent = "Note: " + chiamata.note;

        var esitoElement = document.createElement("p");
        esitoElement.textContent = "Esito: " + chiamata.esito;

        chiamataElement.appendChild(idElement);
        chiamataElement.appendChild(dataElement);
        chiamataElement.appendChild(oraElement);
        chiamataElement.appendChild(noteElement);
        chiamataElement.appendChild(esitoElement);

        contentElement.appendChild(chiamataElement);
      });
    })
    .catch(function(error) {
      alert("Si è verificato un errore durante il recupero delle chiamate.");
      console.log(error);
    });
}

// Collega le funzioni ai pulsanti del modulo
document.getElementById("registra").addEventListener("click", registraChiamata);
document.getElementById("visualizza").addEventListener("click", visualizzaChiamate);
