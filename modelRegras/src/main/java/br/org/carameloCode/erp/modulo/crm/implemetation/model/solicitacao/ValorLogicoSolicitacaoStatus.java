package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValorLogicoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValoresLogicosSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacao(calculo = ValoresLogicosSolicitacao.STATUS)
public class ValorLogicoSolicitacaoStatus extends ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoStatus(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getSolicitacao().isFoiAtendida() && getSolicitacao().isFoiFinalizada()) {
            getSolicitacao().setStatus(FabStatusSolicitacao.FINALIZADO.getRegistro());
        }

        if (!getSolicitacao().isFoiAtendida() && getSolicitacao().isFoiFinalizada()) {
            getSolicitacao().setStatus(FabStatusSolicitacao.RECUSADO.getRegistro());
        }

        return getSolicitacao().getStatus();
    }

    public Solicitacao getSolicitacao() {
        return (Solicitacao) getCampoInst().getObjetoRaizDoAtributo();
    }
}
