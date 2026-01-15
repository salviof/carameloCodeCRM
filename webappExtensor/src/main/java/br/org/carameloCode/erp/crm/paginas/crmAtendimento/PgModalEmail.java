
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalCampoSelecionadoAbstrato;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaComEntityManager;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sfurbino
 */
@ViewScoped
@Named
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MODAL_EMAIL_MB_GESTAO)
@InfoPagina(nomeCurto = "modalEmail", tags = {"modalEmail"})
public class PgModalEmail extends PgModalCampoSelecionadoAbstrato implements Serializable, ItfB_PaginaComEntityManager {

    private EnvioEmail emailSelecionado;

    @Override
    @PostConstruct
    public void inicio() {
        super.inicio();
        try {
            emailSelecionado = (EnvioEmail) getPaginaVinculada().getCampoInstSelecionado().getObjetoDoAtributo();
            ItfPaginaComModalEmail paginaOriginal = (ItfPaginaComModalEmail) getPaginaVinculada();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Chamou PgModalEmail sem configurar o campo instanciado da pagina atual", t);
        }
    }

    @Override
    public EntityManager getEMPagina() {
        if (getPaginaVinculada() != null) {
            return getPaginaVinculada().getEMPagina();
        } else {
            return null;
        }
    }

    @Override
    public ItfPaginaComModalEmail getPaginaVinculada() {
        return (ItfPaginaComModalEmail) super.getPaginaVinculada(); //To change body of generated methods, choose Tools | Templates.
    }

    public void renovarEMPagina() {
        getPaginaVinculada().renovarEntityManager();
    }

    public EnvioEmail getEmailSelecionado() {
        return emailSelecionado;
    }

    public void setEmailSelecionado(EnvioEmail emailSelecionado) {
        this.emailSelecionado = emailSelecionado;
    }

    @Override
    public void fecharModal() {
        //emailSelecionado.getContatos();
        //emailSelecionado.getContatos().stream().forEach(contato -> System.out.println(contato.getId() + "" + contato.getEmail()));
        PrimeFaces.current().dialog().closeDynamic(getCampoInstanciado());
    }

    @Override
    public void recarregarEntidadeSelecionada() {
        getPaginaVinculada().recarregarEntidadeSelecionada();
    }

}
