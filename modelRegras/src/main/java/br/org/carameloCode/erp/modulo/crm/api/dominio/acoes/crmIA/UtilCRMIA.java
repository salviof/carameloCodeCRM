/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 * Instruções de IA dinâmicas (nunca persistidas), geradas a partir dos dados do
 * CRM.
 *
 * @author salvio
 */
public class UtilCRMIA {

    private static boolean temTexto(String pTexto) {
        return !UtilCRCStringValidador.isNuloOuEmbranco(pTexto);
    }

    private static void adicionarLinha(StringBuilder pTexto, String pRotulo, String pValor) {
        if (temTexto(pValor)) {
            pTexto.append("- **").append(pRotulo).append(":** ").append(pValor.trim()).append("\n");
        }
    }

    private static void adicionarDado(StringBuilder pTexto, DadoCRM pDado) {

        ItfCampoInstanciado campo = pDado.getCampoInstanciado();
        String titulo = campo.getLabel();
        try {
            String valor = campo.getValorTextoFormatado();
            if (!temTexto(titulo) || !temTexto(valor)) {
                return;
            }
            adicionarLinha(pTexto, titulo, valor);
        } catch (Throwable t) {
            CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha adicionando dado dinamico: " + titulo, t);

        }
    }

    private static void adicionarDados(StringBuilder pTexto, String pTitulo, List<DadoCRM> pDados) {
        if (pDados == null || pDados.isEmpty()) {
            return;
        }
        StringBuilder dados = new StringBuilder();
        for (DadoCRM dado : pDados) {
            if (dado == null || dado.getCampoInstanciado() == null) {
                continue;
            }
            adicionarDado(pTexto, dado);

        }
        if (dados.length() > 0) {
            pTexto.append("\n").append(pTitulo).append(":\n\n").append(dados);
        }
    }

    private static void adicionarInstrucao(List<InstrucaoIA> pInstrucoes, String pNome, StringBuilder pConteudo) {
        if (pConteudo.length() == 0) {
            return;
        }
        InstrucaoIA instrucao = new InstrucaoIA();
        instrucao.setNome(pNome);
        instrucao.setConteudo(pConteudo.toString());
        instrucao.setAtivo(true);
        pInstrucoes.add(instrucao);
    }

    /**
     * Tipo de atividade, dados coletados durante a atividade e, quando houver,
     * o modelo de e-mail que originou a atividade.
     */
    public static List<InstrucaoIA> getListaInstrucaoIAAtividadeCRM(AtividadeCRM pAtividade) {
        List<InstrucaoIA> instrucoes = new ArrayList<>();
        if (pAtividade == null) {
            return instrucoes;
        }
        TipoAtividadeCRM tipoAtividade = pAtividade.getTipoAtividade();

        StringBuilder textoAtividade = new StringBuilder();
        if (tipoAtividade != null) {
            adicionarLinha(textoAtividade, "Tipo de atividade", tipoAtividade.getNomeAtividade());
            adicionarLinha(textoAtividade, "Orientação do tipo de atividade", tipoAtividade.getTextoAjuda());
        }
        adicionarLinha(textoAtividade, "Atividade", pAtividade.getNomeAtividade());
        if (pAtividade.getStatusAtividade() != null) {
            adicionarLinha(textoAtividade, "Status", pAtividade.getStatusAtividade().getNome());
        }
        adicionarLinha(textoAtividade, "Relatório da atividade", pAtividade.getAnotacoes());
        adicionarDados(textoAtividade, "Dados coletados durante a atividade", pAtividade.getDadosColetados());
        adicionarInstrucao(instrucoes, "Atividade em execução", textoAtividade);

        if (tipoAtividade != null && tipoAtividade.getModeloEmail() != null) {
            ModeloEmail modeloEmail = tipoAtividade.getModeloEmail();
            StringBuilder textoEmail = new StringBuilder();
            if (temTexto(modeloEmail.getTextoModelo())) {
                textoEmail.append("Este e-mail foi iniciado a partir do modelo de e-mail abaixo; mantenha a estrutura e a intenção do modelo.\n\n");
            }
            adicionarLinha(textoEmail, "Modelo", modeloEmail.getNome());
            adicionarLinha(textoEmail, "Assunto do modelo", modeloEmail.getAssunto());
            adicionarLinha(textoEmail, "Instruções de formatação", modeloEmail.getInstrucoesIAFormatacaoEmail());
            if (temTexto(modeloEmail.getTextoModelo())) {
                textoEmail.append("\nTexto do modelo:\n\n").append(modeloEmail.getTextoModelo().trim()).append("\n");
            }
            adicionarInstrucao(instrucoes, "Modelo de e-mail da atividade", textoEmail);

        }
        return instrucoes;
    }

