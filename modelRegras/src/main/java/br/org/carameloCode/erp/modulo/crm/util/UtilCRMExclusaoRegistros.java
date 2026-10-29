/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.util;

import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilCRCPersistenciaJDBC;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

/**
 * Exclusão em SQL nativo de registros com muitas dependências (Pessoa,
 * Contato, usuário cliente), respeitando as chaves estrangeiras do banco.
 *
 * @author salvio
 */
public class UtilCRMExclusaoRegistros {

    /**
     * Executa os comandos na mesma transação de quem chamou.
     */
    public interface ExecutorSQL {

        void executar(String pSql) throws ErroRegraDeNegocio;

        /**
         * @return valores da primeira coluna do resultado
         */
        List<Object> listar(String pSql) throws ErroRegraDeNegocio;

        default List<Long> listarIds(String pSql) throws ErroRegraDeNegocio {
            return listar(pSql).stream().filter(v -> v != null).map(v -> ((Number) v).longValue()).collect(Collectors.toList());
        }
    }

    /**
     * Tabelas de associação do usuário (sem valor histórico), limpas antes de
     * excluir o usuário cliente.
     */
    private static final List<String> ASSOCIACOES_USUARIO = Arrays.asList(
            "prospectos_usuarios_responsaveis.usuarioCRM_id",
            "relacionamento_usuarios_responsaveis.usuarioCRM_id",
            "prospeco_usuarioConvidado.usuarioCRM_id",
            "chamado_convidados_atend.usuario_id",
            "usuario_grupo.usuario_id",
            "origens_usuarioCRM.usuario_id",
            "Permitido_Usuarios.usuario_id",
            "Negado_Usuarios.usuario_id",
            "tipochamado_responsaveis.usuario_id",
            "tipoAtividade_autorizados.usuarioCRM_id",
            "UsuarioCRM_TagsAtendimento.usuarioCRM_id",
            "escopo_etendentes.atendente_id",
            "disponibilidade_atendentes.atendente_id",
            "reserva_atendente.atendente_id"
    );

    /**
     * Referências ao usuário removidas junto com ele (além das associações).
     */
    private static final List<String> REFERENCIAS_DESCARTAVEIS_USUARIO = Arrays.asList(
            "NotificacaoSB.usuario_id",
            "ContatoProspecto.usuarioVinculado_id"
    );

    public static ExecutorSQL getExecutor(EntityManager pEm) {
        return new ExecutorSQL() {
            @Override
            public void executar(String pSql) throws ErroRegraDeNegocio {
                if (!UtilSBPersistencia.executaSQL(pEm, pSql)) {
                    throw new ErroRegraDeNegocio("Falha na exclusão: " + pSql);
                }
            }

            @Override
            public List<Object> listar(String pSql) {
                return pEm.createNativeQuery(pSql).getResultList();
            }
        };
    }

    public static ExecutorSQL getExecutor(Connection pConexao) {
        return new ExecutorSQL() {
            @Override
            public void executar(String pSql) throws ErroRegraDeNegocio {
                try {
                    UtilCRCPersistenciaJDBC.executarSQL(pConexao, pSql);
                } catch (ErroEmBancoDeDados e) {
                    throw new ErroRegraDeNegocio("Falha na exclusão: " + pSql + " " + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
                }
            }

            @Override
            public List<Object> listar(String pSql) throws ErroRegraDeNegocio {
                List<Object> valores = new ArrayList<>();
                try (PreparedStatement ps = pConexao.prepareStatement(pSql); ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        valores.add(rs.getObject(1));
                    }
                } catch (Throwable t) {
                    throw new ErroRegraDeNegocio("Falha na consulta: " + pSql + " " + t.getMessage());
                }
                return valores;
            }
        };
    }

    public static String in(Collection<?> pIds) {
        return "(" + pIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
    }

