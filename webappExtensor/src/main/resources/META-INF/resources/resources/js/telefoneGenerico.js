/**
 * Máscara de telefone brasileira inteligente
 * Basta adicionar a classe: campoResponsivoTelefone
 * Totalmente protegido contra dupla inicialização
 */

function autoSubmitTelefone(input) {

    if (!input || !input.id) {
        return;
    }
    const $inputMascara = $(input);

    const campoInstanciado = $inputMascara.attr('campoinstanciado');

    const $inputValor = $('input[campoinstanciado="' + campoInstanciado + '"].mask-telefone-br-valor');
    $inputValor.val(input.value);

    const idValor = $inputValor.attr('id');
    const idMaskara = $inputMascara.attr('id');
    const idatualizacao = $inputValor.closest('[id$="InputFormPadrao"]').attr('id');
    const areaExternaAtualizacao = $inputValor.attr('idOnchange');
    if (areaExternaAtualizacao) {
        PrimeFaces.ab({
            s: idMaskara,
            p: idValor,
            u: areaExternaAtualizacao,

            oncomplete: function () {
                console.log("Ajax terminou e DOM já foi atualizado");

                // aqui você pode reaplicar máscara, reposicionar cursor, etc

            }
        });
    }

    PrimeFaces.ab({
        s: idMaskara,
        p: idValor,
        u: idatualizacao,

        oncomplete: function () {
            console.log("Ajax terminou e DOM já foi atualizado");

            // aqui você pode reaplicar máscara, reposicionar cursor, etc

        }
    });
    PrimeFaces.ab({
        s: idMaskara,
        p: idValor,
        u: idatualizacao,
        oncomplete: function () {
            console.log("Ajax terminou e DOM já foi atualizado");

            // aqui você pode reaplicar máscara, reposicionar cursor, etc

        }
    });

}
function getInputTelefoneValorPorElementoMascara(pElemento) {
    const $inputMascara = $(pElemento);
    const campoInstanciado = $inputMascara.attr('campoinstanciado');
    const $inputValor = $('input[campoinstanciado="' + campoInstanciado + '"].mask-telefone-br-valor');
    return  $inputValor;
}


function definirMascaraTelefoneGenerico(pElemento, teclaRetorno) {

    const $inputMascara = $(pElemento);
    const campoInstanciado = $inputMascara.attr('campoinstanciado');
    const $inputValor = $('input[campoinstanciado="' + campoInstanciado + '"].mask-telefone-br-valor');
    let mask = $inputMascara[0].inputmask?.maskset?.mask || '+55 (99) 99999-9999';
    let valor = $inputMascara.val().trim();
    let apenasDigitos = valor.replace(/\D/g, '');



    // =============================================
    // INICIALIZAÇÃO ÚNICA
    // =============================================

    if (!$inputMascara.data('mask-init')) {

        if (!valor || valor === '+55 ') {
            $inputMascara.val('+55 ');
            apenasDigitos = '55';
        }

        $inputMascara.data('mask-init', true);

        if (!valor) {
            mask = '+55 (99) 99999-9999';
        }
    }



    // =============================================
    // DECIDE A MÁSCARA CORRETA
    // =============================================

    if (apenasDigitos.startsWith('55')) {

        const numeroLocal = apenasDigitos.substring(4);

        if (numeroLocal.length > 0) {

            mask = numeroLocal.startsWith('9')
                    ? '+55 (99) 99999-9999'
                    : '+55 (99) 9999-9999';

        } else {

            mask = '+55 (99) 99999-9999';
        }
    }

    console.log("A mascaraDefinida foi " + mask);



    if (mask.includes('+55') && apenasDigitos.length > 2) {

        const digitosApos55 = apenasDigitos.substring(2);

        if (digitosApos55.length >= 3) {

            if (!digitosApos55.startsWith('55')) {
                $inputMascara.val(digitosApos55);
            }

            if (digitosApos55 === '55') {
                $inputMascara.val('');
            }
        }
    }



    // =============================================
    // APLICA A MÁSCARA
    // =============================================
    const valorAtual = $inputMascara.val();

    if ($inputMascara.data('inputmask')) {
        $inputMascara.inputmask('remove');
    }

    $inputMascara.inputmask({
        mask: mask,
        placeholder: '_',
        greedy: false,
        clearMaskOnLostFocus: false,
        showMaskOnHover: false
    });



    // =============================================
    // ATUALIZA CAMPO DE VALOR RELACIONADO
    // =============================================

    if ($inputValor.length) {
        $inputValor.val($inputMascara.val());
    }



    // =============================================
    // CURSOR
    // =============================================

    setTimeout(() => {

        const texto = $inputMascara.val();

        if (texto.length > 0) {

            const pos = texto.indexOf('_');

            const novaPos = pos !== -1 ? pos : texto.length;
            if (teclaRetorno && novaPos > 0) {
                const charAnterior = texto.charAt(novaPos - 1);

                // se é traço, mover o cursor antes do traço

                if (charAnterior && !charAnterior.match(/[0-9_]/)) {

                    const textoSemUnderlines = texto.replace(/_/g, ''); // remove todos os "_"
                    //Espaço após o DD exemplo +55(31) <-espaço
                    if (charAnterior === ' ') {

                        $inputMascara.val(textoSemUnderlines.slice(0, -5));
                        $inputValor.val(textoSemUnderlines.slice(0, -5));
                    } else {

                        $inputMascara.val(textoSemUnderlines.slice(0, -2));
                        $inputValor.val(textoSemUnderlines.slice(0, -2));
                    }
                }
            }
            if ($inputMascara[0].setSelectionRange) {
                $inputMascara[0].setSelectionRange(novaPos, novaPos);
            }
        }

    }, 0);
}



