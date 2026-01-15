/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Receção de Email", plural = "Recepção de Email", modulo = ERPCrm.NOME_MODULO_ERP)
public class AtividadeCRMEmailRecepcao extends AtividadeCRM {

    public AtividadeCRMEmailRecepcao() {
        super();
    }

    private String codigoImap;

    public String getCodigoImap() {
        return codigoImap;
    }

    public void setCodigoImap(String codigoImap) {
        this.codigoImap = codigoImap;
    }

}
