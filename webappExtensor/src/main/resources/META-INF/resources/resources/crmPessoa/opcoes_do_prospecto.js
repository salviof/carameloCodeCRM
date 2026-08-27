/* ============================================================
 * opcoes_do_prospecto.js
 * Comportamento da tela "Visao Geral do Prospecto".
 * Carregado via <h:outputScript library="crmPessoa" .../>
 *
 * NOTA sobre o namespace: o coreSuperBitsFrameWork.js declara
 * `var CarameloCode = { ... }` (atribuicao direta). Se o core
 * carregar DEPOIS deste arquivo, ele substituiria o objeto e
 * apagaria o .prospecto. Por isso o modulo se anexa duas vezes:
 * imediatamente E no DOMContentLoaded (quando todos os scripts
 * sincronos ja rodaram), garantindo sobrevivencia em qualquer
 * ordem de carga.
 * ============================================================ */
(function () {
    "use strict";

    var api = (function () {

        /**
         * Realoca a barra de acoes rapidas para dentro do tabView,
         * logo apos a linha de cabecalhos (.ui-tabs-nav).
         *   - Desktop: fica sobreposta a linha das abas (CSS absolute).
         *   - Mobile:  vira uma linha propria ABAIXO das abas.
         * Como o AJAX do JSF substitui elementos pelo id onde quer
         * que estejam no DOM, a realocacao sobrevive a updates.
         * A funcao e idempotente: pode ser chamada varias vezes.
         */
        function posicionarBarraAcoes() {
            var barra = document.querySelector('.DashboardVisaoGeralProspecto .BarraAcoesRapidasLead');
            var abas = document.querySelector('.DashboardVisaoGeralProspecto .ui-tabs');
            if (!barra || !abas) {
                return;
            }
            var nav = abas.querySelector('.ui-tabs-nav');
            if (nav && barra.parentElement !== abas) {
                nav.insertAdjacentElement('afterend', barra);
            }
        }

        /**
         * Sincroniza a aba ativa do widget com o indice vindo do bean.
         * O indice vem de EL (#{pgProspecto.abaSelecionada}), por isso
         * a CHAMADA permanece no XHTML (painel "atualizarAba") e so a
         * logica mora aqui.
         *
         * @param {number} indiceCorreto indice da aba desejada (0-based)
         * @param {string} [widgetVar]   widgetVar do tabView (padrao: abasLead)
         */
        function ajustarAba(indiceCorreto, widgetVar) {
            var widget = PF(widgetVar || 'abasLead');
            if (!widget) {
                return;
            }
            var atual = widget.getActiveIndex();
            if (atual !== indiceCorreto) {
                widget.select(indiceCorreto);
            }
        }

        /**
         * Feedback visual do "copiou": o gatilho fica verde e o icone vira
         * um check por um instante (ver .btnCopiarContatoDoLead--copiado no
         * opcoes_do_prospecto.css). Depois avisa o servidor, se pedido.
         *
         * @param {HTMLElement} gatilho elemento clicado
         */
        function sinalizarCopia(gatilho) {
            var classeCopiado = 'btnCopiarContatoDoLead--copiado';
            gatilho.classList.add(classeCopiado);
            window.setTimeout(function () {
                gatilho.classList.remove(classeCopiado);
            }, 1600);

            /* nome de um p:remoteCommand global (ex.: msgEmailCopiaOK), que
               exibe a mensagem de sucesso pelo growl da pagina */
            var retorno = gatilho.getAttribute('data-retorno-copiar');
            if (retorno && typeof window[retorno] === 'function') {
                window[retorno]();
            }
        }

        /** Copia por textarea temporaria: navegadores antigos e http. */
        function copiarPorTextarea(valor) {
            var area = document.createElement('textarea');
            area.value = valor;
            area.setAttribute('readonly', 'readonly');
            area.className = 'areaCopiaOculta';
            document.body.appendChild(area);
            area.select();
            var ok = false;
            try {
                ok = document.execCommand('copy');
            } catch (e) {
                ok = false;
            }
            document.body.removeChild(area);
            return ok;
        }

        /**
         * Copia para a area de transferencia o valor guardado no proprio
         * elemento clicado (data-valor-copiar).
         *
         * Os gatilhos de copiar da lista de contatos sao renderizados uma vez
         * POR LINHA pelo mesmo componente JSF, entao nao podem ser achados
         * por id (sairia duplicado no DOM e so o primeiro funcionaria) nem
         * ler o contato selecionado (copiariam sempre o mesmo valor). Dai a
         * funcao receber o proprio elemento e tirar dele o valor da linha.
         *
         * @param {HTMLElement} gatilho elemento clicado; traz data-valor-copiar
         *        e, opcionalmente, data-retorno-copiar
         * @param {Event} [evento] clique; a propagacao e interrompida para o
         *        item da listbox nao trocar a selecao e disparar ajax, o que
         *        re-renderiza a lista e mata o feedback visual
         * @returns {boolean} sempre false, para cancelar a navegacao do link
         */
        function copiarValorContato(gatilho, evento) {
            if (evento) {
                evento.stopPropagation();
                if (evento.preventDefault) {
                    evento.preventDefault();
                }
            }

            var valor = gatilho.getAttribute('data-valor-copiar');
            if (!valor) {
                return false;
            }

            var aoCopiar = function () {
                sinalizarCopia(gatilho);
            };
            var alternativa = function () {
                if (copiarPorTextarea(valor)) {
                    aoCopiar();
                }
            };

            /* navigator.clipboard so existe em contexto seguro (https ou
               localhost); fora dele cai na textarea */
            if (navigator.clipboard && window.isSecureContext) {
                navigator.clipboard.writeText(valor).then(aoCopiar, alternativa);
            } else {
                alternativa();
            }
            return false;
        }

        function inicializar() {
            posicionarBarraAcoes();
        }

        /* API publica */
        return {
            posicionarBarraAcoes: posicionarBarraAcoes,
            ajustarAba: ajustarAba,
            copiarValorContato: copiarValorContato,
            inicializar: inicializar
        };
    })();

    /** Pendura o modulo no namespace global sem destruir o que existir. */
    function registrarNamespace() {
        window.CarameloCode = window.CarameloCode || {};
        window.CarameloCode.prospecto = api;
    }

    /* 1) anexa agora (caso o core ja tenha carregado, ou nem exista) */
    registrarNamespace();

    /* 2) re-anexa e inicializa quando o DOM estiver pronto — nesse
     momento todos os scripts sincronos (inclusive o core, se veio
     depois) ja executaram, entao esta anexacao e a definitiva */
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function () {
            registrarNamespace();
            api.inicializar();
        });
    } else {
        registrarNamespace();
        api.inicializar();
    }
})();