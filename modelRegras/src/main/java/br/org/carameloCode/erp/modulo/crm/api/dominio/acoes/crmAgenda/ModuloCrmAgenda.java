/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author sfurbino
 */
public class ModuloCrmAgenda extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_SALVAR_RESERVA_MERGE)
    public static ItfRespostaAcaoDoSistema reservaAtendimento(ReservaHorario pReserva) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getId() == null) {
                    pReserva.setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
                }
                ReservaHorario reserva = (ReservaHorario) atualizarEntidadeConfigRetorno(pReserva);

                MapaHorariosDisponiveis.adicionarReservaAtendente(reserva);
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA.getRegistro().getComoFormulario());
            }
        }.getResposta();

    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema reservaREmover(ReservaHorario pReserva) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getId() == null) {
                    pReserva.setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
                }
                removerEntidade(pReserva);

                MapaHorariosDisponiveis.loadReservasEDisponibilidadesPersistidos();
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA.getRegistro().getComoFormulario());
            }
        }.getResposta();

    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_AGENDAR)
    public static ItfRespostaAcaoDoSistema reservaCriarNova(ReservaHorario pReserva) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (!UtilCRCStringValidador.isNuloOuEmbranco(pReserva.getAtendidoResponsavel().getContatoClienteVinculado().getEmail())) {
                    try {
                        UsuarioCrmCliente usuario = pReserva.getAtendidoResponsavel();
                        if (usuario != null) {
                            ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO, usuario.getContatoClienteVinculado(), usuario.getEmail());

                            String urlIntranet = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(SBCore.getServicoVisualizacao().getFORMULARIO_TOKEN_GENERICO(), token);
                            urlIntranet = urlIntranet.replace("crm.", "atendimento.");

                            ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto()
                                    .enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(),
                                            pReserva.getAtendidoResponsavel(),
                                            SBCore.getUsuarioLogado().getNome() + " agendou uma reunião",
                                            " Uma reunião sobre <i>'" + pReserva.getTipoAgendamento().getNome() + "'</i> Foi agendada para você <br/>"
                                            + " dia " + UtilCRCDataHora.getDataHoraString(pReserva.getInicioReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_EXTENSO) + ", <br/>"
                                            + "para confirmar, ou cancelar acesse a nossa "
                                            + " <h1> <a href='" + urlIntranet + "' > área do cliente </a> </h1> <br/>"
                                            + "");
                        }

                    } catch (ErroEnvioEmail ex) {
                        addAviso("Falha notificando email");
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getId() == null) {
                    pReserva.setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
                }

                if (pReserva.getAtendenteResponsavel() == null) {

                }
                if (pReserva.getInicioReservaAtendente().getTime() <= new Date().getTime()) {
                    throw new ErroRegraDeNegocio("Não é possível agendar no passado");
                }
                if (pReserva.getInicioReservaAtendente().getTime() >= pReserva.getFinalReservaAtendente().getTime()) {
                    throw new ErroRegraDeNegocio("A data final precia ser menor que a data inicial");
                }

                ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, getEm());
                novaconsulta.addCondicaoManyToOneIgualA(CPReservaHorario.atendenteresponsavel, pReserva.getAtendenteResponsavel());
                novaconsulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, new Date());
                novaconsulta.addCondicaoManyToOneContemNoIntervalo(CPReservaHorario.status, Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro()));

                List<ReservaHorario> reservasPossivelConflito = novaconsulta.resultadoRegistros();
                for (ReservaHorario reservaElegivel : reservasPossivelConflito) {
                    if (MapaHorariosDisponiveis.isReservaBloqueadoPorOutraReserva(pReserva, reservaElegivel)) {
                        throw new ErroRegraDeNegocio("Já existe uma reunião para " + pReserva.getAtendenteResponsavel().getNome() + " com " + reservaElegivel.getPessoaRelacionada().getNome());
                    }
                }

                ReservaHorario reserva = (ReservaHorario) atualizarEntidadeConfigRetorno(pReserva);
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA.getRegistro().getComoFormulario());
                MapaHorariosDisponiveis.adicionarReservaAtendente(reserva);
            }
        }.getResposta();

    }

}
