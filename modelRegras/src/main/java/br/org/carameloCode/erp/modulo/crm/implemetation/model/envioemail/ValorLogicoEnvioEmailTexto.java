package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm.ValorLogicoEmailCrmTexto;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.TEXTO)
public class ValorLogicoEnvioEmailTexto extends ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailTexto(ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicaTextoGenerica = new ValorLogicoEmailCrmTexto(pCampo);
    }

    private final ValorLogicoEmailCrmTexto logicaTextoGenerica;

    @Override
    public Object getValor(Object... pEntidade) {
        return logicaTextoGenerica.getValor(pEntidade);

    }

}
