package br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public enum ValidadoresReservaHoraRemotoVideo {
	PESSOARELACIONADA, INICIORESERVAATENDENTE, FINALRESERVAATENDENTE
}