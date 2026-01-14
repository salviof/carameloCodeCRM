/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

/**
 *
 * @author salvio
 */
public class ValorLogicoLinkFatura extends ValorLogicoLinkPadrao {

    public ValorLogicoLinkFatura(ItfCampoInstanciado pCampoInstnciado) {
        super("Fatura", pCampoInstnciado);
    }
    private final static String URLFATURA = "https://fatura.casanovadigital.com.br/Redirect/ac-Redirecionamento_pagina_Cliente.html";

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder valor = new StringBuilder();
        valor.append(URLFATURA);
        if (getDadoLinkIntDinamico().getProspectoEmpresa() == null) {
            return getDadoLinkIntDinamico().getValorEnpacotado();
        }
        String documento = getDadoLinkIntDinamico().getProspectoEmpresa().getCPinst(CPPessoa.documento).getValorTextoFormatado();
        if (UtilCRCStringValidador.isNuloOuEmbranco(documento)) {
            getDadoLinkIntDinamico().setValorEmpacotado(null);
        } else {

            valor.append("?documento=");
            valor.append(UtilCRCStringFiltros.filtrarApenasNumeros(documento));
            String link = valor.toString();
            getDadoLinkIntDinamico().setValorEmpacotado(link);
        }
        return getDadoLinkIntDinamico().getValorEnpacotado();
    }

}
