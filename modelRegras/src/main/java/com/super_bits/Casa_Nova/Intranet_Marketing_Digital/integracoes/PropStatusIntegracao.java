/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;

/**
 *
 * @author salvio
 */
public class PropStatusIntegracao {

    private String envname;
    private String nomeProp;
    private String valorPadrao;
    private String origemVALOR;
    private String parteValor;
    private TipoOrigemProp tipoPropOrigem;

    public PropStatusIntegracao(ItfFabConfigModulo pProp, ConfigModulo pConfig) {
        String valor = pConfig.getPropriedade(pProp);
        if (valor != null && valor.length() > 0) {
            parteValor = UtilCRCStringFiltros.getPrimeirasXLetrasDaString(valor, 15) + "..";
        }
        envname = ConfigModulo.getNomeCompleto(pProp);
        nomeProp = pProp.toString();
        valorPadrao = pProp.getValorPadrao();
        tipoPropOrigem = TipoOrigemProp.NAO_DEFINIDO;
        String propriedadeEnv = System.getenv(envname);
        if (propriedadeEnv == null) {
            propriedadeEnv = System.getenv(nomeProp);
            if (propriedadeEnv != null) {
            }
        }
        if (propriedadeEnv != null) {
            tipoPropOrigem = TipoOrigemProp.VARIAVEL_AMBIENTE;
        } else {

            if (pConfig.getProppriedadesBasicas().containsKey(pProp.toString())) {
                tipoPropOrigem = TipoOrigemProp.ARQUIVO_CONFIGURACAO;
            }
        }

    }

    public String getEnvname() {
        return envname;
    }

    public String getNomeProp() {
        return nomeProp;
    }

    public String getOrigemVALOR() {
        return origemVALOR;
    }

    public String getParteValor() {
        return parteValor;
    }

    public String getTipoPropOrigem() {
        return tipoPropOrigem.toString();
    }

    public String getValorPadrao() {
        return valorPadrao;
    }

    enum TipoOrigemProp {
        NAO_DEFINIDO,
        ARQUIVO_CONFIGURACAO,
        VARIAVEL_AMBIENTE,
        SERVICO_CHAVE_VALOR
    }

}
