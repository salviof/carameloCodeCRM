package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DISPARO_EM_MASSA_MB_GESTAO)
@InfoPagina(nomeCurto = "DSPM", tags = {"disparo em massa"})
@ViewScoped
public class PgDisparoEmMassa extends MB_paginaCadastroEntidades<DisparoEmMassa> {
    @PostConstruct
    public void inicio() {
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_LISTAR);
    }
}
