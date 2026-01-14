/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.dashboardAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
public class ServicoGraficosLeads implements Serializable {

    private Map<UsuarioCRM, GraficoPizzaDeMetasDoUsuario> graficosDeAtendimento = new HashMap<>();
    private Map<UsuarioCRM, GraficoPizzaTagsUsuarioAtend> graficosTagsAtendimento = new HashMap<>();

    private Map<OrigemProspecto, GraficoPizzaOrigem> graficosDeOrigem = new HashMap<>();

    private UsuarioCRM usuarioSelecionado;
    private List<MetaRelacionamento> metas;
    private EntityManager em;

    @PostConstruct
    public void inicio() {
        em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        metas = UtilSBPersistencia.getListaTodos(MetaRelacionamento.class, em);
    }

    @PreDestroy()
    public void fim() {
        UtilSBPersistencia.fecharEM(em);
    }

    public GraficoPizzaDeMetasDoUsuario getGraficoDeAtendimento(UsuarioCRM pUsuario) {
        if (!graficosDeAtendimento.containsKey(pUsuario)) {
            GraficoPizzaDeMetasDoUsuario novoGrafico = new GraficoPizzaDeMetasDoUsuario(metas, pUsuario);
            graficosDeAtendimento.put(pUsuario, novoGrafico);
        }
        return graficosDeAtendimento.get(pUsuario);
    }

    public GraficoPizzaTagsUsuarioAtend getGraficoTagsMonitoradas(UsuarioCRM pUsuario) {
        if (!graficosTagsAtendimento.containsKey(pUsuario)) {
            GraficoPizzaTagsUsuarioAtend novoGrafico = new GraficoPizzaTagsUsuarioAtend(pUsuario);
            graficosTagsAtendimento.put(pUsuario, novoGrafico);
        }
        return graficosTagsAtendimento.get(pUsuario);
    }

    public GraficoPizzaOrigem getGraficoDeOrigem(OrigemProspecto pOrigem) {
        if (!graficosDeOrigem.containsKey(pOrigem)) {
            GraficoPizzaOrigem novoGrafico = new GraficoPizzaOrigem(metas, pOrigem);
            graficosDeOrigem.put(pOrigem, novoGrafico);
        }
        return graficosDeOrigem.get(pOrigem);
    }

    public UsuarioCRM getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(UsuarioCRM usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

}
