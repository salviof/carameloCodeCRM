/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.menu;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenuSB;
import java.util.ArrayList;
import java.util.List;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.FabAcaoOrcamento;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;
import org.coletivojava.fw.api.objetoNativo.view.menu.SessaoMenuSB;

/**
 *
 * @author desenvolvedor
 */
public enum FabMenuIntranetCasaNova implements ComoFabricaMenu {

    WEBMAIL_B2B_ADMIN,
    WEBMAIL_B2B_ATENDIMENTO,
    CRM_ADMIN,
    CRM_ATENDIMENTO,
    CLIENTE,
    DIRETOR_DE_CONTEUDO_E_MARKETING,
    REDATOR,
    DIRETOR_DE_NEGOCIOS,
    DESIGNER,
    CONVIDADO;

    @Override
    public List<ComoMenuSB> getTodosMenus() {
        return new ArrayList<>();
    }

    @Override
    public ComoMenuSB getRegistro() {
        return getMenuPrincipal();

    }

    @Override
    public ComoMenuSB getMenuPrincipal() {
        switch (this) {
            case CRM_ADMIN:
                ComoMenuSB menu = new MenuSBFW();

                menu.addSessao(FabMenuSessoesIntranetMarketing.PROSPECTOS.getRegistro());
                menu.addSessao(FabMenuSessoesIntranetMarketing.ORCAMENTOS.getRegistro());
                menu.addSessao(FabMenuSessoesIntranetMarketing.ATIVIDADES.getRegistro());
                menu.addSessao(FabMenuSessoesIntranetMarketing.MINHA_AGENDA.getRegistro());
                return menu;

            case CRM_ATENDIMENTO:
                ComoMenuSB menuAtendimento = new MenuSBFW();
                menuAtendimento.addSessao(FabMenuSessoesIntranetMarketing.PROSPECTOS.getRegistro());
                menuAtendimento.addSessao(FabMenuSessoesIntranetMarketing.EMAIL.getRegistro());
                menuAtendimento.addSessao(FabMenuSessoesIntranetMarketing.ORCAMENTOS.getRegistro());
                return menuAtendimento;
            case CONVIDADO:
                ComoMenuSB menuConvidado = new MenuSBFW();
                menuConvidado.addSessao(FabMenuSessoesIntranetMarketing.MENU_CONVIDADO.getRegistro());
                return menuConvidado;

            case CLIENTE:
            case DIRETOR_DE_CONTEUDO_E_MARKETING:
            case REDATOR:
            case DIRETOR_DE_NEGOCIOS:
            case DESIGNER:
            case WEBMAIL_B2B_ADMIN:
            case WEBMAIL_B2B_ATENDIMENTO:

            default:
                ComoMenuSB menuSemMenu = new MenuSBFW();
                return menuSemMenu;

        }

    }

    @Override
    public MenuSBFW getMenuSecundario() {
        switch (this) {
            case CRM_ADMIN:
                MenuSBFW menu = new MenuSBFW();

                menu.addSessao(FabSessaoMenuExtendido.CONTATO_METADADOS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.META_DADOS_LOGICOS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.SERVICOS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.DOCUMENTOS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.ATIVIDADES_RELACIONAMENTO.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.PABX_TELEFONIA.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.RESERVAS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.CHAMADOS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.COLABORADORES.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.PERSONAS_IA.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.ADMINISTRATIVO_CONFIGURACOES_GERAIS.getRegistro());
                menu.addSessao(FabSessaoMenuExtendido.INTEGRACOES.getRegistro());

                return menu;

            case DIRETOR_DE_CONTEUDO_E_MARKETING:
                break;
            case REDATOR:
                break;
            case DIRETOR_DE_NEGOCIOS:
                break;
            case DESIGNER:
                break;
            case CRM_ATENDIMENTO:
                MenuSBFW menuAtendunebti = new MenuSBFW();
                menuAtendunebti.addSessao(FabMenuSessoesIntranetMarketing.MEU_PERFIL_ADMIN.getRegistro());
                menuAtendunebti.addSessao(FabMenuSessoesIntranetMarketing.MEU_PERFIL_EMAIL_ADMIN.getRegistro());
                menuAtendunebti.addSessao(FabMenuSessoesIntranetMarketing.MEU_PERFIL_INTEGRACAO.getRegistro());
                menuAtendunebti.addSessao(FabMenuSessoesIntranetMarketing.MINHAS_ORIGENS.getRegistro());
                menuAtendunebti.addSessao(FabMenuSessoesIntranetMarketing.MINHA_AGENDA.getRegistro());

                return menuAtendunebti;

            case CLIENTE:
                return null;
            case WEBMAIL_B2B_ADMIN:
                break;
            case WEBMAIL_B2B_ATENDIMENTO:
                break;
            case CONVIDADO:
                break;

            default:
                throw new AssertionError(this.name());

        }
        MenuSBFW menu = new MenuSBFW();

        return menu;
    }
}
