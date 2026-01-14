package br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.span.EnderecoEmailSpan;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnderecoEmailSpan.class)
public enum CPEnderecoEmailSpan {
	_ID, _EMAILREMETENTE, _USUARIORELATO, _CODIGOEMAILDEFINIDOSPAN, _BLOQUEARTODODOMINIO, _DOMINIOEMAIL, _USUARIODEFINIUSPAN, _DATAHORACADASTROSPAN, _ASSUNTODEFINIDOSPAN, _ULTIMOASSUNTO, _QUANTIDADERECEBIDA;

	public static final String id = "id";
	public static final String emailremetente = "emailRemetente";
	public static final String usuariorelato = "usuarioRelato";
	public static final String codigoemaildefinidospan = "codigoEmailDefinidoSpan";
	public static final String bloqueartododominio = "bloquearTodoDominio";
	public static final String dominioemail = "dominioEmail";
	public static final String usuariodefiniuspan = "usuarioDefiniuSpan";
	public static final String datahoracadastrospan = "dataHoraCadastroSpan";
	public static final String assuntodefinidospan = "assuntoDefinidoSpan";
	public static final String ultimoassunto = "ultimoAssunto";
	public static final String quantidaderecebida = "quantidadeRecebida";
}