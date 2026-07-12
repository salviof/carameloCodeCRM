package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValidacaoExtErpcaramTipoNotificacaoCaminhoUsuarioDestinatario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.CAMINHOUSUARIODESTINATARIO)
public class ValidacaoTiponotificacaoCRMCaminhoUsuarioDestinatario
        extends
        ValidacaoExtErpcaramTipoNotificacaoCaminhoUsuarioDestinatario {

    public ValidacaoTiponotificacaoCRMCaminhoUsuarioDestinatario(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
