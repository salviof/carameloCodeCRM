/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.CPEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmRemoto;

/**
 *
 * @author sfurbino
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.AGENDA)
public enum FabAcaoAgendaMentoPublico implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = DisponibilidadeAtdmtPublico.class, icone = "fa fa-ticket", nomeAcao = "Disponibilidade agenda pública")
    MINHA_DISPONIBILIDADE_MB_GESTAO,
    @InfoTipoAcaoFormulario(descricao = "Lista de disponibilidades cadastradas", nomeAcao = "Minhas disponibilidades",
            campos = {"diasDaSemana", "horarioInicio", "horarioFim", "diaInicial", "diaFinal"}
    )
    MINHA_DISPONIBILIDADE_FRM_LISTAR_MINAS_DISPONIBILIDADES,
    @InfoTipoAcaoFormulario(descricao = "Lista de disponibilidades cadastradas", nomeAcao = "Minhas disponibilidades",
            campos = {"id", "diasDaSemana", "horarioInicio", "horarioFim", "diaInicial", "diaFinal"}
    )
    MINHA_DISPONIBILIDADE_FRM_LISTAR_TODAS,
    @InfoTipoAcaoFormulario(descricao = "Editar Disponibilidade", nomeAcao = "Minhas disponibilidades",
            campos = {"[separador: Informações Básicas]", "usuarioResponsavel", "diaInicial", "diaFinal", "[separador: Horários]", "horarioInicio", "horarioFim",
                "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
                "[separador: Tipos de Reservas disponíveis]", "tiposAgendamentosPublicos"}
    )

    MINHA_DISPONIBILIDADE_FRM_EDITAR,
    @InfoTipoAcaoFormulario(descricao = "Criar nova disponibilidade de agendamento público", nomeAcao = "Ver disponibilidade",
            campos = {"[separador: Informações Básicas]", "usuarioResponsavel", "diaInicial", "diaFinal", "[separador: Horários]", "horarioInicio", "horarioFim",
                "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
                "[separador: Tipos de Reservas disponíveis]", "tiposAgendamentosPublicos"}
    )
    MINHA_DISPONIBILIDADE_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(
            descricao = "Criar nova disponibilidade de agendamento público", nomeAcao = "Nova disponibilidade",
            campos = {"[separador: Informações Básicas]", "usuarioResponsavel", "diaInicial", "diaFinal", "[separador: Horários]", "horarioInicio", "horarioFim",
                "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca", "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
                "[separador: Tipos de Reservas disponíveis]", "tiposAgendamentosPublicos"},
            estadoFormulario = FabEstadoFormulario.NOVO
    )
    MINHA_DISPONIBILIDADE_FRM_NOVO,
    @InfoTipoAcaoController
    MINHA_DISPONIBILIDADE_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = TipoAgendamentoAtdmPublico.class, icone = "fa fa-ticket", nomeAcao = "Tipos de reservas")
    TIPO_RESERVA_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo tipo Remoto", campos = {"[separador: Apresentação da atividade]", "nome", "descricao", "icone",
        //"imagemPequena",
        "[separador: Espaço reservado na agenda Pública]", "minutosProgramadosAtendimento", "minutosAnteriorAReserva", "antecedenciaNovaReservaMinutos",
        "[separador: Contexto da reserva]",
        "fatorOcupacao", "contextoReserva"}, entidade = TipoAgendamentoAtdmPublico.class)
    TIPO_RESERVA_FRM_NOVO_REMOTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo tipo Presencial", campos = {"[separador: Apresentação da atividade]", "nome", "descricao", "icone",
        //"imagemPequena",
        "[separador: Espaço reservado na agenda Pública]", "minutosProgramadosAtendimento", "minutosAnteriorAReserva", "antecedenciaNovaReservaMinutos",
        "[separador: Contexto da reserva]",
        "fatorOcupacao", "contextoReserva"}, entidade = TipoAgendamentoAtdmPresencial.class)
    TIPO_RESERVA_FRM_NOVO_PRESENCIAL,
    @InfoTipoAcaoFormulario(nomeAcao = "Tipos de reservas Públicas", campos = {"id", "tipoAtendimentoPublico", "nome", "icone", "descricao"})
    TIPO_RESERVA_FRM_LISTAR,
    @InfoTipoAcaoFormulario(entidade = TipoAgendamentoAtdmPresencial.class, campos = {"[separador: Apresentação da atividade]", "nome", "descricao", "icone",
        //"imagemPequena",
        "[separador: Espaço reservado na agenda Pública]", "minutosProgramadosAtendimento", "minutosAnteriorAReserva", "antecedenciaNovaReservaMinutos",
        "[separador: Contexto da reserva]",
        "fatorOcupacao", "contextoReserva"}
    )
    TIPO_RESERVA_FRM_EDITAR_PRESENCIAL,
    @InfoTipoAcaoFormulario(entidade = TipoAgendamentoAtdmRemoto.class, campos
            = {"[separador: Apresentação da atividade]", "nome", "descricao", "icone",
                //"imagemPequena",
                "[separador: Espaço reservado na agenda Pública]", "minutosProgramadosAtendimento", "minutosAnteriorAReserva", "antecedenciaNovaReservaMinutos",
                "[separador: Contexto da reserva]",
                "fatorOcupacao", "contextoReserva"}
    )
    TIPO_RESERVA_FRM_EDITAR_REMOTO,
    @InfoTipoAcaoFormulario()
    TIPO_RESERVA_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    TIPO_RESERVA_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = EscopoPesqHorarioPublicado.class, nomeAcao = "Links Publicos de Agendamento")
    ESCOPO_AGENDAMENTO_PUBLICO_MB,
    @InfoTipoAcaoFormulario(campos = {"id", CPEscopoPesquisaMelhorHorario.tipoagendamento,
        CPEscopoPesquisaMelhorHorario.atendentes,
        CPEscopoPesqHorarioPublicado.publicado})
    ESCOPO_AGENDAMENTO_PUBLICO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Escopo Permitido]", "nome",
        "tipoAgendamento", "atendentes", "numeroOpcoes", "qtdMaximoReservas",
        CPEscopoPesquisaMelhorHorario.datainicial,
        "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca",
        "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
        "[separador: Token:]",
        "dataHoraTokenPublicoExpira", "tokenPublicado",
        "[separador: Detalhes da Publicação]",
        "linkDeAcesso",
        "pixelGoogle",
        "pixelFacebook",
        "pixelMautic"

    })
    ESCOPO_AGENDAMENTO_PUBLICO_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Escopo Permitido]", "nome",
        "tipoAgendamento", "atendentes",
        "[separador: Limites do escopo]",
        "numeroOpcoes", "qtdMaximoReservas",
        CPEscopoPesquisaMelhorHorario.datainicial,
        "[separador: Dias da semana válidos]", "diaSemanaSegunda", "diaSemanaTerca",
        "diaSemanaQuarta", "diaSemanaQuinta", "diaSemanaSexta", "diaSemanaSabado", "diaSemanaDomingo",
        "[separador: Token:]",
        "dataHoraTokenPublicoExpira", "tokenPublicado",
        "[separador: Detalhes da Publicação]",
        "linkDeAcesso",
        "pixelGoogle",
        "pixelFacebook",
        "pixelMautic"
    })
    ESCOPO_AGENDAMENTO_PUBLICO_FRM_EDITAR,
    @InfoTipoAcaoController()
    ESCOPO_AGENDAMENTO_PUBLICO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController()
    ESCOPO_AGENDAMENTO_PUBLICO_CTR_ATIVAR,
    @InfoTipoAcaoController()
    ESCOPO_AGENDAMENTO_PUBLICO_CTR_DESATIVAR,
    @InfoTipoAcaoController()
    ESCOPO_AGENDAMENTO_PUBLICO_CTR_REMOVER;

}
