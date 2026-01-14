/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 *
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Tipos de pesquisa", tags = "tipo de Pesquisa", fabricaVinculada = FabTipoPesquisaLeads.class, icone = "fa fa-search")
public class TipoPesquisaLead extends EntidadeSimplesORM implements ComoEntidadeVinculadoAEnum {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descritivo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoPesquisaLeads tipoPesquisa;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public FabTipoPesquisaLeads getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(FabTipoPesquisaLeads tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        tipoPesquisa = (FabTipoPesquisaLeads) pFabrica;
    }

    @Override
    public FabTipoPesquisaLeads getEnumVinculado() {
        return tipoPesquisa;
    }

}
