package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervicosazonal;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValorLogicoTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValoresLogicosTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico.ValorLogicoTipoServicoFoiDefinidoTipoPgto;

@ValorLogicoTipoServicoSazonal(calculo = ValoresLogicosTipoServicoSazonal.FOIDEFINIDOTIPOPGTO)
public class ValorLogicoTipoServicoSazonalFoiDefinidoTipoPgto
        extends
        ValorLogicoTipoServicoFoiDefinidoTipoPgto {

    public ValorLogicoTipoServicoSazonalFoiDefinidoTipoPgto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
