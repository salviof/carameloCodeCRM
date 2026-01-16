/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.config;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.FabAcaoCRMIA;
import br.org.coletivojava.erp.notificacao.padrao.model.statusNotificacao.FabStatusNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabSastisfacaoClienteResolucao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.FabStatusChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.estrategiaResponsavelTipoRelacionamento.FabTipoEstrategiaResponsalvelRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento.FabResultadoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabTipoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.ItfConfigSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.FabUnidadesFederativas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabContextoDeReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;

/**
 *
 * @author Salvio
 */
public class ConfigPercistenciaCrmCarameloCode implements ItfConfigSBPersistencia {

    @Override
    public String bancoPrincipal() {
        return "CRMCarameloCode";
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
            // FabIntegracaoNativa.class,
            FabSastisfacaoClienteResolucao.class,
            FabStatusSolicitacao.class,
            FabTipoSolicitacao.class,
            FabStatusChatBot.class,
            FabStatusNotificacao.class,
            FabTipoNotificacao.class

//    FabAtividadeCRMAutoexecucao.class
        };
    }

}
