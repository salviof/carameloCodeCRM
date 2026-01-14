package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospectoprivado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValorLogicoOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValoresLogicosOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto.ValorLogicoOrigemProspectoQuantidadeMeusLeads;

@ValorLogicoOrigemProspectoPrivado(calculo = ValoresLogicosOrigemProspectoPrivado.QUANTIDADEMEUSLEADS)
public class ValorLogicoOrigemProspectoPrivadoQuantidadeMeusLeads
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoOrigemProspectoQuantidadeMeusLeads quantidadeLeads;

    public ValorLogicoOrigemProspectoPrivadoQuantidadeMeusLeads(ItfCampoInstanciado pCampo) {
        super(pCampo);
        quantidadeLeads = new ValorLogicoOrigemProspectoQuantidadeMeusLeads(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return quantidadeLeads.getValor(pEntidade);
    }

}
