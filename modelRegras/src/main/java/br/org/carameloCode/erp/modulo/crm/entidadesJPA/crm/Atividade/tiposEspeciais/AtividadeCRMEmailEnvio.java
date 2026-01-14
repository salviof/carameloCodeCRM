/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

@Entity
@InfoObjetoSB(tags = "Envio de e-mail", plural = "Envio de e-mail", modulo = ERPCrm.NOME_MODULO_ERP)
public class AtividadeCRMEmailEnvio extends AtividadeCRM {

    private String codigoImap;

    public String getCodigoImap() {
        return codigoImap;
    }

    public void setCodigoImap(String codigoImap) {
        this.codigoImap = codigoImap;
    }

}
