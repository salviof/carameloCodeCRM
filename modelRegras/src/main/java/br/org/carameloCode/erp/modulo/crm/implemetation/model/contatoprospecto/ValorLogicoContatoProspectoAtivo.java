package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;

@ValorLogicoContatoProspecto(calculo = ValoresLogicosContatoProspecto.ATIVO)
public class ValorLogicoContatoProspectoAtivo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoProspectoAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return getContatoProspecto().isAtivo();
    }

    public ContatoProspecto getContatoProspecto() {
        return (ContatoProspecto) getCampoInst().getObjetoDoAtributo();
    }
}
