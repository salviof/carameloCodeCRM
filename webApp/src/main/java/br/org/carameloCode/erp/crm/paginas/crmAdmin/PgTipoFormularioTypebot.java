/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "FRMTYPEBOT", tags = {"tipo formulario typebot"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_MB_GESTAO)
public class PgTipoFormularioTypebot extends MB_paginaCadastroEntidades<TipoFormulario> {

    @PostConstruct
    public void inicio() {
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_FRM_LISTAR);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_FRM_EDITAR);

//        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_FRM_VISUALIZAR);
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {
        return super.getAcoesRegistros(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
