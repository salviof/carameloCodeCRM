package br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoIA.class)
public enum CPTipoDadoIA {
	_PROVEDORIA, _MODEL, _TEMPERATURA, _TOP_P, _MAXIMOTOKENS, _TIMEOUT, _PRESENCE_PENALTY, _FREQUENCY_PENALTY, _CONTEXT_POLICY, _FALLBACK_MODEL, _MONTHLY_TOKEN_LIMIT, _ATIVO, _CONTEUDOPERGUNTAIA, _CONTEUDOALTERNATIVOCASOFALHA, _TIPODADOLOGICACASOFALHA;

	public static final String provedoria = "provedorIA";
	public static final String model = "model";
	public static final String temperatura = "temperatura";
	public static final String top_p = "top_p";
	public static final String maximotokens = "maximoTokens";
	public static final String timeout = "timeout";
	public static final String presence_penalty = "presence_penalty";
	public static final String frequency_penalty = "frequency_penalty";
	public static final String context_policy = "context_policy";
	public static final String fallback_model = "fallback_model";
	public static final String monthly_token_limit = "monthly_token_limit";
	public static final String ativo = "ativo";
	public static final String conteudoperguntaia = "conteudoPerguntaIA";
	public static final String conteudoalternativocasofalha = "conteudoAlternativoCasoFalha";
	public static final String tipodadologicacasofalha = "tipoDadoLogicaCasoFalha";
}