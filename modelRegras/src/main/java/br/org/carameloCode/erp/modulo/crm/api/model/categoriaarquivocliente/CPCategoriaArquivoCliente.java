package br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CategoriaArquivoCliente.class)
public enum CPCategoriaArquivoCliente {
	_ID, _NOME, _ICONE, _COMPARTILHARCOMCONVIDADOS, _PASTAPAI, _SUBPASTAS, _SUBPASTASPRIVADAS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
	public static final String compartilharcomconvidados = "compartilharComConvidados";
	public static final String pastapai = "pastaPai";
	public static final String subpastas = "subPastas";
	public static final String subpastasprivadas = "subpastasPrivadas";
}