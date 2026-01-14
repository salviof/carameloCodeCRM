/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabAtividadeCRMAutoexecucao implements ComoFabrica {

    @InfoObjetoDaFabrica(classeObjeto = TipoAtividadeCRMAutoExecucao.class, id = 100001, nomeObjeto = "Envio de e-mail")
    ATIVIDADE_ENVIAR_EMAIL,
    @InfoObjetoDaFabrica(classeObjeto = TipoAtividadeCRMAutoExecucao.class, id = 100002, nomeObjeto = "Recebimento de e-mail")
    ATIVIDADE_RECEBER_EMAIL,
    @InfoObjetoDaFabrica(classeObjeto = TipoAtividadeCRMAutoExecucao.class, id = 100003, nomeObjeto = "Ligação para Lead")
    ATIVIDADE_LIGAR,
    @InfoObjetoDaFabrica(classeObjeto = TipoAtividadeCRMAutoExecucao.class, id = 100004, nomeObjeto = "Ligação de Lead")
    ATIVIDADE_RECEBER_LIGACAO,
    @InfoObjetoDaFabrica(classeObjeto = TipoAtividadeCRMAutoExecucao.class, id = 100005, nomeObjeto = "Resposta chat")
    ATIVIDADE_CHAT;

    @Override
    public TipoAtividadeCRMAutoExecucao getRegistro() {
        return (TipoAtividadeCRMAutoExecucao) ComoFabrica.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

}
