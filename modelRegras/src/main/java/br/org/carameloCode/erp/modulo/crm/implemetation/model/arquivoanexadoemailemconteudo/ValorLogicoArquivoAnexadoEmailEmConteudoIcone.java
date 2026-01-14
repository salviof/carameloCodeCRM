package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexadoemailemconteudo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemailemconteudo.ValorLogicoArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemailemconteudo.ValoresLogicosArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoArquivoAnexadoEmailEmConteudo(calculo = ValoresLogicosArquivoAnexadoEmailEmConteudo.ICONE)
public class ValorLogicoArquivoAnexadoEmailEmConteudoIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoArquivoAnexadoEmailEmConteudoIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }
}
