package br.org.carameloCode.erp.modulo.crm.api.model.envioemailrascunhoautosave;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmailRascunhoAutoSave;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnvioEmailRascunhoAutoSave.class)
public enum CPEnvioEmailRascunhoAutoSave {
	_ID, _DATAHORA, _PROSPECTO, _USUARIO, _EMAILVINCULADO, _TEXTO, _ASSUNTO;

	public static final String id = "id";
	public static final String datahora = "dataHora";
	public static final String prospecto = "prospecto";
	public static final String usuario = "usuario";
	public static final String emailvinculado = "emailVinculado";
	public static final String texto = "texto";
	public static final String assunto = "assunto";
}