package br.org.carameloCode.erp.modulo.crm.api.model.codigoconvite;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.codigoAcesso.CodigoConvite;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CodigoConvite.class)
public enum CPCodigoConvite {
	_CODIGO, _NOME, _ASSUNTO, _CONTEUDO, _CONTATO, _TIPOCODIGO;

	public static final String codigo = "codigo";
	public static final String nome = "nome";
	public static final String assunto = "assunto";
	public static final String conteudo = "conteudo";
	public static final String contato = "contato";
	public static final String tipocodigo = "tipoCodigo";
}