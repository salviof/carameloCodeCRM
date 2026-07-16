/* ═══════════════════ CARAMELO STORE ═══════════════════
 Componente : seletorAcaoRadial (JS)
 Categoria  : seletorAcaoLinkSimples
 Git        : https://github.com/carameloCode/caramelo-store
 Versão     : 1.3.0
 Requer     : carameloStore.js (core >= 2.0) carregado antes

 Eventos delegados no document: sobrevive a updates ajax
 do PrimeFaces sem re-inicialização. Posições calculadas
 na abertura, com colisão de viewport.

 1.3: adequado ao contrato CarameloStore.componentes
 (nome, descricao, gitUrl, instancias).
 1.2: registrado via core; ciclo de vida (load / pós-ajax)
 passou a ser responsabilidade do core.
 1.1: teclado/ARIA, fechamento limpo, modo lista automático,
 bottom sheet mobile, memorização da última ação,
 CustomEvents crc-sar:abriu / fechou / selecionou.
 ═══════════════════════════════════════════════════════ */

(function () {
    "use strict";

    if (!window.CarameloStore || !window.CarameloStore.registrarComponente) {
        if (window.console) {
            console.error("[CarameloStore] seletorAcaoRadial requer o core carameloStore.js " +
                    "carregado antes. Verifique o h:outputScript no composite component.");
        }
        return;
    }

    // Já registrado (script reincluído via ajax): não anexa listeners de novo
    if (window.CarameloStore.componentes["seletorAcaoRadial"]) {
        return;
    }

    var HOVER_INTENT_MS = 120;   // espera antes de abrir no hover
    var GRACA_FECHAR_MS = 350;   // tolerância para cruzar o vão até as pílulas
    var VIEWPORT_SHEET = 480;    // largura máxima (px) para virar bottom sheet

    var podeHover = window.matchMedia && window.matchMedia("(hover:hover)").matches;
    var timerAbrir = null;
    var timerFechar = null;

    /* ── Helpers ────────────────────────────────────────── */

    function getComp(el) {
        return (el && el.closest) ? el.closest(".crc-sar") : null;
    }
    function mainDe(c) {
        return c.querySelector(".crc-sar-main");
    }
    function wrapDe(c) {
        return c.querySelector(".crc-sar-opcoes");
    }
    function optsDe(c) {
        return Array.prototype.slice.call(c.querySelectorAll(".crc-sar-opt"));
    }
    function estaAberto(c) {
        return c.classList.contains("crc-sar-aberto");
    }

    function emitir(c, nome, detalhe) {
        try {
            c.dispatchEvent(new CustomEvent(nome, {bubbles: true, detail: detalhe || {}}));
        } catch (e) { /* navegador sem CustomEvent: telemetria é opcional */
        }
    }

    /* ── Posicionamento ─────────────────────────────────── */

    function posicionar(c) {
        var opts = optsDe(c);
        var n = opts.length;
        if (n === 0) {
            return;
        }

        c.classList.remove("crc-sar-sheet", "crc-sar-lista");

        // Viewport estreita: bottom sheet (layout fica por conta do CSS)
        if (window.innerWidth <= VIEWPORT_SHEET) {
            c.classList.add("crc-sar-sheet");
            opts.forEach(function (o, k) {
                o.style.removeProperty("--crc-x");
                o.style.removeProperty("--crc-y");
                o.style.transitionDelay = (k * 25) + "ms";
            });
            return;
        }

        var limite = parseInt(c.getAttribute("data-crc-limite") || "5", 10);
        var modo = c.getAttribute("data-crc-modo") || "arco";
        if (n > limite) {
            modo = "lista"; // degrada com elegância: muitas opções viram pilha vertical
            c.classList.add("crc-sar-lista");
        }

        var raio = parseInt(c.getAttribute("data-crc-raio") || "90", 10);
        var rect = c.getBoundingClientRect();
        var vw = window.innerWidth;
        var vh = window.innerHeight;

        // Ângulo central do leque, com flip/inclinação nas bordas
        var centro = -90;
        if (rect.top < raio + 70) {
            centro = 90;
        }
        if (rect.left < raio + 60) {
            centro += (centro === -90) ? 45 : -45;
        } else if (vw - rect.right < raio + 60) {
            centro += (centro === -90) ? -45 : 45;
        }

        for (var k = 0; k < n; k++) {
            var x = 0, y = 0, a;

            if (modo === "lista") {
                var passo = 52;
                var base = rect.height / 2 + 32;
                var alturaTotal = base + (n - 1) * passo + 30;
                var cabeEmbaixo = (vh - rect.bottom) > alturaTotal;
                var cabeEmCima = rect.top > alturaTotal;
                var sinal = (cabeEmbaixo || !cabeEmCima) ? 1 : -1;
                x = 0;
                y = sinal * (base + k * passo);
            } else if (modo === "radial") {
                a = (-90 + (k * 360 / n)) * Math.PI / 180;
                x = Math.round(Math.cos(a) * raio);
                y = Math.round(Math.sin(a) * raio);
            } else { // arco
                var abertura = Math.min(170, 55 * (n - 1));
                var inicio = centro - abertura / 2;
                a = (inicio + (n > 1 ? (k * abertura / (n - 1)) : 0)) * Math.PI / 180;
                x = Math.round(Math.cos(a) * raio);
                y = Math.round(Math.sin(a) * raio);
            }

            opts[k].style.setProperty("--crc-x", x + "px");
            opts[k].style.setProperty("--crc-y", y + "px");
            opts[k].style.transitionDelay = (k * 30) + "ms";
        }
    }

    /* ── Abrir / fechar ─────────────────────────────────── */

    function abrir(c) {
        if (estaAberto(c)) {
            return;
        }
        posicionar(c);
        c.classList.add("crc-sar-aberto");
        var m = mainDe(c);
        if (m) {
            m.setAttribute("aria-expanded", "true");
        }
        var w = wrapDe(c);
        if (w) {
            w.setAttribute("aria-hidden", "false");
        }
        emitir(c, "crc-sar:abriu", {quantidade: optsDe(c).length});
    }

    function fechar(c, devolverFoco) {
        if (!estaAberto(c)) {
            return;
        }
        // Fechamento instantâneo e simultâneo: zera o stagger da abertura
        optsDe(c).forEach(function (o) {
            o.style.transitionDelay = "0ms";
            o.classList.remove("crc-sar-some", "crc-sar-escolhida");
        });
        c.classList.remove("crc-sar-aberto");
        var m = mainDe(c);
        if (m) {
            m.setAttribute("aria-expanded", "false");
            if (devolverFoco) {
                m.focus();
            }
        }
        var w = wrapDe(c);
        if (w) {
            w.setAttribute("aria-hidden", "true");
        }
        emitir(c, "crc-sar:fechou");
    }

    function fecharTodos() {
        var abertos = document.querySelectorAll(".crc-sar.crc-sar-aberto");
        for (var i = 0; i < abertos.length; i++) {
            fechar(abertos[i], false);
        }
    }

    /* ── Memorização da última ação (opt-in) ────────────── */

    function chaveStorage(c) {
        return "crcSar:" + window.location.pathname + ":" + (c.id || "");
    }

    // Promove uma opção a botão principal (troca de papéis no DOM;
    // os handlers inline do PrimeFaces sobrevivem à mudança de posição)
    function promover(c, alvo) {
        var m = mainDe(c);
        var w = wrapDe(c);
        if (!m || !alvo || !w || m === alvo) {
            return;
        }

        ["aria-haspopup", "aria-expanded", "aria-controls"].forEach(function (attr) {
            var v = m.getAttribute(attr);
            if (v !== null) {
                alvo.setAttribute(attr, v);
            }
            m.removeAttribute(attr);
        });

        alvo.classList.remove("crc-sar-opt");
        alvo.classList.add("crc-sar-main");
        alvo.removeAttribute("tabindex");
        alvo.removeAttribute("role");
        alvo.style.removeProperty("--crc-x");
        alvo.style.removeProperty("--crc-y");
        alvo.style.transitionDelay = "";

        m.classList.remove("crc-sar-main");
        m.classList.add("crc-sar-opt");
        m.setAttribute("tabindex", "-1");
        m.setAttribute("role", "menuitem");

        c.insertBefore(alvo, m); // alvo assume o lugar do principal no fluxo
        w.appendChild(m);        // antigo principal vai para o leque
    }

    function aplicarMemorizacao(c) {
        if (c.getAttribute("data-crc-memorizar") !== "true") {
            return;
        }
        var salvo;
        try {
            salvo = window.localStorage.getItem(chaveStorage(c));
        } catch (e) {
            return;
        }
        if (!salvo) {
            return;
        }
        var m = mainDe(c);
        if (!m || m.getAttribute("data-crc-acao") === salvo) {
            return;
        }
        var opts = optsDe(c);
        for (var i = 0; i < opts.length; i++) {
            if (opts[i].getAttribute("data-crc-acao") === salvo) {
                promover(c, opts[i]);
                return;
            }
        }
    }

    // Init idempotente: chamado pelo core no load e após cada ajax
    function init() {
        var comps = document.querySelectorAll(".crc-sar:not([data-crc-init])");
        for (var i = 0; i < comps.length; i++) {
            comps[i].setAttribute("data-crc-init", "1");
            aplicarMemorizacao(comps[i]);
        }
    }

    /* ── Seleção de uma opção ───────────────────────────── */

    function registrarEscolha(c, opt) {
        var acao = opt.getAttribute("data-crc-acao");
        emitir(c, "crc-sar:selecionou", {acao: acao, origem: "leque"});

        if (c.getAttribute("data-crc-memorizar") === "true") {
            try {
                window.localStorage.setItem(chaveStorage(c), acao);
            } catch (e) {
            }
        }

        // Feedback imediato: irmãs somem, escolhida ganha o anel
        optsDe(c).forEach(function (o) {
            o.style.transitionDelay = "0ms";
            if (o !== opt) {
                o.classList.add("crc-sar-some");
            }
        });
        opt.classList.add("crc-sar-escolhida");
        window.setTimeout(function () {
            fechar(c, false);
        }, 220);
    }

    /* ── Hover com intenção (desktop) ───────────────────── */

    document.addEventListener("mouseover", function (e) {
        if (!podeHover) {
            return;
        }
        var c = getComp(e.target);

        if (c) {
            clearTimeout(timerFechar);
            if (e.target.closest(".crc-sar-main")) {
                clearTimeout(timerAbrir);
                timerAbrir = setTimeout(function () {
                    abrir(c);
                }, HOVER_INTENT_MS);
            }
        } else {
            clearTimeout(timerAbrir);
            clearTimeout(timerFechar);
            timerFechar = setTimeout(fecharTodos, GRACA_FECHAR_MS);
        }
    });

    /* ── Clique / toque (fase de captura) ───────────────── */
    // Mobile: primeiro toque no principal só abre (bloqueia o
    // submit do PrimeFaces); o segundo executa a ação padrão.

    document.addEventListener("click", function (e) {
        var m = e.target.closest(".crc-sar-main");
        if (m) {
            var c = getComp(m);
            if (!podeHover && !estaAberto(c)) {
                e.preventDefault();
                e.stopImmediatePropagation();
                abrir(c);
                return;
            }
            emitir(c, "crc-sar:selecionou", {
                acao: m.getAttribute("data-crc-acao"),
                origem: "principal"
            });
            return;
        }

        var opt = e.target.closest(".crc-sar-opt");
        if (opt) {
            registrarEscolha(getComp(opt), opt);
            return; // deixa o p:commandButton disparar o ajax normalmente
        }

        // Clique no backdrop do bottom sheet (o ::before pertence ao próprio .crc-sar)
        if (e.target.classList && e.target.classList.contains("crc-sar")) {
            fecharTodos();
            return;
        }

        if (!e.target.closest(".crc-sar")) {
            fecharTodos();
        }
    }, true);

    /* ── Teclado (padrão ARIA menu button) ──────────────── */
    // Enter/Space no principal: executa a ação padrão (nativo).
    // Seta ↓/↑ no principal: abre e foca a primeira/última opção.
    // Setas circulam, Home/End saltam, Tab fecha, Esc fecha e devolve o foco.

    document.addEventListener("keydown", function (e) {
        if (e.key === "Escape" || e.key === "Esc") {
            var compFoco = getComp(document.activeElement);
            var abertos = document.querySelectorAll(".crc-sar.crc-sar-aberto");
            for (var i = 0; i < abertos.length; i++) {
                fechar(abertos[i], abertos[i] === compFoco);
            }
            return;
        }

        var el = document.activeElement;
        var c = getComp(el);
        if (!c) {
            return;
        }

        if (el.classList.contains("crc-sar-main")) {
            if (e.key === "ArrowDown" || e.key === "ArrowUp") {
                e.preventDefault();
                abrir(c);
                var opts = optsDe(c);
                var alvo = (e.key === "ArrowDown") ? opts[0] : opts[opts.length - 1];
                if (alvo) {
                    alvo.focus();
                }
            }
            return;
        }

        if (estaAberto(c) && el.classList.contains("crc-sar-opt")) {
            var lista = optsDe(c);
            var idx = lista.indexOf(el);
            if (e.key === "ArrowDown" || e.key === "ArrowRight") {
                e.preventDefault();
                lista[(idx + 1) % lista.length].focus();
            } else if (e.key === "ArrowUp" || e.key === "ArrowLeft") {
                e.preventDefault();
                lista[(idx - 1 + lista.length) % lista.length].focus();
            } else if (e.key === "Home") {
                e.preventDefault();
                lista[0].focus();
            } else if (e.key === "End") {
                e.preventDefault();
                lista[lista.length - 1].focus();
            } else if (e.key === "Tab") {
                fechar(c, false); // Tab sai do menu, como manda o padrão
            }
        }
    });

    /* ── Viewport ───────────────────────────────────────── */

    window.addEventListener("resize", fecharTodos);
    window.addEventListener("scroll", fecharTodos, true);

    /* ── Registro no core ───────────────────────────────── */
    // O core chama init() no DOMContentLoaded e após cada ajax.
    // Catálogo: CarameloStore.componentes.seletorAcaoRadial
    //   .nome / .descricao / .gitUrl / .instancias / .api

    window.CarameloStore.registrarComponente({
        nome: "seletorAcaoRadial",
        descricao: "Botão de ação padrão que expande as demais ações em leque, " +
                "radial ou lista vertical, com bottom sheet em telas estreitas. " +
                "Teclado/ARIA, badge de contagem e memorização opcional da última ação.",
        gitUrl: "https://github.com/carameloCode/caramelo-store",
        seletor: '[data-crc-componente="seletorAcaoRadial"]',
        init: init,
        api: {
            init: init,
            abrir: abrir,
            fechar: fechar,
            fecharTodos: fecharTodos
        }
    });
})();