package br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailRecebido.class)
public enum CPEmailRecebido {
	_REMETENTE, _DESTINATARIO, _CODIGOEMAILSERVIDOR, _DATAHORAENVIO, _DATAHORARECEBIMENTOSERVERMAIL, _CATEGORIAEMAILRECEBIDO, _ARQUIVOSRECEBIDOS, _CONTATO, _FOILIDOPORUSUARIODESTINATARIO, _FOIIGNORADO, _FOILIDOLISTAUSUARIOS, _LOGSLEITURAMAILRECEBIDO, _CONSIDERADOSPAN, _TEXTOCOMURLIMAGEMEMANEXO;

	public static final String remetente = "remetente";
	public static final String destinatario = "destinatario";
	public static final String codigoemailservidor = "codigoEmailServidor";
	public static final String datahoraenvio = "dataHoraEnvio";
	public static final String datahorarecebimentoservermail = "dataHoraRecebimentoServerMail";
	public static final String categoriaemailrecebido = "categoriaEmailRecebido";
	public static final String arquivosrecebidos = "arquivosRecebidos";
	public static final String contato = "contato";
	public static final String foilidoporusuariodestinatario = "foiLidoPorUsuarioDestinatario";
	public static final String foiignorado = "foiIgnorado";
	public static final String foilidolistausuarios = "foiLidoListaUsuarios";
	public static final String logsleituramailrecebido = "logsLeituraMailRecebido";
	public static final String consideradospan = "consideradoSpan";
	public static final String textocomurlimagememanexo = "textoComUrlImagemEmAnexo";
}