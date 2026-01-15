package br.org.carameloCode.erp.modulo.crm.api.model.tiposervico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServico.class)
public enum ValoresLogicosTipoServico {
	TEMINTEGRACAOFATURA, CODIGOITENGRACAOFATURA, FOIDEFINIDOTIPOPGTO
}