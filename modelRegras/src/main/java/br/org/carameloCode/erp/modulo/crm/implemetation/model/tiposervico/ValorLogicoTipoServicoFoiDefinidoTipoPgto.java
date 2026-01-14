package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValorLogicoTipoServico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValoresLogicosTipoServico;

@ValorLogicoTipoServico(calculo = ValoresLogicosTipoServico.FOIDEFINIDOTIPOPGTO)
public class ValorLogicoTipoServicoFoiDefinidoTipoPgto
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoServicoFoiDefinidoTipoPgto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getTipoServico().isGeraPgtoSazonal() || getTipoServico().isGeraPgtoRecorrente()) {
            getTipoServico().setFoiDefinidoTipoPgto(true);
        }

        return getTipoServico().isFoiDefinidoTipoPgto();
    }

    public TipoServico getTipoServico() {
        return (TipoServico) getCampoInst().getObjetoDoAtributo();
    }
}
