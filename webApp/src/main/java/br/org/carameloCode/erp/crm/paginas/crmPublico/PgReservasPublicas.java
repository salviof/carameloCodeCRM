/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmPublico;

import br.org.carameloCode.erp.crm.paginas.crmAgenda.ItfPaginaListaDeHorariosDisponiveis;
import br.org.carameloCode.erp.crm.paginas.crmCliente.PgReservasCliente;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.InfoAcaoaAcessoAnonimoCRM;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.ErroAcidenteDeLorean;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgendaPublica.ModuloCRMAgendaPublica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRMLead;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Named
@InfoPagina(nomeCurto = "ReservaPulica", tags = {"Reservas Publicas"})
@ViewScoped
@InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_MB_RESERVAS)
public class PgReservasPublicas extends MB_paginaCadastroEntidades<ReservaHorarioCRM> implements ItfPaginaListaDeHorariosDisponiveis {

    private ContatoAnonimoDadoTansitorio novoContato = new ContatoAnonimoDadoTansitorio();

    @InfoParametroURL(nome = "Token", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = TokenAcessoDinamico.class)
    private ParametroURL parametroToken;

    @InfoParametroURL(nome = "Escopo", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = EscopoPesqHorarioPublicado.class)
    private ParametroURL parametroEscopo;

    private int step = 0; // 0 = CONTATO - 1 = EMPRESA = 2 - OBSERVACAO

    private HorarioDisponivelAtendimentoPublico horario;

