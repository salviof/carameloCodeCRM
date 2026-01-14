/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConvite;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConviteAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_CTR_CONCLUIR;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;

import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
@InfoPagina(tags = {"Colaboracao"}, nomeCurto = "form")
@Named
@ViewScoped
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_MB_CONVITE)
public class PgFormularioExterno extends MB_PaginaConversation {

    @InfoParametroURL(nome = "CodigoConvite", obrigatorio = false, tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CodigoConvite.class)
    private ParametroURL prCodigoAcessoAtividade;

    @InfoMB_Bean(descricao = "Teste")
    private PessoaJuridica prospecto;
    private AtividadeCRM atividade;
    private TipoAtividadeCRM tipoAtividade;
    private CodigoConviteAtividade codigo;

    @PostConstruct
    public void inicio() {
        ItfParametroRequisicaoInstanciado parametroCodigo = getParametroInstanciado(prCodigoAcessoAtividade);
        if (parametroCodigo.isValorDoParametroFoiConfigurado()) {
            try {
                codigo = (CodigoConviteAtividade) parametroCodigo.getValor();
                atualizarFormulario();
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando código", t);
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
            }

        }
    }

    @Override
    public void executarAcaoSelecionada() {

        FabAcaoCRMAtendimento acaoAtual = (FabAcaoCRMAtendimento) getEnumAcaoAtual();
        switch (acaoAtual) {
            case FORMULARIO_CONVIDADO_CTR_CONCLUIR:
                ModuloCRMAtendimento.atividadeCRMComplementarSalvarDadosDinamicosEConcluir(atividade);

                atualizarFormulario();
                getPaginaUtil().atualizaTelaPorID(getIdAreaExbicaoAcaoSelecionada());
                break;

            default:
                super.executarAcaoSelecionada();

        }

    }

    public void atualizarFormulario() {
        ItfParametroRequisicaoInstanciado parametroCodigo = getParametroInstanciado(prCodigoAcessoAtividade);
        if (codigo == null || codigo.getAtividade() == null) {
            xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO.getRegistro()));
        } else {

            if (codigo.getNome().startsWith(parametroCodigo.getTextoEnviadoUrl().split("-")[0])) {
                if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
                    UsuarioCRM usuariocrm = FabUsuarioPadraoMarketingParaWeb.USUAIRO_CONVIDADO.getRegistro();
                    SBCore.getControleDeSessao().logarEmailESenha(usuariocrm.getEmail(), usuariocrm.getSenha());
                }

            } else {
                xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
                return;
            }
            renovarEMPagina();
            dadosDaAtividade = null;
            atividade = UtilSBPersistencia.loadEntidade(codigo.getAtividade(), getEMPagina());
            prospecto = UtilSBPersistencia.loadEntidade(codigo.getAtividade().getProspectoEmpresa(), getEMPagina());
            tipoAtividade = atividade.getTipoAtividade();
            if (atividade.getStatusAtividade().equals(FabStatusAtividade.EM_ANDAMENTO.getRegistro())) {
                if (atividade.isPermitidoConcluir()) {
                    xhtmlAcaoAtual = FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_COLETAR.getRegistro().getComoFormulario().getXhtml();
                } else {
                    xhtmlAcaoAtual = FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_COLETAR.getRegistro().getComoFormulario().getXhtml();
                }
            } else {
                xhtmlAcaoAtual = FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_OBRIGADO.getRegistro().getComoFormulario().getXhtml();
            }

        }

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return atividade;

        //          String urlChamada = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro(), getNovaAtividade().getProspectoEmpresa(), getNovaAtividade().getConvitePrincipal());
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        atividade = (AtividadeCRM) pBeanSimples;
    }

    public PessoaJuridica getProspecto() {
        return prospecto;
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public TipoAtividadeCRM getTipoAtividade() {
        return tipoAtividade;
    }

    public CodigoConvite getCodigo() {
        return codigo;
    }
    private List<DadoCRM> dadosDaAtividade;

    public List<DadoCRM> getDadosDaAtividade() {
        if (dadosDaAtividade == null || dadosDaAtividade.isEmpty()) {
            dadosDaAtividade = new ArrayList<>();
            if (!getAtividade().getTipoAtividade().isGeraNovoRelacionamento()) {
                return dadosDaAtividade;
            }
            dadosDaAtividade.addAll(getAtividade().getDadosNaoColetados());
            dadosDaAtividade.addAll(prospecto.getDadosColetadosPorTipoRelacionamento(getAtividade().getTipoAtividade().getRelacionamentoGerado()));
        }

        return dadosDaAtividade;
    }

    public void renovarSessao() {
        renovarEMPagina();
        prospecto = UtilSBPersistencia.loadEntidade(prospecto, getEMPagina());
        atividade = UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
        dadosDaAtividade = null;
        getDadosDaAtividade();
    }
}
