/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
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
import javax.persistence.Transient;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = {"Subpasta"}, plural = "SubPastas", icone = "fa fa-folder-open-o", modulo = ERPCrm.NOME_MODULO_ERP)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSubPasta")
@EntityListeners(ListenerEntidadePadrao.class)
public class SubPastaPrivada extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @Column(nullable = false, updatable = false, insertable = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String tipoSubPasta;

    @ManyToOne(targetEntity = SubPastaPrivada.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private SubPastaPrivada pastaPai;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValorLogico(nomeCalculo = "Pasta compartilhada?")
    private boolean pastaPublica;

    @ManyToOne(targetEntity = Pessoa.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private Pessoa pessoa;

    @InfoCampoValorLogico(nomeCalculo = "Migalhas de pão")
    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, somenteLeitura = true)
    private List<SubPastaPrivada> migalhasDePao;

    public SubPastaEquipe getComoSubPastaEquipe() {
        return (SubPastaEquipe) this;
    }

    public SubpastaCliente getComoSubPastaCliente() {
        return (SubpastaCliente) this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSubPasta() {
        return tipoSubPasta;
    }

    public void setTipoSubPasta(String tipoSubPasta) {
        this.tipoSubPasta = tipoSubPasta;
    }

    public boolean isPastaPublica() {
        return pastaPublica;
    }

    public void setPastaPublica(boolean pastaPublica) {
        this.pastaPublica = pastaPublica;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public SubPastaPrivada getPastaPai() {
        return pastaPai;
    }

    public void setPastaPai(SubPastaPrivada pastaPai) {
        this.pastaPai = pastaPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SubPastaPrivada> getMigalhasDePao() {
        return migalhasDePao;
    }

    public void setMigalhasDePao(List<SubPastaPrivada> migalhasDePao) {
        this.migalhasDePao = migalhasDePao;
    }

}
