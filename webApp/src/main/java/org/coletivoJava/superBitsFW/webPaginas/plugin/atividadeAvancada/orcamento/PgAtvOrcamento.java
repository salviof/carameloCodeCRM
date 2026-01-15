/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.superBitsFW.webPaginas.plugin.atividadeAvancada.orcamento;

import br.org.carameloCode.erp.crm.paginas.crmAtendimento.MB_PaginaDeAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.coletivoJava.fw.projetos.crm.plugin.orcamento.FabAcaoOrcamento;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.InfoAcaoCRMPluginOrcamento;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.ModuloPluginCrmOrcamento;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sfurbino
 */
@InfoAcaoCRMPluginOrcamento(acao = FabAcaoOrcamento.ORCAMENTO_ATV_MB_GESTAO)
@ViewScoped
@Named
@InfoPagina(nomeCurto = "atvOrcamento", tags = {"Atividades de Orçamento"})
public class PgAtvOrcamento extends MB_PaginaDeAtividade {

    @InfoParametroURL(nome = "AtividadeGerada", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            obrigatorio = false, tipoEntidade = AtividadeCRM.class)
    private ParametroURL prAtividade;

    @Override
    public ItfParametroRequisicaoInstanciado getParametroProspecto() {
        return null;
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametroAtividade() {
        return getParametroInstanciado(prAtividade);
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParaetroCodigoAcesso() {
        return null;
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParaetroTipoAtividade() {
        return null;
    }

    public void salvarOrcamento() {
        ItfRespostaAcaoDoSistema resp = ModuloPluginCrmOrcamento.atividadeOrcamentoSalvarMerge(atividade).dispararMensagens();
        if (resp.isSucesso()) {
            renovarEMPagina();
            PrimeFaces.current().executeScript("parent.location.reload();");
        }
    }

    public void alterarOrcamentoDaAtividade() {
        EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
        // UtilSBPersistencia.iniciarTransacao(em);
        Long idOrcamento = getAtividade().getOrcamento().getId();
        UtilSBPersistencia.mergeRegistro(getAtividade());
        UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        renovarEMPagina();
    }

    public void criarNovoOrcamento() {
        ItfRespostaAcaoDoSistema resp = ModuloPluginCrmOrcamento.atividadeOrcamentoCriarNovo(atividade).dispararMensagens();
        if (resp.isSucesso()) {
            renovarEMPagina();
        }

    }

}
