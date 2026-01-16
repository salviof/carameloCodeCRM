package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadoia;

import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadorTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadoresTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrm.ValidacaoTipoDadoCRMFabricaTipoAtributo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTipoDadoIA(validador = ValidadoresTipoDadoIA.FABRICATIPOATRIBUTO)
public class ValidacaoTipoDadoIAFabricaTipoAtributo
        extends
        ValidacaoTipoDadoCRMFabricaTipoAtributo {

    public ValidacaoTipoDadoIAFabricaTipoAtributo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
