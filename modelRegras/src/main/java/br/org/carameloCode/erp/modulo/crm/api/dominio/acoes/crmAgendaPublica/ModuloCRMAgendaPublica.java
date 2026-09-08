package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgendaPublica;

import br.org.carameloCode.erp.modulo.agenda.api.model.reservahorario.CPReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.FabStatusReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.StatusReserva;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.FabAcaoAgendaMentoPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.InfoAcaoAgendamentoPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.ModuloAgendamentoPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.InfoAcaoaAcessoAnonimoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.CPPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.TipoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRMLead;
import br.org.carameloCode.erp.modulo.notificacao.api.ERPNotificacoes;
import br.org.carameloCode.erp.modulo.notificacao.api.ErroGerandoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.api.FabAcaoNotificacaoPadraoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoUsrParaUsr;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.google.common.collect.Lists;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.ServicoNotificacao;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author salvio
 */
public class ModuloCRMAgendaPublica extends ControllerAbstratoSBPersistencia {

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_ATIVAR)
    public static ItfRespostaAcaoDoSistema validadarDadosCanidatoAgendaPublica(final UsuarioCRMLead pUsuario) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pUsuario), pUsuario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                boolean formulariodefinido = false;
                String celular = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(pUsuario.getTelefone());
                if (celular != null) {
                    ContatoProspecto contatoExistente = (ContatoProspecto) UtilSBPersistencia.gerarConsultaDeEntidade(ContatoProspecto.class, getEMResposta())
                            .addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, celular).getPrimeiroRegistro();
                    if (contatoExistente != null) {
                        List<ReservaHorarioCRM> reservas = new ConsultaDinamicaDeEntidade(ReservaHorarioCRM.class, getEm()).addCondicaoManyToOneIgualA(CPReservaHorario.atendidoresponsavel, contatoExistente.getUsuarioVinculado())
                                .addCondicaoManyToOneContemNoIntervalo(CPReservaHorario.status, Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro()))
                                .addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, new Date())
                                .gerarResultados();
                        if (!reservas.isEmpty()) {
                            System.out.println("Econtrou reserva definida e Apresentou tela de reserva publica concluida");
                            setRetorno(reservas.get(0));
                            setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_RESERVA_CONCLUIDA.getRegistro().getComoFormulario());
                            formulariodefinido = true;
                        } else {
                            formulariodefinido = true;
                            setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_INFORME_ACESSO_AREA_CLIENTE.getRegistro().getComoFormulario());
                        }
                    } else {

                    }

                }
                if (!formulariodefinido) {
                    if (CarameloCode.getUsuarioLogado() instanceof UsuarioCRMLead) {
                        setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS.getRegistro().getComoFormulario());
                    } else {
                        setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS.getRegistro().getComoFormulario());
                        // Permitir uso de token sem envio de dados do lead?
                        // setProximoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario());

                    }
                }

            }
        }.getResposta();

    }

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_ATIVAR)
    public static ItfRespostaAcaoDoSistema escopoPublicoAtivar(final EscopoPesqHorarioPublicado pEscopo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(ModuloAgendamentoPublico.escopoPublicoSalvarMerge(pEscopo), pEscopo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                EscopoPesqHorarioPublicado escopo = loadEntidade(pEscopo);
                if (escopo.getTipoEscopo() == null) {
                    throw new ErroRegraDeNegocio("Defina o tipo de agendamento");
                }
                if (escopo.getAtendentes() == null) {
                    throw new ErroRegraDeNegocio("Defina os atendentes disponíveis");
                }
                if (pEscopo.getDataHoraTokenPublicoExpira() == null) {
                    throw new ErroRegraDeNegocio("Defina a data de expiração do token");
                }
                if (UtilCRCDataHora.intervaloTempoHoras(new Date(), escopo.getDataHoraTokenPublicoExpira()) < 5) {
                    throw new ErroRegraDeNegocio("O token precisa ter uma validade mínima de 5 horas");
                }

                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS,
                        pEscopo, null);
                escopo.setTokenPublicado(token);
                escopo.setPublicado(true);
                String url = CarameloCode.getServicoVisualizacao()
                        .getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);
                url = url.replace("crm.", "atendimento.");
                //MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                escopo.setLinkDeAcesso(url);
                atualizarEntidade(escopo);
            }
        }.getResposta();
    }

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema reservaPubHorarioCancelar(ReservaHorarioCRM pReserva) {

        final String nomeCliente;
        final String tipoChamado;
        if (pReserva.getPessoaRelacionada() == null) {
            nomeCliente = "Cliente indefinodo";
            tipoChamado = "Tipo Agenda indefinida";
        } else {
            nomeCliente = pReserva.getPessoaRelacionada().getNome();
            tipoChamado = pReserva.getTipoAgendamento().getNome();
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                try {
                    if (isSucesso()) {
                        MapaHorariosDisponiveis.removerReservaAtendente((ReservaHorario) getRetorno());
                        ServicoNotificacao.notificarReservaAtendente(FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU, pReserva);
                    }
                } catch (Throwable t) {

                }

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                ReservaHorario reserva = loadEntidade(pReserva);

                if (reserva.getStatus().equals(FabStatusReservaHorario.REALIZADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status não é compatível");
                }
                if (reserva.getStatus().equals(FabStatusReservaHorario.CONFIRMADO.getRegistro())) {
                    addAlerta("Esta reserva já foi confirmada, por favor clique em cancelar novamente, para confirmar sua ação.");
                    reserva.setStatus((StatusReserva) FabStatusReservaHorario.AGENDADO.getRegistro());
                } else {
                    setRetorno(reserva);
                    removerEntidade(reserva);
                    setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS.getRegistro().getComoFormulario());
                }

            }
        }.getResposta();

    }

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CONFIRMAR)
    public static ItfRespostaAcaoDoSistema reservaConfirmar(ReservaHorarioCRM pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHorario reserva = loadEntidade(pReserva);
                reserva.setStatus(FabStatusReservaHorario.CONFIRMADO.getRegistro());
                try {
                    ServicoNotificacao.notificarReservaAtendente(FabTipoNotificacao.NOTIFICAR_SOLICITACAO_CONFIRMADA_CLIENTE, pReserva);
                } catch (Throwable t) {

                }
                atualizarEntidade(reserva);
            }
        }.getResposta();

    }

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_RESERVAR)
    public static ItfRespostaAcaoDoSistema reservaPublicaReservar(ReservaHorarioCRM pReserva) {

        ItfRespostaAcaoDoSistema respCadastroContato = new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                    throw new ErroRegraDeNegocio("Os dados do contato não foram enviados");
                }
                String nomeEmpresa = pReserva.getDadosContatoAcessoAnonimo().getNomeEmpresa();
                String nomeContato = pReserva.getDadosContatoAcessoAnonimo().getNomeUsuario();

                if (nomeEmpresa == null || nomeEmpresa.isEmpty()) {
                    nomeEmpresa = "Empresa em nome de " + nomeContato;
                }

                if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                    throw new ErroRegraDeNegocio("Defina os dados do contato");
                }

                String emailContatp = pReserva.getDadosContatoAcessoAnonimo().getEmail();
                String telefone = pReserva.getDadosContatoAcessoAnonimo().getCelular();
                if (telefone == null) {
                    throw new ErroRegraDeNegocio("Informe o celular");
                }

                String site = pReserva.getDadosContatoAcessoAnonimo().getSite();
                String celInternancional = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
                ContatoProspecto contatoExistente = (ContatoProspecto) UtilSBPersistencia.gerarConsultaDeEntidade(ContatoProspecto.class, getEm()).addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, celInternancional).getPrimeiroRegistro();

                PessoaJuridica pessoa = null;
                if (contatoExistente != null) {
                    pessoa = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, contatoExistente.getProspecto().getId(), getEMResposta());
                    ModuloCRMAtendimento.prospectoCriarUsuarios(pessoa);
                } else {
                    //campos = {
                    //"[separador: Cadastro básico]",
                    //"nome", "site", "umPerfilPrivado", "telefonePrincipal",
                    //"[separador: Dados  Básicos de Marketing]",
                    //"origem", "tipoEmpresa", "porte",
                    //"[separador: Contato]", "responsavel", "contatoPrincipal.email", "contatoPrincipal.celular",
                    //  "[separador: Obeservações]", "observacao"
                    //}
                    pessoa = new PessoaJuridica();
                    try {

                        pessoa.setUsuarioAtendimento((UsuarioCRM) pReserva.getAtendenteResponsavel());
                        pessoa.getCPinst(CPPessoa.nome).setValorSeValido(nomeContato);
                        if (site != null && !site.isEmpty()) {
                            pessoa.getCPinst(CPPessoaJuridica.site).setValorSeValido(site);
                        }
                        pessoa.setOrigem((OrigemProspecto) UtilSBPersistencia.getRegistroByID(OrigemProspecto.class,
                                5l));
                        pessoa.setPorte((Porte) UtilSBPersistencia.getRegistroByID(Porte.class,
                                4l));
                        pessoa
                                .setTipoEmpresa((TipoEmpresa) UtilSBPersistencia.getRegistroByID(TipoEmpresa.class,
                                        4l));
                    } catch (ErroValidacao ex) {
                        throw new ErroRegraDeNegocio(ex.getMessage());
                    }
                    pessoa.setObservacao(pReserva.getDadosContatoAcessoAnonimo().getObservacao());
                    try {
                        pessoa.getCPinst(CPPessoa.responsavel).setValorSeValido(nomeContato);
                        ContatoProspecto novoContato = (ContatoProspecto) pessoa.getCPinst(CPPessoa.contatoprincipal).getValor();
                        novoContato.getCPinst(CPContatoProspecto.email).setValorSeValido(emailContatp);

                        novoContato.getCPinst(CPContatoProspecto.celular).setValorSeValido(telefone);

                    } catch (ErroValidacao ex) {
                        throw new ErroRegraDeNegocio(ex.getMensagemAoUsuario());
                    }

                }
                if (pessoa.getId() == null) {
                    ItfRespostaAcaoDoSistema respostaCadastroNovoProsp = ModuloCRMAtendimento.prospectoSalvar(pessoa);
                    setRetorno(respostaCadastroNovoProsp.getRetorno());
                    if (!respostaCadastroNovoProsp.isSucesso()) {
                        addErro(respostaCadastroNovoProsp.getMensagens().get(0).getMenssagem());
                    }
                } else {
                    setRetorno(pessoa);
                }

            }
        }.getResposta();
        if (respCadastroContato.isSucesso()) {
            return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {
                    if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                        throw new ErroRegraDeNegocio("Defina os dados do contato");
                    }
                    Pessoa pessoa = loadEntidade((Pessoa) respCadastroContato.getRetorno());
                    pReserva.setPessoaRelacionada(pessoa);
                    ContatoProspecto ct = (ContatoProspecto) pessoa.getCPinst(CPPessoa.contatoprincipal).getValor();
                    if (ct.getCPinst("usuarioVinculado").getValor() == null) {
                        throw new ErroRegraDeNegocio("usuário vinculado não foi encontrado");
                    }
                    pReserva.setAtendidoResponsavel(pessoa.getContatoPrincipal().getUsuarioVinculado());
                    ReservaHorario reserva = atualizarEntidade(pReserva);
                    setRetorno(reserva);
                    setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_RESERVA_CONCLUIDA.getRegistro().getComoFormulario());

                    NotificacaoSB notificacaoAtendente;
                    try {

                        notificacaoAtendente = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                                .gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA.getRegistro(getEm()),
                                        reserva.getAtendenteResponsavel(),
                                        reserva);
                        adicionarNotificacoesSucesso(notificacaoAtendente);

                    } catch (ErroGerandoNotificacao ex) {
                        addAviso("Falha registrando notificação do atendente");
                    }
                    NotificacaoSB notificacaoCliente;
                    try {

                        notificacaoCliente = ERPNotificacoes.NOTIFICACAO_PADRAO.getImplementacaoDoContexto()
                                .gerarNotificacao((TipoNotificacao) FabTipoNotificacao.NOTIFICAR_CLIENTE_AGENDA_REUNIAO.getRegistro(getEm()),
                                        reserva.getAtendidoResponsavel(),
                                        reserva);
                        adicionarNotificacoesSucesso(notificacaoCliente);
                    } catch (ErroGerandoNotificacao ex) {
                        addAviso("Falha registrando notificações");
                    }

                }
            }.getResposta();
        } else {
            respCadastroContato.setRetorno(null);
            respCadastroContato.dispararMensagens();
            return respCadastroContato;
        }

    }

}
