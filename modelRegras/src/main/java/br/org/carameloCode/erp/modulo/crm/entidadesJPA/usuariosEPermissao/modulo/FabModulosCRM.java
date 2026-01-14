/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.menu.FabMenuIntranetCasaNova;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCGeradorDeID;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ComoFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;

/**
 *
 *
 * ARQUIVO DE EXEMPLO, PARA INICIO DE APLICAÇÃO.
 *
 * REFATORE ESTA CLASSE COM OS MODULOS DO SEU SISTEMA
 *
 *
 * @author Salvio Furbino
 */
public enum FabModulosCRM implements ComoFabricaModulo {

    ATENDIMENTO_CRM, ADMIN_CRM,
    CLIENTE_CONTATO,
    PRODUCAO, APLICACAO_CRM,
    /**
     * as fabricas de ações dos plugins devem ser salvas em
     * org.coletivoJava.fw.projetos.crm.plugin
     */
    PLUGIN, CONVIDADO;

    @Override
    public ItfModuloAcaoSistema getModulo() {
        ModuloIntranetCasanova modulo = new ModuloIntranetCasanova();
        modulo.setId((long) this.ordinal() + 1);
        modulo.setEnumVinculado(this);
        switch (this) {
            case ATENDIMENTO_CRM:
                modulo.setNome("Central de Relacionamento");
                modulo.setDescricao("Central de Relacionamento com o Cliente");
                break;
            case CLIENTE_CONTATO:
                modulo.setNome("Atendimento ao Cliente");
                modulo.setDescricao("Central de relacionamento do cliente");
                break;
            case PRODUCAO:
                modulo.setNome("Produção");
                modulo.setDescricao("Produção de produtos e serviços");
                break;
            case ADMIN_CRM:
                modulo.setNome("Administração de CRM");
                modulo.setDescricao("");
                break;
            case APLICACAO_CRM:
                modulo.setNome("Aplicação crm CRM");
                modulo.setDescricao("");
                break;
            case PLUGIN:
                modulo.setNome("Plugins");
                modulo.setDescricao("");
                break;
            case CONVIDADO:
                modulo.setNome("Convidado");
                modulo.setDescricao("usuários convidado");
                break;

            default:
                throw new AssertionError(this.name());

        }
        modulo.setId(UtilCRCGeradorDeID.gerarIdUnicoObejtoVinculadoAFabrica(modulo));
        return modulo;
    }

    @Override
    public ModuloIntranetCasanova getRegistro() {
        return (ModuloIntranetCasanova) getModulo();
    }

    private static final FabTipoUtilizacao tipoUtilizacaocaoPadrao
            = FabTipoUtilizacao.getTipoAtualizacaoByString(SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.TIPO_APRESENTACAO));

    @Override
    public ComoFabricaMenu getMenuPadrao() {

        switch (this) {
            case ATENDIMENTO_CRM:
                switch (tipoUtilizacaocaoPadrao) {
                    case WEBMAIL_B2B:
                        break;
                    case WEBMAIL_PESSOA_FISICA:
                        break;
                    case CRM_B2B:
                        break;
                    case CRM_PESSOA_FISICA:
                        break;
                    default:
                        return FabMenuIntranetCasaNova.CRM_ATENDIMENTO;

                }

            case ADMIN_CRM:
                switch (tipoUtilizacaocaoPadrao) {
                    case WEBMAIL_B2B:
                        break;
                    case WEBMAIL_PESSOA_FISICA:
                        break;
                    case CRM_B2B:
                        break;
                    case CRM_PESSOA_FISICA:
                        break;
                    default:
                        throw new AssertionError(tipoUtilizacaocaoPadrao.name());

                }
                return FabMenuIntranetCasaNova.CRM_ADMIN;
            case CLIENTE_CONTATO:
                break;
            case PRODUCAO:
                break;
            case APLICACAO_CRM:
                break;
            case PLUGIN:
                break;
            case CONVIDADO:
                return FabMenuIntranetCasaNova.CONVIDADO;

            default:
                return FabMenuIntranetCasaNova.CRM_ATENDIMENTO;
        }
        return null;
    }
}
