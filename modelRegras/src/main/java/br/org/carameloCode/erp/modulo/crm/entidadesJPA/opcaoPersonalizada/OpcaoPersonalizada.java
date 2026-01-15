/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.opcaoPersonalizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = "Opção Personalizaca", plural = "Opções Personalizadas")
public class OpcaoPersonalizada extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @Column(columnDefinition = "TEXT", length = 8000)
    private String descricao;

    @ManyToOne(targetEntity = TipoDadoCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoDadoCRM tipoDado;

    private String codigoGrupoOpcoes;

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

    public String getCodigoGrupoOpcoes() {
        return codigoGrupoOpcoes;
    }

    public void setCodigoGrupoOpcoes(String codigoGrupoOpcoes) {
        this.codigoGrupoOpcoes = codigoGrupoOpcoes;
    }

    public TipoDadoCRM getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDadoCRM tipoDado) {
        this.tipoDado = tipoDado;
    }

}
