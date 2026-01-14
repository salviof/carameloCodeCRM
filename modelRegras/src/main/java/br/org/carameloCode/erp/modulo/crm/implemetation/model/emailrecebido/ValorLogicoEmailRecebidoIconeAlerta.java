package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.ICONEALERTA)
public class ValorLogicoEmailRecebidoIconeAlerta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoIconeAlerta(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEmailRecebido().isConsideradoSpan()) {
            getEmailRecebido().setIconeAlerta("fa  fa-exclamation-triangle text-danger");
        } else {
            getEmailRecebido().setIconeAlerta("");
        }//TODO implementar alertas quando email for importante.
        //exemplo de e-mail importante: email de prospecto quase fechando negocio

        return getEmailRecebido().getIconeAlerta();
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
