package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorecebida;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCrmLigacaoRecebida;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLigacaoRecebida.class)
public enum ValoresLogicosAtividadeCrmLigacaoRecebida {
	NOMEATIVIDADE, DOCUMENTOENVIADO, DOCUMENTOGERADO, FORMULARIOEXECUCAO, ACAOFORMULARIOEXECUCAO, ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, RELACIONAMENTOGERADO, ORCAMENTO, ORCAMENTOSDISPONIVEIS, POSSIETAPASRESTANTES, DEMANDAPLUGINFINALIZADA, NOESTADOINTERACAOPLUGIN, TIPOBLOQUEIO, LISTABLOQUEIOSRESTANTES, CHATBOT, POSSSUICOLETADADO, DADOSREVISADOS, PERMITIDOCONCLUIR
}