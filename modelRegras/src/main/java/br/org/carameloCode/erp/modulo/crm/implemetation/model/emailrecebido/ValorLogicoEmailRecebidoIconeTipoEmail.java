package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.CPEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.ICONETIPOEMAIL)
public class ValorLogicoEmailRecebidoIconeTipoEmail
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoIconeTipoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if ((boolean) getEmailRecebido().getCPinst(CPEmailRecebido.foilidoporusuariodestinatario).getValor()) {
            return "fa fa-envelope-open-o";
        }
        return "fa fa-download";
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
