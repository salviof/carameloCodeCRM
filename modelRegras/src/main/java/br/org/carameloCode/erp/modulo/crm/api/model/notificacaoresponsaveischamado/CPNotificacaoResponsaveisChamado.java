package br.org.carameloCode.erp.modulo.crm.api.model.notificacaoresponsaveischamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.NotificacaoResponsaveisChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = NotificacaoResponsaveisChamado.class)
public enum CPNotificacaoResponsaveisChamado {
	_ID, _CHAMADO, _DATAHORANOTIFICACAO;

	public static final String id = "id";
	public static final String chamado = "chamado";
	public static final String datahoranotificacao = "dataHoraNotificacao";
}