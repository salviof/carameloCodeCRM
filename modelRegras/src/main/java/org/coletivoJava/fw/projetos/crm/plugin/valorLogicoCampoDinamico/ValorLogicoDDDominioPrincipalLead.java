package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValorLogicoDDDominioPrincipalLead
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDDominioPrincipalLead(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        try {
            URL url = new URL(getDadoDinamico().getProspectoEmpresa().getCPinst("site").getValor().toString());
            getDadoDinamico().setValorEmpacotado(url.getHost());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ValorLogicoDDDominioPrincipalLead.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
