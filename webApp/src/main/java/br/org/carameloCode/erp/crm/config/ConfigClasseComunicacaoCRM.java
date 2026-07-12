/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.notificacao.controller.ServicoNotificacaoComPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItffabricaCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroDetectandoTelaBloqueio;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroSelandoDialogo;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.push.NotificadorIntranetJSF;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class ConfigClasseComunicacaoCRM extends ServicoNotificacaoComPersistencia implements ComoServicoComunicacao {

    private NotificadorIntranetJSF notificadorJsf = new NotificadorIntranetJSF();

    @Override
    public ItffabricaCanalComunicacao getCanalPadrao() {
        return ERPTipoCanalComunicacao.INTRANET_MENU;
    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pCanal, ComoDialogo pComunicacao, int pTempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) throws ErroDetectandoTelaBloqueio {

        if (SBCore.isEmModoDesenvolvimento()) {
            // má prática, em ambiente teste, ServicoComunicacaoWebAppPadrao não deveria ser uasada, deixamos aqui somente para testes de ontras funções testaveis de ServicoComunicacaoWebAppPadrao
            try {
                int dialogResult
                        = JOptionPane.showConfirmDialog(null, pComunicacao.getMensagem(),
                                "Deseja continuar?", JOptionPane.YES_OPTION);
                if (dialogResult
                        == JOptionPane.YES_OPTION) {
                    return FabTipoRespostaComunicacao.SIM;
                } else {
                    System.out.println("não");
                    return FabTipoRespostaComunicacao.NAO;
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando comunicação entre usuários", t);
                return null;
            }
        } else {
            ItfPaginaAtual paginaAtual = null;
            try {
                paginaAtual = (ItfPaginaAtual) UtilSBWPServletTools.getBeanByNamed("paginaAtual", ItfPaginaAtual.class);
                //paginaAtual.getInfoPagina().getComoFormularioWeb().adicionarCodigoCoversa(pComunicacao.getCodigoSelo());
                if (paginaAtual == null) {
                    throw new ErroDetectandoTelaBloqueio("Falha pesquisando pagina atual");
                }
            } catch (Throwable t) {
                throw new ErroDetectandoTelaBloqueio("Falha pesquisando pagina atual" + t.getMessage());
            }
            //TODO Implementar capacidade de enviar apenas na aba do disparo
            String codigoInstancia = ((B_Pagina) paginaAtual.getInfoPagina()).getPaginaInstanciaID();
            pComunicacao.getCPinst("paginaInstanciaID").setValor(codigoInstancia);
            ((ItfB_Pagina) paginaAtual.getInfoPagina()).registrarDialogoTransitorio(pComunicacao);

            try {

                selarComunicacao(pComunicacao);
                notificadorJsf.notificarViaBloqueioTEla(pComunicacao);

            } catch (ErroSelandoDialogo ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha gerando selo do dialogo", ex);
            }
            long deadline = System.currentTimeMillis() + (pTempoAguardar * 1000);
            boolean respondeu = false;
            FabTipoRespostaComunicacao resposta = null;
            while (System.currentTimeMillis() < deadline && !respondeu) {

                try {
                    if (paginaAtual.getInfoPagina().getComoFormularioWeb().getRespostaAcaoAtual() != null) {
                        try {
                            List<RespostaComunicacao> respostasListas = new ArrayList();
                            ((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().values().stream().forEach(respostasListas::add);

                            if (((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().containsKey(pComunicacao.getCodigoSelo())) {
                                respondeu = true;
                                resposta = ((ItfB_Pagina) paginaAtual.getInfoPagina()).getRespostasDialogosTransitorios().get(pComunicacao.getCodigoSelo()).getTipoResposta().getFabricaTipoResposta();
                            }
                        } catch (Throwable t) {
                            throw new ErroDetectandoTelaBloqueio("Falha navegando nas telas respostas do formulário");
                        }

                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                }
            }
            if (resposta == null) {
                resposta = pTipoRespostaTempoFinal;
            }
            return resposta;

        }

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
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return null;
    }

}
