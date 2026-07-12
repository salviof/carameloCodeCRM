package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValorLogicoExtErpcaramTipoNotificacaoEntidadesDisponiveis;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ENTIDADESDISPONIVEIS)
public class ValorLogicoTiponotificacaoCRMEntidadesDisponiveis
        extends
        ValorLogicoExtErpcaramTipoNotificacaoEntidadesDisponiveis {

    public ValorLogicoTiponotificacaoCRMEntidadesDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TiponotificacaoCRM getTiponotificacaoCRM() {
        return (TiponotificacaoCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
