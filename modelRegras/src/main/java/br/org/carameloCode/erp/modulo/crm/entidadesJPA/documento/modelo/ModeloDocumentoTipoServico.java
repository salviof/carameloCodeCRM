/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Modelo de Documento de Servico"}, plural = "Modelos de documento de serviço")
public class ModeloDocumentoTipoServico extends ModeloDocumentoCRM {

    @ManyToOne(targetEntity = TipoServico.class)
    private TipoServico tipoServico;

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

}
