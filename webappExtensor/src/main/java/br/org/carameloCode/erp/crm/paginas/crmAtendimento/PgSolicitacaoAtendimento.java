/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author salvio
 */
@InfoPagina(nomeCurto = "Solicitação Equipe", tags = {"Solicitações"})
@Named
@ViewScoped
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_MB_GESTAO)
public class PgSolicitacaoAtendimento extends MB_paginaCadastroEntidades<Solicitacao> implements ItfPaginaComModalProspecto {

    @InfoParametroURL(nome = "prPessoa", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class, obrigatorio = true)
    private ParametroURL prPessoa;

    @InfoParametroURL(nome = "prContatoCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ContatoProspecto.class, obrigatorio = false)
    private ParametroURL prContatoSolicitacao;

    @InfoParametroURL(nome = "prUsuarioEquipe", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prContatoUsuarioEquipe;

    @InfoParametroURL(nome = "prSolicitacao", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Solicitacao.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL prSolicitacao;

    private Pessoa pessoa;
    private UsuarioCRM usuarioEquipe;
    private ContatoProspecto contatoCliente;

    private CategoriaArquivoEquipe categoriaEquipe;

    @Override
    protected void autoexecEntidadeNova() {
        if (pessoa == null) {
            definirPArametros();
        }
        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
        switch (acao) {
            case SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE:
            case SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE:
                try {
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());
                    if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
                    }
                    {
                        try {
                            getEntidadeSelecionada().prepararNovoObjeto(pessoa, usuarioEquipe);
                        } catch (ErroPreparandoObjeto ex) {
                            Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE:
            case SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE: {
                try {
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());
                    if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
                    }
                    {
                        try {
                            getEntidadeSelecionada().prepararNovoObjeto(pessoa, usuarioEquipe);
                        } catch (ErroPreparandoObjeto ex) {
                            Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;

            default:
                super.autoexecEntidadeNova();
        }
    }

    private void definirPArametros() {
        if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
            pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
        }
        if (getParametroInstanciado(prContatoUsuarioEquipe).isValorDoParametroFoiConfigurado()) {
            usuarioEquipe = UtilSBPersistencia.loadEntidade((UsuarioCRM) getParametroInstanciado(prContatoUsuarioEquipe).getValor(), getEMPagina());
        }
        if (getParametroInstanciado(prContatoSolicitacao).isValorDoParametroFoiConfigurado()) {
            contatoCliente = UtilSBPersistencia.loadEntidade((ContatoProspecto) getParametroInstanciado(prContatoSolicitacao).getValor(), getEMPagina());
        }
        if (getParametroInstanciado(prSolicitacao).isValorDoParametroFoiConfigurado()) {
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prSolicitacao).getValor(), getEMPagina()));
            if (getEntidadeSelecionada() instanceof SolicitacaoArquivoEquipe) {
                categoriaEquipe = getEntidadeSelecionada().getComoSolicitacaoArquivoEquipe().getCategoriaArqEquipe();
            }
        }
    }

    @Override
    public void atualizarEntidadeSelecionada() {
        super.atualizarEntidadeSelecionada();
        if (categoriaEquipe != null) {
            categoriaEquipe = UtilSBPersistencia.loadEntidade(categoriaEquipe, getEMPagina());
        }
    }

    @PostConstruct
    public void inicio() {
        definirPArametros();

    }

    public UsuarioCRM getUsuarioEquipe() {
        return usuarioEquipe;
    }

    public ContatoProspecto getContatoCliente() {
        return contatoCliente;
    }

    public void onModalProspectoClose(SelectEvent event) {

    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {

    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public CategoriaArquivoEquipe getCategoriaEquipe() {
        return categoriaEquipe;
    }

}
