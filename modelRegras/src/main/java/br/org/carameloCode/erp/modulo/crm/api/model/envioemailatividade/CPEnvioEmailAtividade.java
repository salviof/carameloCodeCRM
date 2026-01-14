package br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnvioEmailAtividade.class)
public enum CPEnvioEmailAtividade {
	_ATIVIDADE, _SUBATIVIDADEAGENDADAAPOSLEITURAEMAIL;

	public static final String atividade = "atividade";
	public static final String subatividadeagendadaaposleituraemail = "subAtividadeAgendadaAposLeituraEmail";
}