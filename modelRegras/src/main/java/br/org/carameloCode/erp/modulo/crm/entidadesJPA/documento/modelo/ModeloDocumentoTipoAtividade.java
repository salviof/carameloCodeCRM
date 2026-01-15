/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Modelo documento de Atividade"}, plural = "Modelos de documento de Atividade", modulo = ERPCrm.NOME_MODULO_ERP)
public class ModeloDocumentoTipoAtividade extends ModeloDocumentoCRM {

    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    @InfoCampo(label = "Tipo de Atividade")
    private TipoAtividadeCRM tipoAtividadeVinculada;

    public TipoAtividadeCRM getTipoAtividadeVinculada() {
        return tipoAtividadeVinculada;
    }

    public void setTipoAtividadeVinculada(TipoAtividadeCRM tipoAtividadeVinculada) {
        this.tipoAtividadeVinculada = tipoAtividadeVinculada;
    }

}
