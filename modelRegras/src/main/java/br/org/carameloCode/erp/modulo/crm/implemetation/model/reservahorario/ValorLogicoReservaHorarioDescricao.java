package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValorLogicoReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValoresLogicosReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoReservaHorario(calculo = ValoresLogicosReservaHorario.DESCRICAO)
public class ValorLogicoReservaHorarioDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHorarioDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String texto = getReserva().getTipoAgendamento().getNome() + " com " + getReserva().getAtendenteResponsavel().getNome() + " código: "
                + getReserva().getId();
        getReserva().setDescricao(texto);
        return getReserva().getDescricao();
    }

    public ReservaHorario getReserva() {
        return (ReservaHorario) getCampoInst().getObjetoDoAtributo();
    }
}
