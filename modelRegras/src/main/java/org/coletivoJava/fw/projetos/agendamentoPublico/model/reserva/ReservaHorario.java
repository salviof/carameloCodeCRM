/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoFormLead.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Reserva de Horário", plural = "Reservas de Horário", icone = "fa fa-ticket")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoReservaHorario")
@EntityListeners(ListenerEntidadePadrao.class)
public class ReservaHorario extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "descricaoReserva")
    private String descricao;
    //  @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, somenteLeitura = true)
    //  @Temporal(TemporalType.TIMESTAMP)
    //  private Date inicioReservaAtendido;
    // @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, somenteLeitura = true)
    // @Temporal(TemporalType.TIMESTAMP)
    //private Date finalReservaAtendido;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, somenteLeitura = true, obrigatorio = true)
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampoValidadorLogico()
    private Date inicioReservaAtendente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, somenteLeitura = true, obrigatorio = true)
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampoValidadorLogico()
    private Date finalReservaAtendente;

    @ManyToOne(targetEntity = EscopoPesquisaMelhorHorario.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Escopo Origem")
    private EscopoPesquisaMelhorHorario escopoOrigem;

    @InfoCampoValorLogico(nomeCalculo = "Valor Moeda")
    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorReserva;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoReservaHorario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "codigoReserva")
    private String codigoReserva;

    @ManyToOne(targetEntity = StatusReserva.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private StatusReserva status;

    @ManyToOne(targetEntity = UsuarioCrmCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private UsuarioCrmCliente atendidoResponsavel;

    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.REFRESH)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValidadorLogico()
    private Pessoa pessoaRelacionada;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contatosAtendidos_reservas",
            joinColumns = @JoinColumn(name = "reservaHorario_id"),
            inverseJoinColumns = @JoinColumn(name = "contatoatendido_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "pessoaRelacionada.contatosProspecto")
    private List<ContatoProspecto> contatosAtendidos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reserva_atendidos",
            joinColumns = @JoinColumn(name = "reservaHorario_id"),
            inverseJoinColumns = @JoinColumn(name = "atendido_id"))
    private List<UsuarioCrmCliente> atendidos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reserva_atendente",
            joinColumns = @JoinColumn(name = "reservaHorario_id"),
            inverseJoinColumns = @JoinColumn(name = "atendente_id"))
    private List<UsuarioSB> atendentes;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private UsuarioSB atendenteResponsavel;

    @ManyToOne(targetEntity = TipoAgendamentoAtdmPublico.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private TipoAgendamentoAtdmPublico tipoAgendamento;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivelParaConfirmação")
    private boolean disponivelParaConfirmacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    private boolean ativo;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ContatoAnonimoDadoTansitorio dadosContatoAcessoAnonimo;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = HorarioDisponivelAtendimentoPublico.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        HorarioDisponivelAtendimentoPublico pHorario = getParametroInicialEnviado(HorarioDisponivelAtendimentoPublico.class, parametros);
        setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
        setTipoAgendamento(pHorario.getTipoAgendamento());
        setAtendenteResponsavel(pHorario.getUsuarioResponsavel());
        setInicioReservaAtendente(pHorario.getDataHoraIicialAtendente());
        setFinalReservaAtendente(pHorario.getDatahoraFinalAtendente());
        setDadosContatoAcessoAnonimo(new ContatoAnonimoDadoTansitorio());
        if (!getTipoAgendamento().isUmAtendimentoRemoto()) {
            if (getTipoAgendamento().getComoReservaPresencial().getLocalPadraoReuniao() == null) {
                getComoReservaPresencial().setTextoLocalizacao(getTipoAgendamento().getComoReservaPresencial().getTextoLocalizacaoReuniaoInSide());
            } else {
                getComoReservaPresencial().setLocalizacao(getComoReservaPresencial().getLocalizacao());
            }
        }

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicioReservaAtendente() {
        return inicioReservaAtendente;
    }

    public void setInicioReservaAtendente(Date inicioReservaAtendente) {
        this.inicioReservaAtendente = inicioReservaAtendente;
    }

    public Date getFinalReservaAtendente() {
        return finalReservaAtendente;
    }

    public void setFinalReservaAtendente(Date finalReservaAtendente) {
        this.finalReservaAtendente = finalReservaAtendente;
    }

    public String getTipoReservaHorario() {
        return tipoReservaHorario;
    }

    public UsuarioCrmCliente getAtendidoResponsavel() {
        return atendidoResponsavel;
    }

    public void setAtendidoResponsavel(UsuarioCrmCliente atendidoResponsavel) {
        this.atendidoResponsavel = atendidoResponsavel;
    }

    public List<UsuarioCrmCliente> getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(List<UsuarioCrmCliente> atendidos) {
        this.atendidos = atendidos;
    }

    public List<UsuarioSB> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<UsuarioSB> atendentes) {
        this.atendentes = atendentes;
    }

    public UsuarioCRM getAtendenteResponsavel() {
        return (UsuarioCRM) atendenteResponsavel;
    }

    public void setAtendenteResponsavel(UsuarioSB atendenteResponsavel) {
        this.atendenteResponsavel = atendenteResponsavel;
    }

    public TipoAgendamentoAtdmPublico getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamentoAtdmPublico tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ReservaHoraPresencial getComoReservaPresencial() {
        return (ReservaHoraPresencial) this;
    }

    public ReservaHoraRemotoVideo getComoReservaVideoConferencia() {
        return (ReservaHoraRemotoVideo) this;
    }

    public Pessoa getPessoaRelacionada() {
        return pessoaRelacionada;
    }

    public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
        this.pessoaRelacionada = pessoaRelacionada;
    }

    public List<ContatoProspecto> getContatosAtendidos() {
        return contatosAtendidos;
    }

    public void setContatosAtendidos(List<ContatoProspecto> contatosAtendidos) {
        this.contatosAtendidos = contatosAtendidos;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public double getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }

    public EscopoPesquisaMelhorHorario getEscopoOrigem() {
        return escopoOrigem;
    }

    public void setEscopoOrigem(EscopoPesquisaMelhorHorario escopoOrigem) {
        this.escopoOrigem = escopoOrigem;
    }

    public boolean isDisponivelParaConfirmacao() {
        return disponivelParaConfirmacao;
    }

    public void setDisponivelParaConfirmacao(boolean disponivelParaConfirmacao) {
        this.disponivelParaConfirmacao = disponivelParaConfirmacao;
    }

    public ContatoAnonimoDadoTansitorio getDadosContatoAcessoAnonimo() {
        return dadosContatoAcessoAnonimo;
    }

    public void setDadosContatoAcessoAnonimo(ContatoAnonimoDadoTansitorio dadosContatoAcessoAnonimo) {
        this.dadosContatoAcessoAnonimo = dadosContatoAcessoAnonimo;
    }

}
