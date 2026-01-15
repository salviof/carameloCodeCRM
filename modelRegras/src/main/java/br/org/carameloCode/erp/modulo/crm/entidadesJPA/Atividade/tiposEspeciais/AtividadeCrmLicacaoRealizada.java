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
@InfoObjetoSB(tags = "Ligação realizada", plural = "Ligações realizadas", modulo = ERPCrm.NOME_MODULO_ERP)
public class AtividadeCrmLicacaoRealizada extends AtividadeCRM {

    private String codigoPABX;

    public String getCodigoPABX() {
        return codigoPABX;
    }

    public void setCodigoPABX(String codigoPABX) {
        this.codigoPABX = codigoPABX;
    }

}
