package br.org.carameloCode.erp.modulo.crm.api.model.tipontfrcrmusrtousr;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfrCRMUsrToUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoNtfrCRMUsrToUsr.class)
public enum ValidadoresTipoNtfrCRMUsrToUsr {
	NOME, ACAOGATILHONOTIFICACAO, ACAORESPOSTAPERSONALIZADA, NOTIFICARVIAMATRIX, NOTIFICARVIAMENU, NOTIFICARVIATELADEBLOQUEIO, NOTIFICARVIAMOBILE, NOTIFICARVIAWHATSAPP, NOTIFICARVIAAPIPERSONALIZADA, NOTIFICARVIASMS, NOTIFICARVIAEMAIL, CAMINHOUSUARIODESTINATARIO, ESTRUTURAENTIDADE
}