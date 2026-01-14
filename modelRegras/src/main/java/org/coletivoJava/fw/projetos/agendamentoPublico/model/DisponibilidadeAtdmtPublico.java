/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.sql.Time;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Disponibilidade Atendimento Público", plural = "Disponibilidades de Atendimento", icone = "fa fa-calendar")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDisponibilidade")
@EntityListeners(ListenerEntidadePadrao.class)
public class DisponibilidadeAtdmtPublico extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValidadorLogico
    private String diasDaSemana = "1111110";

    @InfoCampo(tipo = FabTipoAtributoObjeto.HORA, obrigatorio = true)
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HORA, obrigatorio = true)
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioFim;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome = "Pesquisa exemplo";

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na segunda", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaSegunda;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na terça", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaTerca;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na quarta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaQuarta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na quinta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaQuinta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel na sexta", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaSexta;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel no Sábado", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaSabado;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "disponivel no domingo", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean diaSemanaDomingo;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA, obrigatorio = true)
    @Column(nullable = false)
    private Date diaInicial;
    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA, obrigatorio = true)
    @Column(nullable = false)
    private Date diaFinal;

    @Column(nullable = false, updatable = false, insertable = false, length = 500)
    private String tipoDisponibilidade;

    @ManyToOne(targetEntity = Localizacao.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Localizacao local;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "diponibilidade_tipoAtendimento",
            uniqueConstraints = @UniqueConstraint(columnNames = {"disponibilidade_id", "tipoAtendimento_id"}),
            joinColumns = @JoinColumn(name = "disponibilidade_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoAtendimento_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<TipoAgendamentoAtdmPublico> tiposAgendamentosPublicos;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsuarioSB.class)
    @JoinTable(name = "disponibilidade_atendentes",
            uniqueConstraints = @UniqueConstraint(columnNames = {"disponibilidade_id", "atendente_id"}),
            joinColumns = @JoinColumn(name = "disponibilidade_id"),
            inverseJoinColumns = @JoinColumn(name = "atendente_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "listaDeAtendentesPossiveis")
    private List<UsuarioSB> atendentesDisponiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "listaAtendentesPOssiveis")
    private List<UsuarioSB> listaDeAtendentesPossiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioResponsavel;

    @InfoCampo(descricao = "Exemplo 30 dias são 30*24= (729 horas)")
    private int distanciaDoFuturoPermitidoEmHoras;

    private boolean reservarLocal = false;
    private boolean reservarAtendente = true;
    private boolean atendimentoRemoto;
    private boolean ignorarFeriados;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorarioInicio() {

        return horarioInicio;
    }

    public Time getHorarioInicioComoTime() {
        return new Time(horarioInicio.getTime());
    }

    public Time getHorarioFinalComoTime() {
        return new Time(horarioFim.getTime());
    }

    public void setHorarioInicio(Date pHorarioInicio) {

        this.horarioInicio = pHorarioInicio;

    }

    public Date getHorarioFim() {

        return horarioFim;

    }

    public void setHorarioFim(Date pHorarioFim) {

        this.horarioFim = pHorarioFim;

    }

    public Date getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(Date diaInicial) {
        this.diaInicial = diaInicial;
    }

    public Date getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(Date diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public List<UsuarioSB> getAtendentesDisponiveis() {
        return atendentesDisponiveis;
    }

    public void setAtendentesDisponiveis(List<UsuarioCRM> atendentesDisponiveis) {
        this.atendentesDisponiveis = (List) atendentesDisponiveis;
    }

    public UsuarioSB getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(UsuarioSB usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public int getDistanciaDoFuturoPermitidoEmHoras() {
        return distanciaDoFuturoPermitidoEmHoras;
    }

    public void setDistanciaDoFuturoPermitidoEmHoras(int distanciaDoFuturoPermitidoEmHoras) {
        this.distanciaDoFuturoPermitidoEmHoras = distanciaDoFuturoPermitidoEmHoras;
    }

    public boolean isReservarLocal() {
        return reservarLocal;
    }

    public void setReservarLocal(boolean reservarLocal) {
        this.reservarLocal = reservarLocal;
    }

    public boolean isReservarAtendente() {
        return reservarAtendente;
    }

    public void setReservarAtendente(boolean reservarAtendente) {
        this.reservarAtendente = reservarAtendente;
    }

    public boolean isAtendimentoRemoto() {
        return atendimentoRemoto;
    }

    public void setAtendimentoRemoto(boolean atendimentoRemoto) {
        this.atendimentoRemoto = atendimentoRemoto;
    }

    public boolean isIgnorarFeriados() {
        return ignorarFeriados;
    }

    public void setIgnorarFeriados(boolean ignorarFeriados) {
        this.ignorarFeriados = ignorarFeriados;
    }

    public List<TipoAgendamentoAtdmPublico> getTiposAgendamentosPublicos() {
        return tiposAgendamentosPublicos;
    }

    public void setTiposAgendamentosPublicos(List<TipoAgendamentoAtdmPublico> tiposAgendamentosPublicos) {
        this.tiposAgendamentosPublicos = tiposAgendamentosPublicos;
    }

    public List<UsuarioSB> getListaDeAtendentesPossiveis() {
        return listaDeAtendentesPossiveis;
    }

    public void setListaDeAtendentesPossiveis(List<UsuarioSB> listaDeAtendentesPossiveis) {
        this.listaDeAtendentesPossiveis = listaDeAtendentesPossiveis;
    }

    public String getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(String diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
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

    public String getTipoDisponibilidade() {
        return tipoDisponibilidade;
    }

    public void setTipoDisponibilidade(String tipoDisponibilidade) {
        this.tipoDisponibilidade = tipoDisponibilidade;
    }

    public void adicionarAtendenteDisponivel(UsuarioSB pUsuario) {
        if (atendentesDisponiveis == null) {
            atendentesDisponiveis = new ArrayList();
        }
        if (!atendentesDisponiveis.contains(pUsuario)) {
            atendentesDisponiveis.add(pUsuario);
        }
    }

    public void adicionarTipoAgendamentoDisponivel(TipoAgendamentoAtdmPublico pTipoAgendamento) {
        if (tiposAgendamentosPublicos == null) {
            tiposAgendamentosPublicos = new ArrayList();
        }
        if (!tiposAgendamentosPublicos.contains(pTipoAgendamento)) {
            tiposAgendamentosPublicos.add(pTipoAgendamento);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuilder identificadorBuilder = new StringBuilder();
        identificadorBuilder.append(super.toString());

        if (getDiaInicial() != null) {
            identificadorBuilder.append(UtilCRCDataHora.getDataHoraString(getDiaInicial(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_SISTEMA));
        }
        if (getDiaFinal() != null) {
            identificadorBuilder.append(UtilCRCDataHora.getDataHoraString(getDiaFinal(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_SISTEMA));
        }
        if (getHorarioInicio() != null) {
            identificadorBuilder.append(UtilCRCDataHora.getDataHoraString(getHorarioInicio(), UtilCRCDataHora.FORMATO_TEMPO.HORA_SISTEMA));
        }
        if (getHorarioFim() != null) {
            identificadorBuilder.append(UtilCRCDataHora.getDataHoraString(getHorarioFim(), UtilCRCDataHora.FORMATO_TEMPO.HORA_SISTEMA));
        }
        if (getDiasDaSemana() != null) {
            identificadorBuilder.append(getDiasDaSemana());
        }
        if (getTiposAgendamentosPublicos() != null) {
            getTiposAgendamentosPublicos().stream().map(tp -> tp.toString()).forEach(identificadorBuilder::append);
        }
        return identificadorBuilder.toString();
    }

}
