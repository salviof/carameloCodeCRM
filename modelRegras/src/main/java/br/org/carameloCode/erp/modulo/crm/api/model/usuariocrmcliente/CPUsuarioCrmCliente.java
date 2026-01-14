package br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioCrmCliente.class)
public enum CPUsuarioCrmCliente {
	_REPRESENTANTELEGAL, _CLIENTEVERIFICADO, _CONTATOCLIENTEVINCULADO, _DATAHORAPRECADASTRO, _CHAVESECRETARC, _SATISFACAO, _CHAMADOS, _METADADOS, _RESERVAATIVAMAISPROXIMA, _POSSUIRESERVAATIVA;

	public static final String representantelegal = "representanteLegal";
	public static final String clienteverificado = "clienteVerificado";
	public static final String contatoclientevinculado = "contatoClienteVinculado";
	public static final String datahoraprecadastro = "dataHoraPrecadastro";
	public static final String chavesecretarc = "chavesecretaRC";
	public static final String satisfacao = "satisfacao";
	public static final String chamados = "chamados";
	public static final String metadados = "metadados";
	public static final String reservaativamaisproxima = "reservaAtivaMaisProxima";
	public static final String possuireservaativa = "possuiReservaAtiva";
}