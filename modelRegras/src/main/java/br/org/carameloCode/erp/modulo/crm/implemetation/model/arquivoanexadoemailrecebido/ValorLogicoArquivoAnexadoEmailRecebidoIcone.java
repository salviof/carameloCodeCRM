package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexadoemailrecebido;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemailrecebido.ValorLogicoArquivoAnexadoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemailrecebido.ValoresLogicosArquivoAnexadoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoArquivoAnexadoEmailRecebido(calculo = ValoresLogicosArquivoAnexadoEmailRecebido.ICONE)
public class ValorLogicoArquivoAnexadoEmailRecebidoIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoArquivoAnexadoEmailRecebidoIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }

}
