package br.org.carameloCode.erp.modulo.crm.api.model.codigoconviteagendamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConviteAgendamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CodigoConviteAgendamento.class)
public enum CPCodigoConviteAgendamento {
	_FOIAGENDADO, _TIPOATIVIDADE, _USUARIOCRM;

	public static final String foiagendado = "foiagendado";
	public static final String tipoatividade = "tipoAtividade";
	public static final String usuariocrm = "usuarioCRM";
}