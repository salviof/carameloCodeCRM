package br.org.carameloCode.erp.modulo.crm.implemetation.model.documentoatividadecrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.documentoatividadecrm.ValorLogicoDocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.documentoatividadecrm.ValoresLogicosDocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoDocumentoAtividadeCRM(calculo = ValoresLogicosDocumentoAtividadeCRM.ICONE)
public class ValorLogicoDocumentoAtividadeCRMIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDocumentoAtividadeCRMIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }
}
