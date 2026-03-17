/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util;

import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import com.sun.mail.imap.IMAPMessage;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmailObjetos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidordisparoEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoHumano;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCEmailAvancado {

    public static JSONObject getJsonFromListaContato(List<? extends ComoContatoHumano> contatos) {
        try {
            JSONObject resposta = new JSONObject();
            JSONArray contatosJson = new JSONArray();
            contatos.stream().forEach(cEmcp -> {
                JSONObject novoColaborador = new JSONObject();
                novoColaborador.put("email", cEmcp.getEmail());
                if (!UtilCRCStringValidador.isNuloOuEmbranco(cEmcp.getNome())) {
                    novoColaborador.put("nome", cEmcp.getNome());
                } else {
                    novoColaborador.put("nome", cEmcp.getEmail());
                }
                contatosJson.add(novoColaborador);

            });
            resposta.put("contatos", contatosJson);
            return resposta;
        } catch (Throwable t) {
            return null;
        }
    }

    public static List<ContatoProspecto> getContatosFromTextoPadraoProtocoloEmail(String pTextoMails) {
        JSONObject jsonFromStringPara = getJsonFromStringPara(pTextoMails);
        JSONArray contatos = (JSONArray) jsonFromStringPara.get("contatos");
        List<ContatoProspecto> contatosResposta = new ArrayList<>();
        for (Object contato : contatos) {
            ContatoProspecto novocontato = new ContatoProspecto();
            novocontato.setNome(((JSONObject) contato).get("nome").toString());
            novocontato.setEmail(((JSONObject) contato).get("email").toString());
            contatosResposta.add(novocontato);
        }
        return contatosResposta;

    }

    public static JSONObject getJsonFromStringPara(String pString) {
        String[] emailPaternEncontrado = pString.split(",");
        Map<String, String> mapaEmailNome = new HashMap<>();
        Stream.of(emailPaternEncontrado).forEach(emailContato -> {
            String email;
            String nome;
            if (emailContato.contains("<")) {
                email = UtilCRCEmailObjetos.extrairTextoEamailDeStringProtocoloPadrao(emailContato);
                nome = emailContato.substring(0, emailContato.indexOf("<"));
            } else {
                email = UtilCRCEmailObjetos.extrairTextoEamailDeStringProtocoloPadrao(emailContato);
                nome = email;
            }
            mapaEmailNome.put(email, nome);

        });

        JSONObject resposta = new JSONObject();
        JSONArray contatosJson = new JSONArray();
        mapaEmailNome.keySet().forEach(mail
                -> {
            JSONObject contatoMail = new JSONObject();
            contatoMail.put("email", mail);
            contatoMail.put("nome", mapaEmailNome.get(mail));
            contatosJson.add(contatoMail);
        });
        resposta.put("contatos", contatosJson);
        return resposta;
    }

    private static String enviarEmailComAnexoSESApi(ItfServidordisparoEmail pServidor, JSONObject pContatos, JSONObject pCopias,
            String pAssunto, String pConteudo, List<ComoArquivo> pAnexos) throws ErroEnvioEmail {

        Regions regiao = UtilCRCEmail.detectarRegiao(pServidor.getEnderecoServidor());

        System.out.println("Enviando pela região:");
        System.out.println(regiao.getName());

        BasicAWSCredentials awsCreds
                = new BasicAWSCredentials(pServidor.getUsuarioSMTP(), pServidor.getSenhaSMTP());

        //TODO IDENTIFICAR A REGIAO PELO pEnderecoServidor
        AmazonSimpleEmailService sesClient
                = AmazonSimpleEmailServiceClientBuilder.standard()
                        .withRegion(regiao)
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .build();

        Session session = Session.getInstance(new Properties());

        MimeMessage message = new MimeMessage(session);

        try {
            // Remetente com nome
            message.setFrom(
                    new InternetAddress(pServidor.getFromEmail(), pServidor.getFromNome(), "UTF-8")
            );

            JSONArray contatosParaJson = (JSONArray) pContatos.get("contatos");
            if (pCopias != null) {
                JSONArray contaotsEmcopiaJson = (JSONArray) pCopias.get("contatos");
                for (Iterator iterator = contaotsEmcopiaJson.iterator(); iterator.hasNext();) {
                    JSONObject contato = (JSONObject) iterator.next();
                    String nomecontato = (String) contato.get("nome");
                    String emailcontato = (String) contato.get("email");
                    if (nomecontato != null && !nomecontato.contains("@")) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(emailcontato, nomecontato, "UTF-8"));
                    } else {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(emailcontato,
                                "UTF-8"));
                    }

                }

            }

            for (Iterator iterator = contatosParaJson.iterator(); iterator.hasNext();) {
                JSONObject contato = (JSONObject) iterator.next();
                String nomecontato = (String) contato.get("nome");
                String emailcontato = (String) contato.get("email");
                if (nomecontato != null && !nomecontato.contains("@")) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailcontato, nomecontato, "UTF-8"));
                } else {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailcontato,
                            "UTF-8"));
                }

            }

            if (message.getAllRecipients().length == 0) {
                throw new ErroEnvioEmail("Nenhum destinatário foi adicionando no campo para");
            }

            // Permite múltiplos emails e formato "Nome <email>"
            message.setSubject(pAssunto, "UTF-8");

            // Corpo HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(UtilCRCEmail.gerarConteudoEmailNormatizado(pConteudo), "text/html; charset=UTF-8");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            // Converter para RawMessage
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);

            RawMessage rawMessage = new RawMessage(
                    ByteBuffer.wrap(outputStream.toByteArray())
            );

            SendRawEmailRequest request = new SendRawEmailRequest(rawMessage);

            if (pAnexos != null) {
                for (ComoArquivo arquivo : pAnexos) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    String caminhoArquivo = arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo)
                            .getComoArquivoDeEntidade()
                            .getCaminhoArquivoLocal();
                    String nomeArquivo = arquivo.getNome();

                    if (caminhoArquivo.startsWith("http")) {
                        DataSource source = new URLDataSource(new URL(caminhoArquivo));
                        attachmentPart.setDataHandler(new DataHandler(source));
                        attachmentPart.setFileName(nomeArquivo);
                    } else {
                        File file = new File(caminhoArquivo);
                        attachmentPart.attachFile(file);
                    }
                    multipart.addBodyPart(attachmentPart);
                }
            }

            SendRawEmailResult result = sesClient.sendRawEmail(request);
            return result.getMessageId();

        } catch (MessagingException | IOException ex) {
            Logger.getLogger(UtilCRCEmail.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String enviarEmailComAnexoV2(ItfServidordisparoEmail pServidor, JSONObject pContatos, JSONObject pCopias,
            String pAssunto, String pConteudo, List<ComoArquivo> pAnexos) throws ErroEnvioEmail {
        try {
            if (pServidor == null) {
                pServidor = UtilCRCEmail.getServidorDisparoEmail();
            }

            if (pServidor == null) {
                throw new UnsupportedOperationException("Os dados do servidor não foram enviados");
            }

            if (pServidor.getUsuarioSMTP().contains("AKIA")) {
                return enviarEmailComAnexoSESApi(pServidor, pContatos, pCopias, pAssunto, pConteudo, pAnexos);
            }

            HtmlEmail email = new HtmlEmail();
            //email.setCharset("text/html; charset=utf-8");
            email.setCharset("UTF-8");
            email.setAuthentication(pServidor.getUsuarioSMTP(), pServidor.getSenhaSMTP());
            email.setHostName(pServidor.getEnderecoServidor()); // o servidor SMTP para envio do e-mail

            if (pContatos.isEmpty()) {

                throw new UnsupportedOperationException("Você precisa especificar os contatos que irão receber o documento");

            }
            JSONArray contatosParaJson = (JSONArray) pContatos.get("contatos");

            for (Iterator iterator = contatosParaJson.iterator(); iterator.hasNext();) {
                JSONObject contato = (JSONObject) iterator.next();
                String nomecontato = (String) contato.get("nome");
                String emailcontato = (String) contato.get("email");
                if (nomecontato != null && !nomecontato.contains("@")) {
                    email.addTo(emailcontato, nomecontato);
                } else {
                    email.addTo(emailcontato);
                }

            }
            if (email.getToAddresses().isEmpty()) {
                throw new ErroEnvioEmail("Nenhum destinatário foi adicionando no campo para");
            }

            email.setFrom(pServidor.getFromEmail(), pServidor.getRemetenteNome()); //remetente
            email.setSubject(pAssunto); //Assunto
            //pConteudo = "<html> <head> <link rel=\"stylesheet\" "
            //      + "href=\"https://fonts.googleapis.com/css?family=Montserrat:200,600,amp;lang=br\" /> </head> \n"
            //    + pConteudo + "\n </html>";
            if (pCopias != null) {
                JSONArray contaotsEmcopiaJson = (JSONArray) pCopias.get("contatos");

                for (Iterator iterator = contaotsEmcopiaJson.iterator(); iterator.hasNext();) {
                    JSONObject contato = (JSONObject) iterator.next();
                    String nomecontato = (String) contato.get("nome");
                    String emailcontato = (String) contato.get("email");
                    if (nomecontato != null && !nomecontato.contains("@")) {
                        email.addCc(emailcontato, nomecontato);
                    } else {
                        email.addTo(emailcontato);
                    }

                }
            }

            email.setMsg(UtilCRCEmail.gerarConteudoEmailNormatizado(pConteudo));
            //email.setMsg(pConteudo);
            //email.setSmtpPort(465);
            if (pServidor.getPortaSMTP() == 0) {

                email.setSmtpPort(587);
                email.setSSL(true);
                email.setTLS(true);
            } else {
                email.setSmtpPort(pServidor.getPortaSMTP());
                email.setSSL(pServidor.isUsarSSLSMTP());
                email.setTLS(pServidor.isUsarTSLSMTP());
            }

            long tamanhoTotal = 0L;
            final long LIMITE = 10 * 1024 * 1024; // 10 MB

            if (pAnexos != null) {
                for (ComoArquivo arquivo : pAnexos) {
                    String caminhoArquivo = arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo)
                            .getComoArquivoDeEntidade()
                            .getCaminhoArquivoLocal();
                    long tamanhoArquivo = 0L;

                    if (caminhoArquivo.startsWith("http")) {
                        // Para URL, podemos tentar pegar o tamanho pelo cabeçalho HTTP (Content-Length)
                        try {
                            URL url = new URL(caminhoArquivo);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("HEAD");
                            conn.connect();
                            tamanhoArquivo = conn.getContentLengthLong(); // -1 se não informado
                            conn.disconnect();

                            // Se não conseguir pegar, é impossível calcular sem baixar o arquivo
                            if (tamanhoArquivo < 0) {
                                throw new ErroEnvioEmail("Não foi possível determinar o tamanho do anexo: " + arquivo.getNome());
                            }

                        } catch (IOException e) {
                            throw new ErroEnvioEmail("Erro verificando tamanho do anexo URL: " + arquivo.getNome());
                        }

                    } else {
                        File file = new File(caminhoArquivo);
                        if (!file.exists() || !file.isFile()) {
                            throw new ErroEnvioEmail("Arquivo não encontrado: " + arquivo.getNome());
                        }
                        tamanhoArquivo = file.length();
                    }

                    tamanhoTotal += tamanhoArquivo;
                    if (tamanhoTotal > LIMITE) {
                        throw new ErroEnvioEmail("O total de anexos excede 10 MB. Envio abortado.");
                    }
                }
            }

            if (pAnexos != null) {
                for (ComoArquivo arquivo : pAnexos) {

                    String caminhoArquivop = arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo).getComoArquivoDeEntidade().getCaminhoArquivoLocal();
                    //  ItfCampoInstArquivoEntidade campoArquivoInstanciado = arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo).getComoArquivoDeEntidade();
                    try {
                        if (caminhoArquivop.startsWith("http")) {
                            EmailAttachment attachment = new EmailAttachment();
                            attachment.setURL(new URL(caminhoArquivop));
                            if (arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo).getValor() != null) {
                                attachment.setName(arquivo.getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo).getValor().toString());
                            } else {
                                attachment.setName(arquivo.getNome());
                            }
                            attachment.setDisposition(EmailAttachment.ATTACHMENT);
                            email.attach(attachment);

                        } else {
                            EmailAttachment attachment = new EmailAttachment();
                            attachment.setName(arquivo.getNome());
                            attachment.setPath(caminhoArquivop);
                            attachment.setDisposition(EmailAttachment.ATTACHMENT);
                            email.attach(attachment);
                        }
                    } catch (Throwable t) {
                        System.out.println(caminhoArquivop);
                        throw new ErroEnvioEmail("Erro anexando arquivo: " + arquivo.getNome());
                    }
                }
            }

            //email.attach(); // adiciona o anexo à mensagem
            String resp = email.send();// envia o e-mail
            System.out.println(resp);

            return pConteudo;

        } catch (EmailException t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando email" + t.getMessage(), t);
            return null;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando email" + t.getMessage(), t);
            return null;
        }
    }

    public static String enviarEmailComAnexoV2(JSONObject pContatos, String pAssunto, String pConteudo, List<ComoArquivo> pAnexos) throws ErroEnvioEmail {

        return enviarEmailComAnexoV2(UtilCRCEmail.getServidorDisparoEmail(), pContatos, null, pAssunto, pConteudo, pAnexos);
    }

    public static String getIdentificadorUnicoImapMessage(IMAPMessage pMensagem) {
        String codigo = null;
        try {
            codigo = pMensagem.getMessageID();
            if (codigo == null) {
                codigo = String.valueOf((pMensagem.getSubject() + pMensagem.getSender().toString() + pMensagem.getReceivedDate().getTime()).hashCode());
                return codigo;
            } else {
                return codigo;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando identificado unico da mensagem", t);
            return codigo;
        }

    }
}
