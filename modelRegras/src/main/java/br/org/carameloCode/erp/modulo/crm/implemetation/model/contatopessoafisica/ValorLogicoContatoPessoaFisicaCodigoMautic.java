package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValorLogicoContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValoresLogicosContatoPessoaFisica;

@ValorLogicoContatoPessoaFisica(calculo = ValoresLogicosContatoPessoaFisica.CODIGOMAUTIC)
public class ValorLogicoContatoPessoaFisicaCodigoMautic
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoPessoaFisicaCodigoMautic(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
