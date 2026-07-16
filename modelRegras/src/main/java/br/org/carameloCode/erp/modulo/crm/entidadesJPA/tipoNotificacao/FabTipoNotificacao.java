/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoFabricaObjetos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
@InfoFabricaObjetos(manterSempreValorDaFabricaEmBanco = true)
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
    @InfoObjetoDaFabrica(id = 1000008, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente Marcou na agenda")
    NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA,
    @InfoObjetoDaFabrica(id = 1000009, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente confirmou compormisso")
    NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU,
    @InfoObjetoDaFabrica(id = 1000010, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que um novo Lead Marcou visita")
    NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA,
    @InfoObjetoDaFabrica(id = 1000011, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente cancelou compromisso")
    NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU,
    @InfoObjetoDaFabrica(id = 1000012, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar responsáveis, chamado Aberto")
    NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_OUTRO,
    @InfoObjetoDaFabrica(id = 1000013, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar responsáveis, chamado Aberto")
    NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_VOCE,
    @InfoObjetoDaFabrica(id = 1000014, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar responsáveis, chamado Aberto")
    NOTIFICACAO_RESPONSAVEIS_CHAMADO_FINALIZADO,
    @InfoObjetoDaFabrica(id = 1000015, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar solicitação arquivo Equipe")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO,
    @InfoObjetoDaFabrica(id = 1000016, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar atendimento à solicitação arquivo Equipe")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO,
    @InfoObjetoDaFabrica(id = 1000017, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar solicitação arquivo ao Cliente")
    NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE,
    @InfoObjetoDaFabrica(id = 1000018, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar solicitação confirmação ao Cliente")
    NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE;

    public TiponotificacaoCRM getRegistro() {
        TiponotificacaoCRM tipoCRM = (TiponotificacaoCRM) ComoFabricaComPersistencia.super.getRegistro();
        switch (this) {
            case NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO:

                tipoCRM.setAssunto("Chamado numero [id] foi criado");
                tipoCRM.setConteudoHTML("O chamado [id] foi aberto, você pode interagir pelo link  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ou pelo nosso numero de atendimento ");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
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
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_SOLICITAR_INTERACAO:
                tipoCRM.setAssunto("[atendenteResponsavel.nome] solicita sua interação, sobre o chamado [id]");
                tipoCRM.setConteudoHTML("Entre em contato <a href='[linkUrlAcessoCliente]' > neste link </a>:  referente ao chamado [id], sobre  [tipoChamado.nome] ");
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO:
                tipoCRM.setAssunto("Atendimento chamado [id] para [pessoa.nome], foi iniciado");
                tipoCRM.setConteudoHTML("O atendimento [id] para [pessoa.nome], iniciou  às [dataHoraPrimeiroAtendimento], sobre [tipoChamado.nome], para interagir  acesse:  [link:" + FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER.getNomeUnico() + "]");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO:
                tipoCRM.setAssunto("O Chamado [id] encerrou");
                tipoCRM.setConteudoHTML("[atendenteAnterior.nome] fechou o chamado [id], você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO:
                tipoCRM.setAssunto("O Chamado [id] encerrou");
                tipoCRM.setConteudoHTML("O chamado [id] foi finalizado, você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_ALTERACAO_HORARIO:
                tipoCRM.setAssunto("O Horário da reunião foi alterado");
                tipoCRM.setConteudoHTML("O horário da reunião foi alterado");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO:
                tipoCRM.setAssunto("Segue o link para reunião");
                tipoCRM.setConteudoHTML("Segue o link para reunião");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA:
                tipoCRM.setAssunto("[pessoaRelacionada], agendou uma reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU:
                tipoCRM.setAssunto("[pessoaRelacionada], confirmou  a reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], confirmou a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU:
                tipoCRM.setAssunto("[pessoaRelacionada], CANCELOU  a reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], CANCELOU a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA:
                tipoCRM.setAssunto("Novo Lead: [pessoaRelacionada], agendou uma reunião com você!");
                tipoCRM.setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, na sua agenda pública dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                tipoCRM.setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_OUTRO:
                tipoCRM.setAssunto("Outro colaborador assumiu o chamado [id] de [pessoa.nome]");
                tipoCRM.setConteudoHTML("O chamado [id] de [pessoa.nome], foi transferido de [atendenteAnterior.nome] para [atendenteResponsavel.nome], para aconpanhar, acesse:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_VOCE:
                tipoCRM.setAssunto("Agora é  com você, sobre o Chamado [id] de [pessoa.nome]");
                tipoCRM.setConteudoHTML(" O chamado [id] de [pessoa.nome], foi transferido de [atendenteAnterior.nome] para [atendenteResponsavel.nome], para aconpanhar, acesse:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_FINALIZADO:
                tipoCRM.setAssunto("[usuarioCliente.nome] fechou o chamado  [id]");
                tipoCRM.setConteudoHTML("[usuarioCliente.nome] fechou o chamado [id], '[titulo]', descrição: [descricao] você pode reabrir-lo pelo link:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                tipoCRM.setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO:
                //String conteudo = pSolicitacao.getUsuarioSolicitante().getNome() + " solicita um arquivo em " + pSolicitacao.getCategoriaArqEquipe().getNome()
                //+ " com a seguinte observacao: ''" + pSolicitacao.getObeservacao() + "'' atualize este arquivo clicando no link: ";
                tipoCRM.setAssunto("[usuarioSolicitante.nome] solicita um arquivo para [pessoa.nomeCurto]");
                tipoCRM.setConteudoHTML("[usuarioSolicitante.nome] solicita um arquivo em [categoriaArqEquipe.nome], com essas caracteristcas:S \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> "
                        + " <p> para o contato <h1> [pessoa.nome] </h1>"
                        + " <p>  para  fazer o upload clique em: [link:FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE] </p>");
                tipoCRM.setNomeEntidadeReferencia(SolicitacaoArquivoEquipe.class.getSimpleName());
                tipoCRM.setNotificarViaMenu(true);
                tipoCRM.setNotificarViaMatrix(true);
                tipoCRM.setNotificarViaEmail(true);
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO:
                tipoCRM.setAssunto("[usuarioSolicitado.nome] compartilhou o arquivo solicitado para [pessoa.nomeCurto]");
                tipoCRM.setConteudoHTML("[usuarioSolicitado.nome]  atendeu a sua solicitação <br/>\"\n"
                        + "                        + \"e compartilhou com você um arquivo em [categoriaArqEquipe.nome], para [pessoa.nome] acesse a pasta via: "
                        + "        <br/> <h1> <a href='[linkEquipeVerArquivo]'> ESTE LINK </a></h1> ");
                tipoCRM.setNomeEntidadeReferencia(SolicitacaoArquivoEquipe.class.getSimpleName());
                tipoCRM.setNotificarViaMenu(true);
                tipoCRM.setNotificarViaMatrix(true);
                tipoCRM.setNotificarViaEmail(true);
                break;
            case NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE:
                tipoCRM.setAssunto("[usuarioSolicitante.nome] solicita um arquivo em [categoriaArqCliente.nome] ");
                tipoCRM.setConteudoHTML("Oi, [contatoPessoa.nome], [usuarioSolicitante.nome] solicita um arquivo em [categoriaArqCliente.nome], com essas caracteristcas: \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> "
                        + " O arquivo deve ser enviado até dia <h2> [dataHoraDataProgramada] </h2> "
                        + "<p> Caso existam impedimentos para o envio do arquivo, entre em contato com <h2> [usuarioSolicitante.nome]</h2>, e estabeleça novas datas para ajustarmos nosso planejamento.</p>"
                        + " <p>  para  fazer o upload clique em: [link:FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA] </p>");
                tipoCRM.setNomeEntidadeReferencia(SolicitacaoArquivoCliente.class.getSimpleName());
                tipoCRM.setNotificarViaMenu(true);
                tipoCRM.setNotificarViaTelaDeBLoqueio(true);
                tipoCRM.setNotificarViaSMS(true);
                tipoCRM.setNotificarViaMatrix(false);
                tipoCRM.setNotificarViaEmail(true);
                break;
            case NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE:
                tipoCRM.setAssunto("[usuarioSolicitante.nome] solicita uma confirmação");
                tipoCRM.setConteudoHTML("Oi, [contatoPessoa.nome], [usuarioSolicitante.nome] Solicita uma resposta sobre essa questão: \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> </p>");
                tipoCRM.setNomeEntidadeReferencia(SolicitacaoArquivoCliente.class.getSimpleName());
                tipoCRM.setNotificarViaMenu(true);
                tipoCRM.setNotificarViaTelaDeBLoqueio(true);
                tipoCRM.setNotificarViaSMS(true);
                tipoCRM.setNotificarViaMatrix(false);
                tipoCRM.setNotificarViaEmail(true);
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
