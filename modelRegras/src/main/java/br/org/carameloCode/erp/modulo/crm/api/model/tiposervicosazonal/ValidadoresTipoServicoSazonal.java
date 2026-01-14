package br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServicoSazonal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServicoSazonal.class)
public enum ValidadoresTipoServicoSazonal {
	GERAPGTORECORRENTE, GERAPGTOSAZONAL
}