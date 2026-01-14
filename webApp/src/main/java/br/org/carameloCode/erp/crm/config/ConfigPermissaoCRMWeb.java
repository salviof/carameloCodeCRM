/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.coletivojava.erp.notificacao.padrao.controller.ModuloNotificacao;
import br.org.carameloCode.erp.crm.paginas.moduloWeb.ModuloCRMAcoesWeb;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.ModuloCrmAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPermissaoCRMCarameloCodePadrao;

import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ModuloAgendamentoPublicoPluginCRM;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.ModuloPluginCrmOrcamento;

/**
 *
 * @author sfurbino
 */
public class ConfigPermissaoCRMWeb extends ConfigPermissaoCRMCarameloCodePadrao {

    public ConfigPermissaoCRMWeb() {
        super();
    }

}
