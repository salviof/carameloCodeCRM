/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmailRascunhoAutoSave;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import jersey.repackaged.com.google.common.collect.Lists;
import com.super_bits.editorImagem.util.UtilSBImagemEdicao;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroAcessandoCanalComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author desenvolvedor
 */
@RequestScoped
@Named
public class ServicosCRM implements Serializable {

    private TipoAtividadeCRM tipoAtividadeSelecionada;
    private Pessoa prospecto;
    @Inject
    private InfoWebApp webPaginas;
    private static List<ItfAcaoFormulario> acoesModal;
    private static List<ItfAcaoFormulario> acoesDeProspecto;
    private static final ItfAcaoFormulario ACAO_EDICAO_RAPIDA = FabAcaoCRMAtendimento.MODAL_CRM_FRM_EDICAO_RAPIDA.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_COMPARTILHAR_PROSPECTO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_COMPARTILHAR_PROSPECTO.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_ENVIAR_EMAIL_RAPIDO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_EMAIL_RAPIDO.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_ANEXO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_ANEXOS.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_CONTATOS = FabAcaoCRMAtendimento.MODAL_CRM_FRM_CONTATOS.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_AVANCADO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_OPCOES_AVANCADO.getRegistro().getComoFormularioEntidade();

    private static final ItfAcaoFormulario ACAO_HISTORICO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_EXIBIR_ATIVIDADES.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_FLUXO_ETAPA_ATUAL = FabAcaoCRMAtendimento.MODAL_CRM_FRM_FLUXO.getRegistro().getComoFormularioEntidade();
    private static final ItfAcaoFormulario ACAO_DADOS_DINAMICO = FabAcaoCRMAtendimento.MODAL_CRM_FRM_DADOS_DINAMICOS.getRegistro().getComoFormularioEntidade();

    private String idProspectoStr;
    private String idAreaCardAtualizado;

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessaoAtual;

    @Inject
    private EntityManager emPagina;

    @Inject
    private PgUtil paginaUtil;

