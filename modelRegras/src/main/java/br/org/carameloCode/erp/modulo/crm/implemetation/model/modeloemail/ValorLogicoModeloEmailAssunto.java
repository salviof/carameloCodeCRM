package br.org.carameloCode.erp.modulo.crm.implemetation.model.modeloemail;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.modeloemail.ValorLogicoModeloEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.modeloemail.ValoresLogicosModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoModeloEmail(calculo = ValoresLogicosModeloEmail.ASSUNTO)
public class ValorLogicoModeloEmailAssunto extends ValorLogicoCalculoGenerico {

    public ValorLogicoModeloEmailAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ModeloEmail getModeloEmail() {
        return (ModeloEmail) getCampoInst().getObjetoRaizDoAtributo();
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getModeloEmail().getAssunto();
    }

    @Override
    public Boolean isSomenteLeitura() {
        if (getModeloEmail().isEditarComIA()) {
            return true;
        }
        return super.isSomenteLeitura(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
