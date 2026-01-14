package br.org.carameloCode.erp.modulo.crm.implemetation.model.sistemaerpconfiavel;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValorLogicoSistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValoresLogicosSistemaERPConfiavel;

@ValorLogicoSistemaERPConfiavel(calculo = ValoresLogicosSistemaERPConfiavel.NOME)
public class ValorLogicoSistemaERPConfiavelNome
        extends
        org.coletivoJava.fw.projetos.integracao.implemetation.model.sistemaerpconfiavel.ValorLogicoSistemaERPConfiavelNome {

    public ValorLogicoSistemaERPConfiavelNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
