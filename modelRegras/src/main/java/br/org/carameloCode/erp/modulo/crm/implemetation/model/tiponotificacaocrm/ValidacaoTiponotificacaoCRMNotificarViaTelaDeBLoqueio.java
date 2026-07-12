package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNotificarViaTelaDeBLoqueio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOTIFICARVIATELADEBLOQUEIO)
public class ValidacaoTiponotificacaoCRMNotificarViaTelaDeBLoqueio
        extends
        ValidacaoExtErpcaramTipoNotificacaoNotificarViaTelaDeBLoqueio {

    public ValidacaoTiponotificacaoCRMNotificarViaTelaDeBLoqueio(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
