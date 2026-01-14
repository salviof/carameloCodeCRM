package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.api.erp.erpintegracao.servico.ItfIntegracaoERP;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import br.org.coletivoJava.integracoes.restInterprestfull.api.FabIntApiRestIntegracaoERPRestfull;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.FabTipoParametroWhatsapp;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.ParametroMensgemWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.UtilSBServicoArqEntidadeS3;
import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.model.HashsDeArquivoDeEntidade;
import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConviteAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DadosProspectoGoogle;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.ServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.sms.MensagemSMS;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.FabTipoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.transitorio.DadosPesquisaGooglePlace;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.UtilModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMDadoOrigemCaminhoRelativo;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.InfoAcaoCRMAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.FabAcaoCrmAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.InfoAcaoCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail.atividadeCRMComplementarSalvarDadosDinamicos;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail.gerarDocumentos;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail.iniciarAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail.ASSUNTO_PADRAO;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.RespostaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.RespostaComGestaoEntityManagerSomenteLeitura;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCClienteRest;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCInputOutputConversoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.apache.commons.codec.net.URLCodec;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.CPMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.FabStatusPedidoAcesso;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcessoPessoa;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao;

/**
 *
 * @author sfurbino
 */
public class ModuloCRMAtendimento extends ControllerAbstratoSBPersistencia {

