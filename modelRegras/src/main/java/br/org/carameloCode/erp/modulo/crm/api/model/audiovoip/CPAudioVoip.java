package br.org.carameloCode.erp.modulo.crm.api.model.audiovoip;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.AudioVoip;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AudioVoip.class)
public enum CPAudioVoip {
	_ID, _UNIQUEID, _ARQUIVO;

	public static final String id = "id";
	public static final String uniqueid = "uniqueId";
	public static final String arquivo = "arquivo";
}