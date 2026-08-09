/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoFabricaObjetos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
@InfoFabricaObjetos(manterSempreValorDaFabricaEmBanco = true)
public enum FabStatusChamado implements ComoFabricaStatusComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = StatusChamado.class, nomeObjeto = "Rascunho", icone = "fa-solid fa-pen-ruler", cor = "#9E9E9E", id = 1)
    RASCUNHO,
    @InfoObjetoDaFabrica(classeObjeto = StatusChamado.class, nomeObjeto = "Aguardando atendimento", icone = "fa-solid fa-hourglass-half", cor = "#FF9800", id = 2)
    AGUARDANDO_ATENDIMENTO,
    @InfoObjetoDaFabrica(classeObjeto = StatusChamado.class, nomeObjeto = "Em andamento", icone = "fa-solid fa-headset", cor = "#2196F3", id = 3)
    EM_ATENDIMENTO,
    @InfoObjetoDaFabrica(classeObjeto = StatusChamado.class, nomeObjeto = "Atrasado", icone = "fa-solid fa-triangle-exclamation", cor = "#F44336", id = 4)
    ATRAZADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusChamado.class, nomeObjeto = "Finalizado", icone = "fa-solid fa-circle-check", cor = "#4CAF50", id = 5)
    FINALIZADO;

    public static final int ID_RASCUNHO = 1;
    public static final int ID_AGUARDANDO_ATENDIMENTO = 2;
    public static final int ID_EM_ATENDIMENTO = 3;
    public static final int ID_ATRAZADO = 4;
    public static final int ID_FINALIZADO = 5;

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {

        return new ArrayList();

    }

    @Override
    public StatusChamado getRegistro() {
        return (StatusChamado) ComoFabricaStatusComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

}
