/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.calendario;

import com.super_bits.Super_Bits.googleCalendarIntegracao.api.FabIntGoogleCalendar;

import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_CTR_SINCRONIZAR_GOOGLE_SERVER;
import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_FALHA;
import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_SUCESSO;
import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICAR;
import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_SINCRONIZACAO;
import static br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_MB;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SalvioF
 */
@ViewScoped
@InfoAcaoGoogleCalendario(acao = FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_MB)
@InfoPagina(nomeCurto = "Calendario", tags = {"Calendário"})
@Named
public class PgCalendario extends MB_PaginaConversation {

    private UsuarioCRM usuarioLogado;

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada();
        ItfTokenGestaoOauth autorizador = (ItfTokenGestaoOauth) FabIntGoogleCalendar.AGENDAR_ATIVIDADES.getGestaoToken(SBCore.getUsuarioLogado());
        boolean autorizado = false;
        autorizado = autorizador.isTemTokemAtivo();
        if (autorizador.validarToken()) {
            autorizado = true;
        } else {
            autorizado = false;
        }
        if (autorizado) {

        } else {
            SBCore.enviarAvisoAoUsuario("Você precisa fornescer a permissão  pelo google");
            String urlAutenticacao = autorizador.getUrlObterCodigoSolicitacao();
            UtilSBWP_JSFTools.vaParaPagina(urlAutenticacao);
        }

        if (getAcaoSelecionada() != null) {
            getAcaoSelecionada().getEnumAcaoDoSistema();
            FabAcaoGoogleCalendario acao = (FabAcaoGoogleCalendario) getAcaoSelecionada().getEnumAcaoDoSistema();

            switch (acao) {

                case CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_SUCESSO:
                    if (autorizador.getStatusToken().equals(FabStatusToken.ATIVO)) {
                        executaAcaoSelecionadaPorEnum(FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_SINCRONIZACAO);
                    }

                    //autorizador.getCalendario().autorizar(request);
                    break;
                case CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_FALHA:

                    break;
                case CALENDARIO_GOOGLE_FRM_SINCRONIZACAO:
                    if (autorizador.getStatusToken().equals(FabStatusToken.ATIVO)) {
                        executaAcaoSelecionadaPorEnum(FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICAR);
                    }

                    break;
                case CALENDARIO_GOOGLE_CTR_SINCRONIZAR_GOOGLE_SERVER:
                    if (autorizador.getStatusToken().equals(FabStatusToken.ATIVO)) {
                        executaAcaoSelecionadaPorEnum(FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICAR);
                    } else {
                        FabIntGoogleCalendar.AGENDAR_ATIVIDADES.getAcao(SBCore.getUsuarioLogado()).getResposta().dispararMensagens();
                    }
                    break;
                case CALENDARIO_GOOGLE_MB:
                    break;
                case CALENDARIO_GOOGLE_FRM_AUTENTICAR:
                    if (!autorizador.getStatusToken().equals(FabStatusToken.ATIVO)) {
                        String url = autorizador.getUrlObterCodigoSolicitacao();
                        getPaginaUtil().irParaURL(url);
                    }
                    break;
                default:
                    throw new AssertionError(acao.name());

            }
        }

    }

    private UsuarioCRM getUsuarioLogado() {

        usuarioLogado = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, SBCore.getUsuarioLogado().getId(), getEMPagina());
        return usuarioLogado;

    }

    @PostConstruct
    public void inicio() {
        ItfTokenGestaoOauth autorizador = (ItfTokenGestaoOauth) FabIntGoogleCalendar.AGENDAR_ATIVIDADES.getGestaoToken(SBCore.getUsuarioLogado());
        if (autorizador.isTemTokemAtivo()) {
            executaAcaoSelecionadaPorEnum(FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_SINCRONIZACAO);
        } else {
            executaAcaoSelecionadaPorEnum(FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICAR);
        }
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public void gravarAgenda() {

    }

}
