/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.menu;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivojava.fw.api.objetoNativo.view.menu.SessaoMenuSB;

/**
 *
 * @author salvio
 */
public enum FabMenuSessoesMenuCrmCarameloExtendido implements ComoFabrica {
    CONTATO_METADADOS,
    META_DADOS_LOGICOS,
    CADASTRO_CONTATO,
    FORMULARIOS_ENQUETES,
    SERVICOS,
    DOCUMENTOS,
    ATIVIDADES_RELACIONAMENTO,
    PABX_TELEFONIA,
    RESERVAS,
    CHAMADOS,
    COLABORADORES,
    PERSONAS_IA,
    ADMINISTRATIVO_CONFIGURACOES_GERAIS,
    INTEGRACOES,
    SEGURANCA;

    @Override
    public SessaoMenuSB getRegistro() {
        SessaoMenuSB sessao = null;
        switch (this) {
            case SERVICOS:
                sessao = new SessaoMenuSB("Serviços e Produtos", "fa fa-wrench");
                sessao.addAcao(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_MB_GESTAO.getRegistro());

                break;
            case RESERVAS:
                sessao = new SessaoMenuSB("Reservas", "fa fa-calendar-check-o");
                sessao.addAcao(FabAcaoAdminAgenda.COLABORADOR_COM_AGENDA_MB_GESTAO);
                sessao.addAcao(FabAcaoAgendaMentoPublico.TIPO_RESERVA_MB.getRegistro());
                sessao.addAcao(FabAcaoAdminAgenda.RESERVAS_ADMIN_MB_GESTAO);
                sessao.addAcao(FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_MB);

                break;
            case CHAMADOS:

                sessao = new SessaoMenuSB("Chamados", "fa fa-ticket");
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_CHAMADO_MB);

                break;
            case CONTATO_METADADOS:
                sessao = new SessaoMenuSB("Metadados de Contatos", "fa fa-database");
                sessao.addAcao(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.TAG_MB);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_EMPRESA_MB);

                break;
            case COLABORADORES:
                sessao = new SessaoMenuSB("Colaboradores", "fa fa-briefcase");
                sessao.addAcao(FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_LISTAR_ATENDENTE);
                sessao.addAcao(FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_LISTAR_GESTORES);
                sessao.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_LISTAR_ASSINATURAS);
                sessao.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS);
                break;
            case DOCUMENTOS:
                sessao = new SessaoMenuSB("Documentos ", "fa fa-file-pdf-o");
                sessao.addAcao(FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_MB);
                sessao.addAcao(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_MB_GESTAO);

                break;
            case ATIVIDADES_RELACIONAMENTO:
                sessao = new SessaoMenuSB("Atividades e e Relacionamento", "fa fa-handshake-o");
                sessao.addAcao(FabAcaoCrmAdmin.ATIVIDADES_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_ATIVIDADE_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.META_RELACIONAMENTO_MB);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCRMAtendimento.ORGANOGRAMA_FLUXO_MB);
                break;
            case PABX_TELEFONIA:
                sessao = new SessaoMenuSB("Pabx e Whatsapp", "fa fa-phone");
                sessao.addAcao(FabAcaoCrmAdmin.TELEFONE_VOIP_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_FRM_LISTAR);
                sessao.addAcao(FabAcaoCrmAdmin.DISPARO_EM_MASSA_MB_GESTAO);
                break;
            case PERSONAS_IA:
                sessao = new SessaoMenuSB("Persona IA", "fa fa-user-secret");
                sessao.addAcao(FabAcaoCrmAdmin.PERSONA_IA_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_MB_GESTAO);
                break;
            case META_DADOS_LOGICOS:
                sessao = new SessaoMenuSB("Metadados Lógicos e IA ", "fa fa-cubes");
                sessao.addAcao(FabAcaoCrmAdmin.OPCAO_DADOS_CRM_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_MB_GERENCIA);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR);

                break;
            case ADMINISTRATIVO_CONFIGURACOES_GERAIS:
                sessao = new SessaoMenuSB("Configurações Notificação ", "fa fa-cogs");

                sessao.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_LISTAR_ASSINATURAS);
                sessao.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_NOTIFICACAO_MB);

                break;
            case INTEGRACOES:
                sessao = new SessaoMenuSB("Integrações", "fa fa-plug");
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR);
                sessao.addAcao(FabAcaoCrmAdmin.INTEGRACOES_FRM_LISTAR_INTEGRACOES);
                sessao.addAcao(FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_LISTAR);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.MAUTIC_FRM_SELECIONAR_OPCOES);

                break;
            case SEGURANCA:
                sessao = new SessaoMenuSB("Segurança", "fa fa-shield");
                break;
            case CADASTRO_CONTATO:
                sessao = new SessaoMenuSB("Cadastro Manutenção", "fa fa-address-card-o");
                sessao.addAcao(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_MB);
                sessao.addAcao(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_MB);
                break;
            case FORMULARIOS_ENQUETES:
                sessao = new SessaoMenuSB("Formulários", "fa fa-clipboard");
                sessao.addAcao(FabAcaoCrmAdmin.FORM_CHAT_MB_GESTAO);
                sessao.addAcao(FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_MB_GESTAO);

                break;

            default:
                throw new AssertionError(this.name());

        }
        return sessao;
    }
}
