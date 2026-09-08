package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRMLead;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.enterprise.context.SessionScoped;
import com.super_bits.modulosSB.webPaginas.controller.listenners.ItfDefinicaoUrlHostDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.FabConfigModuloWebAppGenerico;

/**
 *
 * @author salvio
 */
@SessionScoped
public class DefinicaoUrlDeSessaso implements ItfDefinicaoUrlHostDeSessao {

    @Override
    public String gerarUrlDeAcesso(ComoSessao pAcesso) {
        if (SBCore.isEmModoProducao()) {
            if (pAcesso.isIdentificado()) {
                ComoUsuario usuario = (ComoUsuario) pAcesso.getUsuario();
                if (usuario instanceof UsuarioCRMLead) {
                    return "https://atendimento.casanovadigital.com.br";
                }
                if (usuario instanceof UsuarioCRM) {
                    if (((UsuarioCRM) usuario).isUmUsuarioDoCliente()) {
                        return "https://atendimento.casanovadigital.com.br";
                    } else {
                        return "https://crm.casanovadigital.com.br";
                    }
                }
            }
        }

        return FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO.getValorParametroSistema();
    }

}
