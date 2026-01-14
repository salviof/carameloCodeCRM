package br.org.carameloCode.erp.modulo.crm.implemetation.model.notificacaosb;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.notificacaosb.ValorLogicoNotificacaoSB;
import br.org.carameloCode.erp.modulo.crm.api.model.notificacaosb.ValoresLogicosNotificacaoSB;

@ValorLogicoNotificacaoSB(calculo = ValoresLogicosNotificacaoSB.ASSUNTO)
public class ValorLogicoNotificacaoSBAssunto extends org.coletivoJava.fw.projetos.erpColetivoJava.implemetation.model.notificacaosb.ValorLogicoNotificacaoSBAssunto {

    public ValorLogicoNotificacaoSBAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
