/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.integracoes.modelController.socialAutenticador;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author SalvioF
 */
@InfoObjetoSB(tags = {"tipo login Social"}, plural = "Tipos de Login Social", fabricaVinculada = FabTipoAutenticacaoSocial.class)
public class TipoCredencialSocial extends EntidadeSimples implements ComoEntidadeVinculadoAEnum {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoAutenticacaoSocial tipo;

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

    public FabTipoAutenticacaoSocial getTipo() {
        return tipo;
    }

    public void setTipo(FabTipoAutenticacaoSocial tipo) {
        this.tipo = tipo;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        setTipo((FabTipoAutenticacaoSocial) pFabrica);
    }

    @Override
    public FabTipoAutenticacaoSocial getEnumVinculado() {
        return tipo;
    }

}
