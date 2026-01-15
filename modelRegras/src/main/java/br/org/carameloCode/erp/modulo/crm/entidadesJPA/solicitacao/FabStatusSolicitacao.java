/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabStatusSolicitacao implements ComoFabricaComPersistencia {
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 1, nomeObjeto = "Solicitado")
    SOLICITADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 2, nomeObjeto = "Atrazaodo")
    ATRAZADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 3, nomeObjeto = "Finalizado")
    FINALIZADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusSolicitacao.class, id = 4, nomeObjeto = "Recusado")
    RECUSADO;

    @Override
    public StatusSolicitacao getRegistro() {
        StatusSolicitacao status = (StatusSolicitacao) ComoFabricaComPersistencia.super.getRegistro();

        switch (this) {

            case SOLICITADO:
                status.setIcone("fa fa-question-circle");
                break;
            case ATRAZADO:
                status.setIcone("fa fa-hourglass-end");
                break;
            case FINALIZADO:
                status.setIcone("fa fa-check-circle-o");
                break;
            case RECUSADO:
                status.setIcone("fa fa-times");
                break;
            default:
                throw new AssertionError();
        }

        return status;
    }

}
