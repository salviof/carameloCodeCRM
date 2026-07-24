package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaochamado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValorLogicoSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValoresLogicosSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoChamado(calculo = ValoresLogicosSolicitacaoChamado.STATUS)
public class ValorLogicoSolicitacaoChamadoStatus
        extends
        ValorLogicoSolicitacaoStatus {

    public ValorLogicoSolicitacaoChamadoStatus(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoChamado getSolicitacaoChamado() {
        return (SolicitacaoChamado) getCampoInst().getObjetoRaizDoAtributo();
    }
}
