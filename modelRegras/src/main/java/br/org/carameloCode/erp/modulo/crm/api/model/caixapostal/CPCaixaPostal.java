package br.org.carameloCode.erp.modulo.crm.api.model.caixapostal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CaixaPostal.class)
public enum CPCaixaPostal {
	_ID, _USUARIORECEPCAO, _USUARIOSMTP, _SENHASMTP, _SENHARECEPCAO, _PORTARECEPCAO, _PORTASMTP, _USARSSLRECEPCAO, _DATAHORAULTIMOEMAILRECEBIDO, _ENDERECOSERVIDOR, _USARSSLSMTP, _USARTSLSMTP, _USUARIOS, _ESTATISTICAS, _ATIVO, _EMAILREMETENTE, _NOMEREMETENTE;

	public static final String id = "id";
	public static final String usuariorecepcao = "usuarioRecepcao";
	public static final String usuariosmtp = "usuarioSMTP";
	public static final String senhasmtp = "senhaSMTP";
	public static final String senharecepcao = "senhaRecepcao";
	public static final String portarecepcao = "portaRecepcao";
	public static final String portasmtp = "portaSMTP";
	public static final String usarsslrecepcao = "usarSSLRecepcao";
	public static final String datahoraultimoemailrecebido = "dataHoraUltimoEmailRecebido";
	public static final String enderecoservidor = "enderecoServidor";
	public static final String usarsslsmtp = "usarSSLSMTP";
	public static final String usartslsmtp = "usarTSLSMTP";
	public static final String usuarios = "usuarios";
	public static final String estatisticas = "estatisticas";
	public static final String ativo = "ativo";
	public static final String emailremetente = "emailRemetente";
	public static final String nomeremetente = "nomeRemetente";
}