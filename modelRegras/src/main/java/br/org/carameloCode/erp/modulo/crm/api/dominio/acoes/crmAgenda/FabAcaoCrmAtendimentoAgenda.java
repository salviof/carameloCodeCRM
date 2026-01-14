/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoaFisica.ContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.sms.MensagemSMS;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.CPMensagemMktWhatsapp;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author sfurbino
 */
@InfoModulosCRM(modulo = FabModulosCRM.ATENDIMENTO_CRM)
public enum FabAcaoCrmAtendimentoAgenda implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = ReservaHorario.class, icone = "fa fa-ticket", utilizarMesmoFormEdicao = false)
    MINHA_AGENDA_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Escopo Reservas de Clientes")
    MINHA_AGENDA_FRM_ESCOPO_RESERVA_CLIENTE,
    @InfoTipoAcaoController(icone = "fa floppy-o", nomeAcao = "Salvar Alterações", entidade = EscopoPesquisaMelhorHorario.class)
    MINHA_AGENDA_CTR_ESCOPO_RESERVA_CLIENTE_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(icone = "fa fa-calendar", nomeAcao = "Reservas")
    MINHA_AGENDA_FRM_VISAO_GERAL,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Nova Reseva")
    MINHA_AGENDA_FRM_NOVA_RESERVA,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Detalhes da reserva", campos = {}, entidade = ReservaHoraPresencial.class)
    MINHA_AGENDA_FRM_EDITAR_RESERVA_NO_LOCAL,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Detalhes da reserva", campos = {}, entidade = ReservaHoraRemotoVideo.class)
    MINHA_AGENDA_FRM_EDITAR_RESERVA_CONFERENCIA,
    @InfoTipoAcaoController(icone = "fa fa-ticket", nomeAcao = "Reservar")
    MINHA_AGENDA_CTR_SALVAR_RESERVA_MERGE,
    @InfoTipoAcaoFormulario(icone = "fa fa-video-camera ", nomeAcao = "Definir url da reunião")
    MINHA_AGENDA_FRM_DEFINIR_URL_REUNIAO,
    @InfoTipoAcaoController(icone = "fa fa-ban", nomeAcao = "Remover agendamento")
    MINHA_AGENDA_CTR_REMOVER,
    @InfoTipoAcaoController(icone = "fa fa-share-alt  ", nomeAcao = "Enviar Lik Conferência online")
    MINHA_AGENDA_CTR_ENVIAR_LINK_REUNIAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-calendar-o", nomeAcao = "Ver Reserva")
    MINHA_AGENDA_FRM_VER_RESERVA,
    @InfoTipoAcaoFormulario(icone = "fa fa-share-square-o", nomeAcao = "Propor reserva ao cliente")
    MINHA_AGENDA_FRM_OFERTAR_RESERVA,
    @InfoTipoAcaoController(icone = "fa fa-share-square-o")
    MINHA_AGENDA_CTR_OFERTAR_RESERVA,
    @InfoTipoAcaoController(icone = "fa fa-bell-o")
    MINHA_AGENDA_CTR_NOTIFICAR_OFERTA,
    @InfoTipoAcaoController(icone = "fa fa-bell-o")
    MINHA_AGENDA_CTR_NOTIFICAR_COMPROMISSO,
    @InfoTipoAcaoFormulario(icone = "fa fa-calendar-plus-o")
    MINHA_AGENDA_FRM_AGENDAR_NOVO,
    @InfoTipoAcaoController(icone = "fa fa-calendar-plus-o", nomeAcao = "Agendar agora")
    MINHA_AGENDA_CTR_AGENDAR,
    @InfoTipoAcaoGestaoEntidade(entidade = ContatoPessoaFisica.class, precisaPermissao = true)
    MEUS_CONTATOS_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-whatsapp", nomeAcao = "Enviar Mensagem de Whatsapp")
    MEUS_CONTATOS_FRM_ENVIAR_MKT_WTZAP,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Compartilhar agenda pública")
    MEUS_CONTATOS_FRM_CONVITE_RESERVA,
    @InfoTipoAcaoFormulario(icone = "fa fa-key", nomeAcao = "Convidar para Área do Cliente")
    MEUS_CONTATOS_FRM_CONVITE_PRIMEIRO_ACESSO,
    @InfoTipoAcaoController(icone = "fa fa-envelope-o", nomeAcao = "Convidar por e-mail")
    MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_EMAIL,
    @InfoTipoAcaoController(icone = "fa fa-whatsapp", nomeAcao = "Convidar por whatsapp")
    MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_WHATSAPP,
    @InfoTipoAcaoController(icone = "fa fa-envelope-o", nomeAcao = "Convidar agenda por email")
    MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_EMAIL,
    @InfoTipoAcaoController(icone = "fa fa-whatsapp", nomeAcao = "Convidar agenda por whatsapp")
    MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_WHATSAPP,
    @InfoTipoAcaoController(icone = "fa fa-whatsapp", nomeAcao = "Convidar acesso por whatsapp")
    MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_WHATSAPP,
    @InfoTipoAcaoController(icone = "fa fa-envelope-o", nomeAcao = "Convidar acesso por email")
    MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_EMAIL,
    MEUS_CONTATOS_FRM_LISTAR,
    MEUS_CONTATOS_FRM_EDITAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-paper-plane-o", nomeAcao = "Mensagem SMS")
    MEUS_CONTATOS_FRM_SMS_FORMATAR,
    @InfoTipoAcaoController(icone = "fa fa-paper-plane-o", nomeAcao = "Enviar SMS Agora", entidade = MensagemSMS.class)
    MEUS_CONTATOS_CTR_SMS_ENVIAR,
    @InfoTipoAcaoController(icone = "fa fa-mobile", nomeAcao = "Ligar pelo meu celular")
    MEUS_CONTATOS_CTR_LIGAR_PELO_CELULAR,
    @InfoTipoAcaoController(icone = "fa fa-whatsapp", nomeAcao = "Abrir chat whatsaoo")
    MEUS_CONTATOS_CTR_ABRIR_CHAT_WHATSAPP,
    @InfoTipoAcaoController(icone = "fa fa-comments-o", nomeAcao = "Convidar para CHAT")
    MEUS_CONTATOS_CTR_CONVIDAR_PARA_CHAT,
    @InfoTipoAcaoController(icone = "fa fa-phone", nomeAcao = "Ligar pelo PABX Online")
    MEUS_CONTATOS_CTR_LIGAR_PABX_ONLINE,
    @InfoTipoAcaoGestaoEntidade(entidade = MensagemMktWhatsapp.class, icone = "fa fa-whatsapp")
    MENSAGEM_MKT_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPMensagemMktWhatsapp.pessoa, "contato", CPMensagemMktWhatsapp.tipo})
    MENSAGEM_MKT_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {CPMensagemMktWhatsapp.pessoa, "contato", CPMensagemMktWhatsapp.tipo})
    MENSAGEM_MKT_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {CPMensagemMktWhatsapp.pessoa, "contato", CPMensagemMktWhatsapp.tipo})
    MENSAGEM_MKT_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-check-circle-o")
    MENSAGEM_MKT_FRM_ENVIO_SUCESSO,
    @InfoTipoAcaoController(nomeAcao = "Enviar Mensagem", icone = "fa fa-paper-plane-o")
    MENSAGEM_MKT_CTR_ENVIAR,
    @InfoTipoAcaoController(nomeAcao = "Enviar Mensagem", icone = "fa  fa-bullhorn")
    MENSAGEM_MKT_CTR_ENVIAR_TODOS_CONTATOS,

}
