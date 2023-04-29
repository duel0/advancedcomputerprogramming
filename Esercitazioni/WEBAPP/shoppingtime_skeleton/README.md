# shoppingtime

## Esercitazione WEBAPP per il corso di Advanced Computing Programming, a.a. 2022/2023 

Si realizzi una *web application* per la gestione di ordini relativi all’acquisto di prodotti. La *web application* si compone di **due viste**:

- Vista cliente;
- Vista operatore.

La **Vista cliente** deve essere caratterizzata da tre *tab*, ognuno dei quali permette ad un generico cliente di:

- Visualizzare gli ordini effettuati, mostrando per ogni ordine:
  - id dell'ordine;
  - stato dell'ordine;
  - lista dei prodotti ordinati;
- Aggiungere prodotti al carrello, permettendo di indicare:
  - nome/descrizione del prodotto da ordinare;
  - quantità di prodotto da ordinare;
- Visualizzare i prodotti presenti nel carrello e sottomettere l'ordine, mostrando per ogni prodotto sia il nome/descrizione che la quantità, prevedendo un pulsante per sottomettere l'ordine.

La vista inoltre deve prevedere una notifica per il cliente quando uno degli ordini sottomessi è stato confermato o respinto da un operatore.


La **Vista operatore** deve essere caratterizzata da due *tab*, ognuno dei quali permette ad un generico operatore di:

- Ottenere la lista degli ordini da elaborare, mostrandone id e stato, e permettendo di completare o rigettare un ordine attraverso degli appositi pulsanti;
- Ottenere la lista degli ordini completati, mostrando id e stato per ogni ordine.

La vista inoltre deve prevedere una notifica per l'operatore quando un nuovo ordine è stato sottomesso da un cliente.

Le due viste sono servite da **due differenti server NodeJS**, i quali operano utilizzando lo stesso datastore (utilizzare *MongoDB*) per la persistenza delle informazioni sugli ordini. 
Ogni **ordine** è caratterizzato delle seguenti informazioni:

- *id*: identificatore univoco dell'ordine, assegnato dal server che gestisce la vista cliente;
- *status*: rappresenta lo stato di un ordine, il quale può assumere i seguenti valori:
  - *CART*: ordine in fase di creazione, e non ancora sottomesso dal cliente;
  - *PENDING*: ordine sottomesso dal cliente, ma non ancora elaborato dall'operatore;
  - *COMPLETED*: ordine completato dall'operatore;
  - *REJECTED*: ordine respinto dall'operatore;
- *products*: array dei prodotti che compongono l'ordine, con ogni prodotto caratterizzato da:
  - *name*: nome/descrizione del prodotto ordinato;
  - *quantity*: quantità ordinata per quel prodotto.



