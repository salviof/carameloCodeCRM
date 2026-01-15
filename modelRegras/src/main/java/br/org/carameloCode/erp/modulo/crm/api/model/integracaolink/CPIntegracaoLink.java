package br.org.carameloCode.erp.modulo.crm.api.model.integracaolink;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = IntegracaoLink.class)
public enum CPIntegracaoLink {
	_ID, _NOME, _IMAGEM, _CODIGOPESSOA, _TIPODADO, _URL;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String imagem = "imagem";
	public static final String codigopessoa = "codigoPessoa";
	public static final String tipodado = "tipoDado";
	public static final String url = "url";
}