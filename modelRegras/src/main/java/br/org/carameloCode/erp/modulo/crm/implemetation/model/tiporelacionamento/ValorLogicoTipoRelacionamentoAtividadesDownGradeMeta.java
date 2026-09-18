package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.CPTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
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

        if (getTipoRelacionamento().getId() == null) {
            return getTipoRelacionamento().getAtividadesDownGradeMeta();
        }

        if (!valordefinido) {
            try {
                List<TipoAtividadeCRM> tiposDoRelacionamentoAtual = new ArrayList<>();
                List<TipoAtividadeCRM> todosTipos = (List<TipoAtividadeCRM>) getTipoRelacionamento().getCPinst(CPTipoRelacionamento.todasatividadesdisponiveis).getValor();

                todosTipos.stream().filter(at -> at.getRelacionamentoGerado() != null
                        && at.getRelacionamentoGerado().getMetaRelacionamento().getPeso() < getTipoRelacionamento().getMetaRelacionamento().getPeso())
                        .forEach(tiposDoRelacionamentoAtual::add);

                getTipoRelacionamento().setAtividadesDownGradeMeta(tiposDoRelacionamentoAtual);
            } finally {
                valordefinido = true;
            }
        }
        return getTipoRelacionamento().getAtividadesDownGradeMeta();
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoDoAtributo();
    }
}
