/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.menu;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivojava.fw.api.objetoNativo.view.menu.SessaoMenuSB;

/**
 *
 * @author desenvolvedor
 */
public enum FabMenuSessoesIntranetMarketing implements ComoFabrica {

    /**
     *
     */
    PROSPECTOS,
    EMAIL,
    CADASTROS,
    DOCUMENTOS,
    ATIVIDADES,
    DADOS_DINAMICOS,
    ADMINISTRATIVO,
    SEGURANCA,
    ORCAMENTOS,
    EMAIL_ADMIN,
    MEU_PERFIL_ADMIN,
    MEU_PERFIL_INTEGRACAO,
    MEU_PERFIL_EMAIL_ADMIN,
    MINHAS_ORIGENS,
    MINHA_AGENDA,
    MENU_CONVIDADO;

    @Override
    public SessaoMenuSB getRegistro() {

        switch (this) {
            case PROSPECTOS:
                AcaoDoSistema prospectos = new AcaoDoSistema();
                prospectos.setNomeAcao("Cartões");
                prospectos.setIconeAcao("fa fa fa-address-book-o");
                SessaoMenuSB sessao = new SessaoMenuSB(prospectos);
                sessao.addAcao(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_MB_GERENCIAR.getRegistro());
                sessao.addAcao(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_URGENTES.getRegistro());
                sessao.addAcao(FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_ORIGENS_PUBLICA);
                sessao.addAcao(FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_ORIGENS_PRIVADAS);
                sessao.addAcao(FabAcaoCRMAtendimento.PROSPECTO_FRM_NOVO_CADASTRO_RAPIDO.getRegistro());
                sessao.addAcao(FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_MB_GERENCIAR.getRegistro());
                return sessao;

            case CADASTROS:

                SessaoMenuSB sessaoCadastros = new SessaoMenuSB("Cadastro", FabIconeFontAwesome.REG_AGRUPAR_REGISTROS.getRegistro().getTagHtml());
                SessaoMenuSB cadastrosBasicos = new SessaoMenuSB("Usuários", "fa fa-users");
                cadastrosBasicos.addAcao(FabAcaoCrmAdmin.CADASTRO_USUARIO_MB_GERENCIAR.getRegistro());
                cadastrosBasicos.addAcao(FabAcaoCrmAdmin.GRUPO_MB_GERENCIAR.getRegistro());

                SessaoMenuSB servicosEChamados = new SessaoMenuSB("Serviços Reservas e Chamados", "fa fa-briefcase");
                servicosEChamados.addAcao(FabAcaoCrmAdmin.TIPO_CHAMADO_MB.getRegistro());

                servicosEChamados.addAcao(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_MB_GESTAO.getRegistro());
                servicosEChamados.addAcao(FabAcaoAgendaMentoPublico.TIPO_RESERVA_MB.getRegistro());
                servicosEChamados.addAcao(FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_LISTAR_TODAS.getRegistro());
                servicosEChamados.addAcao(FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_MB.getRegistro());

                SessaoMenuSB cadastroCartoes = new SessaoMenuSB("Sobre os cartões  ", "fa fa fa-address-book-o");

                cadastroCartoes.addAcao(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_MB_GERENCIAR.getRegistro());
                cadastroCartoes.addAcao(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_MB.getRegistro());
                cadastroCartoes.addAcao(FabAcaoCrmAdmin.TAG_MB.getRegistro());
                SessaoMenuSB meusDados = new SessaoMenuSB("Meus Cadastros", "fa fa-heart-o");
                meusDados.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_MB.getRegistro());
                meusDados.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_ASSINATURA_EMAIL.getRegistro());
                meusDados.addAcao(FabAcaoCRMAtendimento.MINHAS_ORIGENS_MB_GESTAO.getRegistro());
                meusDados.addAcao(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_ESCOPO_RESERVA_CLIENTE.getRegistro());
                meusDados.addAcao(FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_MB_GESTAO.getRegistro());

                sessaoCadastros.addAcao(servicosEChamados);
                sessaoCadastros.addAcao(cadastroCartoes);
                sessaoCadastros.addAcao(meusDados);

                return sessaoCadastros;
            case ADMINISTRATIVO:
                AcaoDoSistema admin = new AcaoDoSistema();
                admin.setNomeAcao("Administrativo");
                admin.setIconeAcao("fa fa-pie-chart");
                SessaoMenuSB sessaoAdmin = new SessaoMenuSB(admin);
                sessaoAdmin.addAcao(FabAcaoCRMAtendimento.ORGANOGRAMA_FLUXO_MB.getRegistro());
                sessaoAdmin.addAcao(FabAcaoCrmAdmin.MAUTIC_MB_INTEGRACAO.getRegistro());

