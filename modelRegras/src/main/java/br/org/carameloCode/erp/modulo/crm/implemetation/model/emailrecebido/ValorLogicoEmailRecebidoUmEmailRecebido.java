package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.UMEMAILRECEBIDO)
public class ValorLogicoEmailRecebidoUmEmailRecebido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoUmEmailRecebido(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        setValorPorReflexao(true);
        return true;
    }

}
