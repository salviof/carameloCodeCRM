package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import cucumber.api.java.pt.Dado;
import javax.validation.constraints.AssertTrue;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class H_Dado__um_chamado_aberto {

    @Dado(EtapasAberturaDeChamadoSimples.DADO_UM_CHAMADO_ABERTO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.renovarConexaoEntityManagerEscopoTeste();
        ChamadoCliente chamado = UtilSBPersistencia.loadEntidade(FluxoChamadoSimples.chamado, FluxoChamadoSimples.getEM());
        Assert.assertEquals("O Status do chamado deveria estar em atendimento", chamado.getStatus(), FabStatusChamado.EM_ATENDIMENTO.getRegistro());
    }
}
