package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRMLead;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.enterprise.context.SessionScoped;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfDefinicaoUrlHostDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.FabConfigModuloWebAppGenerico;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author salvio
 */
@SessionScoped
public class DefinicaoUrlDeSessaso implements ItfDefinicaoUrlHostDeSessao {

    @Inject
    private HttpServletRequest request;

    private static String primeiroValor(String header) {
        if (header == null || header.trim().isEmpty()) {
            return null;
        }
        return header.split(",")[0].trim();
    }

    public String getIssuer(HttpServletRequest req) {
        String host = primeiroValor(req.getHeader("X-Forwarded-Host"));
        if (host == null) {
            host = req.getHeader("Host");
        }
        if (host != null) {
            host = host.toLowerCase().replaceFirst(":\\d+$", ""); // remove porta
        }
        final String urlAtual = host;
        if (host != null) {
            Optional<String> pesquisa = getListaURlsPermitidas().stream().filter(url -> url.contains(urlAtual)).findFirst();
            if (pesquisa.isPresent()) {
                return pesquisa.get();
            }
        }
        return FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO.getValorParametroSistema();
    }

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

        return getIssuer(request);
    }

    @Override
    public List<String> getListaURlsPermitidas() {
        return Lists.newArrayList("https://atendimento.casanovadigital.com.br", "http://localhost:8080", "https://crm.casanovadigital.com.br");
    }

}
