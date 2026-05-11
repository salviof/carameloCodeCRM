package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValorLogicoReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValoresLogicosReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.reservahorario.ValorLogicoReservaHorarioDescricao;

@ValorLogicoReservaHoraPresencial(calculo = ValoresLogicosReservaHoraPresencial.DESCRICAO)
public class ValorLogicoReservaHoraPresencialDescricao
        extends
        ValorLogicoReservaHorarioDescricao {

    public ValorLogicoReservaHoraPresencialDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoReservaHorarioDescricao(pCampo);
    }
    private final ValorLogicoReservaHorarioDescricao valorLogico;

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
