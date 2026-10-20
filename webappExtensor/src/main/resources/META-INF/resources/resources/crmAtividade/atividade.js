/* ==========================================================================
 Navegacao do menu de relacionamentos (atividadesDisponiveisDesktop).

 Comportamento: ao passar o mouse numa linha do menu, os botoes do
 relacionamento atual desaparecem e aparecem os botoes correspondentes
 aquela linha. Nada e executado nem persistido: e navegacao pura.

 - hover numa linha        -> previa daquele relacionamento (120ms)
 - clique numa linha       -> fixa a selecao
 - mouse saindo do bloco   -> volta para o relacionamento fixado (200ms)
 - foco de teclado         -> vale como hover
 - setas / Home / End      -> circulam pelas linhas e ja trocam o conteudo

 A marcacao no HTML e toda por atributos de dado, para o script sobreviver
 a qualquer update ajax:

 .AtividadesDisponiveisDesktop   bloco (raiz de cada instancia)
 [data-atvdisp-menu]             coluna do menu
 .AtvDispRelLinha                linha de relacionamento
 data-atvdisp-alvo="ID"        o que ela mostra ('atual' ou id da atividade)
 .AtvDispPainel                  painel de conteudo
 data-atvdisp-painel="ID"      casa com o data-atvdisp-alvo da linha

 Estado visivel = classes .AtvDispRelLinha--ativa / .AtvDispPainel--ativo.
 O fixado fica em data-atvdisp-fixado no bloco; como e atributo do DOM, ele
 se perde na re-renderizacao e a selecao volta a ser a do servidor (a linha
 marcada como .AtvDispRelLinha--atual), que e exatamente o desejado.
 ========================================================================== */

