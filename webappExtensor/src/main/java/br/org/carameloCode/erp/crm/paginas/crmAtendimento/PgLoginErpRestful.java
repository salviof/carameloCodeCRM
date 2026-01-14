/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

/**
 *
 * @author salvio
 */
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.integracoes.restInterprestfull.api.FabIntApiRestIntegracaoERPRestfull;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@InfoPagina(nomeCurto = "loginRestful", tags = {"Login Restful"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.LOGIN_ERP_RESTFUL_MB_GESTAO)
public class PgLoginErpRestful extends MB_paginaCadastroEntidades<SistemaERPConfiavel> {

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "Parametro", representaEntidadePrincipalMB = true, obrigatorio = false,
            tipoEntidade = SistemaERPConfiavel.class)
    private ParametroURL parametroSistemaResful;

    private SistemaERPConfiavel sistemaErp;

    @PostConstruct
    public void inicio() {
        if (!getParametroInstanciado(parametroSistemaResful).isValorDoParametroFoiConfigurado()) {

            if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.LOGIN_ERP_RESTFUL_FRM_LOGAR_FATURA)) {
                List<SistemaERPConfiavel> sistemasErp = UtilSBPersistencia.getListaTodos(SistemaERPConfiavel.class, getEMPagina());
                Optional<SistemaERPConfiavel> pesquisaFatura = sistemasErp.stream().filter(erp -> erp.getDominio().contains("fatura")).findFirst();
                if (pesquisaFatura.isPresent()) {
                    sistemaErp = pesquisaFatura.get();
                }
            }
        } else {
            sistemaErp = (SistemaERPConfiavel) getParametroInstanciado(parametroSistemaResful).getValor();
        }
        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.LOGIN_ERP_RESTFUL_FRM_LOGAR);
    }

    public SistemaERPConfiavel getSistemaErp() {
        return sistemaErp;
    }

    public void autenticar() {
        System.out.println("Teste");
        ItfTokenGestaoOauth gestaoToken = (ItfTokenGestaoOauth) FabIntApiRestIntegracaoERPRestfull.ACOES_GET_OPCOES.getGestaoToken(sistemaErp);
        String url = gestaoToken.getUrlObterCodigoSolicitacao();
        UtilSBWP_JSFTools.vaParaPagina(url);
    }

}
