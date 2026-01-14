package br.org.carameloCode.erp.modulo.crm.implemetation.model.documentoapresentacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.documentoapresentacao.ValorLogicoDocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.api.model.documentoapresentacao.ValoresLogicosDocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado.ValorLogicoArquivoAnexadoIcone;

@ValorLogicoDocumentoApresentacao(calculo = ValoresLogicosDocumentoApresentacao.ICONE)
public class ValorLogicoDocumentoApresentacaoIcone
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDocumentoApresentacaoIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoArquivoAnexadoIcone(getCampoInst()).getValor();
    }

}
