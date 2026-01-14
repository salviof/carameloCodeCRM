/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.config;

import br.org.coletivojava.erp.notificacao.padrao.controller.ModuloNotificacao;
import br.org.carameloCode.erp.modulo.crm.config.UtilPermissoesObjetos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabSastisfacaoClienteResolucao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM.ADMIN_CRM;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM.ATENDIMENTO_CRM;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM.CLIENTE_CONTATO;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.ModuloCrmAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.menu.FabMenuIntranetCasaNova;
import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;
import com.super_bits.modulos.SBAcessosModel.controller.UtilSBControllerAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.ContatoUsuarioTransiente;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaFabricas;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ErroDadosDeContatoUsuarioNaoEncontrado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.erp.FabTipoAgenteOrganizacao;
import static com.super_bits.modulosSB.SBCore.modulos.erp.FabTipoCanalChat.INTERNO;
import static com.super_bits.modulosSB.SBCore.modulos.erp.FabTipoCanalChat.REDES_SOCIAIS;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoHumano;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenusDeSessao;
import javax.persistence.EntityManager;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ModuloAgendamentoPublicoPluginCRM;
import org.coletivoJava.fw.projetos.crm.plugin.orcamento.ModuloPluginCrmOrcamento;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class ConfigPermissaoCRMCarameloCodePadrao extends ConfigPermissoesAcessoModelAbstrato {

    public ConfigPermissaoCRMCarameloCodePadrao() {
        super(new Class[]{ModuloCRMAdmin.class, ModuloCRMAtendimento.class, ModuloCRMAplicacao.class,
            ModuloCRMAtendimentoEmail.class,
            ModuloPluginCrmOrcamento.class,
            ModuloAgendamentoPublico.class, ModuloAgendamentoPublicoPluginCRM.class,
            ModuloCrmAgenda.class, ModuloCRMCliente.class, ModuloCRMEmail.class, ModuloCRMAtendimentoChamado.class, ModuloNotificacao.class
        });
    }

    @Override
    protected boolean persistirPermissoesNoBanco() {
        super.persistirPermissoesNoBanco(); //To change body of generated methods, choose Tools | Templates.

        try {
            EntityManager emmodulo = UtilSBPersistencia.getNovoEMIniciandoTransacao();
            UtilSBPersistencia.mergeRegistro(FabModulosCRM.CLIENTE_CONTATO.getRegistro(), emmodulo);
            UtilSBPersistencia.finzalizaTransacaoEFechaEM(emmodulo);

            for (FabGruposIntranetCasaNova grupo : FabGruposIntranetCasaNova.values()) {
                UtilSBPersistencia.mergeRegistro(grupo.getRegistro());
            }

            MapaAcoesSistema.getListaTodasAcoes().stream().filter(ac -> ac.isPrecisaPermissao()).forEach(acP -> {

                if (acP.getAcaoDeGestaoEntidade().getModulo().equals(FabModulosCRM.CONVIDADO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CONVIDADO.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.SOLICITACAO_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro(), acP);
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMCliente.RESERVAS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMCliente.FORM_CHAT_INTERATIVO_MB.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMCliente.CONVERSA_MB.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMCliente.CHAMADO_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_CLIENTE.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEUS_CHAMADOS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.ATIVIDADE_MB_GERENCIAR.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_MB_GERENCIAR.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEU_DASHBOARD_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.EMAILS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MINHAS_ORIGENS_MB_GESTAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                if (acP.equals(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_TESTAR_CONFIGURACAO.getRegistro())
                        || acP.equals(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_SALVAR_CONFIGURACAO.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);

                }
                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_MB_GESTAO.getRegistro())
                        || acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEU_PERFIL_MB.getRegistro())
                        || acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.EMAILS_MB_GESTAO.getRegistro())
                        || acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro())
                        || acP.getAcaoDeGestaoEntidade().equals(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_MB_GESTAO.getRegistro())
                        || acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_MB_GERENCIAR.getRegistro())) {
                    UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                }

                if (acP.getAcaoDeGestaoEntidade().equals(FabAcaoCRMAtendimento.PROSPECTO_MB_GERENCIAR.getRegistro())) {
                    if (!acP.equals(FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA.getRegistro())
                            && !acP.equals(FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER.getRegistro())) {
                        UtilSBControllerAcessosModel.adicionarPermissao(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), acP);
                    }
                }
            });
            //EntityManager emmodulo = UtilSBPersistencia.getNovoEMIniciandoTransacao();
            //UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabModulosCRM.class, emmodulo, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            //UtilSBPersistencia.finzalizaTransacaoEFechaEM(emmodulo);

            EntityManager emstatusChamado = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabStatusChamado.class, emstatusChamado, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            UtilSBPersistencia.fecharEM(emstatusChamado);

            EntityManager emSatisfacao = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabSatisfacaoCliente.class, emSatisfacao, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            UtilSBPersistencia.fecharEM(emSatisfacao);

            EntityManager emSatisfacaoChamado = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabSastisfacaoClienteResolucao.class, emSatisfacaoChamado, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            UtilSBPersistencia.fecharEM(emSatisfacaoChamado);

            EntityManager emstatusReserva = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabStatusReservaHorario.class, emstatusReserva, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            UtilSBPersistencia.fecharEM(emstatusReserva);

            MapaObjetosProjetoAtual.adcionarObjeto(MetadadoAtendente.class);
            MapaObjetosProjetoAtual.adcionarObjeto(MetadadoUsuarioCliente.class);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando permissões", t);
            return false;
        }
    }

    @Override
    public ComoMenusDeSessao definirMenu(ComoGrupoUsuario pGrupo) {
        if (pGrupo == null) {
            return new MenusDaSessao(new MenuSBFW());
        }
        try {
            FabModulosCRM modulos = (FabModulosCRM) pGrupo.getModuloPrincipal().getEnumVinculado();

            switch (modulos) {
                case ATENDIMENTO_CRM:
                    return new MenusDaSessao(FabMenuIntranetCasaNova.CRM_ATENDIMENTO.getMenuPrincipal(), FabMenuIntranetCasaNova.CRM_ATENDIMENTO.getMenuSecundario());

                case ADMIN_CRM:
                    return new MenusDaSessao(FabMenuIntranetCasaNova.CRM_ADMIN.getMenuPrincipal(), FabMenuIntranetCasaNova.CRM_ADMIN.getMenuSecundario());

                case CLIENTE_CONTATO:
                    return new MenusDaSessao(FabMenuIntranetCasaNova.CLIENTE.getMenuPrincipal(), FabMenuIntranetCasaNova.CLIENTE.getMenuSecundario());

                case PRODUCAO:
                    return new MenusDaSessao(FabMenuIntranetCasaNova.DIRETOR_DE_CONTEUDO_E_MARKETING.getMenuPrincipal(), FabMenuIntranetCasaNova.DIRETOR_DE_CONTEUDO_E_MARKETING.getMenuSecundario());

                default:
                    throw new AssertionError(modulos.name());

            }
        } catch (Throwable t) {
            return new MenusDaSessao(new MenuSBFW());
        }

    }

    @Override
    public FabTipoAgenteOrganizacao getTipoAgente(ComoUsuario pUsuario) {
        if (pUsuario instanceof UsuarioCRM) {
            if (pUsuario instanceof UsuarioCrmCliente) {
                return FabTipoAgenteOrganizacao.CLIENTE;
            }
            if (pUsuario instanceof UsuarioConvidado) {
                return FabTipoAgenteOrganizacao.CONVIDADO;
            }
            if (pUsuario instanceof UsuarioCRM) {
                if (pUsuario.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                    return FabTipoAgenteOrganizacao.ATENDIMENTO;
                }
                if (pUsuario.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro())) {
                    return FabTipoAgenteOrganizacao.ATENDIMENTO;
                }

            }

        }
        return FabTipoAgenteOrganizacao.PUBLICO;
    }

    @Override
    public ComoContatoHumano getContatoDoUsuario(ComoUsuario pUsuairo) throws ErroDadosDeContatoUsuarioNaoEncontrado {
        ComoContatoHumano dadosDoContatoCliente = null;
        if (pUsuairo instanceof UsuarioCrmCliente) {

            dadosDoContatoCliente = (ComoContatoHumano) pUsuairo.getCPinst("contatoVinculado").getValor();

            String telefone = dadosDoContatoCliente.getCelular();
            String nome = dadosDoContatoCliente.getNome();
            String email = dadosDoContatoCliente.getEmail();

            if (telefone == null || nome == null || email == null
                    || telefone.isEmpty()
                    || nome.isEmpty()
                    || email.isEmpty()) {
                throw new UnsupportedOperationException("Nome, email e telefone não foram definidos, não é possível efetuar login");
            }

            ((UsuarioSB) pUsuairo).setTelefone(telefone);
            ((UsuarioSB) pUsuairo).setNome(nome);
            ((UsuarioSB) pUsuairo).setEmail(email);

            pUsuairo = UtilSBPersistencia.mergeRegistro(pUsuairo);
            if (pUsuairo == null) {
                throw new ErroDadosDeContatoUsuarioNaoEncontrado("Encontramos incosistencias no seu cadastro.");
            }

        }

        switch (getTipoAgente(pUsuairo).getTipoCanal()) {

            case INTERNO:
                return new ContatoUsuarioTransiente((UsuarioSB) pUsuairo);

            case REDES_SOCIAIS:
                if (dadosDoContatoCliente == null) {
                    throw new ErroDadosDeContatoUsuarioNaoEncontrado("Não foi possível encontrar o contato vinculado ao usuário" + pUsuairo.getNome());
                }
                if (dadosDoContatoCliente.getCelular() == null || UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(dadosDoContatoCliente.getCelular()) == null) {
                    throw new ErroDadosDeContatoUsuarioNaoEncontrado("Não foi possível encontrar o telefone vinculado ao contato do usuário" + pUsuairo.getNome());
                }
                return dadosDoContatoCliente;

            default:
                throw new AssertionError();
        }

    }

    @Override
    public boolean isObjetoPermitidoUsuario(ComoUsuario pUsuario, ComoEntidadeSimplesSomenteLeitura pObjeto) {
        if (pUsuario.getGrupo().equals(FabGruposIntranetCasaNova.GRUPOADMIN)) {
            return true;
        }
        if (pObjeto instanceof ArquivoCliente) {
            return UtilPermissoesObjetos.isPermitidoArquivoCliente(pUsuario, (ArquivoCliente) pObjeto);
        } else {
            if (pObjeto instanceof ArquivoAnexado) {
                return UtilPermissoesObjetos.isPermitidoArquivoAnexo(pUsuario, (ArquivoAnexado) pObjeto);
            }
        }
        if (pObjeto instanceof Pessoa) {
            if (!((Pessoa) pObjeto).isUmPerfilPrivado()) {
                return true;
            } else {
                for (UsuarioCRM usr : ((Pessoa) pObjeto).getUsuariosResponsaveis()) {
                    if (SBCore.getUsuarioLogado().equals(usr)) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public ItfTokenAcessoDinamico gerarTokenDinamico(ComoFabricaAcoes pAcao, ComoEntidadeSimplesSomenteLeitura pItem, String pEmail) {
        return super.gerarTokenDinamico(pAcao, pItem, pEmail); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
