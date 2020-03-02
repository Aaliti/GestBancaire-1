function setInputFilter(textbox, inputFilter) {
    $('button').attr("disabled", "disabled");

    ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function (event) {
        textbox.addEventListener(event, function () {

            if (inputFilter(this.value)) {
                this.oldValue = this.value;
                this.oldSelectionStart = this.selectionStart;
                this.oldSelectionEnd = this.selectionEnd;

                soldeValid();

            } else if (this.hasOwnProperty("oldValue")) {
                this.value = this.oldValue;
                this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
            } else {
                this.value = "";
            }
        });
    });

}

function soldeValid() {
    var solde = +document.getElementById("soldeDu").value;
    console.log(typeof solde + ": " + solde);
    var message = '';

    if ($('#soldeDu').val().length > 0 && $('#soldeAu').val().length > 0)
        message = 'Veuillez saisir un montant qui ne dépasse pas votre solde: ' + solde;
    else if ($('#soldeAu').val().length == 0)
        message = 'Veuillez saisir le bénéficiaire';
    else{
        message = 'Veuillez saisir le débiteur';
    }

    var validate;

    if ($('#montant').val() === '' || $('#montant').val() === '.') {
        validate = 0;
    } else {
        validate = parseFloat($('#montant').val());
    }

    if (validate === 0 || (solde < validate)) {
        $('button').attr("disabled", "disabled");
        document.getElementById("error").innerHTML = message;
        if (solde < validate || ($('#soldeAu').val().length == 0)) {
            document.getElementById("error").style.display = 'inline-block';
            //document.getElementById("error").innerHTML = message;
        }

    } else if (validate != 0 && solde >= validate) {
        if ($('#soldeAu').val().length > 0) {
            $('button').removeAttr("disabled");
            document.getElementById("error").style.display = 'none';

        } else {
            $('button').attr("disabled", "disabled");
            //document.getElementById("error").innerHTML = '';
        }
    }

}
