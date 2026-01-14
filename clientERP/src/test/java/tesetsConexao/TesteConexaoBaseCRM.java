package tesetsConexao;

import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.ApiIntegracaoRestfulimpl;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;
import testesFW.TesteJUnitBasicoSemPersistencia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author salvio
 */
public class TesteConexaoBaseCRM extends TesteJUnitBasicoSemPersistencia {

    private static ApiIntegracaoRestfulimpl interacaoERP = (ApiIntegracaoRestfulimpl) ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto();

    @Test
    public void teste() {

        System.out.println(SBCore.getNomeProjeto());
        //   CRMCasaNovaDigitalimpl erpCRM = (CRMCasaNovaDigitalimpl) ERPCrm.CASA_NOVA_DIGITAL.getImplementacaoDoContexto();
        interacaoERP.getSistemaAtual();
        UtilTesteServicoRestfull.iniciarServicoRecepcaoCodigo();
        //        erpCRM.getSistema();
        //        erpCRM.getDadoCRM("06321298670", "33");
        //        erpCRM.getDadoCRM("06321298670", "urlServidorEMail");
        ApiIntegracaoRestfulimpl apiRESFullimpl = (ApiIntegracaoRestfulimpl) ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto();
        apiRESFullimpl.getSistemaAtual();
        //      List<String> acoes = apiRESFullimpl.getAcoesDisponiveis(erpCRM.getSistema());

    }

}
