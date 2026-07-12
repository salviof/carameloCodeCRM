package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacao.ValorLogicoTipoNotificacaoEstruturaEntidade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacao.ValorLogicoTipoNotificacao;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacao.ValoresLogicosTipoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNotificacao(calculo = ValoresLogicosTipoNotificacao.ESTRUTURAENTIDADE)
public class ValorLogicoExtErpcaramTipoNotificacaoEstruturaEntidade
		extends
			ValorLogicoTipoNotificacaoEstruturaEntidade {

	public ValorLogicoExtErpcaramTipoNotificacaoEstruturaEntidade(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}