package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.apresentacao.DocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.TipoChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.GrupoTipoDadoCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRMLogica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.ServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoServico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.util.UtilGeradorDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.Permitido_Grupos;
import com.super_bits.modulos.SBAcessosModel.model.quadroPermissao.QuadroPermissaoGrupo;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.RespostaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloBean;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

import br.org.carameloCode.erp.modulo.crm.api.model.modelodocumentotiposervico.CPModeloDocumentoTipoServico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.CPOrigemProspecto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.superBits.utilitario.editorArquivos.importacao.ImportacaoExcel;

/**
 *
 * @author desenvolvedor
 */
public class ModuloCRMAdmin extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema origemProspectoSalvar(OrigemProspecto pOrigemProspecto) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pOrigemProspecto), pOrigemProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pOrigemProspecto));
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema origemProspectoRemover(OrigemProspecto pOrigemProspecto) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pOrigemProspecto), pOrigemProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                removerEntidade(pOrigemProspecto);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_MOVER)
    public static ItfRespostaAcaoDoSistema origemProspectoMover(OrigemProspecto pOrigemProspecto, OrigemProspecto destino) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pOrigemProspecto), pOrigemProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UtilSBPersistencia.executaSQL(getEMResposta(), "update ProspectoEmpresa set origem_id=" + destino.getId() + " where origem_id=" + pOrigemProspecto.getId());
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_CONVERTER)
    public static ItfRespostaAcaoDoSistema origemProspectConverter(OrigemProspecto pOrigemProspecto, UsuarioCRM pUsuario) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pOrigemProspecto), pOrigemProspecto) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                OrigemProspecto origem = loadEntidade(pOrigemProspecto);
                if (origem.getCampoInstanciadoByNomeOuAnotacao(CPOrigemProspecto.umaorigempublica).getValorComoBoolean()) {
                    if (pUsuario == null) {
                        throw new ErroRegraDeNegocio("O usuário é obrigatório para converter em uma origem privada");
                    }
                    UtilSBPersistencia.executaSQL(getEMResposta(), "update " + OrigemProspecto.class.getSimpleName() + " set umaOrigempublica=false, umaOrigemPrivada=true, tipoOrigem='" + OrigemProspectoPrivado.class.getSimpleName() + "',usuarioDono_id=" + pUsuario.getId() + ",porcentagemAoCompartilhar=0 where id=" + origem.getId());
                } else {
                    UtilSBPersistencia.executaSQL(getEMResposta(), "update " + OrigemProspecto.class.getSimpleName() + " set umaOrigempublica=true,umaOrigemPrivada=false,tipoOrigem='" + OrigemProspecto.class.getSimpleName() + "',usuarioDono_id=null where id=" + origem.getId());
                }

            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.PROSPECTO_ADMIN_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarProspecto(PessoaJuridica pProspecto) {

        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pProspecto)) {
            @Override
            public void regraDeNegocio() {
                if (pProspecto.getLocalizacao() != null) {
                    if (pProspecto.getLocalizacao().getBairro() == null) {
                        pProspecto.setLocalizacao(null);
                    }
                }
                setRetorno(atualizarEntidade(pProspecto));
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_MERGE)
    public static ItfRespostaAcaoDoSistema metaRelacionamentoSalvar(final MetaRelacionamento pMeta) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pMeta), pMeta) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pMeta));
            }
        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema metaRelacionamentoRemover(final MetaRelacionamento pMeta) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pMeta), pMeta) {
            @Override
            public void regraDeNegocio() {
                setRetorno(removerEntidade(pMeta));
            }
        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_ATUALIZAR_PROSPECTO_TESTE)
    public static ItfRespostaAcaoDoSistema atualizarDadosProspectoTeste(ModeloDocumentoCRM pModelo) {

        return new RespostaComGestaoEntityManager(getNovaResposta(PessoaJuridica.class)) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                if (isSucesso()) {
                    PessoaJuridica p = (PessoaJuridica) getRetorno();
                    EntityManager em = UtilSBPersistencia.getNovoEM();

                    UtilSBPersistencia.fecharEM(em);
                }
            }

            @Override
            public void regraDeNegocio() {

                try {
                    ModeloDocumentoCRM modelo = loadEntidade(pModelo);
                    if (modelo.getLeadParaTestes() != null) {
                        Pessoa prospecto = modelo.getLeadParaTestes();

                        atualizarEntidade(prospecto);
                        setRetornoDisparaERetorna(prospecto);
                    } else {
                        addAlerta("Nenhum prospecto foi definido para geração do modelo padrão");
                    }

                } catch (Throwable t) {
                    addErro("Ouve um erro ao gerar o documento exemplo");
                }
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_GERAR_TESTE)

    public static ItfRespostaAcaoDoSistema gerarDocumentoProspectoTeste(final ModeloDocumentoCRM pModelo) {

        return new RespostaComGestaoEntityManager(getNovaRespostaAutorizaChecaNulo(pModelo)) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void regraDeNegocio() {

                try {
                    ModeloDocumentoCRM modelo = loadEntidade(pModelo);
                    Pessoa prospecto = modelo.getLeadParaTestes();
                    // ManterConexao manter = new ManterConexao(UtilSBPersistencia.loadEntidade(prospecto, getEmResposta()), getEmResposta());
                    //manter.start();

                    String diretorioArquivoTempUsuario = SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto() + "/" + SBCore.getControleDeSessao().getSessaoAtual().getUsuario().getEmail().hashCode() + "/arqTemp.docx";
                    String arquivo = UtilGeradorDocumentoCRM.gerarDocumentoPastaTemporariaConvertendoEmPDF(prospecto, pModelo, diretorioArquivoTempUsuario);
                    // manter.parar();
                    ArquivoAnexado arquivoTemp = (ArquivoAnexado) UtilSBPersistencia.getRegistroByPrimeiro(ArquivoAnexado.class, getEm());
                    arquivoTemp.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().uploadArquivo(arquivo, UtilCRCBytes.gerarBytesPorArquivo(new File(arquivo)));

                    atualizarEntidade(arquivoTemp);
                    String link = arquivoTemp.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().getLinkAbrirArquivo();
                    setRetornoDisparaERetorna(link);
                } catch (Throwable t) {
                    addErro("Ouve um erro ao gerar o documento exemplo");
                }
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarOpcaoDadosCRM(TipoDadoCRM poOpcaoDadosCRM) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(poOpcaoDadosCRM), poOpcaoDadosCRM) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (poOpcaoDadosCRM.getFabricaTipoAtributo() == null) {
                    addErro("O tipo de campo é obrigatório");
                }
                if (poOpcaoDadosCRM.getFabricaTipoAtributo() == FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS
                        || poOpcaoDadosCRM.getFabricaTipoAtributo() == FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES) {
                    addErro("O sistema ainda não suporta campos  do tipo " + poOpcaoDadosCRM.getFabricaTipoAtributo());
                    return;
                }

                atualizarEntidade(poOpcaoDadosCRM);
            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CADASTRO_USUARIO_CTR_ALTERAR_STATUS)
    public static ItfRespostaAcaoDoSistema usuario_alterarStatus(UsuarioCRM pUsuarioCRM) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuarioCRM), pUsuarioCRM) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UsuarioCRM usuario = loadEntidade(pUsuarioCRM);
                usuario.setAtivo(!usuario.isAtivo());
                atualizarEntidade(usuario);
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CADASTRO_USUARIO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema usuarioSalvarMerge(UsuarioCRM pUsuarioCRM) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuarioCRM), pUsuarioCRM) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                atualizarEntidade(pUsuarioCRM);

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoServicoSalvarMerge(TipoServico pServico) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pServico), pServico) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                atualizarEntidade(pServico);
            }

        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema tipoAtividadeExcluir(final TipoRelacionamento pTipoRelacionamento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoRelacionamento), pTipoRelacionamento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                //  TipoRelacionamento relacionamento = UtilSBPersistencia.loadEntidade(pTipoRelacionamento, getEm());
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  AtividadeCRM set relacionamentoGerado_id = null where relacionamentoGerado_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando Atividade crm");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  TipoAtividadeCRM set relacionamentoGerado_id = null where relacionamentoGerado_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  OrigemProspecto set relacionamentoPadrao_id = null where relacionamentoPadrao_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando OrigemProspecto");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  DadoCRM set tipoRelacionamentoVinculado_id = null where tipoRelacionamentoVinculado_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), " delete from TipoRelacionamento_TipoAtividadeCRM where TipoRelacionamento_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "delete from  HistoricoRelacionamento where  novoRelacionamento_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "delete from  HistoricoRelacionamento where  relacionamentoAnterior_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "delete from dadosObrigatorios_tipoRelacionamento where  tipoRelacionamento_id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "delete from TipoRelacionamento where id = " + pTipoRelacionamento.getId())) {
                    throw new ErroRegraDeNegocio("Erro atualizando TipoAtividadeCRM");
                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema tipoAtividadeExcluir(final TipoAtividadeCRM pTipoAtividade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoAtividade), pTipoAtividade) {
            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
                List<DadoCRM> dados = UtilSBPersistencia.getListaRegistrosByHQL("from " + DadoCRM.class
                        .getSimpleName() + " where atividadeCRM.tipoAtividade.id=" + pTipoAtividade.getId(), -1, em);

                dados.stream().forEach(dado -> {
                    dado.setAtividadeCRM(null);
                    UtilSBPersistencia.mergeRegistro(dado, em);
                });
                UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
                super.executarAcoesIniciais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                getEMResposta().setProperty("javax.persistence.query.timeoatut", 160000);

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update ArquivoAnexado set atividadeGeradora_id = null where atividadeGeradora_id in (select id from AtividadeCRM where tipoAtividade_id=" + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Arquivos anexados vinculados ao tipo de atividade");
                }
                ;

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update TipoAtividadeCRM set atividadeAgendada_id = null where atividadeAgendada_id =" + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Arquivos anexados vinculados ao tipo de atividade");
                }
                ;

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update TipoAtividadeCRM set atividadeAgendadaAposLeituraEmail_id = null where atividadeAgendadaAposLeituraEmail_id =" + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Arquivos anexados vinculados ao tipo de atividade");
                }
                ;

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from HistoricoRelacionamento where atividadeReferencia_id  in (select id from AtividadeCRM where tipoAtividade_id=" + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Histórico de relacionamento relacionados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from HistoricoRelacionamento where novoRelacionamento_id = " + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando  Histórico de relacionamento relacionados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update ModeloDocumentoCRM set tipoAtividadeVinculada_id =null where tipoAtividadeVinculada_id= " + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Modelos de documento vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update DadoCRM set atividadeCRM_id = null where atividadeCRM_id in (select id from AtividadeCRM where tipoAtividade_id = " + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Dados de atividade vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from  DadoColetado_Atividade where atividade_id in (select id from AtividadeCRM where tipoAtividade_id = " + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Dados de atividade vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from  DadoNaoColetado_Atividade where atividade_id in (select id from AtividadeCRM where tipoAtividade_id = " + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando DadoNaoColetado_Atividade de atividade vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "update  EmailCrm set atividade_id=null,tipoEmail='EnvioEmail'  where atividade_id in (select id from AtividadeCRM where tipoAtividade_id = " + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando DadoNaoColetado_Atividade de atividade vinculados ao tipo de atividade");
                }
                ;

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from CodigoConvite  where atividade_id in (select id from AtividadeCRM where tipoAtividade_id = " + pTipoAtividade.getId() + ");\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Atividades anexados vinculados ao tipo de atividade");
                }
                ;

                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from AtividadeCRM  where tipoAtividade_id= " + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Atividades anexados vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from TipoRelacionamento_TipoAtividadeCRM  where tiposAtividadeDisponiveis_id= " + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha atualizando Atividades anexados vinculados ao tipo de atividade");
                }
                ;
                if (!UtilSBPersistencia.executaSQL(getEMResposta(), "delete from TipoAtividadeCRM where id = " + pTipoAtividade.getId() + ";\n")) {
                    throw new ErroRegraDeNegocio("Falha excluindo o tipo de atividade");
                }
                ;

                // removerEntidade(tipoAtividadeExlusao);
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarModeloDocumentoTipoAtiviadade(final ModeloDocumentoTipoAtividade pModeloDocumento) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pModeloDocumento), pModeloDocumento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ModeloDocumentoTipoAtividade novoModelo = (ModeloDocumentoTipoAtividade) atualizarEntidade(pModeloDocumento, false);

                if (novoModelo == null) {
                    addErro("Ouve Um Erro Salvando o novo Modelo");

                } else {

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_MOVERLEADS)
    public static ItfRespostaAcaoDoSistema tipoRelacionamentoMoverPessoas(TipoRelacionamento pTipo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipo), pTipo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pTipo.getTipoRelacionamentoDestino() == null) {
                    throw new ErroRegraDeNegocio("Selecione um destino");

                }
                if (pTipo.getTipoRelacionamentoDestino().equals(pTipo)) {
                    throw new ErroRegraDeNegocio("Selecione um destino diferente");
                }
                TipoRelacionamento novoRelacionamento = loadEntidade(pTipo.getTipoRelacionamentoDestino());
                UtilSBPersistencia.executaSQL(getEm(), "update  ProspectoEmpresa set relacionamento_id=" + novoRelacionamento.getId() + " where relacionamento_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  ProspectoEmpresa set relacionamentoAnterior_id=" + novoRelacionamento.getId() + " where relacionamentoAnterior_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  AtividadeCRM set relacionamentoGerado_id=" + novoRelacionamento.getId() + " where relacionamentoGerado_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  TipoAtividadeCRM set relacionamentoGerado_id=" + novoRelacionamento.getId() + " where relacionamentoGerado_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  OrigemProspecto set relacionamentoPadrao_id=" + novoRelacionamento.getId() + " where relacionamentoPadrao_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  dadosObrigatorios_tipoRelacionamento set tipoRelacionamento_id=" + novoRelacionamento.getId() + " where  tipoRelacionamento_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  HistoricoRelacionamento set relacionamentoAnterior_id=" + novoRelacionamento.getId() + " where relacionamentoAnterior_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  HistoricoRelacionamento set novoRelacionamento_id=" + novoRelacionamento.getId() + " where novoRelacionamento_id=" + pTipo.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update  DadoCRM set tipoRelacionamentoVinculado_id=" + novoRelacionamento.getId() + " where tipoRelacionamentoVinculado_id=" + pTipo.getId());

            }
        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarTipoRelacionamento(final TipoRelacionamento pTipoRelacionamento) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoRelacionamento), pTipoRelacionamento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                TipoRelacionamento novoModelo = (TipoRelacionamento) atualizarEntidade(pTipoRelacionamento);

                if (novoModelo == null) {
                    addErro("Ouve Um Erro Salvando o novo Modelo");

                } else {

                }

            }
        }.dispararMensagens();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarTipoAtividade(final TipoAtividadeCRM pTipoAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoAtividade), pTipoAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                TipoAtividadeCRM novoModelo = (TipoAtividadeCRM) atualizarEntidade(pTipoAtividade);

                if (novoModelo == null) {
                    addErro("Ouve Um Erro Salvando o novo Modelo");

                } else {

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_DESVINCULAR_EMAIL)
    public static ItfRespostaAcaoDoSistema tipoAtividadeDesvincularEmail(final TipoAtividadeCRM pTipoAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoAtividade), pTipoAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                pTipoAtividade.setModeloEmail(null);
                TipoAtividadeCRM novoModelo = (TipoAtividadeCRM) atualizarEntidade(pTipoAtividade);
                novoModelo.setModeloEmail(null);
                atualizarEntidade(novoModelo);

                if (novoModelo == null) {
                    addErro("Ouve Um Erro Salvando o novo Modelo");

                } else {

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_VINCULAR_EMAIL)
    public static ItfRespostaAcaoDoSistema tipoAtividadeVincularEmail(final TipoAtividadeCRM pTipoAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoAtividade), pTipoAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ModeloEmail email = new ModeloEmail();
                email = atualizarEntidade(email);
                TipoAtividadeCRM atividadeAtualizada = (TipoAtividadeCRM) atualizarEntidade(pTipoAtividade);
                atividadeAtualizada.setModeloEmail(email);

                if (atividadeAtualizada == null) {
                    addErro("Ouve Um Erro Salvando o novo Modelo");

                } else {

                }

            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_CTR_IMPORTAR)
    public static ItfRespostaAcaoDoSistema carregarExcel(String pArquivo, OrigemProspecto pOrigem) {

        return new RespostaComGestaoEntityManager(getNovaResposta(List.class
        )) {
            @Override
            public void regraDeNegocio() {

                ImportacaoExcel<PessoaJuridica> importador;

                Map<String, Integer> mapaDeCamposImp = new HashMap<>();

                mapaDeCamposImp.put("nome", 1);
                mapaDeCamposImp.put("telefonePrincipal", 2);
                mapaDeCamposImp.put("email", 3);
                mapaDeCamposImp.put("site", 4);
                importador = new ImportacaoExcel<>(pArquivo, mapaDeCamposImp, PessoaJuridica.class
                );
                importador.processar();

                List<PessoaJuridica> prospectosAdicionados = new ArrayList<>();

                if (importador.getListaDeErros().isEmpty()) {
                    for (PessoaJuridica p : importador.getRegistrosSucesso()) {
                        p.setOrigem(pOrigem);
                        PessoaJuridica prosp = (PessoaJuridica) atualizarEntidade(p);

                        ItfResposta resultadoSalvar = ModuloCRMAtendimento.prospectoSalvar(prosp);

                        if (resultadoSalvar.isSucesso()) {
                            prospectosAdicionados.add((PessoaJuridica) resultadoSalvar.getRetorno());
                        } else {
                            addAlerta("O prospecto" + p.getNome() + " Não pôde ser adicionado");
                        }
                    }
                } else {
                    String mensagemErro = "Alguns registros da tabela não foram importados";
                    for (String menString : importador.getListaDeErros()) {
                        mensagemErro += menString;
                    }
                    addErro(mensagemErro);
                }
                if (isSucesso()) {
                    setRetorno(prospectosAdicionados);
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_CTR_IMPORTAR)
    public static ItfRespostaAcaoDoSistema malticSalvarConfiguracoes(ConfigModuloBean pConfiguracoes) {
        ItfRespostaAcaoDoSistema resp = getNovaRespostaAutorizaChecaNulo(pConfiguracoes);
        if (!pConfiguracoes.salvarAlteracoes()) {
            resp.addErro("Ouve um erro salvando as configurações do módulo");
        }
        return resp;

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MAUTIC_CTR_EXPORTAR_DADOS)
    public static ItfRespostaAcaoDoSistema malticExportarProspectos() {
        ItfRespostaAcaoDoSistema resp = getNovaRespostaComAutorizacao(String.class
        );
        String relatorio = (String) new ExecucaoConsultaComGestaoEntityManager() {

            @Override
            public Object regraDeNegocioRetornandoResultado() {
                List<PessoaJuridica> prospectos = UtilSBPersistencia.getListaTodos(PessoaJuridica.class,
                        getEm());

                for (PessoaJuridica prosp : prospectos) {
                    try {

                        if (prosp.getOrigem() != null && !UtilCRCStringValidador.isNuloOuEmbranco((prosp.getEmail()))) {
                            ModuloCRMAtendimento.prospectoAtualizarMautic(prosp);

                        }

                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro importando Prospecto" + prosp.toString() + t.getMessage(), t);
                        return "";
                    }
                }
                return "ok";
            }
        }.getResultado();
        if (!relatorio.isEmpty()) {
            resp.addErro("Houve um erro exportando os dados");
        }
        resp.setRetorno(relatorio);
        return resp;

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.OPCAO_DADOS_CRM_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema opcaoDadosRemover(final TipoDadoCRM pDado) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDado), pDado) {
            @Override
            public void regraDeNegocio() {
                removerEntidade(pDado);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_SALVAR_MERGE)
    public synchronized static ItfRespostaAcaoDoSistema documentoApresentacaoSalvar(final DocumentoApresentacao pDocumento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDocumento), pDocumento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pDocumento);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_ATIVAR_DESATIVAR)
    public synchronized static ItfRespostaAcaoDoSistema documentoApresentacaoAlterarStatus(final DocumentoApresentacao pDocumento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDocumento), pDocumento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                DocumentoApresentacao documentoApresentacao = loadEntidade(pDocumento);
                documentoApresentacao.setAtivo(!documentoApresentacao.isAtivo());
                atualizarEntidade(documentoApresentacao);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_REMOVER)
    public synchronized static ItfRespostaAcaoDoSistema documentoApresentacaoExluir(final DocumentoApresentacao pDocumento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDocumento), pDocumento) {
            @Override
            public void regraDeNegocio() {
                removerEntidade(pDocumento);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_CTR_SALVAR_MERGE)
    public synchronized static ItfRespostaAcaoDoSistema grupoDadoDinamicorSalvarMerge(final GrupoTipoDadoCrm pDocumento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDocumento), pDocumento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pDocumento);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TAG_CTR_SALVAR_MERGE)
    public synchronized static ItfRespostaAcaoDoSistema tagSalvar(final TagAtendimento pTag) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTag), pTag) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pTag.getCor() == null) {
                    throw new ErroRegraDeNegocio("A cor é obrigatória");
                }
                atualizarEntidade(pTag);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema documentoApresentacaoRemover(final DocumentoApresentacao pDocumento) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDocumento), pDocumento) {
            @Override
            public void regraDeNegocio() {
                if (pDocumento.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().isExisteArquivo()) {
                    pDocumento.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().excluirArquivo();
                }
                removerEntidade(pDocumento);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_RECEBER_EMAILS)
    public static ItfRespostaAcaoDoSistema configuracoes_receber_emails() {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCRM.class
        ), new UsuarioCRM()) {
            @Override
            public void regraDeNegocio() {
                List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class,
                        getEm());
                boolean tudoOK = true;
                boolean nadafuncionou = true;
                for (UsuarioCRM usuario : usuarios) {
                    ItfResposta resp = configuracoes_receber_email(usuario);
                    if (!resp.isSucesso()) {
                        getMensagens().add(resp.getMensagens().get(0));
                        tudoOK = false;
                    } else {
                        nadafuncionou = false;
                    }

                }
                if (nadafuncionou) {
                    addErro("Não foi possível receber e-mails");
                }

                if (tudoOK) {
                    addAviso("Todas os e-mails foram sincronizados com sucesso");
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_SALVAR_CONFIGURACAO)
    public synchronized static ItfRespostaAcaoDoSistema emailConfiguracoesSalvar(final UsuarioCRM pUsuario) {

        ItfRespostaAcaoDoSistema resp = getNovaRespostaAutorizaChecaNulo(pUsuario);
        if (resp.isSucesso()) {
            ItfRespostaAcaoDoSistema respostaTEste = emailConfiguracoesTestar(pUsuario);
            if (respostaTEste.isSucesso()) {
                UtilSBPersistencia.mergeRegistro(pUsuario);
                return ModuloCRMEmail.receberEmail(pUsuario);
            } else {
                return respostaTEste;
            }
        } else {
            return resp;
        }

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_TESTAR_CONFIGURACAO)
    public synchronized static ItfRespostaAcaoDoSistema emailConfiguracoesTestar(final UsuarioCRM pUsuario) {

        ItfRespostaAcaoDoSistema resp = getNovaRespostaAutorizaChecaNulo(pUsuario);
        if (resp.isSucesso()) {
            return ModuloCRMEmail.emailTestar(pUsuario);
        } else {
            return resp.dispararMensagens();
        }

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_RECEBER_EMAIL)
    public synchronized static ItfRespostaAcaoDoSistema configuracoes_receber_email(final UsuarioCRM pUsuario) {

        ItfRespostaAcaoDoSistema resp = getNovaRespostaAutorizaChecaNulo(pUsuario);
        if (resp.isSucesso()) {
            return ModuloCRMEmail.receberEmail(pUsuario);
        } else {
            return resp;
        }

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.GRUPO_CTR_SALVAR_PERMISSOES)
    public static ItfRespostaAcaoDoSistema grupoSalvarPermissoes(final List<QuadroPermissaoGrupo> pQuadrosPermissaoGrupo
    ) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(GrupoUsuarioSB.class
        ), pQuadrosPermissaoGrupo.get(0)) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                GrupoUsuarioSB grupoATualizado = UtilSBPersistencia.loadEntidade(pQuadrosPermissaoGrupo.get(0).getGrupoUsuario(), getEm());
                if (grupoATualizado.equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                    addErro("As permissões do Grupo Admin, são imutáveis");
                    return;
                }
                List<Permitido_Grupos> permissoesConcedidas = new ArrayList<>();
                pQuadrosPermissaoGrupo.forEach((quadroPermissao) -> {
                    for (ComoAcaoDoSistema acao : quadroPermissao.getAcoesPermitidas()) {
                        Permitido_Grupos permissao = new Permitido_Grupos();
                        permissao.setAcesso(UtilSBPersistencia.getRegistroByID(PermissaoSB.class,
                                UtilSBController.gerarIDAcaoDoSistema(acao.getEnumAcaoDoSistema()), getEm()));
                        if (permissao.getAcesso() == null) {
                            throw new UnsupportedOperationException("O Acesso da ação não foi definido ação->" + acao);
                        }
                        permissao.setGrupo(grupoATualizado);
                        permissao.defineIdPermitidoGrupo();
                        permissoesConcedidas.add(permissao);

                    }
                });
                permissoesConcedidas.forEach(pm -> {
                    if (!grupoATualizado.getPermissoesConcedidas().contains(pm)) {
                        try {
                            atualizarEntidade(pm);
                        } catch (ErroRegraDeNegocio ex) {
                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro salvando" + pm, ex);
                        }
                    }
                });
                List<Permitido_Grupos> pmGrupo = new ArrayList<>();
                grupoATualizado.getPermissoesConcedidas().forEach(pm -> {
                    if (!permissoesConcedidas.contains(pm)) {
                        // removerEntidade(pm);
                        pmGrupo.add(pm);

                    }
                });
                grupoATualizado.getPermissoesConcedidas().removeAll(pmGrupo);

                setRetorno(grupoATualizado);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_CHAMADO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipo(TipoChamado ptipoChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(ptipoChamado), ptipoChamado) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(ptipoChamado));
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema tipoServicoRemover(TipoServico pTipoServico) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoServico), pTipoServico) {
            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                super.executarAcoesIniciais(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                TipoServico tipoServico = loadEntidade(pTipoServico);
                ConsultaDinamicaDeEntidade consultaServicosOferecidos = new ConsultaDinamicaDeEntidade(ServicoOferecido.class,
                        getEMResposta()).addCondicaoManyToOneIgualA(tipoServico);
                List<ServicoOferecido> itensDeOrcamento = consultaServicosOferecidos.resultadoRegistros();
                for (ServicoOferecido itemServicoOferecido : itensDeOrcamento) {
                    removerEntidade(itemServicoOferecido);
                }
                UtilSBPersistencia.executaSQL(getEMResposta(), "delete from tipoSevico_documentoApresentacao where tipoServico_id=" + tipoServico.getId());
                UtilSBPersistencia.executaSQL(getEMResposta(), "UPDATE  ModeloDocumentoCRM set " + CPModeloDocumentoTipoServico.tiposervico + "_id=null  where tipomodelo = '" + ModeloDocumentoTipoServico.class
                        .getSimpleName() + "' and " + CPModeloDocumentoTipoServico.tiposervico + "_id=55");

                //  List<Orcamento> orcamentos = UtilSBPersistencia.getListaRegistrosByHQL("SELECT orc from " + Orcamento.class.getSimpleName() + " orc  \n"
                //          + " JOIN orc.servicoOferecido srvof \n"
                //         + " JOIN srvof.tipoServico ts \n"
                //         + "where srvof.tipoServico.id=" + tipoServico.getId(), -1, getEMResposta());
                //  for (Orcamento orc : orcamentos) {
                //      for (ServicoOferecido itemOrc : orc.getServicoOferecido()) {
                //          System.out.println(itemOrc.getTipoServico().getId());
                //         System.out.println(itemOrc.getValorMensal());
                //        System.out.println(itemOrc.getValorSetup());
                //removerEntidade(itemOrc);
                //  }
                //removerEntidade(orc);
                //}
                //if (!orcamentos.isEmpty()) {
                removerEntidade(tipoServico);
                // }
                //Exluir os orçamentos que possuem o serviço
            }

        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.INTEGRACAO_ERP_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema chavesDeAcessoAtualizar(SistemaERPConfiavel pChaveSistema) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(SistemaERPConfiavel.class), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pChaveSistema);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoMensagemSAlvarAtualizar(TipoMensagemMktWhatsApp pTipoMensagem) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(TipoMensagemMktWhatsApp.class), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pTipoMensagem);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema parametroSAlvarAtualizar(ParametroMensagemWtzap pTipoMensagem) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoMensagem), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pTipoMensagem);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema categoriaSAlvarAtualizar(CategoriaArquivoCliente pCategoria) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pCategoria), pCategoria) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pCategoria);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema categoriaSAlvarAtualizar(CategoriaArquivoEquipe pCategoria) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(CategoriaArquivoEquipe.class), pCategoria) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pCategoria);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipodadoDinamicoLinkIntegracao(TipoDadoCrmLinkIntegracao pTipo) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipo), pTipo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pTipo);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoDadoDinamicoLogico(TipoDadoCRMLogica pCategoria) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(CategoriaArquivoEquipe.class), pCategoria) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pCategoria);
            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_CONVERTER)
    public static ItfRespostaAcaoDoSistema tipoRelacionamento(TipoRelacionamento pTipoRelacionamento) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(CategoriaArquivoEquipe.class), pTipoRelacionamento) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pTipoRelacionamento.getTipoRelacionamentoConversao() == null) {
                    throw new ErroRegraDeNegocio("Informe o tipo de relacionamento que deseja converter");
                }
                if (pTipoRelacionamento.equals(pTipoRelacionamento.getTipoRelacionamentoConversao())) {
                    throw new ErroRegraDeNegocio("Escolha um tipo diferente");
                }

                TipoRelacionamento relacionamentoAnterior = UtilSBPersistencia.loadEntidade(pTipoRelacionamento, getEm());
                TipoRelacionamento relacionamentoConversao = UtilSBPersistencia.loadEntidade(pTipoRelacionamento.getTipoRelacionamentoConversao(), getEm());

                if (!UtilSBPersistencia.executaSQL(getEm(), "update ProspectoEmpresa set relacionamento_id = " + relacionamentoConversao.getId() + " where relacionamento_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizando Cartões ");
                }

                if (!UtilSBPersistencia.executaSQL(getEm(), "update ProspectoEmpresa set relacionamentoAnterior_id = " + relacionamentoConversao.getId() + " where relacionamentoAnterior_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizando Cartões ");
                }

                if (!UtilSBPersistencia.executaSQL(getEm(), "update  AtividadeCRM set relacionamentoGerado_id = " + relacionamentoConversao.getId() + " where relacionamentoGerado_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizando Atividas ");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  TipoAtividadeCRM set relacionamentoGerado_id = " + relacionamentoConversao.getId() + " where relacionamentoGerado_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando relacionamento gerado dos tipos de atividade");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  OrigemProspecto set relacionamentoPadrao_id = " + relacionamentoConversao.getId() + " where relacionamentoPadrao_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando relacionamento padrão da origem");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update  DadoCRM set tipoRelacionamentoVinculado_id = " + relacionamentoConversao.getId() + " where tipoRelacionamentoVinculado_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando relacionamento tipo de relacionamento vinculado");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update TipoRelacionamento_TipoAtividadeCRM set TipoRelacionamento_id = " + relacionamentoConversao.getId() + "  where TipoRelacionamento_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando tipo de relacionamento ");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update HistoricoRelacionamento set novoRelacionamento_id = " + relacionamentoConversao.getId() + " where  novoRelacionamento_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando Historico de RElacionamento");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update HistoricoRelacionamento set relacionamentoAnterior_id = " + relacionamentoConversao.getId() + " where  relacionamentoAnterior_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando relacionamento anterior");
                }
                if (!UtilSBPersistencia.executaSQL(getEm(), "update dadosObrigatorios_tipoRelacionamento  set tipoRelacionamento_id = " + relacionamentoConversao.getId() + " where  tipoRelacionamento_id = " + relacionamentoAnterior.getId())) {
                    throw new ErroRegraDeNegocio("Falha atualizaando relacionamento gerado dos tipos de atividade");
                }

            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_CONVERTER)
    public static ItfRespostaAcaoDoSistema tipoRelacionamento(MetaRelacionamento pMeta) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(CategoriaArquivoEquipe.class), pMeta) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pMeta.getMetaConversao() == null) {
                    throw new ErroRegraDeNegocio("Informe o tipo de relacionamento que deseja converter");
                }
                if (pMeta.equals(pMeta.getMetaConversao())) {
                    throw new ErroRegraDeNegocio("Escolha um tipo diferente");
                }
                UtilSBPersistencia.executaSQL(getEm(), "update ProspectoEmpresa set meta_id = " + pMeta.getMetaConversao().getId() + " where meta_id = " + pMeta.getId());
                UtilSBPersistencia.executaSQL(getEm(), "update TipoRelacionamento set metaRelacionamento_id = " + pMeta.getMetaConversao().getId() + " where metaRelacionamento_id=" + pMeta.getId());

            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.FORM_CHAT_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoRelacionamento(TipoChatBot pChatBot) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(CategoriaArquivoEquipe.class), pChatBot) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidadeConfigRetorno(pChatBot);

            }
        };
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoFormularioTypebotAtualizar(TipoFormulario pTipoFormulario) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(TipoFormulario.class), pTipoFormulario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidade(pTipoFormulario);
            }
        };
    }

}
