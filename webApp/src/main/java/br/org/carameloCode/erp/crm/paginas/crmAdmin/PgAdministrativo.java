/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistenciaGraficos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItemGraficoTotalPorTipo;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.charts.pie.PieChartModel;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.UtilGraficoPrimefaces;

/**
 *
 * @author sfurbino
 */
@InfoPagina(nomeCurto = "AD", tags = {"Administrativo"}, acessoLivre = false)
@ViewScoped
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ADMINISTRATIVO_PAGINA_PRINCIPAL_MB)
@Named
public class PgAdministrativo extends MB_PaginaConversation {

    private List<TipoRelacionamento> listaRelacionamentos;

    private List<TipoAtividadeCRM> listaTiposatividade;

    private List<UsuarioCRM> usuarios;

    private List<MetaRelacionamento> metasRelacionamento;

    private PieChartModel pizzaMetas;

    private PieChartModel pizzaRelacionamento;

    private List<PieChartModel> pizzaUsuarios;

    private PesquisaLead pesquisaLead;

    @PostConstruct
    public void inicio() {
        pesquisaLead = new PesquisaLead();
        pesquisaLead.setUsuario((UsuarioCRM) SBCore.getUsuarioLogado());
    }

    public List<MetaRelacionamento> getMetasRelacionamento() {
        return metasRelacionamento;
    }

    public List<UsuarioCRM> getUsuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, getEMPagina());
        }
        return usuarios;
    }

    public PieChartModel getPizzaMetas() {
        if (pizzaMetas == null) {

            pizzaMetas = UtilGraficoPrimefaces.gerarGraficoPizza(UtilSBPersistenciaGraficos.getTotaisPorTipo(Pessoa.class, "status"),
                    "Empresas em metas").getGrafico();
            // getMetasRElacionamento

        }
        return pizzaMetas;
    }

    public PieChartModel getPizzaRelacionamento() {
        if (pizzaRelacionamento == null) {

            pizzaRelacionamento = UtilGraficoPrimefaces.gerarGraficoPizza(UtilSBPersistenciaGraficos.
                    getTotaisPorTipo(Pessoa.class, getListaRelacionamentos()),
                    "Empresas por  relacionamentos").getGrafico();

        }
        return pizzaRelacionamento;
    }

    public List<PieChartModel> getPizzaUsuarios() {
        if (pizzaUsuarios == null || pizzaUsuarios.isEmpty()) {
            pizzaUsuarios = new ArrayList<>();

            getListaUsuarios().forEach(us -> {

                List<ItfDadoGraficoTotal> dadosDoUsuario = new ArrayList<>();

                getMetasRElacionamento().forEach(mt -> {

                    int qtd = 0;
                    try {
                        qtd = us.getEmpresasNestaMeta(mt);
                    } catch (Throwable t) {

                    }
                    if (qtd > 0) {
                        ItemGraficoTotalPorTipo itemGrafico = new ItemGraficoTotalPorTipo(mt, qtd);
                        dadosDoUsuario.add(itemGrafico);
                    }

                });

                pizzaUsuarios.add(UtilGraficoPrimefaces.gerarGraficoPizza(dadosDoUsuario, us.getNome(), FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR).getGrafico());

            });

        }
        return pizzaUsuarios;
    }

    public List<MetaRelacionamento> getMetasRElacionamento() {
        if (metasRelacionamento == null || metasRelacionamento.isEmpty()) {
            metasRelacionamento = UtilSBPersistencia.getListaTodos(MetaRelacionamento.class, getEMPagina());
        }
        return metasRelacionamento;
    }

    public List<TipoAtividadeCRM> getListaTiposatividade() {
        if (listaTiposatividade == null || listaTiposatividade.isEmpty()) {
            listaTiposatividade = UtilSBPersistencia.getListaTodos(TipoAtividadeCRM.class, getEMPagina());
        }

        return listaTiposatividade;
    }

    public List<TipoRelacionamento> getListaRelacionamentos() {
        if (listaRelacionamentos == null || listaRelacionamentos.isEmpty()) {
            listaRelacionamentos = UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMPagina());
        }
        return listaRelacionamentos;
    }

    public List<UsuarioCRM> getListaUsuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, getEMPagina());
        }
        return usuarios;
    }

    @Override
    public Long getId() {
        return 4l;
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PesquisaLead getPesquisaLead() {
        return pesquisaLead;
    }

}
