package br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoRelacionamento.class)
public enum CPTipoRelacionamento {
	_ID, _NOME, _DESCRICAO, _NOMEDORELACIONADO, _DICAS, _DADOSNESCESSARIOS, _TIPOSATIVIDADEDISPONIVEIS, _ATIVIDADES, _TOTALATIVIDADES, _TOTALATIVIDADESPORUSUARIO, _PESO, _COR, _RESULTADO, _METARELACIONAMENTO, _USUARIOSRESPONSAVEIS, _RESPONSABILIDADEEXCLUSIVA, _ADICIONARTODOSRESPONSAVEIS, _TEMPOACEITAVELRESOLUCAO, _TEMPOACAOINERCIARELACIONAMENTO, _RELACIONAMENTOPERANTEINERCIA, _QTDEMPRESASNESTETIPORELACIONAMENTO, _TIPORELACIONAMENTODESTINO, _PESSOASCOMESTERELACIONAMENTO, _TIPOMENSAGEMWTZP, _TIPORELACIONAMENTOCONVERSAO, _ATIVIDADESDOWNGRADEMETA, _ATIVIDADESUPGRADEMETA, _PROXIMOSRESPONSAVEIS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String nomedorelacionado = "nomeDoRelacionado";
	public static final String dicas = "dicas";
	public static final String dadosnescessarios = "dadosNescessarios";
	public static final String tiposatividadedisponiveis = "tiposAtividadeDisponiveis";
	public static final String atividades = "atividades";
	public static final String totalatividades = "totalAtividades";
	public static final String totalatividadesporusuario = "totalAtividadesPorUsuario";
	public static final String peso = "peso";
	public static final String cor = "cor";
	public static final String resultado = "resultado";
	public static final String metarelacionamento = "metaRelacionamento";
	public static final String usuariosresponsaveis = "usuariosResponsaveis";
	public static final String responsabilidadeexclusiva = "responsabilidadeexclusiva";
	public static final String adicionartodosresponsaveis = "adicionarTodosResponsaveis";
	public static final String tempoaceitavelresolucao = "tempoAceitavelResolucao";
	public static final String tempoacaoinerciarelacionamento = "tempoAcaoInerciaRelacionamento";
	public static final String relacionamentoperanteinercia = "relacionamentoPeranteInercia";
	public static final String qtdempresasnestetiporelacionamento = "qtdEmpresasNesteTipoRelacionamento";
	public static final String tiporelacionamentodestino = "tipoRelacionamentoDestino";
	public static final String pessoascomesterelacionamento = "pessoasComEsteRelacionamento";
	public static final String tipomensagemwtzp = "tipoMensagemWtzp";
	public static final String tiporelacionamentoconversao = "tipoRelacionamentoConversao";
	public static final String atividadesdowngrademeta = "atividadesDownGradeMeta";
	public static final String atividadesupgrademeta = "atividadesUpGradeMeta";
	public static final String proximosresponsaveis = "proximosResponsaveis";
}