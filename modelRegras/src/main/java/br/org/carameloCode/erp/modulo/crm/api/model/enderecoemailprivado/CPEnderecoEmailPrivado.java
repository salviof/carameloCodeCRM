package br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailprivado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span.EnderecoEmailPrivado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnderecoEmailPrivado.class)
public enum CPEnderecoEmailPrivado {
	_ID, _CAIXAPOSTAL, _EMAILORIGEM, _USUARIORELATO, _EMAIL;

	public static final String id = "id";
	public static final String caixapostal = "caixaPostal";
	public static final String emailorigem = "emailOrigem";
	public static final String usuariorelato = "usuarioRelato";
	public static final String email = "email";
}