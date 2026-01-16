/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.util;

import br.org.coletivojava.erp.notificacao.padrao.controller.FabAcaoNotificacaoPadraoSB;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.FabAcaoCrmAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.FabAcaoCRMIA;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabAcaoAgendamentoPublicoCRMPlugin;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.FabAcaoOrcamento;

/**
 *
 * @author salvioF
 */
public abstract class UtilModulosCRM {

    public static Class<? extends ComoFabricaAcoes>[] pAcoesDoSistema() {
        return new Class[]{FabAcaoCRMAtendimento.class, FabAcaoCrmAdmin.class,
            FabAcaoCrmAplicacao.class, FabAcaoOrcamento.class,
            //FabAcaoAgencia.class,
            FabAcaoAgendaMentoPublico.class, FabAcaoAgendamentoPublicoCRMPlugin.class,
            FabAcaoCrmAtendimentoAgenda.class, FabAcaoCRMCliente.class, FabAcaoAcessoAnonimoIntranet.class, FabAcaoCRMConvidado.class,
            FabAcaoNotificacaoPadraoSB.class, FabAcaoCRMIA.class

        };
    }

}
