document.addEventListener('DOMContentLoaded', function() {
    var phoneCallsList = document.getElementById('phoneCallsList');
    var addPhoneCallForm = document.getElementById('addPhoneCallForm');
  
    addPhoneCallForm.addEventListener('submit', function(event) {
      event.preventDefault();
  
      var date = document.getElementById('date').value;
      var time = document.getElementById('time').value;
      var notes = document.getElementById('notes').value;
      var outcome = document.getElementById('outcome').value;
  
      var phoneCall = {
        date: date,
        time: time,
        notes: notes,
        outcome: outcome
      };
  
      savePhoneCall(phoneCall);
      addPhoneCallToList(phoneCall);
      addPhoneCallForm.reset();
    });
  
    function savePhoneCall(phoneCall) {
      // Invia la telefonata al server per salvare i dati
      // Implementazione del salvataggio sul server omessa
    }
  
    function addPhoneCallToList(phoneCall) {
      var phoneCallItem = document.createElement('div');
      phoneCallItem.innerHTML = '<strong>Data:</strong> ' + phoneCall.date +
        '<br><strong>Ora:</strong> ' + phoneCall.time +
        '<br><strong>Note:</strong> ' + phoneCall.notes +
        '<br><strong>Esito:</strong> ' + getOutcomeLabel(phoneCall.outcome);
  
      phoneCallsList.appendChild(phoneCallItem);
    }
  
    function getOutcomeLabel(outcome) {
      var outcomeLabel = '';
  
      switch (outcome) {
        case '1':
          outcomeLabel = 'Scarsamente soddisfacente';
          break;
        case '2':
          outcomeLabel = 'Soddisfacente';
          break;
        case '3':
          outcomeLabel = 'Mediamente soddisfacente';
          break;
        case '4':
          outcomeLabel = 'Molto soddisfacente';
          break;
        case '5':
          outcomeLabel = 'Estremamente soddisfacente';
          break;
        default:
          outcomeLabel = 'N/A';
          break;
      }
  
      return outcomeLabel;
    }
  });
  