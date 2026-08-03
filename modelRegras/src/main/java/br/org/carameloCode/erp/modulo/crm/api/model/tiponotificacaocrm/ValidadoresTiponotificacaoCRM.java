package br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TiponotificacaoCRM.class)
public enum ValidadoresTiponotificacaoCRM {
	NOME, ACAOGATILHONOTIFICACAO, ACAORESPOSTAPERSONALIZADA, NOTIFICARVIAMATRIX, NOTIFICARVIAMENU, NOTIFICARVIATELADEBLOQUEIO, NOTIFICARVIAMOBILE, NOTIFICARVIAWHATSAPP, NOTIFICARVIAAPIPERSONALIZADA, NOTIFICARVIASMS, NOTIFICARVIAEMAIL, CAMINHOUSUARIODESTINATARIO, ESTRUTURAENTIDADE
}