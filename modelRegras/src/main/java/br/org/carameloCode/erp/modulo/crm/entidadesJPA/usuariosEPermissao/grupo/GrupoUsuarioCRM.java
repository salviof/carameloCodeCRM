/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.UtilCRMSistema;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.ModuloIntranetCasanova;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = "Grupos de usuário CRM", plural = "Grupos Usuários do CRM", modulo = ERPCrm.NOME_MODULO_ERP)
public class GrupoUsuarioCRM extends GrupoUsuarioSB {

    @ManyToOne(targetEntity = ModuloIntranetCasanova.class)
    private ModuloIntranetCasanova moduloPrincipal;

    @Override
    public ModuloIntranetCasanova getModuloPrincipal() {
        return moduloPrincipal; //To change body of generated methods, choose Tools | Templates.
    }

    public void setModuloPrincipal(ModuloIntranetCasanova pModuloPrincipal) {
        if (!getModulos().contains(pModuloPrincipal)) {
            throw new UnsupportedOperationException("Erro, este módulo não faz parte dos módulos do usuário");
        }

        this.moduloPrincipal = pModuloPrincipal;
    }

    @Override
    public ComoFabricaAcoes getPaginaInicial() {
        FabTipoUtilizacao tipoUtulizacaoPadrao = UtilCRMSistema.TIPO_UTILIZACAO_PADRAO;
        switch (tipoUtulizacaoPadrao) {
            case WEBMAIL_B2B:
                if (this.equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                    return FabAcaoCRMAtendimento.EMAILS_MB_GESTAO;
                }
                return FabAcaoCRMAtendimento.EMAILS_MB_GESTAO;

            case WEBMAIL_PESSOA_FISICA:
            case CRM_B2B:
            case CRM_PESSOA_FISICA:

            default:
                return super.getPaginaInicial(); //To change body of generated methods, choose Tools | Templates.

        }

    }

}
