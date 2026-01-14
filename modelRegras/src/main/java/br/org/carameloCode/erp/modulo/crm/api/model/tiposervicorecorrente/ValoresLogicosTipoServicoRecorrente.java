package br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServicoRecorrente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServicoRecorrente.class)
public enum ValoresLogicosTipoServicoRecorrente {
	TEMINTEGRACAOFATURA, CODIGOITENGRACAOFATURA, FOIDEFINIDOTIPOPGTO
}