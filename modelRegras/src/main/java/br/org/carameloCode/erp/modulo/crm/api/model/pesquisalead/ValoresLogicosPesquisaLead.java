package br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PesquisaLead.class)
public enum ValoresLogicosPesquisaLead {
	DESCRICAO, ORIGENSPUBLICAS, ORIGENSPRIVADAS, ORIGENSPRIVADASCOMPATILHADAS, METASDISPONIVEIS, TIPOSDERELACIONAMENTODISPONIVEL, DATAINICIAL, MOMENTOATUAL, DATAFINAL, TEXTOINTERVALODATAS, TIPOPESQUISA, TIPOSDISPONIVEIS, HASHULTIMAPESQUISA, TAGSDISPONIVEIS, USUARIOSDISPONIVEIS, QUANTIDADELEADSURGENTES, LEADSENCONTRADOS
}