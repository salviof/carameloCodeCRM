package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.notificacaosb;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.notificacaosb.ValorLogicoNotificacaoSBAssunto;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaosb.ValorLogicoNotificacaoSB;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaosb.ValoresLogicosNotificacaoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoNotificacaoSB(calculo = ValoresLogicosNotificacaoSB.ASSUNTO)
public class ValorLogicoExtErpcaramNotificacaoSBAssunto
		extends
			ValorLogicoNotificacaoSBAssunto {

	public ValorLogicoExtErpcaramNotificacaoSBAssunto(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}