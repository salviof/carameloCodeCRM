/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.ModuloCRMMauticSync;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.FabTipoExecucaoIA;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.ia.controller.ModuloExecucaoAcao;
import br.org.coletivoJava.integracoes.claude.api.FabConfigModuloIAClaude;
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

            /**
             * Com structured output a IA devolve o JSON no texto do bloco
             * "text" da última mensagem do assistente
             */
            private JsonObject getUltimaRespostaEstruturada(String pDialogoJson) throws ErroRegraDeNegocio {
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
        }.getResposta();
    }

    @InfoAcaoCRM_IA(acao = FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL)
    public static ItfRespostaAcaoDoSistema criarDialogoModeloEmail(ExecucaoIAModeloEmailMkt pDialogo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDialogo), pDialogo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pDialogo.getModeloEmailMarketing() == null) {
                    FabConfigModuloIAClaude
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

}
