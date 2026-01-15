package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm;
import br.org.coletivoJava.fw.erp.implementacao.chat.ChatMatrixOrgimpl;
import br.org.coletivojava.fw.utils.agendador.UtilSBAgendadorTarefas;
import com.google.common.collect.Lists;
import com.sun.mail.imap.IMAPMessage;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoBloqueio.FabTipoBloqueio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCRMEmailRecepcao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.FabAtividadeCRMAutoexecucao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmail.ArquivoAnexadoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmailEmConteudo.ArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.ControleEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.UtilSBCrmEmailModel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.UtilModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.util.UtilGeradorDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.FabAcaoCrmAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.InfoAcaoCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento.atividadeConcluir;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento.prospectoAtualizarDadosDinamicos;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.marketing.Util.UtilCRCEmailImap;
import com.super_bits.marketing.Util.imapMail.ImapDoClienteRecebido;
import com.super_bits.marketing.Util.imapMail.PacoteNovosEmailsImapCliente;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.RespostaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmailObjetos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsExtrator;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.persistence.EntityManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.CPCaixaPostal;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.superBits.utilitario.editorArquivos.util.UtilSBEditorArquivosConversor;

/**
 *
 * @author salvio
 */
public class ModuloCRMEmail extends ControllerAbstratoSBPersistencia {