    @PostConstruct
    public void inicioServicos() {
        if (acoesModal == null) {
            acoesDeProspecto = new ArrayList<>();
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_ALTERAR_RELACIONAMENTO.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_LISTAR_ORCAMENTOS_DA_PESSOA.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_PESSOA_JURIDICA.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS.getRegistro().getComoFormulario());

            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_SERVICOS.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_CHAT_EQUIPE.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_TAGS.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_VER_ATIVIDADES.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_VISUALIZAR.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_INTEGRACAO.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_CONVERTER_PESSOA_FISICO_JURIDICO.getRegistro().getComoFormulario());
            acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA.getRegistro().getComoFormulario());

            acoesModal = new ArrayList();
            acoesModal.add(ACAO_EDICAO_RAPIDA);
            acoesModal.add(ACAO_COMPARTILHAR_PROSPECTO);
            acoesModal.add(ACAO_HISTORICO);
            acoesModal.add(ACAO_FLUXO_ETAPA_ATUAL);
            acoesModal.add(ACAO_DADOS_DINAMICO);
            acoesModal.add(ACAO_ANEXO);
            acoesModal.add(ACAO_CONTATOS);
            acoesModal.add(ACAO_AVANCADO);
            acoesModal.add(ACAO_ENVIAR_EMAIL_RAPIDO);
        }

    }

    public List<ItfAcaoFormulario> getAcoesDeProspecto() {
        return acoesDeProspecto;
    }

    public TipoAtividadeCRM getTipoAtividadeSelecionada() {
        return tipoAtividadeSelecionada;
    }

    public void setTipoAtividadeSelecionada(TipoAtividadeCRM tipoAtividadeSelecionada) {
        this.tipoAtividadeSelecionada = tipoAtividadeSelecionada;
    }

    public ItfAcaoFormulario getAcaoContatos() {
        return ACAO_CONTATOS;
    }

    public void continuarAtividade(AtividadeCRM pAtividade) {
        try {
            String urlAtividade = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro(),
                    pAtividade.getTipoAtividade(), pAtividade.getProspectoEmpresa(), pAtividade);
            UtilSBWP_JSFTools.vaParaPagina(urlAtividade);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao iniciar ", t);
            SBCore.enviarMensagemUsuario("Não foi possível iniciar a atividade :(", FabMensagens.AVISO);
        }
    }

    public void iniciarAtividadeInicial(Pessoa pEmpresa) {
        try {
            if (tipoAtividadeSelecionada == null) {
                throw new UnsupportedOperationException("O tipo de atividade não foi enviado");
            }
            if (pEmpresa == null) {
                throw new UnsupportedOperationException("O prospecto não foi enviado");
            }
            String urlAtividade = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro(), tipoAtividadeSelecionada, pEmpresa);
            UtilSBWP_JSFTools.vaParaPagina(urlAtividade);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao iniciar ", t);
            SBCore.enviarMensagemUsuario("Não foi possível iniciar a atividade :(", FabMensagens.AVISO);
        }
    }

    public void enviarDocumentos(Pessoa pEmpresa) {
        try {
            if (tipoAtividadeSelecionada == null) {
                throw new UnsupportedOperationException("O tipo de atividade não foi enviado");
            }
            if (pEmpresa == null) {
                throw new UnsupportedOperationException("O prospecto não foi enviado");
            }
            String urlAtividade = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_MB.getRegistro(),
                    tipoAtividadeSelecionada, pEmpresa);
            UtilSBWP_JSFTools.vaParaPagina(urlAtividade);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao iniciar ", t);
            SBCore.enviarMensagemUsuario("Não foi possível iniciar a atividade :(", FabMensagens.AVISO);
        }
    }

    public void notificarResponsaveis(AtividadeCRM atv) {
        new ExecucaoComGestaoEntityManager() {
            @Override
            public void regraDeNegocio() {
                for (UsuarioCRM usuario : atv.getProspectoEmpresa().getUsuariosResponsaveis()) {

                    ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();
                    int notificados = 0;

                    if (!usuario.equals(SBCore.getUsuarioLogado())) {
                        if (usuario.getCodigoMatrix() != null && !UtilCRCStringValidador.isNuloOuEmbranco(usuario.getCodigoMatrix())) {
                            try {
                                if (servicoChat.enviarDirect(usuario.getCodigoMatrix(), "Oi, " + SBCore.getUsuarioLogado().getNome() + ", executou a atividade "
                                        + atv.getTipoAtividade().getNome() + " para o cliente  "
                                        + atv.getProspectoEmpresa().getNome()
                                        + "") != null) {
                                    ItfDialogo dialogo = SBCore.getServicoComunicacao()
                                            .gerarComunicacaoSistema_Usuario(FabTipoComunicacao.NOTIFICAR,
                                                    usuario,
                                                    "Olá, executei a atividade " + atv.getNomeAtividade()
                                                    + " para " + atv.getProspectoEmpresa().getNome());

                                    SBCore.getServicoComunicacao().dispararComunicacao(dialogo, ERPTipoCanalComunicacao.INTRANET_BLOQUEIO_TELA);

                                }
                            } catch (ErroConexaoServicoChat ex) {
                                Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ErroAcessandoCanalComunicacao ex) {
                                Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            notificados++;
                        } else {
                            ItfDialogo dialogo = SBCore.getServicoComunicacao().gerarComunicacaoSistema_Usuario(FabTipoComunicacao.NOTIFICAR, usuario, "Olá, executei a atividade " + atv.getNomeAtividade()
                                    + " para " + atv.getProspectoEmpresa().getNome());
                            try {
                                SBCore.getServicoComunicacao().dispararComunicacao(dialogo, ERPTipoCanalComunicacao.INTRANET_BLOQUEIO_TELA);
                            } catch (ErroAcessandoCanalComunicacao ex) {
                                Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }

                    if (notificados == 0) {

                        try {
                            servicoChat.enviarDirect(((UsuarioCRM) SBCore.getUsuarioLogado()).getCodigoMatrix(), "Oi, ninguem foi notificado sobre o " + atv.getTipoAtividade().getNome() + " para o cliente  "
                                    + atv.getProspectoEmpresa().getNome()
                                    + "");
                        } catch (ErroConexaoServicoChat ex) {
                            Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        notificados++;
                    }
                    if (notificados > 0) {
                        try {
                            servicoChat.enviarDirect(((UsuarioCRM) SBCore.getUsuarioLogado()).getCodigoMatrix(), "Oi, " + notificados + " colaboradores forão notificados sobre o " + atv.getTipoAtividade().getNome() + " para o cliente  "
                                    + atv.getProspectoEmpresa().getNome()
                                    + "");
                        } catch (ErroConexaoServicoChat ex) {
                            Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        notificados++;
                    }
                }
            }
        };
    }

    public List<ItfAcaoFormulario> getAcoesModal() {
        return acoesModal;
    }

    public void exibirModalProspectoGestaoContatos() {
        if (idProspectoStr != null) {
            prospecto = UtilSBPersistencia.getRegistroByID(Pessoa.class,
                    Long.valueOf(idProspectoStr), emPagina);
        }
        exibirModalProspectoSeResponsavel(prospecto, ACAO_COMPARTILHAR_PROSPECTO);
    }

    public void exibirModalProspectoSeResponsavel(Pessoa p, ItfAcaoFormulario pAcao) {
        UsuarioCRM usuarioVenda = (UsuarioCRM) p.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
        UsuarioCRM usuarioAtendimento = (UsuarioCRM) p.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioatendimento).getValor();
        boolean tempermisao = false;

        if (usuarioVenda != null) {
            if (usuarioVenda.equals(SBCore.getUsuarioLogado())) {
                tempermisao = true;
            }
        }

        if (usuarioAtendimento != null) {
            if (usuarioAtendimento.equals(SBCore.getUsuarioLogado())) {
                tempermisao = true;
            }
        }

        if (!tempermisao) {
            if (!usuarioVenda.equals(SBCore.getUsuarioLogado())) {
                SBCore.getServicoMensagens().enviarMsgAlertaAoUsuario("Somente " + usuarioVenda.getNome() + " pode gerir os responsáveis deste lead");
                return;
            }
        }

        exibirModalProspecto(p, pAcao);

    }

    public void exibirModalProspecto(Pessoa p, ItfAcaoFormulario pAcao) {
        //if (modalProsp.isAberto()) {
        //     modalProsp.fechar();
        // }
        if (p == null) {
            SBCore.enviarAvisoAoUsuario("Selecione um Lead e tente novamente");
            return;
        }
        if (p.getId() == null) {
            SBCore.enviarAvisoAoUsuario("O Lead se encontra no modo rascunho");
            return;
        }

        Map<String, List<String>> parametroEntidade = new HashMap<>();
        parametroEntidade.put("tipoEntidade", Lists.newArrayList("Pessoa"));
        parametroEntidade.put("codigoEntidade", Lists.newArrayList(String.valueOf(p.getId())));
        parametroEntidade.put("acao", Lists.newArrayList(pAcao.getNomeUnico()));

        paginaUtil.exibirModalComBotaoFecharEParametros(FabAcaoCRMAtendimento.MODAL_CRM_MB_PROSPECTOS.getRegistro().getComoFormulario().getXhtml(), parametroEntidade);

    }

    public void abrirPaginaDeProspecto(Pessoa p, ItfAcaoFormulario pAcao) {

        String pUrl = webPaginas.getUrlDaAcao(pAcao, p);
        UtilSBWP_JSFTools.vaParaPagina(pUrl);
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    public synchronized void salvarHistoricoEdicaoEmail(EnvioEmail pEmail) {
        try {
            if (pEmail != null && pEmail.getId() != 0) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                EnvioEmailRascunhoAutoSave historioco = new EnvioEmailRascunhoAutoSave();
                EnvioEmail email = em.find(EnvioEmail.class,
                        pEmail.getId());
                historioco.prepararNovoObjeto(email);

                EnvioEmailRascunhoAutoSave ultimoRascunho
                        = (EnvioEmailRascunhoAutoSave) UtilSBPersistencia.getRegistroByJPQL(
                                "from " + EnvioEmailRascunhoAutoSave.class
                                        .getSimpleName()
                                + " where emailVinculado_id=" + pEmail.getId() + " order by id desc", em
                        );
                if (ultimoRascunho == null || (pEmail.getTexto().length() != ultimoRascunho.getTexto().length())) {
                    UtilSBPersistencia.iniciarTransacao(em);
                    email.setTexto(pEmail.getTexto());
                    email.setAssunto(pEmail.getAssunto());
                    //email.setContatos(pEmail.getContatos());
                    //email.setArquivosAnexados(pEmail.getArquivosAnexados());
                    em.merge(email);
                    em.merge(historioco);
                    UtilSBPersistencia.finalizarTransacao(em);
                }

            }
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Salvando historico temporario", t);
        }

    }

    public void enviarArquivosPessoa(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            ArquivoAnexado arquivo = new ArquivoAnexado();

            try {
                Object atributo = event.getComponent().getAttributes().get("idProspecto");
                Long id = Long.valueOf(atributo.toString());
                prospecto
                        = UtilSBPersistencia.getRegistroByID(Pessoa.class,
                                id, emPagina);
                arquivo.setProspecto(prospecto);
                arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().uploadArquivo(file.getFileName(), UtilCRCBytes.gerarBytePorInputstream(file.getInputStream()));
                prospecto.getArquivos().add(arquivo);
                ModuloCRMAtendimento.prospectoSalvarAnexos(prospecto, prospecto.getArquivos());

            } catch (IOException ex) {
                Logger.getLogger(ServicosCRM.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void enviarArquivosClientePessoa(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            ArquivoCliente arquivo = new ArquivoCliente();

            try {

                prospecto = (Pessoa) event.getComponent().getAttributes().get("pessoa");
                CategoriaArquivoCliente categoria = (CategoriaArquivoCliente) event.getComponent().getAttributes().get("categoriaCliente");
                arquivo.setProspecto(prospecto);
                if (prospecto == null) {
                    SBCore.enviarMensagemUsuario("Pessoa não encontrada", FabMensagens.ALERTA);
                    return;
                }
                if (categoria == null) {
                    SBCore.enviarMensagemUsuario("Categoria não encontrada", FabMensagens.ALERTA);
                    return;
                }
                arquivo.setCategoriaArqCli(categoria);
                arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().uploadArquivo(file.getFileName(), UtilCRCBytes.gerarBytePorInputstream(file.getInputStream()));

                ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimento.arquivoClienteSalvar(arquivo);
                if (!resposta.isSucesso()) {
                    resposta.dispararMensagens();

                }

            } catch (IOException ex) {
                Logger.getLogger(ServicosCRM.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void enviarArquivosEquipePessoa(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            ArquivoAnexado arquivo = new ArquivoAnexado();

            try {

                prospecto = (Pessoa) event.getComponent().getAttributes().get("pessoa");

                CategoriaArquivoEquipe categoria = (CategoriaArquivoEquipe) event.getComponent().getAttributes().get("categoriaEquipe");
                SolicitacaoArquivoEquipe solicitacaoEquipe = null;

                try {
                    solicitacaoEquipe = (SolicitacaoArquivoEquipe) event.getComponent().getAttributes().get("solicitacaoArquivoEquipe");
                } catch (Throwable t) {
                    System.out.println("Solicitação arquivo equipe não enviado");
                }

                try {
                    SubPastaPrivada subPasta = (SubPastaPrivada) event.getComponent().getAttributes().get("subPasta");
                    if (subPasta != null) {
                        arquivo.setSubPasta(subPasta);
                    }
                } catch (Throwable t) {
                    System.out.println("Solicitação arquivo equipe não enviado");
                }
                arquivo.setProspecto(prospecto);
                if (prospecto == null) {
                    SBCore.enviarMensagemUsuario("Pessoa não encontrada", FabMensagens.ALERTA);
                    return;
                }
                if (categoria == null) {
                    SBCore.enviarMensagemUsuario("Categoria não encontrada", FabMensagens.ALERTA);
                    return;
                }
                arquivo.setCategoriaArqEquipe(categoria);
                arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().uploadArquivo(file.getFileName(), UtilCRCBytes.gerarBytePorInputstream(file.getInputStream()));
                ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimento.arquivoEquipeSalvar(arquivo);
                if (!resposta.isSucesso()) {
                    resposta.dispararMensagens();
                } else {
                    if (solicitacaoEquipe != null) {
                        ModuloCRMAtendimento.solicitacaoNotificarArquivoEquipe(solicitacaoEquipe);
                    }
                }

            } catch (IOException ex) {
                SBCore.enviarMensagemUsuario("Erro inesperado" + ex.getMessage(), FabMensagens.ALERTA);
            }

        }
    }

    public void atualizarImagemProspecto(FileUploadEvent event) {
        try {
            Object atributo = event.getComponent().getAttributes().get("idProspecto");
            Long id = Long.valueOf(atributo.toString());
            prospecto
                    = UtilSBPersistencia.getRegistroByID(Pessoa.class,
                            id, emPagina);
            InputStream arquivo;
            if (!UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(event.getFile().getFileName()).equals("png")) {
                BufferedImage imagem = UtilSBImagemEdicao.converterPNGParaJpg(ImageIO.read(event.getFile().getInputStream()), Color.white);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(imagem, "jpg", os);
                arquivo = new ByteArrayInputStream(os.toByteArray());
            } else {
                arquivo = event.getFile().getInputStream();
            }
            arquivo.reset();
            prospecto.uploadFotoTamanhoPequeno(arquivo);

            //      ModuloCRMAtendimento.prospectoSalvarLogoEncontrada(prospecto, prospecto.getImgPequena()).dispararMensagens();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando imagem", t);
        }
    }

    public void configurarPessoa(Pessoa pPessoa) {
        prospecto = pPessoa;
    }

    public void compartilharArquivoAnexoPessoa(ArquivoAnexado pArquivo) {
        String nomeArquivo = pArquivo.getArquivo();
        try {
            SBCore.enviarMensagemUsuario("Não implementado", FabMensagens.AVISO);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro exluindo arquivo" + nomeArquivo, t);
        }

    }

    public void excluirArquivoAnexoPessoa(ArquivoAnexado pArquivo) {
        String nomeArquivo = pArquivo.getArquivo();
        try {

            if (!SBCore.getServicoPermissao().isObjetoPermitidoUsuario(SBCore.getUsuarioLogado(), pArquivo)) {
                SBCore.enviarMensagemUsuario("Sem permissão para esta ação", FabMensagens.ALERTA);

                return;
            }
            if (SBCore.getUsuarioLogado() instanceof UsuarioConvidado) {

                long dias = UtilCRCDataHora.intervaloTempoDias(pArquivo.getDataHoraCriacao(), new Date());

                if ((pArquivo.getUsuarioCriou() != null && !SBCore.getUsuarioLogado().equals(pArquivo.getUsuarioCriou())) || dias >= 15) {
                    SBCore.enviarMensagemUsuario("Sem permissão para esta ação", FabMensagens.ALERTA);

                    return;
                }
            }

            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

            UtilSBPersistencia.iniciarTransacao(em);

            pArquivo.getCPinst("arquivo").getComoArquivoDeEntidade().excluirArquivo();
            UtilSBPersistencia.exluirRegistro(pArquivo, em);

            UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro exluindo arquivo" + nomeArquivo, t);
        }

    }

    public void alterarStatusProspecto(Pessoa pPessoa) {
        ModuloCRMAtendimento.prospectoAlterarStatusRelacionamento(pPessoa).dispararMensagens();
    }

    public String getIdProspectoStr() {
        return idProspectoStr;
    }

    public void setIdProspectoStr(String idProspectoStr) {
        this.idProspectoStr = idProspectoStr;
    }

    public String getIdAreaCardAtualizado() {
        return idAreaCardAtualizado;
    }

    public void setIdAreaCardAtualizado(String idAreaCardAtualizado) {
        this.idAreaCardAtualizado = idAreaCardAtualizado;
    }

    public void solicitarPermissao(Pessoa p) {
        ModuloCRMAtendimento.solicitacaoSolicitarAcessoCArd(p).dispararMensagens();
    }

    public boolean isUmUsuarioAtendimentoLogado() {
        if (sessaoAtual.isIdentificado()) {
            return false;
        }
        if (sessaoAtual.getUsuario() instanceof UsuarioCRM) {
            if (!((UsuarioCRM) sessaoAtual.getUsuario()).isUmUsuarioConvidado() && !((UsuarioCRM) sessaoAtual.getUsuario()).isUmUsuarioDoCliente()) {
                return true;
            }

        }
        return false;
    }
}
