package br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.time.DayOfWeek;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.DIASEMANAQUINTA)
public class ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaQuinta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaQuinta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String diasDaSemana = getDisponibilidade().getDiasDaSemana();
        char registroDiaDaSemana = diasDaSemana.charAt(DayOfWeek.THURSDAY.getValue() - 1);
        boolean diaAtivo = registroDiaDaSemana == '1';
        getDisponibilidade().setDiaSemanaQuinta(diaAtivo);
        return getDisponibilidade().isDiaSemanaQuinta();
    }

    public DisponibilidadeAtdmtPublico getDisponibilidade() {
        return (DisponibilidadeAtdmtPublico) getCampoInst().getObjetoDoAtributo();
    }
}
