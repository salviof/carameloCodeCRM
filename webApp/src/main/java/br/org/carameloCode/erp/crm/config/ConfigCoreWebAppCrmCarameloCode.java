/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.coletivojava.erp.notificacao.padrao.controller.FabAcaoNotificacaoPadraoSB;
import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.ServicoDeArquivosWebAppS3;

import com.super_bits.integracoes.modelController.socialAutenticador.FabAcaoSocialAutenticador;
import com.super_bits.integracoes.modelController.socialAutenticador.TipoCredencialSocial;
import br.org.carameloCode.erp.crm.paginas.calendario.FabAcaoGoogleCalendario;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.FabAcaoCrmAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorCoreDeProjetoWebWarAbstrato;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import javax.servlet.ServletContext;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabAcaoAgendamentoPublicoCRMPlugin;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.FabAcaoOrcamento;

/**
 *
 * @author salvioF
 */
public class ConfigCoreWebAppCrmCarameloCode extends ConfiguradorCoreDeProjetoWebWarAbstrato {

    public ConfigCoreWebAppCrmCarameloCode(ServletContext contexto) {
        super(contexto);
    }

    public ConfigCoreWebAppCrmCarameloCode() {
        super(true);
    }

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        super.defineClassesBasicas(pConfiguracao);
        pConfiguracao.setCentralComunicacao(ConfigClasseComunicacaoCRM.class);

    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {
        pConfig.setClasseConfigPermissao(ConfigPermissaoCRMWeb.class);
        pConfig.setCentralDeArquivos(ServicoDeArquivosWebAppS3.class);
        pConfig.setFabricaDeAcoes(new Class[]{
            FabAcaoCRMAtendimento.class,
            FabAcaoCrmAdmin.class,
            FabAcaoCrmAplicacao.class,
            FabAcaoPaginasDoSistema.class,
            FabAcaoAcessoAnonimoIntranet.class,
            FabAcaoSocialAutenticador.class,
            FabAcaoGoogleCalendario.class,
            FabAcaoProjetoSB.class,
            FabAcaoCrmAtendimentoAgenda.class,
            FabAcaoAgendamentoPublicoCRMPlugin.class,
            FabAcaoAgendaMentoPublico.class,
            FabAcaoOrcamento.class,
            FabAcaoCRMCliente.class,
            FabAcaoCRMConvidado.class,
            FabAcaoNotificacaoPadraoSB.class
        });
        MapaObjetosProjetoAtual.adcionarObjeto(TipoCredencialSocial.class);

    }

}
