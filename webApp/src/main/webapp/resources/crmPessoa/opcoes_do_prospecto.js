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

        function inicializar() {
            posicionarBarraAcoes();
        }

        /* API publica */
        return {
            posicionarBarraAcoes: posicionarBarraAcoes,
            ajustarAba: ajustarAba,
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