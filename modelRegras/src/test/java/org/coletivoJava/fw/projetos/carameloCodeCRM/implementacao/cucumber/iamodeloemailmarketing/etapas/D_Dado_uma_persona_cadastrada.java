package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Dado;
import java.util.List;
import javax.validation.constraints.AssertTrue;
import org.junit.Assert;

public class D_Dado_uma_persona_cadastrada {

    @Dado(EtapasIAModeloEmailMarketing.DADO_UMA_PERSONA_CADASTRADA)
    public void implementacaoEtapa() {

        List<Persona> lista = UtilSBPersistencia.getListaTodos(Persona.class);
        Assert.assertFalse("nova Persona não foi encontrada", lista.isEmpty());

    }
}