    /**
     * Libera as referências aos registros que serão excluídos, consultando
     * todas as chaves estrangeiras do banco: coluna anulável recebe null,
     * linha de tabela de associação (coluna obrigatória) é excluída.
     *
     * Referências obrigatórias vindas de tabelas que também estão em
     * pExclusoes são mantidas: a ordem de pExclusoes deve excluir o filho
     * antes do pai.
     *
     * @param pExclusoes tabela → ids que serão excluídos
     */
    public static void liberarReferencias(ExecutorSQL pSql, Map<String, List<Long>> pExclusoes) throws ErroRegraDeNegocio {
        Map<String, List<Long>> exclusoes = pExclusoes.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (exclusoes.isEmpty()) {
            return;
        }
        List<Object> referencias = pSql.listar(
                "select concat(k.TABLE_NAME, '.', k.COLUMN_NAME, '.', k.REFERENCED_TABLE_NAME, '.', c.IS_NULLABLE) "
                + "from information_schema.KEY_COLUMN_USAGE k "
                + "join information_schema.COLUMNS c on c.TABLE_SCHEMA = k.TABLE_SCHEMA and c.TABLE_NAME = k.TABLE_NAME and c.COLUMN_NAME = k.COLUMN_NAME "
                + "where k.TABLE_SCHEMA = database() and k.REFERENCED_TABLE_NAME in "
                + exclusoes.keySet().stream().map(t -> "'" + t + "'").collect(Collectors.joining(",", "(", ")")));
        for (Object referencia : referencias) {
            String[] partes = referencia.toString().split("\\.");
            String tabela = partes[0];
            String coluna = partes[1];
            List<Long> ids = exclusoes.get(partes[2]);
            boolean anulavel = "YES".equals(partes[3]);
            if (anulavel) {
                pSql.executar("update `" + tabela + "` set `" + coluna + "` = null where `" + coluna + "` in " + in(ids));
            } else if (!pExclusoes.containsKey(tabela)) {
                pSql.executar("delete from `" + tabela + "` where `" + coluna + "` in " + in(ids));
            }
        }
    }

    /**
     * Exclui os registros na ordem do mapa, depois de liberar as referências.
     */
    public static void excluirRegistros(ExecutorSQL pSql, LinkedHashMap<String, List<Long>> pExclusoes) throws ErroRegraDeNegocio {
        liberarReferencias(pSql, pExclusoes);
        for (Map.Entry<String, List<Long>> exclusao : pExclusoes.entrySet()) {
            if (!exclusao.getValue().isEmpty()) {
                pSql.executar("delete from `" + exclusao.getKey() + "` where id in " + in(exclusao.getValue()));
            }
        }
    }

    /**
     * Conta registros que referenciam o usuário fora das associações
     * (atividades, chamados, reservas...). Esses registros são histórico e
     * impedem a exclusão do usuário.
     */
    private static long contarReferenciasHistoricasUsuario(ExecutorSQL pSql, Long pIdUsuario) throws ErroRegraDeNegocio {
        List<Object> colunas = pSql.listar(
                "select concat(TABLE_NAME, '.', COLUMN_NAME) from information_schema.KEY_COLUMN_USAGE "
                + "where TABLE_SCHEMA = database() and REFERENCED_TABLE_NAME = 'UsuarioSB'");
        long total = 0;
        for (Object coluna : colunas) {
            String tabelaColuna = coluna.toString();
            if (ASSOCIACOES_USUARIO.contains(tabelaColuna) || REFERENCIAS_DESCARTAVEIS_USUARIO.contains(tabelaColuna)) {
                continue;
            }
            String[] partes = tabelaColuna.split("\\.");
            String sql = "select count(*) from `" + partes[0] + "` where `" + partes[1] + "` = " + pIdUsuario;
            if (partes[0].equals("UsuarioSB")) {
                sql += " and id <> " + pIdUsuario;
            }
            total += ((Number) pSql.listar(sql).get(0)).longValue();
        }
        return total;
    }

    private static void excluirNotificacoesUsuario(ExecutorSQL pSql, Long pIdUsuario) throws ErroRegraDeNegocio {
        List<Long> notificacoes = pSql.listarIds("select id from NotificacaoSB where usuario_id = " + pIdUsuario);
        if (notificacoes.isEmpty()) {
            return;
        }
        List<Long> disparos = pSql.listarIds("select id from LogDisparoNotificacao where notificacao_id in " + in(notificacoes));
        List<Long> recibos = pSql.listarIds("select reciboEntrega_id from LogDisparoNotificacao where notificacao_id in " + in(notificacoes));
        LinkedHashMap<String, List<Long>> exclusoes = new LinkedHashMap<>();
        exclusoes.put("LogDisparoNotificacao", disparos);
        exclusoes.put("ReciboEntrega", recibos);
        exclusoes.put("NotificacaoSB", notificacoes);
        excluirRegistros(pSql, exclusoes);
    }

