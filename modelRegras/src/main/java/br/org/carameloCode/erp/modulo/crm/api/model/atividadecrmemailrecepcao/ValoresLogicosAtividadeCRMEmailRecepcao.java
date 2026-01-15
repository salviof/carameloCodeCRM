package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCRMEmailRecepcao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMEmailRecepcao.class)
public enum ValoresLogicosAtividadeCRMEmailRecepcao {
	NOMEATIVIDADE, DOCUMENTOENVIADO, DOCUMENTOGERADO, FORMULARIOEXECUCAO, ACAOFORMULARIOEXECUCAO, ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, RELACIONAMENTOGERADO, ORCAMENTO, ORCAMENTOSDISPONIVEIS, POSSIETAPASRESTANTES, DEMANDAPLUGINFINALIZADA, NOESTADOINTERACAOPLUGIN, TIPOBLOQUEIO, LISTABLOQUEIOSRESTANTES, CHATBOT, POSSSUICOLETADADO, DADOSREVISADOS, PERMITIDOCONCLUIR
}