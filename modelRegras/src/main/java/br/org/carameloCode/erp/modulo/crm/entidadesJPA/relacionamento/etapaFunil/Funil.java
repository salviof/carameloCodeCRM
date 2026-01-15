/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Funil de relacionamento", plural = "Funís de relacionamento")
public class Funil extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeFunil;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;

    @OneToMany(mappedBy = "funil")
    @OrderBy(value = "peso ")
    private List<MetaRelacionamento> metas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFunil() {
        return nomeFunil;
    }

    public void setNomeFunil(String nomeFunil) {
        this.nomeFunil = nomeFunil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
