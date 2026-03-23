package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.QTDRESERVASDISPONIVEIS)
public class ValorLogicoEscopoPesquisaMelhorHorarioQtdReservasDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioQtdReservasDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEscopo().getQtdMaximoReservas() == 0) {
            setValorPorReflexao(10000);
        } else {
            int registrados = getEscopo().getCampoInstanciadoByNomeOuAnotacao(CPEscopoPesquisaMelhorHorario.qtdreservasrealizadas).getValorComoInteger();
            if (registrados >= getEscopo().getQtdMaximoReservas()) {
                int restantes = getEscopo().getQtdMaximoReservas() - registrados;
                setValorPorReflexao(restantes);
            }
        }
        return getEscopo().getQtdMaximoReservas();
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }

}
