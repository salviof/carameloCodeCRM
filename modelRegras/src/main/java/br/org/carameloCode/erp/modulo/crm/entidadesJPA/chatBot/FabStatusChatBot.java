/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public enum FabStatusChatBot implements ComoFabricaStatus {

    @InfoObjetoDaFabrica(id = 1, classeObjeto = StatusChatBot.class, nomeObjeto = "Enviado")
    ENVIADO,
    @InfoObjetoDaFabrica(id = 2, classeObjeto = StatusChatBot.class, nomeObjeto = "Acessado")
    ACESSADO,
    @InfoObjetoDaFabrica(id = 3, classeObjeto = StatusChatBot.class, nomeObjeto = "Finalizado")
    FINALIZADO;

    @Override
    public Object getRegistro() {
        StatusChatBot status = (StatusChatBot) ComoFabricaStatus.super.getRegistro();
        switch (this) {
            case ENVIADO:
                status.setIcone("fa fa-paper-plane-o");
                break;
            case ACESSADO:
                status.setIcone("fa fa-play");
                break;
            case FINALIZADO:
                status.setIcone("fa fa-flag-checkered");
                break;
            default:
                throw new AssertionError();
        }

        return status;
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        return new ArrayList<>();
    }
}
