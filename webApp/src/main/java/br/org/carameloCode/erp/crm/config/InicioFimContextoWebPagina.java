/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.api.FabConfigErpCRM;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.DocsClienteDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.DocsEquipeDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoFormLead.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estilo.EstiloVisualizacaoProspecto;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.integracao.IntegracaoManual;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.StatusIntegracao;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ItfInicioFimAppWP;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.integracoes.modelController.socialAutenticador.FabTipoAutenticacaoSocial;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.FabConfigModuloWebAppGenerico;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.SessionCookieConfig;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;

/**
 *
 * @author Salvio
 */
public class InicioFimContextoWebPagina implements ItfInicioFimAppWP {

    private static Thread acoesInicioSistema;

    @Override
    public void inicio() {

        try {
            System.out.println("ConfigSBPaginas");
            SBCore.configurar(
                    new ConfigCoreWebAppCrmCarameloCode(ConfiguradorCoreDeProjetoWebWarAbstrato.contextoDoServlet),
                    SBCore.ESTADO_APP.HOMOLOGACAO
            );
            System.out.println("############### FIM CONFIG core");
            try {

                SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode());
                SBPersistencia.criarRegistrosIniciais();
            } catch (Throwable t) {
                System.out.println("FALHA CRIANDO JPA");
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando JPA", t);
            }

            String usuarioAdmin = FabConfigErpCRM.EMAIL_USUARIO_AMDIN.getValorParametroSistema();
            if (SBCore.getServicoPermissao().getUsuarioByEmail(usuarioAdmin) == null) {

                UsuarioCRM novoUsuario = new UsuarioCRM();
                novoUsuario.setNome(FabConfigErpCRM.NOME_USUARIO_ADMIN.getValorParametroSistema());
                novoUsuario.setEmail(FabConfigErpCRM.EMAIL_USUARIO_AMDIN.getValorParametroSistema());
                novoUsuario.setSenha(UtilCRCStringGerador.getStringRandomicaTokenAleatorio(48));
                novoUsuario.setGrupo(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro());
                UtilSBPersistencia.mergeRegistro(novoUsuario);

            }

            System.out.println("############### FIM CONFIG JPA");
            SBWebPaginas.configurar(new ConfigWP_CRM_CarameloCode());
            System.out.println("############### FIM CONFIG WEBPAGINAS");
            System.out.println(SBWebPaginas.isAmbienteConfigurado());
            SBCore.adicionarFabricaObjetoEstatico(FabTipoAutenticacaoSocial.class);
            SBCore.getServicoPermissao().persitirMergePermissoes();
            if (!SBWebPaginas.isAmbienteConfigurado()) {
                throw new UnsupportedOperationException("Falha configurando webpagina");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Impossível acessar o arquivo de configuração básico da  aplicação" + t.getMessage(), t);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InicioFimContextoWebPagina.class.getName()).log(Level.SEVERE, null, ex);
        }
        MapaObjetosProjetoAtual.adcionarObjeto(EstiloVisualizacaoProspecto.class);
        MapaObjetosProjetoAtual.adcionarObjeto(StatusIntegracao.class);
        MapaObjetosProjetoAtual.adcionarObjeto(ContatoAnonimoDadoTansitorio.class);
        MapaObjetosProjetoAtual.adcionarObjeto(PesquisaAtividade.class);
        MapaObjetosProjetoAtual.adcionarObjeto(DocsClienteDaCategoria.class);
        MapaObjetosProjetoAtual.adcionarObjeto(DocsEquipeDaCategoria.class);
        MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoLink.class);
        MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoManual.class);
        //   ModuloCRMAplicacao.formularioSincronizar();

        acoesInicioSistema = new Thread() {
            @Override
            public void run() {
                MapaHorariosDisponiveis.loadReservasEDisponibilidadesPersistidos();
                //ModuloCRMAplicacao.inicializarTarefasAgendadas();
                //  ModuloCRMAplicacao.relacionamentoPorInerciaTodos();
                //  ModuloCRMAplicacao.receberEmailsTodos();
                //  ModuloCRMAdmin.rocketChatAtualizarUsuarios();
            }
        };

        acoesInicioSistema.start();
        //  SBCore.getServicoPermissao().persitirMergePermissoes();

    }

    @Override
    public void fim() {
        System.out.println("FIm do contexto de ->" + SBCore.getNomeProjeto());
    }

    @Override
    public void definirConfiguracoesDeCookie(SessionCookieConfig pSEssao) {
        if (SBCore.isEmModoProducao()) {
            pSEssao.setDomain(".casanovadigital.com.br");
            pSEssao.setPath("/");
            pSEssao.setHttpOnly(true);
            pSEssao.setSecure(true); // Requer HTTPS
        } else {
            try {
                URL url = new URL(FabConfigModuloWebAppGenerico.URL_DOMINIO_APLICACAO.getValorParametroSistema());
                //    pSEssao.setPath("/");
                //pSEssao.setDomain(url.getHost());
                //   pSEssao.setDomain(null);
                //   pSEssao.setDomain("office.casanovadigital.com.br");

                pSEssao.setHttpOnly(true);
                pSEssao.setSecure(false); // Requer HTTPS
            } catch (MalformedURLException ex) {
                Logger.getLogger(InicioFimContextoWebPagina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
