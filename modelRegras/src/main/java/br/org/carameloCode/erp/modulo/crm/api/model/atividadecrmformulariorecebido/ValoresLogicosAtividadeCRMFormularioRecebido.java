package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMFormularioRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMFormularioRecebido.class)
public enum ValoresLogicosAtividadeCRMFormularioRecebido {
	NOMEATIVIDADE, DOCUMENTOENVIADO, DOCUMENTOGERADO, FORMULARIOEXECUCAO, ACAOFORMULARIOEXECUCAO, ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, RELACIONAMENTOGERADO, ORCAMENTO, ORCAMENTOSDISPONIVEIS, POSSIETAPASRESTANTES, DEMANDAPLUGINFINALIZADA, NOESTADOINTERACAOPLUGIN, TIPOBLOQUEIO, LISTABLOQUEIOSRESTANTES, CHATBOT, POSSSUICOLETADADO, DADOSREVISADOS, PERMITIDOCONCLUIR
}