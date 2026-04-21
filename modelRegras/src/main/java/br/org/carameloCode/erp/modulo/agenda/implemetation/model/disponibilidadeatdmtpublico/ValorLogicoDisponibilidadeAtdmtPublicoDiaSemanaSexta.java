package br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.time.DayOfWeek;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.DIASEMANASEXTA)
public class ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSexta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSexta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String diasDaSemana = getDisponibilidade().getDiasDaSemana();
        char registroDiaDaSemana = diasDaSemana.charAt(DayOfWeek.FRIDAY.getValue() - 1);
        boolean diaAtivo = registroDiaDaSemana == '1';
        getDisponibilidade().setDiaSemanaSexta(diaAtivo);
        return getDisponibilidade().isDiaSemanaSexta();
    }

    public DisponibilidadeAtdmtPublico getDisponibilidade() {
        return (DisponibilidadeAtdmtPublico) getCampoInst().getObjetoDoAtributo();
    }
}
