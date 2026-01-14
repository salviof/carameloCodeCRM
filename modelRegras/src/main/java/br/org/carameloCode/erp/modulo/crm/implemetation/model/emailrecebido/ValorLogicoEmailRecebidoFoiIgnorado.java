package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.FOIIGNORADO)
public class ValorLogicoEmailRecebidoFoiIgnorado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoFoiIgnorado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getEmailRecebido().getDataHoraRecebimentoServerMail() == null) {
            return false;
        }
        if (getEmailRecebido().isFoiIgnorado()) {
            return true;
        }
        getEmailRecebido().setFoiIgnorado(false);
        if (!getEmailRecebido().isFoiLidoPorUsuarioDestinatario()) {
            getEmailRecebido().setFoiIgnorado(false);
            if (UtilCRCDataHora.intervaloTempoDias(getEmailRecebido().getDataHoraRecebimentoServerMail(), new Date()) > 60) {

                getEmailRecebido().setFoiIgnorado(true);
            }
        } else {
            getEmailRecebido().setFoiIgnorado(false);
        }
        return getEmailRecebido().isFoiIgnorado();
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
