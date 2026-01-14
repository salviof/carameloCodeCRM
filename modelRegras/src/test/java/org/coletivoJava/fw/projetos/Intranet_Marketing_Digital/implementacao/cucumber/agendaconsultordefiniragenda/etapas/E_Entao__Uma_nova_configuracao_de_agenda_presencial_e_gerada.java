package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import cucumber.api.java.pt.Entao;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.CPDisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import static org.junit.Assert.assertFalse;
import static testesFW.TesteJunitSBPersistencia.getEM;
import testesFW.cucumber.TesteIntegracaoFuncionalidadeCucumber;

public class E_Entao__Uma_nova_configuracao_de_agenda_presencial_e_gerada {

    @Entao(EtapasAgendaConsultorDefinirAgenda.ENTAO_UMA_NOVA_CONFIGURACAO_DE_AGENDA_PRESENCIAL_E_GERADA)
    public void implementacaoEtapa() {
        TesteIntegracaoFuncionalidadeCucumber.renovarConexaoEntityManagerEscopoTeste();
        List<DisponibilidadeAtdmtPublico> disponibilidades = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class, getEM()).
                addCondicaoManyToManyContendoObjeto(CPDisponibilidadeAtdmtPublico.atendentesdisponiveis,
                        SBCore.getUsuarioLogado())
                .addCondicaoManyToManyContendoObjeto(CPDisponibilidadeAtdmtPublico.tiposagendamentospublicos, FabTipoAgendamentoPublicoCrm.PRESENCIAL.getRegistro()).resultadoRegistros();
        assertFalse("Não foi encontrado uma regra de disponibilidade de atendimento público", disponibilidades.isEmpty());
    }
}
