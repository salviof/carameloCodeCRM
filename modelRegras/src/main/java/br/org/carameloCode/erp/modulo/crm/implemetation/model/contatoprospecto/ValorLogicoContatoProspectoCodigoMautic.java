package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;

@ValorLogicoContatoProspecto(calculo = ValoresLogicosContatoProspecto.CODIGOMAUTIC)
public class ValorLogicoContatoProspectoCodigoMautic
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoProspectoCodigoMautic(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getContato().getCodigoMautic();
    }

    public ContatoProspecto getContato() {
        return (ContatoProspecto) getCampoInst().getObjetoDoAtributo();
    }
}
