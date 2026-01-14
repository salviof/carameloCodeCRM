package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.time.DayOfWeek;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.DIASEMANASEGUNDA)
public class ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSegunda
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSegunda(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String diasDaSemana = getEscopoPesquisaMelhorHorario().getDiasDaSemana();
        char registroDiaDaSemana = diasDaSemana.charAt(DayOfWeek.MONDAY.getValue() - 1);
        boolean diaAtivo = registroDiaDaSemana == '1';
        getEscopoPesquisaMelhorHorario().setDiaSemanaSegunda(diaAtivo);
        return getEscopoPesquisaMelhorHorario().isDiaSemanaSegunda();
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
