package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.TipoFormulario;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotrespostas.EtapasFluxoTypebotRespostas;
import cucumber.api.java.pt.Dado;
import java.lang.UnsupportedOperationException;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.FluxoTypebotFormulario;
import org.junit.Assert;

public class A_Dado_que_existe_um_formulario_a_ser_processado_no_banco_de_dados {

    @Dado(EtapasFluxoTypebotRespostas.DADO_QUE_EXISTE_UM_FORMULARIO_A_SER_PROCESSADO_NO_BANCO_DE_DADOS)
    public void implementacaoEtapa() {
        List<TipoFormulario> formularios = UtilSBPersistencia.getListaTodos(TipoFormulario.class, FluxoTypebotFormulario.getEM());
        Assert.assertTrue("Formulario a ser processado não encontrado", formularios.stream().filter(f -> f.isIntegrarDados()).findFirst().isPresent());
    }
}
