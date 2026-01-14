package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivocliente.ValorLogicoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivocliente.ValoresLogicosArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoArquivoCliente(calculo = ValoresLogicosArquivoCliente.ICONE)
public class ValorLogicoArquivoClienteIcone extends ValorLogicoArquivoAnexadoIcone {

    public ValorLogicoArquivoClienteIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