    private final static ItfErpCrm servicoCRM = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto();

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO)
    public static ItfRespostaAcaoDoSistema atividadeEmailAplicarModelo(AtividadeCRM pAtividade) {
        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pAtividade)) {
            @Override
            public void regraDeNegocio() {

                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                EnvioEmailAtividade email = (EnvioEmailAtividade) atividade.getEmailVinculado();
                for (DocumentoAtividadeCRM doc : atividade.getDocumentosGerados()) {
                    email.adicionarArquivoAnexado(doc);
                }
                UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class,
                        SBCore.getUsuarioLogado().getId(), getEm());
                UtilSBCrmEmailModel.aplicarModelov2(email,
                        usuario);
                atualizarEntidadeConfigRetorno(email);
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO_EMAIL_DE_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema atividadeAplicarModeloEmail(final AtividadeCRM pAtividade) {

        boolean aplicarModeloAgora = (boolean) new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {
                AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEm());
                if (!atividade.getStatusAtividade().equals(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro())
                        && (!atividade.getStatusAtividade().equals(FabStatusAtividade.CANCELADA.getRegistro()))) {
                    FabTipoBloqueio tipoBloqueio = (FabTipoBloqueio) atividade.getCPinst(CPAtividadeCRM.tipobloqueio).getValor();
                    if (tipoBloqueio == null) {
                        return false;
                    }
                    if (tipoBloqueio.equals(FabTipoBloqueio.PRECISA_ENVIAR_EMAIL)) {
                        if (atividade.getEmailVinculado() == null) {

                            return true;
                        }
                    }
                }
                return false;
            }
        }.getResultado();

        if (aplicarModeloAgora) {
            return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(AtividadeCRM.class
            ), pAtividade) {
                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {
                    AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(pAtividade, getEm());

                    EnvioEmailAtividade novoEmail = new EnvioEmailAtividade();
                    novoEmail.prepararNovoObjeto(atividade);
                    novoEmail = atualizarEntidade(novoEmail, false);
                    novoEmail = UtilSBPersistencia.loadEntidade(novoEmail, getEm());
                    if (novoEmail == null) {
                        addErro("Erro cadastrando novo e-mail da atividade");
                        return;
                    }
                    try {
                        for (DocumentoAtividadeCRM doc : atividade.getDocumentosGerados()) {

                            novoEmail.adicionarArquivoAnexado(doc);

                        }
                    } catch (Throwable t) {
                        addErro("Erro viculando arquivo anexado ao documento gerado");
                        return;
                    }
                    novoEmail = atualizarEntidade(novoEmail, false);
                    try {
                        UtilSBCrmEmailModel.aplicarModelov2(novoEmail, atividade.getUsuarioInsersao());

                    } catch (Throwable t) {
                        addAlerta("Houve um erro aplicando o modelo de e-mail");
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro aplicando modelo de email", t);
                    }

                    atividade.setEmailVinculado(novoEmail);
                    atividade = atualizarEntidade(atividade, false);
                    setRetorno(atividade);

                }
            }.getResposta();
        }
        return getNovaResposta(AtividadeCRM.class
        ).setRetorno(pAtividade);

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CANCELAR_ATIVIDADE_ANTERIOR)
    public static ItfRespostaAcaoDoSistema atividadecancelaAtividadeAnteriorEmAberto(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

                if (isSucesso()) {
                    atividadeAplicarModeloEmail((AtividadeCRM) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                if (!atividadeAtualizada.getStatusAtividade().equals(FabStatusAtividade.EM_ANDAMENTO.getRegistro())) {

                    throw new ErroRegraDeNegocio("O Status da atividade já não se encontra em andamento");
                }
                // AtividadeCRM atividadeAnterior = atividadeAtualizada.getAtividadeMesmoTipoAbertaUsuarioLogado();
                //  if (atividadeAnterior == null) {
                //      addErro("Nenhuma Atividade Aberta foi encontrada para ser sobrescrita");
                //      return;
                //  }

                //atividadeAtualizada.setSobrescreverAcaoAtual(true);
                ItfResposta criacaoNovaAtividade = iniciarAtendimento(atividadeAtualizada.getTipoAtividade(), atividadeAtualizada.getProspectoEmpresa(), true);
                if (!criacaoNovaAtividade.isSucesso()) {
                    criacaoNovaAtividade.dispararMensagens();
                    throw new ErroRegraDeNegocio("Falha criando atividade");
                }
                atividadeAtualizada.setStatusAtividade(FabStatusAtividade.CANCELADA.getRegistro());
                atualizarEntidade(atividadeAtualizada, false);

                setRetorno(criacaoNovaAtividade.getRetorno());
                if (isSucesso()) {
                    addAviso("A atividade [" + atividadeAtualizada.getAnotacoes() + "], do dia " + atividadeAtualizada.getDataHoraInicioAtividade() + " foi Cancelada");
                }

            }

        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONTINUAR_ANTERIOR_EXCLUINDO_ESTA)
    public static ItfRespostaAcaoDoSistema atividadeExcluiEstaAtividadeERetomaAAnterior(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pAtividade)) {

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

                if (isSucesso()) {
                    atividadeAplicarModeloEmail((AtividadeCRM) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() {

                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());
                setRetorno(atividadeAtualizada);
                //  AtividadeCRM atividadeAnterior = atividadeAtualizada.getAtividadeMesmoTipoAbertaUsuarioLogado();
                //  if (atividadeAnterior == null) {
                //      addErro("Nenhuma Atividade Aberta foi encontrar para ser sobrescrita");
                //      return;
                // }
                //for (EnvioEmailAtividade email : atividadeAtualizada.getEmailsVinculados()) {
                //    getEmResposta().remove(email);
                //}
                //getEmResposta().remove(atividadeAtualizada);
                //setRetorno(atividadeAnterior);
                if (isSucesso()) {
                    addAviso("Continuando atividade  " + atividadeAtualizada.getNomeAtividade() + " do dia " + atividadeAtualizada.getDataHoraInicioAtividade());
                }

            }

        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS)
    public static ItfRespostaAcaoDoSistema atividadeCRMComplementarSalvarDadosDinamicos(final AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    atividadeAplicarModeloEmail(pAtividade);
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ItfResposta dadosExistentesUpdate = prospectoAtualizarDadosDinamicos(pAtividade.getProspectoEmpresa());

                Pessoa prospAtualizado;
                if (dadosExistentesUpdate.isSucesso()) {
                    prospAtualizado = (Pessoa) dadosExistentesUpdate.getRetorno();
                } else {
                    addAlerta("Falha atualizando dados existentes do prospecto");
                    prospAtualizado = UtilSBPersistencia.loadEntidade(pAtividade.getProspectoEmpresa(), getEm());
                }
                //ProspectoEmpresa prospAtualizado = (PessoaJuridica) prospectoAtualizarDadosDinamicos(pAtividade.getProspectoEmpresa()).getRetorno();

                // Definindo usuário que criou ação, como responsável
                UsuarioCRM usuarioLogado = UtilModulosCRM.getUsuarioCRMLogado(getEmResposta());
                prospAtualizado = UtilSBPersistencia.loadEntidade(prospAtualizado, getEmResposta());
                if (!prospAtualizado.getUsuariosResponsaveis().contains(usuarioLogado)) {
                    prospAtualizado.getUsuariosResponsaveis().add(usuarioLogado);
                }
                //Salvando Prospecto
                prospAtualizado = (Pessoa) UtilSBPersistencia.mergeRegistro(prospAtualizado, getEmResposta());

                // Salvando Dados Coletados
                pAtividade.ajustarColeta();
                List<DadoCRM> coletadosPersistidos = new ArrayList<>();
                for (DadoCRM pdado : pAtividade.getDadosColetados()) {

                    pdado.setProspectoEmpresa(prospAtualizado);
                    pdado.setAtividadeCRM(pAtividade);

                    DadoCRM dadoPersistido = (DadoCRM) atualizarEntidade(pdado, false);
                    if (pdado.getCampoInstanciado().isUmCampoArquivoEntidade()) {
                        if (pdado.getCampoInstanciado().getComoArquivoDeEntidade().getIntputTemporario() != null) {

                            SBCore.getCentralDeArquivos().salvarArquivo(
                                    dadoPersistido,
                                    pdado.getCampoInstanciado().getComoArquivoDeEntidade().getIntputTemporario(),
                                    pdado.getCampoInstanciado().getNomeCamponaClasse(), pdado.getCampoInstanciado().getValor().toString()
                            );
                        }
                    } else {
                        if (!UtilCRCStringValidador.isNuloOuEmbranco(dadoPersistido.getTipoDadoCRM().getCampoProspectoCorrespondente())) {
                            try {
                                prospAtualizado.getCampoInstanciadoByNomeOuAnotacao(dadoPersistido.getTipoDadoCRM().getCampoProspectoCorrespondente()).setValor(dadoPersistido.getCampoInstanciado().getValor());
                            } catch (Throwable t) {
                                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro vinculando dado dinamico a propriedade do prospecto", t);
                            }

                        }
                    }

                    if (dadoPersistido == null) {
                        addAlerta("O dado" + pdado.getNome() + " Não pode ser Salvo");
                    } else {
                        coletadosPersistidos.add(dadoPersistido);
                    }

                }

                pAtividade.setDadosColetados(coletadosPersistidos);
                pAtividade.setProspectoEmpresa(prospAtualizado);

                prospAtualizado.setDadosColetados(coletadosPersistidos);
                prospAtualizado = (Pessoa) UtilSBPersistencia.mergeRegistro(prospAtualizado, getEmResposta());
                List<DadoCRM> naoColetadosPersistidos = new ArrayList<>();

                // Caso seja uma atividade Geradora com dados que deveriam ser coletados, salva quais foram os dados que não foram coletados
                boolean temdadoNaoColetado = false;
                if (pAtividade.getTipoAtividade().getRelacionamentoGerado() != null) {

                    for (DadoCRM pdado : pAtividade.getDadosNaoColetados()) {
                        pdado.setProspectoEmpresa(null);
                        pdado.setAtividadeCRM(pAtividade);
                        DadoCRM dadoPersistido = (DadoCRM) UtilSBPersistencia.mergeRegistro(pdado, getEmResposta());
                        if (dadoPersistido == null) {
                            addAlerta("O dado" + pdado.getNome() + " Não pode ser Salvo");
                            temdadoNaoColetado = true;
                        } else {
                            naoColetadosPersistidos.add(dadoPersistido);
                        }
                    }
                    pAtividade.setDadosNaoColetados(naoColetadosPersistidos);
                }

                FabTipoBloqueio tipobloqueio = (FabTipoBloqueio) pAtividade.getCPinst(CPAtividadeCRM.tipobloqueio).getValor();
                if (tipobloqueio != null) {
                    addAlerta("Faltam dados obrigatórios:" + tipobloqueio.gerarMensagemPorTipo(pAtividade));
                }

                if (isSucesso()) {
                    setRetorno(pAtividade);
                }
                if (prospAtualizado == null) {
                    addErro("Aconteceu um erro ao tentar salvar o Prospecto!");

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_INICIAR_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema iniciarAtendimento(TipoAtividadeCRM ptipoAtividade, Pessoa pProspecto, boolean sobrescrever) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pProspecto), pProspecto) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

                if (isSucesso()) {

                    EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                    AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade((AtividadeCRM) getRetorno(), em);
                    atividadeAplicarModeloEmail(atividadeAtualizada);
                    if (atividadeAtualizada.getTipoAtividade().isExecucaoDiretaSemRelatorio()) {
                        ItfRespostaAcaoDoSistema resp = atividadeConcluir(atividadeAtualizada);
                        setProximoFormulario(resp.getAcaoProximoFormulario());
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                TipoAtividadeCRM tipoAtividadeAtualizado = UtilSBPersistencia.loadEntidade(ptipoAtividade, getEmResposta());
                Pessoa prospectoAtualizado = UtilSBPersistencia.loadEntidade(pProspecto, getEmResposta());
                AtividadeCRM novaAtividade = new AtividadeCRM(tipoAtividadeAtualizado, prospectoAtualizado);

                if (!sobrescrever) {
                    Optional<AtividadeCRM> atividadeEncontrada = prospectoAtualizado.getAtividadesEmAndamento().stream().filter(atividade
                            -> (atividade.getTipoAtividade().equals(ptipoAtividade) && atividade.getUsuarioAtividade() != null && atividade.getUsuarioAtividade().equals(SBCore.getUsuarioLogado()))).findFirst();
                    if (atividadeEncontrada.isPresent()) {
                        setRetorno(atividadeEncontrada.get());
                        throw new ErroRegraDeNegocio("Já existe uma atividade deste tipo em andamento");

                    } else {
                        novaAtividade.setSobrescreverAcaoAtual(true);
                    }

                } else {

                }
                if (!tipoAtividadeAtualizado.getUsuariosAutorizados().isEmpty()) {
                    if (!tipoAtividadeAtualizado.getUsuariosAutorizados().contains(UtilModulosCRM.getUsuarioCRMLogado(getEm()))) {
                        throw new ErroRegraDeNegocio("O Usuário não possui permissão para " + tipoAtividadeAtualizado.getNomeInicioAtivida());

                    }
                }
                novaAtividade.setDataHoraRealizacao(new Date());
                novaAtividade.setStatusAtividade(FabStatusAtividade.EM_ANDAMENTO.getRegistro());
                novaAtividade.setTipoAtividade(tipoAtividadeAtualizado);
                novaAtividade.setUsuarioAtividade((UsuarioSB) SBCore.getUsuarioLogado());

                novaAtividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.nomeatividade).getValor();
                novaAtividade.setDadosNaoColetados(new ArrayList<>());
                novaAtividade = (AtividadeCRM) atualizarEntidade(novaAtividade, false);

                setRetornoDisparaERetorna(novaAtividade);

            }
        };

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.ATIVIDADE_CTR_GERAR_DOCUMENTOS)
    public static ItfRespostaAcaoDoSistema gerarDocumentos(final AtividadeCRM pAtividade, UsuarioCRM pUsuario) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(AtividadeCRM.class
        ), pAtividade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                if (isSucesso()) {

                    atividadeAplicarModeloEmail(pAtividade);

                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                AtividadeCRM atividadeGenrenciada = (AtividadeCRM) UtilSBPersistencia.loadEntidade(pAtividade, getEmResposta());

                atividadeGenrenciada = atualizarEntidade(atividadeGenrenciada, false);
                //  ManterConexao manter = new ManterConexao(atividadeGenrenciada, getEm());
                //  manter.start();
                for (ModeloDocumentoTipoAtividade modeloDocumento : atividadeGenrenciada.getTipoAtividade().getModelosDocumentoVinculados()) {
                    //String extencao = UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivoSemPonto(modeloDocumento.getCPinst("documento").getComoArquivoDeEntidade().getLinkAbrirArquivo());
                    String diretorioArquivoTempUsuario = SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto() + "/" + atividadeGenrenciada.getUsuarioAtividade().getEmail().hashCode() + "/arqTemp.docx";
                    if (modeloDocumento != null) {
                        System.out.println("Gerando documento baseado em" + modeloDocumento.getNome());
                    }
                    String arqModificado = UtilGeradorDocumentoCRM.gerarDocumentoPastaTemporaria(atividadeGenrenciada.getProspectoEmpresa(), modeloDocumento, diretorioArquivoTempUsuario);
                    if (!new File(arqModificado).exists()) {
                        throw new ErroRegraDeNegocio("Falha editando o arquivo docx, cheque a integridade do arquivo modelo");
                    }
                    DocumentoAtividadeCRM novoDocumento = new DocumentoAtividadeCRM();
                    novoDocumento.prepararNovoObjeto(atividadeGenrenciada, modeloDocumento);

                    String arquivoPdf = arqModificado.concat(".pdf");
                    boolean conversaoPDF = UtilSBEditorArquivosConversor.converterWordEmPDF(arqModificado, arquivoPdf);
                    if (!conversaoPDF) {
                        throw new ErroRegraDeNegocio("Falha convertendo documento em pdf, cheque a integridade do arquivo modelo");
                    }

                    try {
                        String nomeDocumento = UtilCRCStringFiltros.gerarUrlAmigavel(modeloDocumento.getNome());
                        int pontoEmNomeDocumento = nomeDocumento.lastIndexOf(nomeDocumento);
                        if (pontoEmNomeDocumento != -1) {
                            nomeDocumento = UtilCRCStringNomeArquivosEDiretorios.getNomeDoArquivoSemExtencao(nomeDocumento);
                        }
                        nomeDocumento = UtilCRCStringFiltros.getNomeReduzido(nomeDocumento);
                        nomeDocumento = nomeDocumento;
                        String dataTextoDoc = new SimpleDateFormat("dd'do'MM'de'yy").format(new Date());
                        nomeDocumento = nomeDocumento + "_" + dataTextoDoc + "_CA" + atividadeGenrenciada.getId() + ".pdf";
                        novoDocumento.setNome("GeradoPorAtv" + atividadeGenrenciada.getId() + "_" + nomeDocumento);
                        System.out.println("Vinculando " + arquivoPdf + " a entidade" + novoDocumento);
                        novoDocumento.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().uploadArquivo(
                                nomeDocumento,
                                FileUtils.readFileToByteArray(new File(arquivoPdf)));
                        novoDocumento.setDataHoraCriacao(new Date());
                        novoDocumento.setUsuarioCriou(atividadeGenrenciada.getUsuarioInsersao());

                        atualizarEntidade(novoDocumento, false);

                        try {
                            new File(arquivoPdf).delete();
                            new File(arqModificado).delete();
                        } catch (Throwable t) {
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro excluindo os arquivos " + arqModificado + " e " + arquivoPdf, t);
                        }
                    } catch (Throwable t) {
                        addErro("Erro gerando arquivo temporário");
                    }
                }
                //     manter.parar();
            }
        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_SALVAR_EMAIL_RECEBIDO)
    public synchronized static ItfRespostaAcaoDoSistema salvarEmailRecebido(IMAPMessage pMensagem, CaixaPostal caixaPostal) {
        ItfRespostaAcaoDoSistema resposta = getNovaResposta(UsuarioCRM.class);
        return new RespostaComGestaoEMRegraDeNegocioPadrao(resposta, caixaPostal) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                EmailRecebido emailatualizado = null;
                try {

                    String codigoemail = UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(pMensagem);
                    ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(EmailRecebido.class, getEm());
                    consulta.addcondicaoCampoIgualA("codigoEmailServidor", codigoemail);
                    EmailRecebido email = (EmailRecebido) consulta.getPrimeiroRegistro();
                    if (email != null) {
                        setRetorno(email);
                        return;
                    }
                    ConsultaDinamicaDeEntidade consultaControle = new ConsultaDinamicaDeEntidade(ControleEmailRecebido.class, getEm());
                    consultaControle.addcondicaoCampoIgualA("codigo", codigoemail);
                    ControleEmailRecebido controle = (ControleEmailRecebido) consultaControle.getPrimeiroRegistro();
                    IMAPMessage message = (IMAPMessage) pMensagem;
                    String remetente = UtilCRCStringsExtrator.extrairEmail(message.getFrom()[0].toString());
                    if (controle == null) {

                        ControleEmailRecebido ct = new ControleEmailRecebido();
                        ct.setRemetente(remetente);
                        ct.setCodigo(codigoemail);
                        atualizarEntidade(ct);
                    }

                    //message.getContentType();
                    List<EmailRecebido> mailarqmazenado = (List) UtilSBPersistencia.getListaRegistrosByHQL("from EmailRecebido where codigoEmailServidor= ?0 ", 1, UtilSBPersistencia.getEntyManagerPadraoNovo(), message.getMessageID());
                    if (!mailarqmazenado.isEmpty()) {
                        throw new ErroRegraDeNegocio("O e-mail já foi armazenado anteriormente");
                    }

                    EmailRecebido recebido = new EmailRecebido(message);
                    recebido.setCaixaPostal(caixaPostal);
                    //recebido.setConsideradoSpan(caixaPos);
                    recebido.setCodigoEmailServidor(UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(message));
                    recebido.setCaixaPostal(caixaPostal);
                    recebido.setTexto(UtilCRCEmailObjetos.lerConteudo(message));
                    recebido.setDataHoraRecebimentoServerMail(message.getReceivedDate());
                    recebido.setDataHoraEnvio(message.getSentDate());
                    recebido.setRemetente(remetente);
                    recebido.setAssunto(message.getSubject());

                    List<ContatoProspecto> contatosDePessoas
                            = UtilSBPersistencia.getListaRegistrosByHQL("from ContatoProspecto where email = ?0 and tipoContato='" + ContatoProspecto.class.getSimpleName() + "'", 1, getEm(), remetente);
                    if (!contatosDePessoas.isEmpty()) {
                        recebido.setContato(contatosDePessoas.get(0));
                        recebido.setProspecto(contatosDePessoas.get(0).getProspecto());
                    }
                    recebido.setCategoriaEmailRecebido(FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro());

                    try {
                        message.getContentType();
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha obetando tipo de mensagem" + t.getMessage(), t);
                        throw new ErroRegraDeNegocio("Falha lendo mensagem " + codigoemail);
                    }

                    if (recebido.getCategoriaEmailRecebido().equals(FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro())) {
                        recebido.setContato(loadEntidade(recebido.getContato()));
                        if (recebido.getProspecto() != null) {
                            recebido.setProspecto(loadEntidade(recebido.getProspecto()));
                        }
                    }
                    recebido.getCamposInstanciados().forEach(cpInst
                            -> {
                        if (cpInst != null && !cpInst.isCampoNaoInstanciado()) {
                            cpInst.getValor();
                        }
                    }
                    );
                    emailatualizado = atualizarEntidade(recebido, false);
                    setRetorno(emailatualizado);
                } catch (MessagingException ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando mensagem" + ex.getClass().getSimpleName() + ex.getMessage(), ex);
                    throw new ErroRegraDeNegocio("Falha lendo mensagem " + "Erro processando mensagem" + ex.getClass().getSimpleName() + ex.getMessage());
                } catch (IOException ex) {
                    throw new ErroRegraDeNegocio("Falha lendo mensagem " + "Erro processando mensagem" + ex.getMessage());
                }

                if (isSucesso()) {
                    try {
                        int i = 1;
                        List<UtilCRCEmailObjetos.ArquivoAnexoEmail> anexosEncontrados = new ArrayList<>();
                        try {
                            anexosEncontrados = UtilCRCEmailObjetos.lerAnexos(pMensagem);

                        } catch (Throwable t) {
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando anexos do e-mail" + pMensagem.getSubject(), t);
                        }
                        for (UtilCRCEmailObjetos.ArquivoAnexoEmail streamDoArquivo : anexosEncontrados) {
                            ArquivoAnexadoEmailRecebido arquivo = null;
                            if (streamDoArquivo.isContemCid()) {
                                arquivo = new ArquivoAnexadoEmailEmConteudo();
                                ((ArquivoAnexadoEmailEmConteudo) arquivo).setCid(streamDoArquivo.getCid());
                            } else {
                                arquivo = new ArquivoAnexadoEmailRecebido();
                            }

                            arquivo.setNome(UtilCRCStringFiltros.gerarUrlAmigavel(streamDoArquivo.getNome()));
                            arquivo.setProspecto(emailatualizado.getProspecto());

                            arquivo.setEmailCrm(emailatualizado);
                            String nomeArquivo = arquivo.getNome();
                            if (UtilCRCStringValidador.isNuloOuEmbranco(nomeArquivo)) {
                                nomeArquivo = "anexoEmail" + emailatualizado.getId() + "-" + i;
                            }
                            arquivo.setArquivo(nomeArquivo);

                            arquivo.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().uploadArquivo(nomeArquivo, IOUtils.toByteArray(streamDoArquivo.getArquivo()));
                            atualizarEntidade(arquivo);
                            i++;
                        }
                    } catch (Throwable t) {
                        try {
                            addErro("Erro processando anexos do e-mail");
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando anexos do e-mail" + pMensagem.getSubject(), t);

                        } catch (MessagingException ex) {
                            Logger.getLogger(ModuloCRMAplicacao.class.getName()).log(Level.SEVERE, null, ex);
                            throw new ErroRegraDeNegocio("Falha lendo mensagem " + "Erro processando mensagem" + ex.getMessage());
                        }
                    }

                }

            }

        };

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TESTAR_CAIXA_DE_ENTRADA)
    public static ItfRespostaAcaoDoSistema
            emailTestar(UsuarioCRM pUsuario) {

        ItfRespostaAcaoDoSistema resposta = getNovaResposta(UsuarioCRM.class
        );

        return new RespostaComGestaoEMRegraDeNegocioPadrao(resposta, pUsuario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                try {

                    String host = pUsuario.getCaixaPostalPrincipal().getEnderecoServidor();
                    String user = pUsuario.getCaixaPostalPrincipal().getUsuarioRecepcao();
                    String password;

                    if (pUsuario.getCaixaPostalPrincipal() == null) {
                        throw new ErroRegraDeNegocio("a senha da caixa postal  não foi encontrada");
                    }
                    if (pUsuario.getCaixaPostalPrincipal().getSenhaRecepcao() == null) {

                        UsuarioCRM usuarioAtualizado = UtilSBPersistencia.loadEntidade(pUsuario, getEm());
                        if (usuarioAtualizado.getCaixaPostalPrincipal() != null) {
                            password = usuarioAtualizado.getCaixaPostalPrincipal().getSenhaRecepcao();
                        } else {
                            throw new ErroRegraDeNegocio("A senha da caixa de entrada não foi encontrada");
                        }
                    } else {
                        password = pUsuario.getCaixaPostalPrincipal().getSenhaRecepcao();

                    }

                    if (host == null || host.isEmpty() || host.length() < 3) {
                        throw new ErroRegraDeNegocio("Configure o endereço do servidor");
                    }
                    //create properties field
                    System.out.println("Conectando com sevidor, salvando email em " + host);

                    //create properties field
                    Properties properties = new Properties();
                    properties.setProperty("mail.store.protocol", "imaps");
                    properties.setProperty("mail.imap.port", "993");
                    properties.setProperty("mail.imap.socketFactory.class", "com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.crmAdmin.AlwaysTrustSSLContextFactory");
                    properties.put("mail.imap.starttls.enable", "true");
                    properties.put("mail.imap.ssl.checkserveridentity", "false");

                    Session emailSession = Session.getDefaultInstance(properties);
                    //create the POP3 store object and connect with the pop server

                    //  Store store = emailSession.getStore("pop3s");
                    Store store = emailSession.getStore();

                    //create the POP3 store object and connect with the pop server
                    //  Store store = emailSession.getStore("pop3s");
                    store.connect(host, user, password);
                    //create the folder object and open it
                    Folder emailFolder = store.getFolder("INBOX");

                    for (Folder ff : store.getSharedNamespaces()) {
                        System.out.println(ff.getName());
                    }
                    for (Folder ff : store.getPersonalNamespaces()) {
                        System.out.println(ff.getName());
                    }
                    emailFolder.open(Folder.READ_WRITE);
                    store.close();

                    CaixaPostal caixa = pUsuario.getCaixaPostalPrincipal();
                    caixa.setAtivo(true);
                    UtilSBPersistencia.mergeRegistro(caixa);

                } catch (MessagingException ex) {

                    if (ex instanceof AuthenticationFailedException) {
                        CaixaPostal caixa = pUsuario.getCaixaPostalPrincipal();
                        caixa.setAtivo(false);
                        UtilSBPersistencia.mergeRegistro(caixa);

                        //   UtilSBPersistencia.executaSQL("update CaixaPostal set ativo = false where id = " + caixa.getId());
                    }
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha recebendo email de " + pUsuario, ex);
                    throw new ErroRegraDeNegocio("Falha acessando caixa de entrada" + ex.getMessage());
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_RECEBER_EMAIL)
    public synchronized static ItfRespostaAcaoDoSistema
            receberEmail(UsuarioCRM pUsuarioCaixaPostal
            ) {

        ItfRespostaAcaoDoSistema resposta = getNovaResposta(UsuarioCRM.class
        );
        Store pastaImap = null;
        List<Folder> pastasImap = null;
        Folder[] todasPastas = null;

        try {
            try {
                pastaImap = UtilCRCEmailImap.getPastaImap(pUsuarioCaixaPostal);

                todasPastas = pastaImap.getDefaultFolder().list("*");
                pastasImap = Lists.newArrayList(todasPastas);
            } catch (Throwable ex) {
                resposta.addErro("Falha conectando com servidor" + ex.getMessage());
                return resposta;
            }

            //  Store store = emailSession.getStore("pop3s");
            //create the folder object and open it
            Optional<Folder> pastaSpan = pastasImap.stream().filter(fd -> fd.getName().toLowerCase().contains(".span")).findFirst();
            Optional<Folder> pastaCaixaDeEntrada = pastasImap.stream().filter(fd -> fd.getName().toLowerCase().endsWith("inbox")).findFirst();
            Optional<Folder> pastaItemEnviado = pastasImap.stream().filter(fd -> fd.getName().toLowerCase().endsWith("sent")).findFirst();
            List<Optional<Folder>> pastasRecebidos = Lists.newArrayList(pastaSpan, pastaCaixaDeEntrada);
            final List<ImapDoClienteRecebido> mensagensRecebidas = new ArrayList<>();
            List<PacoteNovosEmailsImapCliente> pacotesNovasMensagens = new ArrayList<>();

            for (Optional<Folder> pastaRecebidos : pastasRecebidos) {
                if (!pastaRecebidos.isPresent()) {
                    continue;
                }
                try {
                    boolean umSpan = pastaRecebidos.get().getName().toLowerCase().contains(".span");
                    PacoteNovosEmailsImapCliente pacote = UtilCRCEmailImap.getNovosEmails(pUsuarioCaixaPostal.getCaixaPostalPrincipal().getUsuarioRecepcao(), pastaRecebidos.get(), umSpan);
                    if (pacote == null) {
                        continue;
                    }
                    pacotesNovasMensagens.add(pacote);
                    if (pacotesNovasMensagens == null) {
                        continue;
                    }
                    mensagensRecebidas.addAll(pacote.getNovasMensagens());
                } catch (MessagingException ex) {
                    return resposta.addErro("Falha recebendo email" + ex.getMessage());
                } catch (Throwable ex) {
                    return resposta.addErro("Falha recebendo email" + ex.getMessage());
                }
            }
            if (mensagensRecebidas.isEmpty()) {
                return resposta.addAlerta("Sem novos e-mails");
            }
            ItfRespostaAcaoDoSistema respostaCriacaoEamils = new RespostaComGestaoEMRegraDeNegocioPadrao(resposta, pUsuarioCaixaPostal) {
                @Override
                public void executarAcoesFinais() throws ErroEmBancoDeDados {
                    super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

                    UtilSBAgendadorTarefas.agendarTarefa(FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_RECEBER_EMAIL, UtilCRCDataHora.incrementaMinutos(new Date(), 3), pUsuarioCaixaPostal);
                }

                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {

                    UsuarioCRM usuarioAtualizado = loadEntidade(pUsuarioCaixaPostal);
                    for (ImapDoClienteRecebido mensagemRecebida : mensagensRecebidas) {
                        ItfResposta resp = salvarEmailRecebido(mensagemRecebida.getMensagem(), usuarioAtualizado.getCaixaPostalPrincipal());
                        if (!resp.isSucesso()) {
                            try {
                                throw new ErroRegraDeNegocio("Falha salvando mensagem " + mensagemRecebida.getMensagem().getSubject());
                            } catch (MessagingException ex) {
                                throw new ErroRegraDeNegocio("Falha salvando mensagem ");
                            }
                        }

                    }

                }
            }.getResposta();

            if (!respostaCriacaoEamils.isSucesso()) {
                return respostaCriacaoEamils;
            }

            ItfRespostaAcaoDoSistema respostaAtividades = new RespostaComGestaoEMRegraDeNegocioPadrao(resposta, pUsuarioCaixaPostal) {
                @Override
                public void executarAcoesFinais() throws ErroEmBancoDeDados {
                    super.executarAcoesFinais();
                    if (isSucesso()) {
                        for (ImapDoClienteRecebido mensagem : mensagensRecebidas) {
                            List<String> destinatarios = new ArrayList<>();

                            Date dataRecebimento;
                            try {
                                dataRecebimento = mensagem.getMensagem().getReceivedDate();
                                long diferenca = UtilCRCDataHora.intervaloTempoMinutos(dataRecebimento, new Date());
                                if (diferenca >= 500) {
                                    continue;

                                }
                            } catch (MessagingException ex) {
                                Logger.getLogger(ModuloCRMEmail.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            destinatarios = UtilCRCEmailObjetos.getListaDestinatarios(mensagem.getMensagem());

                            for (String email : destinatarios) {
                                UsuarioCRM usuario = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(email);

                                UsuarioCrmCliente usuarioCliente = mensagem.getUsuarioCliente();
                                if (usuario.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro())
                                        || usuario.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                                    try {
                                        ChatMatrixOrgimpl chat = (ChatMatrixOrgimpl) ERPChat.MATRIX_ORG.getImplementacaoDoContexto();
                                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_VER_ATIVIDADES, usuarioCliente.getRepresentanteLegal());

                                        chat.enviarDirect(usuario.getCodigoMatrix(), usuarioCliente.getNome() + " de " + usuarioCliente.getRepresentanteLegal().getNome() + " te enviou email, assunto: " + mensagem.getMensagem().getSubject() + " acesse as atividade em " + url);
                                    } catch (Throwable t) {

                                    }
                                }

                            }

                        }
                    }
                }

                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {
                    for (ImapDoClienteRecebido mensagem : mensagensRecebidas) {
                        AtividadeCRMEmailRecepcao atividadeRecebido = new AtividadeCRMEmailRecepcao();
                        String codigoemail = UtilCRCEmailAvancado.getIdentificadorUnicoImapMessage(mensagem.getMensagem());
                        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(EmailRecebido.class,
                                getEm());
                        consulta.addcondicaoCampoIgualA("codigoEmailServidor", codigoemail);
                        EmailRecebido emailRecebido = (EmailRecebido) consulta.getPrimeiroRegistro();
                        if (emailRecebido == null) {
                            try {
                                throw new ErroRegraDeNegocio("email " + mensagem.getMensagem().getSubject() + " não cod" + codigoemail + "não encontrado");
                            } catch (MessagingException ex) {
                                throw new ErroRegraDeNegocio("Falha lendo assunto de atividade" + codigoemail);
                            }
                        }

                        atividadeRecebido.prepararNovoObjeto(loadEntidade(FabAtividadeCRMAutoexecucao.ATIVIDADE_RECEBER_EMAIL.getRegistro()), loadEntidade(emailRecebido.getProspecto()));
                        atividadeRecebido.setAnotacoes(emailRecebido.getAssunto());
                        atividadeRecebido.setStatusAtividade(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro());
                        atividadeRecebido.setUsuarioAtividade(loadEntidade(mensagem.getUsuarioCliente()));
                        atividadeRecebido.setEmailVinculado(emailRecebido);
                        atividadeRecebido.setDataHoraRealizacao(emailRecebido.getDataHoraEnvio());
                        atividadeRecebido.setDataHoraInicioAtividade(emailRecebido.getDataHoraEnvio());
                        atividadeRecebido.setCodigoImap(emailRecebido.getCodigoEmailServidor());
                        atividadeRecebido.setNomeAtividade("Resposta de " + emailRecebido);

                        //UtilSBPersistencia.executaSQL(getEMResposta(), "insert into AtividadeCRM set ");
                        atividadeRecebido = atualizarEntidade(atividadeRecebido);
                        if (atividadeRecebido == null) {
                            throw new ErroRegraDeNegocio("Falha salvando atividade");
                        }
                        System.out.println(atividadeRecebido.getCodigoImap());
                    }
                    for (PacoteNovosEmailsImapCliente pct : pacotesNovasMensagens) {
                        UtilSBPersistencia.mergeRegistro(pct.getControleLeituraAtualizacao());
                        UsuarioCRM usuarioAtualizado = loadEntidade(pUsuarioCaixaPostal);
                        CaixaPostal caixaAtualizada = UtilSBPersistencia.loadEntidade(usuarioAtualizado.getCaixaPostalPrincipal(), getEMResposta());
                        CaixaPostalEstatisticas estatisticas = (CaixaPostalEstatisticas) caixaAtualizada.getCPinst(CPCaixaPostal.estatisticas).getValor();
                        CaixaPostalEstatisticas estatisticaAtualizada = atualizarEntidade(estatisticas);
                        caixaAtualizada.setEstatisticas(estatisticaAtualizada);

                    }

                }
            }.getResposta();
            return respostaAtividades;
        } finally {
            if (todasPastas != null) {
                for (Folder pastaImapAberta : todasPastas) {
                    try {
                        if (pastaImapAberta.isOpen()) {
                            pastaImapAberta.close(true);
                        }
                    } catch (MessagingException ex) {
                    }
                }

            }
        }

    }
}
