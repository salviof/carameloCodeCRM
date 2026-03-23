package br.org.carameloCode.erp.modulo.crm.api.model.reservahorario;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHorario.class)
public enum ValidadoresReservaHorario {
    INICIORESERVAATENDENTE, FINALRESERVAATENDENTE, PESSOARELACIONADA
}
