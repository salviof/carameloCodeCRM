package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;

@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TELEFONE_VOIP_MB_GESTAO)
@InfoPagina(nomeCurto = "TELPABX", tags = {"telefone"})
@ViewScoped
public class PgTelefone extends MB_paginaCadastroEntidades<Telefone> {

}
