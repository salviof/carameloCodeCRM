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
public class InfoLocalizadorInformacaoHtml {

    private final FabMetodoLocalicacaoInformacaoEmCodigo metodo;
    private final String campo;
    private final String termoPesquisaCSS;
    private boolean apenasNumeros = false;
    private boolean apenasLetras = false;
    private String regex;

    public InfoLocalizadorInformacaoHtml(FabMetodoLocalicacaoInformacaoEmCodigo metodo, String campo, String termoPesquisaCSS) {
        this.metodo = metodo;
        this.campo = campo;
        this.termoPesquisaCSS = termoPesquisaCSS;
    }

    public FabMetodoLocalicacaoInformacaoEmCodigo getMetodo() {
        return metodo;
    }

    public String getCampo() {
        return campo;
    }

    public String getTermoPesquisaCSS() {
        return termoPesquisaCSS;
    }

    public InfoLocalizadorInformacaoHtml setApenasLetras() {
        this.apenasLetras = true;
        return this;
    }

    public InfoLocalizadorInformacaoHtml setApenasNumeros() {
        this.apenasNumeros = true;
        return this;
    }

    public boolean isApenasNumeros() {
        return apenasNumeros;
    }

    public boolean isApenasLetras() {
        return apenasLetras;
    }

    public String getRegex() {
        return regex;
    }

}