function inicializarMascarasTelefone() {
    $(document).off('pfAjaxComplete.telefone');
    $(document).on('pfAjaxComplete.telefone', function () {

        $('.campoResponsivoTelefone').each(function () {
            const   valor = getInputTelefoneValorPorElementoMascara(this);
            $(this).val(valor.val());
        });

    });

    $(document)

            .on('keydown.masktel', '.campoResponsivoTelefone', function (e) {
                const backspaceOuDireita = e.key === "Backspace" || e.key === "ArrowRight";
                definirMascaraTelefoneGenerico(this, backspaceOuDireita);


                $(this).trigger('keyup');


                if (e.key === "Backspace" || e.keyCode === 8) {
                    const pos = this.inputmask.caretPos || this.selectionStart;
                    const val = this.inputmask.unmaskedvalue();

                    // Se estamos em cima de um separador, pula para o número anterior
                    if (this.value[pos] === '-' || this.value[pos] === ')' || this.value[pos] === '(' || this.value[pos] === ' ') {
                        e.preventDefault();
                        this.inputmask.caret(pos - 1);
                    }
                }


                if (e.key === '+' || (e.shiftKey && e.key === '=')) {

                    e.preventDefault();

                    const $inputMascara = $(this);

                    $inputMascara.val('+');

                    if ($inputMascara.data('inputmask')) {
                        $inputMascara.inputmask('remove');
                    }

                    $inputMascara.inputmask({
                        mask: '+{1,4} 99999999999999999',
                        placeholder: '_',
                        greedy: false,
                        clearMaskOnLostFocus: false
                    });
                    setTimeout(() => $inputMascara[0].setSelectionRange(1, 1), 0);
                    return;
                }



                const valor = $(this).val();

                if (e.key === 'Backspace' && (valor === '+55 ' || valor === '+55' || valor === '+')) {
                    e.preventDefault();
                    $(this).val('+');
                    setTimeout(() => this.setSelectionRange(1, 1), 0);
                }

            })



            .on('keyup.masktel input.masktel paste.masktel', '.campoResponsivoTelefone', function () {
                definirMascaraTelefoneGenerico(this, false);
            })



            .on('blur.masktel', '.campoResponsivoTelefone', function () {
                autoSubmitTelefone(this);
            });
}



$(function () {
    inicializarMascarasTelefone();
});