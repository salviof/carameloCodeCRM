/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
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
    NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO,
    @InfoObjetoDaFabrica(id = 1000007, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente Marcou na agenda")
    NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA,
    @InfoObjetoDaFabrica(id = 1000007, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente confirmou compormisso")
    NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU,
    @InfoObjetoDaFabrica(id = 1000007, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que um novo Lead Marcou visita")
    NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA,
    @InfoObjetoDaFabrica(id = 1000007, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente cancelou compromisso")
    NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU;

    public TiponotificacaoCRM getRegistro() {
        TiponotificacaoCRM tipoCRM = (TiponotificacaoCRM) ComoFabricaComPersistencia.super.getRegistro();
        switch (this) {
            case NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO:

                tipoCRM.setAssunto("Chamado numero [id] foi criado");
                tipoCRM.setConteudoHTML("O chamado [id] foi aberto, você pode interagir pelo link  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ou pelo nosso numero de atendimento ");
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO:
                tipoCRM.setAssunto("[atendenteResponsavel.nome] assumiu o chamado [id] \"");
                tipoCRM.setConteudoHTML("Olá, [atendenteResponsavel.nome] assumiu o chamado Cod. [id] do dipo [tipoChamado.nome]"
                        + " você pode interajir com este chamado "
                        + "<h1><a href='[linkUrlAcessoCliente]'>CLICANDO AQUI "
                        + "</a> </h1> <br/>"
                        + "O assunto do chamado é: <h3> "
                        + "[descricao]</h3>"
                );

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
            case NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA:
                tipoCRM.setAssunto("[pessoaRelacionada], agendou uma reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");

                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU:
                tipoCRM.setAssunto("[pessoaRelacionada], confirmou  a reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], confirmou a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU:
                tipoCRM.setAssunto("[pessoaRelacionada], CANCELOU  a reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], CANCELOU a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                break;
            case NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA:
                tipoCRM.setAssunto("Novo Lead: [pessoaRelacionada], agendou uma reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, na sua agenda pública dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                break;

            default:
                throw new AssertionError();
        }
        return tipoCRM;
    }

    public String getEntidade() {
        switch (this) {

            case NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO:
            case NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO:
            case NOTIFICACAO_CLIENTE_CHAMADO_SOLICITAR_INTERACAO:
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO:
            case NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO:
                return ChamadoCliente.class.getSimpleName();
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO:
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_ALTERACAO_HORARIO:
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO:
            case NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA:
            case NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU:
            case NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA:
            case NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU:
                return ReservaHorarioCRM.class.getSimpleName();
            default:
                throw new AssertionError();
        }
    }

}
