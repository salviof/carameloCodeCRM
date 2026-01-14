/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo Parametro Whatsapp", plural = "Tipos de parametro de Whtzap")
public class TipoParametroWhtzapMkt extends EntidadeSimplesORM {

    @Id
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoParametroMsgWhatzap tipoParametroEnum;

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

    public FabTipoParametroMsgWhatzap getTipoParametroEnum() {
        return tipoParametroEnum;
    }

    public void setTipoParametroEnum(FabTipoParametroMsgWhatzap tipoParametroEnum) {
        this.tipoParametroEnum = tipoParametroEnum;
    }

}
