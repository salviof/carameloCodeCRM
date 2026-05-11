/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.menu;

import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenuSB;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;

/**
 *
 * @author desenvolvedor
 */
public enum FabMenuCRMCaramelo implements ComoFabricaMenu {

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

                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.PROSPECTOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.ORCAMENTOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.ATIVIDADES.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MINHA_AGENDA.getRegistro());
                return menu;

            case CRM_ATENDIMENTO:
                ComoMenuSB menuAtendimento = new MenuSBFW();
                menuAtendimento.addSessao(FabMenuSessoesMenuCRMCaramelo.PROSPECTOS.getRegistro());
                menuAtendimento.addSessao(FabMenuSessoesMenuCRMCaramelo.EMAIL.getRegistro());
                menuAtendimento.addSessao(FabMenuSessoesMenuCRMCaramelo.ORCAMENTOS.getRegistro());
                return menuAtendimento;
            case CONVIDADO:
                ComoMenuSB menuConvidado = new MenuSBFW();
                menuConvidado.addSessao(FabMenuSessoesMenuCRMCaramelo.MENU_CONVIDADO.getRegistro());
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
        MenuSBFW menu = new MenuSBFW();
        switch (this) {
            case CRM_ADMIN:

                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.CONTATO_METADADOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.META_DADOS_LOGICOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.SERVICOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.DOCUMENTOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.ATIVIDADES_RELACIONAMENTO.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.PABX_TELEFONIA.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.RESERVAS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.CHAMADOS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.COLABORADORES.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.PERSONAS_IA.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.ADMINISTRATIVO_CONFIGURACOES_GERAIS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.INTEGRACOES.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.CADASTRO_CONTATO.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCrmCarameloExtendido.FORMULARIOS_ENQUETES.getRegistro());

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

                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MEU_PERFIL_ADMIN.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MEU_PERFIL_EMAIL_ADMIN.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MEU_PERFIL_INTEGRACAO.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MINHAS_ORIGENS.getRegistro());
                menu.addSessao(FabMenuSessoesMenuCRMCaramelo.MINHA_AGENDA.getRegistro());

                return menu;

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

        return menu;
    }
}
