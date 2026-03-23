package br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraPresencial.class)
public enum ValidadoresReservaHoraPresencial {
    ATENDIMENTOOUTSIDE, INICIORESERVAATENDENTE, FINALRESERVAATENDENTE, PESSOARELACIONADA
}
