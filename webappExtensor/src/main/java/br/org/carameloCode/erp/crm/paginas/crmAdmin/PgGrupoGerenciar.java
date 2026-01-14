/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.quadroPermissao.QuadroPermissaoGrupo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PagniaGrupoPermissao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Grupos", tags = "Grupos de Usuários")
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.GRUPO_MB_GERENCIAR)
public class PgGrupoGerenciar extends MB_PagniaGrupoPermissao<GrupoUsuarioSB> {

    public PgGrupoGerenciar() {
        super(FabAcaoCrmAdmin.GRUPO_MB_GERENCIAR.getRegistro().getAcaoDeGestaoEntidade());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.GRUPO_FRM_EDITAR_PERMISSOES.getRegistro());
    }

    @Override
    public GrupoUsuarioSB getEntidadeSelecionada() {
        return super.getEntidadeSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEntidadeSelecionada(GrupoUsuarioSB pEntidadeSelecionada) {

        if (pEntidadeSelecionada != null && pEntidadeSelecionada.getId() != null && pEntidadeSelecionada.getId() > 0) {
            if (getEntidadeSelecionada() == null || !getEntidadeSelecionada().equals(pEntidadeSelecionada)) {

                permissoesGrupo.clear();
                gestoesPermissionaveis.values().forEach((gestao) -> {
                    permissoesGrupo.add(new QuadroPermissaoGrupo((GrupoUsuarioSB) pEntidadeSelecionada, gestao));
                });
            }
        }
        super.setEntidadeDireto(pEntidadeSelecionada); //chamada super do metodo (implementação classe pai)
    }

    @Override
    public ItfRespostaAcaoDoSistema autoExecAcaoController(ComoEntidadeSimples pEntidade) {
        FabAcaoCrmAdmin acao = getEnumAcaoAtual();
        switch (acao) {
            case GRUPO_CTR_SALVAR_PERMISSOES:
                return ModuloCRMAdmin.grupoSalvarPermissoes(permissoesGrupo).dispararMensagens();
        }
        return super.autoExecAcaoController(pEntidade);
    }

}
