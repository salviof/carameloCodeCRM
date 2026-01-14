package br.org.carameloCode.erp.modulo.crm.implemetation.model.subpastaequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValorLogicoSubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValoresLogicosSubPastaEquipe;

@ValorLogicoSubPastaEquipe(calculo = ValoresLogicosSubPastaEquipe.PASTAPUBLICA)
public class ValorLogicoSubPastaEquipePastaPublica
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSubPastaEquipePastaPublica(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return null;
    }
}
