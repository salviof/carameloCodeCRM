package br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PesquisaAtividade.class)
public enum ValoresLogicosPesquisaAtividade {
	DESCRICAO, ORIGENSPUBLICAS, ORIGENSPRIVADAS, ORIGENSPRIVADASCOMPATILHADAS, METASDISPONIVEIS, TIPOSDERELACIONAMENTODISPONIVEL, DATAINICIAL, MOMENTOATUAL, DATAFINAL, TEXTOINTERVALODATAS, HASHULTIMAPESQUISA, USUARIOSDISPONIVEIS, ATIVIDADESENCONTRADAS
}