package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValorLogicoReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValoresLogicosReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoReservaHorario(calculo = ValoresLogicosReservaHorario.DISPONIVELPARACONFIRMACAO)
public class ValorLogicoReservaHorarioDisponivelParaConfirmacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHorarioDisponivelParaConfirmacao(
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

    public ReservaHorario getReservaHorarioEncontroPresencial() {
        return (ReservaHorario) getCampoInst().getObjetoDoAtributo();
    }
}
