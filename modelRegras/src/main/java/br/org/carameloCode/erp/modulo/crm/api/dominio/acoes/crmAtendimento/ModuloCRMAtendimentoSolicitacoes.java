package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabTipoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.api.ERPNotificacoes;
import br.org.carameloCode.erp.modulo.notificacao.api.ErroGerandoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.api.FabAcaoNotificacaoPadraoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabStatusComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
public class ModuloCRMAtendimentoSolicitacoes extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO)
    public static ItfRespostaAcaoDoSistema solicitacaoEnviarArquivoEquipe(Solicitacao pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Solicitacao solicitacao = loadEntidade(pSOlicitacao);

                if (solicitacao instanceof SolicitacaoArquivoCliente) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoArquivoEquipe) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoChamado) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoOrcamento) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ORCAMENTO.getRegistro().getComoFormulario());
                }

                if (solicitacao instanceof SolicitacaoConfirmacaoCliente) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_CONFIRMACAO_EQUIPE.getRegistro().getComoFormulario());
                }

                if (solicitacao instanceof SolicitacaoAcessoCard) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_CONCEDER_ACESSO.getRegistro().getComoFormulario());
                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoEnviarArquivoEquipe(SolicitacaoArquivoEquipe pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                SolicitacaoArquivoEquipe solicitacao = loadEntidade(pSOlicitacao);

                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO.getRegistro(getEm()),
                            pSOlicitacao.getUsuarioSolicitado(), solicitacao);

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiFinalizada(true);
                atualizarEntidade(solicitacao);
                ComoDialogo dialogo = CarameloCode.getServicoComunicacao().getArmazenamento().getDialogoAtivoByCodigoSelo(solicitacao.getCodigoSelo());
                SBCore.getServicoComunicacao().
                        responderComunicacao(solicitacao.getCodigoSelo(), dialogo.getRepostasPossiveis().stream().filter(rp -> rp.getTipoResposta().isRespostasPosiva()).findFirst().get(),
                                ERPTipoCanalComunicacao.INTRANET_MENU);

            }
        }
                .getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_CLIENTE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarConfirmacaoCliente(final SolicitacaoConfirmacaoCliente pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getContatoPessoa() == null) {
                    throw new ErroRegraDeNegocio("Defina o contato responsável");
                }
                ContatoProspecto ct = UtilSBPersistencia.loadEntidade(pSolicitacao.getContatoPessoa(), getEm());

                if (ct.getCPinst("usuarioVinculado").getValor() == null) {
                    throw new ErroRegraDeNegocio("O contato não tem um usuário vincolado, verifique se o e-mail foi definido");
                }
                if (ct.getUsuarioVinculado().getId() == null) {
                    ct.setUsuarioVinculado(atualizarEntidade(ct.getUsuarioVinculado()));
                }
                pSolicitacao.setUsuarioSolicitado(ct.getUsuarioVinculado());
                SolicitacaoConfirmacaoCliente solicitacaoCriada = atualizarEntidade(pSolicitacao);
                setRetorno(pSolicitacao);
                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE.getRegistro(getEm()),
                            pSolicitacao.getUsuarioSolicitado(), solicitacaoCriada);
                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_CLIENTE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoCliente(final SolicitacaoArquivoCliente pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getContatoPessoa() == null) {
                    throw new ErroRegraDeNegocio("Defina o contato responsável");
                }
                ContatoProspecto ct = UtilSBPersistencia.loadEntidade(pSolicitacao.getContatoPessoa(), getEm());

                if (ct.getCPinst("usuarioVinculado").getValor() == null) {
                    throw new ErroRegraDeNegocio("O contato não tem um usuário vincolado, verifique se o e-mail foi definido");
                }
                if (ct.getUsuarioVinculado().getId() == null) {
                    ct.setUsuarioVinculado(atualizarEntidade(ct.getUsuarioVinculado()));
                }
                pSolicitacao.setUsuarioSolicitado(ct.getUsuarioVinculado());
                SolicitacaoArquivoCliente solicitacaoCriada = atualizarEntidade(pSolicitacao);
                setRetorno(pSolicitacao);
                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE.getRegistro(getEm()),
                            pSolicitacao.getUsuarioSolicitado(), solicitacaoCriada);
                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoEqipe(SolicitacaoArquivoEquipe pSolicitacao) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getCategoriaArqEquipe() == null) {
                    throw new ErroRegraDeNegocio("Selecione uma categoria");
                }
                if (pSolicitacao.getDataHoraDataProgramada() == null) {
                    throw new ErroRegraDeNegocio("Defina a data limite para entrega");
                }
                if (pSolicitacao.getObservacao() == null || pSolicitacao.getObservacao().length() < 10) {
                    throw new ErroRegraDeNegocio("Descreva melhor sua solicitação");
                }
                if (pSolicitacao.getId() != null && pSolicitacao.getCodigoSelo() != null) {
                    ComoDialogo dialogo = CarameloCode.getServicoComunicacao().getArmazenamento().getDialogoAtivoByCodigoSelo(pSolicitacao.getCodigoSelo());
                    if (dialogo.getStatusComunicacao() == null) {
                        throw new ErroRegraDeNegocio("Aguardando resposta do usuário!");
                    } else {
                        if (!dialogo.getStatusComunicacao().equals(FabStatusComunicacao.RESPONDIDO)) {
                            //CarameloCode.getServicoComunicacao().dispararComunicacao(dialogo, ERPTipoCanalComunicacao.INTRANET_MENU);
                            throw new ErroRegraDeNegocio("Aguardando resposta do usuário uma notificação já foi gerada!");
                        }
                    }

                }

                SolicitacaoArquivoEquipe solicitacaoCriada = atualizarEntidade(pSolicitacao);
                setRetorno(solicitacaoCriada);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());
                if (solicitacaoCriada.getId() == null) {
                    throw new ErroRegraDeNegocio("Falha criando solicitação");
                }
                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO.getRegistro(getEm()),
                            pSolicitacao.getUsuarioSolicitado(), (ComoEntidadeSimples) getRetorno());
                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

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
                    //    SBCore.getServicoComunicacao().getArmazenamento().registrarDialogoAtivo((ComoDialogo) getRetorno());
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

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarArquivoEqipe(SolicitacaoConfirmacaoEquipe pSolicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getObservacao() == null || pSolicitacao.getObservacao().length() < 10) {
                    throw new ErroRegraDeNegocio("Descreva melhor sua solicitação");
                }

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
}
