/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.categoriaMailRecebido;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Categorias Emails Recebidos", tags = "Categoria Email Recebido", fabricaVinculada = FabCategoriaEmailRecebido.class)
public class CategoriaEmailRecebido extends EntidadeSimplesORM implements ComoEntidadeVinculadoAEnum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    @Enumerated(EnumType.ORDINAL)
    private FabCategoriaEmailRecebido categoriaFab;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        categoriaFab = (FabCategoriaEmailRecebido) pFabrica;
    }

    @Override
    public FabCategoriaEmailRecebido getEnumVinculado() {
        return categoriaFab;
    }

    public FabCategoriaEmailRecebido getCategoriaFab() {
        return categoriaFab;
    }

    public void setCategoriaFab(FabCategoriaEmailRecebido categoriaFab) {
        this.categoriaFab = categoriaFab;
    }

}
