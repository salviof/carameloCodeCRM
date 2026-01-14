package br.org.carameloCode.erp.modulo.crm.implemetation.model.subpastacliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastacliente.ValorLogicoSubpastaCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastacliente.ValoresLogicosSubpastaCliente;

@ValorLogicoSubpastaCliente(calculo = ValoresLogicosSubpastaCliente.DIRETORIOPROXIMO)
public class ValorLogicoSubpastaClienteDiretorioProximo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSubpastaClienteDiretorioProximo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
