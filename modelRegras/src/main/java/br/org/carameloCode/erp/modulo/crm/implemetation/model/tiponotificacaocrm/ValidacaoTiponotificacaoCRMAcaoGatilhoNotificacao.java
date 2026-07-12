package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoAcaoGatilhoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.ACAOGATILHONOTIFICACAO)
public class ValidacaoTiponotificacaoCRMAcaoGatilhoNotificacao
        extends
        ValidacaoExtErpcaramTipoNotificacaoAcaoGatilhoNotificacao {

    public ValidacaoTiponotificacaoCRMAcaoGatilhoNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