(function () {
    if (window.AtvDispDesktopIniciado) {
        return;
    }
    window.AtvDispDesktopIniciado = true;

    var ESPERA_PREVIA = 120;   // antiflicker ao entrar na previa
    var ESPERA_VOLTA = 200;    // antiflicker ao voltar para o fixado
    var SEL_BLOCO = '.AtividadesDisponiveisDesktop';

    function maisProximo(el, seletor) {
        if (!el) {
            return null;
        }
        if (el.closest) {
            return el.closest(seletor);
        }
        // fallback para navegador sem Element.closest
        var no = el;
        while (no && no.nodeType === 1) {
            if (no.matches ? no.matches(seletor) : false) {
                return no;
            }
            no = no.parentNode;
        }
        return null;
    }

    function bloco(el) {
        return maisProximo(el, SEL_BLOCO);
    }
    function linhaDe(el) {
        return maisProximo(el, '.AtvDispRelLinha');
    }
    function dentroDoMenu(el) {
        return maisProximo(el, '[data-atvdisp-menu]');
    }
    /* Gatilhos de previa: as linhas do menu e qualquer elemento marcado com
     data-atvdisp-previa -- hoje o cabecalho, que traz de volta o estagio
     atual. So o hover; fixar a selecao continua sendo so das linhas. */
    function gatilhoDe(el) {
        return maisProximo(el, '.AtvDispRelLinha, [data-atvdisp-previa]');
    }
    function alvoDe(gatilho) {
        return gatilho.getAttribute('data-atvdisp-alvo')
                || gatilho.getAttribute('data-atvdisp-previa');
    }
    function linhas(bl) {
        return Array.prototype.slice.call(bl.querySelectorAll('.AtvDispRelLinha'));
    }
    function painel(bl, alvo) {
        return bl.querySelector('.AtvDispPainel[data-atvdisp-painel="' + alvo + '"]');
    }
    function fixado(bl) {
        if (bl.getAttribute('data-atvdisp-fixado')) {
            return bl.getAttribute('data-atvdisp-fixado');
        }
        var atual = bl.querySelector('.AtvDispRelLinha--atual');
        return atual ? atual.getAttribute('data-atvdisp-alvo') : 'atual';
    }
    function limparEsperas(bl) {
        if (bl._atvDispPrevia) {
            window.clearTimeout(bl._atvDispPrevia);
            bl._atvDispPrevia = null;
        }
        if (bl._atvDispVolta) {
            window.clearTimeout(bl._atvDispVolta);
            bl._atvDispVolta = null;
        }
    }

    /* Troca o painel visivel. Se nao existir painel para o alvo, nao faz nada:
     melhor manter o que esta na tela do que esvaziar a area de conteudo.
     ARIA e ordem de tabulacao acompanham: so a linha exibida fica tabulavel. */
    function mostrar(bl, alvo) {
        if (!alvo || !painel(bl, alvo)) {
            return;
        }

        linhas(bl).forEach(function (linha) {
            var ativa = linha.getAttribute('data-atvdisp-alvo') === alvo;
            linha.classList.toggle('AtvDispRelLinha--ativa', ativa);
            linha.setAttribute('aria-selected', ativa ? 'true' : 'false');
            linha.setAttribute('tabindex', ativa ? '0' : '-1');
        });

        Array.prototype.forEach.call(bl.querySelectorAll('.AtvDispPainel'), function (pn) {
            pn.classList.toggle('AtvDispPainel--ativo',
                    pn.getAttribute('data-atvdisp-painel') === alvo);
        });
    }

    function agendarPrevia(bl, alvo) {
        limparEsperas(bl);
        bl._atvDispPrevia = window.setTimeout(function () {
            bl._atvDispPrevia = null;
            mostrar(bl, alvo);
        }, ESPERA_PREVIA);
    }
    function agendarVolta(bl) {
        limparEsperas(bl);
        bl._atvDispVolta = window.setTimeout(function () {
            bl._atvDispVolta = null;
            mostrar(bl, fixado(bl));
        }, ESPERA_VOLTA);
    }
    function fixar(bl, alvo) {
        limparEsperas(bl);
        bl.setAttribute('data-atvdisp-fixado', alvo);
        mostrar(bl, alvo);
    }

    /* ----- hover ----- */
    document.addEventListener('mouseover', function (e) {
        var gatilho = gatilhoDe(e.target);
        if (!gatilho) {
            return;
        }
        var bl = bloco(gatilho);
        if (bl) {
            agendarPrevia(bl, alvoDe(gatilho));
        }
    });

    /* Sair de um gatilho volta para o fixado, com uma excecao deliberada: se o
     ponteiro seguiu para a area de conteudo, a previa e mantida, senao o
     painel desapareceria justamente no caminho ate o botao que o usuario
     quer clicar. */
    document.addEventListener('mouseout', function (e) {
        var bl = bloco(e.target);
        if (!bl) {
            return;
        }

        var destino = e.relatedTarget;
        if (!destino || !bl.contains(destino)) {
            agendarVolta(bl);
            return;
        }

        // segue num gatilho: o proprio mouseover resolve o que exibir
        if (dentroDoMenu(destino) || gatilhoDe(destino)) {
            return;
        }
        // entrou na area de conteudo: mantem o que esta na tela
        if (maisProximo(destino, '.AtvDispConteudo')) {
            limparEsperas(bl);
            return;
        }

        agendarVolta(bl);
    });

    /* ----- clique: fixa a selecao ----- */
    document.addEventListener('click', function (e) {
        var linha = linhaDe(e.target);
        if (!linha) {
            return;
        }
        var bl = bloco(linha);
        if (!bl) {
            return;
        }
        e.preventDefault();
        fixar(bl, linha.getAttribute('data-atvdisp-alvo'));
    });

    /* ----- teclado: foco vale como hover ----- */
    document.addEventListener('focusin', function (e) {
        var linha = linhaDe(e.target);
        if (linha) {
            var bl = bloco(linha);
            if (bl) {
                limparEsperas(bl);
                mostrar(bl, linha.getAttribute('data-atvdisp-alvo'));
            }
            return;
        }
        // Tab saindo da linha para os botoes da direita mantem a previa;
        // foco fora do bloco e que volta para o relacionamento fixado.
        var blAtivo = bloco(e.target);
        if (blAtivo) {
            limparEsperas(blAtivo);
        }
        Array.prototype.forEach.call(document.querySelectorAll(SEL_BLOCO), function (outro) {
            if (outro !== blAtivo) {
                agendarVolta(outro);
            }
        });
    });

    document.addEventListener('keydown', function (e) {
        var linha = linhaDe(e.target);
        if (!linha) {
            return;
        }
        var bl = bloco(linha);
        if (!bl) {
            return;
        }

        var lista = linhas(bl);
        var pos = lista.indexOf(linha);
        var destino = -1;

        if (e.key === 'ArrowDown' || e.key === 'Down') {
            destino = (pos + 1) % lista.length;
        } else if (e.key === 'ArrowUp' || e.key === 'Up') {
            destino = (pos - 1 + lista.length) % lista.length;
        } else if (e.key === 'Home') {
            destino = 0;
        } else if (e.key === 'End') {
            destino = lista.length - 1;
        } else {
            return;
        }

        e.preventDefault();
        lista[destino].focus();   // o focusin cuida da troca de conteudo
    });

    /* ----- execucao: sinaliza andamento e descarta clique repetido -----
     Na fase de captura, para conseguir barrar o segundo clique antes de ele
     chegar ao handler do PrimeFaces e disparar a acao duas vezes. */
    document.addEventListener('click', function (e) {
        var botao = maisProximo(e.target, 'a.SbAtvQuad');
        if (!botao || !bloco(botao)) {
            return;
        }
        if (botao.classList.contains('AtvDispExecutando')) {
            e.preventDefault();
            e.stopImmediatePropagation();
            e.stopPropagation();
            return;
        }
        botao.classList.add('AtvDispExecutando');
    }, true);

    if (window.jQuery) {
        jQuery(document).on('pfAjaxComplete pfAjaxError', function () {
            Array.prototype.forEach.call(document.querySelectorAll('.AtvDispExecutando'), function (botao) {
                botao.classList.remove('AtvDispExecutando');
            });
        });
    }

    /* Mesmo papel do exibirNovasAcoesDaAtividade previsto no esqueleto: expoe
     a troca de painel para quem quiser chamar direto da marcacao. */
    window.exibirNovasAcoesDaAtividade = function (alvo) {
        var linha = document.querySelector('.AtvDispRelLinha[data-atvdisp-alvo="' + alvo + '"]');
        var bl = bloco(linha);
        if (bl) {
            mostrar(bl, String(alvo));
        }
    };

    /* Diagnostico: AtvDispDiagnostico() no console responde se o script
     carregou, quantas linhas e paineis existem e se os alvos das linhas
     casam com os paineis. Alvo sem painel correspondente e a causa tipica
     de "passo o mouse e nada acontece". */
    window.AtvDispDiagnostico = function () {
        return Array.prototype.map.call(document.querySelectorAll(SEL_BLOCO), function (bl) {
            var alvos = linhas(bl).map(function (l) {
                return l.getAttribute('data-atvdisp-alvo');
            });
            var paineis = Array.prototype.map.call(bl.querySelectorAll('.AtvDispPainel'), function (pn) {
                return pn.getAttribute('data-atvdisp-painel');
            });
            return {
                linhas: alvos.length,
                paineis: paineis.length,
                alvosDasLinhas: alvos,
                alvosDosPaineis: paineis,
                semPainel: alvos.filter(function (a) {
                    return paineis.indexOf(a) === -1;
                }),
                fixado: fixado(bl)
            };
        });
    };
})();

