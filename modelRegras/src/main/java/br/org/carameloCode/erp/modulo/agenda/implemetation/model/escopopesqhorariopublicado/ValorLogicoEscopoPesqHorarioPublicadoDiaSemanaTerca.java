package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaTerca;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.DIASEMANATERCA)
public class ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaTerca
        extends ValorLogicoEscopoPesquisaMelhorHorarioDiaSemanaTerca {

    public ValorLogicoEscopoPesqHorarioPublicadoDiaSemanaTerca(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
