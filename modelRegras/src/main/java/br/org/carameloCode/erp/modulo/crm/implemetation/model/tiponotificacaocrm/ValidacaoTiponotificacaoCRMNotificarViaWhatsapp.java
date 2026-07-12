package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNotificarViaWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOTIFICARVIAWHATSAPP)
public class ValidacaoTiponotificacaoCRMNotificarViaWhatsapp
        extends
        ValidacaoExtErpcaramTipoNotificacaoNotificarViaWhatsapp {

    public ValidacaoTiponotificacaoCRMNotificarViaWhatsapp(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
