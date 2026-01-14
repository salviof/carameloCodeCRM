/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.util.UtilSBWPComponentePrimeModel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade.FluxoDeAtividades;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.model.diagram.DefaultDiagramModel;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "Organograma", tags = {"Organograma"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.ORGANOGRAMA_FLUXO_MB)
@Named
@ViewScoped
public class PgOrganograma extends MB_PaginaConversation {

    private FluxoDeAtividades fluxo;
    private DefaultDiagramModel organograma;

    @PostConstruct
    public void init() {
        try {
            fluxo = new FluxoDeAtividades(UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMPagina()));
            organograma = UtilSBWPComponentePrimeModel.getDiagramModel(fluxo);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando organograma", t);
        }
    }

    public FluxoDeAtividades getFluxo() {
        return fluxo;
    }

    public DefaultDiagramModel getOrganograma() {
        return organograma;
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return fluxo;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        fluxo = (FluxoDeAtividades) pBeanSimples;
    }

}
