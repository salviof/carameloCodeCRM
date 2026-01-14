package br.org.carameloCode.erp.modulo.crm.implemetation.model.caixapostal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValorLogicoCaixaPostal;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValoresLogicosCaixaPostal;

@ValorLogicoCaixaPostal(calculo = ValoresLogicosCaixaPostal.USUARIOS)
public class ValorLogicoCaixaPostalUsuarios extends ValorLogicoCalculoGenerico {

    public ValorLogicoCaixaPostalUsuarios(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getCaixaPosta().getUsuarios();
    }

    public CaixaPostal getCaixaPosta() {
        return (CaixaPostal) getCampoInst().getObjetoDoAtributo();
    }
}
