package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospectoprivado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValorLogicoOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValoresLogicosOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto.ValorLogicoOrigemProspectoQuantidadeLeads;

@ValorLogicoOrigemProspectoPrivado(calculo = ValoresLogicosOrigemProspectoPrivado.QUANTIDADELEADS)
public class ValorLogicoOrigemProspectoPrivadoQuantidadeLeads
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoOrigemProspectoQuantidadeLeads quantidadeLeads;

    public ValorLogicoOrigemProspectoPrivadoQuantidadeLeads(ItfCampoInstanciado pCampo) {
        super(pCampo);
        quantidadeLeads = new ValorLogicoOrigemProspectoQuantidadeLeads(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return quantidadeLeads.getValor(pEntidade);
    }

}
