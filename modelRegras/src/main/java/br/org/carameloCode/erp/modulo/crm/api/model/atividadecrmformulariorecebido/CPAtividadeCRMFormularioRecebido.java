package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMFormularioRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMFormularioRecebido.class)
public enum CPAtividadeCRMFormularioRecebido {
	_CODIGOCHAT, _CODIGORESPOSTA;

	public static final String codigochat = "codigoChat";
	public static final String codigoresposta = "codigoResposta";
}