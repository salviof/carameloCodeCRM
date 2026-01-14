// Função para inicializar o editor Quill
function initializeEmailEditor() {
    // Seleciona o elemento com o ID 'crm-mail-editor'
    var editorContainer = document.getElementById('crm-mail-editor');

    // Verifica se o contêiner existe na página
    if (!editorContainer) {
        console.error("Elemento com ID 'crm-mail-editor' não encontrado.");
        return;
    }

    // Inicializa o Quill no contêiner
    var quill = new Quill('#crm-mail-editor', {
        theme: 'snow',
        placeholder: 'Digite seu e-mail aqui...',
        modules: {
            toolbar: [
                [{header: [1, 2, false]}], // Cabeçalhos
                ['bold', 'italic', 'underline'], // Estilos de texto
                ['link', 'image'], // Links e imagens
                [{list: 'ordered'}, {list: 'bullet'}], // Listas
                ['clean'], // Limpar formatação
            ],
        },
    });
    quill.root.innerHTML = document.querySelectorAll('[name$=":textoEmail"]')[0].value;
    // Captura o evento 'text-change' (digitação ou alteração no editor)
    quill.on('text-change', function (delta, oldDelta, source) {
        // Conteúdo atual em texto simples
        const plainText = quill.getText();
        console.log('Texto atual:', plainText);
        //document.querySelectorAll('[name$=":textoEmail"]')[0].value=plainText;
        // Conteúdo atual com formatação HTML
        const htmlContent = quill.root.innerHTML;
        document.querySelectorAll('[name$=":textoEmail"]')[0].value = htmlContent;
        console.log('Conteúdo HTML atual:', htmlContent);
    });

    // Captura o evento 'blur' (quando o editor perde o foco)
    quill.on('selection-change', function (range, oldRange, source) {
        if (!range) {
            executarAutosave();
        }
    });

    console.log("Editor de e-mail inicializado com sucesso!");
}

// Chama a função para inicializar o editor ao carregar o script
document.addEventListener('DOMContentLoaded', initializeEmailEditor);