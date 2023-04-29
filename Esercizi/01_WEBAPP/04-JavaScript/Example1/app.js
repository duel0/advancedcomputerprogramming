var main = function () {
    "use strict"; //With strict mode, you can not, for example, use undeclared variables.

    window.alert("hello world!");
};

// - Attenzione!!! $(document).ready(main) attende che il DOM sia caricato
// Può essere usato $(window).on("load", main); in modo da attendere il caricamento
// anche di elementi come immagini etc.
// - Spesso $() viene utilizzato per $( document ).ready()


$(document).ready(main); //$().ready(main); è equivalente
// $().ready(main);