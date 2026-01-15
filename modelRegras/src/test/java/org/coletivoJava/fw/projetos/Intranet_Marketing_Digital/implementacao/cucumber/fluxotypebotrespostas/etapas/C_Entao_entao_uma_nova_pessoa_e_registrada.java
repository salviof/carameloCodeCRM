package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotrespostas.EtapasFluxoTypebotRespostas;
import cucumber.api.java.pt.Entao;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.FluxoTypebotRespostas;
import org.junit.Assert;

public class C_Entao_entao_uma_nova_pessoa_e_registrada {

    @Entao(EtapasFluxoTypebotRespostas.ENTAO_ENTAO_UMA_NOVA_PESSOA_E_REGISTRADA)
    public void implementacaoEtapa() {
        FluxoTypebotRespostas.renovarConexaoEntityManagerEscopoTeste();
        ConsultaDinamicaDeEntidade novaConsutla = new ConsultaDinamicaDeEntidade(Pessoa.class, FluxoTypebotRespostas.getEM());
        novaConsutla.addcondicaoCampoIgualA(CPPessoa.email, "samuel@casanovadigital.com.br");
        Pessoa samuel = novaConsutla.getPrimeiroRegistro();
        Assert.assertNotNull("Samuel não encontrado", samuel);
    }
}
