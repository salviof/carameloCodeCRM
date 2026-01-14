/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.loginSocial;

import com.super_bits.integracoes.modelController.socialAutenticador.FabAcaoSocialAutenticador;
import com.super_bits.integracoes.modelController.socialAutenticador.FabTipoAutenticacaoSocial;
import com.super_bits.integracoes.modelController.socialAutenticador.TipoCredencialSocial;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import org.brickred.socialauth.Permission;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.OAuthConfig;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.utilCoreBase.UtilCRCFabrica;

/**
 *
 * @author SalvioF
 */
@SessionScoped
public class SessaoSocialManager implements Serializable {

    private SocialAuthConfig config;
    private SocialAuthManager manager;
    private List<TipoCredencialSocial> credenciais;
    private String paginaSucesso;

    @PostConstruct
    public void inicio() {
        try {
            credenciais = (List) UtilCRCFabrica.listaRegistros(FabTipoAutenticacaoSocial.class);
            config = new SocialAuthConfig();

            String arquivoConfiguracao = SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto() + "/loginTerceiros.prop";
            try {
                UtilCRCArquivos.criarDiretorioParaArquivo(arquivoConfiguracao);
                if (!new File(arquivoConfiguracao).exists()) {
                    new File(arquivoConfiguracao).createNewFile();
                }
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha lendo configuração" + t.getMessage(), t);
            }
            //config.load();
            FileInputStream inputstream = (FileInputStream) UTilSBCoreInputs.getStreamByLocalFile(arquivoConfiguracao);
            Properties proppriedadesBasicas = new Properties();
            proppriedadesBasicas.load(inputstream);
            config.load(proppriedadesBasicas);

            manager = new SocialAuthManager();
            manager.setSocialAuthConfig(config);
            paginaSucesso = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICADO_SUCESSO);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando gestão de login social", t);
        }
    }

    public SocialAuthConfig getConfig() {
        return config;
    }

    public SocialAuthManager getManager() {
        return manager;
    }

    public List<TipoCredencialSocial> getCredenciais() {
        return credenciais;
    }

    public String getPaginaSucesso() {
        return paginaSucesso;
    }

}
