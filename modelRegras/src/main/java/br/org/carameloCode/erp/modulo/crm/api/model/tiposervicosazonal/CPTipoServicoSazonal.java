package br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoSazonal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServicoSazonal.class)
public enum CPTipoServicoSazonal {
	_VALORSETUPMIN, _VALORSETUPMAX;

	public static final String valorsetupmin = "valorSetupMin";
	public static final String valorsetupmax = "valorSetupMax";
}