/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.TipoAtividadeCRMAutoExecucao;
import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IncrementGenerator;

/**
 *
 * @author salvio
 */
public class GeradorIdTipoAtividade extends IncrementGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        if (obj instanceof TipoAtividadeCRMAutoExecucao) {
            return ((TipoAtividadeCRMAutoExecucao) obj).getId();
        }
        Serializable valorID = super.generate(s, obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return valorID;
    }

}
