package br.org.carameloCode.erp.modulo.crm.implemetation.model.modeloemailmarketing;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.modeloemailmarketing.ValorLogicoModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.api.model.modeloemailmarketing.ValoresLogicosModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.modeloemail.ValorLogicoModeloEmailAssunto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoModeloEmailMarketing(calculo = ValoresLogicosModeloEmailMarketing.ASSUNTO)
public class ValorLogicoModeloEmailMarketingAssunto
        extends
        ValorLogicoModeloEmailAssunto {

    public ValorLogicoModeloEmailMarketingAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ModeloEmailMarketing getModeloEmailMarketing() {
        return (ModeloEmailMarketing) getCampoInst().getObjetoRaizDoAtributo();
    }
}
