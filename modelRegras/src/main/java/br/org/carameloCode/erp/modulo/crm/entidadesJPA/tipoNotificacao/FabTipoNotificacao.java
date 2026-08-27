/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
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
    @InfoObjetoDaFabrica(id = 1000008, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente Marcou na agenda")
    NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA,
    @InfoObjetoDaFabrica(id = 1000009, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Atendimento que o Cliente confirmou compormisso")
    NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU_AGENDA,
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
    @InfoObjetoDaFabrica(id = 1000015, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificar solicitação novo arquivo Equipe")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO,
    @InfoObjetoDaFabrica(id = 1000016, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificar solicitação atualização arquivo Equipe")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ATUALIZACAO_ARQUIVO,
    @InfoObjetoDaFabrica(id = 1000017, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar envio arquivo atualizado")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ENVIO_ATUALIZACAO_ARQUIVO,
    @InfoObjetoDaFabrica(id = 1000018, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar atendimento à solicitação arquivo Equipe")
    NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO,
    @InfoObjetoDaFabrica(id = 1000019, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificar solicitação arquivo ao Cliente")
    NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE,
    @InfoObjetoDaFabrica(id = 1000020, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificar solicitação confirmação ao Cliente")
    NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE,
    @InfoObjetoDaFabrica(id = 1000021, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificar solicitação confirmação ao Cliente")
    NOTIFICACAO_SOLICITACAO_CONFIRMACAO_A_EQUIPE,
    @InfoObjetoDaFabrica(id = 1000022, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Confirmção de solicitação à equipe")
    NOTIFICAR_SOLICITACAO_CONFIRMADA_EQUIPE,
    @InfoObjetoDaFabrica(id = 1000023, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificar Confirmação de solicitação ao cliente")
    NOTIFICAR_SOLICITACAO_CONFIRMADA_CLIENTE,
    @InfoObjetoDaFabrica(id = 1000024, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificação de chamado")
    NOTIFICACAO_SOLICITACAO_EQUIPE_CHAMADO,
    @InfoObjetoDaFabrica(id = 1000025, classeObjeto = TipoNtfrCRMUsrToUsr.class, nomeObjeto = "Notificação de orçamento")
    NOTIFICACAO_SOLICITACAO_ORCAMENTO,
    @InfoObjetoDaFabrica(id = 1000026, classeObjeto = TiponotificacaoCRM.class, nomeObjeto = "Notificação de orçamento atendida")
    NOTIFICACAO_SOLICITACAO_ORCAMENTO_ATENDIDA;

    public ComoTipoComunicCRM getRegistro() {
        ComoTipoComunicCRM tipoCRM = (ComoTipoComunicCRM) ComoFabricaComPersistencia.super.getRegistro();

        switch (this) {
            case NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO:

                ((TiponotificacaoCRM) tipoCRM).setAssunto("Chamado numero [id] foi criado");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("O chamado [id] foi aberto, você pode interagir pelo link  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ou pelo nosso numero de atendimento ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[atendenteResponsavel.nome] assumiu o chamado [id] \"");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("Olá, [atendenteResponsavel.nome] assumiu o chamado Cod. [id] do dipo [tipoChamado.nome]"
                        + " você pode interajir com este chamado "
                        + "<h1><a href='[linkUrlAcessoCliente]'>CLICANDO AQUI "
                        + "</a> </h1> <br/>"
                        + "O assunto do chamado é: <h3> "
                        + "[descricao]</h3>"
                );
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_SOLICITAR_INTERACAO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[atendenteResponsavel.nome] solicita sua interação, sobre o chamado [id]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("Entre em contato <a href='[linkUrlAcessoCliente]' > neste link </a>:  referente ao chamado [id], sobre  [tipoChamado.nome] ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("Atendimento chamado [id] para [pessoa.nome], foi iniciado");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("O atendimento [id] para [pessoa.nome], iniciou  às [dataHoraPrimeiroAtendimento], sobre [tipoChamado.nome], para interagir  acesse:  [link:" + FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER.getNomeUnico() + "]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("O Chamado [id] encerrou");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendenteAnterior.nome] fechou o chamado [id], você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("O Chamado [id] encerrou");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("O chamado [id] foi finalizado, você pode reabrir-lo pelo link:  <a href='[linkUrlAcessoCliente]' > [linkUrlAcessoCliente] </a> ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_ALTERACAO_HORARIO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("O Horário da reunião foi alterado");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("O horário da reunião foi alterado");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_CLIENTE_AGENDA_REUNIAO_LINK_REUNIAO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("Segue o link para reunião");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("Segue o link para reunião");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[pessoaRelacionada], agendou uma reunião com você!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU_AGENDA:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[pessoaRelacionada], confirmou  a reunião com você!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], confirmou a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());

                break;
            case NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[pessoaRelacionada], CANCELOU  a reunião com você!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], CANCELOU a reunião, dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("Novo Lead: [pessoaRelacionada], agendou uma reunião com você!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendidoResponsavel.nome], da empresa [pessoaRelacionada], agendou uma reunião com você, na sua agenda pública dia [inicioReservaAtendente], sobre [tipoAgendamento.nome] ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ReservaHorarioCRM.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_OUTRO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("Outro colaborador assumiu o chamado [id] de [pessoa.nome]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("O chamado [id] de [pessoa.nome], foi transferido de [atendenteAnterior.nome] para [atendenteResponsavel.nome], para aconpanhar, acesse:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_VOCE:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("Agora é  com você, sobre o Chamado [id] de [pessoa.nome]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML(" O chamado [id] de [pessoa.nome], foi transferido de [atendenteAnterior.nome] para [atendenteResponsavel.nome], para aconpanhar, acesse:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_FINALIZADO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[usuarioCliente.nome] fechou o chamado  [id]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[usuarioCliente.nome] fechou o chamado [id], '[titulo]', descrição: [descricao] você pode reabrir-lo pelo link:  [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(ChamadoCliente.class.getSimpleName());
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO:
                //String conteudo = pSolicitacao.getUsuarioSolicitante().getNome() + " solicita um arquivo em " + pSolicitacao.getCategoriaArqEquipe().getNome()
                //+ " com a seguinte observacao: ''" + pSolicitacao.getObeservacao() + "'' atualize este arquivo clicando no link: ";
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita um arquivo para [pessoa.nomeCurto]");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("[usuarioSolicitante.nome] solicita um arquivo em [categoriaArqEquipe.nome], com essas caracteristcas:S \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> "
                        + " <p> para o contato <h1> [pessoa.nome] </h1>"
                        + " <p>  para  fazer o upload clique em: [link:FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE] </p>");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoArquivoEquipe.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());

                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[usuarioSolicitado.nome] compartilhou o arquivo solicitado para [pessoa.nomeCurto]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[usuarioSolicitado.nome]  atendeu a sua solicitação <br/>\"\n"
                        + "                        + \"e compartilhou com você um arquivo em [categoriaArqEquipe.nome], para [pessoa.nome] acesse a pasta via: "
                        + "        <br/> <h1> <a href='[linkEquipeVerArquivo]'> ESTE LINK </a></h1> ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(SolicitacaoArquivoEquipe.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMenu(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMatrix(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaEmail(true);

                break;
            case NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita um arquivo em [categoriaArqCliente.nome] ");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Oi, [contatoPessoa.nome], [usuarioSolicitante.nome] solicita um arquivo em [categoriaArqCliente.nome], com essas caracteristcas: \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> "
                        + " O arquivo deve ser enviado até dia <h2> [dataHoraDataProgramada] </h2> "
                        + "<p> Caso existam impedimentos para o envio do arquivo, entre em contato com <h2> [usuarioSolicitante.nome]</h2>, e estabeleça novas datas para ajustarmos nosso planejamento.</p>"
                        + " <p>  para  fazer o upload clique em: [link:FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA] </p>");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoArquivoCliente.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(false);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita uma confirmação");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Oi, [contatoPessoa.nome], [usuarioSolicitante.nome] Solicita uma resposta sobre essa questão: \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> </p>");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoArquivoCliente.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(false);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMCliente.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_CHAMADO:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita atendimento do chamado [chamado.id]");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Olá, [contatoPessoa.nome], [usuarioSolicitante.nome] Solicita sua interação em relação ao chamado [chamado.resumoDescricao], \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> </p>"
                        + "para interagir com o chamado acesse: [link:FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO]"
                        + ""
                        + "");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoArquivoCliente.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ATUALIZACAO_ARQUIVO:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita um novo [arquivo.nome]");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Olá, [usuarioSolicitado.nome], [usuarioSolicitante.nome] solicita uma nova versão do arquivo [arquivo.nome], para [pessoa.nome], "
                        + "em [arquivo.categoriaArqEquipe.nome] para atualizar clique no link [link:FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO]  ");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitArqAtualizacaoEqp.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ENVIO_ATUALIZACAO_ARQUIVO:

                ((TiponotificacaoCRM) tipoCRM).setAssunto("[usuarioSolicitado.nome] atualizou o arquivo solicitado para [pessoa.nomeCurto]");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[usuarioSolicitado.nome]  atendeu a sua solicitação <br/>\"\n"
                        + "                        + \"e atualixou com você um arquivo em [categoriaArqEquipe.nome], para [pessoa.nome] acesse a pasta via: "
                        + "        <br/> <h1> [link:" + FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_EQUIPE.getNomeUnico() + "] </h1> ");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(SolicitArqAtualizacaoEqp.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMenu(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMatrix(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaEmail(true);

                break;
            case NOTIFICACAO_SOLICITACAO_CONFIRMACAO_A_EQUIPE:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita uma confirmação");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Oi, [usuarioSolicitado.nome], [usuarioSolicitante.nome] Solicita uma resposta sobre essa questão: \" <blockquote>\n"
                        + "  <p>[observacao]</p>\n" + "</blockquote> </p>");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoConfirmacaoEquipe.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(false);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICAR_SOLICITACAO_CONFIRMADA_EQUIPE:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[usuarioSolicitado.nome], respondeu!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[usuarioSolicitado.nome], respondeu sobre [pessoa.nome], com:  [descricaoConfirmacao]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(SolicitacaoConfirmacaoEquipe.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMatrix(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMenu(true);
                break;
            case NOTIFICAR_SOLICITACAO_CONFIRMADA_CLIENTE:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[pessoaRelacionada], confirmou  a reunião com você!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[atendidoResponsavel.nome], sobre [pessoaRelacionada], respondeu [descricaoConfirmacao] , sobre [observação]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(SolicitacaoConfirmacaoCliente.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMatrix(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMenu(true);
                break;
            case NOTIFICACAO_SOLICITACAO_ORCAMENTO:
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setAssunto("[usuarioSolicitante.nome] solicita interação com orçamento");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setConteudoHTML("Oi, [usuarioSolicitado.nome], [usuarioSolicitante.nome] Solicita sua interação no orcamento [orcamento.id], de [pessoa.nome], com a seguinte observação: [observacao]");
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeEntidadeReferencia(SolicitacaoOrcamento.class.getSimpleName());
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMenu(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaTelaDeBLoqueio(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaSMS(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaMatrix(false);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNotificarViaEmail(true);
                ((TipoNtfrCRMUsrToUsr) tipoCRM).setNomeFabricaAcaoRespostaPersonalizada(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getNomeUnico());
                break;
            case NOTIFICACAO_SOLICITACAO_ORCAMENTO_ATENDIDA:
                ((TiponotificacaoCRM) tipoCRM).setAssunto("[usuarioSolicitado.nome], abriu o orçamento!");
                ((TiponotificacaoCRM) tipoCRM).setConteudoHTML("[usuarioSolicitado.nome], abriu o orçamento [orcamento.id] de [pessoa.nome]");
                ((TiponotificacaoCRM) tipoCRM).setNomeEntidadeReferencia(SolicitacaoOrcamento.class.getSimpleName());
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMatrix(true);
                ((TiponotificacaoCRM) tipoCRM).setNotificarViaMenu(true);
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
            case NOTIFICAR_ATENDENTE_CLIENTE_CONFIRMOU_AGENDA:
            case NOTIFICAR_ATENDENTE_LEAD_MARCOU_CONSULTORIA:
            case NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU:
                return ReservaHorarioCRM.class.getSimpleName();
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_OUTRO:
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_VOCE:
            case NOTIFICACAO_RESPONSAVEIS_CHAMADO_FINALIZADO:
                return ChamadoCliente.class.getSimpleName();

            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO:
                return SolicitacaoArquivoEquipe.class.getSimpleName();
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ATUALIZACAO_ARQUIVO:
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ENVIO_ATUALIZACAO_ARQUIVO:
                return SolicitArqAtualizacaoEqp.class.getSimpleName();
            case NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO:
                return SolicitacaoArquivoEquipe.class.getSimpleName();

            case NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE:
                return SolicitacaoArquivoCliente.class.getSimpleName();

            case NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE:
                return SolicitacaoConfirmacaoCliente.class.getSimpleName();

            case NOTIFICACAO_SOLICITACAO_CONFIRMACAO_A_EQUIPE:
            case NOTIFICAR_SOLICITACAO_CONFIRMADA_EQUIPE:
                return SolicitacaoConfirmacaoEquipe.class.getSimpleName();

            case NOTIFICAR_SOLICITACAO_CONFIRMADA_CLIENTE:
                return SolicitacaoConfirmacaoCliente.class.getSimpleName();
            case NOTIFICACAO_SOLICITACAO_EQUIPE_CHAMADO:
                return SolicitacaoChamado.class.getSimpleName();

            default:
                throw new AssertionError();
        }
    }

}
