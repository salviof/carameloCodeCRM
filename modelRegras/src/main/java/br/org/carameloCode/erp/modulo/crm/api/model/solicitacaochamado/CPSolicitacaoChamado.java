package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoChamado.class)
public enum CPSolicitacaoChamado {
	_CHAMADO;

	public static final String chamado = "chamado";
}