/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@InfoPagina(nomeCurto = "Atividades", tags = {"Atividades"}, acessoLivre = false)
@ViewScoped
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ATIVIDADES_MB_GESTAO)
@Named
public class PgAtividades extends MB_paginaCadastroEntidades<AtividadeCRM> {

    private PesquisaAtividade pesquisaAtividade;

    @PostConstruct
    public void inicio() {
    }

    public PesquisaAtividade getPesquisaAtividade() {
        if (pesquisaAtividade == null) {
            pesquisaAtividade = new PesquisaAtividade();

        }
        return pesquisaAtividade;
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        List<AtividadeCRM> atividades = UtilSBPersistencia.getListaRegistrosByHQL("from " + AtividadeCRM.class.getSimpleName() + " order by id DESC", 200, getEMPagina());
        setEntidadesListadas(atividades);
    }

}
