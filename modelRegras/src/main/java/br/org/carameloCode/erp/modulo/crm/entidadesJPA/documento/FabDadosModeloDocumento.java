/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public class FabDadosModeloDocumento {

    public static ModeloDocumentoCRM getModeloByAtividade(AtividadeCRM pAtividade, EntityManager pEm) {
        return (ModeloDocumentoCRM) UtilSBPersistencia.getRegistroByJPQL("from ModeloDocumentoCRM where tipoAtividadeVinculada.id =" + pAtividade.getTipoAtividade().getId(), ModeloDocumentoCRM.class, pEm);
    }

}
