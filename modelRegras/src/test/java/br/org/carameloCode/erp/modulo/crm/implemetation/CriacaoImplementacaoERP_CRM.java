/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.implemetation;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import org.apache.logging.log4j.LogManager;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;
import org.junit.Test;
import testes.geradorCodigo.erp.GeradorERPImplementacaoContexto;
import testes.geradorCodigo.erp.dto.GeradorDTOInterface;
import testes.geradorCodigo.erp.dto.GeradorDTOPojo;
import testes.geradorCodigo.erp.dto.GeradorDTOProcessador;

/**
 *
 * @author salvio
 */
public class CriacaoImplementacaoERP_CRM extends TesteCRMCarameloCode {

    public CriacaoImplementacaoERP_CRM() {
    }

    @Test
    public void criarImplementacao() {

        try {

            new GeradorERPImplementacaoContexto(ERPCrm.CARAMELO_CODE_PADRAO).salvarEmDiretorioPadraCASO_NAO_EXISTA();

            for (Class entidade : ERPCrm.CARAMELO_CODE_PADRAO.getInterfacesDeEntidade()) {
                GeradorDTOInterface geradorInterface = new GeradorDTOInterface(ERPCrm.CARAMELO_CODE_PADRAO, entidade);
                geradorInterface.salvarEmDiretorioPadraoSubstituindoAnterior();
                GeradorDTOPojo geradorPojo = new GeradorDTOPojo(ERPCrm.CARAMELO_CODE_PADRAO, entidade);
                geradorPojo.salvarEmDiretorioPadraCASO_NAO_EXISTA();
                GeradorDTOProcessador geradorProcessador = new GeradorDTOProcessador(ERPCrm.CARAMELO_CODE_PADRAO, entidade);
                geradorProcessador.salvarEmDiretorioPadraCASO_NAO_EXISTA();

            }
        } catch (Throwable t) {
            LogManager.getLogger(LogPadraoSB.class).error("Erro Criando anotações", t);
        }
    }

}
