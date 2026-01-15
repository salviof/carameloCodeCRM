/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util.navegador;

import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author SalvioF
 */
public class RoboInterpredadorProspecto extends Navegador {

    private final List<InfoLocalizadorInformacaoHtml> informacoes;

    private final String parteUrlDescricao;
    private final List<String> paginasListagens;
    private List<String> paginasDetlheLista = new ArrayList<>();

    private Map<String, PessoaJuridica> prospectosEncontrados = new ArrayMap<>();

    private int paginaDetalheIndice;
    private int paginaListagemIndice;

    protected Document documentoDetalhes;

    public RoboInterpredadorProspecto(String pParteUrlDescricao, List<String> pUrls) {
        super(pUrls.get(0));

        paginaListagemIndice = 0;
        parteUrlDescricao = pParteUrlDescricao;
        paginasListagens = pUrls;
        informacoes = new ArrayList<>();
        processarListagemAtual();
    }

    public final void processarListagemAtual() {
        navegar(paginasListagens.get(paginaListagemIndice));
        System.out.println("titulo Listagem:" + documento.title()); //doc.get

        Elements links = documento.select("a[href]");
        paginasDetlheLista.clear();
        paginaDetalheIndice = 0;
        for (Element link : links) {
            if (link.attr("href").contains(parteUrlDescricao)) {
                String endereco = "http://www.cadastroindustrialmg.com.br" + link.attr("href");
                paginasDetlheLista.add(endereco);
            }
        }

    }

    public void adicionarInformacaoLocalizada(InfoLocalizadorInformacaoHtml pInformacao) {
        if (!informacoes.contains(pInformacao)) {
            informacoes.add(pInformacao);
        }
    }

    public void proximaPaginaListagem() {

        paginaListagemIndice++;
        if (paginaListagemIndice > paginasListagens.size() - 1) {
            paginaListagemIndice = 0;

        }
        processarListagemAtual();
    }

    public void proximaPaginaDetalhes() {
        paginaDetalheIndice++;
        if (paginaDetalheIndice > paginasDetlheLista.size() - 1) {
            paginaDetalheIndice = 0;
        }
        processarPaginaDetalhesAtual();
    }

    public void processarPaginaDetalhesAtual() {
        try {
            documentoDetalhes = Jsoup.parse(new URL(paginasDetlheLista.get(paginaDetalheIndice)), 2000);
            PessoaJuridica novoProsp = new PessoaJuridica();
            for (InfoLocalizadorInformacaoHtml informacao : informacoes) {
                Element elemento = null;
                try {

                    switch (informacao.getMetodo()) {
                        case TEXTO:
                            elemento = documentoDetalhes.select(informacao.getTermoPesquisaCSS()).first();
                            break;
                        case PRIMEIRO_TEXTO:
                            elemento = documentoDetalhes.select(informacao.getTermoPesquisaCSS()).first();
                            break;
                        case ULTIMO_TEXTO:
                            elemento = documentoDetalhes.select(informacao.getTermoPesquisaCSS()).last();

                            break;
                        case SEGUNDO_TEXTO:
                            elemento = documentoDetalhes.select(informacao.getTermoPesquisaCSS()).get(1);
                            break;
                        default:
                            throw new AssertionError(informacao.getMetodo().name());
                    }
                    if (elemento != null) {
                        String texto = elemento.text();

                        if (informacao.isApenasNumeros()) {
                            texto = UtilCRCStringFiltros.getNumericosDaString(texto);

                        } else if (informacao.isApenasLetras()) {
                            texto = UtilCRCStringFiltros.getStringSemNumeros(texto);
                        }
                        String campo = informacao.getCampo();
                        if (campo.equals("observacao")) {
                            if (novoProsp.getCampoInstanciadoByNomeOuAnotacao(campo).getValor() != null) {
                                texto = texto + "|" + novoProsp.getCampoInstanciadoByNomeOuAnotacao(campo).getValor();
                            }
                        }
                        novoProsp.getCampoInstanciadoByNomeOuAnotacao(campo).setValor(texto);
                    }

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro interpretando valor" + t.getMessage(), t);
                }

            }
            prospectosEncontrados.put(novoProsp.getNome(), novoProsp);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando pagina de detalhes", t);
        }
    }

    public List<PessoaJuridica> getTodosProspectos() {

        if (paginaListagemIndice == 0) {
            processarListagemAtual();
            processarPaginaDetalhesAtual();
            proximaPaginaDetalhes();
            while (paginaDetalheIndice > 0) {

                proximaPaginaDetalhes();

            }
            proximaPaginaListagem();
            while (paginaListagemIndice > 0) {
                processarPaginaDetalhesAtual();
                proximaPaginaDetalhes();
                while (paginaDetalheIndice > 0) {
                    proximaPaginaDetalhes();
                }

                proximaPaginaListagem();
            }
        }
        return Lists.newArrayList(prospectosEncontrados.values());
    }

    public List<PessoaJuridica> getProspectosPaginaAtual() {
        throw new UnsupportedOperationException("Não Implementado");
    }

    public boolean isTemProximaPagina() {
        throw new UnsupportedOperationException("Não Implementado");
    }

    public void lerProximaPagina() {
        throw new UnsupportedOperationException("Não Implementado");
    }

}
