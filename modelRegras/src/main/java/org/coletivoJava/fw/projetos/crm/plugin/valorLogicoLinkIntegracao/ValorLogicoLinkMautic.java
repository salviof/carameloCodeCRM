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
public class ValorLogicoLinkMautic extends ValorLogicoLinkPadrao {

    private final static String URLFATURA = "https://campanha.casanovadigital.com.br/s/contacts?search=company:\"";

    public ValorLogicoLinkMautic(ItfCampoInstanciado pCampoInstnciado) {
        super("Mautic", pCampoInstnciado);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder valor = new StringBuilder();
        valor.append(URLFATURA);
        if (getDadoLinkIntDinamico().getProspectoEmpresa() == null) {
            return getDadoLinkIntDinamico().getValorEnpacotado();
        }

        valor.append(getDadoLinkIntDinamico().getProspectoEmpresa().getNome()).append("\"");

        getDadoLinkIntDinamico().setValorEmpacotado(valor.toString());

        return getDadoLinkIntDinamico().getValorEnpacotado();
    }

}