                sessaoAdmin.addAcao(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_MB.getRegistro());
                sessaoAdmin.addAcao(FabAcaoCrmAdmin.PROSPECTO_ADMIN_MB_GERENCIAR.getRegistro());
                sessaoAdmin.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS.getRegistro());
                sessaoAdmin.addAcao(FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_MB_GESTAO.getRegistro());
                return sessaoAdmin;
            case DOCUMENTOS:
                SessaoMenuSB sessaoDocumentos = new SessaoMenuSB("Documentos", "fa fa-file-pdf-o");
                sessaoDocumentos.addAcao(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR.getRegistro());
                sessaoDocumentos.addAcao(FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_MB.getRegistro());
                sessaoDocumentos.addAcao(FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_MB_GESTAO.getRegistro());
                sessaoDocumentos.addAcao(FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_MB_GESTAO.getRegistro());
                return sessaoDocumentos;

            case ATIVIDADES:

                SessaoMenuSB sessaoAtividades = new SessaoMenuSB("Atividades", "fa fa-sitemap");

                sessaoAtividades.addAcao(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_MB_GERENCIAR.getRegistro());
                sessaoAtividades.addAcao(FabAcaoCrmAdmin.META_RELACIONAMENTO_MB.getRegistro());
                sessaoAtividades.addAcao(FabAcaoCrmAdmin.ATIVIDADES_FRM_LISTAR_ATIVIDADES.getRegistro());
                sessaoAtividades.addAcao(FabAcaoCrmAdmin.TIPO_ATIVIDADE_MB_GERENCIAR.getRegistro());
                sessaoAtividades.addAcao(FabAcaoCrmAdmin.FORM_CHAT_MB_GESTAO);

                return sessaoAtividades;
            case EMAIL:
                SessaoMenuSB sessaoEmail = new SessaoMenuSB("E-mails ", "fa fa-sitemap");
                sessaoEmail.addAcao(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO.getRegistro());
                sessaoEmail.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_SERVIDOR_ENVIO_PERSONALIZADO.getRegistro());
                sessaoEmail.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_RECEBER_EMAILS.getRegistro());

                sessaoEmail.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_ASSINATURA_EMAIL.getRegistro());

                return sessaoEmail;
            case SEGURANCA:
                SessaoMenuSB sessaoSeguranca = new SessaoMenuSB("Segurança", "fa fa-key");
                sessaoSeguranca.addAcao(FabAcaoCrmAdmin.GRUPO_MB_GERENCIAR.getRegistro());
                sessaoSeguranca.addAcao(FabAcaoCrmAdmin.CADASTRO_USUARIO_MB_GERENCIAR.getRegistro());
                sessaoSeguranca.addAcao(FabAcaoCrmAdmin.INTEGRACOES_MB_GESTAO);
                return sessaoSeguranca;

            case ORCAMENTOS:
                SessaoMenuSB sessaoOrcamenos = new SessaoMenuSB("Orçamentos", "fa fa-money");
                sessaoOrcamenos.addAcao(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_MB_GESTAO.getRegistro());
                return sessaoOrcamenos;
            case EMAIL_ADMIN:
                SessaoMenuSB sessaoEmailaDMIN = new SessaoMenuSB("Administração E-mails", "fa fa-envelope");
                sessaoEmailaDMIN.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_RECEBER_EMAILS.getRegistro());
                sessaoEmailaDMIN.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS.getRegistro());
                sessaoEmailaDMIN.addAcao(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_LISTAR_ASSINATURAS.getRegistro());
                return sessaoEmailaDMIN;
            case MEU_PERFIL_ADMIN:
                SessaoMenuSB meuPerfilAdmin = new SessaoMenuSB("Meu Perfil", "fa fa-user-circle-o");
                meuPerfilAdmin.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR);
                meuPerfilAdmin.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_SERVIDOR_ENVIO_PERSONALIZADO);
                meuPerfilAdmin.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_TAGS);
                meuPerfilAdmin.addAcao(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_ASSINATURA_EMAIL);

                return meuPerfilAdmin;

            case MEU_PERFIL_INTEGRACAO:
                SessaoMenuSB meuPerfilIntegracao = new SessaoMenuSB("Minhas Integrações", "fa  fa-cubes");
                //meuPerfilIntegracao.
                return meuPerfilIntegracao;

            case MEU_PERFIL_EMAIL_ADMIN:
                SessaoMenuSB meuPerfilEMailAdmin = new SessaoMenuSB("Minha Caixa de Correio", "fa fa-inbox");
                meuPerfilEMailAdmin.addAcao(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO);
                meuPerfilEMailAdmin.addAcao(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ENVIADOS);
                meuPerfilEMailAdmin.addAcao(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ULTMOS_EMAILS);
                return meuPerfilEMailAdmin;
            case MINHAS_ORIGENS:
                SessaoMenuSB minhasOrigens = new SessaoMenuSB("Minhas Origens", "fa fa-users");
                minhasOrigens.addAcao(FabAcaoCRMAtendimento.MINHAS_ORIGENS_MB_GESTAO);
                return minhasOrigens;
            case MINHA_AGENDA:
                SessaoMenuSB minhaAgenda = new SessaoMenuSB("Minha Agenda", "fa fa-calendar");
                minhaAgenda.addAcao(FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_MB_GESTAO);
                minhaAgenda.addAcao(FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_FRM_HOJE);
                minhaAgenda.addAcao(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VISAO_GERAL);
                minhaAgenda.addAcao(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_OFERTAR_RESERVA);
                minhaAgenda.addAcao(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_NOVA_RESERVA);
                minhaAgenda.addAcao(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_ESCOPO_RESERVA_CLIENTE.getRegistro());
                return minhaAgenda;
            case DADOS_DINAMICOS:
                SessaoMenuSB dadosDinamicos = new SessaoMenuSB("Dados Dinâmicos", "fa fa-pencil-square-o");
                dadosDinamicos.addAcao(FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR.getRegistro());
                dadosDinamicos.addAcao(FabAcaoCrmAdmin.OPCAO_DADOS_CRM_MB_GERENCIAR.getRegistro());
                dadosDinamicos.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR.getRegistro());
                dadosDinamicos.addAcao(FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_MB_GERENCIA.getRegistro());
                dadosDinamicos.addAcao(FabAcaoCrmAdmin.TIPO_ATIVIDADE_MB_GERENCIAR.getRegistro());
                return dadosDinamicos;
            case MENU_CONVIDADO:
                SessaoMenuSB meusClientes = new SessaoMenuSB("Meus Clientes", "fa fa-heart");
                meusClientes.addAcao(FabAcaoCRMConvidado.MEUS_CLIENTES_MB_GESTAO);
                return meusClientes;

            default:
                throw new AssertionError(this.name());

        }

    }

}
