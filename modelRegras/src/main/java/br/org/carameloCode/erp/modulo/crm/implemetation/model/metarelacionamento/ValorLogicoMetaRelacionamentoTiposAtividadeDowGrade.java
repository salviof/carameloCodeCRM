package br.org.carameloCode.erp.modulo.crm.implemetation.model.metarelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.ValorLogicoMetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.ValoresLogicosMetaRelacionamento;

@ValorLogicoMetaRelacionamento(calculo = ValoresLogicosMetaRelacionamento.TIPOSATIVIDADEDOWGRADE)
public class ValorLogicoMetaRelacionamentoTiposAtividadeDowGrade
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetaRelacionamentoTiposAtividadeDowGrade(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valordefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valordefinido) {
            List<TipoAtividadeCRM> tipos = new ArrayList<>();

            getMeta().getTiposAtividadeDisponiveis().stream().filter(at -> at.getRelacionamentoGerado() != null
                    && at.getRelacionamentoGerado().getMetaRelacionamento().getPeso() < getMeta().getPeso())
                    .forEach(tipos::add);

            getMeta().setTiposAtividadeDowGrade(tipos);
        }
        return getMeta().getTiposAtividadeDowGrade();
    }

    public MetaRelacionamento getMeta() {
        return (MetaRelacionamento) getCampoInst().getObjetoDoAtributo();
    }
}
