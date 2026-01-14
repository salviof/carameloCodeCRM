package br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ArquivoAnexado.class)
public enum CPArquivoAnexado {
	_ID, _NOME, _ARQUIVO, _PROSPECTO, _TIPOANEXO, _USUARIOCRIOU, _USUARIOATUALIZOU, _DATAHORACRIACAO, _DATAHORAEDICAO, _EMAILSENVIADOSCOMANEXO, _ICONE, _CATEGORIAARQEQUIPE, _SUBPASTA, _ATIVO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String arquivo = "arquivo";
	public static final String prospecto = "prospecto";
	public static final String tipoanexo = "tipoAnexo";
	public static final String usuariocriou = "usuarioCriou";
	public static final String usuarioatualizou = "usuarioAtualizou";
	public static final String datahoracriacao = "dataHoraCriacao";
	public static final String datahoraedicao = "dataHoraEdicao";
	public static final String emailsenviadoscomanexo = "emailsEnviadosComAnexo";
	public static final String icone = "icone";
	public static final String categoriaarqequipe = "categoriaArqEquipe";
	public static final String subpasta = "subPasta";
	public static final String ativo = "ativo";
}