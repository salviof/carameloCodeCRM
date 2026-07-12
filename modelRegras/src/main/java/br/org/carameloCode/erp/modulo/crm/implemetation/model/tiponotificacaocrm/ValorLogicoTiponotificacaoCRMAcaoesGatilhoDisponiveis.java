package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValorLogicoExtErpcaramTipoNotificacaoAcaoesGatilhoDisponiveis;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ACAOESGATILHODISPONIVEIS)
public class ValorLogicoTiponotificacaoCRMAcaoesGatilhoDisponiveis
        extends
        ValorLogicoExtErpcaramTipoNotificacaoAcaoesGatilhoDisponiveis {

    public ValorLogicoTiponotificacaoCRMAcaoesGatilhoDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TiponotificacaoCRM getTiponotificacaoCRM() {
        return (TiponotificacaoCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
