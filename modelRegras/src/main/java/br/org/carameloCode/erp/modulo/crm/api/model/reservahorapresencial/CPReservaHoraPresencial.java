package br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraPresencial.class)
public enum CPReservaHoraPresencial {
    _LOCALIZACAO, _TEXTOLOCALIZACAO, _ATENDIMENTOOUTSIDE;

    public static final String localizacao = "localizacao";
    public static final String textolocalizacao = "textoLocalizacao";
    public static final String atendimentooutside = "atendimentoOutSide";
}
