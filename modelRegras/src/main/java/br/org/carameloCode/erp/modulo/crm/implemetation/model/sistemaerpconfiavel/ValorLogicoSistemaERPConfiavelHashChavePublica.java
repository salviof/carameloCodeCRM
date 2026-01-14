package br.org.carameloCode.erp.modulo.crm.implemetation.model.sistemaerpconfiavel;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValorLogicoSistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValoresLogicosSistemaERPConfiavel;

@ValorLogicoSistemaERPConfiavel(calculo = ValoresLogicosSistemaERPConfiavel.HASHCHAVEPUBLICA)
public class ValorLogicoSistemaERPConfiavelHashChavePublica
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSistemaERPConfiavelHashChavePublica(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!UtilCRCStringValidador.isNuloOuEmbranco(getSistema().getChavePublica())) {
            getSistema().setHashChavePublica(String.valueOf(getSistema().getChavePublica().hashCode()));
        }

        return getSistema().getHashChavePublica();
    }

    public SistemaERPConfiavel getSistema() {
        return (SistemaERPConfiavel) getCampoInst().getObjetoDoAtributo();
    }

}
