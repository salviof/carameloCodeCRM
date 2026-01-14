/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.integracoes.modelController.socialAutenticador;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author SalvioF
 */
public enum FabTipoAutenticacaoSocial implements ComoFabrica {

    @InfoObjetoDaFabrica(id = 1, nomeObjeto = "facebook", classeObjeto = TipoCredencialSocial.class)
    FACEBOOK,
    @InfoObjetoDaFabrica(id = 2, nomeObjeto = "github", classeObjeto = TipoCredencialSocial.class)
    GITHUB,
    @InfoObjetoDaFabrica(id = 2, nomeObjeto = "googleColetivoJava", classeObjeto = TipoCredencialSocial.class)
    GOOGLE;

    public static FabTipoAutenticacaoSocial getFabricaPorString(String pString) {
        for (Enum fab : FabTipoAutenticacaoSocial.class.getEnumConstants()) {
            FabTipoAutenticacaoSocial tipo = (FabTipoAutenticacaoSocial) fab;
            if (pString.toLowerCase().equals(tipo.getStrIdentificacao().toLowerCase())) {
                return tipo;
            }
        }
        return null;
    }

    @Override
    public TipoCredencialSocial getRegistro() {
        return (TipoCredencialSocial) ComoFabrica.super.getRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public String getStrIdentificacao() {
        return ((ComoEntidadeSimples) getRegistro()).getNome();
    }

}
