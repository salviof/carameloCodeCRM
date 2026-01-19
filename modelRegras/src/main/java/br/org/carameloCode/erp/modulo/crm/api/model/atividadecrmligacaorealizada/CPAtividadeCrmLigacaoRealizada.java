package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorealizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCrmLigacaoRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLigacaoRealizada.class)
public enum CPAtividadeCrmLigacaoRealizada {
	_TELEFONEVOIP, _CONTATOPROSPECTO, _AUDIOVOIP;

	public static final String telefonevoip = "telefoneVoip";
	public static final String contatoprospecto = "contatoProspecto";
	public static final String audiovoip = "audioVoip";
}