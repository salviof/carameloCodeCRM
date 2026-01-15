package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotrespostas.EtapasFluxoTypebotRespostas;
import cucumber.api.java.pt.Entao;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.FluxoTypebotRespostas;
import org.junit.Assert;

public class D_Entao_os_dados_dinamicos_sao_atualizados {

    @Entao(EtapasFluxoTypebotRespostas.E_OS_DADOS_DINAMICOS_SAO_ATUALIZADOS)
    public void implementacaoEtapa() {
        FluxoTypebotRespostas.renovarConexaoEntityManagerEscopoTeste();
        ConsultaDinamicaDeEntidade novaConsutla = new ConsultaDinamicaDeEntidade(Pessoa.class, FluxoTypebotRespostas.getEM());
        novaConsutla.addcondicaoCampoIgualA(CPPessoa.email, "samuel@casanovadigital.com.br");
        Pessoa samuel = novaConsutla.getPrimeiroRegistro();
        Assert.assertNotNull("Samuel não encontrado", samuel);

        Assert.assertTrue("campo tipo obs nao encontrado", samuel.getDadosColetados()
                .stream().filter(dc -> dc.getTipoDadoCRM().getNome().equals("obs")).findFirst().isPresent());

        DadoCRM dadoCRM = samuel.getDadosColetados().stream().filter(dc -> dc.getTipoDadoCRM().getNome().equals("obs")).findFirst().get();
        Assert.assertNotNull("Valor obs não é o valor esperado", dadoCRM.getValor());
    }
}
