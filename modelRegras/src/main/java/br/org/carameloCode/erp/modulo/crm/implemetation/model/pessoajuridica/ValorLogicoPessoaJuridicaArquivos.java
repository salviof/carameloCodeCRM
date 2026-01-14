package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaArquivos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ARQUIVOS)
public class ValorLogicoPessoaJuridicaArquivos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaArquivos arquivosPadrao;

    public ValorLogicoPessoaJuridicaArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        arquivosPadrao = new ValorLogicoPessoaArquivos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return arquivosPadrao.getValor(pEntidade);
    }
}
