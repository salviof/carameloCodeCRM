package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadoia;

import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadorTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadoresTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrm.ValidacaoTipoDadoCRMValorPadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTipoDadoIA(validador = ValidadoresTipoDadoIA.VALORPADRAO)
public class ValidacaoTipoDadoIAValorPadrao
        extends
        ValidacaoTipoDadoCRMValorPadrao {

    public ValidacaoTipoDadoIAValorPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
