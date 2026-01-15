/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GODCRM", tags = {"Gerenciar opções dados CRM"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_MB_GERENCIAR)
public class PgOpcaoDadosCRM extends MB_paginaCadastroEntidades<TipoDadoCRM> {

    private final ComoAcaoDoSistema acaoListar = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_LISTAR.getRegistro();
    private final ComoAcaoDoSistema acaoSalvar = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_CTR_SALVAR_MERGE.getRegistro();
    private final ComoAcaoDoSistema acaoNovo = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_NOVO.getRegistro();
    private String parametroDePesquisa;
    @InfoParametroURL(representaEntidadePrincipalMB = true, tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "Tipo Selecionado", obrigatorio = false, tipoEntidade = TipoDadoCRM.class)
    private ParametroURL opcaoSelecionada;

    public PgOpcaoDadosCRM() {
        super(FabAcaoCrmAdmin.OPCAO_DADOS_CRM_MB_GERENCIAR.getRegistro().getComoGestaoEntidade());
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void executarAcao(TipoDadoCRM pEntidadeSelecionada) {

        super.executarAcao(pEntidadeSelecionada);

    }

    public String getParametroDePesquisa() {
        return parametroDePesquisa;
    }

    public void setParametroDePesquisa(String parametroDePesquisa) {
        this.parametroDePesquisa = parametroDePesquisa;
    }

    public ComoAcaoDoSistema getAcaoListar() {
        return acaoListar;
    }

    public ComoAcaoDoSistema getAcaoSalvar() {
        return acaoSalvar;
    }

    public ComoAcaoDoSistema getAcaoNovo() {
        return acaoNovo;
    }

}
