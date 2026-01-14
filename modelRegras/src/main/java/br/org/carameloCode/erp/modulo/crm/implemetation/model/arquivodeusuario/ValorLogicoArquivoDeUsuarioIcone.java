package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivodeusuario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivodeusuario.ValorLogicoArquivoDeUsuario;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivodeusuario.ValoresLogicosArquivoDeUsuario;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoArquivoDeUsuario(calculo = ValoresLogicosArquivoDeUsuario.ICONE)
public class ValorLogicoArquivoDeUsuarioIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoArquivoDeUsuarioIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }
}
