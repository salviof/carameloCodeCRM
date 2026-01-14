package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValorLogicoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValoresLogicosTipoRelacionamento;

@ValorLogicoTipoRelacionamento(calculo = ValoresLogicosTipoRelacionamento.ATIVIDADESDOWNGRADEMETA)
public class ValorLogicoTipoRelacionamentoAtividadesDownGradeMeta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoRelacionamentoAtividadesDownGradeMeta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valordefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valordefinido) {

            List<TipoAtividadeCRM> tiposDoRelacionamentoAtual = new ArrayList<>();
            List<TipoAtividadeCRM> tiposDaMetaAtual = new ArrayList<>();

            getTipoRelacionamento().getTiposAtividadeDisponiveis().stream().filter(at -> at.getRelacionamentoGerado() != null
                    && at.getRelacionamentoGerado().getMetaRelacionamento().getPeso() < getTipoRelacionamento().getMetaRelacionamento().getPeso())
                    .forEach(tiposDoRelacionamentoAtual::add);

            getTipoRelacionamento().getMetaRelacionamento().getTiposAtividadeDisponiveis().stream().filter(at -> at.getRelacionamentoGerado() != null
                    && at.getRelacionamentoGerado().getMetaRelacionamento().getPeso() < getTipoRelacionamento().getMetaRelacionamento().getPeso())
                    .forEach(tiposDaMetaAtual::add);
            tiposDaMetaAtual.stream().filter(tipo -> !tiposDoRelacionamentoAtual.contains(tipo)).forEach(tiposDoRelacionamentoAtual::add);

            getTipoRelacionamento().setAtividadesDownGradeMeta(tiposDoRelacionamentoAtual);
            valordefinido = true;
        }
        return getTipoRelacionamento().getAtividadesDownGradeMeta();
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoDoAtributo();
    }
}
