package br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailRecebido.class)
public enum ValidadoresEmailRecebido {
	EMAILSOLICITANTE, ASSUNTO
}