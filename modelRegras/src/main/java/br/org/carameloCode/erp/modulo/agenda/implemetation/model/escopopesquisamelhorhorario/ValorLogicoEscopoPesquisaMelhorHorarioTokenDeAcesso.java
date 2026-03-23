package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.TOKENDEACESSO)
public class ValorLogicoEscopoPesquisaMelhorHorarioTokenDeAcesso
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioTokenDeAcesso(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return getEscopo().getTokenDeAcesso();

    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
