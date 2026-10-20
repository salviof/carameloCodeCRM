/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoGestorUrlClienteChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCObjetoSB;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.FabConfigModuloWebAppGenerico;
import org.coletivojava.fw.utilCoreBase.UtilCRCReflexaoObjetoSimples;

/**
 *
 * @author salvio
 */
public class DefinicaoURLChat implements ComoGestorUrlClienteChat {

    private final String SUBDOMINIO_PADRAO_ATENDIMENTO = "chatatendimento";
    private final String SUBDOMINIO_PADRAO_FORNECEDOR = "chatcomunidade";
    private final String SUBDOMINIO_PADRAO_CLIENTE = "chatcliente";
    private final String SUBDOMINIO_PADRAO_FULL_CHAT = "chat";

    private String dominioPrincipal;

    private String urlChatFull;

    @Override
    public String getUrlChatBaseUsuario(ComoUsuario pUsuario) {
        StringBuilder urlChat = new StringBuilder();
        urlChat.append("https://");

        if (SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            if (SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {

                if (SBCore.getUsuarioLogado().getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_CLIENTE.getRegistro().getId())) {
                    urlChat.append("chatcliente.");
                } else if (SBCore.getUsuarioLogado().getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_ATENDIMENTO.getRegistro().getId())) {
                    urlChat.append("chatatendimento.");
                } else if (SBCore.getUsuarioLogado().getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_CONVIDADO.getRegistro().getId())) {
                    urlChat.append("chatcomunidade.");
                } else {
                    if (UtilCRCReflexaoObjeto.getClassExtraindoProxy(pUsuario.getClass().getSimpleName()).equals(UsuarioCRM.class)) {
                        urlChat.append("chatatendimento.");
                    }
                }
            }
        } else {
            urlChat.append("chat.");
        }
        if (dominioPrincipal == null) {
            dominioPrincipal = FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO.getValorParametroSistema().replace("crm", "").replace("https://", "").replace("atendimento", "").replace("/", "");
        }
        urlChat.append(dominioPrincipal);
        urlChat.append("?modokiosk=true");
        if (SBCore.getUsuarioLogado().getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_CLIENTE.getRegistro().getId())) {
            urlChat.append("&sso=3");
        } else {
            urlChat.append("&sso=1");
        }

        return urlChat.toString();
    }

}
