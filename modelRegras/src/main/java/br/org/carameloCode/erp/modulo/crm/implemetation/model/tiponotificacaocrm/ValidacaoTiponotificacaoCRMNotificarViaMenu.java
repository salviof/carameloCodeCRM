package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNotificarViaMenu;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOTIFICARVIAMENU)
public class ValidacaoTiponotificacaoCRMNotificarViaMenu
        extends
        ValidacaoExtErpcaramTipoNotificacaoNotificarViaMenu {

    public ValidacaoTiponotificacaoCRMNotificarViaMenu(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
