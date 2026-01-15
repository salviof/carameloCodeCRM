package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.coletivoJava.fw.erp.implementacao.chat.ChatMatrixOrgimpl;
import br.org.coletivoJava.fw.erp.implementacao.chat.model.model.FabTipoSalaMatrix;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.marketing.Util.ErroNotificacao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class UtilCRMChat {

    public static ChatMatrixOrgimpl chatService = (ChatMatrixOrgimpl) ERPChat.MATRIX_ORG.getImplementacaoDoContexto();
    private static ComoUsuarioChat usuarioAdmin;

    public static boolean isTeveAlteracoesUsuarioChat(ComoUsuarioChat pUsuarioChatSevice, UsuarioCRM pUsuarioCrm) {
        String telefoneInternacionalSistema = null;
        if (pUsuarioCrm.isUmUsuarioDoCliente()) {
            telefoneInternacionalSistema = gerarTelefoneInternacional(pUsuarioCrm.getComoUsuarioCliente());
        } else {
            telefoneInternacionalSistema = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(pUsuarioCrm.getTelefonePrincipal());
        }
        String telefoneInternacionaChatService = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(pUsuarioChatSevice.getTelefone());
        String emailSistema = pUsuarioCrm.getEmail();
        String emailChatService = pUsuarioChatSevice.getEmail();

        if (telefoneInternacionalSistema != null && telefoneInternacionaChatService == null) {
            return true;
        }
        if (emailSistema != null && emailChatService == null) {
            return true;
        }

        if (emailSistema == null || emailChatService == null || telefoneInternacionalSistema == null || telefoneInternacionaChatService == null) {
            return false;
        }
        String nomeSistema = pUsuarioCrm.getNome();
        String nomeChat = pUsuarioChatSevice.getNome();

        return !(telefoneInternacionalSistema.equals(telefoneInternacionaChatService) || !emailSistema.equals(emailChatService));

    }

    public static String gerarTelefoneInternacional(UsuarioCrmCliente pUsuario) {
        String telefone = pUsuario.getUsuarioComoUsrCliente().getContatoClienteVinculado().getCelular();
        telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
        return telefone;
    }

    public static ComoUsuarioChat gerarUsuarioAtendimento(UsuarioCRM pUsuario) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {
        if (pUsuario.isUmUsuarioDoCliente()) {
            throw new UnsupportedOperationException("Impossível gerar um usuário de atendimento, com um usuário do cliente");
        }
        String codigo = chatService.gerarCodigoUsuarioAtendimento(pUsuario.getNome(), pUsuario.getEmail());
        ComoUsuarioChat usuarioAtendimento = chatService.getUsuarioByCodigo(codigo);
        if (usuarioAtendimento == null) {
            usuarioAtendimento = chatService.gerarUsuarioAtendimento(pUsuario.getNome(), pUsuario.getEmail());
        }
        boolean teveAlteracoes = UtilCRMChat.isTeveAlteracoesUsuarioChat(usuarioAtendimento, pUsuario);
        if (teveAlteracoes) {
            usuarioAtendimento = chatService.usuarioAtualizar(usuarioAtendimento.getCodigoUsuario(), pUsuario.getNome(), pUsuario.getEmail(), pUsuario.getTelefone());
        }
        if (pUsuario.getCodigoMatrix() == null || !pUsuario.getCodigoMatrix().equals(usuarioAtendimento.getCodigoUsuario())) {
            boolean codigoAtualizado = UtilSBPersistencia.executaSQL("update UsuarioSB set codigoMatrix='" + usuarioAtendimento.getCodigoUsuario() + "' where id =" + pUsuario.getId());
            System.out.println(codigoAtualizado);
        }
        return usuarioAtendimento;
    }

    public static ComoUsuarioChat gerarUsuarioContatoCliente(UsuarioCrmCliente pUsuario) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {
        String codigo = chatService.gerarCodigoUsuarioContato(pUsuario.getContatoClienteVinculado().getCelular());
        ComoUsuarioChat usuarioChatService = chatService.getUsuarioByCodigo(codigo);

        try {
            if (usuarioChatService == null) {
                try {
                    String telefone = gerarTelefoneInternacional(pUsuario);
                    usuarioChatService = chatService.gerarUsuarioContato(pUsuario.getNome(), telefone);

                } catch (ErroConexaoServicoChat ex) {

                    return null;
                }
            }
            //verificando se precisa atualizar o telefone ou Email
            if (usuarioChatService != null) {
                if (pUsuario.getCodigoMatrix() == null || !pUsuario.getCodigoMatrix().equals(usuarioChatService.getCodigoUsuario())) {
                    UtilSBPersistencia.executaSQL("update " + UsuarioSB.class.getSimpleName() + " set codigoMatrix = '" + usuarioChatService.getCodigoUsuario() + "' where id = " + pUsuario.getId());
                }
            }
            boolean teveAlteracoes = UtilCRMChat.isTeveAlteracoesUsuarioChat(usuarioChatService, pUsuario);

            if (teveAlteracoes) {
                chatService.usuarioAtualizarSenha(codigo, chatService.gerarSenhaPadrao(pUsuario, codigo));
                usuarioChatService = chatService.usuarioAtualizar(codigo, pUsuario.getNome(), pUsuario.getEmail(), pUsuario.getTelefone());
            }

            if (usuarioChatService != null) {

                return usuarioChatService;
            } else {
                return null;
            }
        } finally {
            // validar
        }

    }

    public static List<UsuarioCRM> gerarListasUsuariosContatoPrincipal(Pessoa pPessoa) throws ErroRegraDeNEgocioChat {
        List<UsuarioCRM> usuariosExternosCLiante = new ArrayList<>();

        ContatoProspecto contato = (ContatoProspecto) pPessoa.getCPinst(CPPessoa.contatoprincipal).getValor();
        if (contato == null) {
            throw new UnsupportedOperationException("Cotntato principal de " + pPessoa.getNome());
        }
        if (contato.getCelular() == null) {
            throw new UnsupportedOperationException("Telefone de " + contato.getNome() + " não foi definido");
        }
        usuariosExternosCLiante.add(contato.getUsuarioVinculado());
        return usuariosExternosCLiante;
    }

    public static List<UsuarioCRM> gerarListasUsuariosContatoClienteInternet(Pessoa pPessoa) {
        List<UsuarioCRM> usuariosExternosCLiante = new ArrayList<>();

        for (ContatoProspecto contato : pPessoa.getContatosProspecto()) {
            if (contato.getUsuarioVinculado() != null) {
                UsuarioCrmCliente usuarioCliente = (UsuarioCrmCliente) contato.getCPinst(CPContatoProspecto.usuariovinculado).getValor();
                usuariosExternosCLiante.add(usuarioCliente);
            }
        }
        return usuariosExternosCLiante;
    }

    public static List<UsuarioCRM> gerarListasUsuariosAtendimentGrupo(Pessoa pPessoa) {

        List<UsuarioCRM> usuariosAtendentes = new ArrayList<>();
        for (UsuarioCRM usr : pPessoa.getUsuariosResponsaveis()) {
            usuariosAtendentes.add(usr);
        }
        if (pPessoa.getUsuarioAtendimento() != null) {
            if (!usuariosAtendentes.contains(pPessoa.getUsuarioAtendimento())) {
                usuariosAtendentes.add(pPessoa.getUsuarioAtendimento());
            }
        }

        UsuarioCRM usuarioREsp = (UsuarioCRM) pPessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor();
        if (usuarioREsp != null) {
            if (!usuariosAtendentes.contains(usuarioREsp)) {
                usuariosAtendentes.add(usuarioREsp);
            }
        }

        return usuariosAtendentes;
    }

    public static List<UsuarioCRM> gerarListasUsuariosContatos(ChamadoCliente pChamdo) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {
        List<UsuarioCRM> usuariosExternosCLiante = new ArrayList<>();
        UsuarioCrmCliente usuario = pChamdo.getUsuarioCliente();
        usuariosExternosCLiante.add(usuario);
        return usuariosExternosCLiante;
    }

    public static List<UsuarioCRM> getarListasUsuariosAtendTimeIntranet(ChamadoCliente pChamado) {

        List<UsuarioCRM> usuarioInternoAtendimento = new ArrayList<>();

        for (UsuarioCRM usr : pChamado.getAtendentesConvidados()) {
            usuarioInternoAtendimento.add(usr);
        }
        if (pChamado.getAtendenteResponsavel() != null) {
            usuarioInternoAtendimento.add(pChamado.getAtendenteResponsavel());
        }
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {

            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pChamado.getPessoa(), em);
            if (usuarioInternoAtendimento.isEmpty()) {

                UsuarioCRM atendimento = (UsuarioCRM) pessoa.getCPinst(CPPessoa.usuarioatendimento).getValor();

                if (atendimento != null) {
                    usuarioInternoAtendimento.add(atendimento);
                }

            }

            if (usuarioInternoAtendimento.isEmpty()) {
                UsuarioCRM captadorLead = (UsuarioCRM) pessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor();
                usuarioInternoAtendimento.add(captadorLead);
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return usuarioInternoAtendimento;
    }

    public static void notificarSalaAtendimentoGeral(UsuarioCrmCliente pUsuarioCLiente, String pMensagem) throws ErroNotificacao {

        ComoChatSalaBean sala;
        try {
            sala = getSalaAtendimentoContatoPrincipal(pUsuarioCLiente);
        } catch (ErroRegraDeNEgocioChat | ErroConexaoServicoChat ex) {
            throw new ErroNotificacao("Falçha notificando sala de atendimento" + ex.getMessage());
        }

        if (sala == null) {
            throw new ErroNotificacao("Falha pesquisando sala");
        }
        try {
            chatService.salaEnviarMesagem(sala, chatService.getUsuarioAdmin(), String.valueOf(pMensagem.hashCode()), pMensagem);
        } catch (ErroConexaoServicoChat ex) {
            throw new ErroNotificacao("Falçha notificando sala de atendimento");
        }
    }

    private static ComoChatSalaBean gerarSala(Pessoa pPessoa, FabTipoSalaMatrix tipoSala, String nomePersonalizado, ComoEntidadeSimples pObjetoVinculado,
            List<UsuarioCRM> pUsuarioAtendimento, List<UsuarioCRM> pUsuariosExternosCLiente) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {

        List<ComoUsuarioChat> usuariosMatrixExternosCLiente = new ArrayList<>();
        List<ComoUsuarioChat> usuariosMatrixAtendimento = new ArrayList<>();
        String apelido = null;
        switch (tipoSala) {

            case WTZAP_ATENDIMENTO:
            case WTZAP_VENDAS:
                pPessoa.getContatoPrincipal().getUsuarioVinculado().setTelefone(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(pPessoa.getContatoPrincipal().getCelular()));
                apelido = tipoSala.getAliasSalaParaUsuario(gerarUsuarioContatoCliente(pPessoa.getContatoPrincipal().getUsuarioVinculado()));
                break;
            case WTZAP_ATENDIMENTO_GRUPO_CLIENTE:
                break;
            case MATRIX_CHAT_VENDAS:
            case MATRIX_CHAT_ATENDIMENTO:
            case MATRIX_CHAT_ATENDIMENTO_CHAMADO:
            case MATRIX_CHAT_DEBATE_INTERNO_LEAD_CLIENTE:
            case CHAT_DINAMICO_DE_ENTIDADE:
                apelido = tipoSala.getAliasSalaParaEnttidade(pObjetoVinculado);
                break;
            default:
                throw new AssertionError();
        }
        ComoChatSalaBean sala = null;
        if (apelido != null) {
            sala = chatService.getSalaByAlias(apelido);
        }

        UsuarioCRM usuarioGestao = pPessoa.getUsuarioAtendimento();
        if (usuarioGestao == null) {
            usuarioGestao = (UsuarioCRM) pPessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor();
        }
        if (usuarioGestao != null) {
            if (usuarioGestao.getCodigoMatrix() == null) {
                gerarUsuarioAtendimento(usuarioGestao);
            }

        }
        List<UsuarioCRM> usuariosSala = new ArrayList<>();
        usuariosSala.addAll(pUsuariosExternosCLiente);
        usuariosSala.addAll(pUsuarioAtendimento);
        if (sala != null) {
            new Thread(new AssincAddUserRespCanalIntranet(sala, usuarioGestao, usuariosSala)).start();
            return sala;
        }

        for (UsuarioCRM usr : pUsuariosExternosCLiente) {
            if (usr.isUmUsuarioDoCliente()) {
                try {
                    usuariosMatrixExternosCLiente.add(gerarUsuarioContatoCliente(usr.getComoUsuarioCliente()));
                } catch (ErroRegraDeNEgocioChat ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando usuário Matrix do Cliente", ex);
                }
            }
        }
        for (UsuarioCRM usr : pUsuarioAtendimento) {
            if (!usr.isUmUsuarioDoCliente()) {
                try {
                    usuariosMatrixAtendimento.add(gerarUsuarioAtendimento(usr));
                } catch (ErroRegraDeNEgocioChat ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando usuário Matrix do Atendimento", ex);
                }
            }
        }

        ComoChatSalaBean salaIdealizada;
        try {
            salaIdealizada = tipoSala.
                    getSalaMatrix(pObjetoVinculado, chatService.getUsuarioAdmin(), usuariosMatrixAtendimento, usuariosMatrixExternosCLiente);
        } catch (ErroPreparandoObjeto ex) {
            throw new ErroConexaoServicoChat("Parametros inválidos para criação de sala" + ex.getMessage());
        }
        if (nomePersonalizado != null) {
            salaIdealizada.setNome(nomePersonalizado);
        }

        //Usuario Atendimento
        //Usuario REsponsavel
        sala = chatService.getSalaCriandoSeNaoExistir(salaIdealizada, salaIdealizada.getApelido());
        new Thread(new AssincAddUserRespCanalIntranet(sala, usuarioGestao, usuariosSala)).start();

        return sala;
    }

    private static ComoChatSalaBean getSalaAtendimentoContatoPrincipal(UsuarioCrmCliente pUsuario) throws ErroRegraDeNEgocioChat, ErroConexaoServicoChat {

        //String codigoSala = FabTipoSalaMatrix.MATRIX_CHAT_ATENDIMENTO.getAliasSalaParaUsuario(pUsuario.getRepresentanteLegal());
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

        UsuarioCrmCliente usuarioLoad = UtilSBPersistencia.loadEntidade(pUsuario, em);

        List<UsuarioCRM> usuariosExternosCLiante = UtilCRMChat.gerarListasUsuariosContatoPrincipal(pUsuario.getRepresentanteLegal());
        List<UsuarioCRM> usuariosAtendimento = UtilCRMChat.gerarListasUsuariosAtendimentGrupo(pUsuario.getRepresentanteLegal());
        try {
            return gerarSala(usuarioLoad.getRepresentanteLegal(),
                    FabTipoSalaMatrix.WTZAP_ATENDIMENTO,
                    UtilCRCStringFiltros.getNomeReduzido(pUsuario.getRepresentanteLegal().getNome()),
                    pUsuario.getRepresentanteLegal(),
                    usuariosAtendimento, usuariosExternosCLiante);

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
    }

    public static ComoChatSalaBean gerarSalaChamado(ChamadoCliente ppChamado) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(ppChamado, em);
            List<UsuarioCRM> usuariosExternosCLiente = gerarListasUsuariosContatos(chamado);
            List<UsuarioCRM> usuariosAtendimento = getarListasUsuariosAtendTimeIntranet(chamado);

            String nomePErsonalizado = "Chamado " + chamado.getId() + " " + UtilCRCStringFiltros.getNomeReduzido(chamado.getCliente().getRepresentanteLegal().getNome());

            ComoChatSalaBean chat = gerarSala(chamado.getPessoa(),
                    FabTipoSalaMatrix.MATRIX_CHAT_ATENDIMENTO_CHAMADO,
                    nomePErsonalizado,
                    chamado,
                    usuariosAtendimento, usuariosExternosCLiente);

            UtilSBPersistencia.executaSQL("update " + ChamadoCliente.class.getSimpleName() + " set salaMatrix='" + chat.getApelido() + "' where id = " + chamado.getId());
            return chat;

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    public synchronized static ComoChatSalaBean gerarSalaDebate(Pessoa pPessoa) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            List<UsuarioCRM> usuariosExternosCLiante = new ArrayList<>();
            List<UsuarioCRM> usuariosAtendimento = UtilCRMChat.gerarListasUsuariosAtendimentGrupo(pPessoa);
            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pPessoa, em);

            ComoChatSalaBean sala = gerarSala(pessoa, FabTipoSalaMatrix.MATRIX_CHAT_DEBATE_INTERNO_LEAD_CLIENTE, "Int_" + pPessoa.getNome(),
                    pPessoa, usuariosAtendimento, usuariosExternosCLiante); //
            return sala;

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

    public static ComoChatSalaBean gerarSalaContatoPrincipal(Pessoa pPessoa) throws ErroConexaoServicoChat, ErroRegraDeNEgocioChat {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

        try {
            Pessoa pessoa = (Pessoa) UtilSBPersistencia.loadEntidade(pPessoa, em);
            List<UsuarioCRM> usuariosExternosCLiante = UtilCRMChat.gerarListasUsuariosContatoPrincipal(pessoa);
            List<UsuarioCRM> usuariosAtendimento = UtilCRMChat.gerarListasUsuariosAtendimentGrupo(pessoa);
            String nomeSala = UtilCRCStringSlugs.gerarSlugSimples(pPessoa.getContatoPrincipal().getNome()) + UtilCRCStringSlugs.gerarSlugSimples(pessoa.getNome())
                    + UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(pessoa.getContatoPrincipal().getTelefone());
            ComoChatSalaBean sala = gerarSala(pessoa, FabTipoSalaMatrix.WTZAP_ATENDIMENTO, nomeSala,
                    pPessoa, usuariosAtendimento, usuariosExternosCLiante
            ); //
            return sala;

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }
}
