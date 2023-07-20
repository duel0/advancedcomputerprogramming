function telefonataValida(newTelefonata) { //VERIFICA VALIDITA' PRIMA DEL POST
  
    // Verifico se tutti i campi sono presenti e non sono vuoti
    if (
      newTelefonata.numero.trim() === "" ||
      newTelefonata.data.trim() === "" ||
      newTelefonata.ora.trim() === "" ||
      newTelefonata.note.trim() === "" ||
      newTelefonata.esito.trim() === ""
    ) {
      window.alert("C'è almeno un campo vuoto!")
      return false;
    }
  
    // Verifica se il numero è nel formato corretto "3XX-XXX-XXXX"
    var numeroPattern = /^3\d{2}-\d{3}-\d{4}$/;
    if (!numeroPattern.test(newTelefonata.numero)) {
      window.alert("Il numero inserito è invalido!")
      return false;
    }
  
    // Tutte le verifiche sono passate, la telefonata è valida
    return true;
  }
  

var main = function (callObjects) {

    "use strict";
    // Inizializzo Vettore con Opzioni Radio
    var radioOptions = [
        { label: 'Occupato\t', value: 1 },
        { label: 'Senza risposta\t', value: 2 },
        { label: 'Da richiamare\t', value: 3 },
        { label: 'Non interessato\t', value: 4 },
        { label: 'Appuntamento fissato\t', value: 5 }
      ];

    // Uso map per inizializzare l'array di telefonate a partire dai dati che mi fornisce MongoDB
    var calls = callObjects.map(function (call) {
          return "Numero: "+call.numero+"\nData: "+call.data+"\nOra: "+call.ora+"\nNote: "+call.note+"\nEsito: "+radioOptions[call.esito-1].label; //Stampo l'esito sotto forma di testo!
    });

    $(".tabs a span").toArray().forEach(function (element) {
        var $element = $(element);

        // Handler
        $element.on("click", function () {
            var $content,
                i;

            $(".tabs a span").removeClass("active");
            $element.addClass("active");
            $("main .content").empty();

            if ($element.parent().is(":nth-child(1)")) {
                $content = $("<ul>");
                for (i = calls.length-1; i >= 0; i--) {
                    $content.append($("<pre>").text(calls[i])); //Ordinamento dalla più recente, uso <pre> in modo che sia supportato il "\n"
                }

            }  else if ($element.parent().is(":nth-child(2)")) {

                var $numero = $("<input type='tel' id='telefono' name='telefono' placeholder='3XX-XXX-XXXX' pattern='[0-9]{3}-[0-9]{3}-[0-9]{4}' required>").addClass("numero"),
                    $numeroLabel = $("<p>").text("Numero: "),
                    $data = $("<input type='date'>").addClass("data"),
                    $dataLabel = $("<p>").text("Data: "),
                    $ora = $("<input type='time'>").addClass("ora"),
                    $oraLabel = $("<p>").text("Ora: "),
                    $note = $("<input>").addClass("note"),
                    $noteLabel = $("<p>").text("Note: "),
                    $form = $('<form></form>'),
                    $fieldset = $('<fieldset></fieldset>'),
                    $esitoLabel = $("<p>").text("Esito: "),
                    $bottone = $("<button>").text(" Registra Esito ").addClass("form_botton"); // ho cambiato span con button
                
                radioOptions.forEach(function(option){
                    var radioLabel = $("<label>").text(option.label);
                    var radioButton = $('<input type="radio" name="status" value="' + option.value + '">');
                    // Aggiunta degli elementi radio al fieldset
                    $fieldset.append(radioButton);
                    $fieldset.append(radioLabel);
                });
                
                $form.append($fieldset);   
                
                $bottone.on("click", function(){
                    
                    var numero = $numero.val(), data = $data.val(), ora=$ora.val(), note=$note.val(), esito=$("input[type='radio'][name='status']:checked").val();
                    var newTelefonata = {"numero":numero,"data":data,"ora":ora,"note":note,"esito":esito};

                    if (telefonataValida(newTelefonata)){
                        $.post("calls", newTelefonata, function(result) {
                            console.log(result);
                            callObjects = result; 
    
                            calls = callObjects.map(function (call){
                                // Stesso discorso: uso radioOptions[call.esito-1] per usare il testo invece di un numero
                                return "Numero: "+call.numero+"\nData: "+call.data+"\nOra: "+call.ora+"\nNote: "+call.note+"\nEsito: "+radioOptions[call.esito-1].label;
                            });
    
                            $numero.val("");
                            $data.val("");
                            $ora.val("");
                            $note.val("");
                            $('input[name="status"]').prop('checked', false);
                            if(numero==="333-123-1234"){
                                window.alert("Mi sono autotelefonato!")
                            } else {
                                window.alert("Telefonata Registrata Con Successo!");
                            }
                        });
                    }
                    
                });
                
                $content = $("<div>").append($numeroLabel).append($numero).append($dataLabel)
                                     .append($data).append($oraLabel).append($ora).append($noteLabel).append($note).append($esitoLabel).append($form).append($bottone);
            }

            $("main .content").append($content);

            return false;
        });
    });

    $(".tabs a:first-child span").trigger("click"); //Triggero il click in automatico
};

$(document).ready(function () {
    $.getJSON("calls.json", function (callObjects) {
        main(callObjects);
    });
});
