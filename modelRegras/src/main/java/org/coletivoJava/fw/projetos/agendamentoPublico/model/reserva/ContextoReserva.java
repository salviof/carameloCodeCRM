/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Contextos de Agendamento", tags = "Contexto de Agendamento",
        fabricaVinculada = FabContextoDeReserva.class)
public class ContextoReserva extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    private String descricao;
    private double fatorDeOcupacaoAgenda = 1;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabContextoDeReserva fabrica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getFatorDeOcupacaoAgenda() {
        return fatorDeOcupacaoAgenda;
    }

    public void setFatorDeOcupacaoAgenda(double fatorDeOcupacaoAgenda) {
        this.fatorDeOcupacaoAgenda = fatorDeOcupacaoAgenda;
    }

    public FabContextoDeReserva getFabrica() {
        return fabrica;
    }

    public void setFabrica(FabContextoDeReserva fabrica) {
        this.fabrica = fabrica;
    }

}
