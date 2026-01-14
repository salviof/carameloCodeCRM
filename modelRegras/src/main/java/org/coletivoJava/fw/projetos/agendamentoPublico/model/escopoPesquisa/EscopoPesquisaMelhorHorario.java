/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ContextoReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabContextoDeReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Escopo Melhor Horário", tags = "Escopos de pesquisa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEscopo")
@EntityListeners(ListenerEntidadePadrao.class)
public class EscopoPesquisaMelhorHorario extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escopo_etendentes", uniqueConstraints = @UniqueConstraint(columnNames = {"escopo_id", "atendente_id"}),
            joinColumns = @JoinColumn(name = "escopo_id"),
            inverseJoinColumns = @JoinColumn(name = "atendente_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "atendentesPossiveis")
    private List<UsuarioSB> atendentes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "listaAtendentesPOssiveis")
    private List<UsuarioSB> atendentesPossiveis;

    @ManyToOne(targetEntity = TipoAgendamentoAtdmPublico.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoAgendamentoAtdmPublico tipoAgendamento;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escopo_disponivel",
            joinColumns = @JoinColumn(name = "escopo_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoReserva_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Tipos de Reservas Disponíveis")
    private List<TipoAgendamentoAtdmPublico> tiposAgendamentosDisponiveis;

    @ManyToOne(targetEntity = ContextoReserva.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ContextoReserva contexto = FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro();

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int numeroOpcoes = 9;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, nomeOrigem = "Máximo Reservas Permitidas")
    private int qtdMaximoReservas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, nomeOrigem = "Reservas Realizadas pelo escopo")
    @InfoCampoValorLogico(nomeCalculo = "Reservas realizadas")
    private int qtdReservasRealizadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    @InfoCampoValorLogico(nomeCalculo = "Reservas disponíveis")
    private int qtdReservasDisponiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, somenteLeitura = false)
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampoValidadorLogico()
    @InfoCampoValorLogico(nomeCalculo = "Data inicial", somenteLeitura = false)
    private Date dataInicial = new Date();

    @InfoCampo(tipo = FabTipoAtributoObjeto.HORA, label = "Horário Início")
    @Temporal(TemporalType.TIME)
    private Date horarioinicio;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HORA, label = "Horário Final")
    @Temporal(TemporalType.TIME)
    private Date horarioFinal;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEscopo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String diasDaSemana = "1111111";

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Lista disponibilidade", atualizarSempreQueSalvar = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<HorarioDisponivelAtendimentoPublico> listaHorariosDisponiveis;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Disponibilidae do escopo", atualizarSempreQueSalvar = false)
    private List<DisponibilidadeAtdmtPublico> disponibilidadesDoEscopo;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Máximo pesquisa")
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date datahoraMaximoPesquisa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    @InfoCampoValorLogico(nomeCalculo = "Ativo")
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso(iconeNegativo = "fa fa-times text-danger", iconePostivio = "fa fa-check text-success")
    private boolean ativo = false;

    @ManyToOne(targetEntity = TokenAcessoDinamico.class)
    @InfoCampo(label = "Token de acesso")
    @InfoCampoValorLogico(nomeCalculo = "Token")
    private TokenAcessoDinamico tokenDeAcesso;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na segunda", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaSegunda;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na terça", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaTerca;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na quarta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaQuarta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na quinta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaQuinta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na sexta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaSexta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel no Sábado", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaSabado;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel no domingo", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean diaSemanaDomingo;

    public EscopoPesquisaMelhorHorario() {
        super();
        setDataInicial(new Date());

    }

    public TokenAcessoDinamico getTokenDeAcesso() {
        return tokenDeAcesso;
    }

    public void setTokenDeAcesso(TokenAcessoDinamico tokenDeAcesso) {
        this.tokenDeAcesso = tokenDeAcesso;
    }

    public TipoAgendamentoAtdmPublico getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamentoAtdmPublico tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;

    }

    public List<HorarioDisponivelAtendimentoPublico> getListaHorariosDisponiveis() {
        return listaHorariosDisponiveis;
    }

    public void setListaHorariosDisponiveis(List<HorarioDisponivelAtendimentoPublico> listaHorariosDisponiveis) {
        this.listaHorariosDisponiveis = listaHorariosDisponiveis;
    }

    public List<UsuarioSB> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<UsuarioSB> atendentes) {
        this.atendentes = atendentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroOpcoes() {
        return numeroOpcoes;
    }

    public void setNumeroOpcoes(int numeroOpcoes) {
        this.numeroOpcoes = numeroOpcoes;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;

    }

    public Date getHorarioinicio() {
        return horarioinicio;
    }

    public void setHorarioinicio(Date horarioinicio) {
        this.horarioinicio = horarioinicio;
    }

    public Date getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getTipoEscopo() {
        return tipoEscopo;
    }

    public void setTipoEscopo(String tipoEscopo) {
        this.tipoEscopo = tipoEscopo;
    }

    public void adicionarAtendente(UsuarioSB pUsuario) {
        atendentes = new ArrayList<>();
        if (!atendentes.contains(pUsuario)) {
            atendentes.add(pUsuario);
        }
    }

    public String getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(String diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public List<DisponibilidadeAtdmtPublico> getDisponibilidadesDoEscopo() {
        return disponibilidadesDoEscopo;
    }

    public void setDisponibilidadesDoEscopo(List<DisponibilidadeAtdmtPublico> disponibilidadesDoEscopo) {
        this.disponibilidadesDoEscopo = disponibilidadesDoEscopo;
    }

    public Date getDatahoraMaximoPesquisa() {
        return datahoraMaximoPesquisa;
    }

    public void setDatahoraMaximoPesquisa(Date datahoraMaximoPesquisa) {
        this.datahoraMaximoPesquisa = datahoraMaximoPesquisa;
    }

    public ContextoReserva getContexto() {
        return contexto;
    }

    public void setContexto(ContextoReserva contexto) {
        this.contexto = contexto;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdMaximoReservas() {
        return qtdMaximoReservas;
    }

    public void setQtdMaximoReservas(int qtdMaximoReservas) {
        this.qtdMaximoReservas = qtdMaximoReservas;
    }

    public int getQtdReservasDisponiveis() {
        return qtdReservasDisponiveis;
    }

    public void setQtdReservasDisponiveis(int qtdReservasDisponiveis) {
        this.qtdReservasDisponiveis = qtdReservasDisponiveis;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<TipoAgendamentoAtdmPublico> getTiposAgendamentosDisponiveis() {
        return tiposAgendamentosDisponiveis;
    }

    public void setTiposAgendamentosDisponiveis(List<TipoAgendamentoAtdmPublico> tiposAgendamentosDisponiveis) {
        this.tiposAgendamentosDisponiveis = tiposAgendamentosDisponiveis;
    }

    public int getQtdReservasRealizadas() {
        return qtdReservasRealizadas;
    }

    public void setQtdReservasRealizadas(int qtdReservasRealizadas) {
        this.qtdReservasRealizadas = qtdReservasRealizadas;
    }

    public boolean isDiaSemanaSegunda() {
        return diaSemanaSegunda;
    }

    public void setDiaSemanaSegunda(boolean diaSemanaSegunda) {
        this.diaSemanaSegunda = diaSemanaSegunda;
    }

    public boolean isDiaSemanaTerca() {
        return diaSemanaTerca;
    }

    public void setDiaSemanaTerca(boolean diaSemanaTerca) {
        this.diaSemanaTerca = diaSemanaTerca;
    }

    public boolean isDiaSemanaQuarta() {
        return diaSemanaQuarta;
    }

    public void setDiaSemanaQuarta(boolean diaSemanaQuarta) {
        this.diaSemanaQuarta = diaSemanaQuarta;
    }

    public boolean isDiaSemanaQuinta() {
        return diaSemanaQuinta;
    }

    public void setDiaSemanaQuinta(boolean diaSemanaQuinta) {
        this.diaSemanaQuinta = diaSemanaQuinta;
    }

    public boolean isDiaSemanaSexta() {
        return diaSemanaSexta;
    }

    public void setDiaSemanaSexta(boolean diaSemanaSexta) {
        this.diaSemanaSexta = diaSemanaSexta;
    }

    public boolean isDiaSemanaSabado() {
        return diaSemanaSabado;
    }

    public void setDiaSemanaSabado(boolean diaSemanaSabado) {
        this.diaSemanaSabado = diaSemanaSabado;
    }

    public boolean isDiaSemanaDomingo() {
        return diaSemanaDomingo;
    }

    public void setDiaSemanaDomingo(boolean diaSemanaDomingo) {
        this.diaSemanaDomingo = diaSemanaDomingo;
    }

    public List<UsuarioSB> getAtendentesPossiveis() {
        return atendentesPossiveis;
    }

    public void setAtendentesPossiveis(List<UsuarioSB> atendentesPossiveis) {
        this.atendentesPossiveis = atendentesPossiveis;
    }

    public EscopoPesqHorarioPublicado getComoEscopoPublicado() {
        return (EscopoPesqHorarioPublicado) this;
    }
}
