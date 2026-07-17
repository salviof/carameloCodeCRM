/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.crm.paginas.moduloWeb.ModuloAcoesWebCRM;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.ModuloAgendamentoPublico;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.ModuloCrmAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoSolicitacoes;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.ModuloCRM_IA;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPermissaoCRMCarameloCodePadrao;
import br.org.carameloCode.erp.modulo.notificacao.controller.ModuloNotificacao;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ModuloAgendamentoPublicoPluginCRM;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.ModuloPluginCrmOrcamento;

/**
 *
 * @author sfurbino
 */
public class ConfigPermissaoCRMWeb extends ConfigPermissaoCRMCarameloCodePadrao {

    public ConfigPermissaoCRMWeb() {
        super(new Class[]{ModuloCRMAdmin.class, ModuloCRMAtendimento.class, ModuloCRMAplicacao.class,
            ModuloCRMAtendimentoEmail.class,
            ModuloPluginCrmOrcamento.class,
            ModuloAgendamentoPublico.class, ModuloAgendamentoPublicoPluginCRM.class,
            ModuloCrmAgenda.class,
            ModuloCRMCliente.class, ModuloCRMEmail.class, ModuloCRMAtendimentoChamado.class,
            ModuloNotificacao.class, ModuloCRM_IA.class, ModuloCRMAtendimentoSolicitacoes.class, ModuloAcoesWebCRM.class});
    }

}
