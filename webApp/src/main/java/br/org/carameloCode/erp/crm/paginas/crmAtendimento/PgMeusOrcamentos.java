/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "MEUSORCAMENTOS", tags = {"Meus Orçamentos"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_MB_GESTAO)
public class PgMeusOrcamentos extends MB_paginaCadastroEntidades<Orcamento> {

    @InfoParametroURL(nome = "Prospecto", tipoEntidade = PessoaJuridica.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prospectoSelecionado;

    @InfoParametroURL(nome = "Orcamento", tipoEntidade = Orcamento.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = true)
    private ParametroURL orcamentoSelecionado;

    private Pessoa pessoaSelecionada;

    public boolean isMostrarApenasOrcamentoDaPessoa() {
        return getParametroInstanciado(prospectoSelecionado).isValorDoParametroFoiConfigurado();
    }

    public Pessoa getPessoaSelecionada() {
        try {
            pessoaSelecionada = (Pessoa) getParametroInstanciado(prospectoSelecionado).getValor();
            if (pessoaSelecionada != null) {
                pessoaSelecionada = (Pessoa) UtilSBPersistencia.getRegistroByID(UtilCRCReflexaoObjeto.getClassExtraindoProxy(pessoaSelecionada.getClass().getSimpleName()), pessoaSelecionada.getId(), getEMPagina());
            }
            return pessoaSelecionada;
        } catch (Throwable t) {
            return pessoaSelecionada;
        }
    }

    @Override
    public void executarAcao(Orcamento pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @PostConstruct
    public void inicio() {
        getAcoesRegistros().clear();
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_EDITAR);
        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();

            executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO);
            return;
        }
        if (getParametroInstanciado(prospectoSelecionado).isValorDoParametroFoiConfigurado()) {
            PessoaJuridica pessoa = UtilSBPersistencia.loadEntidade((PessoaJuridica) getParametroInstanciado(prospectoSelecionado).getValor(), getEMPagina());
            boolean permitidoExibir = false;
            if (pessoa.isUmPerfilPrivado()) {
                if (pessoa.getUsuariosResponsaveis().contains(SBCore.getUsuarioLogado())) {
                    permitidoExibir = true;
                }
            } else {
                permitidoExibir = true;
            }
            if (!permitidoExibir) {
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO);
                return;
            }

        } else {
            UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());

        }
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        if (pResposta != null && !pResposta.isSucesso() && pResposta.getAcaoVinculada().getEnumAcaoDoSistema().equals(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_ENVIAR_PARA_FATURAMENTO)) {
            if (pResposta.getUrlDestino() != null) {
                PrimeFaces.current().executeScript("window.open('" + pResposta.getUrlDestino() + "','pagina',\"width=650, height=355, top=100, left=110, scrollbars=no \" )");
            }
            pResposta.setUrlDestino(null);
        }

        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();

            executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO);
            return;
        }
        if (getParametroInstanciado(prospectoSelecionado).isValorDoParametroFoiConfigurado()) {
            PessoaJuridica pessoa = UtilSBPersistencia.loadEntidade((PessoaJuridica) getParametroInstanciado(prospectoSelecionado).getValor(), getEMPagina());
            boolean permitidoExibir = false;
            if (pessoa.isUmPerfilPrivado()) {
                if (pessoa.getUsuariosResponsaveis().contains(SBCore.getUsuarioLogado())) {
                    permitidoExibir = true;
                }
            } else {
                permitidoExibir = true;
            }
            if (!permitidoExibir) {
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO);
                return;
            }
            setEntidadesListadas(pessoa.getOrcamentos());
        } else {
            UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            setEntidadesListadas(usuario.getOrcamentos());
        }

    }

    @Override
    protected void autoexecEntidadeNova() {
        setEntidadeSelecionada(new Orcamento()); //To change body of generated methods, choose Tools | Templates.
        if (getPessoaSelecionada() != null) {
            getEntidadeSelecionada().setPessoa(getPessoaSelecionada());
        }
    }

    @Override
    public Orcamento getEntidadeSelecionada() {
        return super.getEntidadeSelecionada();
    }

}
