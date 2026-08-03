package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.notificacaousrparausr;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.notificacaousrparausr.ValorLogicoNotificacaoUsrParaUsrConteudoHtml;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaousrparausr.ValorLogicoNotificacaoUsrParaUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaousrparausr.ValoresLogicosNotificacaoUsrParaUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoUsrParaUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoNotificacaoUsrParaUsr(calculo = ValoresLogicosNotificacaoUsrParaUsr.CONTEUDOHTML)
public class ValorLogicoExtErpcaramNotificacaoUsrParaUsrConteudoHtml
		extends
			ValorLogicoNotificacaoUsrParaUsrConteudoHtml {

	public ValorLogicoExtErpcaramNotificacaoUsrParaUsrConteudoHtml(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}