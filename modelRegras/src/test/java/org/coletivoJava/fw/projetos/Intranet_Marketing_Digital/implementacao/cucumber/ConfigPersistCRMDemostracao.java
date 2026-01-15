/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber;

import br.org.coletivojava.erp.notificacao.padrao.model.statusNotificacao.FabStatusNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.FabAtividadeCRMAutoexecucao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.FabIntegracaoNativa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabSastisfacaoClienteResolucao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.FabStatusChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.estrategiaResponsavelTipoRelacionamento.FabTipoEstrategiaResponsalvelRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.FabMetasRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.FabTipoRelacionamentoMarketingDigital;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento.FabResultadoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabTipoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabDadosIniciais;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabTipoChamado;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabTipoEmpresa;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabTipoOrigemDemo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.ItfConfigSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.FabUnidadesFederativas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabContextoDeReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;

/**
 *
 * @author salvio
 */
public class ConfigPersistCRMDemostracao implements ItfConfigSBPersistencia {

    @Override
    public String bancoPrincipal() {
        return "CRMCasaNova";
    }

    @Override
    public String[] bancosExtra() {
        return new String[0];
    }

    @Override
    public String formatoDataBanco() {
        return UtilCRCDataHora.datahoraSistemaFr.toString();
    }

    @Override
    public String formatoDataUsuario() {
        return UtilCRCDataHora.horaUsuarioFr.toString();
    }

    @Override
    public String pastaImagensJPA() {
        return "/img";
    }

    @Override
    public void criarBancoInicial() {

    }

    @Override
    public Class<? extends ComoFabrica>[] fabricasRegistrosIniciais() {
        return new Class[]{
            FabSatisfacaoCliente.class,
            FabUnidadesFederativas.class,
            FabTipoEmpresa.class,
            FabTipoDadoCRM.class,
            FabPorteProspectoEmpresa.class,
            FabModulosCRM.class,
            FabGruposIntranetCasaNova.class,
            FabUsuarioPadraoMarketingParaWeb.class,
            FabResultadoTipoRelacionamento.class,
            FabStatusEnvioEmail.class,
            FabStatusAtividade.class,
            FabCategoriaEmailRecebido.class,
            FabTipoPesquisaLeads.class,
            FabTipoEstrategiaResponsalvelRelacionamento.class,
            FabContextoDeReserva.class,
            FabStatusReservaHorario.class,
            FabStatusChamado.class,
            FabIntegracaoNativa.class,
            FabSastisfacaoClienteResolucao.class,
            FabStatusSolicitacao.class,
            FabTipoSolicitacao.class,
            FabStatusChatBot.class,
            FabMetasRelacionamento.class,
            FabAtividadeCRMAutoexecucao.class,
            FabTipoAgendamentoPublicoCrm.class,
            FabTipoNotificacao.class,
            FabTipoRelacionamentoMarketingDigital.class,
            FabTipoOrigemDemo.class,
            FabDadosIniciais.class,
            FabUsuarioPadraoMarketingParaWeb.class,
            FabTipoChamado.class,
            FabStatusNotificacao.class,};
    }
}
