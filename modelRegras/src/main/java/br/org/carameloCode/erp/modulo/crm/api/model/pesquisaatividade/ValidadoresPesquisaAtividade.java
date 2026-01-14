package br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PesquisaAtividade.class)
public enum ValidadoresPesquisaAtividade {
	ORIGEM, DATAINICIAL, DATAFINAL, USUARIO
}