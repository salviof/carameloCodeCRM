package br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoRecorrente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServicoRecorrente.class)
public enum CPTipoServicoRecorrente {
	_VALORMENSALMIN, _VALORMENSALMAX;

	public static final String valormensalmin = "valorMensalMin";
	public static final String valormensalmax = "valorMensalMax";
}