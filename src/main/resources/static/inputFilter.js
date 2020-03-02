function setInputFilter(textbox, inputFilter) {
    $('button').attr("disabled", "disabled");

    //var solde = [[${soldeCompte}]];
    solde = +document.getElementById("soldeDu").value;
    console.log(typeof solde + ": " + solde);
    document.getElementById("error").innerText += solde;

    ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function (event) {
        textbox.addEventListener(event, function () {

            if (inputFilter(this.value)) {
                this.oldValue = this.value;
                this.oldSelectionStart = this.selectionStart;
                this.oldSelectionEnd = this.selectionEnd;

                var validate;

                if (this.value === '' || this.value === '.') {
                    validate = 0;
                } else {
                    validate = parseFloat(this.value);
                    //console.log(typeof validate + ": " + validate)
                }

                if (validate === 0 || ((solde < validate) && (document.theForm.action.value === "Debit"))) {
                    $('button').attr("disabled", "disabled");
                    if (solde < validate) {
                        document.getElementById("error").style.display = 'inline-block';
                        // $('label#error').css('background', 'red');
                    }

                } else if ((validate != 0 && solde >= validate) || (document.theForm.action.value === "Credit")) {
                    $('button').removeAttr("disabled");
                    document.getElementById("error").style.display = 'none';
                }

            } else if (this.hasOwnProperty("oldValue")) {
                this.value = this.oldValue;
                this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
            } else {
                this.value = "";
            }
        });
    });

}