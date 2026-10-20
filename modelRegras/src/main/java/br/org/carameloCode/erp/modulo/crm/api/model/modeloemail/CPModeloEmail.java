package br.org.carameloCode.erp.modulo.crm.api.model.modeloemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloEmail.class)
public enum CPModeloEmail {
	_ID, _NOME, _ASSUNTO, _TIPOMODELO, _DESCRICAO, _TEXTOMODELO, _USUARIOCRIOU, _USUARIOEDITOU, _DATAHORACRIOU, _DATAHORAEDITOU, _EDITARCOMIA, _PERSONACOAUTORIA, _INSTRUCOESIAFORMATACAOEMAIL, _HISTORICOALTERACOES, _INSTRUCAOCOMPLETAIA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String assunto = "assunto";
	public static final String tipomodelo = "tipoModelo";
	public static final String descricao = "descricao";
	public static final String textomodelo = "textoModelo";
	public static final String usuariocriou = "usuariocriou";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String datahoracriou = "dataHoraCriou";
	public static final String datahoraeditou = "dataHoraEditou";
	public static final String editarcomia = "editarComIA";
	public static final String personacoautoria = "personaCoautoria";
	public static final String instrucoesiaformatacaoemail = "instrucoesIAFormatacaoEmail";
	public static final String historicoalteracoes = "historicoAlteracoes";
	public static final String instrucaocompletaia = "instrucaoCompletaIA";
}