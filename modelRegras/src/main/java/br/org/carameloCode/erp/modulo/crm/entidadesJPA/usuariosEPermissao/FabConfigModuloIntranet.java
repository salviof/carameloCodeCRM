/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;

/**
 *
 * @author SalvioF
 */
public enum FabConfigModuloIntranet implements ItfFabConfigModulo {

    UTRL_PAGINA,
    URL_ATENDIMENTO,
    CHAVE_SECRETA_RC_USUARIO_CLIENTE,
    TIPO_APRESENTACAO;

    private FabConfigModuloIntranet() {
    }

    @Override
    public String getValorPadrao() {
        switch (this) {
            case UTRL_PAGINA:
                return "https://localhost:8080/";
            case TIPO_APRESENTACAO:
                return "WEBMAIL";
            case URL_ATENDIMENTO:
                return "https://atendimento.localhost:8080/";

            case CHAVE_SECRETA_RC_USUARIO_CLIENTE:
                return UtilCRCStringGerador.getStringRandomicaUUID();

            default:
                throw new AssertionError(this.name());

        }
    }

}
