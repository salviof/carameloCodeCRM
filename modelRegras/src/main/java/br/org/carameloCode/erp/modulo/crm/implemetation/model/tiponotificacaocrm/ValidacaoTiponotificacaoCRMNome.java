package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoNome;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.NOME)
public class ValidacaoTiponotificacaoCRMNome
        extends
        ValidacaoExtErpcaramTipoNotificacaoNome {

    public ValidacaoTiponotificacaoCRMNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
