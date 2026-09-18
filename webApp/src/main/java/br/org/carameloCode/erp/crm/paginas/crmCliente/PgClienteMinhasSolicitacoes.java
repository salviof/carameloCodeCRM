package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.SOLICITACAO_MB_GESTAO)
@InfoPagina(nomeCurto = "CLSOL", tags = {"Minhas Solicitações"})
public class PgClienteMinhasSolicitacoes extends MB_paginaCadastroEntidades<Solicitacao> {

    @InfoParametroURL(nome = "Solicitação", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Solicitacao.class, representaEntidadePrincipalMB = true)
    private ParametroURL prSolicitacao;

}
