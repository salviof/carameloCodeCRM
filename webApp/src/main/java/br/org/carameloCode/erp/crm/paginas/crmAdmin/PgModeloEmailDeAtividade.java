package br.org.carameloCode.erp.crm.paginas.crmAdmin;

/**
 *
 * @author salvio
 */
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@InfoPagina(nomeCurto = "EMAILATIVIDADE", tags = {"Gerenciar E-mail de atividade"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_MB_GESTAO)
public class PgModeloEmailDeAtividade
        extends MB_paginaCadastroEntidades<ModeloEmail> {

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "Modelo Atividade", tipoEntidade = ModeloEmail.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL pParametro;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "Atividade", tipoEntidade = TipoAtividadeCRM.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL pParametroTPAtividade;

    @PostConstruct
    public void inicio() {
        getAcoesDoRegistroSelecionado().clear();
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_FRM_VISUALIZAR);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_FRM_EDITAR);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_CTR_REMOVER);
        if (getParametroInstanciado(pParametroTPAtividade).isValorDoParametroFoiConfigurado()) {
            ModeloEmail mod = ((TipoAtividadeCRM) getParametroInstanciado(pParametroTPAtividade).getValor()).getModeloEmail();
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(mod, getEMPagina()));

        }
    }

}
