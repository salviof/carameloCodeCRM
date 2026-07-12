package br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.api.model.integracaolink.CPIntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPersistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRMTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class TipoDadoCrmLinkIntegracaoTest {

    @Test
    public void teste() {
        //Nas classes de ambiente padrão do sistema  modo desenvolvimento significa execuções via JUNIT, HOmologação Jetty na sua maquina, e Produção na Web
        SBCore.configurar(new ConfiguradorCoreCRMTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        SBPersistencia.configuraJPA(new ConfigPersistenciaCrmCarameloCode(), true, false);
        UtilCRCEmail.configurar(new ConfigEmailServersProjeto("mail.casanovadigital.com.br", "contato@casanovadigital.com.br", "acasadigital@2017"));
        MapaObjetosProjetoAtual.adcionarObjeto(PesquisaAtividade.class);
        MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoLink.class);
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        List<Pessoa> lista = UtilSBPersistencia.getListaTodos(Pessoa.class, em);

        Pessoa pessoa = lista.get(0);
        pessoa.getCPinst(CPPessoa.integracoeslink).getValor();

        IntegracaoLink integracao = pessoa.getIntegracoesLink().get(0);

        try {
            integracao.getCPinst(CPIntegracaoLink.url).setValorSeValido("https://fatura.casanovadiital.com.br");
        } catch (ErroValidacao ex) {
            Logger.getLogger(TipoDadoCrmLinkIntegracaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(integracao.getCPinst(CPIntegracaoLink.url).getValor());

    }

}
