package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.enterprise.context.SessionScoped;
import com.super_bits.modulosSB.webPaginas.controller.listenners.ItfDefinicaoUrlHostDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
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
                UsuarioCRM usuario = (UsuarioCRM) pAcesso.getUsuario();
                if (usuario.isUmUsuarioDoCliente()) {
                    return "https://atendimento.casanovadigital.com.br";
                } else {
                    return "https://crm.casanovadigital.com.br";
                }
            }
        }

        return FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO.getValorParametroSistema();
    }

}
