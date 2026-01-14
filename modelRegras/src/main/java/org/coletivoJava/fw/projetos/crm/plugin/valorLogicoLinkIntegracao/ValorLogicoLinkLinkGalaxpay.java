/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

/**
 *
 * @author salvio
 */
public class ValorLogicoLinkLinkGalaxpay extends ValorLogicoLinkPadrao {

    private final static String URLFATURA_INICIO = "https://crm.casanovadigital.com.br/Prospectos";
    private final static String URLFATURA_FIM = "/ac-Infografico_da_pessoa.html";

    public ValorLogicoLinkLinkGalaxpay(ItfCampoInstanciado pCampoInstnciado) {
        super("GalaxPay",
                pCampoInstnciado);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getDadoLinkIntDinamico().getProspectoEmpresa() == null) {
            return getDadoLinkIntDinamico().getValorEnpacotado();
        }
        String url = "https://www.gpay.com.br/landing9900";

        getDadoLinkIntDinamico().setValorEmpacotado(url);

        return getDadoLinkIntDinamico().getValorEnpacotado();
    }

}