    private static final Gson GSON_RESPOSTA = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE)
    private static ItfRespostaAcaoDoSistema iniciarAtendimentoPorTelefone(String pTelefone, EntityManager pem) {

        ItfRespostaAcaoDoSistema resp = getNovaRespostaComAutorizacao(AtividadeCRM.class);

        addMensagemNulo(pTelefone, "Telefone", resp);

        PessoaJuridica empresa = new PessoaJuridica();
        if (empresa.loadEmpresabyContato(pTelefone, pem)) {
            ItfRespostaAcaoDoSistema resposta = iniciarAtendimento(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro(), empresa, false);
            return resposta;
        }
        return resp.addMensagemErroDisparaERetorna("Erro cadastrando nova Atividade");
    }

    /**
     *
     *
     * Inicia um Atendimento Receptivo a partir de um telefone, criando um novo
     * prospecto caso não encontre o número
     *
     * @param pTelefone
     * @param pem
     * @return
     */
    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_RECEPTIVO)
    public static ItfRespostaAcaoDoSistema iniciarAtendimentoReceptivoPorTelefone(String pTelefone, EntityManager pem) {
        return iniciarAtendimentoPorTelefone(pTelefone, pem);

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_ATIVO)
    public static ItfRespostaAcaoDoSistema iniciarAtendimentoAtivoPorTelefone(String pTelefone, EntityManager pem) {
        return iniciarAtendimentoPorTelefone(pTelefone, pem);
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR_E_CONCLUIR)
    public static ItfRespostaAcaoDoSistema atividadeEmailEnviareConcluir(AtividadeCRM pAtividade) {

        ItfRespostaAcaoDoSistema resp = atividadeEmailEnviar(pAtividade);
        if (resp.isSucesso()) {
            return new RespostaComGestaoEMRegraDeNegocioPadrao(resp, (ComoEntidadeSimples) resp.getRetorno()) {
                @Override
                public void regraDeNegocio() {
                    if (isSucesso()) {
                        atividadeConcluir(pAtividade);
                    }
                }
            }.getResposta();
        } else {
            return resp;
        }

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR)
    public static ItfRespostaAcaoDoSistema atividadeEmailEnviar(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                //      UtilCRCEmail.enviarPorServidorPadrao(pDestinatario, pMensagem, pAssunto, pAnexos);
                if (UtilCRCStringValidador.isNuloOuEmbranco(pAtividade.getEmailVinculado().getAssunto())
                        || pAtividade.getEmailVinculado().getAssunto().length() < 3 || pAtividade.getEmailVinculado().getAssunto().equals(ASSUNTO_PADRAO)) {
                    pAtividade.getEmailVinculado().setAssunto("");
                    throw new ErroRegraDeNegocio("Por favor, defina o assunto.");
                }
                EnvioEmailAtividade emailVinculado = (EnvioEmailAtividade) atualizarEntidade(pAtividade.getEmailVinculado(), false);
                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                atividade.setEmailVinculado(emailVinculado);
                if (atividade.getTipoAtividade().isPrecisaTerAnexo()) {

                    if (emailVinculado.getArquivosAnexados().isEmpty()) {
                        throw new ErroRegraDeNegocio("Selecione um anexo para este e-mail");
                    }

                }
                if (emailVinculado.getContatos().isEmpty()) {
                    throw new ErroRegraDeNegocio("Adicione um contato para enviar o email");

                }

                if (emailVinculado.getArquivosAnexados().isEmpty()) {
                    if (!emailVinculado.getAtividade().getDocumentosGerados().isEmpty()) {
                        throw new ErroRegraDeNegocio("Os documentos da atividade não foram anexados");

                    }

                }

                File[] arquivos = new File[emailVinculado.getArquivosAnexados().size()];
                int i = 0;
                for (ArquivoAnexado docApresentacao : emailVinculado.getArquivosAnexados()) {
                    String arquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(docApresentacao.getCampoInstanciadoByNomeOuAnotacao("arquivo"));
                    arquivos[i] = new File(arquivo);
                    i++;
                }

                if (isSucesso()) {
                    UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, SBCore.getUsuarioLogado().getId(),
                            UtilSBPersistencia.getNovoEM());
                    List<UsuarioCRM> usuariosEmCopia = new ArrayList();
                    List<ContatoProspecto> contatoEmcopia = new ArrayList();
                    if (atividade.getEmailComoEnvio().isEnviarCopiaColaboradores()) {
                        emailVinculado.getProspecto().getUsuariosResponsaveis().forEach((usr) -> {
                            usuariosEmCopia.add(UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, usr.getId(), UtilSBPersistencia.getNovoEM()));
                        });
                    }
                    usuariosEmCopia.stream().forEach(usr -> contatoEmcopia.add(usr.getContatoVinculado()));

                    try {

                        boolean emailenviado = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), emailVinculado, (List) emailVinculado.getArquivosAnexados());

                        if (emailenviado) {
                            emailVinculado.setStatusEnvio(FabStatusEnvioEmail.ENVIADO.getRegistro());
                            emailVinculado.setDatadisparo(new Date());

                            atividade.setAnotacoes("Email:" + emailVinculado.getAssunto());
                            emailVinculado.setTexto(ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().aplicarAssinaturaEMail(SBCore.getUsuarioLogado(), emailVinculado.getTexto()));
                            atividade = atualizarEntidade(atividade, false);
                            emailVinculado.setAtividade(atividade);

                            atualizarEntidade(emailVinculado, false);
                        } else {
                            throw new ErroRegraDeNegocio("Ouve um erro tentando enviar o e-mail, verifique se o e-email está correto, caso esteja, procure o suporte, ou tente novamente.");
                        }
                    } catch (ErroEnvioEmail erro) {
                        throw new ErroRegraDeNegocio("Erro enviando email");
                    }

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_RASCUNHO)
    public static ItfRespostaAcaoDoSistema atividadeEmailSalvarRascunhoEmail(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pAtividade)) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                EnvioEmail email = atividade.getEmailComoEnvio();
                if (email.getStatusEnvio() == null) {
                    email.setStatusEnvio(FabStatusEnvioEmail.RASCUNHO.getRegistro());
                } else {
                    if (!email.getStatusEnvio().equals(FabStatusEnvioEmail.RASCUNHO.getRegistro())) {
                        throw new ErroRegraDeNegocio("O status do email não permite edição");
                    }

                }

                if (UtilCRCStringValidador.isNuloOuEmbranco(atividade.getAnotacoes())) {
                    atividade.setAnotacoes(pAtividade.getEmailVinculado().getAssunto());
                    atividade = atualizarEntidade(atividade);

                }

                // EnvioEmailRascunhoAutoSave ultimoRascunho = pAtividade.getEmailVinculado().getUltimoRascunho();
                // if (ultimoRascunho == null || ultimoRascunho.getTexto().length() != pAtividade.getEmailVinculado().getTexto().length()) {
                //   List<EnvioEmailRascunhoAutoSave> consulta = new ConsultaDinamicaDeEntidade(EnvioEmailRascunhoAutoSave.class, getEm())
                //           .addCondicaoManyToOneIgualA("emailVinculado", atividade.getEmailVinculado())
                //           .resultadoRegistros();
                //  Collections.reverse(consulta);
                // consulta.stream().skip(10).forEach(getEm()::remove);
                //  atividade = UtilSBPersistencia.loadEntidade(atividade, getEmResposta());
                //EnvioEmailAtividade email = atividade.getEmailVinculado();
                //email.setTexto(pAtividade.getEmailVinculado().getTexto());
                //email.setNome(pAtividade.getEmailVinculado().getAssunto());
                // atualizarEntidade(email);
                setRetorno(atividade);
            }

        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_FORMATADO)
    public static ItfRespostaAcaoDoSistema atividadeEmailFormatado(AtividadeCRM pAtividade) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pAtividade)) {
            @Override
            public void regraDeNegocio() {

                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());

                if (UtilCRCStringValidador.isNuloOuEmbranco(atividade.getAnotacoes())) {
                    atividade.setAnotacoes(pAtividade.getEmailVinculado().getAssunto());
                    atualizarEntidade(atividade);
                }

                atividade = UtilSBPersistencia.loadEntidade(atividade, getEmResposta());
                EnvioEmailAtividade email = atividade.getEmailComoEnvio();
                email.setTexto(pAtividade.getEmailVinculado().getTexto());
                email.setNome(pAtividade.getEmailVinculado().getAssunto());
                email.setStatusEnvio(FabStatusEnvioEmail.FORMATADO.getRegistro());
                atualizarEntidade(email);
                atividade = UtilSBPersistencia.loadEntidade(atividade, getEmResposta());
                setRetorno(atividade);

            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ENVIAR_CONVITE)
    public static ItfRespostaAcaoDoSistema conviteEnviar(final CodigoConviteAtividade pCodigoConvite) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pCodigoConvite), pCodigoConvite) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                try {
                    boolean novoCodigo = false;
                    if (pCodigoConvite.getId() == null) {
                        novoCodigo = true;
                        String urlChamada = "[URL_CHAMADA]";
                        AtividadeCRM atividadeVinculada = UtilSBPersistencia.loadEntidade(pCodigoConvite.getAtividade(), getEm());
                        pCodigoConvite.setAssunto("Convite para colaborar");
                        pCodigoConvite.setConteudo(
                                UtilCRCComunicacao.getSaudacao() + ", Você foi convidado para colaborar, executando a atividade: " + atividadeVinculada.getTipoAtividade().getNome() + ", clique no link a seguir para colaborar."
                                + " <a href=\"" + urlChamada + "\">  " + urlChamada + " </a>");

                    }

                    CodigoConviteAtividade codigoGerado = atualizarEntidade(pCodigoConvite, false);
                    if (codigoGerado == null || codigoGerado.getId() == null) {
                        addErro("Ouve um erro Gerando o código de acesso.");
                        return;

                    }
                    if (!novoCodigo) {
                        EnvioEmailAtividade novoEnvio = new EnvioEmailAtividade();
                        novoEnvio.prepararNovoObjeto(codigoGerado.getAtividade());
                        List<ContatoProspecto> contatos = new ArrayList();
                        contatos.add(codigoGerado.getContato());

                        novoEnvio = atualizarEntidade(novoEnvio, false);

                        AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pCodigoConvite.getAtividade(), getEm());
                        atividade.setEmailVinculado(novoEnvio);
                        atualizarEntidade(atividade, false);

                        if (ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().
                                enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), novoEnvio, (List) novoEnvio.getArquivosAnexados())) {
                            throw new ErroRegraDeNegocio("Ouve um erro ao tentar enviar o e-mail ao usuaŕio");
                        } else {
                            novoEnvio.setStatusEnvio(FabStatusEnvioEmail.ENVIADO.getRegistro());
                        }
                    }
                } catch (ErroEnvioEmail p) {
                    throw new ErroRegraDeNegocio("Ouve um erro ao tentar enviar o e-mail ao usuaŕio");
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando convite " + t.getMessage(), t);
                    addErro("Erro ideterminado gerando convite, entre em contato com o suporte");
                }
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_CTR_PESQUISAR)
    public static ItfRespostaAcaoDoSistema descobrirProspectos(final DadosPesquisaGooglePlace pDados) {

        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pDados)) {
            @Override
            public void regraDeNegocio() {
                try {

                    URLCodec codec = new URLCodec();
                    String pesquisaTermo = pDados.getTermo().replace(" ", "+");
                    String pesquisaCidade = pDados.getLocal().replace(" ", "+");
                    JsonObject prospectosJson = null;
                    if (pDados.isTemPageToken()) {

                        prospectosJson = UtilCRCClienteRest.getObjetoJsonPorUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?pagetoken=" + pDados.getPageToken() + "&key=AIzaSyBq19FFkPPmCY9r17dcrkxqMr3KZcFbp_M&language=pt-BR");
                        pDados.getDadosEncontrados().clear();

                    } else {
                        prospectosJson = UtilCRCClienteRest.getObjetoJsonPorUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + pesquisaTermo + "+in+" + pesquisaCidade + "&key=AIzaSyBq19FFkPPmCY9r17dcrkxqMr3KZcFbp_M&language=pt-BR");
                    }
                    try {
                        String erro = (String) prospectosJson.getString("error_message");
                        if (erro != null) {
                            SBCore.enviarAvisoAoUsuario("Erro conectando com google empresas " + erro);
                        }
                    } catch (Throwable t) {

                    }
                    JsonArray listadeEmpresas = (JsonArray) prospectosJson.getJsonArray("results");
                    if (prospectosJson.getString("next_page_token") != null) {
                        String token = (String) prospectosJson.getString("next_page_token");
                        System.out.println(token);
                        pDados.setPageToken(token);
                    } else {
                        pDados.setPageToken(null);
                    }

                    for (Iterator iterator1 = listadeEmpresas.iterator(); iterator1.hasNext();) {
                        try {
                            JsonObject empresa = (JsonObject) iterator1.next();
                            String idEmpresa = (String) empresa.getString("place_id");
                            JsonObject jsonRespostaDetalhesEmpresa = UtilCRCClienteRest.getObjetoJsonPorUrl("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + idEmpresa + "&key=AIzaSyBq19FFkPPmCY9r17dcrkxqMr3KZcFbp_M");
                            JsonObject detalhes = (JsonObject) jsonRespostaDetalhesEmpresa.getJsonObject("result");
                            DadosProspectoGoogle dadosEmpresa = new DadosProspectoGoogle(empresa, detalhes);
                            try {
                                Pessoa prosp = dadosEmpresa.gerarProspecto();
                                System.out.println(prosp.getNome());
                            } catch (Throwable t) {
                                System.out.println("Erro " + t.getMessage());
                            }

                            pDados.adicionarDado(dadosEmpresa);
                            // JSONObject jsonDetalhesEmpresa = UtilCRCClienteRest.getObjetoJsonPorUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + pesquisaTermo + "+in+" + pesquisaCidade + "&key=AIzaSyBq19FFkPPmCY9r17dcrkxqMr3KZcFbp_M");
                        } catch (Throwable t) {
                            System.out.println("Erro " + t.getMessage());
                        }

                    }
                    setRetorno(pDados);
                } catch (Throwable t) {
                    setRetorno(pDados);
                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONCLUIR_MAIS_TARDE)
    public static ItfRespostaAcaoDoSistema salvarAtividadeComoInacabado(final AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());

                atividade.setStatusAtividade(FabStatusAtividade.EM_ANDAMENTO.getRegistro());
                atividade.setAnotacoes(pAtividade.getAnotacoes());
                AtividadeCRM ativiadadeGerada = (AtividadeCRM) atualizarEntidade(atividade, false);

                if (ativiadadeGerada == null) {

                    addMensagemErroDisparaERetorna("Erro salvando atividade");

                }
                // addAviso("Atividade armazenada como não concluída");
                setRetorno(ativiadadeGerada);
            }

        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS_CONCLUINDO_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema atividadeCRMComplementarSalvarDadosDinamicosEConcluir(final AtividadeCRM pAtividade) {
        pAtividade.setDadosRevisados(true);
        UtilSBPersistencia.mergeRegistro(pAtividade);

        ItfRespostaAcaoDoSistema resp = atividadeCRMComplementarSalvarDadosDinamicos(pAtividade);
        if (!resp.isSucesso()) {
            return resp;

        }

        ItfRespostaAcaoDoSistema respEtapa2 = atividadeConcluir((AtividadeCRM) resp.getRetorno());
        return respEtapa2;

    }

    public static ItfRespostaAcaoDoSistema prospectoAtualizarDadosDinamicos(Pessoa p) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(p), p) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                p.getDadosColetados().forEach(dado -> {
                    if (!UtilCRCStringValidador.isNuloOuEmbranco(dado.getTipoDadoCRM().getCampoProspectoCorrespondente())) {
                        try {
                            if (p.getCampoInstanciadoByNomeOuAnotacao(dado.getTipoDadoCRM().getCampoProspectoCorrespondente()).getFabricaTipoAtributo().equals(dado.getCampoInstanciado().getFabricaTipoAtributo())) {
                                p.getCampoInstanciadoByNomeOuAnotacao(dado.getTipoDadoCRM().getCampoProspectoCorrespondente()).setValor(dado.getValor());
                            }
                        } catch (Throwable t) {
                            addAlerta("Erro atualizando campo vinculo  de  campo dinamico com campo da empresa: " + dado.getTipoDadoCRM().getLabel());
                        }
                    }
                });
                setRetorno(atualizarEntidade(p, false));

            }
        };
    }

    public static ItfRespostaAcaoDoSistema atividadeCRMGerarDocumentos(AtividadeCRM pAtividade) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pAtividade)) {
            @Override
            public void regraDeNegocio() {
                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade.getUsuarioInsersao(), getEm());
                gerarDocumentos(pAtividade, atividade.getUsuarioInsersao());

            }

        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema atividadecancelaAtividade(AtividadeCRM pAtividade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaComAutorizacao(AtividadeCRM.class
        ), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEm());

                atividadeAtualizada.setStatusAtividade(FabStatusAtividade.CANCELADA.getRegistro());

                AtividadeCRM atv = atualizarEntidade(atividadeAtualizada, false);
                setRetorno(atv);
            }
        }.getResposta();
    }

    /**
     *
     *
     * Salva uma atividade no sistema
     *
     * -> Se tiver modelo de documento vinculado, gera o documento ao Concluir
     * -> Se tiver um relacionamento gerador na atividade muda o relacionamento
     * do prospecto -> Se for uma atividade geradora marca como inconclusiva
     *
     *
     * @param pAtividade
     * @param pEm
     * @return
     */
    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONCLUIR_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema atividadeConcluir(final AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

                if (isSucesso()) {
                    ModuloCRMAplicacao.atividadeAcoesAtomaticasPosConclusao(pAtividade);
                } else {

                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividadeLoaded = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                atividadeLoaded.getDadosColetados().clear();

                if (atividadeLoaded.getStatusAtividade().equals(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro())) {
                    addErro("Impossível Salvar as alrerações, esta atividade já foi concluida");
                    return;
                }
                FabTipoBloqueio tipoBloqueio = (FabTipoBloqueio) atividadeLoaded.getCPinst(CPAtividadeCRM.tipobloqueio).getValor();
                if (tipoBloqueio != null) {

                    throw new ErroRegraDeNegocio(tipoBloqueio.gerarMensagemPorTipo(pAtividade));
                }

                /// Alterando RElacionamento Gerado do prospecto
                TipoRelacionamento novoRelacionamento = (TipoRelacionamento) atividadeLoaded.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.relacionamentogerado).getValor();
                if (novoRelacionamento != null) {
                    ItfResposta mudancaRelacionamento = ModuloCRMAplicacao.atividadeAlterarRElacionamentoPessoa(atividadeLoaded).dispararMensagens();

                    if (!mudancaRelacionamento.isSucesso()) {
                        mudancaRelacionamento.dispararMensagens();
                        throw new ErroRegraDeNegocio("Falha alterando o relacionamento para conclusão da atividade");
                    } else {
                        atividadeLoaded.setProspectoEmpresa((Pessoa) mudancaRelacionamento.getRetorno());
                    }

                }
                if (atividadeLoaded.getTipoAtividade().getTipoChatBot() != null
                        && atividadeLoaded.getProspectoEmpresa().getCPinst("contatoPrincipal").getValor() != null) {
                    ItfTokenAcessoDinamico tokenChat = SBCore.getServicoPermissao()
                            .gerarTokenDinamico(FabAcaoCRMCliente.FORM_CHAT_INTERATIVO_FRM_INTERACAO, atividadeLoaded, atividadeLoaded.getProspectoEmpresa().getContatoPrincipal().getEmail());
                    String url = SBCore.getServicoVisualizacao().getEndrRemotoFormularioTokenAcesso(tokenChat);
                    atividadeLoaded.getCPinst(CPAtividadeCRM.chatbot).getValor();

                }

                atividadeLoaded.setStatusAtividade(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro());

                for (DocumentoAtividadeCRM docGeradoAtividade : atividadeLoaded.getDocumentosGerados()) {

                    if (docGeradoAtividade.getModeloDocumento() != null) {
                        if (docGeradoAtividade.getModeloDocumento().getCategoriaArquivoEquipe() != null) {
                            docGeradoAtividade.setCategoriaArqEquipe(docGeradoAtividade.getModeloDocumento().getCategoriaArquivoEquipe());
                        }
                        if (docGeradoAtividade.getModeloDocumento().getCategoriaArquivoCliente() != null) {
                            ArquivoCliente novoArquivo = new ArquivoCliente();
                            novoArquivo.setProspecto(atividadeLoaded.getProspectoEmpresa());
                            novoArquivo.setCategoriaArqCli(docGeradoAtividade.getModeloDocumento().getCategoriaArquivoCliente());
                            novoArquivo.setNome(docGeradoAtividade.getNome());
                            novoArquivo.setArquivo(docGeradoAtividade.getArquivo());
                            novoArquivo = atualizarEntidade(novoArquivo);
                            String jpaQl = UtilSBServicoArqEntidadeS3.getHSQLPesquisaHashsDeArquivoDeEntidade(DocumentoAtividadeCRM.class.getSimpleName(), "arquivo", String.valueOf(docGeradoAtividade.getId()));
                            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                            HashsDeArquivoDeEntidade arquivoHashAnterior = (HashsDeArquivoDeEntidade) UtilSBPersistencia.getRegistroByJPQL(jpaQl,
                                    HashsDeArquivoDeEntidade.class, em);
                            if (arquivoHashAnterior != null) {
                                HashsDeArquivoDeEntidade arquivo = new HashsDeArquivoDeEntidade();
                                arquivo.setEntidade(ArquivoCliente.class.getSimpleName());
                                arquivo.setAtributo("arquivo");
                                arquivo.setIdEntidade(novoArquivo.getId());
                                arquivo.setHashCalculado(arquivoHashAnterior.getHashCalculado());
                                atualizarEntidade(arquivo);
                            }
                        }
                    }

                }

                atividadeLoaded.setAnotacoes(pAtividade.getAnotacoes());

                setRetorno(atualizarEntidade(atividadeLoaded, false));

                //   atividadeGerada.gerarDocumentos();
                addAviso("Atividade Armazenada com sucesso!");

            }

        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_SERVICOS)
    public static ItfRespostaAcaoDoSistema prospectoSalvarServicos(final Pessoa pProspecto) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Orcamento pOrcamento = pProspecto.getUltimoOrcamento();
                pOrcamento = atualizarEntidade(pProspecto.getUltimoOrcamento(), false);

            }

        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ABANDONAR_PROSPECTO)
    public static ItfRespostaAcaoDoSistema prospectoAbandonar(final Pessoa pProspecto) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Pessoa prospAtualizado = loadEntidade(pProspecto);

                UsuarioCRM usuarioLogado = loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado());
                if (!prospAtualizado.getUsuariosResponsaveis().contains(usuarioLogado)) {
                    addErro("Seu usuário já estava desvinculado a empresa");
                } else {
                    if (prospAtualizado.getAtividadesEmAndamento().stream().filter(atv -> atv.getUsuarioAtividade().equals(usuarioLogado)).findFirst().isPresent()) {
                        addErro("Existem atividades em aberto em seu nome para esta empresa");
                        return;
                    }
                    UsuarioCRM usrRemocao = prospAtualizado.getUsuariosResponsaveis().stream().filter(usr -> usr.equals(usuarioLogado)).findFirst().get();
                    prospAtualizado.getUsuariosResponsaveis().remove(usrRemocao);
                    if (prospAtualizado.getUsuariosResponsaveis().isEmpty()) {
                        prospAtualizado.setUsuarioResponsavel(null);
                        prospAtualizado.setUmPerfilPrivado(false);
                        prospAtualizado.setUmPerfilPublico(true);
                    }
                    UtilSBPersistencia.mergeRegistro(prospAtualizado, getEm());
                }
            }
        };
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema prospectoRemover(final Pessoa pProspecto) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //chamada super do metodo (implementação classe pai)
                if (isSucesso()) {
                    setProximoFormulario(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR.getRegistro().getComoFormulario());
                } else {
                    setProximoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA.getRegistro().getComoFormulario());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                UsuarioCRM usuarioLogado = UtilModulosCRM.getUsuarioCRMLogado(getEmResposta());
                Pessoa prospAtualizado = loadEntidade(pProspecto);

                if (!prospAtualizado.getUsuariosResponsaveis().contains(usuarioLogado)) {
                    throw new ErroRegraDeNegocio("Seu usuário, não consta como responsável por este prospecto.");
                }

                if (prospAtualizado.getContatoPrincipal() != null) {
                    removerEntidade(prospAtualizado.getContatoPrincipal());
                }
                for (ContatoProspecto ct : prospAtualizado.getContatosProspecto()) {
                    removerEntidade(ct);
                }

                prospAtualizado.getServicos().forEach(srv -> {
                    removerEntidade(srv);
                });

                prospAtualizado.getServicos().clear();
                prospAtualizado.getHistoricoRelacionamento().forEach(hist -> {
                    removerEntidade(hist);
                });

                prospAtualizado.getEmailsEnviadoRecebido().forEach(emailEnviadoRecebido -> {
                    if (emailEnviadoRecebido.isUmEmailRecebido()) {
                        ((EmailRecebido) emailEnviadoRecebido).getArquivosRecebidos().forEach(arm -> {
                            removerEntidade(arm);
                        });
                    }
                    removerEntidade(emailEnviadoRecebido);
                });
                prospAtualizado.getArquivos().forEach(arq -> {
                    removerEntidade(arq);
                });
                prospAtualizado.getTodosdadosColetados().forEach(dado -> {
                    removerEntidade(dado);
                });
                prospAtualizado.getOrcamentos().forEach(orc -> {
                    removerEntidade(orc);

                });
                UtilSBPersistencia.executaSQL(getEm(), "delete from " + ServicoOferecido.class.getSimpleName() + " where  prospecto_id=" + pProspecto.getId());

                prospAtualizado.getServicos().stream().forEach(this::removerEntidade);
                prospAtualizado.getAtividadesRealizadas().forEach(atv -> {
                    if (atv != null) {
                        atv.getDadosVinculoDireto().forEach(vc -> removerEntidade(vc));
                        atv.getDadosColetados().forEach(dc -> removerEntidade(dc));
                        atv.getDadosNaoColetados().forEach(dnc -> removerEntidade(dnc));
                        removerEntidade(atv);
                    }
                });
                removerEntidade(prospAtualizado);

            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ATUALIZAR_MAUTIC)
    public static ItfRespostaAcaoDoSistema prospectoAtualizarMautic(final Pessoa pPessoaJuridica) {
        return new RespostaComGestaoEntityManagerSomenteLeitura(getNovaRespostaAutorizaChecaNulo(pPessoaJuridica)) {
            private String zeraNulo(String pValor) {
                if (pValor == null) {
                    return "";
                } else {
                    return pValor;
                }
            }

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    System.out.println(pPessoaJuridica.getNome() + " sincronizada com sucesso no mautic");
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (!FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().isTemTokemAtivo()) {
                    FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().gerarNovoToken();
                    if (!FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().isTemTokemAtivo()) {
                        throw new ErroRegraDeNegocio("O mautic não está conectado, impossível atualizar o Leed no maútic");
                    }
                }

                String observacao = null;
                String relatorio = new String();
                Pessoa pessoaJuridicaAtualizada = loadEntidade(pPessoaJuridica);
                if (pessoaJuridicaAtualizada.getObservacao() != null) {
                    observacao = pessoaJuridicaAtualizada.getObservacao();
                }
                if (pessoaJuridicaAtualizada.getOrigem() != null) {
                    observacao += "[" + pessoaJuridicaAtualizada.getOrigem().getNome() + "]";
                } else {
                    observacao += "[Sem_Origem_Definida]";
                }
                if (pessoaJuridicaAtualizada.getCPinst("porte").getValor() != null) {
                    observacao += "[" + pessoaJuridicaAtualizada.getCPinst("porte").getValorComoEntidadeSimples().getNome() + "]";
                } else {
                    observacao += "[Sem_Porte_Definido]";
                }
                String email = pessoaJuridicaAtualizada.getEmail();
                String site = pessoaJuridicaAtualizada.getComoPessoaJuridica().getSite();
                String telolofone = pessoaJuridicaAtualizada.getTelefonePrincipal();
                observacao += "[codigoIntra:" + String.valueOf(pessoaJuridicaAtualizada.getId()) + "]";
                observacao += "[" + pessoaJuridicaAtualizada.getRelacionamento().getNome() + "]";
                if (pessoaJuridicaAtualizada.getRelacionamento().getMetaRelacionamento() != null) {
                    observacao += "[" + pessoaJuridicaAtualizada.getRelacionamento().getMetaRelacionamento().getNome() + "]";
                } else {
                    observacao += "[Sem Meta Definida]";
                }
                StringBuilder tags = new StringBuilder();
                pessoaJuridicaAtualizada.getTagsAtendimento().forEach(tg -> {
                    tags.append("[").append(tg.getNome()).append("]");
                });
                if (!tags.toString().isEmpty()) {
                    observacao += tags.toString();
                }
                if (!FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getGestaoToken().getStatusToken().equals(FabStatusToken.ATIVO)) {
                    FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getGestaoToken().getComoGestaoOauth().validarToken();
                }
                if (UtilCRCStringValidador.isNAO_NuloNemBranco(pessoaJuridicaAtualizada.getEmail())
                        && !pessoaJuridicaAtualizada.getEmail().contains("informado")) {
                    String idEmpresa = "0";
                    try {
                        ItfAcaoApiRest registroUnico = FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getAcao(pessoaJuridicaAtualizada.getEmail());
                        if (registroUnico.getResposta().getCodigoResposta() != 404) {
                            try {
                                JsonObject empresas = registroUnico.getResposta().getRespostaComoObjetoJson().getJsonObject("companies");
                                for (Object codEmpresa : empresas.keySet()) {
                                    idEmpresa = codEmpresa.toString();
                                }
                            } catch (Throwable t) {
                                //PessoaJuridica não existe
                            }
                        }
                    } catch (Throwable t) {
                        throw new UnsupportedOperationException("Falha conectando com Mautic");
                    }
                    ItfAcaoApiRest conexaoAtualizarEmpresa = null;
                    if (idEmpresa == null || Integer.valueOf(idEmpresa) != 0) {
                        conexaoAtualizarEmpresa = FabMauticContatoRest.EMPRESA_CTR_SALVAR_EDITAR_EMPRESA.getAcao(idEmpresa,
                                pessoaJuridicaAtualizada.getNome(),
                                zeraNulo(email),
                                zeraNulo(site),
                                zeraNulo(telolofone),
                                zeraNulo(observacao)
                        );
                    } else {
                        conexaoAtualizarEmpresa = FabMauticContatoRest.EMPRESA_CTR_SALVAR_NOVA_EMPRESA.getAcao(
                                pessoaJuridicaAtualizada.getNome(),
                                zeraNulo(email),
                                zeraNulo(site),
                                zeraNulo(telolofone),
                                zeraNulo(observacao)
                        );
                    }

                    if (conexaoAtualizarEmpresa == null) {
                        System.out.println("Falha enviando comando para " + FabMauticContatoRest.EMPRESA_CTR_SALVAR_EDITAR_EMPRESA);
                    } else {
                        ItfRespostaWebServiceSimples respAtualizacaoEmpresa = conexaoAtualizarEmpresa.getResposta();
                        if (respAtualizacaoEmpresa != null) {
                            if (!respAtualizacaoEmpresa.isSucesso()) {
                                relatorio += "\n Erro enviando" + pessoaJuridicaAtualizada.getNome() + respAtualizacaoEmpresa.getMensagens();

                            } else {
                                idEmpresa = String.valueOf(respAtualizacaoEmpresa.getRespostaComoObjetoJson().getJsonObject("company").getInt("id"));
                                final String empresaAtualizada = idEmpresa;
                                pessoaJuridicaAtualizada.getContatosProspecto().forEach(ct -> {
                                    String idContato = null;
                                    if (!UtilCRCStringValidador.isNuloOuEmbranco(ct.getEmail())
                                            && !UtilCRCStringValidador.isNuloOuEmbranco(ct.getNome())) {
                                        String contatoPrimeiroNome = zeraNulo(ct.getPrimeiroNome());
                                        String contatoSobrenove = "";
                                        if (!UtilCRCStringValidador.isNuloOuEmbranco(ct.getPrimeiroNome())) {
                                            contatoSobrenove = zeraNulo(ct.getNome().replace(ct.getPrimeiroNome(), ""));
                                        }

                                        String contatoEmail = zeraNulo(ct.getEmail());
                                        String contatoMobile = zeraNulo(ct.getCelular());

                                        try {
                                            ItfAcaoApiRest registroContato = FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getAcao(ct.getEmail());
                                            JsonObject contatosEncontrados = registroContato.getResposta().getRespostaComoObjetoJson();
                                            if (contatosEncontrados != null) {
                                                Iterator<String> codigosContatoEncontradas = contatosEncontrados.get("contacts").asJsonObject().keySet().iterator();
                                                if (codigosContatoEncontradas.hasNext()) {
                                                    idContato = contatosEncontrados.get("contacts").asJsonObject().keySet().iterator().next();
                                                }
                                            }
                                        } catch (Throwable t) {

                                        }

                                        if (idContato == null) {
                                            idContato = "0";
                                        }

                                        List<String> tagsString = ct.getProspecto().getComoPessoaJuridica().getTagsAtendimento().stream().map(tag -> tag.getNome()).collect(Collectors.toList());
                                        ItfAcaoApiRest conexaoEditar = FabMauticContatoRest.CONTATO_CTR_SALVAR_EDITAR_CONTATO.
                                                getAcao(idContato, contatoEmail, contatoPrimeiroNome,
                                                        contatoSobrenove, contatoMobile, tagsString
                                                );

                                        JsonObject jsonContatoAtualizado = conexaoEditar.getResposta().getRespostaComoObjetoJson();
                                        idContato = String.valueOf(jsonContatoAtualizado.getJsonObject("contact").getInt("id"));

                                        //UtilCRCJson.getValorApartirDoCaminho("contact.id", jsonContatoAtualizado);
                                        try {
                                            System.out.println("Vinculando contato" + idContato + " com empresa " + empresaAtualizada);
                                            ItfAcaoApiRest adicionarContato = FabMauticContatoRest.EMPRESA_CTR_SALVAR_ADICIONAR_CONTATO.getAcao(empresaAtualizada, idContato);
                                            if (!adicionarContato.getResposta().isSucesso()) {
                                                throw new UnsupportedOperationException("Erro adicionando contato" + empresaAtualizada + "----" + idContato);
                                            }
                                        } catch (Throwable t) {
                                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando contato" + t.getMessage(), t);
                                            throw new UnsupportedOperationException("Erro adicionando contato" + empresaAtualizada + "----" + idContato + " Erro" + t.getMessage());
                                        }

                                    }
                                });

                            }
                        }
                    }

                }

            }

        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.CONTATO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema contatoRemover(ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                super.executarAcoesIniciais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ContatoProspecto contato = loadEntidade(pContato);
                boolean permissao = false;
                if (pContato.getProspecto().getContatoPrincipal().equals(pContato)) {
                    throw new ErroRegraDeNegocio("o contato principal não pode ser removido");
                }
                Pessoa pessoa = loadEntidade(pContato.getProspecto());
                if (SBCore.isEmModoProducao()) {
                    try {
                        permissao = pessoa.getCPinst("atendenteLogadoResponsavelPrincipal").getValorComoBoolean();
                        //pessoa.getContatosProspecto().remove(contato);
                        if (!permissao) {
                            throw new ErroRegraDeNegocio("Sem permissão para exclusão");
                        }
                    } catch (Throwable t) {

                    }
                }

                //removerEntidade(contato.getUsuarioVinculado());
                // removerEntidade(contato);
                UtilSBPersistencia.executaSQL(getEMResposta(), "delete from envioDocumento_contatos where contatos_id = " + pContato.getId());

                UsuarioCrmCliente usuarioVinculado = contato.getUsuarioVinculado();
                if (usuarioVinculado == null) {
                    List<UsuarioCrmCliente> usuarios = new ConsultaDinamicaDeEntidade(UsuarioCrmCliente.class, getEm()).addCondicaoManyToOneIgualA(CPUsuarioCrmCliente.contatoclientevinculado, contato).resultadoRegistros();
                    if (!usuarios.isEmpty()) {
                        usuarioVinculado = usuarios.get(0);
                    }
                }

                if (usuarioVinculado != null) {
                    if (pessoa.getContatoPrincipal().getUsuarioVinculado() != null) {
                        UtilSBPersistencia.executaSQL(getEMResposta(), "update ChamadoCliente set usuarioCliente_id = " + pessoa.getContatoPrincipal().getUsuarioVinculado().getId() + " where usuarioCliente_id =" + usuarioVinculado.getId());
                    } else {
                        UtilSBPersistencia.executaSQL(getEMResposta(), "delete ChamadoCliente  where usuarioCliente_id =" + usuarioVinculado.getId());
                    }
                    UtilSBPersistencia.executaSQL(getEMResposta(), "update ContatoProspecto set usuarioVinculado_id = null where usuarioVinculado_id =" + usuarioVinculado.getId());
                    UtilSBPersistencia.executaSQL(getEMResposta(), "delete from UsuarioSB where id = " + usuarioVinculado.getId());

                }

                UtilSBPersistencia.executaSQL(getEMResposta(), "delete from ContatoProspecto where id = " + pContato.getId());
                //atualizarEntidade(pessoa);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.CONTATO_CTR_SALVAR)
    public static ItfRespostaAcaoDoSistema contatoSalvarMerge(ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pContato.getEmail())) {
                    throw new ErroRegraDeNegocio("O e-mail é obrigatório");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pContato.getNome())) {
                    throw new ErroRegraDeNegocio("O nome é obrigatorio");
                }
                if (pContato.getProspecto() == null) {
                    throw new ErroRegraDeNegocio("O prospecto é obrigatorio");
                }
                setRetorno(atualizarEntidade(pContato));

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE_PESSOA_FISICA)
    public static ItfRespostaAcaoDoSistema prospectoSalvarPessoaFisica(final PessoaFisica pProspecto) {
        return prospectoSalvar(pProspecto);
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema prospectoSalvarPessoaJuridica(final Pessoa pProspecto) {
        return prospectoSalvar(pProspecto);
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE_PESSOA_GENERICO)
    public static ItfRespostaAcaoDoSistema prospectoCriarUsuarios(final Pessoa pProspecto) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Pessoa pessoa = loadEntidade(pProspecto);
                for (ContatoProspecto ct : pessoa.getContatosProspecto()) {
                    if (ct.getUsuarioVinculado() == null) {
                        ct.getCPinst("usuarioVinculado").getValor();
                        UsuarioCrmCliente usuarioCliente = atualizarEntidade(ct.getUsuarioVinculado());
                        ct.setUsuarioVinculado(usuarioCliente);
                        atualizarEntidade(ct);
                    }
                }
            }

        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE_PESSOA_GENERICO)
    public static ItfRespostaAcaoDoSistema prospectoSalvar(final Pessoa pProspecto) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                if (isSucesso()) {
                    ModuloCRMAtendimento.prospectoCriarUsuarios(pProspecto);
                    ModuloCRMAtendimento.prospectoAtualizarMautic((Pessoa) pProspecto);
                    addAviso("Alguns dados de [" + pProspecto.getNome() + "] foram atualizado");
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                try {
                    TipoRelacionamento relacionamento = pProspecto.getRelacionamento();
                    if (relacionamento == null) {
                        if (pProspecto.getOrigem() != null) {
                            if (pProspecto.getOrigem().getRelacionamentoPadrao() != null) {
                                relacionamento = pProspecto.getOrigem().getRelacionamentoPadrao();
                            }
                        }
                        if (relacionamento == null) {
                            relacionamento = (TipoRelacionamento) UtilSBPersistencia.getListaRegistrosByHQL("from TipoRelacionamento where peso=?0", 1, getEm(), 0).get(0);
                        }
                        if (relacionamento != null) {
                            pProspecto.prepararNovoObjeto(relacionamento);
                        }
                    }

                } catch (Throwable r) {

                    addAviso("Nenhum Relacionamento peso 0 foi encontrada no sistema");
                    TipoRelacionamento relacionamento = (TipoRelacionamento) UtilSBPersistencia.getListaRegistrosByHQL("from TipoRelacionamento where peso> ?", 1, getEm(), -5).get(0);
                    pProspecto.prepararNovoObjeto(relacionamento);

                }
                //ListenerEntidadePessoa listenerEntidade = new ListenerEntidadePessoa();
                // listenerEntidade.acaoAntesDeAtualizar(pProspecto);

                ContatoProspecto novocontatoPrincipal = null;
                if (pProspecto.getId() == null) {
                    pProspecto.setDataHoraMudancaRelacionamento(new Date());
                    if (pProspecto.getOrigem() instanceof OrigemProspectoPrivado) {
                        OrigemProspectoPrivado privado = (OrigemProspectoPrivado) pProspecto.getOrigem();
                        if (!pProspecto.getUsuariosResponsaveis().contains(privado.getUsuarioDono())) {
                            pProspecto.getUsuariosResponsaveis().add(privado.getUsuarioDono());
                            pProspecto.setUmPerfilPrivado(true);
                        }
                    }
                    if (pProspecto.getContatoPrincipal() != null) {
                        if (!UtilCRCStringValidador.isNuloOuEmbranco(pProspecto.getContatoPrincipal().getNome())) {
                            novocontatoPrincipal = pProspecto.getContatoPrincipal();
                        }
                    }
                }

                for (ContatoProspecto contato : pProspecto.getContatosProspecto()) {
                    contato.getCPinst(CPContatoProspecto.celularformatointernacional).getValor();
                    if (contato.getUsuarioVinculado() != null) {
                        if (!UtilCRCStringValidador.isNuloOuEmbranco(contato.getEmail())) {
                            try {
                                contato.getUsuarioVinculado().getCPinst(CPContatoProspecto.email).setValorSeValido(contato.getEmail());
                            } catch (ErroValidacao ex) {
                                throw new ErroRegraDeNegocio(ex.getMensagemAoUsuario());
                            }
                        }
                        contato.getUsuarioVinculado().setNome(contato.getNome());//
                        // UtilSBPersistencia.mergeRegistro(contato.getUsuarioVinculado(), getEm());
                    }
                }
                // prosp = atualizarEntidade(pProspecto, false);

                if (pProspecto.isUmPerfilPrivado()) {
                    pProspecto.setUmPerfilPublico(false);
                } else if (pProspecto.getUsuariosResponsaveis().isEmpty()) {
                    pProspecto.setUmPerfilPrivado(false);
                    pProspecto.setUmPerfilPublico(true);
                }

                //  ContatoProspecto contatoPrAtualizado = atualizarEntidade(prosp.getContatoPrincipal(), true);
                ContatoProspecto contato = (ContatoProspecto) pProspecto.getCPinst(CPPessoa.contatoprincipal).getValor();
                if (contato.getEmail() != null) {
                    pProspecto.getContatoPrincipal().getCPinst(CPContatoProspecto.usuariovinculado).getValor();
                    contato = pProspecto.getContatoPrincipal();
                }

                UsuarioCrmCliente usuarioCliente = contato.getUsuarioVinculado();
                if (!pProspecto.getContatosProspecto().contains(contato)) {
                    pProspecto.getContatosProspecto().add(contato);
                }
                //Pessoa prosp = atualizarEntidade(pProspecto, true);
                //validarAtributos(pProspecto);
                Pessoa prosp = UtilSBPersistencia.mergeRegistro(pProspecto, getEm());
                setRetorno(prosp);
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS_EXCLUIR_ARQUIVOS_ORFAOS)
    public static ItfRespostaAcaoDoSistema prospectoexcluirArquivosProsp(final List<ArquivoAnexado> pArquivos) {

        if (!pArquivos.isEmpty()) {
            return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ArquivoAnexado.class
            ), pArquivos.get(0)) {
                @Override
                public void executarAcoesIniciais() throws ErroEmBancoDeDados {

                    getEm();

                }

                @Override
                public void executarAcoesFinais() throws ErroEmBancoDeDados {

                    UtilSBPersistencia.fecharEM(getEm());
                }

                @Override
                public void regraDeNegocio() {

                    List<String> arquivosParaExclusao = new ArrayList();
                    if (!pArquivos.isEmpty()) {
                        Pessoa prospAtualizado = loadEntidade(pArquivos.get(0).getProspecto());

                        prospAtualizado.getArquivos().stream().filter((arquivo) -> (!pArquivos.contains(arquivo))).map((arquivo) -> {
                            if (UtilSBPersistencia.exluirRegistro(arquivo)) {

                                arquivosParaExclusao.add(arquivo.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().getCaminhoArquivoLocal());
                                return arquivo;
                            } else {
                                return null;
                            }
                            //arquivo.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().excluirArquivo();

                        }).forEachOrdered((arquivo) -> {
                            if (arquivo != null) {
                                arquivo.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().excluirArquivo();
                                System.out.println("exluido" + arquivo.getId());
                            }
                            //removerEntidade(arquivo);
                            //getEm().flush();
                        });
                    }
                }
            };
        }
        return getNovaResposta(ArquivoAnexado.class
        );
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_CLIENTE)
    public static ItfRespostaAcaoDoSistema arquivoClienteSalvar(final ArquivoCliente parquivo
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(parquivo), parquivo) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                    try {

                        ArquivoCliente arquivoCliente = UtilSBPersistencia.loadEntidade(parquivo, em);
                        Pessoa pessoa = arquivoCliente.getProspecto();
                        ContatoProspecto contatoPrincipal = pessoa.getContatoPrincipal();
                        if (contatoPrincipal.isPossuiEmail()) {

                            ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA, arquivoCliente.getCategoriaArqCli(), contatoPrincipal.getEmail());

                            token = UtilSBPersistencia.mergeRegistro(token);

                            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario("FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB", token);

                            url = url.replace("https://crm.", "https://atendimento.");

                            UsuarioCRM remetente = (UsuarioCRM) SBCore.getUsuarioLogado();

                            if (contatoPrincipal.isPossuiEmail()) {
                                try {
                                    ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), contatoPrincipal, SBCore.getUsuarioLogado().getNome() + " compartilhou o arquivo " + arquivoCliente.getArquivo() + " com você ",
                                            "Para ver o arquivo acesse a àrea do cliente clicando <h1><a href='" + url + "'> neste link </a></h1> ");
                                } catch (ErroEnvioEmail ex) {
                                    addErro("Falha enviando email para " + contatoPrincipal.getEmail());
                                }

                            }

                            if (contatoPrincipal.isPossuiTelefone()) {
                                FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(contatoPrincipal.getCelular()), SBCore.getUsuarioLogado().getNome() + " compartilhou o arquivo " + arquivoCliente.getArquivo() + " com você,acesse via " + url).getResposta().isSucesso();
                            }
                        }
                    } finally {
                        UtilSBPersistencia.fecharEM(em);
                    }

                }
            }

            @Override

            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (parquivo.getUsuarioCriou() == null) {
                    parquivo.setUsuarioCriou((UsuarioSB) SBCore.getUsuarioLogado());
                } else {
                    parquivo.setUsuarioAtualizou((UsuarioSB) SBCore.getUsuarioLogado());
                }
                ArquivoCliente arquivo = atualizarEntidade(parquivo);
                if (!arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()) {
                    throw new ErroRegraDeNegocio("Falha salvando arquivo ");
                }

                String hashstr = SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(arquivo.getCPinst("arquivo"));
                ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class, getEm());

                novaconsulta.addCondicaoManyToOneIgualA("categoriaArqCli", arquivo.getCategoriaArqCli());
                novaconsulta.addCondicaoManyToOneIgualA("prospecto", arquivo.getProspecto());
                List<ArquivoCliente> arquivosComEsteHash = (List) novaconsulta.resultadoRegistros();
                Optional<ArquivoCliente> arquivoMesmoHash = arquivosComEsteHash.stream().filter(arq -> arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo())
                        .filter(arq -> arq.getId() != arquivo.getId()
                        && arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()
                        && SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(arq.getCPinst("arquivo")).equals(hashstr)).findFirst();
                if (arquivoMesmoHash.isPresent()) {
                    ArquivoCliente arquivoDuplicado = arquivoMesmoHash.get();
                    arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().excluirArquivo();
                    removerEntidade(arquivo);
                    throw new ErroRegraDeNegocio("O arquivo já se encontra compartilhado com o cliente nesta categoria com o nome" + arquivoDuplicado.getArquivo());
                }

                setRetorno(arquivo);
            }

        }
                .dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_EQUIPE)
    public static ItfRespostaAcaoDoSistema arquivoEquipeSalvar(final ArquivoAnexado parquivo
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(parquivo), parquivo) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                    try {
                        ArquivoAnexado arquivo = UtilSBPersistencia.loadEntidade((ArquivoAnexado) getRetorno(), em);

                        for (UsuarioCRM usr : arquivo.getProspecto().getUsuariosResponsaveis()) {
                            if (usr.equals(SBCore.getUsuarioLogado())) {
                                continue;
                            }
                            if (!UtilCRCStringValidador.isNuloOuEmbranco(usr.getEmail()) && usr.getEmail().equals("camila@casanovadigital.com.br")) {
                                boolean usuarioIsResp = false;
                                if (arquivo.getProspecto().getUsuarioResponsavel() != null) {
                                    if (arquivo.getProspecto().getUsuarioResponsavel().equals(usr)) {
                                        usuarioIsResp = true;
                                    }
                                }
                                if (arquivo.getProspecto().getUsuarioAtendimento() != null) {
                                    if (arquivo.getProspecto().getUsuarioAtendimento().equals(usr)) {
                                        usuarioIsResp = true;
                                    }
                                }
                                if (!usuarioIsResp) {
                                    continue;
                                }
                            }
                            if (usr.getCodigoMatrix() != null) {
                                ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

                                String linkPasta = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_EQUIPE, arquivo.getCategoriaArqEquipe(), arquivo.getProspecto());
                                try {
                                    servicoChat.enviarDirect(usr.getCodigoMatrix(), "Olá, " + SBCore.getUsuarioLogado().getNome()
                                            + " compartilhou o arquivo " + arquivo.getArquivo() + " em " + arquivo.getCategoriaArqEquipe().getNome() + " com você, para o cliente " + arquivo.getProspecto().getNome()
                                            + "para baixar acesse: " + linkPasta
                                    );
                                } catch (Throwable t) {
                                    addAlerta("Falha alertando " + usr.getNome() + " via Matrix");
                                }

                                if (!UtilCRCStringValidador.isNuloOuEmbranco(usr.getEmail())) {
                                    StringBuilder mensagemTexto = new StringBuilder();

                                    String linkAbrir = arquivo.getCPinst(CPArquivoAnexado.arquivo).getComoArquivoDeEntidade().getLinkAbrirArquivo();
                                    String linkBaixar = arquivo.getCPinst(CPArquivoAnexado.arquivo).getComoArquivoDeEntidade().getLinkBaixarArquivo();
                                    mensagemTexto.append("Olá, ").append(usr.getNome())
                                            .append(", ")
                                            .append(SBCore.getUsuarioLogado().getNome()).append(" compartilhou <h2>").append(arquivo.getArquivo()).append("</h2> com a equipe de atendimento da ")
                                            .append(arquivo.getProspecto().getNome()).
                                            append(" <br/> seguem os links pra você:   <br/> ").
                                            append("<br/> <h1> <a href='");
                                    mensagemTexto.append(linkBaixar).append("' > Baixe o arquivo aqui </a> </h1> <br/>");

                                    mensagemTexto.append(" <h1> <a href='");
                                    mensagemTexto.append(linkAbrir).append("' > Abra o arquivo aqui </a> </h1> <br/>");

                                    mensagemTexto.append(" <h1> <a href='");
                                    mensagemTexto.append(linkPasta).append("' > Abra a pasta aqui </a> </h1> <br/>");
                                    String msgEmail = mensagemTexto.toString();
                                    try {
                                        boolean emailenviado = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(),
                                                usr,
                                                "Estou compartilhou um arquivo  " + arquivo.getProspecto().getNome() + " com a equipe", msgEmail);
                                    } catch (ErroEnvioEmail ex) {
                                        addAviso(usr.getNome() + " não foi notificado por email");
                                    }

                                }
                            }
                        }
                    } finally {
                        UtilSBPersistencia.fecharEM(em);
                    }
                }

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (!SBCore.getServicoPermissao().isObjetoPermitidoUsuario(SBCore.getUsuarioLogado(), parquivo)) {
                    throw new ErroRegraDeNegocio("Sem permissão para esta operação");
                }

                if (parquivo.getUsuarioCriou() == null) {
                    parquivo.setUsuarioCriou((UsuarioSB) SBCore.getUsuarioLogado());
                } else {
                    parquivo.setUsuarioAtualizou((UsuarioSB) SBCore.getUsuarioLogado());
                }
                if (!parquivo.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()) {
                    throw new ErroRegraDeNegocio("Falha salvando arquivo do cliente");
                }

                ArquivoAnexado arquivo = atualizarEntidade(parquivo);

                String hashstr = SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(arquivo.getCPinst("arquivo"));
                ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class,
                        getEm());
                if (parquivo.getCategoriaArqEquipe() == null) {
                    throw new ErroRegraDeNegocio("A categoria é obrigatória");
                }
                novaconsulta.addCondicaoManyToOneIgualA("categoriaArqEquipe", arquivo.getCategoriaArqEquipe());
                novaconsulta.addCondicaoManyToOneIgualA("prospecto", arquivo.getProspecto());
                List<ArquivoCliente> arquivosComEsteHash = (List) novaconsulta.resultadoRegistros();
                Optional<ArquivoCliente> arquivoMesmoHash = arquivosComEsteHash.stream().filter(arq -> arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo())
                        .filter(arq -> arq.getId() != arquivo.getId()
                        && arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()
                        && SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(arq.getCPinst("arquivo")).equals(hashstr)).findFirst();
                if (arquivoMesmoHash.isPresent()) {
                    ArquivoCliente arquivoDuplicado = arquivoMesmoHash.get();
                    arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().excluirArquivo();
                    throw new ErroRegraDeNegocio("O arquivo já se encontra compartilhado com o cliente nesta categoria com o nome" + arquivoDuplicado.getArquivo());
                }

                setRetorno(arquivo);
            }
        }
                .dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS)
    public static ItfRespostaAcaoDoSistema prospectoSalvarAnexos(Pessoa prosp, final List<ArquivoAnexado> pArquivos) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(prosp), prosp) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                Pessoa prospectoAtualizado = loadEntidade(prosp);
                List<ArquivoAnexado> arqExcluxao = new ArrayList();

                prospectoAtualizado.getArquivos().stream().filter(arq -> arq.getId() != 0 && !pArquivos.contains(arq)).forEach(arqExcluxao::add);
                pArquivos.stream().filter(arq -> arq.getId() != 0 && !arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo() && !arqExcluxao.contains(arq)).forEach(arqExcluxao::add);

                arqExcluxao.forEach(arq -> {
                    if (arq.getId() != 0) {
                        arq.getEmailsEnviadosComAnexo().clear();
                        ArquivoAnexado arLoad = loadEntidade(arq);
                        arLoad.getEmailsEnviadosComAnexo().clear();
                        removerEntidade(arLoad);
                    } else {
                        removerEntidade(arq);

                    }
                    if (arq.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()) {
                        arq.getCPinst("arquivo").getComoArquivoDeEntidade().excluirArquivo();
                    }

                    prospectoAtualizado.getArquivos().remove(arq);

                });

                if (!pArquivos.isEmpty()) {
                    List<ArquivoAnexado> arquivosGerados = new ArrayList();
                    for (ArquivoAnexado arquivo : pArquivos) {
                        if (arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().isExisteArquivo()) {
                            if (arquivo.getId() == null) {
                                ArquivoAnexado arquivoGerado = atualizarEntidade(arquivo, false);

                                if (arquivoGerado == null) {
                                    addErro("Ouve um erro salvando o arquivo" + arquivo);
                                } else {
                                    arquivosGerados.add(arquivo);
                                    prospectoAtualizado.getArquivos().add(arquivoGerado);
                                }
                            }
                        }
                    }
                    setRetorno(arquivosGerados);
                }
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema salvarProspectoEExecutarAtividade(final Pessoa pProspecto
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(prospectoSalvar(pProspecto), pProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pProspecto, false);
                setProximoFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario());
                setRetorno(pProspecto);
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_CADASTRAR_DETALHES)
    public static ItfRespostaAcaoDoSistema salvarProspectoECadastrarDetalhes(final Pessoa pProspecto
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(prospectoSalvar(pProspecto), pProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pProspecto, false);
                setProximoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro().getComoFormulario());
                setRetorno(pProspecto);
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS)
    public static ItfResposta
            prospectoDadosDinamicosSalvar(List<DadoCRM> dadosDinamicos
            ) {
        if (dadosDinamicos == null) {
            return getNovaResposta(DadoCRM.class
            ).addMensagemErroDisparaERetorna("foram enviados dados nulos ");
        }
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(dadosDinamicos.get(0))) {
            @Override
            public void regraDeNegocio() {
                for (DadoCRM dado : dadosDinamicos) {
                    if (UtilCRCStringValidador.isNAO_NuloNemBranco(dado.getValor())) {
                        atualizarEntidade(dado);
                    }
                }
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_CTR_ENVIAR_EMAIL)
    public static ItfRespostaAcaoDoSistema envioDocumentoEnviar(EnvioEmail pacoteDeEnvio
    ) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pacoteDeEnvio)) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                try {
                    //      UtilCRCEmail.enviarPorServidorPadrao(pDestinatario, pMensagem, pAssunto, pAnexos);
                    if (pacoteDeEnvio.getContatos().isEmpty()) {
                        addErro("Nenhum contato foi adicionado");
                        return;
                    }
                    File[] arquivos = new File[pacoteDeEnvio.getArquivosAnexados().size()];
                    int i = 0;
                    for (ArquivoAnexado docmuentoGerado : pacoteDeEnvio.getArquivosAnexados()) {
                        String arquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(docmuentoGerado.getCampoInstanciadoByNomeOuAnotacao("arquivo"));
                        arquivos[i] = new File(arquivo);
                        i++;
                    }
                    UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEm());
                    List<UsuarioCRM> usuariosEmCopia = new ArrayList();
                    List<ContatoProspecto> pCpmtatosCopia = new ArrayList<>();

                    if (pacoteDeEnvio.isEnviarCopiaColaboradores()) {
                        pacoteDeEnvio.getProspecto().getUsuariosResponsaveis().forEach((usr) -> {
                            usuariosEmCopia.add(UtilSBPersistencia.getRegistroByID(UsuarioCRM.class,
                                    usr.getId(), UtilSBPersistencia.getNovoEM()));
                        });
                    }
                    usuariosEmCopia.stream().forEach(usr -> pCpmtatosCopia.add(usr.getContatoVinculado()));
                    try {
                        boolean emailenviado = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), pacoteDeEnvio, (List) pacoteDeEnvio.getArquivosAnexados());
                        if (emailenviado) {
                            pacoteDeEnvio.setTexto(ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().aplicarAssinaturaEMail(SBCore.getUsuarioLogado(), pacoteDeEnvio.getTexto()));
                            atualizarEntidadeConfigRetorno(pacoteDeEnvio);
                        }
                    } catch (ErroEnvioEmail ex) {
                        throw new ErroRegraDeNegocio("Falha enviando email" + ex.getMensagemUsuario());
                    }

                } catch (Throwable t) {
                    throw new ErroRegraDeNegocio("Erro: " + t.getMessage());

                }
            }
        }
                .dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEU_PERFIL_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema meuPerfilSalvar(final UsuarioCRM pUsuario
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuario), pUsuario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.GRUPOADMIN)) {
                    if (!SBCore.getUsuarioLogado().equals(pUsuario)) {
                        throw new ErroRegraDeNegocio("Você não tem permissão para realizar esta alteração.");
                    }
                }

                if (pUsuario.getSenha() == null || pUsuario.getSenha().length() < 6) {
                    throw new ErroRegraDeNegocio("A senha precisa ter 6 caracteres");
                }

                //    if (pUsuario.getAssinaturaEmail() == null || pUsuario.getAssinaturaEmail().length() < 3) {
                //        addErro("Especifique uma assinatura de e-mail");
                //   }
                atualizarEntidade(pUsuario, false);
                setProximoFormulario(FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR.getRegistro().getComoFormulario());

            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEU_PERFIL_CTR_TESTAR)
    public static ItfRespostaAcaoDoSistema meuPerfilTestarEnvioEmail(final UsuarioCRM pUsuario
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuario), pUsuario) {
            @Override
            public void regraDeNegocio() {

//                pacoteDeEnvio.setProspecto((Pessoa) FabDadosIniciais.PROSPECTO1.getRegistro());
                UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class,
                        SBCore.getUsuarioLogado().getId(), UtilSBPersistencia.getNovoEM());

                try {

                    if (UtilCRCEmailAvancado.enviarEmailComAnexoV2(usuario.getCaixaPostalPrincipal(),
                            UtilCRCEmailAvancado.getJsonFromListaContato((List) Lists.newArrayList(usuario.getContatoVinculado())),
                            null, "Email teste CRM", "Apenas uma e-mail teste, o envio de e-mail ocorreu com sucesso", null) == null) {
                        addErro("Erro tentando enviar email, com servidor do usuário");

                    }
                } catch (Throwable ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, ex.getMessage(), ex);
                }

            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_LOGO_MARCA_ENCONTRADA)
    public static ItfRespostaAcaoDoSistema prospectoSalvarLogoEncontrada(final Pessoa prosp, String urlEmpresa
    ) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(prosp)) {
            @Override
            public void regraDeNegocio() {
                Pessoa prospAtualizado = UtilSBPersistencia.loadEntidade(prosp, getEm());
                try {
                    BufferedImage imagem = UTilSBCoreInputs.getImagemBufferedbyURL(urlEmpresa);
                    String extencao = UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivoSemPonto(urlEmpresa);
                    imagem = UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, extencao);
                    InputStream inputImagem = UtilCRCInputOutputConversoes.BufferedImageToInputStream(imagem, extencao);
                    inputImagem.reset();
                    prospAtualizado.uploadFotoTamanhoPequeno(inputImagem);
                } catch (Throwable t) {
                    addErro("Erro processando nova logomarca");
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processanod imagem", t);
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_REDUZIR_LOGO)
    public static ItfRespostaAcaoDoSistema prospectoSalvaRedimencionarLogo(final Pessoa prosp
    ) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(prosp)) {
            @Override
            public void regraDeNegocio() {
                Pessoa prospAtualizado = UtilSBPersistencia.loadEntidade(prosp, getEm());
                try {

                    if (!prospAtualizado.isTemImagemPequenaAdicionada()) {
                        throw new ErroRegraDeNegocio("Nenhuma imagem encontrada para redimencionar");
                    }
                    String url = prospAtualizado.getImgPequena();
                    BufferedImage imagem = UTilSBCoreInputs.getImagemBufferedbyURL(url);

                    String extencao = UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivoSemPonto(url);
                    imagem = UtilSBImagemEdicao.reduzirProporcionalAlturaMaxima(imagem, 85, extencao);
                    InputStream inputImagem = UtilCRCInputOutputConversoes.BufferedImageToInputStream(imagem, extencao);
                    inputImagem.reset();
                    prospAtualizado.uploadFotoTamanhoPequeno(inputImagem);
                } catch (Throwable t) {
                    addErro("Erro processando nova logomarca");
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processanod imagem", t);
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ALTERAR_RELACIONAMENTO)
    public static ItfRespostaAcaoDoSistema prospectoAlterarRelacionamento(final Pessoa prosp) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(prosp)) {

            private boolean dadoDefinido(TipoDadoCRM pTipodado, DadoCRM pDado) {
                if (pDado.getTipoDadoCRM().equals(pTipodado)) {
                    return true;
                }

                if (!UtilCRCStringValidador.isNuloOuEmbranco(pTipodado.getCampoProspectoCorrespondente())) {
                    if (prosp.getCampoInstanciadoByNomeOuAnotacao(pTipodado.getCampoProspectoCorrespondente()).isUmValorNuloOuEmBranco()) {
                        return true;

                    }
                }

                return false;
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Pessoa prospAtualizado = UtilSBPersistencia.loadEntidade(prosp, getEm());
                TipoRelacionamento novoRelacionamento = loadEntidade(prosp.getRelacionamento());
                if (prosp.getRelacionamento().equals(prospAtualizado.getRelacionamento())) {

                    throw new ErroRegraDeNegocio("Você escolheu o mesmo relacionamento, anterior");
                } else {
                    try {
                        for (TipoDadoCRM tipoDado : novoRelacionamento.getDadosNescessarios()) {
                            Optional<DadoCRM> dadoEncontrado = prospAtualizado.getDadosColetados().stream().filter(dado
                                    -> dadoDefinido(tipoDado, dado)
                            ).findFirst();
                            if (!dadoEncontrado.isPresent()) {
                                throw new ErroRegraDeNegocio("O campo de atividade:" + tipoDado.getLabel() + " é obrigatório para este tipo de atividade");
                            }
                        }
                        prospAtualizado.alterarRelacionamento(prosp.getRelacionamento());
                    } catch (ErroValidacao ex) {
                        addErro(ex.getMensagemAoUsuario());
                    }

                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_AGENDAR)

    public static ItfRespostaAcaoDoSistema agendarAtividade(final AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (pAtividade.getDataHoraPrevisaoExecucao() == null) {
                    addErro("Você precisa estabelecer a data e hora do agendamento");
                    return;
                }
                if (pAtividade.getDataHoraPrevisaoExecucao().getTime() < new Date().getTime()) {
                    addErro("Você precisa agendar a atividade para o futuro, pois o Dr. Brown (De devolta para o futuro) não finalizou seu trabalho.");
                    return;
                }

                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                atividadeAtualizada.setStatusAtividade(FabStatusAtividade.AGENDADO.getRegistro());
                atividadeAtualizada.setDataHoraPrevisaoExecucao(pAtividade.getDataHoraPrevisaoExecucao());
                atividadeAtualizada.setAnotacoes(pAtividade.getAnotacoes());

                AtividadeCRM ativiadadeGerada = (AtividadeCRM) atualizarEntidade(atividadeAtualizada, false);

                if (ativiadadeGerada == null) {

                    addErro("Erro salvando atividade");

                }
                if (isSucesso()) {

                    pAtividade.setStatusAtividade(FabStatusAtividade.AGENDADO.getRegistro());
                    addAviso("Atividade agendada para " + UtilCRCDataHora.converteDataEmStringFormatada(pAtividade.getDataHoraPrevisaoExecucao()));
                    setRetorno(ativiadadeGerada);
                }

            }

        };

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema orcamentoSalvar(final Orcamento orcamento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(orcamento), orcamento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (orcamento.getPessoa() == null) {
                    throw new ErroRegraDeNegocio("O Lead qualidficado do orçamento (Pessoa física ou empresa) é obrigatório.");
                }
                Orcamento orcamentoAtualizado = atualizarEntidade(orcamento);
                Pessoa pessoaVinculada = orcamentoAtualizado.getPessoa();
                pessoaVinculada.setUltimoOrcamento(orcamentoAtualizado);
                atualizarEntidade(pessoaVinculada);
                setRetorno(orcamentoAtualizado);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_SOLICITAR_ACESSO)
    public static ItfRespostaAcaoDoSistema pessoaSolicitarAcesso(final Pessoa pPessoa) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                Pessoa pessoa = loadEntidade(pPessoa);
                if (pessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor() == null) {
                    throw new ErroRegraDeNegocio("Falha buscando usuário responsável pela pessoa");
                }

                UsuarioCRM solicitante = loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado());
                UsuarioCRM usuarioResponsavel = pessoa.getUsuarioResponsavel();
                if (pessoa.getUsuarioResponsavel().getCodigoMatrix() != null) {
                    ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();
                    try {
                        servicoChat.enviarDirect(pessoa.getUsuarioResponsavel().getCodigoMatrix(), "Olá, " + solicitante.getNome() + " pede solicitação ao prospect" + pessoa.getNome());
                    } catch (ErroConexaoServicoChat ex) {
                        Logger.getLogger(ModuloCRMAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                PedidoAcessoPessoa pedido = new PedidoAcessoPessoa();
                pedido.setPessoa(pessoa);
                pedido.setStatus(FabStatusPedidoAcesso.AGUARDANDO_RESPOSTA.getRegistro());
                pedido.setUsuarioSolicitante(solicitante);
                pedido.setUsuarioConcedente(usuarioResponsavel);
                atualizarEntidade(pedido);
                addAviso("Um pedido foi enviado para " + usuarioResponsavel.getNome());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MINHAS_ORIGENS_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema origemSalvarMerge(final OrigemProspectoPrivado pOrigem) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pOrigem), pOrigem) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                OrigemProspectoPrivado origemPrivado = atualizarEntidade(pOrigem, true);
                setRetorno(origemPrivado);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ALTERAR_STATUS_PRIVADO)
    public static ItfRespostaAcaoDoSistema prospectoAlterarStatusRelacionamento(final Pessoa pPessoa) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                Pessoa pessoaJuridica = loadEntidade(pPessoa);
                pessoaJuridica.setUmPerfilPrivado(!pessoaJuridica.isUmPerfilPrivado());
                //atualizarEntidade(pessoaJuridica);
                if (pessoaJuridica.getUsuariosResponsaveis().isEmpty()) {
                    pessoaJuridica.getUsuariosResponsaveis().add((UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado()));
                }
                UtilSBPersistencia.mergeRegistro(pessoaJuridica, getEMResposta());
                if (pessoaJuridica.isUmPerfilPrivado()) {

                    addAviso("O Lead só poderá ser visto pelos colaboradores listados");
                } else {
                    addAviso("Lead poderá ser visto por todos usuários.");
                }
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_PASSAR_A_BOLA)
    public static ItfRespostaAcaoDoSistema prospectoPassarABola(final Pessoa pPessoa) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UsuarioCRM usuarioResponsavel = pPessoa.getUsuarioResponsavel();
                if (usuarioResponsavel == null) {
                    throw new ErroRegraDeNegocio("Defina o novo responsável");
                }

                Pessoa pessoa = loadEntidade(pPessoa);
                if (!pessoa.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor().equals(SBCore.getUsuarioLogado())) {
                    throw new ErroRegraDeNegocio("Somente o responsável atual pode passar a bola para outro consultor");
                }
                pessoa.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
                if (pessoa.getUsuarioResponsavel().equals(usuarioResponsavel)) {
                    throw new ErroRegraDeNegocio(pPessoa.getUsuarioResponsavel().getNome() + " já é o responsável ");
                }
                pessoa.setUsuarioResponsavel(usuarioResponsavel);
                UtilSBPersistencia.mergeRegistro(pessoa, getEm());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DADO_CRM_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema
            dadoDinamicoCRMSalvar(DadoCRM pDAdo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDAdo), pDAdo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pDAdo);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_SMS_ENVIAR)
    public static ItfRespostaAcaoDoSistema contatoProspectoEnviarSMS(final MensagemSMS pMensagem) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pMensagem), pMensagem) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pMensagem == null) {
                    throw new ErroRegraDeNegocio("A mensagem não pode ser nula");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pMensagem.getTexto())) {
                    throw new ErroRegraDeNegocio("A mensagem não pode ser nula");
                }
                if (pMensagem.getContato() == null) {
                    throw new ErroRegraDeNegocio("O contato não foi definido");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pMensagem.getContato().getCelular())) {
                    throw new ErroRegraDeNegocio("O Celular do contato não foi definido");
                }
                pMensagem.setDataHoraEnvio(new Date());
                MensagemSMS mensagemAtualizada = atualizarEntidade(pMensagem);
                String telefone = (String) pMensagem.getContato().getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.celularformatointernacional).getValor();
                ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, pMensagem.getTexto()).getResposta();
                String respostaStr = resposta.getRespostaTexto();
                if (!resposta.isSucesso()) {
                    throw new ErroRegraDeNegocio("Falha enviando SMS para " + telefone + " - Erro:" + respostaStr);
                }
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_SMS_FORMATAR.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_CTR_ENVIAR)
    public static ItfRespostaAcaoDoSistema contatoProspectoEnviarWhatzapMtk(final MensagemMktWhatsapp pMensagem) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pMensagem), pMensagem) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pMensagem == null) {
                    throw new ErroRegraDeNegocio("A mensagem não pode ser nula");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pMensagem.getTipo())) {
                    throw new ErroRegraDeNegocio("Especifique o tipo");
                }
                if (pMensagem.getContato() == null) {
                    throw new ErroRegraDeNegocio("O contato não foi definido");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pMensagem.getContato().getCelular())) {
                    throw new ErroRegraDeNegocio("O Celular do contato não foi definido");
                }
                pMensagem.setDataHora(new Date());
                MensagemMktWhatsapp mensagemAtualizada = atualizarEntidade(pMensagem);

                setRetorno(mensagemAtualizada);

                Pessoa pessoa = loadEntidade(pMensagem.getPessoa());
                String telefone = (String) pMensagem.getContato().getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.celularformatointernacional).getValor();
                TipoMensagemMktWhatsApp tipoMensagem = loadEntidade(pMensagem.getTipo());

                if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.GRUPOADMIN)) {
                    if (tipoMensagem.isEnvioUnico()) {
                        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(MensagemMktWhatsapp.class,
                                getEm()).addCondicaoManyToOneIgualA(CPMensagemMktWhatsapp.contato, pMensagem.getContato())
                                .addCondicaoManyToOneIgualA(CPMensagemMktWhatsapp.tipo, pMensagem.getTipo());
                        if (!novaConsulta.resultadoRegistros().isEmpty()) {
                            throw new ErroRegraDeNegocio("A Mensagem de whatasapp não foi enviada, pois o usuário já recebeu essa mensagem antes");
                        }
                    }
                }
                List<ParametroMensgemWhatsapp> listaPrs = new ArrayList();
                for (ParametroMensagemWtzap pr : tipoMensagem.getParametros()) {
                    String valor = null;
                    if (pr.isUmaOrigemDadpDinamico()) {
                        TipoDadoCRM tipoDado = loadEntidade(pr.getTipoDado());
                        DadoCRM novoDado = null;
                        if (mensagemAtualizada.getAtividade() == null) {
                            novoDado = FabDadoCRM.getDadoNovoSeNaoExistir(pessoa, tipoDado);
                        } else {
                            novoDado = FabDadoCRM.getDadoNovoSeNaoExistir(mensagemAtualizada.getAtividade(), tipoDado);
                        }

                        if (novoDado.getId() == null) {
                            novoDado.getValor();
                            novoDado = atualizarEntidade(novoDado);
                            Object valorDinamico = novoDado.getCampoInstanciado().getValor();
                            if (UtilCRCStringValidador.isNuloOuEmbranco(valorDinamico)) {
                                throw new ErroRegraDeNegocio("impossível obter o valor de dado pelo dado dinamico" + pr.getTipoDado().getId() + " - " + pr.getTipoDado().getNome());
                            }
                            valor = novoDado.getCampoInstanciado().getValorTextoFormatado();
                        } else {
                            atualizarEntidade(novoDado);
                            valor = novoDado.getCampoInstanciado().getValorTextoFormatado();
                        }
                    }
                    if (pr.isUmaOrigemCaminhoRelativo()) {
                        try {
                            valor = UtilCRMDadoOrigemCaminhoRelativo.getValor(mensagemAtualizada.getContato(), pr.getDadoRelativo());
                        } catch (ErroValidacao ex) {
                            throw new ErroRegraDeNegocio("impossível obter o valor de dado pelo caminho" + pr.getDadoRelativo());
                        }
                    }
                    if (UtilCRCStringValidador.isNuloOuEmbranco(valor)) {
                        throw new ErroRegraDeNegocio("impossível obter o valor de dado pelo dado dinamico" + pr.getTipoDado().getId() + " - " + pr.getTipoDado().getNome());
                    }
                    FabTipoParametroWhatsapp tipo = FabTipoParametroWhatsapp.getTipoByString(pr.getTipoParametroWtzap());
                    switch (tipo) {
                        case TEXT:
                            listaPrs.add(new ParametroMensgemWhatsapp(pr.getCodigoParametro(), valor, pr.isCabecalho()));
                            break;
                        case DOCUMENT:
                            listaPrs.add(new ParametroMensgemWhatsapp(pr.getCodigoParametro(), valor, pr.getNome(), "document", pr.isCabecalho()));
                            break;
                        case IMAGE:
                            listaPrs.add(new ParametroMensgemWhatsapp(pr.getCodigoParametro(), valor, pr.getNome(), "image", pr.isCabecalho()));
                            break;
                        case VIDEO:
                            listaPrs.add(new ParametroMensgemWhatsapp(pr.getCodigoParametro(), valor, pr.getNome(), "video", pr.isCabecalho()));
                            break;
                        case LOCATION:
                            break;

                        case RODAPE_BOTAO:

                            if (valor.length() == 36) {

                                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(TokenAcessoDinamico.class,
                                        getEm());
                                consulta.addcondicaoCampoIgualA("codigo", valor);
                                List<TokenAcessoDinamico> token = consulta.resultadoRegistros();
                                if (token.isEmpty()) {
                                    throw new ErroRegraDeNegocio("Codigo de acesso não encontrado");
                                }
                                valor = valor + "-" + token.get(0).getId() + "/.html";

                            }
                            listaPrs.add(new ParametroMensgemWhatsapp(pr.getCodigoParametro(), valor, pr.getNome(), "button", false));
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
                String codigoPadrao = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
                ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES.
                        getAcao(codigoPadrao, telefone, mensagemAtualizada.getTipo().getSlugTemplate(), listaPrs).getResposta();
                String respostaStr = resposta.getRespostaTexto();

                if (!resposta.isSucesso()) {
                    throw new ErroRegraDeNegocio("Falha enviando Whatsap para " + telefone + " - Erro:" + respostaStr);
                } else {
                    mensagemAtualizada.setEnviado(true);
                    addAviso("Mensagem enviada com sucesso para " + telefone);
                }
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_FRM_ENVIO_SUCESSO.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ACESSO_PESSOA)
    public static ItfRespostaAcaoDoSistema
            solicitacaoSolicitarAcessoCArd(final Pessoa pPessoa) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (isSucesso()) {
                    //    SBCore.getServicoComunicacao().getArmazenamento().registrarDialogo((ItfDialogo) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Pessoa pessoa = loadEntidade(pPessoa);

                ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(SolicitacaoAcessoCard.class,
                        getEm());
                novaconsulta.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitante, SBCore.getUsuarioLogado());
                novaconsulta.addCondicaoManyToOneIgualA(CPSolicitacao.pessoa, pessoa);
                novaconsulta.addCondicaoNegativo(CPSolicitacao.foifinalizada);
                if (!novaconsulta.resultadoRegistros().isEmpty()) {
                    throw new ErroRegraDeNegocio("Você já realizou essa solicitacao");
                }
                SolicitacaoAcessoCard solicitacao = new SolicitacaoAcessoCard();
                solicitacao.setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ACESSO.getRegistro());
                try {
                    solicitacao.prepararNovoObjeto(pessoa);
                } catch (ErroPreparandoObjeto ex) {
                    throw new ErroRegraDeNegocio("Houve um erro criando o envelope de solicitação" + ex.getMessage());
                }
                atualizarEntidadeConfigRetorno(solicitacao);
                addAviso("A solicitação foi enviada para " + solicitacao.getUsuarioSolicitado().getNome());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoEqipe(SolicitacaoArquivoEquipe pSolicitacao) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE, pSolicitacao, pSolicitacao.getPessoa());
                    String conteudo = pSolicitacao.getUsuarioSolicitante().getNome() + " solicita um arquivo em " + pSolicitacao.getCategoriaArqEquipe().getNome()
                            + " com a seguinte observacao: ''" + pSolicitacao.getObeservacao() + "'' atualize este arquivo clicando no link: ";

                    String conteudomail = conteudo + "<h1><a href='" + url + "'> LINK PARA UPLOAD </a> </h1> ";
                    String conteudoMatrix = conteudo + url;
                    try {
                        ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(pSolicitacao.getUsuarioSolicitado().getCodigoMatrix(),
                                conteudoMatrix
                        );
                    } catch (ErroConexaoServicoChat ex) {
                        Logger.getLogger(ModuloCRMAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), pSolicitacao.getUsuarioSolicitado(),
                                pSolicitacao.getUsuarioSolicitante().getNome() + " solicita um arquivo  para" + pSolicitacao.getPessoa().getNomeCurto(),
                                conteudomail);
                    } catch (ErroEnvioEmail ex) {
                        SBCore.enviarAvisoAoUsuario("A Solicitação foi registrada, mas o e-mail não pôde ser enviada");
                    }

                    //     SBCore.getServicoComunicacao().getArmazenamento().registrarDialogo((SolicitacaoArquivoEquipe) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getCategoriaArqEquipe() == null) {
                    throw new ErroRegraDeNegocio("Selecione uma categoria");
                }
                if (pSolicitacao.getDataHoraDataProgramada() == null) {
                    throw new ErroRegraDeNegocio("Defina a data limite para entrega");
                }
                if (pSolicitacao.getObeservacao() == null || pSolicitacao.getObeservacao().length() < 10) {
                    throw new ErroRegraDeNegocio("Descreva melhor sua solicitação");
                }
                setRetorno(atualizarEntidade(pSolicitacao));
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoEqipe(SolicitacaoConfirmacaoEquipe pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getObeservacao() == null || pSolicitacao.getObeservacao().length() < 10) {
                    throw new ErroRegraDeNegocio("Descreva melhor sua solicitação");
                }

                atualizarEntidade(pSolicitacao);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_CLIENTE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoCliente(final SolicitacaoArquivoCliente pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                atualizarEntidade(pSolicitacao);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarConfirmacaoCliente(SolicitacaoConfirmacaoCliente pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                atualizarEntidade(pSolicitacao);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_CONCEDER_ACESSO)
    public static ItfRespostaAcaoDoSistema solicitacaoConcederAcessoCard(SolicitacaoAcessoCard pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                SolicitacaoAcessoCard solicitacao = loadEntidade(pSolicitacao);
                if (!SBCore.getUsuarioLogado().getEmail().equals(solicitacao.getUsuarioSolicitado().getEmail())) {
                    throw new ErroRegraDeNegocio("Esta solicitação só pode ser atendida por " + solicitacao.getUsuarioSolicitado().getNome());
                }
                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiAtendida(true);
                atualizarEntidade(solicitacao);
                Pessoa pessoa = loadEntidade(pSolicitacao.getPessoa());
                pessoa.getUsuariosResponsaveis().add(solicitacao.getUsuarioSolicitante());
                atualizarEntidade(pessoa);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_NEGAR_ACESSO)
    public static ItfRespostaAcaoDoSistema solicitacaoNegarAcessoCard(SolicitacaoAcessoCard pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                SolicitacaoAcessoCard solicitacao = loadEntidade(pSolicitacao);
                if (!SBCore.getUsuarioLogado().equals(solicitacao.equals(solicitacao.getUsuarioSolicitado()))) {
                    throw new ErroRegraDeNegocio("Esta solicitação só pode ser atendida por " + solicitacao.getUsuarioSolicitado().getNome());
                }
                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiAtendida(false);
                atualizarEntidade(solicitacao);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_CARREGAR_SOLICITACOES_AUTO_EXEC)
    public static ItfRespostaAcaoDoSistema carregarSolicitacoes() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(new Solicitacao()), new Solicitacao()) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Solicitacao.class,
                        getEm());
                consulta.addCondicaoNegativo(CPSolicitacao.foifinalizada);
                List<Solicitacao> solicitacoes = consulta.resultadoRegistros();
                for (Solicitacao slc : solicitacoes) {
//                    SBCore.getServicoComunicacao().getArmazenamento().registrarDialogo(slc);
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoNotificarArquivoEquipe(SolicitacaoArquivoEquipe pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                SolicitacaoArquivoEquipe solicitacao = loadEntidade(pSOlicitacao);
                String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_ARQUIVOS, solicitacao.getPessoa(), solicitacao.getCategoriaArqEquipe());

                String conteudo = solicitacao.getUsuarioSolicitado().getNome() + " atendeu a sua solicitação <br/>"
                        + "e compartilhou com você um arquivo em " + solicitacao.getCategoriaArqEquipe().getNome() + ", acesse a pasta via: ";
                String conteudoEMail = conteudo + " <h1> <a href='" + url + "'> ESTE LINK </a></h1> ";
                String conteudoMatrix = conteudo + url;

                try {
                    ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(solicitacao.getUsuarioSolicitante().getCodigoMatrix(), conteudoMatrix);
                } catch (ErroConexaoServicoChat ex) {
                    Logger.getLogger(ModuloCRMAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(),
                            solicitacao.getUsuarioSolicitado(),
                            solicitacao.getUsuarioSolicitado().getNome() + " compartilhou um arquivo com você", conteudoEMail);
                } catch (ErroEnvioEmail ex) {
                    addAviso("O arquivo foi arquivado, mas o usuário solicitante não pôde ser notificado, houve falha no serviço de e-mail");
                }

                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiFinalizada(true);
                atualizarEntidade(solicitacao);
                SBCore.getServicoComunicacao().getArmazenamento().removerDialogoByCodigoSelo(solicitacao.getCodigoSelo());

            }
        }
                .getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_CONVERTER_PESSOA_FISICO_JURIDICO)
    public static ItfRespostaAcaoDoSistema pessoaConverter(Pessoa pPessoa) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

                    try {
                        Pessoa pessoa = UtilSBPersistencia.getRegistroByID(Pessoa.class,
                                pPessoa.getId(), em);
                        setRetorno(pessoa);
                        //  setUrlDestinoSucesso(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, pessoa));
                    } finally {
                        UtilSBPersistencia.fecharEM(em);
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Pessoa pessoa = loadEntidade(pPessoa);
                boolean resultado = false;
                if (pessoa.isUmaPessoaJuridica()) {
                    resultado = UtilSBPersistencia.executaSQL("update ProspectoEmpresa set umaPessoaFisica=true, umaPessoaJuridica = false,"
                            + "tipoPessoa = \"PessoaFisica\", "
                            + "cnpj=null "
                            + "where id=" + pessoa.getId() + ";");
                }
                if (pessoa.isUmaPessoaFisica()) {
                    resultado = UtilSBPersistencia.executaSQL("update ProspectoEmpresa set umaPessoaFisica=false, umaPessoaJuridica = true,"
                            + "tipoPessoa = \"PessoaJuridica\", "
                            + "cpf=null "
                            + "where id=" + pessoa.getId() + ";");
                }
                if (!resultado) {
                    throw new ErroRegraDeNegocio("Falha modificando tipo de pessoa");
                }
                setProximoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_ENVIAR_LINK_REUNIAO)
    public static ItfRespostaAcaoDoSistema pessoaConverter(ReservaHorario pReserval) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserval), pReserval) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHorario reserva = loadEntidade(pReserval);
                reserva.getComoReservaVideoConferencia().setLinkConferencia(pReserval.getComoReservaVideoConferencia().getLinkConferencia());
                boolean resultado = false;

                if (!(reserva instanceof ReservaHoraRemotoVideo)) {
                    throw new ErroRegraDeNegocio("O compromisso não é do tipo Remoto");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(reserva.getComoReservaVideoConferencia().getLinkConferencia())) {
                    throw new ErroRegraDeNegocio("Insira o link da conferência");
                }
                reserva.setStatus(FabStatusReservaHorario.CONFIRMADO.getRegistro());
                reserva = atualizarEntidade(reserva);
                boolean notificacao = false;
                String frase = "A reunião agendada com " + reserva.getAtendenteResponsavel().getNome() + " iniciou, segue o link para acesso: ";
                String telefone = (String) reserva.getAtendidoResponsavel().getContatoClienteVinculado().getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.celularformatointernacional).getValor();
                if (!UtilCRCStringValidador.isNuloOuEmbranco(telefone)) {
                    ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, frase + reserva.getComoReservaVideoConferencia().getLinkConferencia()).getResposta();
                    String respostaStr = resposta.getRespostaTexto();
                    notificacao = resposta.isSucesso();
                    if (!resposta.isSucesso()) {
                        addAlerta("Falha enviando SMS para " + telefone + " - Erro:" + respostaStr);

                    }
                }

                String conteudoemail = frase + "<center><h1><a href='" + reserva.getComoReservaVideoConferencia().getLinkConferencia() + "' target='cndConferencia'> " + reserva.getComoReservaVideoConferencia().getLinkConferencia() + " </a></h1></center>";

                if (!UtilCRCStringValidador.isNuloOuEmbranco(reserva.getAtendidoResponsavel().getContatoClienteVinculado().getEmail())) {
                    try {
                        notificacao = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), reserva.getAtendidoResponsavel().getContatoClienteVinculado(), "O Link para sua reunião está pronto", conteudoemail);
                    } catch (ErroEnvioEmail ex) {
                        addAviso("Falha enviando email" + ex.getMensagemUsuario());
                    }

                }
                if (!notificacao) {
                    throw new ErroRegraDeNegocio(reserva.getAtendidoResponsavel().getContatoClienteVinculado() + " está incontactavel por aqui, houve falha tentando enviar e-mail e sms");
                }
                setProximoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SUBPASTA_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema pessoaConverter(SubPastaPrivada pSubpasta) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSubpasta), pSubpasta) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                SubPastaPrivada subPasta = atualizarEntidade(pSubpasta);
                if (pSubpasta instanceof SubPastaEquipe) {
                    CategoriaArquivoEquipe categoria = (CategoriaArquivoEquipe) subPasta.getCPinst("diretorioProximo").getValor();
                    setUrlDestinoSucesso(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_EQUIPE, subPasta, subPasta.getPessoa(), categoria));

                } else {
                    setUrlDestinoSucesso(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_CLIENTE, pSubpasta, pSubpasta.getPessoa(), subPasta.getComoSubPastaEquipe().getDiretorioProximo()));
                }

            }
        }.getResposta();
    }

}
