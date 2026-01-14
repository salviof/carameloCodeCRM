/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class AssincAddUserRespCanalIntranet extends Thread implements Runnable {

    private final ComoChatSalaBean sala;
    private final UsuarioCRM usuarioGestor;
    private final List<UsuarioCRM> novaLista;
    private final List<ComoUsuarioChat> listaUsuarioChat = new ArrayList<>();

    public AssincAddUserRespCanalIntranet(ComoChatSalaBean pCanal, UsuarioCRM pUsuarioGestor, List<UsuarioCRM> pListaUsuarios) {

        sala = pCanal;
        novaLista = pListaUsuarios;
        usuarioGestor = pUsuarioGestor;

    }

    private void adicionarAdicionarUsuarios() {
        ItfErpChatService servicochat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

        try {

            for (UsuarioCRM usuario : novaLista) {
                if (usuario.isUmUsuarioDoCliente()) {
                    try {
                        listaUsuarioChat.add(UtilCRMChat.gerarUsuarioContatoCliente(usuario.getComoUsuarioCliente()));
                    } catch (ErroRegraDeNEgocioChat ex) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando usuario contato", ex);
                    }
                } else {
                    try {
                        listaUsuarioChat.add(UtilCRMChat.gerarUsuarioAtendimento(usuario));
                    } catch (ErroRegraDeNEgocioChat ex) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando usuario atendimento", ex);
                    }
                }
            }

            for (ComoUsuarioChat usr : sala.getUsuarios()) {
                if (!listaUsuarioChat.contains(usr)) {
                    //TODO sala remover membro

                }
            }

            for (ComoUsuarioChat usr : listaUsuarioChat) {
                if (!sala.getUsuarios().contains(usr)) {
                    servicochat.salaAdicionarMembro(sala, usr.getCodigoUsuario());
                }
            }
        } catch (ErroConexaoServicoChat ex) {
            Logger.getLogger(AssincAddUserRespCanalIntranet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tornardono() {
        if (usuarioGestor != null || !usuarioGestor.isUmUsuarioDoCliente()) {
            ComoUsuarioChat usuarioChat;
            try {
                usuarioChat = UtilCRMChat.gerarUsuarioAtendimento(usuarioGestor);
                UtilCRMChat.chatService.salaTornarMembroAdmin(sala, usuarioChat.getCodigoUsuario());
            } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                SBCore.RelatarErroAoUsuario(FabErro.SOLICITAR_REPARO, "Falha definindo usuario admin", ex);
            }

        }
    }

    @Override
    public void run() {

        adicionarAdicionarUsuarios();
        if (usuarioGestor != null) {
            tornardono();
        }

    }

}
