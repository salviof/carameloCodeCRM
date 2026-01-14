/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.erp.client;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author salvio
 */
public enum FabConfigModuloERPCRMClient implements ItfFabConfigModulo {

    DOMINIO_CRM,
    HASH_CHAVE_PUBLICA_CRM,
    USUARIO_ADMIN_CRM;

    @Override
    public String getValorPadrao() {
        return "Não definido";
    }

}
