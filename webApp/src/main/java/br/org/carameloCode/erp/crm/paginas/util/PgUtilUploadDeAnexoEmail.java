/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilUploadDeArquivo;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
public class PgUtilUploadDeAnexoEmail extends PgUtilUploadDeArquivo {

    private EnvioEmail email;

    @Override
    public synchronized void enviarArquivo(FileUploadEvent event) {
        try {

            if (email.getId() == null) {
                SBCore.enviarAvisoAoUsuario("Para adicionar anexos é nescessário salvar o rascunho, defina um assunto para criar um rascunho");
            } else {
                String textoAtual = email.getTexto();
                String assuntoAtual = email.getAssunto();
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                email = UtilSBPersistencia.loadEntidade(email, em);
                setEntidade(email.getProspecto());
                super.enviarArquivo(event); //To change body of generated methods, choose Tools | Templates.
                UtilSBPersistencia.fecharEM(em);
                em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                email = UtilSBPersistencia.loadEntidade(email, em);
                if (email != null && email.getProspecto() != null) {

                    Pessoa pessoa = UtilSBPersistencia.loadEntidade(email.getProspecto(), em);
                    email.setTexto(textoAtual);
                    email.setAssunto(assuntoAtual);
                    if (!email.getArquivosAnexados().contains(pessoa.getArquivos().get(0))) {
                        email.getArquivosAnexados().add(0, pessoa.getArquivos().get(0));
                        UtilSBPersistencia.iniciarTransacao(em);
                        UtilSBPersistencia.mergeRegistro(email, em);
                        UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
                    }

                }
            }
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario(t.getMessage(), FabMensagens.ALERTA);
        }

    }

    public EnvioEmail getEmail() {
        return email;
    }

    public void setEmail(EnvioEmail email) {
        this.email = email;
    }

}
