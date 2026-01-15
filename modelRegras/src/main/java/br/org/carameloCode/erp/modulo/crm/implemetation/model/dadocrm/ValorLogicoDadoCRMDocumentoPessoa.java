package br.org.carameloCode.erp.modulo.crm.implemetation.model.dadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.dadocrm.ValorLogicoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.dadocrm.ValoresLogicosDadoCRM;

@ValorLogicoDadoCRM(calculo = ValoresLogicosDadoCRM.DOCUMENTOPESSOA)
public class ValorLogicoDadoCRMDocumentoPessoa
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDadoCRMDocumentoPessoa(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getDado().getProspectoEmpresa() != null) {
            getDado().setDocumentoPessoa(getDado().getProspectoEmpresa().getDocumento());
        }

        return getDado().getDocumentoPessoa();
    }

    public DadoCRM getDado() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
