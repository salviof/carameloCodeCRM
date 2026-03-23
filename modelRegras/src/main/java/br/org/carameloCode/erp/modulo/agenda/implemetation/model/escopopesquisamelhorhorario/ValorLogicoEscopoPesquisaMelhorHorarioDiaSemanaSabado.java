package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.time.DayOfWeek;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.DIASEMANASABADO)
public class ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSabado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSabado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String diasDaSemana = getEscopoPesquisaMelhorHorario().getDiasDaSemana();
        char registroDiaDaSemana = diasDaSemana.charAt(DayOfWeek.SATURDAY.getValue() - 1);
        boolean diaAtivo = registroDiaDaSemana == '1';
        getEscopoPesquisaMelhorHorario().setDiaSemanaSabado(diaAtivo);
        return getEscopoPesquisaMelhorHorario().isDiaSemanaSabado();
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
