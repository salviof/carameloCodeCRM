package br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public enum ValidadoresReservaHoraRemotoVideo {
    INICIORESERVAATENDENTE, FINALRESERVAATENDENTE, PESSOARELACIONADA
}
