/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

/**
 *
 * @author SalvioF
 */
public class ModeloEmailTest extends TesteWPCrmCarameloCode {

    public void teste() {

        ModeloEmail modeloEmail = new ModeloEmail();
        ItfCampoInstanciado campo = modeloEmail.getCampoInstanciadoByNomeOuAnotacao("textoModelo");
        campo.getComoTemplate().getOpcoesPalavraChave();

    }
}
