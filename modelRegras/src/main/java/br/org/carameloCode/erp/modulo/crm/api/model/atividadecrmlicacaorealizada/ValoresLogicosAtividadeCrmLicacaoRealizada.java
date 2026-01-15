package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmlicacaorealizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCrmLicacaoRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLicacaoRealizada.class)
public enum ValoresLogicosAtividadeCrmLicacaoRealizada {
	NOMEATIVIDADE, DOCUMENTOENVIADO, DOCUMENTOGERADO, FORMULARIOEXECUCAO, ACAOFORMULARIOEXECUCAO, ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, RELACIONAMENTOGERADO, ORCAMENTO, ORCAMENTOSDISPONIVEIS, POSSIETAPASRESTANTES, DEMANDAPLUGINFINALIZADA, NOESTADOINTERACAOPLUGIN, TIPOBLOQUEIO, LISTABLOQUEIOSRESTANTES, CHATBOT, POSSSUICOLETADADO, DADOSREVISADOS, PERMITIDOCONCLUIR
}