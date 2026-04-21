/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class ModuloCRMAplicacaoTest extends TesteCRMCarameloCodeSemLimparBanco {

    public ModuloCRMAplicacaoTest() {
    }

    /**
     * Test of receberEmailsTodos method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testReceberEmailsTodos() {
        System.out.println("receberEmailsTodos");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.receberEmailsTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of relacionamentoPorInerciaTodos method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testRelacionamentoPorInerciaTodos() {
        System.out.println("relacionamentoPorInerciaTodos");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.relacionamentoPorInerciaTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alteracaoDeRelacionamentoPorInercia method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAlteracaoDeRelacionamentoPorInercia() {
        System.out.println("alteracaoDeRelacionamentoPorInercia");
        HistoricoRelacionamento pHistorico = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.alteracaoDeRelacionamentoPorInercia(pHistorico);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviarEmailProgramado method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testEnviarEmailProgramado() {
        System.out.println("enviarEmailProgramado");
        EnvioEmail pEmail = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.enviarEmailProgramado(pEmail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializarTarefasAgendadas method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testInicializarTarefasAgendadas() {
        System.out.println("inicializarTarefasAgendadas");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.inicializarTarefasAgendadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeAlterarRElacionamentoPessoa method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeAlterarRElacionamentoPessoa() {
        System.out.println("atividadeAlterarRElacionamentoPessoa");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeAlterarRElacionamentoPessoa(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeAlteracaoRelacionamentoAcoesAutonomas method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeAlteracaoRelacionamentoAcoesAutonomas() {
        System.out.println("atividadeAlteracaoRelacionamentoAcoesAutonomas");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeAlteracaoRelacionamentoAcoesAutonomas(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeCriarChatBot method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeCriarChatBot() {
        System.out.println("atividadeCriarChatBot");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeCriarChatBot(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeEnviarMensagemWhatsapp method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeEnviarMensagemWhatsapp() {
        System.out.println("atividadeEnviarMensagemWhatsapp");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeEnviarMensagemWhatsapp(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeAcoesAtomaticasPosConclusao method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeAcoesAtomaticasPosConclusao() {
        System.out.println("atividadeAcoesAtomaticasPosConclusao");
        AtividadeCRM pAtividadeOriginal = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeAcoesAtomaticasPosConclusao(pAtividadeOriginal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeConclusaoAgendarNova method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeConclusaoAgendarNova() {
        System.out.println("atividadeConclusaoAgendarNova");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeConclusaoAgendarNova(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeConclusaoAcaoAtomaticaAlterarTags method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeConclusaoAcaoAtomaticaAlterarTags() {
        System.out.println("atividadeConclusaoAcaoAtomaticaAlterarTags");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeConclusaoAcaoAtomaticaAlterarTags(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atividadeConcluisaoEmailMktTransacional method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testAtividadeConcluisaoEmailMktTransacional() {
        System.out.println("atividadeConcluisaoEmailMktTransacional");
        AtividadeCRM pAtividade = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.atividadeConcluisaoEmailMktTransacional(pAtividade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formularioTypebotProcessar method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testFormularioTypebotProcessar() {
        System.out.println("formularioTypebotProcessar");
        TipoFormulario pTipo = null;
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.formularioTypebotProcessar(pTipo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of respostasFormularioSincronizar method, of class
     * ModuloCRMAplicacao.
     */
    @Test
    public void testRespostasFormularioSincronizar() {
        System.out.println("respostasFormularioSincronizar");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.respostasFormularioSincronizar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leadsUrgentes method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testLeadsUrgentes() {
        System.out.println("leadsUrgentes");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.leadsUrgentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sincronizarChamadasPabx method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testSincronizarChamadasPabx() {
        System.out.println("sincronizarChamadasPabx");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.sincronizarChamadasPabx();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
