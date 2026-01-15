/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.marketing.Util.imapMail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.ControleCaixaDeEntrada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public class PacoteNovosEmailsImapCliente {

    private ControleCaixaDeEntrada controleLeituraAtualizacao;

    private final List<ImapDoClienteRecebido> novasMensagens = new ArrayList<>();

    public ControleCaixaDeEntrada getControleLeituraAtualizacao() {
        return controleLeituraAtualizacao;
    }

    public void setControleLeituraAtualizacao(ControleCaixaDeEntrada controleLeituraAtualizacao) {
        this.controleLeituraAtualizacao = controleLeituraAtualizacao;
    }

    public List<ImapDoClienteRecebido> getNovasMensagens() {
        return novasMensagens;
    }

}
