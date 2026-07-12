package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNotificarViaSMS;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOTIFICARVIASMS)
public class ValidacaoTiponotificacaoCRMNotificarViaSMS
        extends
        ValidacaoExtErpcaramTipoNotificacaoNotificarViaSMS {

    public ValidacaoTiponotificacaoCRMNotificarViaSMS(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
