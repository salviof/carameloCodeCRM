package br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraPresencial;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraPresencial.class)
public enum ValidadoresReservaHorarioEncontroPresencial {
    ATENDIMENTOOUTSIDE, PESSOARELACIONADA
}
