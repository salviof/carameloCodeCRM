package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.DATAINICIAL)
public class ValorLogicoEscopoPesquisaMelhorHorarioDataInicial
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioDataInicial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        Date datainicial = new Date();
        if (getEscopo().getDataInicial().getTime() >= datainicial.getTime()) {
            datainicial = getEscopo().getDataInicial();
        }
        getEscopo().setDataInicial(datainicial);

        return getEscopo().getDataInicial();
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }

}
