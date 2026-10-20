package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoia;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = InstrucaoIA.class)
public enum CPInstrucaoIA {
	_ID, _NOME, _TIPOINSTRUCAO, _CONTEUDO, _ATIVO, _TIPOENTIDADE, _INFORMARPLACEHOLDERSDISPONIVEIS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tipoinstrucao = "tipoInstrucao";
	public static final String conteudo = "conteudo";
	public static final String ativo = "ativo";
	public static final String tipoentidade = "tipoEntidade";
	public static final String informarplaceholdersdisponiveis = "informarPlaceHoldersDisponiveis";
}