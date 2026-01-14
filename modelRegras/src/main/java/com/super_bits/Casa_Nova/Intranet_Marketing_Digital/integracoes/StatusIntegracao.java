/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ComoFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = {"Status integração"}, plural = "Status de integração")
public class StatusIntegracao extends EntidadeSimplesORM {

    public StatusIntegracao() {
    }

    private List<PropStatusIntegracao> propriedasdes = new ArrayList<>();

    public StatusIntegracao(ComoFabricaIntegracaoRest api) {
        integracao = api;
        nomeIntegracao = integracao.getDadosIntegracao().nomeIntegracao();
        ItfTokenGestao gestaoToken = null;

        try {
            gestaoToken = integracao.getGestaoToken();

        } catch (Throwable t) {
            diagnostico = t.getMessage();
        }
        if (gestaoToken != null) {
            try {
//                integracao.getGestaoToken().validarToken();
                //              integracao.getGestaoToken(SBCore.getUsuarioLogado()).validarToken();
            } catch (Throwable t) {

            }
            sistemaFoiIntegrado = integracao.getGestaoToken().isTemTokemAtivo();
            usuarioFoiIntegrado = integracao.getGestaoToken(SBCore.getUsuarioLogado()).isTemTokemAtivo();
            diagnostico = integracao.getGestaoToken().getStatusToken().toString();
            id = (long) nomeIntegracao.hashCode();

        } else {
            sistemaFoiIntegrado = false;
            usuarioFoiIntegrado = false;
            diagnostico = "Erro configurando integrador" + diagnostico;
            id = (long) nomeIntegracao.hashCode();
        }
        caminhoArquivoConfig = api.getConfiguracao().getPatchArquivoConfig();

        Class classeConfig = api.getConfiguracao().getFabricaConfig();
        ConfigModulo config = api.getConfiguracao();
        for (Object prop : classeConfig.getEnumConstants()) {

            ItfFabConfigModulo propConfig = (ItfFabConfigModulo) prop;
            PropStatusIntegracao propriedade = new PropStatusIntegracao(propConfig, config);
            propriedasdes.add(propriedade);

        }

    }

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeIntegracao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean sistemaFoiIntegrado;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean usuarioFoiIntegrado;
    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_PEQUENA)
    private String icone;

    private String diagnostico;

    private String caminhoArquivoConfig;

    private ComoFabricaIntegracaoRest integracao;

    public boolean isSistemaFoiIntegrado() {
        return sistemaFoiIntegrado;
    }

    public void setSistemaFoiIntegrado(boolean sistemaFoiIntegrado) {
        this.sistemaFoiIntegrado = sistemaFoiIntegrado;
    }

    public String getNomeIntegracao() {
        return nomeIntegracao;
    }

    public void setNomeIntegracao(String nomeIntegracao) {
        this.nomeIntegracao = nomeIntegracao;
    }

    public boolean isUsuarioFoiIntegrado() {
        return usuarioFoiIntegrado;
    }

    public void setUsuarioFoiIntegrado(boolean usuarioFoiIntegrado) {
        this.usuarioFoiIntegrado = usuarioFoiIntegrado;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public ComoFabricaIntegracaoRest getIntegracao() {
        return integracao;
    }

    public void setIntegracao(ComoFabricaIntegracaoRest integracao) {
        this.integracao = integracao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getCaminhoArquivoConfig() {
        return caminhoArquivoConfig;
    }

    public List<PropStatusIntegracao> getPropriedasdes() {
        return propriedasdes;
    }

}