    /**
     * Resolve o usuário cliente cujos contatos estão sendo removidos:
     * <ul>
     * <li>vinculado a outro contato: permanece, apontando para esse
     * contato;</li>
     * <li>com histórico: é desativado e desvinculado;</li>
     * <li>sem histórico: é excluído com suas associações e notificações.</li>
     * </ul>
     *
     * Deve ser chamado antes de excluir os contatos.
     */
    public static void desvincularUsuarioCliente(ExecutorSQL pSql, Long pIdUsuario, Collection<Long> pIdsContatosRemovidos) throws ErroRegraDeNegocio {
        String contatosRemovidos = in(pIdsContatosRemovidos);
        pSql.executar("update ContatoProspecto set usuarioVinculado_id = null where usuarioVinculado_id = " + pIdUsuario + " and id in " + contatosRemovidos);
        List<Long> outrosContatos = pSql.listarIds("select id from ContatoProspecto where usuarioVinculado_id = " + pIdUsuario);
        if (!outrosContatos.isEmpty()) {
            pSql.executar("update UsuarioSB set contatoClienteVinculado_id = " + outrosContatos.get(0)
                    + " where id = " + pIdUsuario + " and (contatoClienteVinculado_id is null or contatoClienteVinculado_id in " + contatosRemovidos + ")");
            return;
        }
        if (contarReferenciasHistoricasUsuario(pSql, pIdUsuario) > 0) {
            pSql.executar("update UsuarioSB set ativo = false, contatoClienteVinculado_id = null where id = " + pIdUsuario);
            return;
        }
        for (String associacao : ASSOCIACOES_USUARIO) {
            String[] partes = associacao.split("\\.");
            pSql.executar("delete from `" + partes[0] + "` where `" + partes[1] + "` = " + pIdUsuario);
        }
        excluirNotificacoesUsuario(pSql, pIdUsuario);
        pSql.executar("update UsuarioSB set usuarioAlteracao_id = null, usuarioInsercao_id = null where id = " + pIdUsuario);
        pSql.executar("delete from UsuarioSB where id = " + pIdUsuario);
    }

