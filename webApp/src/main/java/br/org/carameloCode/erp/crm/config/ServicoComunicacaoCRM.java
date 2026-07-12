/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.notificacao.controller.ServicoNotificacaoComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
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

}
