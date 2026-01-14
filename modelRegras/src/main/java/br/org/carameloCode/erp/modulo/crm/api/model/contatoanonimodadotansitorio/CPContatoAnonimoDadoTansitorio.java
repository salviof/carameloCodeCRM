package br.org.carameloCode.erp.modulo.crm.api.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoFormLead.ContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContatoAnonimoDadoTansitorio.class)
public enum CPContatoAnonimoDadoTansitorio {
	_ID, _NOMEEMPRESA, _SITE, _NOMEUSUARIO, _CELULAR, _EMAIL, _HORARIODARESERVA, _OBSERVACAO;

	public static final String id = "id";
	public static final String nomeempresa = "nomeEmpresa";
	public static final String site = "site";
	public static final String nomeusuario = "nomeUsuario";
	public static final String celular = "celular";
	public static final String email = "email";
	public static final String horariodareserva = "horarioDaReserva";
	public static final String observacao = "observacao";
}