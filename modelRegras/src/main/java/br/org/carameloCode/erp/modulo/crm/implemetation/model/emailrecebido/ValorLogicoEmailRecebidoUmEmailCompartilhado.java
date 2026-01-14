package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.UMEMAILCOMPARTILHADO)
public class ValorLogicoEmailRecebidoUmEmailCompartilhado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoUmEmailCompartilhado(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getEmailRecebido().isUmEmailCompartilhado();
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
