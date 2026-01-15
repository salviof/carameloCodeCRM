/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmail;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = {"Arquivo recebido email "}, plural = "Apresentações CRM", icone = "fa fa-file-powerpoint-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class ArquivoAnexadoEmailRecebido extends ArquivoAnexado {

    @ManyToOne(targetEntity = EmailCrm.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private EmailCrm emailCrm;

    public EmailCrm getEmailCrm() {
        return emailCrm;
    }

    public void setEmailCrm(EmailCrm emailCrm) {
        this.emailCrm = emailCrm;

    }

}