/* ==========================================================================
 Painel de acoes da atividade (crmAtividade/opcoesAtividade).

 O abrir/fechar por hover e foco e CSS puro; aqui fica so o que o CSS nao
 alcanca:
 - clique no cabecalho      -> alterna .aberto (caminho do toque) e aria-expanded
 - Escape                   -> fecha e devolve o foco ao cabecalho
 - clique fora              -> fecha o que estiver .aberto
 - .AcoesAtv--recolhido     -> fechado a pedido com mouse/foco ainda dentro;
 vence :hover e :focus-within ate sairem do painel
 - .AcoesAtv--semEntrada    -> a entrada (espiar/cutucar) roda so no primeiro
 render; o @form das acoes re-renderiza o painel
 - clique numa acao         -> recolhe o painel e trava os itens ate o ajax
 terminar (.AcoesAtv--carregando no painel,
 .botaoAcaoAtv--carregando no item, que mostra o
 spinner no rail). Vale para todos os itens
 (Resumido e facet extra inclusive), sem depender
 do onStart/onComplete de cada botaoAcaoAtividade
 - clique com o painel travado -> descartado antes de chegar ao botao
 - fim do ajax com a fila vazia -> destrava; se o update re-renderiza o
 painel, ele ja volta sem as classes

 Ouvintes delegados ao document, como o bloco acima, para sobreviver a
 qualquer update ajax.
 ========================================================================== */

