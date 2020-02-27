function setInputFilter(textbox, inputFilter) {
    $('button').attr("disabled", "disabled");
    //var solde = [[${soldeCompte}]];


    //$('button#go').css('background', 'green');

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
    //document.getElementById("error").innerText;

    var validate;
    if ($('#montant').val() === '' || $('#montant').val() === '.')
        validate = 0;
    else
        validate = parseFloat($('#montant').val());

    //console.log(document.theForm.action.value);

    if (validate === 0 || (solde < validate)) {
        $('button').attr("disabled", "disabled");
        if (solde < validate) {
            document.getElementById("error").style.display = 'inline-block';
            document.getElementById("error").innerHTML = 'Veuillez saisir un montant qui ne depasse pas votre solde: '
                + solde;
            // $('label#error').css('background', 'red');
        }

    } else if (validate != 0 && solde >= validate) {
        $('button').removeAttr("disabled");
        //document.getElementById("error").style.display = 'none';
    }
}
