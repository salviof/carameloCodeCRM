package br.org.carameloCode.erp.modulo.crm.api.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnvioEmail.class)
public enum ValidadoresEnvioEmail {
	CONTATOS, EMAILSOLICITANTE, ASSUNTO
}