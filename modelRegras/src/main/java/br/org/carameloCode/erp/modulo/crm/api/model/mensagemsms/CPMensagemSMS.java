package br.org.carameloCode.erp.modulo.crm.api.model.mensagemsms;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.sms.MensagemSMS;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MensagemSMS.class)
public enum CPMensagemSMS {
	_ID, _TEXTO, _CONTATO, _DATAHORACRIACAO, _DATAHORAAGENDAMENTO, _DATAHORAENVIO, _ENVIADO;

	public static final String id = "id";
	public static final String texto = "texto";
	public static final String contato = "contato";
	public static final String datahoracriacao = "dataHoraCriacao";
	public static final String datahoraagendamento = "dataHoraAgendamento";
	public static final String datahoraenvio = "dataHoraEnvio";
	public static final String enviado = "enviado";
}