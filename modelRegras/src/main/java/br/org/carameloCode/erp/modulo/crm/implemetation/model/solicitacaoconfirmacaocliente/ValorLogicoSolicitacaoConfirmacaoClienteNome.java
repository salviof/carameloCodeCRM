package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValorLogicoSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValoresLogicosSolicitacaoConfirmacaoCliente;

@ValorLogicoSolicitacaoConfirmacaoCliente(calculo = ValoresLogicosSolicitacaoConfirmacaoCliente.NOME)
public class ValorLogicoSolicitacaoConfirmacaoClienteNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoClienteNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
