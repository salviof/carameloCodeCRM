/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.InfoAgendaAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@InfoAgendaAdmin(acao = FabAcaoAdminAgenda.COLABORADOR_COM_AGENDA_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Colaboradores com agenda", tags = {"ColabCAG"})
public class PgColaboradoresComAgenda extends MB_paginaCadastroEntidades<DisponibilidadeAtdmtPublico> {

    @Override
    protected void listarDados(boolean mostrarInativos) {
        super.listarDados(false); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @PostConstruct
    public void inicio() {
        getAcoesRegistros().clear();
        adicionarAcaoDeEntidade(FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_FRM_LISTAR_DISPONIBILIDADES);
        adicionarAcaoDeEntidade(FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_DISPONIVEIS);
    }

}
