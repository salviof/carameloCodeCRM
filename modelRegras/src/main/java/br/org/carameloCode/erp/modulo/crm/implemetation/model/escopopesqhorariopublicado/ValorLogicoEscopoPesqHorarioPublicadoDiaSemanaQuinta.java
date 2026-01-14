package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaQuinta;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.DIASEMANAQUINTA)
public class ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaQuinta
        extends
        ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaQuinta {

    public ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaQuinta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
