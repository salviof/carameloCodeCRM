/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.acoesAutomatizadas.FabTipoAutoExecucaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author sfurbino
 */
@InfoModulosCRM(modulo = FabModulosCRM.CLIENTE_CONTATO)
public enum FabAcaoCRMCliente implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-life-ring", entidade = ChamadoCliente.class, utilizarMesmoFormEdicao = false, precisaPermissao = true)
    CHAMADO_MB_GESTAO,
    @InfoTipoAcaoFormulario()
    CHAMADO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Tipo de chamado]", "tipoChamado"}, nomeAcao = "NOVO CHAMADO")
    CHAMADO_FRM_NOVO,
    @InfoTipoAcaoFormulario(icone = "fa fa-life-ring", campos = {"[separador: Dados do Chamado]", "id", "titulo", "status", "atendenteResponsavel", "descricao"})
    CHAMADO_FRM_VISUALIZAR,
    @InfoTipoAcaoController(icone = "fa fa-save", nomeAcao = "Salvar Rascunho")
    CHAMADO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Notificar Chamado aberto", entidade = UsuarioCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR, autoExecucao = FabTipoAutoExecucaoAcao.HORAS_A_CADA_1)
    CHAMADO_CTR_NOTIFICAR_CHAMADOS_ABERTOS_AUTO_EXEC,
    @InfoTipoAcaoController(icone = "fa fa-bell-o", nomeAcao = "Notificar Responsáveis")
    CHAMADO_CTR_NOTIFICAR_RESONSAVEIS,
    @InfoTipoAcaoController(icone = "fa fa-check-circle-o", nomeAcao = "Fechar chamado")
    CHAMADO_CTR_FINALIZAR,
    @InfoTipoAcaoController(icone = "fa fa-life-ring", nomeAcao = "Abrir chamado")
    CHAMADO_CTR_ABRIR_CHAMADO,
    @InfoTipoAcaoController(icone = "fa fa-ban")
    CHAMADO_CTR_CANCELAR,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-ticket", entidade = ReservaHorario.class, precisaPermissao = true)
    RESERVAS_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-ban")
    RESERVAS_FRM_SEM_ATENDIMENTO,
    @InfoTipoAcaoFormulario(icone = "fa fa-users")
    RESERVAS_FRM_ESCOLHA_ATENDENTE,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket")
    RESERVAS_FRM_LISTAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-clock-o")
    RESERVAS_FRM_HORARIOS_DISPONIVEIS,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket")
    RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", nomeAcao = "Reserva")
    RESERVAS_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", entidade = ReservaHorario.class, nomeAcao = "Reserva horário remoto")
    RESERVAS_FRM_VISUALIZAR_REMOTO,
    @InfoTipoAcaoFormulario(icone = "fa fa-ticket", entidade = ReservaHoraPresencial.class)
    RESERVAS_FRM_VISUALIZAR_PRESENCIAL,
    @InfoTipoAcaoController(icone = "fa fa-calendar-check-o")
    RESERVAS_CTR_RESERVAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-ban")
    RESERVAS_FRM_CANCELAR,
    @InfoTipoAcaoController(icone = "fa fa-ban")
    RESERVAS_CTR_CANCELAR,
    @InfoTipoAcaoController(icone = "fa fa-check")
    RESERVAS_CTR_CONFIRMAR,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-tachometer", entidade = UsuarioCrmCliente.class, precisaPermissao = true)
    DASHBOARD_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-address-card-o", entidade = UsuarioCrmCliente.class, precisaPermissao = true)
    DASHBOARD_FRM_MEUS_DADOS,
    @InfoTipoAcaoController(icone = "fa fa-star-o", nomeAcao = "Muito satisfeito")
    DASHBOARD_CTR_MUITO_SATISFEITO,
    @InfoTipoAcaoController(icone = "fa fa-thumbs-o-up", nomeAcao = "Satisfeito")
    DASHBOARD_CTR_SATISFEITO,
    @InfoTipoAcaoController(icone = "fa fa-thumbs-o-down", nomeAcao = "Insatisfeito")
    DASHBOARD_CTR_INSATISFEITO,
    @InfoTipoAcaoGestaoEntidade(entidade = UsuarioCrmCliente.class, precisaPermissao = true)
    CONVERSA_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-comments-o")
    CONVERSA_FRM_CHAT,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-folder-o", entidade = ArquivoCliente.class, utilizarMesmoFormEdicao = false)
    DOCUMENTOS_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Categorias de Arquivos")
    DOCUMENTOS_FRM_LISTAR_PASTAS,
    @InfoTipoAcaoFormulario(icone = "fa fa-folder-open-o", xhtmlDaAcao = "arquivosPasta.xhtml")
    DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA,
    @InfoTipoAcaoController()
    DOCUMENTOS_CTR_BAIXAR_ARQUIVO,
    @InfoTipoAcaoController()
    DOCUMENTOS_CTR_VISUALIZAR_ARQUIVO,
    @InfoTipoAcaoController(icone = "fa fa-upload")
    DOCUMENTOS_CTR_ENVIAR_ARQUIVO,
    @InfoTipoAcaoGestaoEntidade(entidade = ChatBot.class, precisaPermissao = true, icone = "fa fa-comments-o")
    FORM_CHAT_INTERATIVO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-comments-o")
    FORM_CHAT_INTERATIVO_FRM_INTERACAO,

}