    public static List<InstrucaoIA> getListaInstrucaoPessoaDadosBasicos(Pessoa pPessoa) {

        List<InstrucaoIA> instrucoes = new ArrayList<>();
        if (pPessoa == null) {
            return instrucoes;
        }
        StringBuilder textoCliente = new StringBuilder();
        adicionarLinha(textoCliente, "Nome", pPessoa.getNome());
        if (pPessoa.getContatoPrincipal() != null) {
            adicionarLinha(textoCliente, "Contato principal", descreverContato(pPessoa.getContatoPrincipal()));
        }
        TipoRelacionamento relacionamento = pPessoa.getRelacionamento();
        if (relacionamento != null) {
            adicionarLinha(textoCliente, "Relacionamento atual com a empresa", relacionamento.getNome());
            adicionarLinha(textoCliente, "Descrição do relacionamento", relacionamento.getDescricao());
            MetaRelacionamento meta = relacionamento.getMetaRelacionamento();
            if (meta != null) {
                adicionarLinha(textoCliente, "Estágio (meta) do cliente", meta.getNome());
                adicionarLinha(textoCliente, "Descrição do estágio", meta.getDescricaoEtapaVisaoPrestador());
            }
        }
        adicionarLinha(textoCliente, "Atendente Novos contratos", pPessoa.getUsuarioResponsavel() == null ? "Não definido" : pPessoa.getUsuarioResponsavel().getNome());
        adicionarLinha(textoCliente, "Atendente gestão de projetos do cliente", pPessoa.getUsuarioAtendimento() == null ? "Não definido" : pPessoa.getUsuarioAtendimento().getNome());
        adicionarInstrucao(instrucoes, "Dados básicos do cliente", textoCliente);
        return instrucoes;
    }

    /**
     * Detalhes sobre o cliente: relacionamento atual com a empresa, estágio
     * (meta) em que se encontra e dados coletados.
     */
    public static List<InstrucaoIA> getListaInstrucaoPessoaTodosDados(Pessoa pPessoa) {
        List<InstrucaoIA> instrucoes = getListaInstrucaoPessoaDadosBasicos(pPessoa);
        StringBuilder stringb = new StringBuilder();
        adicionarDados(stringb, "Dados coletados do cliente", pPessoa.getDadosColetados());
        adicionarInstrucao(instrucoes, "Detalhes sobre o cliente", stringb);
        return instrucoes;
    }

    private static String descreverContato(ContatoProspecto pContato) {
        StringBuilder contato = new StringBuilder(temTexto(pContato.getNome()) ? pContato.getNome().trim() : "Sem nome");
        if (temTexto(pContato.getCargo())) {
            contato.append(" (").append(pContato.getCargo().trim()).append(")");
        }
        if (temTexto(pContato.getEmail())) {
            contato.append(" - ").append(pContato.getEmail().trim());
        }
        return contato.toString();
    }

    /**
     * Estado do e-mail no momento da edição: assunto, contatos destinatários e
     * conteúdo atual.
     */
    public static String getStatusEmailAtual(EnvioEmail pEmail) {
        if (pEmail == null) {
            return null;
        }
        StringBuilder status = new StringBuilder();
        adicionarLinha(status, "Assunto atual", pEmail.getAssunto());
        if (pEmail.getContatos() != null && !pEmail.getContatos().isEmpty()) {
            status.append("- **Contatos neste e-mail:**\n");
            for (ContatoProspecto contato : pEmail.getContatos()) {
                if (contato != null) {
                    status.append("  - ").append(descreverContato(contato)).append("\n");
                }
            }
        } else {
            status.append("- **Contatos neste e-mail:** nenhum contato selecionado\n");
        }
        if (temTexto(pEmail.getTexto())) {
            status.append("\nConteúdo atual do e-mail:\n\n").append(pEmail.getTexto().trim()).append("\n");
        } else {
            status.append("\nO e-mail ainda não possui conteúdo.\n");
        }
        return status.toString();
    }

}
