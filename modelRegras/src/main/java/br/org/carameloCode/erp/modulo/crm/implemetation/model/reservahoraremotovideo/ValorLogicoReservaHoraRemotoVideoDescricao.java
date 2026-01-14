package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValorLogicoReservaHorarioDescricao;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.DESCRICAO)
public class ValorLogicoReservaHoraRemotoVideoDescricao
        extends
        ValorLogicoReservaHorarioDescricao {

    public ValorLogicoReservaHoraRemotoVideoDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoReservaHorarioDescricao(pCampo);
    }
    private final ValorLogicoReservaHorarioDescricao valorLogico;

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
