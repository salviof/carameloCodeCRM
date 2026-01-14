package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaArquivos;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.ARQUIVOS)
public class ValorLogicoPessoaFisicaArquivos extends ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaArquivos arquivosPadrao;

    public ValorLogicoPessoaFisicaArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        arquivosPadrao = new ValorLogicoPessoaArquivos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return arquivosPadrao.getValor(pEntidade);
    }

}
