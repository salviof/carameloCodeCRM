package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervicorecorrente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente.ValorLogicoTipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente.ValoresLogicosTipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico.ValorLogicoTipoServicoFoiDefinidoTipoPgto;

@ValorLogicoTipoServicoRecorrente(calculo = ValoresLogicosTipoServicoRecorrente.FOIDEFINIDOTIPOPGTO)
public class ValorLogicoTipoServicoRecorrenteFoiDefinidoTipoPgto
        extends
        ValorLogicoTipoServicoFoiDefinidoTipoPgto {

    public ValorLogicoTipoServicoRecorrenteFoiDefinidoTipoPgto(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
