package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexadoemail;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemail.ValorLogicoArquivoAnexadoEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemail.ValoresLogicosArquivoAnexadoEmail;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoArquivoAnexadoEmail(calculo = ValoresLogicosArquivoAnexadoEmail.ICONE)
public class ValorLogicoArquivoAnexadoEmailIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoArquivoAnexadoEmailIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }

}
