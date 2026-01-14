/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller;

import br.org.coletivojava.erp.notificacao.api.ERPNotificacoes;
import br.org.coletivojava.erp.notificacao.api.ItfERPNotificacao;
import br.org.coletivojava.erp.notificacao.padrao.controller.ModuloNotificacao;
import br.org.coletivojava.erp.notificacao.padrao.model.notificacao.NotificacaoSB;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tipoNotificacao.TiponotificacaoCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class ServicoNotificacao {

    public static ItfERPNotificacao NOTIFICACAO_SRV = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto();

    public static boolean notificarClienteContatoPrincipal(TiponotificacaoCRM tipoNotificacao, ChamadoCliente pChamado) {
        return false;
    }

    public static boolean notificarClienteContatoPrincipal(AtividadeCRM tipoNotificacao, ChamadoCliente pChamado) {
        return false;
    }

    public static boolean notificarClienteContatoPrincipal(Pessoa tipoNotificacao, ChamadoCliente pChamado) {
        return false;
    }

    public static boolean chamadoNotificarResponsaveis(FabTipoNotificacao pTipo, ChamadoCliente pChamado) {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        TiponotificacaoCRM tipoNotificacao = pTipo.getRegistro();

        try {

            tipoNotificacao = UtilSBPersistencia.loadEntidade(tipoNotificacao, em);
            UtilSBPersistencia.iniciarTransacao(em);
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(pChamado, em);
            Pessoa p = UtilSBPersistencia.loadEntidade(chamado.getPessoa(), em);
            for (UsuarioSB usuario : p.getUsuariosResponsaveis()) {
                NotificacaoSB notificacao = NOTIFICACAO_SRV.getNotificacao(tipoNotificacao, usuario, pChamado);
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
