package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.persona;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Persona.class)
public enum CPPersona {
	_ID, _NOME, _TIPOIA, _IMAGEMAVATARPERSONA, _DESCRICAO, _TONALIDADE, _TIPOENTIDADE, _OBJETIVO, _REGRASRESPOSTA, _LIMITEPALAVRAS, _CONTEXTO, _INSTRUCOESADICIONAIS, _PUBLICOALVO, _IDIOMA, _TIPORESPOSTASPADRAO, _PALAVRASPROIBIDAS, _ATIVO, _TIPOPERSONA, _INSTRUCOESCOMPARTILHADAS, _TEXTOMODELFILEIA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tipoia = "tipoIA";
	public static final String imagemavatarpersona = "imagemAvatarPersona";
	public static final String descricao = "descricao";
	public static final String tonalidade = "tonalidade";
	public static final String tipoentidade = "tipoEntidade";
	public static final String objetivo = "objetivo";
	public static final String regrasresposta = "regrasResposta";
	public static final String limitepalavras = "limitePalavras";
	public static final String contexto = "contexto";
	public static final String instrucoesadicionais = "instrucoesAdicionais";
	public static final String publicoalvo = "publicoAlvo";
	public static final String idioma = "idioma";
	public static final String tiporespostaspadrao = "tipoRespostasPadrao";
	public static final String palavrasproibidas = "palavrasProibidas";
	public static final String ativo = "ativo";
	public static final String tipopersona = "tipoPersona";
	public static final String instrucoescompartilhadas = "instrucoesCompartilhadas";
	public static final String textomodelfileia = "textoModelFileIA";
}