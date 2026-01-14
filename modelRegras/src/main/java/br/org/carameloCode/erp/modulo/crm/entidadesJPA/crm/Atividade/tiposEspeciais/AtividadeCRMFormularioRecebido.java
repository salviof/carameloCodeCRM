/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Formulário recebido", plural = "Formulário Recebido", modulo = ERPCrm.NOME_MODULO_ERP)
public class AtividadeCRMFormularioRecebido extends AtividadeCRM {

    private String codigoChat;
    private String codigoResposta;

    public String getCodigoChat() {
        return codigoChat;
    }

    public void setCodigoChat(String codigoChat) {
        this.codigoChat = codigoChat;
    }

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }

}
