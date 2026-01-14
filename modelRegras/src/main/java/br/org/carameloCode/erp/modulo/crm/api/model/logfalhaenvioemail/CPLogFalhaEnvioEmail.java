package br.org.carameloCode.erp.modulo.crm.api.model.logfalhaenvioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.LogFalhaEnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = LogFalhaEnvioEmail.class)
public enum CPLogFalhaEnvioEmail {
	_ID, _TIPOLOG, _MENSAGEMRETORNO, _CABECALHOMENSAGEMRETORNO, _OBERVACOES, _MENSAGEMAOUSUARIO, _EMAIL;

	public static final String id = "id";
	public static final String tipolog = "tipoLog";
	public static final String mensagemretorno = "mensagemRetorno";
	public static final String cabecalhomensagemretorno = "cabecalhoMensagemRetorno";
	public static final String obervacoes = "obervacoes";
	public static final String mensagemaousuario = "mensagemAoUsuario";
	public static final String email = "email";
}