package br.org.carameloCode.erp.modulo.crm.implemetation.model.pedidoacessopessoa;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pedidoacessopessoa.ValorLogicoPedidoAcessoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pedidoacessopessoa.ValoresLogicosPedidoAcessoPessoa;

@ValorLogicoPedidoAcessoPessoa(calculo = ValoresLogicosPedidoAcessoPessoa.ATIVO)
public class ValorLogicoPedidoAcessoPessoaAtivo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPedidoAcessoPessoaAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