(function () {
    if (window.AcoesAtvIniciado) {
        return;
    }
    window.AcoesAtvIniciado = true;

    var RECOLHIDO = 'AcoesAtv--recolhido';
    var jaEspiou = false;

    function maisProximo(el, seletor) {
        return el && el.closest ? el.closest(seletor) : null;
    }

    function definirAberto(painel, abrir) {
        painel.classList.toggle('aberto', abrir);
        var botao = painel.querySelector('.AcoesAtvCabecalho');
        if (botao) {
            botao.setAttribute('aria-expanded', abrir ? 'true' : 'false');
        }
    }

    /* Painel novo no DOM: o da carga da pagina anima; os que chegam depois
     por ajax entram sem animacao. A animacao comeca recolhida e com 300ms de
     espera, entao desliga-la no pfAjaxComplete nao da salto visivel. */
    function marcarNovos() {
        var novos = document.querySelectorAll('.AcoesAtv:not([data-acoes-atv])');
        for (var i = 0; i < novos.length; i++) {
            novos[i].setAttribute('data-acoes-atv', '');
            if (jaEspiou) {
                novos[i].classList.add('AcoesAtv--semEntrada');
            }
        }
        if (novos.length) {
            jaEspiou = true;
        }
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', marcarNovos);
    } else {
        marcarNovos();
    }
    if (window.jQuery) {
        jQuery(document).on('pfAjaxComplete', marcarNovos);
    }

    document.addEventListener('click', function (e) {
        var cabecalho = maisProximo(e.target, '.AcoesAtvCabecalho');
        if (cabecalho) {
            var painel = cabecalho.closest('.AcoesAtv');
            var abrir = !painel.classList.contains('aberto');
            painel.classList.remove(RECOLHIDO);
            definirAberto(painel, abrir);
            if (!abrir) {
                /* sem isso o :focus-within manteria aberto o que o usuario mandou fechar */
                painel.classList.add(RECOLHIDO);
            }
            return;
        }
        var abertos = document.querySelectorAll('.AcoesAtv.aberto');
        for (var j = 0; j < abertos.length; j++) {
            if (!abertos[j].contains(e.target)) {
                definirAberto(abertos[j], false);
            }
        }
    });

    /* Escape fecha e devolve o foco ao cabecalho, que continua visivel no rail */
    document.addEventListener('keydown', function (e) {
        if (e.key !== 'Escape' && e.key !== 'Esc') {
            return;
        }
        var painel = maisProximo(document.activeElement, '.AcoesAtv');
        var abertos = document.querySelectorAll('.AcoesAtv.aberto');
        for (var k = 0; k < abertos.length; k++) {
            definirAberto(abertos[k], false);
        }
        if (painel) {
            painel.classList.add(RECOLHIDO);
            var cabecalho = painel.querySelector('.AcoesAtvCabecalho');
            if (cabecalho) {
                cabecalho.focus();
            }
        }
    });

    /* O recolhimento forcado vale ate o mouse ou o foco sairem do painel,
     ou ate o teclado seguir para uma acao (Tab depois do Escape) */
    document.addEventListener('focusin', function (e) {
        var painel = maisProximo(e.target, '.AcoesAtv.' + RECOLHIDO);
        if (painel && !maisProximo(e.target, '.AcoesAtvCabecalho')) {
            painel.classList.remove(RECOLHIDO);
        }
    });
    document.addEventListener('mouseout', function (e) {
        var painel = maisProximo(e.target, '.AcoesAtv.' + RECOLHIDO);
        if (painel && !painel.contains(e.relatedTarget)) {
            painel.classList.remove(RECOLHIDO);
        }
    });
    document.addEventListener('focusout', function (e) {
        var painel = maisProximo(e.target, '.AcoesAtv.' + RECOLHIDO);
        if (painel && !painel.contains(e.relatedTarget)) {
            painel.classList.remove(RECOLHIDO);
        }
    });

    /* ----- Trava durante o ajax de uma acao ----- */
    var CARREGANDO = 'AcoesAtv--carregando';
    var ITEM_CARREGANDO = 'botaoAcaoAtv--carregando';
    /* rede de seguranca: request perdido nao deixa o menu travado para sempre */
    var LIMITE_MS = 120000;
    var limite = null;

    function destravar() {
        clearTimeout(limite);
        var travados = document.querySelectorAll('.AcoesAtv.' + CARREGANDO);
        for (var i = 0; i < travados.length; i++) {
            travados[i].classList.remove(CARREGANDO);
            travados[i].removeAttribute('aria-busy');
            var itens = travados[i].querySelectorAll('.' + ITEM_CARREGANDO);
            for (var j = 0; j < itens.length; j++) {
                itens[j].classList.remove(ITEM_CARREGANDO);
            }
        }
    }

    function filaOcupada() {
        var fila = window.PrimeFaces && PrimeFaces.ajax && PrimeFaces.ajax.Queue;
        if (!fila) {
            return false;
        }
        return (fila.requests && fila.requests.length > 0)
                || (fila.xhrs && fila.xhrs.length > 0);
    }

    /* Captura: roda antes do onclick do botao, para o clique repetido (mouse
     ou Enter/espaco) ser descartado antes de disparar outro remoteCommand */
    document.addEventListener('click', function (e) {
        var botao = maisProximo(e.target, '.AcoesAtv .botaoAcaoAtvBotao');
        if (!botao) {
            return;
        }
        var painel = botao.closest('.AcoesAtv');
        if (painel.classList.contains(CARREGANDO)) {
            e.preventDefault();
            e.stopPropagation();
            return;
        }
        painel.classList.add(CARREGANDO);
        painel.setAttribute('aria-busy', 'true');
        var item = botao.closest('.comp-crc-botaoAcaoAtividade');
        if (item) {
            item.classList.add(ITEM_CARREGANDO);
        }
        /* Recolhe: o spinner do item fica no rail, visivel. O RECOLHIDO vence
         o :hover e o :focus-within do botao que acabou de receber o clique */
        definirAberto(painel, false);
        painel.classList.add(RECOLHIDO);
        clearTimeout(limite);
        limite = setTimeout(destravar, LIMITE_MS);
    }, true);

    if (window.jQuery) {
        /* o setTimeout deixa o PrimeFaces tirar o request da fila antes da conferencia */
        jQuery(document).on('pfAjaxComplete', function () {
            setTimeout(function () {
                if (!filaOcupada()) {
                    destravar();
                }
            }, 0);
        });
    }
})();


