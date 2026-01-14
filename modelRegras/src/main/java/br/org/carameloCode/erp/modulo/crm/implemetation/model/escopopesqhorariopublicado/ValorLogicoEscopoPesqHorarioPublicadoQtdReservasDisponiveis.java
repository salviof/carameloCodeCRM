package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.QTDRESERVASDISPONIVEIS)
public class ValorLogicoEscopoPesqHorarioPublicadoQtdReservasDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesqHorarioPublicadoQtdReservasDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        int resultado = getEscopo().getCPinst(CPEscopoPesquisaMelhorHorario.qtdmaximoreservas).getValorComoInteger()
                - getEscopo().getCPinst(CPEscopoPesquisaMelhorHorario.qtdreservasrealizadas).getValorComoInteger();
        getEscopo().setQtdReservasDisponiveis(resultado);
        return getEscopo().getQtdReservasDisponiveis();
    }

    public EscopoPesqHorarioPublicado getEscopo() {
        return (EscopoPesqHorarioPublicado) getCampoInst().getObjetoDoAtributo();
    }

}
