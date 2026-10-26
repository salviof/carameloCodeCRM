/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.ModuloCRMMauticSync;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import javax.persistence.EntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.FabTipoExecucaoIA;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.ia.controller.ModuloExecucaoAcao;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

/**
 *
 * @author salvio
 */
public class ModuloCRM_IA extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.TIPO_DADO_IA_CTR_SALVAR_MERG)
    public static ItfRespostaAcaoDoSistema cadastrarNovaSenhaUsuario(TipoDadoIA pTipoDadoIA) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(TipoDadoIA.class), pTipoDadoIA) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pTipoDadoIA);
            }
        }.getResposta();
    }

    /**
     * Executa o diálogo e aplica no modelo de e-mail marketing o assunto e o
     * corpoHtml da última resposta estruturada da IA.
     */
    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_ATUALIZAR_MODELO_DOCUMENTO)
    public static ItfRespostaAcaoDoSistema modeloDocumentoAtualizar(ExecucaoIAModeloEmailMkt pDialogo) {

        ItfRespostaAcaoDoSistema respostaConsultaIA = ModuloExecucaoAcao.executarDialogo(pDialogo);

        if (!respostaConsultaIA.isSucesso()) {
            return getNovaResposta(ExecucaoIAModeloEmailMkt.class).addErro(respostaConsultaIA.getMensagens().get(0).getMenssagem());
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    ModuloCRMMauticSync.enviarModeloEmailMktParaMautic(pDialogo.getModeloEmailMarketing());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ExecucaoIAModeloEmailMkt execucao = loadEntidade((ExecucaoIAModeloEmailMkt) respostaConsultaIA.getRetorno());
                execucao.setNovaMensagem(pDialogo.getNovaMensagem());
                if (execucao.getModeloEmailMarketing() == null) {
                    throw new ErroRegraDeNegocio("A execução não possui modelo de e-mail marketing");
                }
                JsonObject conteudo = getUltimaRespostaEstruturada(execucao.getDialogoJson());
                String novoTexto = conteudo.getString("corpoHtml", null);
                String novoAssunto = conteudo.getString("assunto", null);
                if (UtilCRCStringValidador.isNuloOuEmbranco(novoTexto)) {
                    throw new ErroRegraDeNegocio("A IA não retornou o conteúdo do e-mail");
                }
                ModeloEmailMarketing modelo = loadEntidade(execucao.getModeloEmailMarketing());
                modelo.setTextoModelo(novoTexto);
                if (!UtilCRCStringValidador.isNuloOuEmbranco(novoAssunto)) {
                    modelo.setAssunto(novoAssunto);
                }
                atualizarEntidade(modelo);
                //retorna a execução atualizada (diálogo com a resposta da IA e tokens)
                setRetorno(execucao);
            }
        }.getResposta();
    }

    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL_MARKETING)
    public static ItfRespostaAcaoDoSistema criarDialogoModeloEmailMarketing(ExecucaoIAModeloEmailMkt pDialogo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pDialogo.getModeloEmailMarketing() == null) {

                    throw new ErroRegraDeNegocio("Informe o modelo de e-mail marketing");
                }
                pDialogo.setTipoExecucao(FabTipoExecucaoIA.EMAIL_MARKETING.getRegistro());
                if (pDialogo.getPersona() == null) {
                    pDialogo.setPersona(pDialogo.getModeloEmailMarketing().getPersonaCoautoria());
                }

                setRetorno(atualizarEntidade(pDialogo));
            }
        }.getResposta();
    }

    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema criarDialogoAtividade(ExecucaoIAAtividade pDialogo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pDialogo.getAtividade() == null) {
                    throw new ErroRegraDeNegocio("Informe a atividade");
                }
                pDialogo.setTipoExecucao(FabTipoExecucaoIA.EXECUCAO_ATIVIDADE.getRegistro());
                if (pDialogo.getPersona() == null && pDialogo.getAtividade().getTipoAtividade() != null) {
                    pDialogo.setPersona(pDialogo.getAtividade().getTipoAtividade().getPersona());
                }

                setRetorno(atualizarEntidade(pDialogo));
            }
        }.getResposta();
    }

    /**
     * Executa o diálogo e aplica no e-mail vinculado à atividade o assunto e o
     * corpoHtml da última resposta estruturada da IA.
     */
    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_ATUALIZAR_EMAIL_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema emailAtividadeAtualizar(ExecucaoIAAtividade pDialogo) {
        String statusEmail = null;
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            AtividadeCRM atividadeAtual = UtilSBPersistencia.loadEntidade(pDialogo.getAtividade(), em);
            if (atividadeAtual != null && atividadeAtual.getEmailVinculado() instanceof EnvioEmail) {
                statusEmail = UtilCRMIA.getStatusEmailAtual((EnvioEmail) atividadeAtual.getEmailVinculado());
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        if (statusEmail != null && pDialogo.getNovaMensagem() != null && !pDialogo.getNovaMensagem().contains("Execute este pedido: [")) {
            String mensagemComStatusAtual = "Execute este pedido: [" + pDialogo.getNovaMensagem() + "] sabendo que  o status atual deste email é: " + statusEmail;
            pDialogo.setNovaMensagem(mensagemComStatusAtual);
        }
        ItfRespostaAcaoDoSistema respostaConsultaIA = ModuloExecucaoAcao.executarDialogo(pDialogo);

        if (!respostaConsultaIA.isSucesso()) {
            return getNovaResposta(ExecucaoIAAtividade.class).addErro(respostaConsultaIA.getMensagens().get(0).getMenssagem());
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ExecucaoIAAtividade execucao = loadEntidade((ExecucaoIAAtividade) respostaConsultaIA.getRetorno());
                if (execucao.getAtividade() == null) {
                    throw new ErroRegraDeNegocio("A execução não possui atividade");
                }
                JsonObject conteudo = getUltimaRespostaEstruturada(execucao.getDialogoJson());
                String novoTexto = conteudo.getString("corpoHtml", null);
                String novoAssunto = conteudo.getString("assunto", null);
                if (UtilCRCStringValidador.isNuloOuEmbranco(novoTexto)) {
                    throw new ErroRegraDeNegocio("A IA não retornou o conteúdo do e-mail");
                }
                AtividadeCRM atividade = loadEntidade(execucao.getAtividade());
                EmailCrm email = atividade.getEmailVinculado();
                if (email == null) {
                    throw new ErroRegraDeNegocio("A atividade não possui e-mail vinculado");
                }
                email.setTexto(novoTexto);
                if (!UtilCRCStringValidador.isNuloOuEmbranco(novoAssunto)) {
                    email.setAssunto(novoAssunto);
                }
                atualizarEntidade(email);
                //retorna a execução atualizada (diálogo com a resposta da IA e tokens)
                setRetorno(execucao);
            }
        }.getResposta();
    }

    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL)
    public static ItfRespostaAcaoDoSistema criarDialogoModeloEmail(ExecucaoIAModeloEmailAtividade pDialogo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pDialogo.getModeloEmail() == null) {
                    throw new ErroRegraDeNegocio("Informe o modelo de e-mail");
                }
                pDialogo.setTipoExecucao(FabTipoExecucaoIA.EMAIL_TRANSACIONAL.getRegistro());
                if (pDialogo.getPersona() == null) {
                    pDialogo.setPersona(pDialogo.getModeloEmail().getPersonaCoautoria());
                }

                setRetorno(atualizarEntidade(pDialogo));
            }
        }.getResposta();
    }

    /**
     * Executa o diálogo e aplica o assunto e o corpoHtml da última resposta
     * estruturada da IA no modelo de e-mail em edição, sem gravar no banco: o
     * modelo alterado é o recebido em
     * {@link ExecucaoIAModeloEmailAtividade#getEntidadeVinculada()} (a
     * instância da página) e o usuário confirma salvando o formulário.
     */
    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_ATUALIZAR_MODELO_EMAIL_TRANSITORIO)
    public static ItfRespostaAcaoDoSistema modeloEmailTransitorioAtualizar(ExecucaoIAModeloEmailAtividade pDialogo) {

        ItfRespostaAcaoDoSistema respostaConsultaIA = ModuloExecucaoAcao.executarDialogo(pDialogo);

        if (!respostaConsultaIA.isSucesso()) {
            return getNovaResposta(ExecucaoIAModeloEmailAtividade.class).addErro(respostaConsultaIA.getMensagens().get(0).getMenssagem());
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ExecucaoIAModeloEmailAtividade execucao = loadEntidade((ExecucaoIAModeloEmailAtividade) respostaConsultaIA.getRetorno());
                if (execucao.getModeloEmail() == null) {
                    throw new ErroRegraDeNegocio("A execução não possui modelo de e-mail");
                }
                if (!(pDialogo.getEntidadeVinculada() instanceof ModeloEmail)
                        || !execucao.getModeloEmail().getId().equals(pDialogo.getEntidadeVinculada().getId())) {
                    throw new ErroRegraDeNegocio("O modelo de e-mail em edição não foi informado");
                }
                JsonObject conteudo = getUltimaRespostaEstruturada(execucao.getDialogoJson());
                String novoTexto = conteudo.getString("corpoHtml", null);
                String novoAssunto = conteudo.getString("assunto", null);
                if (UtilCRCStringValidador.isNuloOuEmbranco(novoTexto)) {
                    throw new ErroRegraDeNegocio("A IA não retornou o conteúdo do e-mail");
                }
                //Transitório: altera somente a instância em edição, a gravação fica a cargo do formulário
                ModeloEmail modelo = (ModeloEmail) pDialogo.getEntidadeVinculada();
                modelo.setTextoModelo(novoTexto);
                if (!UtilCRCStringValidador.isNuloOuEmbranco(novoAssunto)) {
                    modelo.setAssunto(novoAssunto);
                }
                execucao.setEntidadeVinculada(modelo);
                //retorna a execução atualizada (diálogo com a resposta da IA e tokens)
                setRetorno(execucao);
            }
        }.getResposta();
    }

    /**
     * Com structured output a IA devolve o JSON no texto do bloco "text" da
     * última mensagem do assistente
     */
    private static JsonObject getUltimaRespostaEstruturada(String pDialogoJson) throws ErroRegraDeNegocio {
        JsonArray dialogo = UtilCRCJson.getJsonArrayByTexto(pDialogoJson);
        for (int i = dialogo.size() - 1; i >= 0; i--) {
            JsonObject mensagem = dialogo.getJsonObject(i);
            if (!"assistant".equals(mensagem.getString("role", null))) {
                continue;
            }
            JsonValue conteudo = mensagem.get("content");
            if (conteudo != null && conteudo.getValueType() == JsonValue.ValueType.ARRAY) {
                for (JsonValue bloco : conteudo.asJsonArray()) {
                    if (bloco.getValueType() == JsonValue.ValueType.OBJECT
                            && "text".equals(bloco.asJsonObject().getString("type", null))) {
                        return UtilCRCJson.getJsonObjectByTexto(bloco.asJsonObject().getString("text"));
                    }
                }
            } else if (conteudo != null && conteudo.getValueType() == JsonValue.ValueType.STRING) {
                return UtilCRCJson.getJsonObjectByTexto(mensagem.getString("content"));
            }
            break;
        }
        throw new ErroRegraDeNegocio("Nenhuma resposta da IA encontrada no diálogo");
    }

}
