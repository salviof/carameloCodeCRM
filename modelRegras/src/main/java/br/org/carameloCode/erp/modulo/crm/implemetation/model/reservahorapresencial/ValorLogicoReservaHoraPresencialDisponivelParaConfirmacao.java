package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValorLogicoReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValoresLogicosReservaHoraPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;

@ValorLogicoReservaHoraPresencial(calculo = ValoresLogicosReservaHoraPresencial.DISPONIVELPARACONFIRMACAO)
public class ValorLogicoReservaHoraPresencialDisponivelParaConfirmacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHoraPresencialDisponivelParaConfirmacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        Date dataHora = getReservaHorarioEncontroPresencial().getInicioReservaAtendente();

        long horasParaReuniao = UtilCRCDataHora.intervaloTempoHoras(new Date(), dataHora);

        if (horasParaReuniao < 24) {
            getReservaHorarioEncontroPresencial().setDisponivelParaConfirmacao(true);
        } else {
            getReservaHorarioEncontroPresencial().setDisponivelParaConfirmacao(false);
        }
        return getReservaHorarioEncontroPresencial().isDisponivelParaConfirmacao();
    }

    public ReservaHoraPresencial getReservaHorarioEncontroPresencial() {
        return (ReservaHoraPresencial) getCampoInst().getObjetoDoAtributo();
    }
}
