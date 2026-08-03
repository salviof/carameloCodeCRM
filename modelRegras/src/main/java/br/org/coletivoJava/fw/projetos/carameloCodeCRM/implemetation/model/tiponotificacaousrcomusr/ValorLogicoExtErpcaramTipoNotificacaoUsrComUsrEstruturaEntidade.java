package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacaousrcomusr;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsrEstruturaEntidade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValoresLogicosTipoNotificacaoUsrComUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNotificacaoUsrComUsr(calculo = ValoresLogicosTipoNotificacaoUsrComUsr.ESTRUTURAENTIDADE)
public class ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrEstruturaEntidade
		extends
			ValorLogicoTipoNotificacaoUsrComUsrEstruturaEntidade {

	public ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrEstruturaEntidade(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}