/* ==========================================================================
 Revisao do e-mail antes do envio (crmEmailAtividade/emailRevisao).

 A etapa fica marcada em azul enquanto o modal dela esta aberto: e o que
 responde "estou editando esta". A marca e uma classe, e nao :hover nem
 :focus, de proposito -- o modal abre por cima do ponteiro e o mouseleave
 nunca chega, entao hover/foco travariam acesos. Quem liga e o clique, quem
 desliga e o onHide do proprio modal: a marca acompanha o modal, nao o
 ponteiro.

 Marcacao (sobrevive a qualquer update ajax, pois o ouvinte e delegado ao
 document e nao depende de script inline no componente):
 .crc-comp-emailRevisao          raiz de cada instancia do componente
 [data-revisao-modal="WV"]       link que abre o modal de widgetVar WV
 .Atv-exec-email-revisaoEtapa    faixa que recebe a marca de edicao

 Antes o script ficava inline num h:outputScript target="head": num update
 ajax o head nao e re-renderizado, a funcao nunca era definida e o clique
 falhava (o destaque de foco, CSS puro, continuava funcionando).
 ========================================================================== */

(function () {
    if (window.EmailRevisaoIniciado) {
        return;
    }
    window.EmailRevisaoIniciado = true;

    var SEL_RAIZ = '.crc-comp-emailRevisao';
    var EM_EDICAO = 'Atv-exec-email-revisaoEtapaEmEdicao';
    var MODAL_ABERTO = 'Atv-exec-email-revisaoModalAberto';

    function maisProximo(el, seletor) {
        return el && el.closest ? el.closest(seletor) : null;
    }

    function limparEtapas() {
        document.body.classList.remove(MODAL_ABERTO);
        Array.prototype.forEach.call(document.querySelectorAll('.' + EM_EDICAO), function (etapa) {
            etapa.classList.remove(EM_EDICAO);
        });
    }

    function abrirEtapa(link, modal) {
        limparEtapas();
        var etapa = maisProximo(link, '.Atv-exec-email-revisaoEtapa');
        if (etapa) {
            etapa.classList.add(EM_EDICAO);
        }
        /* marca no body: e ela que suspende o hover das outras etapas
         enquanto o modal cobre a tela. Nao da para usar a mascara do
         PrimeFaces (.ui-widget-overlay) como sinal: confirmDialog e
         BlockUI deixam mascaras no DOM o tempo todo, e o hover ficaria
         suspenso para sempre. */
        document.body.classList.add(MODAL_ABERTO);
        var widget = window.PF ? PF(modal) : null;
        if (widget) {
            widget.show();
        } else {
            limparEtapas();
        }
    }

    /* Inicializacao de cada instancia renderizada (carga da pagina ou
     update ajax). Um componente re-renderizado recria os modais fechados,
     e o onHide do modal antigo nao chega a disparar: sem isso a marca de
     edicao e a do body ficariam presas. */
    function iniciarNovos() {
        var novos = document.querySelectorAll(SEL_RAIZ + ':not([data-revisao-iniciado])');
        if (!novos.length) {
            return;
        }
        for (var i = 0; i < novos.length; i++) {
            novos[i].setAttribute('data-revisao-iniciado', '');
        }
        limparEtapas();
    }

    document.addEventListener('click', function (e) {
        var link = maisProximo(e.target, SEL_RAIZ + ' [data-revisao-modal]');
        if (!link) {
            return;
        }
        e.preventDefault();
        abrirEtapa(link, link.getAttribute('data-revisao-modal'));
    });

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', iniciarNovos);
    } else {
        iniciarNovos();
    }
    if (window.jQuery) {
        jQuery(document).on('pfAjaxComplete', iniciarNovos);
    }

    /* Nomes globais mantidos para o onHide dos modais e chamadas antigas */
    window.revisaoAbrirEtapa = abrirEtapa;
    window.revisaoLimparEtapas = limparEtapas;
})();


function salvarEEnviarEmail() {
    PF('blockDisparo').show();
    var execucaoAutosaveEmail = executarAutosave();
    execucaoAutosaveEmail.then(function (responseData) {
        console.log(responseData);
        var execucaoEnvioDeEmail = enviarEmail();
        execucaoEnvioDeEmail.then(function (respostaEnvio) {
            console.log(respostaEnvio);
            PF('blockDisparo').hide();
        }).catch(function (errorEnvio) {
            alert("falhouEnviando");
            console.log(errorEnvio);

        });
    }).catch(function (errorRascunho) {
        console.error("Falhou");
        alert("falhou Salvando rascunho");
        console.log(errorRascunho);
    });

}