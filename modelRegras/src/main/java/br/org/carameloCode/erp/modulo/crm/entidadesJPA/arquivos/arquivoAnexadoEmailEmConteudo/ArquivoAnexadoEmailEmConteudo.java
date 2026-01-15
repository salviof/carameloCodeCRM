/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmailEmConteudo;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmail.ArquivoAnexadoEmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;

/**
 *
 * @author salviofurbino
 * @since 13/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(plural = "Arquivos de conteúdo", tags = {"Conteúdo"}, modulo = ERPCrm.NOME_MODULO_ERP)
public class ArquivoAnexadoEmailEmConteudo extends ArquivoAnexadoEmailRecebido {

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
