/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class TesteEmail extends TesteCRMCarameloCode {

    @Test
    public void listasEmail() {
        try {
            List<EnvioEmail> emailsEnviados = UtilSBPersistencia.getListaRegistrosByHQL(" from EnvioEmail where prospecto_id>? order by datadisparo", 100, getEMTeste(), 0);
            List<EmailRecebido> emailsRecebidos = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailRecebido where prospecto_id>? order by dataHoraRecebimento", 100, getEMTeste(), 0);

            List<ComoEmailSimples> emails = new ArrayList<>();
            emails.addAll(emailsEnviados);
            emails.addAll(emailsRecebidos);

            Comparator<ComoEmailSimples> ordenadorData = new Comparator<ComoEmailSimples>() {
                @Override
                public int compare(ComoEmailSimples o1, ComoEmailSimples o2) {
                    if (o1 == null) {
                        return 1;
                    }
                    if (o2 == null) {
                        return 2;
                    }
                    return (o1.getDataHoraEmailArmazenado().getTime() < o2.getDataHoraEmailArmazenado().getTime() ? -1
                            : (o1.getDataHoraEmailArmazenado().getTime() == o2.getDataHoraEmailArmazenado().getTime() ? 0 : 1));
                }
            };
            Collections.sort(emails, ordenadorData);
            System.out.println(emails);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    public void teste() {
        try {
            String host = "mail.casanovadigital.com.br";
            String user = "suporte@casanovadigital.com.br";
            String password = "suporte@123";
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", "mail.casanovadigital.com.br");
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                System.out.println("Data" + message.getSentDate());

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
