package br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrmautoexecucao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.TipoAtividadeCRMAutoExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAtividadeCRMAutoExecucao.class)
public enum ValidadoresTipoAtividadeCRMAutoExecucao {
	EXECUCAODIRETASEMRELATORIO, MODELOEMAIL, RESULTAEMRELACIONAMENTOANTERIOR
}