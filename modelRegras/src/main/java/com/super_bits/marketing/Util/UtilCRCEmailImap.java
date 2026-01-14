/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.marketing.Util;

import com.sun.mail.imap.IMAPMessage;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.ControleCaixaDeEntrada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.AlwaysTrustSSLContextFactory;
import com.super_bits.marketing.Util.imapMail.ImapDoClienteRecebido;
import com.super_bits.marketing.Util.imapMail.PacoteNovosEmailsImapCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsExtrator;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class UtilCRCEmailImap {

    public static synchronized Store getPastaImap(UsuarioCRM pUsuario) throws ErroRegraDeNegocio {
        AlwaysTrustSSLContextFactory.getDefault();
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            UsuarioCRM usuarioAtualizado = UtilSBPersistencia.loadEntidade(pUsuario, em);
            if (usuarioAtualizado.getCaixaPostalPrincipal() == null) {
                throw new ErroRegraDeNegocio("O usuário" + usuarioAtualizado.getNome() + "não possui caixa postal");
            }
            String host = usuarioAtualizado.getCaixaPostalPrincipal().getEnderecoServidor();
            String user = usuarioAtualizado.getCaixaPostalPrincipal().getUsuarioRecepcao();
            String password = usuarioAtualizado.getCaixaPostalPrincipal().getSenhaRecepcao();
            if (host == null || host.isEmpty() || host.length() < 3) {
                throw new ErroRegraDeNegocio("Configure o endereço do servidor");
            }
            //create properties field
            Properties properties = new Properties();
            //PROPRIEDADES POP3
            //      properties.put("mail.pop3.host", host);
            //      properties.put("mail.pop3.port", "995");
            //properties.setProperty("mail.pop3s.socketFactory.class", "com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.crmAdmin.AlwaysTrustSSLContextFactory");
            //properties.put("mail.pop3.starttls.enable", "true");
            //properties.put("mail.imap.ssl.checkserveridentity", "false");
            //properties.put("mail.imap.ssl.trust", "*");
            //  Store store = emailSession.getStore("pop3s");
            properties.setProperty("mail.store.protocol", "imaps");
            properties.setProperty("mail.imap.port", "993");
            properties.setProperty("mail.imap.socketFactory.class", "com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.crmAdmin.AlwaysTrustSSLContextFactory");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.checkserveridentity", "false");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store;
            try {
                store = emailSession.getStore();
                store.connect(host, user, password);

                return store;
            } catch (NoSuchProviderException ex) {
                throw new ErroRegraDeNegocio(ex.getMessage() + "Falha conectando com as pastas de Email");
            } catch (MessagingException ex) {
                throw new ErroRegraDeNegocio(ex.getMessage() + "Falha Lendo mensagens no servidor");
            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    public static synchronized PacoteNovosEmailsImapCliente getNovosEmails(String pEmail, Folder pPasta, boolean pUmaCaixaSpan) throws MessagingException {

        PacoteNovosEmailsImapCliente pacoteLeitura = new PacoteNovosEmailsImapCliente();
        if (pPasta == null) {
            return null;
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(pEmail)) {
            return null;
        }
        if (!pPasta.isOpen()) {
            pPasta.open(Folder.READ_ONLY);
        }

        int totalMensagens = pPasta.getMessageCount();
        int qtdUltimas = 10;
        int inicio = Math.max(1, totalMensagens - qtdUltimas + 1);
        //Message[] messages = pPasta.getMessages();
        Message[] messages = pPasta.getMessages(inicio, totalMensagens);

        if (messages.length == 0) {
            return null;
        }

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            boolean encontrouUltimoEmail = false;
            final ControleCaixaDeEntrada controleInbox;
            List<ControleCaixaDeEntrada> controles = (List) UtilSBPersistencia.getListaRegistrosByHQL("from ControleCaixaDeEntrada where email= ?0 and nomePasta= ?1 ", 1, em, pEmail, pPasta.getName());
            IMAPMessage mensagemInicial = (IMAPMessage) messages[messages.length - 1];
            IMAPMessage mensagemFinal = (IMAPMessage) messages[0];
            if (!controles.isEmpty()) {
                controleInbox = controles.get(0);
                //se a quantidade de emails for igual, e o ultimo e primeiro emails for igual não tem um email novo
                if (messages.length == controleInbox.getQuantidadeEmails()
                        && controleInbox.getCodigoPrimeiroEmail().equals(UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagemInicial))
                        && controleInbox.getCodigoUltimoEmail().equals(UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagemFinal))) {
                    return null;
                }

            } else {
                controleInbox = new ControleCaixaDeEntrada();
                controleInbox.setEmail(pEmail);
                controleInbox.setNomePasta(pPasta.getName());
                controleInbox.setUmaCaixaDeSapan(pUmaCaixaSpan);
            }
            boolean chegouLoteJaProcessado = false;

            for (int i = messages.length - 1; i >= 0; i--) {
                IMAPMessage mensagemImap = (IMAPMessage) messages[i];
                // System.out.println(mensagemImap.getSubject());
                String identificador = UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagemImap);
                if (controleInbox.getCodigoPrimeiroEmail() != null) {
                    if (controleInbox.getCodigoPrimeiroEmail().equals(identificador)) {
                        break;
                    }
                }
                String remetente = UtilCRCStringsExtrator.extrairEmail(mensagemImap.getFrom()[0].toString());
                UsuarioCRM usuario = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(remetente);
                if (usuario instanceof UsuarioCrmCliente) {
                    pacoteLeitura.getNovasMensagens().add(new ImapDoClienteRecebido((UsuarioCrmCliente) usuario, mensagemImap));
                }

            }

            controleInbox.setCodigoPrimeiroEmail(UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagemInicial));
            controleInbox.setQuantidadeEmails(messages.length);
            controleInbox.setCodigoUltimoEmail(UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagemFinal));
            pacoteLeitura.setControleLeituraAtualizacao(controleInbox);
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return pacoteLeitura;
    }

}
