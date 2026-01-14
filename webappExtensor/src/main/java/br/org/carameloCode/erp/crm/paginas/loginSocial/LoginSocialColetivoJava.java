/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.loginSocial;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.integracoes.modelController.socialAutenticador.FabAcaoSocialAutenticador;
import com.super_bits.integracoes.modelController.socialAutenticador.FabTipoAutenticacaoSocial;
import com.super_bits.integracoes.modelController.socialAutenticador.TipoCredencialSocial;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.coletivojava.fw.utilCoreBase.UtilCRCFabrica;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class LoginSocialColetivoJava {

    private static List<TipoCredencialSocial> credenciais;

    @PostConstruct
    private void inicio() {

    }

    public List<TipoCredencialSocial> getCredenciais() {
        if (credenciais == null) {
            //credenciais = (List) UtilCRCFabrica.listaRegistros(FabTipoAutenticacaoSocial.class);
            credenciais = new ArrayList<>();
            credenciais.add((TipoCredencialSocial) FabTipoAutenticacaoSocial.GOOGLE.getRegistro());
        }
        return credenciais;
    }

    public String getUrlObterCodigoSolicitacao(TipoCredencialSocial pTipo) {
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO.getRegistro(), pTipo);

        String tipo = pTipo.getTipo().getStrIdentificacao();

        url = url.replace("Autenticador_Social//", "Autenticador_Social/" + FabTipoAutenticacaoSocial.class.getSimpleName()
                + "." + tipo + "/");
        return url;
    }

    public String getUrlReceberCodigoSolicitacao(TipoCredencialSocial pTipo) {
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_logar.getRegistro(), pTipo);

        String tipo = pTipo.getTipo().getStrIdentificacao();

        url = url.replace("Autenticador_Social//", "Autenticador_Social/" + FabTipoAutenticacaoSocial.class.getSimpleName()
                + "." + tipo + "/");
        return url;
    }

    public void autenticar(TipoCredencialSocial pTipo) {
        String url = getUrlObterCodigoSolicitacao(pTipo);
        UtilSBWP_JSFTools.vaParaPagina(url);
    }

}
