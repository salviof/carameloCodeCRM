package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ACAORESPOSTAPERSONALIZADA)
public class ValorLogicoTiponotificacaoCRMAcaoRespostaPersonalizada
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTiponotificacaoCRMAcaoRespostaPersonalizada(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TiponotificacaoCRM getTiponotificacaoCRM() {
		return (TiponotificacaoCRM) getCampoInst().getObjetoRaizDoAtributo();
	}
}