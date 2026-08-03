package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacaousrcomusr;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsrEntidadesDisponiveis;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValorLogicoTipoNotificacaoUsrComUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValoresLogicosTipoNotificacaoUsrComUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNotificacaoUsrComUsr(calculo = ValoresLogicosTipoNotificacaoUsrComUsr.ENTIDADESDISPONIVEIS)
public class ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrEntidadesDisponiveis
		extends
			ValorLogicoTipoNotificacaoUsrComUsrEntidadesDisponiveis {

	public ValorLogicoExtErpcaramTipoNotificacaoUsrComUsrEntidadesDisponiveis(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}