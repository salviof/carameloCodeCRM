/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.InfoAcaoCRMAgenda;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
public class ModuloAgendamentoPublico extends ControllerAbstratoSBPersistencia {

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema disponibilidadeAtendimentoMerge(DisponibilidadeAtdmtPublico pDisponibilidade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDisponibilidade), pDisponibilidade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidadeConfigRetorno(pDisponibilidade);
                MapaHorariosDisponiveis.adicionarDisponibilidade((DisponibilidadeAtdmtPublico) getRetorno());
            }
        }.getResposta();

    }

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema reservaAtendimento(DisponibilidadeAtdmtPublico pDisponibilidade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDisponibilidade), pDisponibilidade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    MapaHorariosDisponiveis.loadReservasEDisponibilidadesPersistidos();
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidadeConfigRetorno(pDisponibilidade);
                MapaHorariosDisponiveis.adicionarDisponibilidade((DisponibilidadeAtdmtPublico) getRetorno());

            }
        }.getResposta();

    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_ESCOPO_RESERVA_CLIENTE_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema atualizarMeuEscopoClientePadrao(UsuarioCRM pUsuario) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuario), pUsuario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                UsuarioCRM usuarioLogado = loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado());
                if (!usuarioLogado.equals(pUsuario)) {
                    throw new ErroRegraDeNegocio("Somente o usuário " + pUsuario.getNome() + " pode alterar este escopo");
                }
                EscopoPesquisaMelhorHorario escopo = atualizarEntidade(pUsuario.getEscopoAgendaClientes());
                usuarioLogado.setEscopoAgendaClientes(escopo);
                atualizarEntidade(usuarioLogado);
            }
        }.getResposta();

    }

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.TIPO_RESERVA_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema atualizarMeuEscopoClientePadrao(TipoAgendamentoAtdmPublico pTipoAgendamento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoAgendamento), pTipoAgendamento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                TipoAgendamentoAtdmPublico tipoAgendamento = atualizarEntidade(pTipoAgendamento);
                setRetorno(tipoAgendamento);

            }
        }.getResposta();

    }

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema escopoPublicoSalvarMerge(EscopoPesqHorarioPublicado pEscopo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEscopo), pEscopo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                EscopoPesqHorarioPublicado escopo = atualizarEntidade(pEscopo);
                setRetorno(escopo);

            }
        }.getResposta();

    }

}
