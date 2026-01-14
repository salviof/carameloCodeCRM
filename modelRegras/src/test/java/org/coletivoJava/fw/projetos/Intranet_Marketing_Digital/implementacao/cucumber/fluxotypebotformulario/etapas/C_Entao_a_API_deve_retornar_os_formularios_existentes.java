package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.etapas;

import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.FluxoTypebotFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.TipoFormulario;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotformulario.EtapasFluxoTypebotFormulario;
import cucumber.api.java.pt.Entao;
import java.util.List;
import org.junit.Assert;

public class C_Entao_a_API_deve_retornar_os_formularios_existentes {

    @Entao(EtapasFluxoTypebotFormulario.ENTAO_A_API_DEVE_RETORNAR_OS_FORMULARIOS_EXISTENTES)
    public void implementacaoEtapa() {
        List<TipoFormulario> formularios = UtilSBPersistencia.getListaTodos(TipoFormulario.class, FluxoTypebotFormulario.getEM());
        Assert.assertTrue("Formularios não encotnrados", !formularios.isEmpty());
        for (TipoFormulario tp : formularios) {
            System.out.println(tp.getNome());
            System.out.println(tp.getUrlPublica());
        }
    }
}
