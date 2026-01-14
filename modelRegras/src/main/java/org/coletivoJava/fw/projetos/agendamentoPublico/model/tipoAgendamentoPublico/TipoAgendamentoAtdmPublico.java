/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ContextoReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabContextoDeReserva;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Tipo Agenda pública", plural = "Tipos de agendamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoAtendimentoPublico")
@EntityListeners(ListenerEntidadePadrao.class)
public class TipoAgendamentoAtdmPublico extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;
    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_PEQUENA)
    private String imagemPequena;
    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_MEDIA)
    private String imagemMedia;
    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_GRANDE)
    private String imagemGrande;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Minutos para o atendimento", valorMinimo = 2, descricao = "Minutos programados para este tipo de atedimento")
    private int minutosProgramadosAtendimento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Minutos de espaçamento", valorMinimo = 0, valorMaximo = 300, descricao = "Algumas atividades precisam de um espaço entre elas, exemplo: uma visita de clientes precisa de 30 minutos reservados ao translado até o local")
    private int minutosAnteriorAReserva;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Minutos Antecedencia", descricao = "Exemplo: Este tipo de agendamento, só pode ser realizado com [600 minutos -> (10 Horas)] de antecedencia ")
    private int antecedenciaNovaReservaMinutos = 180;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL,
            valorMaximo = 1,
            somenteLeitura = true,
            descricao = "O fator de ocupação, define se o atendidimento se dará para uma ou mais pessoas, sendo o fator 1 reserva o horário inteiro para 1 pessoa, 0.5 divide o atendimento para 2 pessoas no mesmo horário ou 0.01 100 pessoas (Para o caso de palestras, e outros tipos de atendimento simultãneo)")
    private double fatorOcupacao = 1;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true, descricao = "Define se a reserva ocupará um espaço físico, ou a agenda de um consultor, esta opção permanecerá desativada nesta versão")
    @ManyToOne(targetEntity = ContextoReserva.class)
    private ContextoReserva contextoReserva = FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro();

    @Column(nullable = false, updatable = false, insertable = false, length = 500)
    private String tipoAtendimentoPublico;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getImagemPequena() {
        return imagemPequena;
    }

    public void setImagemPequena(String imagemPequena) {
        this.imagemPequena = imagemPequena;
    }

    public String getImagemMedia() {
        return imagemMedia;
    }

    public void setImagemMedia(String imagemMedia) {
        this.imagemMedia = imagemMedia;
    }

    public String getImagemGrande() {
        return imagemGrande;
    }

    public void setImagemGrande(String imagemGrande) {
        this.imagemGrande = imagemGrande;
    }

    public int getDuracaoAtendenteMinutos() {
        return minutosProgramadosAtendimento;
    }

    public void setDuracaoAtendenteMinutos(int duracaoAtendenteMinutos) {
        this.minutosProgramadosAtendimento = duracaoAtendenteMinutos;
    }

    public int getAntecedenciaNovaReservaMinutos() {
        return antecedenciaNovaReservaMinutos;
    }

    public void setAntecedenciaNovaReservaMinutos(int antecedenciaNovaReservaMinutos) {
        this.antecedenciaNovaReservaMinutos = antecedenciaNovaReservaMinutos;
    }

    public String getTipoAtendimentoPublico() {
        return tipoAtendimentoPublico;
    }

    public void setTipoAtendimentoPublico(String tipoAtendimentoPublico) {
        this.tipoAtendimentoPublico = tipoAtendimentoPublico;
    }

    public double getFatorOcupacao() {
        return fatorOcupacao;
    }

    public void setFatorOcupacao(double fatorOcupacao) {
        this.fatorOcupacao = fatorOcupacao;
    }

    public ContextoReserva getContextoReserva() {
        return contextoReserva;
    }

    public void setContextoReserva(ContextoReserva contextoReserva) {
        this.contextoReserva = contextoReserva;
    }

    public boolean isUmAtendimentoRemoto() {
        return !(this instanceof TipoAgendamentoAtdmPresencial);
    }

    public TipoAgendamentoAtdmPresencial getComoReservaPresencial() {
        return (TipoAgendamentoAtdmPresencial) this;
    }

    public TipoAgendamentoAtdmRemoto getComoReservaVideoConferencia() {
        return (TipoAgendamentoAtdmRemoto) this;
    }

    public int getMinutosProgramadosAtendimento() {
        return minutosProgramadosAtendimento;
    }

    public void setMinutosProgramadosAtendimento(int minutosProgramadosAtendimento) {
        this.minutosProgramadosAtendimento = minutosProgramadosAtendimento;
    }

    public int getMinutosAnteriorAReserva() {
        return minutosAnteriorAReserva;
    }

    public void setMinutosAnteriorAReserva(int minutosAnteriorAReserva) {
        this.minutosAnteriorAReserva = minutosAnteriorAReserva;
    }

}
