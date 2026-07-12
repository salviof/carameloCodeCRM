package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao.ValorLogicoExtErpcaramTipoNotificacaoAcaoAutoExecucaoEntrega;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ACAOAUTOEXECUCAOENTREGA)
public class ValorLogicoTiponotificacaoCRMAcaoAutoExecucaoEntrega
        extends
        ValorLogicoExtErpcaramTipoNotificacaoAcaoAutoExecucaoEntrega {

    public ValorLogicoTiponotificacaoCRMAcaoAutoExecucaoEntrega(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TiponotificacaoCRM getTiponotificacaoCRM() {
        return (TiponotificacaoCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
