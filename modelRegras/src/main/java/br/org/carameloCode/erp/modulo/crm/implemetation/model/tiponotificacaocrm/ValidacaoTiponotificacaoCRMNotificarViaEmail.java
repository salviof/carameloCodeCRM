package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNotificarViaEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOTIFICARVIAEMAIL)
public class ValidacaoTiponotificacaoCRMNotificarViaEmail
        extends
        ValidacaoExtErpcaramTipoNotificacaoNotificarViaEmail {

    public ValidacaoTiponotificacaoCRMNotificarViaEmail(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
