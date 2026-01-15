package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospectoprivado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValorLogicoOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValoresLogicosOrigemProspectoPrivado;

@ValorLogicoOrigemProspectoPrivado(calculo = ValoresLogicosOrigemProspectoPrivado.UMAORIGEMPRIVADA)
public class ValorLogicoOrigemProspectoPrivadoUmaOrigemPrivada
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrigemProspectoPrivadoUmaOrigemPrivada(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getOrigem().setUmaOrigemPrivada(true);
        return getOrigem().isUmaOrigemPrivada();

    }

    public OrigemProspectoPrivado getOrigem() {
        return (OrigemProspectoPrivado) getCampoInst().getObjetoDoAtributo();
    }
}
