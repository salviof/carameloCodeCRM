/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.api.model.reservahorario.CPReservaHorario;
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
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.FabStatusReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;

/**
 *
 * @author sfurbino
 */
public class ModuloCrmAgenda extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_ENVIAR_LINK_REUNIAO)
    public static ItfRespostaAcaoDoSistema pessoaConverter(ReservaHoraRemotoVideo pReserval) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserval), pReserval) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHoraRemotoVideo reserva = loadEntidade(pReserval);
                reserva.setLinkConferencia(pReserval.getComoReservaVideoConferencia().getLinkConferencia());
                boolean resultado = false;

                if (reserva.getTipoAgendamento().isUmAtendimentoRemoto()) {
                    throw new ErroRegraDeNegocio("O compromisso não é do tipo Remoto");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(reserva.getComoReservaVideoConferencia().getLinkConferencia())) {
                    throw new ErroRegraDeNegocio("Insira o link da conferência");
                }
                reserva.setStatus(FabStatusReservaHorario.CONFIRMADO.getRegistro());
                reserva = atualizarEntidade(reserva);
                boolean notificacao = false;
                String frase = "A reunião agendada com " + reserva.getAtendenteResponsavel().getNome() + " iniciou, segue o link para acesso: ";
                String telefone = (String) reserva.getAtendidoResponsavel().getContatoClienteVinculado().getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.celularformatointernacional).getValor();
                if (!UtilCRCStringValidador.isNuloOuEmbranco(telefone)) {
                    ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, frase + reserva.getComoReservaVideoConferencia().getLinkConferencia()).getResposta();
                    String respostaStr = resposta.getRespostaTexto();
                    notificacao = resposta.isSucesso();
                    if (!resposta.isSucesso()) {
                        addAlerta("Falha enviando SMS para " + telefone + " - Erro:" + respostaStr);

                    }
                }

                String conteudoemail = frase + "<center><h1><a href='" + reserva.getComoReservaVideoConferencia().getLinkConferencia() + "' target='cndConferencia'> " + reserva.getComoReservaVideoConferencia().getLinkConferencia() + " </a></h1></center>";

                if (!UtilCRCStringValidador.isNuloOuEmbranco(reserva.getAtendidoResponsavel().getContatoClienteVinculado().getEmail())) {
                    try {
                        notificacao = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), reserva.getAtendidoResponsavel().getContatoClienteVinculado(), "O Link para sua reunião está pronto", conteudoemail);
                    } catch (ErroEnvioEmail ex) {
                        addAviso("Falha enviando email" + ex.getMensagemUsuario());
                    }

                }
                if (!notificacao) {
                    throw new ErroRegraDeNegocio(reserva.getAtendidoResponsavel().getContatoClienteVinculado() + " está incontactavel por aqui, houve falha tentando enviar e-mail e sms");
                }
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

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
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VISAO_GERAL.getRegistro().getComoFormulario());
            }
        }.getResposta();

    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_AGENDAR)
    public static ItfRespostaAcaoDoSistema reservaCriarNova(ReservaHorarioCRM pReserva) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (!UtilCRCStringValidador.isNuloOuEmbranco(pReserva.getAtendidoResponsavel().getContatoClienteVinculado().getEmail())) {
                    try {
                        UsuarioCrmCliente usuario = (UsuarioCrmCliente) pReserva.getAtendidoResponsavel();
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

                ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(ReservaHorarioCRM.class, getEm());
                novaconsulta.addCondicaoManyToOneIgualA(CPReservaHorario.atendenteresponsavel, pReserva.getAtendenteResponsavel());
                novaconsulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, new Date());
                novaconsulta.addCondicaoManyToOneContemNoIntervalo(CPReservaHorario.status, Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro()));

                List<ReservaHorarioCRM> reservasPossivelConflito = novaconsulta.resultadoRegistros();
                for (ReservaHorarioCRM reservaElegivel : reservasPossivelConflito) {
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
