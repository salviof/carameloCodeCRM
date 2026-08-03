package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.CPSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabTipoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.api.ERPNotificacoes;
import br.org.carameloCode.erp.modulo.notificacao.api.ErroGerandoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.api.FabAcaoNotificacaoPadraoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoUsrParaUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabStatusComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
public class ModuloCRMAtendimentoSolicitacoes extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO)
    public static ItfRespostaAcaoDoSistema solicitacaoabirFormularioResolucao(Solicitacao pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                Solicitacao solicitacao = loadEntidade(pSOlicitacao);
                setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS.getRegistro().getComoFormulario());
                if (solicitacao instanceof SolicitacaoArquivoCliente) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoArquivoEquipe) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoChamado) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_CHAMADO.getRegistro().getComoFormulario());
                }
                if (solicitacao instanceof SolicitacaoOrcamento) {

                    NotificacaoSB ntf = (NotificacaoSB) solicitacao.getCPinst(CPSolicitacao.notificacao).getValor();
                    if (ntf != null && !ntf.getDisparos().isEmpty()) {
                        ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().registrarReciboLeitura(ntf.getDisparos().get(0).getCodigoRegistroEnvio(), UtilCRCStringGerador.getStringRandomicaUUID());

                    }
                    String url = CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_EDITAR, ((SolicitacaoOrcamento) pSOlicitacao).getOrcamento());
                    setUrlDestinoSucesso(url);

                    NotificacaoSB notificacao;
                    try {
                        notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_ORCAMENTO_ATENDIDA.getRegistro(getEm()),
                                pSOlicitacao.getUsuarioSolicitante(), solicitacao);

                    } catch (ErroGerandoNotificacao ex) {
                        throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                    }
                    solicitacao = atualizarEntidade(solicitacao);
                    adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

                }

                if (solicitacao instanceof SolicitArqAtualizacaoEqp) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ATUALIZAR_ARQUIVO.getRegistro().getComoFormulario());
                }

                if (solicitacao instanceof SolicitacaoConfirmacaoCliente) {
                    throw new ErroRegraDeNegocio("O cliente deve confirmar essa ação");
                }

                if (solicitacao instanceof SolicitacaoConfirmacaoEquipe) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_CONFIRMACAO_EQUIPE.getRegistro().getComoFormulario());
                }

                if (solicitacao instanceof SolicitacaoAcessoCard) {
                    setProximoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_CONCEDER_ACESSO.getRegistro().getComoFormulario());
                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICITAR_ABERTURA_CHAMADO)
    public static ItfRespostaAcaoDoSistema solicitacaoChamadoEquipe(SolicitacaoChamado pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (pSOlicitacao.getPessoa() == null || pSOlicitacao.getPessoa().getContatoPrincipal() == null || pSOlicitacao.getPessoa().getContatoPrincipal().getUsuarioVinculado() == null) {
                    throw new ErroRegraDeNegocio("O Cliente não possui um usuário válido, verifique o email e telefone do contato");
                }

                if (pSOlicitacao.getChamado() == null) {
                    ChamadoCliente novoChamado = new ChamadoCliente();
                    novoChamado.setDescricao("Chamado criado a pedido de  " + pSOlicitacao.getUsuarioSolicitante().getNome() + " sobre: " + pSOlicitacao.getObservacao());
                    novoChamado.setTipoChamado(pSOlicitacao.getTipoChamado());
                    novoChamado.setPessoa(pSOlicitacao.getPessoa());
                    novoChamado.setUsuarioCliente(pSOlicitacao.getPessoa().getContatoPrincipal().getUsuarioVinculado());

                    novoChamado.setUsuarioCriou(pSOlicitacao.getUsuarioSolicitado());
                    novoChamado.setStatus(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                    ItfRespostaAcaoDoSistema respChamado = ModuloCRMAtendimentoChamado.chamadocriar(novoChamado);
                    if (!respChamado.isSucesso()) {
                        throw new ErroRegraDeNegocio("Falha criando chamado, " + respChamado.getMensagens().get(0).getMenssagem());
                    }
                    novoChamado = (ChamadoCliente) respChamado.getRetorno();
                    pSOlicitacao.setChamado(novoChamado);
                }
                pSOlicitacao.setFoiFinalizada(false);
                pSOlicitacao.setFoiAtendida(false);
                pSOlicitacao.setFoiRecebida(false);
                SolicitacaoChamado solicitacaoChamado = atualizarEntidade(pSOlicitacao);

                NotificacaoSB notificacao;
                try {

                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_CHAMADO.getRegistro(getEm()),
                                    solicitacaoChamado.getUsuarioSolicitante(),
                                    solicitacaoChamado.getUsuarioSolicitado(),
                                    solicitacaoChamado);
                    solicitacaoChamado.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }

                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

            }
        }
                .getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQ_ATUALIZACAO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarAtaulizacaoArquivoEqipe(SolicitArqAtualizacaoEqp pSolicitacao) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getArquivo() == null) {
                    throw new ErroRegraDeNegocio("Selecione um arquivo");
                }

                if (pSolicitacao.getDataHoraDataProgramada() == null) {
                    throw new ErroRegraDeNegocio("Defina a data limite para entrega");
                }

                if (pSolicitacao.getObservacao() == null || pSolicitacao.getObservacao().length() < 10) {
                    throw new ErroRegraDeNegocio("Descreva melhor sua solicitação");
                }

                SolicitArqAtualizacaoEqp solicitacaoCriada = atualizarEntidade(pSolicitacao);
                setRetorno(solicitacaoCriada);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());
                if (solicitacaoCriada.getId() == null) {
                    throw new ErroRegraDeNegocio("Falha criando solicitação");
                }
                NotificacaoSB notificacao;
                try {
                    TipoNotificacaoUsrComUsr tipoNotificacao = (TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ATUALIZACAO_ARQUIVO.getRegistro(getEm());
                    System.out.println(tipoNotificacao.getNomeEntidadeReferencia());
                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios(tipoNotificacao,
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    solicitacaoCriada);

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());
                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_VERSAO_ATUALIZADA)
    public static ItfRespostaAcaoDoSistema envioArquivoAtualizado(SolicitArqAtualizacaoEqp pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    if (dialogo != null) {
                        CarameloCode.getServicoComunicacao().getArmazenamento().removerDialogoAtivo(dialogo.getCodigoSelo());
                    }
                }
            }
            ComoDialogo dialogo;

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                SolicitArqAtualizacaoEqp solicitacao = loadEntidade(pSOlicitacao);

                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ENVIO_ATUALIZACAO_ARQUIVO.getRegistro(getEm()),
                            pSOlicitacao.getUsuarioSolicitante(), solicitacao);

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiAtendida(true);
                solicitacao.setStatus(FabStatusSolicitacao.FINALIZADO.getRegistro());
                atualizarEntidade(solicitacao);
                dialogo = CarameloCode.getServicoComunicacao().getArmazenamento().getDialogoAtivoByCodigoSelo(solicitacao.getCodigoSelo());

                NotificacaoSB ntf = (NotificacaoSB) solicitacao.getCPinst(CPSolicitacao.notificacao).getValor();
                if (ntf != null && !ntf.getDisparos().isEmpty()) {
                    ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().registrarReciboLeitura(ntf.getDisparos().get(0).getCodigoRegistroEnvio(), UtilCRCStringGerador.getStringRandomicaUUID());
                }
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

            }
        }
                .getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_EQUIPE)
    public static ItfRespostaAcaoDoSistema solicitacaoEnviarArquivoEquipe(SolicitacaoArquivoEquipe pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    if (dialogo != null) {
                        CarameloCode.getServicoComunicacao().getArmazenamento().removerDialogoAtivo(dialogo.getCodigoSelo());
                    }
                }
            }
            ComoDialogo dialogo;

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                SolicitacaoArquivoEquipe solicitacao = loadEntidade(pSOlicitacao);

                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO_ENVIO.getRegistro(getEm()),
                            pSOlicitacao.getUsuarioSolicitante(), solicitacao);

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);

                solicitacao.setFoiFinalizada(true);
                solicitacao.setFoiAtendida(true);
                solicitacao.setStatus(FabStatusSolicitacao.FINALIZADO.getRegistro());
                atualizarEntidade(solicitacao);
                dialogo = CarameloCode.getServicoComunicacao().getArmazenamento().getDialogoAtivoByCodigoSelo(solicitacao.getCodigoSelo());

                NotificacaoSB ntf = (NotificacaoSB) solicitacao.getCPinst(CPSolicitacao.notificacao).getValor();
                if (ntf != null && !ntf.getDisparos().isEmpty()) {
                    ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().registrarReciboLeitura(ntf.getDisparos().get(0).getCodigoRegistroEnvio(), UtilCRCStringGerador.getStringRandomicaUUID());
                }
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

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

                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_CONFIRMACAO_AO_CLIENTE.getRegistro(getEm()),
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    solicitacaoCriada);

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE, CarameloCode.getUsuarioLogado()));
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

                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_ARQUIVO_DA_EQUIPE_AO_CLIENTE.getRegistro(getEm()),
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    solicitacaoCriada);

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE, CarameloCode.getUsuarioLogado()));
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
                NotificacaoUsrParaUsr notificacao;
                try {
                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_EQUIPE_ARQUIVO.getRegistro(getEm()),
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    (ComoEntidadeSimples) getRetorno());

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICITAR_CRIACAO_ORCAMENTO)
    public static ItfRespostaAcaoDoSistema solicitacaoSolicitarOrcamento(SolicitacaoOrcamento pSolicitacao) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSolicitacao), pSolicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pSolicitacao.getOrcamento() == null) {
                    throw new ErroRegraDeNegocio("Selecione um orçamento");
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

                            throw new ErroRegraDeNegocio("Aguardando resposta do usuário uma notificação já foi gerada!");
                        }
                    }

                }

                SolicitacaoOrcamento solicitacaoCriada = atualizarEntidade(pSolicitacao);
                setRetorno(solicitacaoCriada);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());
                if (solicitacaoCriada.getId() == null) {
                    throw new ErroRegraDeNegocio("Falha criando solicitação");
                }
                NotificacaoUsrParaUsr notificacao;
                try {
                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_ORCAMENTO.getRegistro(getEm()),
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    (ComoEntidadeSimples) getRetorno());

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ACESSO_PESSOA)
    public static ItfRespostaAcaoDoSistema solicitarAcessoPessoa(final Pessoa pPessoa) {
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
                atualizarEntidadeSetRetorno(solicitacao);
                addAviso("A solicitação foi enviada para " + solicitacao.getUsuarioSolicitado().getNome());
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

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
                solicitacao.setStatus(FabStatusSolicitacao.FINALIZADO.getRegistro());
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
                solicitacao.setStatus(FabStatusSolicitacao.RECUSADO.getRegistro());
                atualizarEntidade(solicitacao);
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

                SolicitacaoConfirmacaoEquipe solicitacaoCriada = atualizarEntidade(pSolicitacao);
                addAviso("A solicitação foi enviada para " + pSolicitacao.getUsuarioSolicitado().getNome());

                NotificacaoSB notificacao;
                try {

                    notificacao = (NotificacaoUsrParaUsr) ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                            .gerarNotificacaoEntreUsuarios((TipoNotificacaoUsrComUsr) FabTipoNotificacao.NOTIFICACAO_SOLICITACAO_CONFIRMACAO_A_EQUIPE.getRegistro(getEm()),
                                    pSolicitacao.getUsuarioSolicitante(),
                                    pSolicitacao.getUsuarioSolicitado(),
                                    solicitacaoCriada);

                    solicitacaoCriada.setCodigoSelo(notificacao.getCodigoSeloComunicacao());

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_CTR_CONFIRMACAO_EQUIPE)
    public static ItfRespostaAcaoDoSistema enviarConfirmacaoEquipe(SolicitacaoConfirmacaoEquipe pSOlicitacao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pSOlicitacao), pSOlicitacao) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    if (dialogo != null) {
                        CarameloCode.getServicoComunicacao().getArmazenamento().removerDialogoAtivo(dialogo.getCodigoSelo());
                    }
                }
            }
            ComoDialogo dialogo;

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (pSOlicitacao.getTipoRespostaSelecionada() == null) {
                    throw new ErroRegraDeNegocio("o tipo de resposta é obrigatório");
                }

                pSOlicitacao.setFoiFinalizada(true);
                pSOlicitacao.setFoiAtendida(true);
                pSOlicitacao.setStatus(FabStatusSolicitacao.FINALIZADO.getRegistro());
                SolicitacaoConfirmacaoEquipe solicitacao = atualizarEntidade(pSOlicitacao);

                NotificacaoSB notificacao;
                try {
                    notificacao = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICAR_SOLICITACAO_CONFIRMADA_EQUIPE.getRegistro(getEm()),
                            pSOlicitacao.getUsuarioSolicitante(), solicitacao);

                } catch (ErroGerandoNotificacao ex) {
                    throw new ErroRegraDeNegocio("Falha gerando regra de negocio");
                }
                solicitacao = atualizarEntidade(solicitacao);
                adicionarGatilhoExecucaoFinalComSucesso(FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO, notificacao);
                dialogo = CarameloCode.getServicoComunicacao().getArmazenamento().getDialogoAtivoByCodigoSelo(solicitacao.getCodigoSelo());

                NotificacaoSB ntf = (NotificacaoSB) solicitacao.getCPinst(CPSolicitacao.notificacao).getValor();
                if (ntf != null && !ntf.getDisparos().isEmpty()) {
                    ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto().registrarReciboLeitura(ntf.getDisparos().get(0).getCodigoRegistroEnvio(), UtilCRCStringGerador.getStringRandomicaUUID());
                }
                setUrlDestinoSucesso(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE, CarameloCode.getUsuarioLogado()));

            }
        }.getResposta();

    }

}
