/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller;

import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.notificacao.api.ERPNotificacoes;
import br.org.carameloCode.erp.modulo.notificacao.api.ItfERPNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.controller.ModuloNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.ComoTipoComunicCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class ServicoNotificacao {

    public static ItfERPNotificacao NOTIFICACAO_SRV = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto();

    public static boolean notificarReservaCliente(FabTipoNotificacao pTipo, ReservaHorarioCRM pReserva) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        ComoTipoComunicCRM tipoNotificacao = pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ReservaHorarioCRM reserva = UtilSBPersistencia.loadEntidade(pReserva, em);
            Pessoa p = UtilSBPersistencia.loadEntidade(reserva.getAtendidoResponsavel().getRepresentanteLegal(), em);
            List<ContatoProspecto> contatos = p.getContatosProspecto();
            if (!contatos.contains(p.getContatoPrincipal())) {
                contatos.add(p.getContatoPrincipal());
            }
            for (ContatoProspecto usuario : p.getContatosProspecto()) {
                NotificacaoSB notificacao = NOTIFICACAO_SRV.gerarNotificacao((TipoNotificacao) tipoNotificacao, usuario.getUsuarioVinculado(), pReserva);
                notificacao.setCodigoEntidadeRelacionada(reserva.getId().toString());
                return ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();
            }

        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        }
        return true;
    }

    public static boolean notificarReservaAtendente(FabTipoNotificacao pTipo, ReservaHorarioCRM pReserva) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        ComoTipoComunicCRM tipoNotificacao = pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ReservaHorarioCRM reserva = UtilSBPersistencia.loadEntidade(pReserva, em);
            Pessoa p = UtilSBPersistencia.loadEntidade(reserva.getAtendidoResponsavel().getRepresentanteLegal(), em);
            List<ContatoProspecto> contatos = p.getContatosProspecto();
            if (!contatos.contains(p.getContatoPrincipal())) {
                contatos.add(p.getContatoPrincipal());
            }
            for (ContatoProspecto usuario : p.getContatosProspecto()) {
                NotificacaoSB notificacao = NOTIFICACAO_SRV.gerarNotificacao((TipoNotificacao) tipoNotificacao, usuario.getUsuarioVinculado(), pReserva);
                notificacao.setCodigoEntidadeRelacionada(reserva.getId().toString());
                return ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();
            }

        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        }
        return true;
    }

    public static boolean notificarChamadoAtendente(FabTipoNotificacao pTipo, ChamadoCliente pChamado) {
        return notificarChamadoAtendente(pTipo, pChamado, false, false, false, true);
    }

    public static boolean notificarChamadoAtendente(FabTipoNotificacao pTipo, ChamadoCliente pChamado, boolean pNotificarResponsavelCliente, boolean pNotificarAtendenteAnterior, boolean pNotificarOutrosAtendentes, boolean pNotificarResponsavel) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        ComoTipoComunicCRM tipoNotificacao = pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(pChamado, em);

            List<UsuarioCRM> atendentes = new ArrayList<>();

            if (pNotificarResponsavelCliente) {
                if (chamado.getCPinst(CPChamadoCliente.pessoa).getValor() != null) {
                    if (chamado.getPessoa().getCPinst(CPPessoa.usuarioatendimento).getValor() != null) {
                        if (!atendentes.contains(chamado.getPessoa().getUsuarioAtendimento())) {
                            atendentes.add(chamado.getPessoa().getUsuarioAtendimento());
                        }
                    } else {
                        if (chamado.getPessoa().getCPinst(CPPessoa.usuarioresponsavel).getValor() != null) {
                            if (!atendentes.contains(chamado.getPessoa().getUsuarioResponsavel())) {
                                atendentes.add(chamado.getPessoa().getUsuarioResponsavel());
                            }

                        }
                    }
                }
            }

            if (pNotificarAtendenteAnterior) {
                if (chamado.getAtendenteAnterior() != null) {
                    if (atendentes.contains(chamado.getAtendenteAnterior())) {
                        atendentes.add(chamado.getAtendenteAnterior());
                    }
                }
            }
            if (pNotificarResponsavel) {
                if (chamado.getAtendenteResponsavel() != null) {
                    if (atendentes.contains(chamado.getAtendenteResponsavel())) {
                        atendentes.add(chamado.getAtendenteResponsavel());
                    }
                }
            }
            if (pNotificarOutrosAtendentes) {
                for (UsuarioCRM usr : chamado.getAtendentesConvidados()) {
                    if (!atendentes.contains(usr)) {
                        atendentes.add(usr);
                    }
                }
            }

            boolean notificado = false;
            for (UsuarioCRM usr : atendentes) {
                NotificacaoSB notificacao = NOTIFICACAO_SRV.gerarNotificacao((TipoNotificacao) tipoNotificacao, usr, chamado);
                notificacao.setCodigoEntidadeRelacionada(chamado.getId().toString());
                ItfRespostaAcaoDoSistema respNotificado = ModuloNotificacao.notificacaoRegistrar(notificacao);
                if (respNotificado.isSucesso()) {
                    notificado = true;
                }
            }
            return notificado;
        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        }

    }

    public static boolean notificarChamadoCliente(FabTipoNotificacao pTipo, ChamadoCliente pChamado) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        ComoTipoComunicCRM tipoNotificacao = pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(pChamado, em);
            Pessoa p = UtilSBPersistencia.loadEntidade(chamado.getPessoa(), em);
            List<ContatoProspecto> contatos = p.getContatosProspecto();
            if (!contatos.contains(p.getContatoPrincipal())) {
                contatos.add(p.getContatoPrincipal());
            }

            UsuarioCrmCliente usuario = chamado.getUsuarioCliente();

            NotificacaoSB notificacao = NOTIFICACAO_SRV.gerarNotificacao((TipoNotificacao) tipoNotificacao, usuario, pChamado);

            notificacao.setCodigoEntidadeRelacionada(chamado.getId().toString());
            return ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();

        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        }

    }

    public static boolean notificarClienteContatoPrincipal(FabTipoNotificacao tipoNotificacao, ChamadoCliente pChamado) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(pChamado, em);
            NotificacaoSB notificacao;
            try {
                notificacao = NOTIFICACAO_SRV.gerarNotificacao((TipoNotificacao) tipoNotificacao.getRegistro(), pChamado.getUsuarioCliente(), pChamado);
            } catch (Throwable ex) {
                return false;
            }
            notificacao.setCodigoEntidadeRelacionada(chamado.getId().toString());
            ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return false;
    }

    public static boolean notificacarChamadoResponsaveis(FabTipoNotificacao pTipo, ChamadoCliente pChamado) {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        TiponotificacaoCRM tipoNotificacao = (TiponotificacaoCRM) pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(pChamado, em);
            Pessoa p = UtilSBPersistencia.loadEntidade(chamado.getPessoa(), em);
            for (UsuarioSB usuario : p.getUsuariosResponsaveis()) {
                NotificacaoSB notificacao = NOTIFICACAO_SRV.gerarNotificacao(tipoNotificacao, usuario, pChamado);
                notificacao.setCodigoEntidadeRelacionada(chamado.getId().toString());
                return ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();
            }

        } catch (Throwable t) {
            return false;
        } finally {
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        }
        return true;

    }

}
