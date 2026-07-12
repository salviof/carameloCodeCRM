/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.carameloCode.erp.modulo.notificacao.controller.RepositorioComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicacaoDesktopTransient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;

/**
 *
 * @author salvio
 */
public class ServicoComunicacaoCRMTestes extends CentralComunicacaoDesktopTransient implements ComoServicoComunicacao {

    public ServicoComunicacaoCRMTestes() {

    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return pUsuario.getEmail();
    }

}
