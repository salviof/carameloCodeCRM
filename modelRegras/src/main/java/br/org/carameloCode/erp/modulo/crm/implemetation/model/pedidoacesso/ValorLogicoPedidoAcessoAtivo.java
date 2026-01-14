package br.org.carameloCode.erp.modulo.crm.implemetation.model.pedidoacesso;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pedidoacesso.ValorLogicoPedidoAcesso;
import br.org.carameloCode.erp.modulo.crm.api.model.pedidoacesso.ValoresLogicosPedidoAcesso;

@ValorLogicoPedidoAcesso(calculo = ValoresLogicosPedidoAcesso.ATIVO)
public class ValorLogicoPedidoAcessoAtivo extends ValorLogicoCalculoGenerico {

    public ValorLogicoPedidoAcessoAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
