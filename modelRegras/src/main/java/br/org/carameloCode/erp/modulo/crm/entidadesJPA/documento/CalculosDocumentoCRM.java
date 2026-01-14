/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum CalculosDocumentoCRM implements ItfCalculos {
    documentoFoiEnviado;

    @Override
    public Object getValor(Object... pEntidade) {

        DocumentoAtividadeCRM doc;
        try {
            doc = (DocumentoAtividadeCRM) pEntidade[0];
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Chamada de calculo sem definir Objeto", t);
            return null;
        }

        switch (this) {
            case documentoFoiEnviado:
                break;
            default:
                throw new AssertionError(this.name());

        }

        return null;

    }

}
