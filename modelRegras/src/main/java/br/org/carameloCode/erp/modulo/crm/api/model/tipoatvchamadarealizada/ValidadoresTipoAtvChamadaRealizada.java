package br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAtvChamadaRealizada.class)
public enum ValidadoresTipoAtvChamadaRealizada {
	EXECUCAODIRETASEMRELATORIO, MODELOEMAIL, RESULTAEMRELACIONAMENTOANTERIOR
}