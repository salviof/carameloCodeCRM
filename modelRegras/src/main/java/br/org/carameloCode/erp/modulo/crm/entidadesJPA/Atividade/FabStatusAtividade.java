/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabStatusAtividade implements ComoFabrica {

    @InfoObjetoDaFabrica(classeObjeto = StatusAtividade.class, nomeObjeto = "Em andamento", id = FabStatusAtividade.idEmAndamento)
    EM_ANDAMENTO,
    @InfoObjetoDaFabrica(classeObjeto = StatusAtividade.class, nomeObjeto = "Sucesso", id = FabStatusAtividade.idConcluidaComSucesso)
    CONCLUIDA_COM_SUCESSSO,
    @InfoObjetoDaFabrica(classeObjeto = StatusAtividade.class, nomeObjeto = "Agendado", id = FabStatusAtividade.idAgendado)
    AGENDADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusAtividade.class, nomeObjeto = "Cancelado", id = 4)
    CANCELADA;

    @Override
    public StatusAtividade getRegistro() {
        return (StatusAtividade) ComoFabrica.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

    public final static int idEmAndamento = 1;
    public final static int idConcluidaComSucesso = 2;
    public final static int idAgendado = 3;

}
