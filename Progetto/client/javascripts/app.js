var main = function (callObjects) {

    "use strict";
    console.log("SANITY CHECK");

    var radioOptions = [
        { label: 'Occupato\t', value: 1 },
        { label: 'Senza risposta\t', value: 2 },
        { label: 'Da richiamare\t', value: 3 },
        { label: 'Non interessato\t', value: 4 },
        { label: 'Appuntamento fissato\t', value: 5 }
      ];

    var calls = callObjects.map(function (call) {
          return "Numero: "+call.numero+"\nData: "+call.data+"\nOra: "+call.ora+"\nNote: "+call.note+"\nEsito: "+radioOptions[call.esito-1].label; //Stampo l'esito sotto forma di testo!
    });

    $(".tabs a span").toArray().forEach(function (element) {
        var $element = $(element);

        // create a click handler for this element
        $element.on("click", function () {
            var $content,
                $input,
                $button,
                i;

            $(".tabs a span").removeClass("active");
            $element.addClass("active");
            $("main .content").empty();

            if ($element.parent().is(":nth-child(1)")) {
                $content = $("<ul>");
                for (i = calls.length-1; i >= 0; i--) {
                    $content.append($("<li>").text(calls[i]));
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
                    $bottone = $("<button>").text(" Registra Esito "); // ho cambiato span con button
                
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
                    $.post("calls", newTelefonata, function(result) {
                        console.log(result);
                        callObjects = result; 

                        calls = callObjects.map(function (call){
                            return "Numero: "+call.numero+"\nData: "+call.data+"\nOra: "+call.ora+"\nNote: "+call.note+"\nEsito: "+radioOptions[call.esito-1].label;
                        });

                        $numero.val("");
                        $data.val("");
                        $ora.val("");
                        $note.val("");
                        $('input[name="status"]').prop('checked', false);
                        window.alert("Telefonata Registrata Con Successo!");
                    });
                    
                });
                
                $content = $("<div>").append($numeroLabel).append($numero).append($dataLabel)
                                     .append($data).append($oraLabel).append($ora).append($noteLabel).append($note).append($esitoLabel).append($form).append($bottone);
            }

            $("main .content").append($content);

            return false;
        });
    });

    $(".tabs a:first-child span").trigger("click");
};

$(document).ready(function () {
    $.getJSON("calls.json", function (callObjects) {
        main(callObjects);
    });
});
