package br.org.carameloCode.erp.modulo.crm.api.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailCrm.class)
public enum ValidadoresEmailCrm {
	EMAILSOLICITANTE, ASSUNTO
}