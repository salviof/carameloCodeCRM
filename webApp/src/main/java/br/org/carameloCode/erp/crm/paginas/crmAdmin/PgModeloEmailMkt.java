/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

/**
 *
 * @author salvio
 */
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@InfoPagina(nomeCurto = "MASSMAIL", tags = {"Gerenciar Email marketing"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_MB_GESTAO)
public class PgModeloEmailMkt
        extends MB_paginaCadastroEntidades<ModeloEmailMarketing> {

    @PostConstruct
    public void inicio() {
        getAcoesDoRegistroSelecionado().clear();
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_EDITAR_COM_IA);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_EDITAR);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_ENVIAR_PARA_MAUTIC);
    }

    @Override
    public void executarAcao(ModeloEmailMarketing pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada);
        if (getEntidadeSelecionada() != null) {
            if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_EDITAR_COM_IA)
                    || isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_ENVIAR_PARA_MAUTIC)) {
                getEntidadeSelecionada().setEditarComIA(true);
            } else {
                getEntidadeSelecionada().setEditarComIA(false);
            }
        }
    }

}
