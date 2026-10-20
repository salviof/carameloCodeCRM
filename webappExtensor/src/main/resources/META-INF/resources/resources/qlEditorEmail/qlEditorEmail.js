/* ==========================================================================
 Editor de e-mail (Quill) do componente crmEmail/inputEmail.

 O Quill vem do PrimeFaces (texteditor.js, importado pelo componente) e e
 criado sobre .crm-mail-editor. O HTML digitado e espelhado no textarea
 oculto .crm-mail-texto, que e o campo enviado ao servidor. Ao sair do
 editor (blur, inclusive clicando direto no X do modal) ou apertar ESC, o
 texto vai ao bean pelo p:remoteCommand executarAutosave do componente.

 Sem script inline no componente: a inicializacao roda na carga da pagina,
 quando este arquivo chega num update ajax e apos cada update ajax
 (pfAjaxComplete). Cada editor criado e marcado, entao as chamadas repetidas
 so criam os novos (componente re-renderizado, ou vindo por ajax dentro de
 um modal).

 Marcacao:
 .crc-comp-inputEmail   raiz de cada instancia do componente
 .crm-mail-editor       div onde o Quill e criado
 .crm-mail-texto        textarea oculto com o valor do e-mail
 ========================================================================== */

(function () {
    if (window.QlEditorEmailIniciado) {
        return;
    }
    window.QlEditorEmailIniciado = true;

    var SEL_RAIZ = '.crc-comp-inputEmail';
    var SEL_EDITOR = '.crm-mail-editor';
    var SEL_TEXTO = '.crm-mail-texto';
    var INICIADO = 'data-editor-email-iniciado';

    /* Textarea da mesma instancia, buscado a cada uso: um update ajax pode
     trocar o textarea sem recriar o editor. */
    function campoTexto(editor) {
        var raiz = editor.closest(SEL_RAIZ);
        return raiz ? raiz.querySelector(SEL_TEXTO) : null;
    }

    function criarEditor(editor) {
        var campo = campoTexto(editor);
        if (!campo) {
            return;
        }
        editor.setAttribute(INICIADO, '');

        var quill = new Quill(editor, {
            theme: 'snow',
            placeholder: 'Digite seu e-mail aqui...',
            modules: {
                toolbar: [
                    [{header: [1, 2, false]}], // Cabeçalhos
                    ['bold', 'italic', 'underline'], // Estilos de texto
                    ['link', 'image'], // Links e imagens
                    [{list: 'ordered'}, {list: 'bullet'}], // Listas
                    ['clean'] // Limpar formatação
                ]
            }
        });
        quill.root.innerHTML = campo.value || '';

        var barra = quill.getModule('toolbar');
        var ultimoEnviado = campo.value;

        // Espelha o HTML do editor no textarea da mesma instância
        function sincronizar() {
            var campoAtual = campoTexto(editor);
            if (campoAtual) {
                campoAtual.value = quill.root.innerHTML;
            }
            return campoAtual;
        }

        /* Envia o texto ao bean pelo p:remoteCommand executarAutosave, so se
         mudou desde o ultimo envio (blur e ESC podem chegar em sequencia). */
        function salvarRascunho() {
            var campoAtual = sincronizar();
            if (!campoAtual || campoAtual.value === ultimoEnviado
                    || typeof window.executarAutosave !== 'function') {
                return;
            }
            ultimoEnviado = campoAtual.value;
            window.executarAutosave();
        }

        // Digitação ou alteração no editor: espelha o HTML no textarea
        quill.on('text-change', sincronizar);

        /* Saiu do editor: salva o rascunho. E o blur do editor, e nao o
         selection-change do Quill: clicar num link ou botao (ex.: o X do
         modal) tira o foco sem mover a selecao, e o selection-change nao
         dispara. O blur vem no mousedown, antes do clique fechar o modal,
         entao o autosave entra na fila ajax do PrimeFaces antes do "close".
         Foco indo para a barra ou para o tooltip de link do proprio editor
         nao conta como saida. */
        quill.root.addEventListener('blur', function (e) {
            var destino = e.relatedTarget;
            if (destino && (editor.contains(destino)
                    || (barra && barra.container && barra.container.contains(destino)))) {
                return;
            }
            salvarRascunho();
        });

        /* ESC fecha o modal sem tirar o foco do editor. Este ouvinte roda
         antes do keydown que o PrimeFaces escuta no document para fechar. */
        quill.root.addEventListener('keydown', function (e) {
            if (e.key === 'Escape') {
                salvarRascunho();
            }
        });
    }

    /* Pode ser chamada quantas vezes for preciso: ignora os editores ja
     criados e so cria os que ainda nao foram marcados. */
    function iniciarNovos() {
        var novos = document.querySelectorAll(SEL_RAIZ + ' ' + SEL_EDITOR + ':not([' + INICIADO + '])');
        if (!novos.length) {
            return;
        }
        if (typeof window.Quill === 'undefined') {
            console.warn('qlEditorEmail: Quill (texteditor.js do PrimeFaces) nao carregado; editor nao iniciado.');
            return;
        }
        for (var i = 0; i < novos.length; i++) {
            try {
                criarEditor(novos[i]);
            } catch (erro) {
                console.error('qlEditorEmail: falha ao criar o editor.', erro);
            }
        }
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', iniciarNovos);
    } else {
        iniciarNovos();
    }
    if (window.jQuery) {
        jQuery(document).on('pfAjaxComplete', iniciarNovos);
    }

    /* Requisicoes com global="false" nao disparam pfAjaxComplete: se uma
     delas re-renderizar o componente, chamar esta funcao no oncomplete. */
    window.qlEditorEmailIniciarNovos = iniciarNovos;
})();
