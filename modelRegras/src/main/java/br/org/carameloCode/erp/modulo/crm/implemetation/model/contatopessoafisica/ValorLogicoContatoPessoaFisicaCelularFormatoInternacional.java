package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValorLogicoContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValoresLogicosContatoPessoaFisica;

@ValorLogicoContatoPessoaFisica(calculo = ValoresLogicosContatoPessoaFisica.CELULARFORMATOINTERNACIONAL)
public class ValorLogicoContatoPessoaFisicaCelularFormatoInternacional
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoPessoaFisicaCelularFormatoInternacional(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
