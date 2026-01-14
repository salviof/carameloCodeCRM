/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabUsuarioPadraoMarketingParaWeb implements ComoFabrica {

    SALVIO_ADMIN,
    ATENDIMENTO,
    USUAIRO_CONVIDADO,
    ATENDIMENTO2,;

    @Override
    public UsuarioCRM getRegistro() {
        UsuarioCRM novoUsuario = new UsuarioCRM();
        novoUsuario.prepararNovoObjeto();
        switch (this) {
            case SALVIO_ADMIN:
                novoUsuario.setId(1l);
                novoUsuario.setNome("Sálvio Furbino");
                novoUsuario.setEmail("salvio@casanovadigital.com.br");
                novoUsuario.setSenha("123");
                novoUsuario.setAtivo(true);
                novoUsuario.setGrupo(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro());
                break;
            case ATENDIMENTO:
                novoUsuario.setId(2l);
                novoUsuario.setNome("Naidoca");
                novoUsuario.setEmail("atendimento@casanovadigital.com.br");
                novoUsuario.setSenha("123");
                novoUsuario.setAtivo(true);
                novoUsuario.setGrupo(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro());
                break;
            case USUAIRO_CONVIDADO:
                novoUsuario.setId(3l);
                novoUsuario.setNome("Convidado");
                novoUsuario.setGrupo(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro());
                novoUsuario.setEmail("convidado@casanovadigital.com.br");
                novoUsuario.setSenha(String.valueOf("senhgaSecreta".hashCode()));
                break;
            case ATENDIMENTO2:
                novoUsuario.setId(4l);
                novoUsuario.setNome("Camila");
                novoUsuario.setEmail("marketing@casanovadigital.com.br");
                novoUsuario.setSenha("123");

                break;

            default:
                throw new AssertionError(this.name());

        }
        return novoUsuario;

    }
}
