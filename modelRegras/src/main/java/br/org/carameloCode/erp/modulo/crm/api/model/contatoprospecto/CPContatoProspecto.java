package br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContatoProspecto.class)
public enum CPContatoProspecto {
	_ID, _NOME, _PROSPECTO, _CARGO, _CELULAR, _CELULARFORMATOINTERNACIONAL, _TELEFONEALTERNATIVO, _EMAIL, _OBSERVACAO, _UMCONTATOPRINCIPAL, _EMAILCLIENTEVERIFICADO, _TELEFONECLIENTEVERIFICADO, _DATAHORAVERIFICACAOCLIENTE, _TIPOCONTATO, _PROSPECTOSDISPONIVEIS, _LOCALIZACAOCONTATO, _CODIGOMAUTIC, _USUARIOVINCULADO, _ATIVO, _RESERVAS, _MENSAGENS, _MENSAGENSSMS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String prospecto = "prospecto";
	public static final String cargo = "cargo";
	public static final String celular = "celular";
	public static final String celularformatointernacional = "celularFormatoInternacional";
	public static final String telefonealternativo = "telefoneAlternativo";
	public static final String email = "email";
	public static final String observacao = "observacao";
	public static final String umcontatoprincipal = "umContatoPrincipal";
	public static final String emailclienteverificado = "emailClienteverificado";
	public static final String telefoneclienteverificado = "telefoneClienteverificado";
	public static final String datahoraverificacaocliente = "dataHoraVerificacaoCliente";
	public static final String tipocontato = "tipoContato";
	public static final String prospectosdisponiveis = "prospectosDisponiveis";
	public static final String localizacaocontato = "localizacaoContato";
	public static final String codigomautic = "codigoMautic";
	public static final String usuariovinculado = "usuarioVinculado";
	public static final String ativo = "ativo";
	public static final String reservas = "reservas";
	public static final String mensagens = "mensagens";
	public static final String mensagenssms = "mensagensSMS";
}