/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.contrato;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(descricao = "Proposta", icone = "", plural = "Propostas", generoFeminino = true, tags = "Proposta")
public class Proposta extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(descricao = "Identificador", label = "Id", tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(descricao = "Nome", label = "Nome", tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(descricao = "Descrição", label = "Descrição", tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;

    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @InfoCampo(descricao = "Prospecto", label = "Prospecto", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    Pessoa prospecto;

    @ManyToOne(targetEntity = AtividadeCRM.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @InfoCampo(descricao = "Atividade", label = "Atividade", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    AtividadeCRM atividade;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(PessoaJuridica prospecto) {
        this.prospecto = prospecto;
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
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

}
