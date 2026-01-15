package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.UMACONVERSAINTERNA)
public class ValorLogicoEmailRecebidoUmaConversaInterna
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoUmaConversaInterna(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        setValorPorReflexao(getEmailRecebido().getProspecto() != null);
        return getEmailRecebido().getProspecto() != null;
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
