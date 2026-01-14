/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util.navegador;

/**
 *
 * @author SalvioF
 */
public enum FabMetodoLocalicacaoInformacaoEmCodigo {
    TEXTO,
    PRIMEIRO_TEXTO,
    SEGUNDO_TEXTO,
    ULTIMO_TEXTO;

    public InfoLocalizadorInformacaoHtml getLocalizador(String pCampo, String pPesquisaCSS) {
        return new InfoLocalizadorInformacaoHtml(this, pCampo, pPesquisaCSS);
    }

}