    private AgendaDisponibilidade agendaDisponibilidade;

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada();
    }

    @Override
    public void setEntidadeSelecionada(ReservaHorarioCRM entidadeSelecionada) {
        if (entidadeSelecionada != null) {
            entidadeSelecionada.setDadosContatoAcessoAnonimo(novoContato);

        }
        super.setEntidadeSelecionada(entidadeSelecionada);

    }

    private EscopoPesquisaMelhorHorario escopoPesquisa;

    @Override
    public EscopoPesquisaMelhorHorario getEscopoPesquisa() {
        TokenAcessoDinamico token = (TokenAcessoDinamico) getParametroInstanciado(parametroToken).getValor();
        EscopoPesqHorarioPublicado escopoPublico = (EscopoPesqHorarioPublicado) getParametroInstanciado(parametroEscopo).getValor();
        if (escopoPublico != null && escopoPublico.getId() != Long.valueOf(token.getCodigoEntidade())) {
            escopoPublico = null;
            System.out.println("Token não enviado, acesso negado");
            executaAcaoSelecionadaPorEnum(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM);
        }
        if (escopoPublico != null) {

            escopoPesquisa = UtilSBPersistencia.loadEntidade(escopoPublico, getEMPagina());

        } else {
            System.out.println("Escopo não enviado, acesso negado");
            executaAcaoSelecionadaPorEnum(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM);
        }
        return escopoPesquisa;
    }

    @PostConstruct
    public void inicio() {

        if (CarameloCode.getServicoSessao().getSessaoAtual().getUsuario() instanceof UsuarioCRMLead) {

            String celInternancional = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(CarameloCode.getUsuarioLogado().getTelefone());
            if (celInternancional == null) {
                CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Número de telefone inválido");
            }
            novoContato.setNomeUsuario(((UsuarioCRMLead) CarameloCode.getUsuarioLogado()).getNomeUsuario());
            novoContato.setCelular(CarameloCode.getUsuarioLogado().getTelefone());

            ItfRespostaAcaoDoSistema resposta = ModuloCRMAgendaPublica.validadarDadosCanidatoAgendaPublica((UsuarioCRMLead) CarameloCode.getUsuarioLogado());
            if (resposta.getAcaoProximoFormulario() != null) {
                executaAcaoSelecionadaPorEnum(resposta.getAcaoProximoFormulario().getEnumAcaoDoSistema());
                setEntidadeSelecionada((ReservaHorarioCRM) resposta.getRetorno());
            }
        } else {
            executaAcaoSelecionadaPorEnum(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS);
        }

    }

    public AgendaDisponibilidade getAgendaDisponibilidade() {

        if (agendaDisponibilidade == null) {
            System.out.println("Obtendo AgendaDisponibilidade Publica");
            agendaDisponibilidade = new AgendaDisponibilidade(getEscopoPesquisa());
            agendaDisponibilidade.setUsuarioAtendente(getUsuarioAtendente());
            agendaDisponibilidade.setTipoAgendamento(getTipoAgendamento());
            try {
                agendaDisponibilidade.loadgendaXDias();
            } catch (ErroAcidenteDeLorean ex) {
                CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Os horários disponíveis para este token se esgotaram.");
            }
        }

        return agendaDisponibilidade;
    }

    public void setAgendaDisponibilidade(AgendaDisponibilidade agendaDisponibilidade) {
        this.agendaDisponibilidade = agendaDisponibilidade;
    }

    public ContatoAnonimoDadoTansitorio getNovoContato() {
        return novoContato;
    }

    public HorarioDisponivelAtendimentoPublico getHorario() {
        return horario;
    }

    @Override
    public HorarioDisponivelAtendimentoPublico getHorarioDisponivelSelecionado() {

        return horario;
    }

    @Override
    public void setHorarioDisponivelSelecionado(HorarioDisponivelAtendimentoPublico pHorarioDisponivel) {

        if (pHorarioDisponivel.getTipoAgendamento().isUmAtendimentoRemoto()) {
            setEntidadeSelecionada(new ReservaHoraRemotoVideo());
            try {
                getEntidadeSelecionada().prepararNovoObjeto(pHorarioDisponivel);
                // getEntidadeSelecionada().setPessoaRelacionada(usuarioLogado.getRepresentanteLegal());
                // getEntidadeSelecionada().setAtendidoResponsavel(usuarioLogado);
                getEntidadeSelecionada().setAtendidos(new ArrayList<>());
                // getEntidadeSelecionada().getAtendidos().add(usuarioLogado);
                getEntidadeSelecionada().setContatosAtendidos(new ArrayList());
                //   getEntidadeSelecionada().getContatosAtendidos().add(usuarioLogado.getContatoClienteVinculado());
                horario = pHorarioDisponivel;
            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(PgReservasCliente.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            setEntidadeSelecionada(new ReservaHoraPresencial());
            try {
                getEntidadeSelecionada().prepararNovoObjeto(pHorarioDisponivel);
                //   getEntidadeSelecionada().setPessoaRelacionada(usuarioLogado.getRepresentanteLegal());
                //     getEntidadeSelecionada().setAtendidoResponsavel(usuarioLogado);
                getEntidadeSelecionada().setAtendidos(new ArrayList<>());
                //       getEntidadeSelecionada().getAtendidos().add(usuarioLogado);
                getEntidadeSelecionada().setContatosAtendidos(new ArrayList());
                //      getEntidadeSelecionada().getContatosAtendidos().add(usuarioLogado.getContatoClienteVinculado());
                //      horarioDisponivelSelecionado = pHorarioDisponivel;
                horario = pHorarioDisponivel;
            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(PgReservasCliente.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaConferenciaRemota() {
        return FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_MEUS_DADOS.getRegistro().getComoFormulario();
    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaVisitaLocal() {
        return FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_MEUS_DADOS.getRegistro().getComoFormulario();
    }

    public void proximoStep() {
        if (step < 3) {
            step++;
        }
    }

    public void voltarStep() {
        if (step > 0) {
            step--;
        }
    }

    public void goToStep(int index) {
        step = index;
    }

    public int getStep() {
        return step;
    }

    @Override
    public ComoUsuario getUsuarioAtendente() {
        return getEscopoPesquisa().getAtendentes().get(0);
    }

    @Override
    public ComoUsuario getUsuarioAtendedido() {
        return null;
    }
    private TipoAgendamentoAtdmPublico tipoAgendamento;

    @Override
    public TipoAgendamentoAtdmPublico getTipoAgendamento() {
        if (tipoAgendamento == null && getEscopoPesquisa().getTiposAgendamentosDisponiveis().size() == 1) {
            tipoAgendamento = UtilSBPersistencia.loadEntidade(getEscopoPesquisa().getTiposAgendamentosDisponiveis().get(0), getEMPagina());
        }
        return getEscopoPesquisa().getTiposAgendamentosDisponiveis().get(0);
    }
}
