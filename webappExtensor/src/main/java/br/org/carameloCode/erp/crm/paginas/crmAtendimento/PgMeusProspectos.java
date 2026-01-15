/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.integracao.primefaces.Agenda;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.TipoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.UtilModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_ATIVOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_URGENTES;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GMP", tags = {"Meus prospectos"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_PROSPECTOS_MB_GERENCIAR)
public class PgMeusProspectos extends MB_PaginaConversation implements ItfPaginaComModalProspecto {

    private final ComoAcaoDoSistema acaoListar = FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR.getRegistro();
    private Pessoa prospectoSelecionado;
    private List<AcaoDoSistema> acoesProspectos;
    private UsuarioCRM usuarioLogado;
    private List<OrigemProspecto> origens;

    private PesquisaLead pesquisa;
    private Agenda agenda;
    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "meta",
            tipoEntidade = MetaRelacionamento.class, obrigatorio = false)
    private ParametroURL parametroMeta;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "tipoLead",
            tipoEntidade = TipoPesquisaLead.class, obrigatorio = false)
    private ParametroURL parametroTipoPesquisa;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "origem",
            tipoEntidade = OrigemProspecto.class, obrigatorio = false)
    private ParametroURL parametroOrigem;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "origemPrivada",
            tipoEntidade = OrigemProspectoPrivado.class, obrigatorio = false)
    private ParametroURL parametroOrigemPrivada;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "usuarioSelecionado",
            tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL parametroUsuarioSelecionado;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "tagAtendimento",
            tipoEntidade = TagAtendimento.class, obrigatorio = false)
    private ParametroURL parametrotagSelecionada;

    @PostConstruct
    public void init() {
        try {
            if (SBCore.getCentralDeSessao().getSessaoAtual().isIdentificado() && !SBCore.getCentralDeSessao().getSessaoAtual().getUsuario().equals(FabUsuarioPadraoMarketingParaWeb.USUAIRO_CONVIDADO.getRegistro())) {
                if (acaoSelecionada == null) {
                    setAcaoSelecionada(acaoListar);
                }

            } else {
                setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro());
            }

            usuarioLogado = UtilModulosCRM.getUsuarioCRMLogado(getEMPagina());
            origens = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
            if (pesquisa == null) {
                pesquisa = new PesquisaLead();
                FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
                if (acao != null && pesquisa != null) {
                    switch (acao) {

                        case MEUS_PROSPECTOS_FRM_LISTAR_ATIVOS:
                            pesquisa.getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.tipopesquisa).setValorValidando(FabTipoPesquisaLeads.MEUS_LEADS.getRegistro());
                            break;
                        case MEUS_PROSPECTOS_FRM_LISTAR:
                            pesquisa.getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.tipopesquisa).setValorValidando(FabTipoPesquisaLeads.MEUS_LEADS.getRegistro());
                            break;
                        case MEUS_PROSPECTOS_FRM_LISTAR_URGENTES:
                            pesquisa.getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.tipopesquisa).setValorValidando(FabTipoPesquisaLeads.LEADS_URGENTES.getRegistro());
                            break;

                    }
                }

                if (getParametroInstanciado(parametroUsuarioSelecionado).isValorDoParametroFoiConfigurado()) {
                    if (SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                        pesquisa.setUsuario(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(parametroUsuarioSelecionado).getValor(), getEMPagina()));

                    } else {
                        pesquisa.setUsuario(usuarioLogado);
                    }
                }
            }
            executarAcaoSelecionada();
            if (getParametroInstanciado(parametroMeta).isValorDoParametroFoiConfigurado()) {
                pesquisa.setMetaRelacionamento((MetaRelacionamento) getParametroInstanciado(parametroMeta)
                        .getValor());
            }
            if (getParametroInstanciado(parametrotagSelecionada).isValorDoParametroFoiConfigurado()) {
                pesquisa.setTagAtendimento((TagAtendimento) getParametroInstanciado(parametrotagSelecionada).getValor());
            }

            if (getParametroInstanciado(parametroTipoPesquisa).isValorDoParametroFoiConfigurado()) {
                pesquisa.setTipoPesquisa((TipoPesquisaLead) getParametroInstanciado(parametroTipoPesquisa)
                        .getValor());
            }
            if (getParametroInstanciado(parametroOrigem).isValorDoParametroFoiConfigurado()) {
                pesquisa.getCampoInstanciadoByNomeOuAnotacao("origem").setValorSeValido(getParametroInstanciado(parametroOrigem).getValor());
            }

            if (getParametroInstanciado(parametroOrigemPrivada).isValorDoParametroFoiConfigurado()) {
                pesquisa.getCampoInstanciadoByNomeOuAnotacao("origem").setValorSeValido(getParametroInstanciado(parametroOrigemPrivada).getValor());
            }
            if (((TipoPesquisaLead) pesquisa.getCPinst(CPPesquisaLead.tipopesquisa).getValor()).getTipoPesquisa().equals(FabTipoPesquisaLeads.MEUS_LEADS)) {
                pesquisa.setDatainicial(null);
                pesquisa.setDatafinal(null);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando pagina 'Minhas empresas '", t);
        }

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return prospectoSelecionado;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        prospectoSelecionado = (Pessoa) pBeanSimples;
    }

    @Override
    public void onModalProspectoClose(SelectEvent event) {
        renovarEMPagina();
        Pessoa pessoa = (Pessoa) event.getObject();
        setBeanSelecionado(UtilSBPersistencia.loadEntidade(pessoa, getEMPagina()));
        try {
            int idex = pesquisa.getLeadsEncontrados().indexOf(pessoa);
            if (idex >= 0) {
                pesquisa.getLeadsEncontrados().set(idex, (Pessoa) getBeanSelecionado());
            }
        } catch (Throwable t) {

        }
        //  paginaUtil.atualizaTelaPorID("cardPessoa" + pessoa.getId());
        //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PesquisaLead getPesquisa() {
        return pesquisa;
    }

}
