/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

/**
 *
 * @author SalvioF
 */
public class BfluxoRelacionamento implements Serializable {

    private final List<BFluxoItem> itensPositivo;
    private final List<BFluxoItem> itensNegativo;
    private final List<BFluxoItem> itensNeutro;
    private BFluxoItem itemRelacionamentoAtual;
    private final LAYOUT_FLUXO layout;
    private int quantidadeItem;

    public static enum LAYOUT_FLUXO {
        HORIZONTAL, VERTICAL
    }

    public void adicionarItem(BFluxoItem pItem) {

        switch (pItem.getTipoItem()) {
            case RELACIONAMENTO_NEGATIVO:
                pItem.setIdentificador("negativo" + itensNegativo.size() + 1);
                itensNegativo.add(pItem);
                break;
            case RELACIONAMENTO_ATUAL:
                pItem.setIdentificador("atual");
                itemRelacionamentoAtual = pItem;
                break;
            case RELACIONAMENTO_POSITIVO:
                pItem.setIdentificador("postivo" + itensPositivo.size() + 1);
                itensPositivo.add(pItem);
                break;
            case RELACIONAMENTO_NEUTRO:
                pItem.setIdentificador("negativo" + itensNeutro.size() + 1);
                itensNeutro.add(pItem);
                break;
            default:
                throw new AssertionError(pItem.getTipoItem().name());

        }
        quantidadeItem++;

    }

    public BfluxoRelacionamento(TipoRelacionamento pTipoRelacionamento, LAYOUT_FLUXO pLayout) {
        this.itensNeutro = new ArrayList<>();
        this.itensNegativo = new ArrayList<>();
        this.itensPositivo = new ArrayList<>();
        layout = pLayout;

        new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {

                UtilSBPersistencia.loadEntidade(pTipoRelacionamento, getEm());
                BFluxoItem itemDoRelacionamento = new BFluxoItem(BFluxoItem.TIPO_ITEM.RELACIONAMENTO_ATUAL);
                itemDoRelacionamento.setConteudo(pTipoRelacionamento.getNomeDoRelacionado());
                adicionarItem(itemDoRelacionamento);

                for (TipoAtividadeCRM tipoAtividade : pTipoRelacionamento.getTiposAtividadeDisponiveis()) {
                    BFluxoItem itemRElacionamento = null;
                    if (tipoAtividade.getRelacionamentoGerado() != null) {

                        TipoRelacionamento relacionamentogerado = tipoAtividade.getRelacionamentoGerado();
                        if (relacionamentogerado.getPeso() > pTipoRelacionamento.getPeso()) {
                            itemRElacionamento = new BFluxoItem(BFluxoItem.TIPO_ITEM.RELACIONAMENTO_POSITIVO);
                        }
                        if (relacionamentogerado.getPeso() < pTipoRelacionamento.getPeso()) {
                            itemRElacionamento = new BFluxoItem(BFluxoItem.TIPO_ITEM.RELACIONAMENTO_NEGATIVO);
                        }
                        if (relacionamentogerado.getPeso() == pTipoRelacionamento.getPeso()) {
                            itemRElacionamento = new BFluxoItem(BFluxoItem.TIPO_ITEM.RELACIONAMENTO_NEUTRO);
                        }
                        if (itemRElacionamento != null) {
                            itemRElacionamento.setConteudo(relacionamentogerado.getNome());
                            itemRElacionamento.setAcaoGeradora(tipoAtividade.getNomeAtividade());
                            adicionarItem(itemRElacionamento);
                        }

                    }

                }

                return null;
            }

        };

        atualizarFluxo();

    }

    public final void atualizarFluxo() {
        int quantidadeNegativo = 1;
        int quantidadepositivo = 1;
        int quantidadeNeutro = 1;

        switch (layout) {
            case HORIZONTAL:
                itemRelacionamentoAtual.setX("30em;");
                itemRelacionamentoAtual.setY("10em;");
                break;
            case VERTICAL:
                itemRelacionamentoAtual.setX("1em;");
                itemRelacionamentoAtual.setY("30em;");
                break;
            default:
                throw new AssertionError(layout.name());

        }

        for (BFluxoItem item : getItensNegativo()) {
            switch (layout) {
                case HORIZONTAL:
                    int xNegativoHorizontal = 1;
                    int yNegativoHorizontal = quantidadeNegativo * 10;
                    item.setX(xNegativoHorizontal + "em;");
                    item.setY(yNegativoHorizontal + "em;");
                    break;
                case VERTICAL:
                    int xNegativoVertical = quantidadeNegativo * 10;
                    int yNegativoVertical = 1;
                    item.setX(xNegativoVertical + "em;");
                    item.setY(yNegativoVertical + "em;");
                    break;
                default:
                    throw new AssertionError(layout.name());

            }
            quantidadeNegativo++;
        }

        for (BFluxoItem item : getItensPositivo()) {
            switch (layout) {
                case HORIZONTAL:
                    int xNegativoHorizontal = 60;
                    int yNegativoHorizontal = quantidadepositivo * 10;
                    item.setX(xNegativoHorizontal + "em;");
                    item.setY(yNegativoHorizontal + "em;");
                    break;
                case VERTICAL:
                    int xNegativoVertical = quantidadepositivo * 10;
                    int yNegativoVertical = 1;
                    item.setX(xNegativoVertical + "em;");
                    item.setY(yNegativoVertical + "em;");
                    break;
                default:

                    throw new AssertionError(layout.name());

            }
            quantidadepositivo++;
        }

        for (BFluxoItem item : getItensNeutro()) {
            switch (layout) {
                case HORIZONTAL:
                    int xNegativoHorizontal = 30;
                    int yNegativoHorizontal = 30;
                    item.setX(xNegativoHorizontal + "em;");
                    item.setY(yNegativoHorizontal + "em;");
                    break;
                case VERTICAL:
                    int xNegativoVertical = quantidadeNeutro * 10;
                    int yNegativoVertical = quantidadeNeutro * 10;
                    item.setX(xNegativoVertical + "em;");
                    item.setY(yNegativoVertical + "em;");
                    break;
                default:
                    throw new AssertionError(layout.name());

            }
            quantidadeNeutro++;
        }

    }

    public List<BFluxoItem> getItensPositivo() {
        return Lists.newArrayList(itensPositivo);
    }

    public List<BFluxoItem> getItensNegativo() {
        return Lists.newArrayList(itensNegativo);
    }

    public List<BFluxoItem> getItensNeutro() {
        return Lists.newArrayList(itensNeutro);
    }

    public BFluxoItem getItemRelacionamentoAtual() {
        return itemRelacionamentoAtual;
    }

}
