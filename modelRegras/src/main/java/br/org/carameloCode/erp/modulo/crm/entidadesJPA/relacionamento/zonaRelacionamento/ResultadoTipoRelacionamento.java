/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = "Resultado", plural = "Resultados")
@Entity
public class ResultadoTipoRelacionamento extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    private String corResultado;
    private String classeResultado;
    private FabResultadoTipoRelacionamento fabricaResultado;

    public ResultadoTipoRelacionamento() {
    }

    public ResultadoTipoRelacionamento(FabResultadoTipoRelacionamento fab) {
        fabricaResultado = fab;
    }

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

    public String getCorResultado() {
        return corResultado;
    }

    public void setCorResultado(String corResultado) {
        this.corResultado = corResultado;
    }

    public String getClasseResultado() {
        return classeResultado;
    }

    public void setClasseResultado(String classeResultado) {
        this.classeResultado = classeResultado;
    }

    public FabResultadoTipoRelacionamento getFabricaResultado() {
        return fabricaResultado;
    }

    public void setFabricaResultado(FabResultadoTipoRelacionamento fabricaResultado) {
        this.fabricaResultado = fabricaResultado;
    }

}
