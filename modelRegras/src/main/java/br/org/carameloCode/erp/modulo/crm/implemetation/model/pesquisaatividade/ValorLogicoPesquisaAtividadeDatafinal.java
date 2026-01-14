package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.DATAFINAL)
public class ValorLogicoPesquisaAtividadeDatafinal
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeDatafinal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPesquisaAtividade().isMomentoAtual()) {
            if (getPesquisaAtividade().getDatafinal() == null) {

                getPesquisaAtividade().setDatafinal(UtilCRCDataHora.incrementaDias(new Date(), 1));
            }
        }
        return getPesquisaAtividade().getDatafinal();
    }

    private PesquisaAtividade getPesquisaAtividade() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
