package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacaousrcomusr;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsrAcaoAutoExecucaoEntrega;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValoresLogicosTipoNotificacaoUsrComUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNotificacaoUsrComUsr(calculo = ValoresLogicosTipoNotificacaoUsrComUsr.ACAOAUTOEXECUCAOENTREGA)
public class ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrAcaoAutoExecucaoEntrega
		extends
			ValorLogicoTipoNotificacaoUsrComUsrAcaoAutoExecucaoEntrega {

	public ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrAcaoAutoExecucaoEntrega(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}