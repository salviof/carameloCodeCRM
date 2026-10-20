/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.config;

import br.org.coletivoJava.fw.erp.implementacao.chat.atendente.janelaChat.GestaoNotificacaoJanelaChat;
import br.org.carameloCode.erp.modulo.notificacao.controller.ServicoNotificacaoComPersistencia;
import br.org.coletivoJava.fw.erp.implementacao.chat.atendente.RepositorioJanelasChatAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.chat.NotificacaoUsuarioJanelaChat;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroDetectandoTelaBloqueio;
import com.super_bits.modulosSB.webPaginas.push.NotificadorIntranetJSF;

/**
 *
 * @author salvio
 */
public class ServicoComunicacaoCRM extends ServicoNotificacaoComPersistencia {

    private NotificadorIntranetJSF notificadorJsf = new NotificadorIntranetJSF();

    public ServicoComunicacaoCRM() {

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pCanal, ComoDialogo pComunicacao, int pTempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) throws ErroDetectandoTelaBloqueio {
        return notificadorJsf.aguardarRespostaComunicacaoModalTransitorio(pCanal, pComunicacao, pTempoAguardar, pTipoRespostaTempoFinal);
    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return null;
    }

    @Override
    public boolean notificarViaMenu(ComoDialogo pDialogo) {
        return notificadorJsf.notificarViaMenu(pDialogo);
    }

    @Override
    public boolean notificarViaBloqueioTEla(ComoDialogo pDialogo) {
        return notificadorJsf.notificarViaBloqueioTEla(pDialogo);
    }

    @Override
    public NotificacaoUsuarioJanelaChat gerarGestaoNotificacaoChat(String pEmailAtendimento) {
        if (CarameloCode.getServicoSessao().getSessaoAtual().isIdentificado()) {
            return new GestaoNotificacaoJanelaChat((ComoUsuario) CarameloCode.getServicoSessao().getSessaoAtual().getUsuario(),
                    RepositorioJanelasChatAtendimento.getServico().getServicoUrlCliente().getUrlChatBaseUsuario(CarameloCode.getServicoPermissao().getUsuarioByEmail(pEmailAtendimento)));

        } else {
            return null;
        }

    }

}
