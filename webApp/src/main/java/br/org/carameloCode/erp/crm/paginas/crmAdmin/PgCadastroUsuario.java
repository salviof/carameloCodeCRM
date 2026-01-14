/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GCU", tags = {"Usuarios"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CADASTRO_USUARIO_MB_GERENCIAR)
public class PgCadastroUsuario extends MB_paginaCadastroEntidades<UsuarioCRM> {

    public PgCadastroUsuario() {
        super(FabAcaoCrmAdmin.CADASTRO_USUARIO_MB_GERENCIAR.getRegistro().getComoGestaoEntidade());
    }

    @PostConstruct
    public void inicio() {
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_EDITAR_SENHA);

        if (getEntidadesListadas() == null || getEntidadesListadas().isEmpty()) {

            listarDados();
        }
    }

    @Override
    public void listarDados() {
        listarDados(true);
    }

    @Override
    public void executarAcao(UsuarioCRM pUsuario) {

        super.executarAcao(pUsuario);

    }

}
