/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;

/**
 *
 * @author salvio
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.AGENDA)
public enum FabAcaoAdminAgenda implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Agenda de Colaboradores", entidade = DisponibilidadeAtdmtPublico.class,
            utilizarMesmoFormEdicao = false)
    AGENDA_ADMIN_DIPONIBILIDADES_MB_GESTAO,
    @InfoTipoAcaoFormulario(descricao = "Lista de disponibilidades cadastradas", nomeAcao = "Disponibilidades do usuário",
            campos = {"id", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo", "horarioInicio", "horarioFim", "diaInicial", "diaFinal"})
    AGENDA_ADMIN_DIPONIBILIDADES_FRM_LISTAR_DISPONIBILIDADES,
    @InfoTipoAcaoFormulario(descricao = "Editar Disponibilidade", nomeAcao = "Disponibilidades",
            icone = "fa fa-calendar",
            campos = {"[separador: Informações Básicas]", "usuarioResponsavel", "diaInicial", "diaFinal", "[separador: Horários]", "horarioInicio", "horarioFim",
                "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
                "[separador: Tipos de Reservas disponíveis]", "tiposAgendamentosPublicos"}
    )
    AGENDA_ADMIN_DIPONIBILIDADES_FRM_EDITAR_DISPONIBILIDADE,
    @InfoTipoAcaoFormulario(
            descricao = "Criar nova disponibilidade de agendamento público",
            nomeAcao = "Nova disponibilidade",
            campos = {"[separador: Informações Básicas]", "usuarioResponsavel", "diaInicial", "diaFinal", "[separador: Horários]", "horarioInicio", "horarioFim",
                "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
                "[separador: Tipos de Reservas disponíveis]", "tiposAgendamentosPublicos"},
            estadoFormulario = FabEstadoFormulario.NOVO
    )
    AGENDA_ADMIN_DIPONIBILIDADES_FRM_NOVO_DISPONIBILIDADE,
    @InfoTipoAcaoFormulario(icone = "fa fa-calendar-check-o")
    @InfoTipoAcaoController
    AGENDA_ADMIN_DIPONIBILIDADES_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = ReservaHorario.class, nomeAcao = "Testar Horários livres")
    RESERVAS_ADMIN_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atendentes", icone = "fa fa-users")
    RESERVAS_ADMIN_FRM_ATENDENTES,
    @InfoTipoAcaoFormulario(nomeAcao = "Tipo reserva disponível", icone = "fa fa-ticket")
    RESERVAS_ADMIN_FRM_TIPOS_RESERVAS,
    @InfoTipoAcaoFormulario(entidade = ReservaHorario.class, nomeAcao = "Listar Horarios Disponíveis", icone = "fa fa-calendar-check-o")
    RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_DISPONIVEIS,
    @InfoTipoAcaoFormulario(entidade = ReservaHorario.class, nomeAcao = "Ver Reservas Feitas")
    RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_REGISTRADAS,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Agenda de Colaboradores", entidade = UsuarioCRM.class,
            utilizarMesmoFormEdicao = false)
    COLABORADOR_COM_AGENDA_MB_GESTAO,
    @InfoTipoAcaoFormulario(entidade = UsuarioCRM.class, estadoFormulario = FabEstadoFormulario.VISUALIZAR,
            campos = {"id", "nome", "email"}, valoresParametroEstatico = {CPUsuarioSB.grupo, "1"}
    )
    COLABORADOR_COM_AGENDA_FRM_LISTAR;

}
