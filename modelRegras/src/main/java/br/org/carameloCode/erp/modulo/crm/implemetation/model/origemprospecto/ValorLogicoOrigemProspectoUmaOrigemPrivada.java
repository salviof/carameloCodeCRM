package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.ValorLogicoOrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.ValoresLogicosOrigemProspecto;

@ValorLogicoOrigemProspecto(calculo = ValoresLogicosOrigemProspecto.UMAORIGEMPRIVADA)
public class ValorLogicoOrigemProspectoUmaOrigemPrivada
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrigemProspectoUmaOrigemPrivada(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getOrigem().setUmaOrigemPrivada(false);
        return getOrigem().isUmaOrigemPrivada();
    }

    public OrigemProspecto getOrigem() {
        return (OrigemProspecto) getCampoInst().getObjetoDoAtributo();
    }
}
