/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.marketing.Util.imapMail;

import com.sun.mail.imap.IMAPMessage;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;

/**
 *
 * @author salvio
 */
public class ImapDoClienteRecebido {

    private final UsuarioCrmCliente usuarioCliente;
    private final IMAPMessage mensagem;

    public ImapDoClienteRecebido(UsuarioCrmCliente pUsuario, IMAPMessage pMensagem) {
        usuarioCliente = pUsuario;
        mensagem = pMensagem;
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return usuarioCliente;
    }

    public IMAPMessage getMensagem() {
        return mensagem;
    }

}
