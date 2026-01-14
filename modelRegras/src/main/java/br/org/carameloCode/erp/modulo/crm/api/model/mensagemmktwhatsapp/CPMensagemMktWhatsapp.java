package br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MensagemMktWhatsapp.class)
public enum CPMensagemMktWhatsapp {
	_ID, _TIPO, _PESSOA, _CONTATO, _DATAHORA, _RECIBOENVIO, _ENVIADO, _RECEBIDO, _LIDO, _ATIVIDADE, _LINKPREVIEW;

	public static final String id = "id";
	public static final String tipo = "tipo";
	public static final String pessoa = "pessoa";
	public static final String contato = "contato";
	public static final String datahora = "dataHora";
	public static final String reciboenvio = "reciboEnvio";
	public static final String enviado = "enviado";
	public static final String recebido = "recebido";
	public static final String lido = "lido";
	public static final String atividade = "atividade";
	public static final String linkpreview = "linkPreview";
}