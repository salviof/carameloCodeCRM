package br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tipoNotificacao.TiponotificacaoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TiponotificacaoCRM.class)
public enum CPTiponotificacaoCRM {
	_TIPOMENSAGEM;

	public static final String tipomensagem = "tipoMensagem";
}