/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_MB_GESTAO)
@InfoPagina(nomeCurto = "TWTZP", tags = {"Tipo Mensagem Whatsapp"})
@ViewScoped
public class PgTipoMsgMktWhatsapp extends MB_paginaCadastroEntidades<TipoMensagemMktWhatsApp> {

    @PostConstruct
    public void inicio() {
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_FRM_LISTAR);
    }
}
