package br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PesquisaAtividade.class)
public enum CPPesquisaAtividade {
	_ID, _DESCRICAO, _ORIGENSPUBLICAS, _ORIGENSPRIVADAS, _ORIGENSPRIVADASCOMPATILHADAS, _ORIGEM, _METASDISPONIVEIS, _TIPORELACIONAMENTO, _TIPOSDERELACIONAMENTODISPONIVEL, _DATAINICIAL, _MOMENTOATUAL, _DATAFINAL, _TEXTOINTERVALODATAS, _TERMOPESQUISA, _HASHULTIMAPESQUISA, _RELACIONAMENTO, _METARELACIONAMENTO, _USUARIO, _TAGATENDIMENTO, _USUARIOSDISPONIVEIS, _ATIVIDADESENCONTRADAS;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String origenspublicas = "origensPublicas";
	public static final String origensprivadas = "origensPrivadas";
	public static final String origensprivadascompatilhadas = "origensPrivadasCompatilhadas";
	public static final String origem = "origem";
	public static final String metasdisponiveis = "metasDisponiveis";
	public static final String tiporelacionamento = "tipoRelacionamento";
	public static final String tiposderelacionamentodisponivel = "tiposDeRelacionamentoDisponivel";
	public static final String datainicial = "datainicial";
	public static final String momentoatual = "momentoAtual";
	public static final String datafinal = "datafinal";
	public static final String textointervalodatas = "textoIntervaloDatas";
	public static final String termopesquisa = "termoPesquisa";
	public static final String hashultimapesquisa = "hashUltimaPesquisa";
	public static final String relacionamento = "relacionamento";
	public static final String metarelacionamento = "metaRelacionamento";
	public static final String usuario = "usuario";
	public static final String tagatendimento = "tagAtendimento";
	public static final String usuariosdisponiveis = "usuariosDisponiveis";
	public static final String atividadesencontradas = "atividadesEncontradas";
}