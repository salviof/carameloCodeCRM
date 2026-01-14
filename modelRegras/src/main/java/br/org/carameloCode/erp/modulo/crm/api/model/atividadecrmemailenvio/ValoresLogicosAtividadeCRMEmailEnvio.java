package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailenvio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMEmailEnvio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMEmailEnvio.class)
public enum ValoresLogicosAtividadeCRMEmailEnvio {
	NOMEATIVIDADE, DOCUMENTOENVIADO, DOCUMENTOGERADO, FORMULARIOEXECUCAO, ACAOFORMULARIOEXECUCAO, ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, RELACIONAMENTOGERADO, ORCAMENTO, ORCAMENTOSDISPONIVEIS, POSSIETAPASRESTANTES, DEMANDAPLUGINFINALIZADA, NOESTADOINTERACAOPLUGIN, TIPOBLOQUEIO, LISTABLOQUEIOSRESTANTES, CHATBOT, POSSSUICOLETADADO, DADOSREVISADOS, PERMITIDOCONCLUIR
}