package br.org.carameloCode.erp.modulo.crm.implemetation.model.disponibilidadeatdmtpublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.time.DayOfWeek;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.DIASEMANASEGUNDA)
public class ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSegunda
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSegunda(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String diasDaSemana = getDisponibilidade().getDiasDaSemana();
        char registroDiaDaSemana = diasDaSemana.charAt(DayOfWeek.MONDAY.getValue() - 1);
        boolean diaAtivo = registroDiaDaSemana == '1';
        getDisponibilidade().setDiaSemanaSegunda(diaAtivo);
        return getDisponibilidade().isDiaSemanaSegunda();
    }

    public DisponibilidadeAtdmtPublico getDisponibilidade() {
        return (DisponibilidadeAtdmtPublico) getCampoInst().getObjetoDoAtributo();
    }
}
