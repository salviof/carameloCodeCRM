/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabTipoNotificacao implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(id = 1000000, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Cliente chamado em atendimento ")
    NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO,
    @InfoObjetoDaFabrica(id = 1000001, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Clientes sobre abertura de chamado")
    NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO,
    @InfoObjetoDaFabrica(id = 1000002, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notifiicar Cliente, interação sob chamado")
    NOTIFICACAO_CLIENTE_CHAMADO_SOLICITAR_INTERACAO,
    @InfoObjetoDaFabrica(id = 1000003, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar responsáveis, chamado Aberto")
    NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO,
    @InfoObjetoDaFabrica(id = 1000004, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar cliente, chamado finalizado")
    NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO,
    @InfoObjetoDaFabrica(id = 1000005, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar cliente Agendamento agenda criada")
    NOTIFICAR_CLIENTE_AGENDA_REUNIAO,
    @InfoObjetoDaFabrica(id = 1000006, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar cliente Agendamento alteração horario")
    NOTIFICAR_CLIENTE_AGENDA_REUNIAO_ALTERACAO_HORARIO,
    @InfoObjetoDaFabrica(id = 1000007, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar cliente agendamento link reunião")
    NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO;

    public TiponotificacaoCRM getRegistro() {
        TiponotificacaoCRM tipoCRM = (TiponotificacaoCRM) ComoFabricaComPersistencia.super.getRegistro();
        switch (this) {
            case NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO:

                tipoCRM.setAssunto("Chamado numero [id] foi criado, para ");
                tipoCRM.setConteudoHTML("O chamado [id] foi aberto, você pode interagir pelo link  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO:
                tipoCRM.setAssunto("[pessoa.nome] abriu um chamado cod [id]");
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_SOLICITAR_INTERACAO:
                tipoCRM.setAssunto("[atendenteResponsavel.nome] solicita sua interação, sobre o chamado [id]");
                tipoCRM.setConteudoHTML("Entre em contato <a href='[linkUrlAcessoCliente]' > neste link </a>:  referente ao chamado [id], sobre  [tipoChamado.nome] ");
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO:
                tipoCRM.setAssunto("Atendimento chamado [id] para [pessoa.nome], foi iniciado");
                tipoCRM.setConteudoHTML("O atendimento [id] para [pessoa.nome], iniciou  às [dataHoraPrimeiroAtendimento], sobre [tipoChamado.nome], para interagir  acesse:  [link:" + FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER.getNomeUnico() + "]");
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO:
                tipoCRM.setAssunto("O Chamado [id] encerrou");
                tipoCRM.setConteudoHTML("O chamado [id] foi finalizado, você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO:
                tipoCRM.setAssunto("O Chamado [id] encerrou");
                tipoCRM.setConteudoHTML("O chamado [id] foi finalizado, você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_ALTERACAO_HORARIO:
                tipoCRM.setAssunto("O Horário da reunião foi alterado");
                tipoCRM.setConteudoHTML("O horário da reunião foi alterado");
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO:
                tipoCRM.setAssunto("Segue o link para reunião");
                tipoCRM.setConteudoHTML("Segue o link para reunião");
                break;

            default:
                throw new AssertionError();
        }
        return tipoCRM;
    }

}