    /**
     * Exclui a Pessoa (ProspectoEmpresa) e tudo que pertence a ela: contatos,
     * atividades, chamados, e-mails, orçamentos, reservas... Usuários cliente
     * dos contatos seguem {@link #desvincularUsuarioCliente}.
     */
    public static void excluirPessoa(ExecutorSQL sql, Long pIdPessoa, Long pIdUsuarioLogado) throws ErroRegraDeNegocio {
        List<Long> contatos = sql.listarIds("select id from ContatoProspecto where prospecto_id = " + pIdPessoa);
        List<Long> atividades = sql.listarIds("select id from AtividadeCRM where prospectoEmpresa_id = " + pIdPessoa);
        List<Long> chamados = sql.listarIds("select id from ChamadoCliente where pessoa_id = " + pIdPessoa);
        List<Long> emails = sql.listarIds("select id from EmailCrm where prospecto_id = " + pIdPessoa);
        List<Long> orcamentos = sql.listarIds("select id from Orcamento where pessoa_id = " + pIdPessoa);
        List<Long> preAnalises = sql.listarIds("select id from PreAnalise where pessoaJuridica_id = " + pIdPessoa);
        List<Long> dados = sql.listarIds("select id from DadoCRM where prospectoEmpresa_id = " + pIdPessoa
                + (atividades.isEmpty() ? "" : " or atividadeCRM_id in " + in(atividades)));

        // Usuários cliente dos contatos (atendentes nunca são excluídos aqui)
        String filtroContatos = contatos.isEmpty() ? "" : " or contatoClienteVinculado_id in " + in(contatos)
                + " or id in (select usuarioVinculado_id from ContatoProspecto where prospecto_id = " + pIdPessoa + ")";
        List<Long> usuarios = sql.listarIds("select id from UsuarioSB where tipoUsuario = 'UsuarioCrmCliente' and (representanteLegal_id = " + pIdPessoa + filtroContatos + ")");
        if (usuarios.contains(pIdUsuarioLogado)) {
            throw new ErroRegraDeNegocio("O usuário logado está vinculado ao usuário da pospecao");
        }
        for (Long idUsuario : usuarios) {
            desvincularUsuarioCliente(sql, idUsuario, contatos);
        }

        // Ordem: filhos antes dos pais. Referências externas (anuláveis ou associações) são liberadas automaticamente
        LinkedHashMap<String, List<Long>> exclusoes = new LinkedHashMap<>();
        exclusoes.put("ChatBot", atividades.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from ChatBot where atividade_id in " + in(atividades)));
        exclusoes.put("ExecucaoIA", atividades.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from ExecucaoIA where atividade_id in " + in(atividades)));
        exclusoes.put("EventoChamado", chamados.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from EventoChamado where chamado_id in " + in(chamados)));
        exclusoes.put("NotificacaoResponsaveisChamado", chamados.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from NotificacaoResponsaveisChamado where chamado_id in " + in(chamados)));
        exclusoes.put("Solicitacao", sql.listarIds("select id from Solicitacao where pessoa_id = " + pIdPessoa));
        exclusoes.put("ChamadoCliente", chamados);
        exclusoes.put("HistoricoDadoCrm", dados.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from HistoricoDadoCrm where dadoCRm_id in " + in(dados)));
        exclusoes.put("DadoCRM", dados);
        if (!emails.isEmpty()) {
            String inEmails = in(emails);
            exclusoes.put("LogFalhaEnvioEmail", sql.listarIds("select id from LogFalhaEnvioEmail where email_id in " + inEmails));
            exclusoes.put("LogEmailRecebidoLido", sql.listarIds("select id from LogEmailRecebidoLido where email_id in " + inEmails));
            exclusoes.put("LogEMailEnviadoLido", sql.listarIds("select id from LogEMailEnviadoLido where email_id in " + inEmails));
        }
        exclusoes.put("EnvioEmailRascunhoAutoSave", sql.listarIds("select id from EnvioEmailRascunhoAutoSave where prospecto_id = " + pIdPessoa
                + (emails.isEmpty() ? "" : " or emailVinculado_id in " + in(emails))));
        exclusoes.put("EmailCrm", emails);
        exclusoes.put("ArquivoAnexado", sql.listarIds("select id from ArquivoAnexado where prospecto_id = " + pIdPessoa));
        exclusoes.put("SubPastaPrivada", sql.listarIds("select id from SubPastaPrivada where pessoa_id = " + pIdPessoa));
        exclusoes.put("HistoricoRelacionamento", sql.listarIds("select id from HistoricoRelacionamento where prospecto_id = " + pIdPessoa));
        exclusoes.put("MensagemMktWhatsapp", sql.listarIds("select id from MensagemMktWhatsapp where pessoa_id = " + pIdPessoa));
        exclusoes.put("MensagemSMS", contatos.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from MensagemSMS where contato_id in " + in(contatos)));
        exclusoes.put("ServicoOferecido", sql.listarIds("select id from ServicoOferecido where prospecto_id = " + pIdPessoa
                + (orcamentos.isEmpty() ? "" : " or orcamento_id in " + in(orcamentos))));
        exclusoes.put("Orcamento", orcamentos);
        exclusoes.put("Proposta", sql.listarIds("select id from Proposta where prospecto_id = " + pIdPessoa));
        exclusoes.put("ReservaHorario", sql.listarIds("select id from ReservaHorario where pessoaRelacionada_id = " + pIdPessoa));
        exclusoes.put("AuditoriaPreAnalise", preAnalises.isEmpty() ? Collections.emptyList()
                : sql.listarIds("select id from AuditoriaPreAnalise where preanalise_id in " + in(preAnalises)));
        exclusoes.put("PreAnalise", preAnalises);
        exclusoes.put("AtividadeCRM", atividades);
        exclusoes.put("ContatoProspecto", contatos);
        exclusoes.put("ProspectoEmpresa", Collections.singletonList(pIdPessoa));
        excluirRegistros(sql, exclusoes);
    }

}
