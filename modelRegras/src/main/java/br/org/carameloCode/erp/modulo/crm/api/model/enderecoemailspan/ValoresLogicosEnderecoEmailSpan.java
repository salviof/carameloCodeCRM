package br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.span.EnderecoEmailSpan;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnderecoEmailSpan.class)
public enum ValoresLogicosEnderecoEmailSpan {
	BLOQUEARTODODOMINIO, DOMINIOEMAIL
}