package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValorLogicoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValoresLogicosSolicitacao;

@ValorLogicoSolicitacao(calculo = ValoresLogicosSolicitacao.NOME)
public class ValorLogicoSolicitacaoNome extends ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
