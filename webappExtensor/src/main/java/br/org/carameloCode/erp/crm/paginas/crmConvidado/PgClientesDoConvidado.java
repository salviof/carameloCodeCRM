/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmConvidado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.InfoAcaoCRMConvidado;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "CLIENTE_CV", tags = {"Clientes do convidado"})
@InfoAcaoCRMConvidado(acao = FabAcaoCRMConvidado.MEUS_CLIENTES_MB_GESTAO)
public class PgClientesDoConvidado extends MB_paginaCadastroEntidades<Pessoa> {

    private UsuarioConvidado usuarioConviado;

    @PostConstruct
    public void inicio() {
        usuarioConviado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
    }

    public UsuarioConvidado getUsuarioConvidaddo() {
        return usuarioConviado;
    }

}
