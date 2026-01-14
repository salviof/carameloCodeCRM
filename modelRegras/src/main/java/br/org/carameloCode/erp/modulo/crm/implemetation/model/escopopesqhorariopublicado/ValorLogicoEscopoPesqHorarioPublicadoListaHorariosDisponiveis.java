package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.LISTAHORARIOSDISPONIVEIS)
public class ValorLogicoEscopoPesqHorarioPublicadoListaHorariosDisponiveis
        extends
        ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis {

    public ValorLogicoEscopoPesqHorarioPublicadoListaHorariosDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
