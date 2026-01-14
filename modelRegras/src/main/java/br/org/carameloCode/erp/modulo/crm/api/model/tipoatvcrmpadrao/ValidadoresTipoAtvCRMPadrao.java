package br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtvCRMPadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAtvCRMPadrao.class)
public enum ValidadoresTipoAtvCRMPadrao {
	EXECUCAODIRETASEMRELATORIO, MODELOEMAIL, RESULTAEMRELACIONAMENTOANTERIOR
}