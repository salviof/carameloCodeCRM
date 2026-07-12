package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValorLogicoExtErpcaramTipoNotificacaoAcaoGatilhoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ACAOGATILHONOTIFICACAO)
public class ValorLogicoTiponotificacaoCRMAcaoGatilhoNotificacao
        extends
        ValorLogicoExtErpcaramTipoNotificacaoAcaoGatilhoNotificacao {

    public ValorLogicoTiponotificacaoCRMAcaoGatilhoNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TiponotificacaoCRM getTiponotificacaoCRM() {
        return (TiponotificacaoCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
