package br.org.carameloCode.erp.modulo.crm.api.model.modelodocumentocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloDocumentoCRM.class)
public enum CPModeloDocumentoCRM {
	_ID, _NOME, _DESCRICAO, _EXTENCAO, _DOCUMENTO, _TIPODATODINAMICO, _TIPOMODELO, _CATEGORIAARQUIVOCLIENTE, _CATEGORIAARQUIVOEQUIPE, _LEADPARATESTES;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String extencao = "extencao";
	public static final String documento = "documento";
	public static final String tipodatodinamico = "tipoDatoDinamico";
	public static final String tipomodelo = "tipoModelo";
	public static final String categoriaarquivocliente = "categoriaArquivoCliente";
	public static final String categoriaarquivoequipe = "categoriaArquivoEquipe";
	public static final String leadparatestes = "leadParaTestes";
}