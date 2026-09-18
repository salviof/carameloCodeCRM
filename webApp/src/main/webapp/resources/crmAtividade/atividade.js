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
    if (window.AtvDispDesktopIniciado) { return; }
    window.AtvDispDesktopIniciado = true;

    var ESPERA_PREVIA = 120;   // antiflicker ao entrar na previa
    var ESPERA_VOLTA = 200;    // antiflicker ao voltar para o fixado
    var SEL_BLOCO = '.AtividadesDisponiveisDesktop';

    function maisProximo(el, seletor) {
        if (!el) { return null; }
        if (el.closest) { return el.closest(seletor); }
        // fallback para navegador sem Element.closest
        var no = el;
        while (no && no.nodeType === 1) {
            if (no.matches ? no.matches(seletor) : false) { return no; }
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
        if (bl._atvDispPrevia) { window.clearTimeout(bl._atvDispPrevia); bl._atvDispPrevia = null; }
        if (bl._atvDispVolta) { window.clearTimeout(bl._atvDispVolta); bl._atvDispVolta = null; }
    }

    /* Troca o painel visivel. Se nao existir painel para o alvo, nao faz nada:
       melhor manter o que esta na tela do que esvaziar a area de conteudo.
       ARIA e ordem de tabulacao acompanham: so a linha exibida fica tabulavel. */
    function mostrar(bl, alvo) {
        if (!alvo || !painel(bl, alvo)) { return; }

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
        if (!gatilho) { return; }
        var bl = bloco(gatilho);
        if (bl) { agendarPrevia(bl, alvoDe(gatilho)); }
    });

    /* Sair de um gatilho volta para o fixado, com uma excecao deliberada: se o
       ponteiro seguiu para a area de conteudo, a previa e mantida, senao o
       painel desapareceria justamente no caminho ate o botao que o usuario
       quer clicar. */
    document.addEventListener('mouseout', function (e) {
        var bl = bloco(e.target);
        if (!bl) { return; }

        var destino = e.relatedTarget;
        if (!destino || !bl.contains(destino)) { agendarVolta(bl); return; }

        // segue num gatilho: o proprio mouseover resolve o que exibir
        if (dentroDoMenu(destino) || gatilhoDe(destino)) { return; }
        // entrou na area de conteudo: mantem o que esta na tela
        if (maisProximo(destino, '.AtvDispConteudo')) { limparEsperas(bl); return; }

        agendarVolta(bl);
    });

    /* ----- clique: fixa a selecao ----- */
    document.addEventListener('click', function (e) {
        var linha = linhaDe(e.target);
        if (!linha) { return; }
        var bl = bloco(linha);
        if (!bl) { return; }
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
        if (blAtivo) { limparEsperas(blAtivo); }
        Array.prototype.forEach.call(document.querySelectorAll(SEL_BLOCO), function (outro) {
            if (outro !== blAtivo) { agendarVolta(outro); }
        });
    });

    document.addEventListener('keydown', function (e) {
        var linha = linhaDe(e.target);
        if (!linha) { return; }
        var bl = bloco(linha);
        if (!bl) { return; }

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
        if (!botao || !bloco(botao)) { return; }
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
        if (bl) { mostrar(bl, String(alvo)); }
    };

    /* Diagnostico: AtvDispDiagnostico() no console responde se o script
       carregou, quantas linhas e paineis existem e se os alvos das linhas
       casam com os paineis. Alvo sem painel correspondente e a causa tipica
       de "passo o mouse e nada acontece". */
    window.AtvDispDiagnostico = function () {
        return Array.prototype.map.call(document.querySelectorAll(SEL_BLOCO), function (bl) {
            var alvos = linhas(bl).map(function (l) { return l.getAttribute('data-atvdisp-alvo'); });
            var paineis = Array.prototype.map.call(bl.querySelectorAll('.AtvDispPainel'), function (pn) {
                return pn.getAttribute('data-atvdisp-painel');
            });
            return {
                linhas: alvos.length,
                paineis: paineis.length,
                alvosDasLinhas: alvos,
                alvosDosPaineis: paineis,
                semPainel: alvos.filter(function (a) { return paineis.indexOf(a) === -1; }),
                fixado: fixado(bl)
            };
        });
    };
})();
