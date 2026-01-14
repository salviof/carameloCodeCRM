/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

/**
 *
 * @author salvio
 */
public interface ItfTDadoDinamicoCRM {

    public Long getId();

    public String getDocumentoPessoa();

    public String getNome();

    public FabTipoAtributoObjeto getTipoCampo();

    public String getValor();

}
