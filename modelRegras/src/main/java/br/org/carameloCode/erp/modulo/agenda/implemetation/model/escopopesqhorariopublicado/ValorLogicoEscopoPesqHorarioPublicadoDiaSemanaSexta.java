package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSexta;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.DIASEMANASEXTA)
public class ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaSexta
        extends
        ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaSexta {

    public ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaSexta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
