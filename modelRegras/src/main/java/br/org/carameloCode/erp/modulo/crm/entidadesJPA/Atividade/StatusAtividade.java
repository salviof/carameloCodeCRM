/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
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
@InfoObjetoSB(tags = {"Status Atividade"}, plural = "Status de Atividade", fabricaVinculada = FabStatusAtividade.class,
         modulo = ERPCrm.NOME_MODULO_ERP)
@Entity
public class StatusAtividade extends EntidadeSimplesORM {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @Id
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;

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

}
